package pl.javamarkt;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public final class ProductUtils {

    public static final Comparator<Product> DEFAULT_CART_SORTING =
            Comparator.comparingDouble(Product::getDiscountPrice)
                    .reversed()
                    .thenComparing(Product::getName);

    private ProductUtils() {
    }

    public static Product findCheapest(List<Product> products) {
        return clean(products).stream()
                .min(Comparator.comparingDouble(Product::getDiscountPrice))
                .orElse(null);
    }

    public static Product findMostExpensive(List<Product> products) {
        return clean(products).stream()
                .max(Comparator.comparingDouble(Product::getDiscountPrice))
                .orElse(null);
    }

    public static List<Product> findNCheapest(List<Product> products, int n) {
        if (n <= 0) {
            return List.of();
        }
        return clean(products).stream()
                .sorted(Comparator.comparingDouble(Product::getDiscountPrice))
                .limit(n)
                .toList();
    }

    public static List<Product> findNMostExpensive(List<Product> products, int n) {
        if (n <= 0) {
            return List.of();
        }
        return clean(products).stream()
                .sorted(Comparator.comparingDouble(Product::getDiscountPrice).reversed())
                .limit(n)
                .toList();
    }

    public static List<Product> sort(List<Product> products, Comparator<Product> comparator) {
        if (comparator == null) {
            throw new IllegalArgumentException("Comparator cannot be null");
        }
        return clean(products).stream()
                .sorted(comparator)
                .toList();
    }

    public static double sumRegularPrices(List<Product> products) {
        return clean(products).stream()
                .mapToDouble(Product::getPrice)
                .sum();
    }

    public static double sumDiscountPrices(List<Product> products) {
        return clean(products).stream()
                .mapToDouble(Product::getDiscountPrice)
                .sum();
    }

    public static List<Product> clean(List<Product> products) {
        if (products == null) {
            return List.of();
        }
        return products.stream()
                .filter(Objects::nonNull)
                .toList();
    }

    public static List<Product> copyProducts(List<Product> products) {
        return clean(products).stream()
                .map(Product::copy)
                .toList();
    }
}
