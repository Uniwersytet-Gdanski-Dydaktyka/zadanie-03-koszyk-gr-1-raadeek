package pl.javamarkt;

import java.util.ArrayList;
import java.util.List;

public class PromotionService {

    public void applyPromotions(List<Product> products, List<Promotion> promotions) {
        if (products == null || promotions == null) {
            return;
        }

        for (Promotion promotion : promotions) {
            if (promotion != null) {
                promotion.apply(products);
            }
        }
    }

    public List<Product> applyBestPromotionOrder(List<Product> products, List<Promotion> promotions) {
        if (products == null || products.isEmpty()) {
            return List.of();
        }
        if (promotions == null || promotions.isEmpty()) {
            return ProductUtils.copyProducts(products);
        }

        List<Promotion> cleanPromotions = promotions.stream()
                .filter(promotion -> promotion != null)
                .toList();

        List<List<Promotion>> permutations = generatePermutations(new ArrayList<>(cleanPromotions));
        List<Product> bestProducts = null;
        double bestTotal = Double.MAX_VALUE;

        for (List<Promotion> order : permutations) {
            List<Product> copiedProducts = new ArrayList<>(ProductUtils.copyProducts(products));

            for (Promotion promotion : order) {
                promotion.apply(copiedProducts);
            }

            double total = ProductUtils.sumDiscountPrices(copiedProducts);
            if (total < bestTotal) {
                bestTotal = total;
                bestProducts = copiedProducts;
            }
        }

        return bestProducts == null ? List.of() : bestProducts;
    }

    private List<List<Promotion>> generatePermutations(List<Promotion> promotions) {
        List<List<Promotion>> result = new ArrayList<>();
        permute(promotions, 0, result);
        return result;
    }

    private void permute(List<Promotion> promotions, int index, List<List<Promotion>> result) {
        if (index == promotions.size()) {
            result.add(new ArrayList<>(promotions));
            return;
        }

        for (int i = index; i < promotions.size(); i++) {
            swap(promotions, index, i);
            permute(promotions, index + 1, result);
            swap(promotions, index, i);
        }
    }

    private void swap(List<Promotion> list, int i, int j) {
        Promotion temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}
