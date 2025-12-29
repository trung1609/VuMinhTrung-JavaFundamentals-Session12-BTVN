package Session12.lt3;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Order {
    private int orderID;
    private String customerName;
    private String phoneNumber;
    private String address;
    private float orderAmount;
    private Status status;
    public static int AUTO_ID = 1;

    public Order() {
    }

    public Order(int orderID, String customerName, String phoneNumber, String address, float orderAmount, Status status) {
        this.orderID = orderID;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.orderAmount = orderAmount;
        this.status = status;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(float orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void inputData(Scanner sc) {
        this.orderID = AUTO_ID++;
        this.customerName = inputCustomerName(sc);
        this.phoneNumber = inputPhoneNumber(sc);
        this.address = inputAddress(sc);
        this.orderAmount = inputOrderAmount(sc);
        this.status = inputStatus(sc);
    }

    public String inputCustomerName(Scanner sc) {
        String customer_name;
        do {
            System.out.print("Nhap ten khach hang: ");
            customer_name = sc.nextLine();
            if (customer_name.length() < 6 || customer_name.length() > 100) {
                System.err.println("Vui long nhap lai ten khach hang");
            }
        } while (customer_name.length() < 6 || customer_name.length() > 100);
        return customer_name;
    }

    public String inputPhoneNumber(Scanner sc) {
        String phone_number;
        String regex = "0[3|5|7|8|9][0-9]{8}";
        do {
            System.out.print("Nhap so dien thoai lien he: ");
            phone_number = sc.nextLine();
            if (!Pattern.matches(regex, phone_number)) {
                System.err.println("Vui long nhap lai so dien thoai");
            }
        } while (!Pattern.matches(regex, phone_number));
        return phone_number;
    }

    public String inputAddress(Scanner sc) {
        String addressInput;
        do {
            System.out.print("Nhap dia chi giao hang: ");
            addressInput = sc.nextLine();
            if (addressInput.trim().isEmpty()) {
                System.err.println("Vui long nhap dia chi giao hang");
            }
        } while (addressInput.trim().isEmpty());
        return addressInput;
    }

    public float inputOrderAmount(Scanner sc) {
        float orderAmountInput;
        do {
            System.out.print("Nhap gia tri don hang: ");
            orderAmountInput = Float.parseFloat(sc.nextLine());
            if (orderAmountInput < 0) {
                System.err.println("Vui long nhap lai gia tri don hang");
            }

        } while (orderAmountInput < 0);
        return orderAmountInput;
    }

    public Status inputStatus(Scanner sc) {
        Status inputStatus;
        do {
            System.out.print("Nhap trang thai don hang: ");
            String status = sc.nextLine().toUpperCase();
            if (status.equals("PENDING") || status.equals("SHIPPED") || status.equals("DELIVERED")) {
                inputStatus = Status.valueOf(status);
                break;
            }
            System.err.println("Vui long nhap lai trang thai don hang");
        } while (true);
        return inputStatus;
    }

    @Override
    public String toString() {
        return "Ma don hang: " + orderID +
                "\nTen khach hang: " + customerName +
                "\nSo dien thoai: " + phoneNumber +
                "\nDia chi giao hang: " + address +
                "\nGia tri don hang: " + orderAmount +
                "\nTrang thai don hang: " + status;
    }

}
