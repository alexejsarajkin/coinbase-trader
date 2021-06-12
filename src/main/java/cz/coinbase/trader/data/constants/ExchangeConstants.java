package cz.coinbase.trader.data.constants;

import javax.crypto.Mac;
import java.security.NoSuchAlgorithmException;

public class ExchangeConstants {

    private ExchangeConstants() {
    }

    public static Mac SHARED_MAC;

    static {
        try {
            SHARED_MAC = Mac.getInstance("HmacSHA256");
        } catch (NoSuchAlgorithmException nsaEx) {
            nsaEx.printStackTrace();
        }
    }
}
