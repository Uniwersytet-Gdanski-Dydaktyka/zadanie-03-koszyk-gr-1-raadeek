package pl.javamarkt;

import pl.javamarkt.promotions.CheapestFreePromotion;
import pl.javamarkt.promotions.Coupon30PercentPromotion;
import pl.javamarkt.promotions.FivePercentOver300Promotion;
import pl.javamarkt.promotions.FreeMugOver200Promotion;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Cart cart = new Cart();
        cart.addProduct(new Product("P001", "Laptop", 250.0));
        cart.addProduct(new Product("P002", "Mouse", 50.0));
        cart.addProduct(new Product("P003", "Keyboard", 100.0));

        List<Promotion> promotions = List.of(
                new FivePercentOver300Promotion(),
                new CheapestFreePromotion(),
                new FreeMugOver200Promotion(),
                new Coupon30PercentPromotion("P003")
        );

        PromotionService promotionService = new PromotionService();
        List<Product> bestResult = promotionService.applyBestPromotionOrder(cart.getProducts(), promotions);

        System.out.println("Best result:");
        for (Product product : ProductUtils.sort(bestResult, ProductUtils.DEFAULT_CART_SORTING)) {
            System.out.println(product);
        }
        System.out.println("Total: " + ProductUtils.sumDiscountPrices(bestResult));
    }
}
