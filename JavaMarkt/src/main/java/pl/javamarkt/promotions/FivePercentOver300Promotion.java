package pl.javamarkt.promotions;

import pl.javamarkt.Product;
import pl.javamarkt.ProductUtils;
import pl.javamarkt.Promotion;

import java.util.List;

public class FivePercentOver300Promotion implements Promotion {
    private static final double LIMIT = 300.0;
    private static final double DISCOUNT = 0.05;

    @Override
    public void apply(List<Product> products) {
        List<Product> cleanProducts = ProductUtils.clean(products);
        double total = ProductUtils.sumDiscountPrices(cleanProducts);

        if (total > LIMIT) {
            for (Product product : cleanProducts) {
                product.setDiscountPrice(product.getDiscountPrice() * (1.0 - DISCOUNT));
            }
        }
    }

    @Override
    public String getName() {
        return "5% discount over 300 zl";
    }
}
