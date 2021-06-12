package cz.coinbase.trader.service.impl;

import cz.coinbase.trader.data.constants.Coinbase;
import cz.coinbase.trader.data.dto.AccountBalanceDTO;
import cz.coinbase.trader.data.dto.AccountDTO;
import cz.coinbase.trader.data.type.Currency;
import cz.coinbase.trader.service.AccountService;
import cz.coinbase.trader.service.ExchangeService;
import cz.coinbase.trader.service.ProductService;
import cz.coinbase.trader.service.TelegramService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static cz.coinbase.trader.data.type.Currency.BTC;
import static cz.coinbase.trader.data.type.Currency.EUR;
import static cz.coinbase.trader.data.type.ProductType.BTC_EUR;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final ExchangeService exchangeService;

    private final ProductService productService;

    private final TelegramService telegramService;

    public AccountServiceImpl(ExchangeService exchangeService, ProductService productService, TelegramService telegramService) {
        this.exchangeService = exchangeService;
        this.productService = productService;
        this.telegramService = telegramService;
    }

    @Override
    public List<AccountDTO> getAccounts() {
        return exchangeService.getAsList(
                Coinbase.ACCOUNT_ENDPOINT,
                new ParameterizedTypeReference<>() {
                });
    }

    @Override
    public AccountBalanceDTO getAccountBalance() {
        List<AccountDTO> accounts = getAccounts();

        AccountBalanceDTO accountBalanceDTO = new AccountBalanceDTO();

        Optional<AccountDTO> accountEUR = findAccount(accounts, EUR);
        accountEUR.ifPresent(accountDTO -> accountBalanceDTO.setAccountBalanceEUR(accountDTO.getBalance()));

        Optional<AccountDTO> accountBTC = findAccount(accounts, BTC);
        accountBTC.ifPresent(accountDTO -> accountBalanceDTO.setAccountBalanceBTC(accountDTO.getBalance()));

        return accountBalanceDTO;
    }

    @Override
    @Scheduled(cron = "0 0 10 * * *", zone = "Europe/Prague") // Each day at 10:00
    public void sentAccountBalance() {
        AccountBalanceDTO accountBalance = getAccountBalance();

        BigDecimal productPrice = productService.getProductPrice(BTC_EUR);

        String message = "Account balance EUR: " + String.format("%.2f", accountBalance.getAccountBalanceEUR()) +
                ", BTC: " + String.format("%.4f", accountBalance.getAccountBalanceBTC()) +
                ", Total: " + String.format("%.2f", accountBalance.getAccountBalanceEUR().add(accountBalance.getAccountBalanceBTC().multiply(productPrice)));

        log.info("Account balance - " + message);

        telegramService.sendMessage(message);
    }

    @Override
    public BigDecimal getAccountBalanceEUR() {
        AccountBalanceDTO accountBalance = getAccountBalance();

        BigDecimal productPrice = productService.getProductPrice(BTC_EUR);

        return accountBalance.getAccountBalanceEUR().add(accountBalance.getAccountBalanceBTC().multiply(productPrice));
    }

    private Optional<AccountDTO> findAccount(List<AccountDTO> accounts, Currency currency) {
        return accounts.stream()
                .filter(e -> currency.getValue().equalsIgnoreCase(e.getCurrency()))
                .findFirst();
    }
}
