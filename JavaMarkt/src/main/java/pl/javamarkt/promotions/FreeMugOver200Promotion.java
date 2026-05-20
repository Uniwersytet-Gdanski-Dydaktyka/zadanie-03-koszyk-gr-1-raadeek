package pl.javamarkt.promotions;

import pl.javamarkt.Product;
import pl.javamarkt.ProductUtils;
import pl.javamarkt.Promotion;

import java.util.List;

public class FreeMugOver200Promotion implements Promotion {
    private static final double LIMIT = 200.0;
    private static final String MUG_CODE = "MUG-001";

    @Override
    public void apply(List<Product> products) {
        if (products == null) {
            return;
        }

        List<Product> cleanProducts = ProductUtils.clean(products);
        double total = ProductUtils.sumDiscountPrices(cleanProducts);

        if (total > LIMIT && !containsMug(cleanProducts)) {
            products.add(new Product(MUG_CODE, "Firmowy kubek", 0.0));
        }
    }

    private boolean containsMug(List<Product> products) {
        return products.stream()
                .anyMatch(product -> product.getCode().equals(MUG_CODE));
    }

    @Override
    public String getName() {
        return "Free company mug over 200 zl";
    }
}
