package lt1;

import java.util.Scanner;

public class Product {
    private int productId;
    private String productName;
    private float price;
    private String category;
    public static int AUTO_ID = 1;
    private int quantity;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product(int productId, String productName, float price, String category, int quantity) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
    }

    public Product() {
    }

    public void inputData(Scanner sc, Product[] arrProduct, int index) {
        this.productId = AUTO_ID++;
        this.productName = inputProductName(sc, arrProduct, index);
        this.price = inputPrice(sc);
        this.category = inputCategory(sc);
        this.quantity = inputQuantity(sc);
    }

    public String inputProductName(Scanner sc, Product[] arrProduct, int index) {
        do {
            System.out.print("Nhap ten san pham: ");
            productName = sc.nextLine();
            if (productName.length() > 10 && productName.length() < 50) {
                boolean isExist = false;
                for (int i = 0; i < index; i++) {
                    if (arrProduct[i].getProductName().equals(productName)){
                        System.err.println("Ten san pham da ton tai.");;
                        isExist = true;
                        break;
                    }
                }
                if (!isExist) {
                    return productName;
                }
            }
        } while (true);
    }

    public float inputPrice(Scanner sc){
        do {
            System.out.print("Nhap gia san pham: ");
            price = Float.parseFloat(sc.nextLine());
            if(price <= 0){
                System.err.println("Vui long nhap gia lon hon 0.");
            }
        }while (price <= 0);
        return price;
    }

    public String inputCategory(Scanner sc){
        do {
            System.out.print("Nhap loai san pham: ");
            category = sc.nextLine();
            if(category.length() > 200){
                System.err.println("Vui long nhap loai san pham nho hon 200 ky tu.");
            }
        }while(category.length() > 200);
        return category;
    }

    public int inputQuantity(Scanner sc){
        do{
            System.out.print("Nhap so luong ton kho: ");
            quantity = Integer.parseInt(sc.nextLine());
            if(quantity < 0){
                System.err.println("Vui long nhap so luong lon hon hoac bang 0.");
            }
        }while(quantity < 0);
        return quantity;
    }

    public void displayData(){
        System.out.println("Ma san pham: " + productId);
        System.out.println("Ten san pham: " + productName);
        System.out.println("Gia san pham: " + price);
        System.out.println("Loai san pham: " + category);
        System.out.println("So luong ton kho: " + quantity);
    }
}
