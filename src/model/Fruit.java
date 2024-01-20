package model;

public class Fruit {

    protected String fruitId;
    protected String fruitName;
    protected Double fruitPrice;
    protected int quantity;
    protected String origin;

    public Fruit(String fruitId, String fruitName, Double fruitPrice, int quantity, String origin) {
        this.fruitId = fruitId;
        this.fruitName = fruitName;
        this.fruitPrice = fruitPrice;
        this.quantity = quantity;
        this.origin = origin;
    }

    public Fruit() {

    }

    public Fruit(String fruitId, String fruitName, String origin, Double fruitPrice) {
        this.fruitId = fruitId;
        this.fruitName = fruitName;
        this.fruitPrice = fruitPrice;
        this.origin = origin;
    }

    public String getFruitId() {
        return fruitId;
    }

    public void setFruitId(String fruitId) {
        this.fruitId = fruitId;
    }

    public String getFruitName() {
        return fruitName;
    }

    public void setFruitName(String fruitName) {
        this.fruitName = fruitName;
    }

    public Double getFruitPrice() {
        return fruitPrice;
    }

    public void setFruitPrice(Double fruitPrice) {
        this.fruitPrice = fruitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @Override
    public String toString() {
        return String.format("%-5s | %-15s | %-10.2f | %-8d | %-15s",
                fruitId, fruitName, fruitPrice, quantity, origin);
    }

}
