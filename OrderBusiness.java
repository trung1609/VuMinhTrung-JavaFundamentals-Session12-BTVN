package Session12.lt3;

import java.util.Arrays;
import java.util.Scanner;

public class OrderBusiness {
    static Order[] arrOrder = new Order[100];
    static int currentOrderIndex = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("************************* QUAN LY DON HANG *************************");
            System.out.println("1. Them don hang");
            System.out.println("2. Hien thi danh sach don hang");
            System.out.println("3. Cap nhat trang thai don hang theo ma don hang");
            System.out.println("4. Xoa don hang theo ma don hang");
            System.out.println("5. Tim kiem don hang theo ten khach hang");
            System.out.println("6. Thong ke tong so don hang");
            System.out.println("7. Thong ke tong doanh thu cac don hang co trang thai Delivered");
            System.out.println("8. Thong ke so luong don hang theo tung trang thai");
            System.out.println("9. Tim kiem don hang co gia tri lon nhat");
            System.out.println("0. Thoat");
            System.out.println("********************************************************************");
            System.out.print("Lua chon cua ban: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    addOrder(sc);
                    break;
                case 2:
                    displayOrder();
                    break;
                case 3:
                    updateOrderStatus(sc);
                    break;
                case 4:
                    deleteOrder(sc);
                    break;
                case 5:
                    searchOrder(sc);
                    break;
                case 6:
                    totalOrder();
                    break;
                case 7:
                    totalAmount();
                    break;
                case 8:
                    totalOrderByStatus();
                    break;
                case 9:
                    searchMaxOrderAmount();
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.err.println("Vui long nhap lua chon phu hop.");
            }
        } while (true);
    }

    public static void addOrder(Scanner sc) {
        System.out.print("Nhap so don hang can them: ");
        int count = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < count; i++) {
            System.out.printf("Nhap thong tin don hang thu %d: \n", (i + 1));
            arrOrder[currentOrderIndex] = new Order();
            arrOrder[currentOrderIndex].inputData(sc);
            currentOrderIndex++;
        }
        System.out.printf("Them %d don hang thanh cong\n", count);
    }

    public static void displayOrder() {
        if (currentOrderIndex == 0) {
            System.out.println("Chua co san pham nao duoc them");
        } else {
            for (int i = 0; i < currentOrderIndex - 1; i++) {
                for (int j = 0; j < currentOrderIndex - i - 1; j++) {
                    if (arrOrder[j].getOrderAmount() < arrOrder[j + 1].getOrderAmount()) {
                        Order tmp = arrOrder[j];
                        arrOrder[j] = arrOrder[j + 1];
                        arrOrder[j + 1] = tmp;
                    }
                }
            }
            for (int i = 0; i < currentOrderIndex; i++) {
                System.out.println(arrOrder[i]);
            }
        }
    }

    public static int checkOrderId(int orderId) {
        for (int i = 0; i < currentOrderIndex; i++) {
            if (arrOrder[i].getOrderID() == orderId) {
                return i;
            }
        }
        return -1;
    }

    public static void updateOrderStatus(Scanner sc) {
        if (currentOrderIndex == 0) {
            System.out.println("Chua co san pham nao duoc them");
        } else {
            System.out.print("Nhap ma don hang: ");
            int order_id = Integer.parseInt(sc.nextLine());
            int indexUpdate = checkOrderId(order_id);
            if (indexUpdate == -1) {
                System.err.println("Ma don hang khong ton tai");
            } else {
                do {
                    System.out.print("Nhap trang thai moi cua don hang: ");
                    String newStatus = sc.nextLine().toUpperCase();
                    Status currentStatus = arrOrder[indexUpdate].getStatus();
                    if (currentStatus == Status.PENDING && newStatus.equals("SHIPPED")) {
                        arrOrder[indexUpdate].setStatus(Status.SHIPPED);
                        break;
                    } else if (currentStatus == Status.SHIPPED && newStatus.equals("DELIVERED")) {
                        arrOrder[indexUpdate].setStatus(Status.DELIVERED);
                        break;
                    } else {
                        System.err.println("Khong cho phep chuyen trang thai nay");
                    }
                } while (true);
                System.out.println("Cap nhat trang thai don hang thanh cong");
            }
        }
    }

    public static void deleteOrder(Scanner sc) {
        if (currentOrderIndex == 0) {
            System.out.println("Chua co san pham nao duoc them");
        } else {
            System.out.print("Nhap ma don hang: ");
            int order_id = Integer.parseInt(sc.nextLine());
            int indexDelete = checkOrderId(order_id);
            if (indexDelete == -1) {
                System.err.println("Ma don hang khong ton tai");
            } else {
                if (arrOrder[indexDelete].getStatus().equals(Status.PENDING)) {
                    for (int i = indexDelete; i < currentOrderIndex; i++) {
                        arrOrder[i] = arrOrder[i + 1];
                    }
                    currentOrderIndex--;
                    System.out.println("Xoa don hang thanh cong");
                } else {
                    System.out.println("Khong the xoa don hang co trang thai khac PENDING");
                }
            }
        }
    }

    public static void searchOrder(Scanner sc) {
        if (currentOrderIndex == 0) {
            System.out.println("Chua co san pham nao duoc them");
        } else {
            System.out.print("Nhap ten khach hang: ");
            String customer_name = sc.nextLine();
            boolean isFound = false;
            for (int i = 0; i < currentOrderIndex; i++) {
                if (arrOrder[i].getCustomerName().toLowerCase().contains(customer_name.toLowerCase())) {
                    System.out.println(arrOrder[i]);
                    isFound = true;
                }
            }
            if (!isFound) {
                System.out.println("Khong tim thay don hang");
            }
        }
    }

    public static void totalOrder() {
        System.out.println("Tong so don hang: " + currentOrderIndex);
    }

    public static void totalAmount() {
        float total = 0;
        for (int i = 0; i < currentOrderIndex; i++) {
            if (arrOrder[i].getStatus().equals(Status.DELIVERED)) {
                total += arrOrder[i].getOrderAmount();
            }
        }
        System.out.println("Tong doanh thu cac don hang co trang thai Delivered: " + total + " VND");
    }

    public static void totalOrderByStatus() {
        if (currentOrderIndex == 0) {
            System.out.println("Chua co san pham nao duoc them");
        } else {
            for (Status s : Status.values()) {
                int count = countOrderByStatus(s);
                System.out.printf("Co %d don hang co trang thai %s\n", count, s);
            }
        }
    }

    public static int countOrderByStatus(Status status) {
        int count = 0;
        for (int i = 0; i < currentOrderIndex; i++) {
            if (arrOrder[i].getStatus() == status) {
                count++;
            }
        }
        return count;
    }

    public static void searchMaxOrderAmount(){
        float max = arrOrder[0].getOrderAmount();
        Order maxOrder = new Order();
        for (int i = 0; i < currentOrderIndex; i++) {
            if (arrOrder[i].getOrderAmount() > max) {
                max = arrOrder[i].getOrderAmount();
                maxOrder = arrOrder[i];
            }
        }
        System.out.println("Don hang co gia tri lon nhat la: ");
        System.out.println(maxOrder);
    }
}
