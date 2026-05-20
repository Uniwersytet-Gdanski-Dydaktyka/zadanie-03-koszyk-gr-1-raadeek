package pl.javamarkt;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Cart {
    private final List<Product> products = new ArrayList<>();
    private Comparator<Product> sortingStrategy = ProductUtils.DEFAULT_CART_SORTING;

    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Product> getSortedProducts() {
        return ProductUtils.sort(products, sortingStrategy);
    }

    public void setSortingStrategy(Comparator<Product> sortingStrategy) {
        if (sortingStrategy == null) {
            throw new IllegalArgumentException("Sorting strategy cannot be null");
        }
        this.sortingStrategy = sortingStrategy;
    }

    public double getTotalPrice() {
        return ProductUtils.sumDiscountPrices(products);
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }
}
