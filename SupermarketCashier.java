import java.time.DayOfWeek;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class SupermarketCashier {
    private Map<String, Purchasable> products;
    private Customer customer;
    private String supermarketName = "SUPERMARKET Andalas"; 

    private static final int CAPTCHA_LENGTH = 5; 
    private static final String CORRECT_USERNAME = "admin";
    private static final String CORRECT_PASSWORD = "qwerty";

    public SupermarketCashier() {
        products = new HashMap<>();
        products.put("001", new Product("Buku", 4000.0));
        products.put("002", new Product("Pensil", 2000.0));
    }

    public void startTransaction() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("SELAMAT DATANG DI " + supermarketName);
            System.out.println("==============================");

            String cashierName = authenticateUser(scanner);

           
            String captcha = generateCaptcha();
            System.out.println("Kode Captcha : " + captcha);

        
            System.out.print("Entry Captcha : ");
            String enteredCaptcha = scanner.nextLine();

            if (!enteredCaptcha.equals(captcha)) {
                throw new ItemNotFoundException("Kode Captcha tidak valid.");
            }

            System.out.println("(message)");

            DayOfWeek dayOfWeek = ZonedDateTime.now().getDayOfWeek();
            String dayName = dayOfWeek.getDisplayName(java.time.format.TextStyle.FULL, java.util.Locale.getDefault());

            System.out.println("NAMA " + supermarketName + "/MINI MARKET");
            System.out.println("Tanggal : " + dayName + ", " + ZonedDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            System.out.println("Waktu   : " + ZonedDateTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss z")));
            System.out.println("========================");

            System.out.println("DATA PELANGGAN");
            System.out.println("----------------------------------------");
            System.out.print("Nama Pelanggan : ");
            String customerName = scanner.nextLine();
            System.out.print("No. HP         : ");
            String phoneNumber = scanner.nextLine();

            customer = new Customer(customerName, phoneNumber);

            double total = 0;
            String productCode;
            do {
                System.out.println("----------------------------------------");
                System.out.print("Kode Barang    : ");
                productCode = scanner.nextLine();

                if (products.containsKey(productCode)) {
                    Purchasable product = products.get(productCode);

                    // Access the name using the getter method
                    System.out.println("Nama Barang     : " + ((Product) product).getName());

                    System.out.println("Harga Barang    : " + product.getPrice());

                    System.out.print("Jumlah Beli     : ");
                    int quantity = scanner.nextInt();
                    scanner.nextLine();

                    product.setQuantity(quantity);
                    total += product.getTotalPrice();

                    System.out.println("DATA PEMBELIAN BARANG");
                    System.out.println("----------------------------------------");
                    System.out.println("Kode Barang     : " + productCode);
                    System.out.println("Nama Barang     : " + ((Product) product).getName());
                    System.out.println("Harga Barang    : " + product.getPrice());
                    System.out.println("Jumlah Beli     : " + quantity);
                    System.out.println("TOTAL BAYAR      : " + product.getTotalPrice());
                } else if (!productCode.equalsIgnoreCase("selesai")) {
                    throw new ItemNotFoundException("Kode barang tidak valid.");
                }
            } while (!productCode.equalsIgnoreCase("selesai"));

            System.out.println("----------------------------------------");
            System.out.println("=== Struk Belanja ===");
            System.out.println("Tanggal Transaksi: " + ZonedDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss z")));
            System.out.println(customer);
            System.out.println("Total Pembayaran: " + total);
            System.out.println("++++++++++++++++++++++++");
            System.out.println("Kasir    : " + cashierName);

        } catch (ItemNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private String authenticateUser(Scanner scanner) throws ItemNotFoundException {
  
        System.out.print("Username    : ");
        String enteredUsername = scanner.nextLine();
        System.out.print("Password    : ");
        String enteredPassword = scanner.nextLine();

        
        if (!enteredUsername.equals(CORRECT_USERNAME) || !enteredPassword.equals(CORRECT_PASSWORD)) {
            throw new ItemNotFoundException("Username atau password tidak valid.");
        }

        return enteredUsername;
    }

    private String generateCaptcha() {
        Random random = new Random();
        StringBuilder captcha = new StringBuilder();

        for (int i = 0; i < CAPTCHA_LENGTH; i++) {
            char randomChar = (char) (random.nextInt(26) + 'A'); // Generate a random uppercase letter
            captcha.append(randomChar);
        }

        return captcha.toString();
    }

    public static void main(String[] args) {
        SupermarketCashier cashier = new SupermarketCashier();
        cashier.startTransaction();
    }
}