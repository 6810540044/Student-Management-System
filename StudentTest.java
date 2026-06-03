package StudentSystem;

import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentTest {

    public static void showMenu() {
        System.out.println("----- Menu -----");
        System.out.println("- Add");
        System.out.println("- Remove");
        System.out.println("- View");
        System.out.println("- Search");
        System.out.println("- Update");
        System.out.println("- Exit");
        System.out.print("Enter your choice: ");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentsManager manager = new StudentsManager();
        manager.loadFromFile();
        // Student s1 = new Student("02051997","Bambam","Got7",4.00);
        // System.out.println(s1.toString());
        showMenu();

        while (true) {
            String cmd = sc.next();
            if (cmd.equalsIgnoreCase("add")) {
                System.out.print("Enter Student ID: ");
                String id = sc.next();
                sc.nextLine();
                System.out.print("Enter Student Name: ");
                String name = sc.nextLine();
                System.out.print("Enter Student Major: ");
                String major = sc.nextLine();
                while (true) {
                    System.out.print("Enter Student GPA: ");
                    try {
                        double gpa = sc.nextDouble();
                        if (gpa < 0 || gpa > 4) {
                            System.out.println("GPA must be between 0 and 4.");
                        } else {
                            manager.addStudent(id, name, major, gpa);
                            break;
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid GPA. Please enter a number.");
                        sc.nextLine();
                    }
                }
                manager.saveToFile();

            } else if (cmd.equalsIgnoreCase("remove")) {
                System.out.print("Enter Student ID: ");
                String id = sc.next();
                manager.removeStudent(id);
                manager.saveToFile();

            } else if (cmd.equalsIgnoreCase("view")) {
                manager.viewStudent();

            } else if (cmd.equalsIgnoreCase("search")) {
                System.out.print("Enter Student ID: ");
                String id = sc.next();
                System.out.println(manager.searchStudent(id));

            } else if (cmd.equalsIgnoreCase("update")) {
                System.out.print("Enter Student ID: ");
                String id = sc.next();
                Student stud = manager.searchStudent(id);

                while (stud != null) {
                    System.out.print("What would you like to update? (Name/Major/GPA): ");
                    String choice = sc.next();
                    sc.nextLine();
                    if (choice.equalsIgnoreCase("name")) {
                        System.out.print("Enter Name: ");
                        String newName = sc.nextLine();
                        stud.setName(newName);
                        System.out.println("Name updated successfully.");
                        break;

                    } else if (choice.equalsIgnoreCase("major")) {
                        System.out.print("Enter Major: ");
                        String newMajor = sc.nextLine();
                        stud.setMajor(newMajor);
                        System.out.println("Major updated successfully.");
                        break;

                    } else if (choice.equalsIgnoreCase("gpa")) {
                        while (true) {
                            System.out.print("Enter GPA: ");
                            try {
                                double newGPA = sc.nextDouble();
                                if (newGPA < 0 || newGPA > 4) {
                                    System.out.println("GPA must be between 0 and 4.");
                                } else {
                                    stud.setGpa(newGPA);
                                    break;
                                }

                            } catch (InputMismatchException e) {
                                System.out.println("Invalid GPA. Please enter a number.");
                                sc.nextLine();
                            }
                        }
                        System.out.println("GPA updated successfully.");
                        break;

                    } else {
                        System.out.println("Invalid choice. Please try again.");
                    }

                }
                manager.saveToFile();

            } else if (cmd.equalsIgnoreCase("exit")) {
                System.out.println("Thank you for using Student Management System.");
                break;

            } else {
                System.out.println("Invalid choice. Please try again.");

            }
            showMenu();
        }
        sc.close();
    }
}