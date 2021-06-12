package cz.coinbase.trader.service;

import cz.coinbase.trader.data.dto.AccountBalanceDTO;
import cz.coinbase.trader.data.dto.AccountDTO;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {
    List<AccountDTO> getAccounts();

    AccountBalanceDTO getAccountBalance();

    BigDecimal getAccountBalanceEUR();

    void sentAccountBalance();
}
