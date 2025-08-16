import java.util.*;

public class GradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Student> students = new ArrayList<>();

        // Subjects
        System.out.print("Enter number of subjects: ");
        int subjectCount = inputPositiveInt(scanner);

        String[] subjectNames = new String[subjectCount];
        for (int i = 0; i < subjectCount; i++) {
            System.out.print("Enter name for Subject " + (i + 1) + ": ");
            subjectNames[i] = scanner.nextLine();
        }

        // Students
        System.out.print("Enter number of students: ");
        int studentCount = inputPositiveInt(scanner);

        for (int i = 0; i < studentCount; i++) {
            System.out.print("Enter student name: ");
            String name = scanner.nextLine();

            Map<String, Integer> marks = new LinkedHashMap<>();
            for (String subject : subjectNames) {
                int mark;
                while (true) {
                    System.out.print("Enter marks for " + subject + " (0-100): ");
                    mark = inputPositiveInt(scanner);
                    if (mark >= 0 && mark <= 100) break;
                    else System.out.println("Invalid input! Marks must be between 0 and 100.");
                }
                marks.put(subject, mark);
            }

            students.add(new Student(name, marks));
        }

        // Display Results
        System.out.println("\n-- Student Results --");
        for (Student s : students) s.displayResult();

        // Statistics
        displayStatistics(students);

        // Grade Distribution
        displayGradeDistribution(students);

        // Search by Name
        System.out.print("\nSearch for a student by name (or press Enter to skip): ");
        String searchName = scanner.nextLine().trim();
        if (!searchName.isEmpty()) {
            boolean found = false;
            for (Student s : students) {
                if (s.getName().equalsIgnoreCase(searchName)) {
                    s.displayResult();
                    found = true;
                }
            }
            if (!found) System.out.println("Student not found.");
        }
    }

    // Input Validation
    public static int inputPositiveInt(Scanner scanner) {
        while (true) {
            try {
                int num = Integer.parseInt(scanner.nextLine().trim());
                if (num > 0) return num;
                else System.out.print("Please enter a positive integer: ");
            } catch (NumberFormatException e) {
                System.out.print("Invalid input! Enter a positive integer: ");
            }
        }
    }

    // Statistics
    public static void displayStatistics(List<Student> students) {
        double highest = -1, lowest = 101, total = 0;
        String highestStudent = "", lowestStudent = "";
        for (Student s : students) {
            double avg = s.getAverage();
            total += avg;
            if (avg > highest) {
                highest = avg;
                highestStudent = s.getName();
            }
            if (avg < lowest) {
                lowest = avg;
                lowestStudent = s.getName();
            }
        }
        double classAvg = total / students.size();
        System.out.println("\n-- Statistics --");
        System.out.println("Class Average: " + String.format("%.2f", classAvg));
        System.out.println("Highest Average: " + String.format("%.2f", highest) + " (" + highestStudent + ")");
        System.out.println("Lowest Average: " + String.format("%.2f", lowest) + " (" + lowestStudent + ")");
    }

    // Grade Distribution
    public static void displayGradeDistribution(List<Student> students) {
        Map<Character, Integer> gradeCount = new HashMap<>();
        for (Student s : students) {
            char grade = s.getGrade();
            gradeCount.put(grade, gradeCount.getOrDefault(grade, 0) + 1);
        }
        System.out.println("\n-- Grade Distribution --");
        for (char grade : new char[]{'A','B','C','D','F'}) {
            System.out.println(grade + ": " + gradeCount.getOrDefault(grade, 0));
        }
    }
}
