import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.NoSuchElementException;

public class Cashiers {

    private static String username;
    private static String password;

    public static void main(String[] args) {
        String namaSupermarket = "Supermarket Andalas";
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, dd/MM/yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss z");

        System.out.println("SELAMAT DATANG DI " + namaSupermarket);
        System.out.println("==============================");

        login();

        Date currentDate = new Date();
        System.out.println("Tanggal : " + dateFormat.format(currentDate));
        System.out.println("Waktu   : " + timeFormat.format(currentDate));
        System.out.println("========================");

   
        System.out.println("DATA PELANGGAN");
        System.out.println("----------------------------------------");
        String namaPelanggan = getStringInput("Nama Pelanggan");
        String noHP = getStringInput("No. HP");
        String alamat = getStringInput("Alamat");
        System.out.println("++++++++++++++++++++++++");

        System.out.println("DATA PEMBELIAN BARANG");
        System.out.println("----------------------------------------");
        String kodeBarang = getStringInput("Kode Barang");
        String namaBarang = getStringInput("Nama Barang");
        double hargaBarang = getDoubleInput("Harga Barang");
        int jumlahBeli = getIntInput("Jumlah Beli");
        double totalBayar = hargaBarang * jumlahBeli;
        System.out.println("TOTAL BAYAR      : " + totalBayar);
        System.out.println("++++++++++++++++++++++++");


        String kasir = "Rehan"; 


        System.out.println("Kasir    : " + kasir);
    }

    private static String getStringInput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(prompt + " : ");
        return scanner.nextLine();
    }

    private static int getIntInput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(prompt + " : ");
        while (true) {
            try {
                return scanner.nextInt();
            } catch (NoSuchElementException e) {
                System.out.println("Input harus berupa angka");
                System.out.print(prompt + " : ");
                scanner.next(); 
            }
        }
    }

    
    private static double getDoubleInput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(prompt + " : ");
        while (true) {
            try {
                return scanner.nextDouble();
            } catch (NoSuchElementException e) {
                System.out.println("Input harus berupa angka");
                System.out.print(prompt + " : ");
                scanner.next(); 
            }
        }
    }

    private static void login() {
        Scanner scanner = new Scanner(System.in);

        
        boolean loggedIn = false;
        do {
            try {
                System.out.print("Username    : ");
                username = scanner.nextLine();

                System.out.print("Password     : ");
                password = scanner.nextLine();

               
                if (username.equals("admin") && password.equals("qwerty")) {
                    loggedIn = true;
                    System.out.println("Login berhasil!");
                } else {
                    throw new IllegalArgumentException("Login gagal. Username atau password salah.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (!loggedIn);
    }
}
