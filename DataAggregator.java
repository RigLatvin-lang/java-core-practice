import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;

public class DataAggregator {
    public ProductInfo aggregateProductInfo(String productName) {

        CompletableFuture<Double> priceFuture = CompletableFuture
                .supplyAsync(() -> fetchPrice(productName))
                .exceptionally(ex -> {
                    System.out.println("Сбой цены: " + ex.getMessage());
                    return 0.0;
                });

        CompletableFuture<String> descriptionFuture = CompletableFuture
                .supplyAsync(() -> fetchDescription(productName))
                .exceptionally(ex -> {
                    System.out.println("Сбой описания: " + ex.getMessage());
                    return "Нет данных";
                });

        CompletableFuture<Double> ratingFuture = CompletableFuture
                .supplyAsync(() -> fetchRating(productName))
                .exceptionally(ex -> {
                    System.out.println("Сбой рейтинга: " + ex.getMessage());
                    return 0.0;
                });
        return CompletableFuture.allOf(priceFuture, descriptionFuture, ratingFuture)
                .thenApply(v -> new ProductInfo(
                        productName,
                        priceFuture.join(),
                        descriptionFuture.join(),
                        ratingFuture.join()))
                .join();
    }

    private double fetchPrice(String productName) {
        simulateLatencyAndFailure("price");
        double price = ThreadLocalRandom.current().nextDouble(100, 1000);
        return Math.round(price * 100) / 100.0;
    }

    private String fetchDescription(String productName) {
        simulateLatencyAndFailure("description");
        return "Описание товара: " + productName;
    }

    private double fetchRating(String productName) {
        simulateLatencyAndFailure("rating");
        double rating = ThreadLocalRandom.current().nextDouble(1, 5);
        return Math.round(rating * 10) / 10.0;
    }

    private void simulateLatencyAndFailure(String serviceName) {
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 3001));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Поток прерван: " + serviceName, e);
        }
        if (ThreadLocalRandom.current().nextDouble() < 0.2) {
            throw new RuntimeException("Сбой сервиса " + serviceName);
        }
    }

    public static void main(String[] args) {
        DataAggregator aggregator = new DataAggregator();
        long start = System.currentTimeMillis();
        ProductInfo info = aggregator.aggregateProductInfo("Ноутбук");
        System.out.println(info);
        System.out.println("Время: " + (System.currentTimeMillis() - start) + " мс");
    }
}