package model;

public class Order {

    protected String fruitId;
    protected String fruitName;
    protected int quantityOders;
    protected Double price;

    public Order() {
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

    public int getQuantityOders() {
        return quantityOders;
    }

    public void setQuantityOders(int quantityOders) {
        this.quantityOders = quantityOders;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setValuesFromFruit(Fruit fruit) {
        this.fruitId = fruit.getFruitId();
        this.fruitName = fruit.getFruitName();
        this.price = fruit.getFruitPrice();
    }

}
