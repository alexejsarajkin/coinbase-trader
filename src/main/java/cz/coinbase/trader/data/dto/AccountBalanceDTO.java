package cz.coinbase.trader.data.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountBalanceDTO {
    private BigDecimal accountBalanceEUR;
    private BigDecimal accountBalanceBTC;

    @Override
    public String toString() {
        return "Account balance - " +
                "EUR = " + accountBalanceEUR +
                ", BTC = " + accountBalanceBTC;
    }
}
