public class Goods {
    private String description;
    private double price;

    public Goods(String description, double price) {
        this.description = description;
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public void display() {
        System.out.println("Description : " + description);
        System.out.println("Price       : " + price);
    }
}
