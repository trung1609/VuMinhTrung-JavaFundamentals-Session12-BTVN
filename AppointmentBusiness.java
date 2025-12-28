package Session12.lt2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class AppointmentBusiness {
    static Appointment[] arrApointment = new Appointment[100];
    static int currentIndex = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("*********************** QUAN LY LICH HEN ***********************");
            System.out.println("1. Them lich hen");
            System.out.println("2. Hien thi danh sach lich hen");
            System.out.println("3. Tim kiem lich hen theo ten benh nhan");
            System.out.println("4. Cap nhat lich hen theo ma lich hen");
            System.out.println("5. Xoa lich hen theo ma lich hen");
            System.out.println("6. Thong ke");
            System.out.println("7. Thoat");
            System.out.println("****************************************************************");
            System.out.print("Lua chon cua ban: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    addAppointment(sc);
                    break;
                case 2:
                    displayAppointment();
                    break;
                case 3:
                    searchAppointment(sc);
                    break;
                case 4:
                    updateAppointment(sc);
                    break;
                case 5:
                    deleteAppointment(sc);
                    break;
                case 6:
                    thongKe(sc);
                    break;
                case 7:
                    System.exit(0);
                default:
                    System.err.println("Vui long nhap lua chon phu hop");
            }
        } while (true);
    }

    public static void addAppointment(Scanner sc) {
        arrApointment[currentIndex] = new Appointment();
        arrApointment[currentIndex].inputData(sc, arrApointment, currentIndex);
        currentIndex++;
        System.out.println("Them lich hen thanh cong");
    }

    public static void displayAppointment() {
        if (currentIndex == 0) {
            System.out.println("Chua co lich hen nao duoc them");
        } else {
            for (int i = 0; i < currentIndex; i++) {
                if (currentIndex == 1) {
                    System.out.println(arrApointment[i]);
                } else {
                    System.out.printf("Danh sach lich hen thu %d: \n", (i + 1));
                    System.out.println(arrApointment[i]);
                }
            }
        }
    }

    public static void searchAppointment(Scanner sc) {
        System.out.print("Nhap ten benh nhan: ");
        String patient_name = sc.nextLine();
        boolean isExist = false;
        for (int i = 0; i < currentIndex; i++) {
            if (arrApointment[i].getPatientName().equals(patient_name)) {
                System.out.println(arrApointment[i]);
                isExist = true;
            }
        }
        if (!isExist) {
            System.out.println("Khong tim thay lich hen.");
        }
    }

    public static int checkAppointmentId(String appointment_id) {
        for (int i = 0; i < currentIndex; i++) {
            if (arrApointment[i].getAppointmentId().equals(appointment_id)) {
                return i;
            }
        }
        return -1;
    }

    public static void updateAppointment(Scanner sc) {
        if (currentIndex == 0) {
            System.out.println("Chua co lich hen nao duoc them.");
        } else {
            System.out.print("Nhap ma lich hen can cap nhat: ");
            String appointment_id = sc.nextLine();
            int indexUpdate = checkAppointmentId(appointment_id);
            if (indexUpdate == -1) {
                System.err.println("Ma lich hen khong ton tai.");
            } else {
                do {
                    System.out.println("1. Cap nhat ten benh nhan");
                    System.out.println("2. Cap nhat so dien thoai lien he");
                    System.out.println("3. Cap nhat ngay hen kham");
                    System.out.println("4. Cap nhat bac si phu trach");
                    System.out.println("5. Thoat");
                    System.out.print("Lua chon cua ban: ");
                    int choice = Integer.parseInt(sc.nextLine());
                    switch (choice) {
                        case 1:
                            String newPatientName;
                            do {
                                System.out.print("Nhap ten benh nhan moi: ");
                                newPatientName = sc.nextLine();
                                if (newPatientName.length() < 10 || newPatientName.length() > 50) {
                                    System.err.println("Vui long nhap lai ten benh nhan");
                                }
                            } while (newPatientName.length() < 10 || newPatientName.length() > 50);
                            arrApointment[indexUpdate].setPatientName(newPatientName);
                            System.out.println("Cap nhat ten benh nhan thanh cong");
                            break;
                        case 2:
                            String newPhoneNumber;
                            String regex = "0[3|5|7|8|9][0-9]{8}";
                            do {
                                System.out.print("Nhap so dien thoai lien he moi: ");
                                newPhoneNumber = sc.nextLine();
                                if (!Pattern.matches(regex, newPhoneNumber)) {
                                    System.err.println("Vui long nhap lai so dien thoai");
                                }
                            } while (!Pattern.matches(regex, newPhoneNumber));
                            arrApointment[indexUpdate].setPhoneNumber(newPhoneNumber);
                            System.out.println("Cap nhat so dien thoai lien he moi");
                            break;
                        case 3:
                            String newDate;
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                            while (true) {
                                try {
                                    System.out.print("Nhap ngay hen kham moi: ");
                                    newDate = sc.nextLine();
                                    arrApointment[indexUpdate].setAppointmentDate(LocalDate.parse(newDate, formatter));
                                    break;
                                } catch (DateTimeParseException e) {
                                    System.err.println("Vui long nhap dung dinh dang ngay.");
                                }
                            }
                            System.out.println("Cap nhat ngay hen kham thanh cong");
                            break;
                        case 4:
                            String newDoctor;
                            do {
                                System.out.print("Nhap ten bac si phu trach: ");
                                newDoctor = sc.nextLine();
                                if (newDoctor.length() > 200) {
                                    System.err.println("Vui long nhap lai bac si phu trach.");
                                }
                                arrApointment[indexUpdate].setDoctor(newDoctor);
                            } while (newDoctor.length() > 200);
                            System.out.println("Cap nhat bac si phu trach thanh cong");
                            break;
                        case 5:
                            return;
                        default:
                            System.err.println("Vui long nhap lua chon phu hop");
                    }
                } while (true);
            }
        }
    }

    public static void deleteAppointment(Scanner sc) {
        if (currentIndex == 0) {
            System.out.println("Chua co lich hen nao duoc them.");
        } else {
            System.out.print("Nhap ma lich hen can xoa: ");
            String appointment_id = sc.nextLine();
            int indexDelete = checkAppointmentId(appointment_id);
            if (indexDelete == -1) {
                System.err.println("Ma lich hen khong ton tai");
            } else {
                System.out.print("Xac nhan truoc khi xoa (Nhap Y/N): ");
                String confirm = sc.nextLine().toUpperCase();
                if (confirm.equals("N")) {
                    System.out.println("Da huy xoa lich hen.");
                } else if (confirm.equals("Y")) {
                    for (int i = indexDelete; i < currentIndex - 1; i++) {
                        arrApointment[i] = arrApointment[i + 1];
                    }
                    currentIndex--;
                    System.out.println("Xoa lich hen thanh cong");
                }
            }
        }
    }

    public static void thongKe(Scanner sc) {
        System.out.println("Tong so lich hen: " + currentIndex);
        String[] arrDoctor = new String[currentIndex];
        int doctorIndex = 0;
        for (int i = 0; i < currentIndex; i++) {
            String doctor = arrApointment[i].getDoctor();

            boolean isExist = false;
            for (int j = 0; j < doctorIndex; j++) {
                if (arrDoctor[j].equals(doctor)) {
                    isExist = true;
                    break;
                }
            }
            if (!isExist) {
                int count = 0;
                for (int k = 0; k < currentIndex; k++) {
                    if (arrApointment[k].getDoctor().equals(doctor)) {
                        count++;
                    }
                }
                System.out.println("Bac si " + doctor + ": " + count + " lich hen");
                arrDoctor[doctorIndex++] = doctor;
            }
        }
    }
}
