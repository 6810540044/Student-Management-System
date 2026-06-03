package StudentSystem;

import java.util.Scanner;
import java.util.ArrayList;

public class StudentsManager {
    Scanner sc = new Scanner(System.in);
    ArrayList<Student> students = new ArrayList<Student>();

    public void addStudent(String id, String name,String major, double gpa) {
        for (Student s : students){
            if (id.equals(s.getId())){
                System.out.println("Student ID already exists.\n");
                return;
            }
        }
        Student s = new Student(id, name, major, gpa);
        students.add(s);
        System.out.println("Student added successfully.\n");
    }

    public void removeStudent(String id) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(id)) {
                students.remove(i);
                System.out.println("Student removed.\n");
                return;
            }
        }
        System.out.println("Student not found.\n");
    }

    public void viewStudent() {
        if (students.isEmpty()) {
            System.out.println("Student not found\n");
            return;
        }

        for (Student s : students) {
            System.out.println(s);
            System.out.println();
        }
    }

    public Student searchStudent(String id) {
        for (Student s : students) {
            if (s.getId().equals(id)) {
                return s;
            }
        }
        System.out.println("Student not found.");
        return null;
    }
}
