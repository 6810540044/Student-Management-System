package StudentSystem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

public class StudentsManager {
    private ArrayList<Student> students = new ArrayList<Student>();

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

    public void saveToFile(){
        try {
            BufferedWriter write = new BufferedWriter(
                new FileWriter("C:\\Users\\admin\\MyProject\\StudentSystem\\StudentData"));
            for (Student s : students){
                write.write(s.getId()+",");
                write.write(s.getName()+",");
                write.write(s.getMajor()+",");
                write.write(Double.toString(s.getGpa())+"\n");
            }
            write.close();
        }catch (Exception e){
            return;
        }
    }

    public void loadFromFile(){
        students.clear();
        try {
            BufferedReader reader = new BufferedReader(
                new FileReader("C:\\Users\\admin\\MyProject\\StudentSystem\\StudentData"));
            String line;
            while ((line = reader.readLine()) != null){
                String[] studentData = line.split(",");
                Student s = new Student(
                    studentData[0],
                    studentData[1],
                    studentData[2],
                    Double.parseDouble(studentData[3]));
                students.add(s);
            }
            reader.close();
            System.out.println("Data loaded successfully.");
        } catch (Exception e){
            return;
        }
        
    }
}