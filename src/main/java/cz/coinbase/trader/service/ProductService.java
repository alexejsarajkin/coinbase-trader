package cz.coinbase.trader.service;

import cz.coinbase.trader.data.type.ProductType;

import java.math.BigDecimal;

public interface ProductService {
    BigDecimal getProductPrice(ProductType productType);
}
