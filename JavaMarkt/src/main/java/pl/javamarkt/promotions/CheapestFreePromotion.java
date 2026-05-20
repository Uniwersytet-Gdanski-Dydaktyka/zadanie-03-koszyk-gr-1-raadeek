package pl.javamarkt.promotions;

import pl.javamarkt.Product;
import pl.javamarkt.ProductUtils;
import pl.javamarkt.Promotion;

import java.util.List;

public class CheapestFreePromotion implements Promotion {

    @Override
    public void apply(List<Product> products) {
        List<Product> cleanProducts = ProductUtils.clean(products);

        if (cleanProducts.size() < 3) {
            return;
        }

        Product cheapest = ProductUtils.findCheapest(cleanProducts);
        if (cheapest != null) {
            cheapest.setDiscountPrice(0.0);
        }
    }

    @Override
    public String getName() {
        return "Buy 3 products, cheapest one is free";
    }
}
