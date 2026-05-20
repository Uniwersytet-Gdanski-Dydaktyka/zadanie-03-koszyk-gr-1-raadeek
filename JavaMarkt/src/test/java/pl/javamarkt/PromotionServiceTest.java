package pl.javamarkt;

import org.junit.jupiter.api.Test;
import pl.javamarkt.promotions.CheapestFreePromotion;
import pl.javamarkt.promotions.Coupon30PercentPromotion;
import pl.javamarkt.promotions.FivePercentOver300Promotion;
import pl.javamarkt.promotions.FreeMugOver200Promotion;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PromotionServiceTest {

    @Test
    void shouldApplyFivePercentDiscountWhenTotalIsOver300() {
        List<Product> products = List.of(new Product("P1", "Laptop", 400));

        new FivePercentOver300Promotion().apply(products);

        assertEquals(380.0, products.get(0).getDiscountPrice(), 0.001);
    }

    @Test
    void shouldNotApplyFivePercentDiscountWhenTotalIsNotOver300() {
        List<Product> products = List.of(new Product("P1", "Mouse", 100));

        new FivePercentOver300Promotion().apply(products);

        assertEquals(100.0, products.get(0).getDiscountPrice(), 0.001);
    }

    @Test
    void shouldMakeCheapestProductFreeWhenThereAreAtLeastThreeProducts() {
        List<Product> products = List.of(
                new Product("P1", "Laptop", 300),
                new Product("P2", "Mouse", 50),
                new Product("P3", "Keyboard", 100)
        );

        new CheapestFreePromotion().apply(products);

        Product mouse = products.get(1);
        assertEquals(0.0, mouse.getDiscountPrice(), 0.001);
    }

    @Test
    void shouldAddFreeMugWhenTotalIsOver200() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("P1", "Laptop", 300));

        new FreeMugOver200Promotion().apply(products);

        assertEquals(2, products.size());
        assertEquals("MUG-001", products.get(1).getCode());
        assertEquals(0.0, products.get(1).getPrice(), 0.001);
    }

    @Test
    void shouldApplyThirtyPercentCouponToSelectedProduct() {
        List<Product> products = List.of(
                new Product("P1", "Laptop", 300),
                new Product("P2", "Mouse", 100)
        );

        new Coupon30PercentPromotion("P2").apply(products);

        assertEquals(70.0, products.get(1).getDiscountPrice(), 0.001);
    }

    @Test
    void shouldChooseBestPromotionOrder() {
        List<Product> products = List.of(
                new Product("P1", "Laptop", 250),
                new Product("P2", "Keyboard", 100),
                new Product("P3", "Mouse", 50)
        );

        List<Promotion> promotions = List.of(
                new FivePercentOver300Promotion(),
                new CheapestFreePromotion()
        );

        PromotionService service = new PromotionService();
        List<Product> bestResult = service.applyBestPromotionOrder(products, promotions);
        double total = ProductUtils.sumDiscountPrices(bestResult);

        assertTrue(total <= 332.5);
    }
}
