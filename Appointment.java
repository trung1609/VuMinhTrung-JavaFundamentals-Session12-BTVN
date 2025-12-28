package Session12.lt2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Appointment {
    private String appointmentId;
    private String patientName;
    private String phoneNumber;
    private LocalDate appointmentDate;
    private String doctor;

    public Appointment() {
    }

    public Appointment(String appointmentId, String patientName, String phoneNumber, LocalDate appointmentDate, String doctor) {
        this.appointmentId = appointmentId;
        this.patientName = patientName;
        this.phoneNumber = phoneNumber;
        this.appointmentDate = appointmentDate;
        this.doctor = doctor;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public void inputData(Scanner sc, Appointment[] appointments, int index) {
        this.appointmentId = inputAppointmentId(sc, appointments, index);
        this.patientName = inputPatientName(sc);
        this.phoneNumber = inputPhoneNumber(sc);
        this.appointmentDate = inputAppointmentDate(sc);
        this.doctor = inputDoctor(sc);
    }

    public String inputAppointmentId(Scanner sc, Appointment[] appointments, int index) {
        String appointment_id;
        do {
            System.out.print("Nhap ma lich hen: ");
            appointment_id = sc.nextLine();
            if (appointment_id.length() != 6) {
                System.err.println("Ma lich hen phai dung 6 ky tu.");
                continue;
            }
            boolean isExist = false;
            for (int i = 0; i < index; i++) {
                if (appointments[i].getAppointmentId().equalsIgnoreCase(appointment_id)) {
                    System.err.println("Ma lich hen da ton tai");
                    isExist = true;
                    break;
                }
            }
            if (!isExist) {
                return appointment_id;
            }
        } while (true);
    }

    public String inputPatientName(Scanner sc) {
        String patient_name;
        do {
            System.out.print("Nhap ten benh nhan: ");
            patient_name = sc.nextLine();
            if (patient_name.length() < 10 || patient_name.length() > 50) {
                System.err.println("Vui long nhap lai ten benh nhan");
            }
        } while (patient_name.length() < 10 || patient_name.length() > 50);
        return patient_name;
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

    public LocalDate inputAppointmentDate(Scanner sc) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        do {
            try {
                System.out.print("Nhap ngay hen kham: ");
                String date = sc.nextLine();
                return LocalDate.parse(date, formatter);
            } catch (DateTimeParseException e) {
                System.err.println("Vui long nhap dung dinh dang ngay.");
            }
        } while (true);
    }

    public String inputDoctor(Scanner sc) {
        String doctor;
        do {
            System.out.print("Nhap ten bac si phu trach: ");
            doctor = sc.nextLine();
            if (doctor.length() < 200) {
                return doctor;
            }
            System.err.println("Vui long nhap lai bac si phu trach.");
        } while (true);
    }

    @Override
    public String toString() {
        return "Ma lich hen: " + appointmentId +
                "\nTeb benh nhan: " + patientName + " | So dien thoai lien he: " + phoneNumber +
                "\nNgay hen kham: " + appointmentDate + " | Bac si phu trach: " + doctor;
    }

}
