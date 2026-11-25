public class CartItem {
    private final Product product;
    private final int qty;

    public CartItem(Product product, int qty) {
        if (qty <= 0) {
            throw new IllegalArgumentException(
                "Qty harus lebih dari 0");
        }
        this.product = product;
        this.qty = qty;
    }

    public Product getProduct() { return product; }
    public int getQty() { return qty; }

    public int getSubtotal() {
        return product.getPrice() * qty;
    }
}
