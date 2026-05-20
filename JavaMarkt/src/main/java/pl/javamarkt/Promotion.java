package pl.javamarkt;

import java.util.List;

public interface Promotion {
    void apply(List<Product> products);

    String getName();
}
