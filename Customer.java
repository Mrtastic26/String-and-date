class Customer {
    String name;
    String phoneNumber;

    public Customer(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Pelanggan: " + name + "\nNomor Telepon: " + phoneNumber;
    }
}