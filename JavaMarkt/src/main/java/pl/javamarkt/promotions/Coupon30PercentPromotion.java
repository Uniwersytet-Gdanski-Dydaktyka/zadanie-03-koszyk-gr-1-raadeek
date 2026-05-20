package pl.javamarkt.promotions;

import pl.javamarkt.Product;
import pl.javamarkt.ProductUtils;
import pl.javamarkt.Promotion;

import java.util.List;

public class Coupon30PercentPromotion implements Promotion {
    private final String productCode;

    public Coupon30PercentPromotion(String productCode) {
        if (productCode == null || productCode.isBlank()) {
            throw new IllegalArgumentException("Product code cannot be null or blank");
        }
        this.productCode = productCode;
    }

    @Override
    public void apply(List<Product> products) {
        for (Product product : ProductUtils.clean(products)) {
            if (product.getCode().equals(productCode)) {
                product.setDiscountPrice(product.getDiscountPrice() * 0.7);
                return;
            }
        }
    }

    @Override
    public String getName() {
        return "30% coupon for product " + productCode;
    }
}
