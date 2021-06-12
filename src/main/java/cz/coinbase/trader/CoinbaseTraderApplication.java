package cz.coinbase.trader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CoinbaseTraderApplication {

    public static void main(String[] args) {
        SpringApplication.run(
                CoinbaseTraderApplication.class,
                args
        );
    }

}
