import java.util.*;

public class Student {
    private String name;
    private Map<String, Integer> marks; // Subject name to mark
    private double average;
    private char grade;

    public Student(String name, Map<String, Integer> marks) {
        this.name = name;
        this.marks = marks;
        calculateAverage();
        assignGrade();
    }

    private void calculateAverage() {
        int sum = 0;
        for (int mark : marks.values()) {
            sum += mark;
        }
        average = sum / (double) marks.size();
    }

    private void assignGrade() {
        if (average >= 90) grade = 'A';
        else if (average >= 80) grade = 'B';
        else if (average >= 70) grade = 'C';
        else if (average >= 60) grade = 'D';
        else grade = 'F';
    }

    public void displayResult() {
        System.out.print("Student: " + name + " | Subjects: ");
        for (Map.Entry<String, Integer> entry : marks.entrySet()) {
            System.out.print(entry.getKey() + "=" + entry.getValue() + " ");
        }
        System.out.println("| Average: " + String.format("%.2f", average) + " | Grade: " + grade);
    }

    public String getName() {
        return name;
    }

    public double getAverage() {
        return average;
    }

    public char getGrade() {
        return grade;
    }
}

