package lt1;

import java.util.ArrayList;
import java.util.Scanner;

public class ProductBusiness {
    static Product[] arrProduct = new Product[100];
    static int currentIndex = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("***************** QUAN LY SAN PHAM *****************");
            System.out.println("1. Them san pham");
            System.out.println("2. Danh sach san pham");
            System.out.println("3. Cap nhat san pham theo ma san pham");
            System.out.println("4. Xoa san pham theo ma san pham");
            System.out.println("5. Tim kiem san pham theo ten");
            System.out.println("6. Sap xep san pham theo gia tang dan");
            System.out.println("7. Sap xep san pham theo so luong giam dan");
            System.out.println("8. Thoat");
            System.out.println("***************************************************");
            System.out.print("Lua chon cua ban: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    addProduct(sc);
                    break;
                case 2:
                    displayProduct();
                    break;
                case 3:
                    updateProduct(sc);
                    break;
                case 4:
                    deleteProduct(sc);
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    System.exit(0);
                default:
                    System.err.println("Vui long nhap lua chon phu hop.");
            }
        } while (true);
    }

    public static void addProduct(Scanner sc) {
        System.out.print("Nhap so luong san pham muon them: ");
        int count = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < count; i++) {
            System.out.printf("Nhap thong tin san pham thu %d: \n", i + 1);
            arrProduct[currentIndex] = new Product();
            arrProduct[currentIndex].inputData(sc, arrProduct, currentIndex);
            currentIndex++;
        }
        System.out.printf("Them moi %d san pham thanh cong.\n", count);
    }

    public static void displayProduct() {
        if (currentIndex == 0) {
            System.out.println("Chua co san pham nao duoc them.");
        } else {
            for (int i = 0; i < currentIndex; i++) {
                if (currentIndex == 1) {
                    arrProduct[i].displayData();
                } else {
                    System.out.printf("--- Danh Sach San Pham Thu %d ---\n", i + 1);
                    arrProduct[i].displayData();
                }
            }
        }
    }

    public static int checkProductId(int productId) {
        for (int i = 0; i < currentIndex; i++) {
            if (arrProduct[i].getProductId() == productId) {
                return i;
            }
        }
        return -1;
    }

    public static void updateProduct(Scanner sc) {
        if (currentIndex == 0) {
            System.out.println("Chua co san pham nao duoc them.");
        } else {
            System.out.print("Nhap ma san pham can tim: ");
            int productId = Integer.parseInt(sc.nextLine());
            int indexUpdate = checkProductId(productId);
            if (indexUpdate == -1) {
                System.err.println("Ma san pham khong ton tai");
            } else {
                do {
                    System.out.println("Chon thong tin can cap nhat: ");
                    System.out.println("1. Cap nhat ten san pham");
                    System.out.println("2. Cap nhat gia san pham");
                    System.out.println("3. Cap nhat loai san pham");
                    System.out.println("4. Cap nhat so luong ton kho");
                    System.out.println("5. Thoat");
                    System.out.print("Lua chon cua ban: ");
                    int choice = Integer.parseInt(sc.nextLine());
                    switch (choice) {
                        case 1:
                            String newProductName;
                            do {
                                System.out.print("Nhap ten san pham moi: ");
                                newProductName = sc.nextLine();
                                if (newProductName.length() > 10 && newProductName.length() < 50) {
                                    boolean isExist = false;
                                    for (int i = 0; i < currentIndex; i++) {
                                        if (arrProduct[i].getProductName().equals(newProductName)) {
                                            System.err.println("Ten san pham da ton tai.");
                                            ;
                                            isExist = true;
                                            break;
                                        }
                                    }
                                    if (!isExist) {
                                        arrProduct[indexUpdate].setProductName(newProductName);
                                    }
                                }
                                break;
                            } while (true);
                            System.out.println("Cap nhat ten san pham thanh cong.");
                            break;
                        case 2:
                            float newPrice;
                            do {
                                System.out.print("Nhap gia san pham moi: ");
                                newPrice = Float.parseFloat(sc.nextLine());
                                if (newPrice <= 0) {
                                    System.err.println("Vui long nhap gia lon hon 0.");
                                }
                            } while (newPrice <= 0);
                            arrProduct[indexUpdate].setPrice(newPrice);
                            System.out.println("Cap nhat gia san pham thanh cong.");
                            break;
                        case 3:
                            String newCategory;
                            do {
                                System.out.print("Nhap loai san pham moi: ");
                                newCategory = sc.nextLine();
                                if (newCategory.length() > 200) {
                                    System.err.println("Vui long nhap loai san pham nho hon 200 ky tu.");
                                }
                            } while (newCategory.length() > 200);
                            arrProduct[indexUpdate].setCategory(newCategory);
                            System.out.println("Cap nhat loai san pham thanh cong.");
                            break;
                        case 4:
                            int newQuantity;
                            do {
                                System.out.print("Nhap so luong ton kho moi: ");
                                newQuantity = Integer.parseInt(sc.nextLine());
                                if (newQuantity < 0) {
                                    System.err.println("Vui long nhap so luong lon hon hoac bang 0.");
                                }
                            } while (newQuantity < 0);
                            arrProduct[indexUpdate].setQuantity(newQuantity);
                            System.out.println("Cap nhat so luong ton kho thanh cong.");
                            break;
                        case 5:
                            return;
                        default:
                            System.err.println("Vui long nhap lua chon phu hop.");
                    }
                } while (true);
            }
        }
    }

    public static void deleteProduct(Scanner sc) {
        if (currentIndex == 0) {
            System.out.println("Chua co san pham nao duoc them");
        } else {
            System.out.print("Nhap ma san pham can xoa: ");
            int productId = Integer.parseInt(sc.nextLine());
            int indexDelete = checkProductId(productId);
            if (indexDelete == -1) {
                System.err.println("Ma san pham khong ton tai.");
            } else {
                for (int i = indexDelete; i < currentIndex - 1; i++) {
                    arrProduct[i] = arrProduct[i + 1];
                }
                currentIndex--;
                System.out.println("Xoa san pham thanh cong.");
            }
        }
    }
}
