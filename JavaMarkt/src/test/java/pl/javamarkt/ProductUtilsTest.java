package pl.javamarkt;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductUtilsTest {

    @Test
    void shouldFindCheapestProduct() {
        List<Product> products = List.of(
                new Product("P1", "Laptop", 300),
                new Product("P2", "Mouse", 50),
                new Product("P3", "Keyboard", 100)
        );

        Product cheapest = ProductUtils.findCheapest(products);

        assertNotNull(cheapest);
        assertEquals("Mouse", cheapest.getName());
    }

    @Test
    void shouldFindMostExpensiveProduct() {
        List<Product> products = List.of(
                new Product("P1", "Laptop", 300),
                new Product("P2", "Mouse", 50),
                new Product("P3", "Keyboard", 100)
        );

        Product mostExpensive = ProductUtils.findMostExpensive(products);

        assertNotNull(mostExpensive);
        assertEquals("Laptop", mostExpensive.getName());
    }

    @Test
    void shouldReturnNCheapestProducts() {
        List<Product> products = List.of(
                new Product("P1", "Laptop", 300),
                new Product("P2", "Mouse", 50),
                new Product("P3", "Keyboard", 100)
        );

        List<Product> result = ProductUtils.findNCheapest(products, 2);

        assertEquals(2, result.size());
        assertEquals("Mouse", result.get(0).getName());
        assertEquals("Keyboard", result.get(1).getName());
    }

    @Test
    void shouldSortByPriceDescendingAndThenNameAscending() {
        List<Product> products = List.of(
                new Product("P1", "Banana", 100),
                new Product("P2", "Apple", 100),
                new Product("P3", "Laptop", 300)
        );

        List<Product> sorted = ProductUtils.sort(products, ProductUtils.DEFAULT_CART_SORTING);

        assertEquals("Laptop", sorted.get(0).getName());
        assertEquals("Apple", sorted.get(1).getName());
        assertEquals("Banana", sorted.get(2).getName());
    }

    @Test
    void shouldHandleNullCollection() {
        assertNull(ProductUtils.findCheapest(null));
        assertEquals(0.0, ProductUtils.sumDiscountPrices(null));
        assertTrue(ProductUtils.findNCheapest(null, 3).isEmpty());
    }

    @Test
    void shouldIgnoreNullProductsInsideCollection() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("P1", "Laptop", 300));
        products.add(null);
        products.add(new Product("P2", "Mouse", 50));

        Product cheapest = ProductUtils.findCheapest(products);

        assertNotNull(cheapest);
        assertEquals("Mouse", cheapest.getName());
    }
}
