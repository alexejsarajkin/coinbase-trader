package cz.coinbase.trader.converter;

import cz.coinbase.trader.data.type.ProductType;
import org.springframework.core.convert.converter.Converter;

public class StringToProductTypeConverter implements Converter<String, ProductType> {

    @Override
    public ProductType convert(String source) {
        return ProductType.fromString(source);
    }
}
