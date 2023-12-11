class Product implements Purchasable {
    private String name;
    private double price;
    private int quantity;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
        this.quantity = 0;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public double getTotalPrice() {
        return price * quantity;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Nama Barang: " + name + "\nHarga: " + price + "\nJumlah Beli: " + quantity;
    }
}
