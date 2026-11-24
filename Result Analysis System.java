import java.io.*;
import java.util.*;

class Student {
    String name;
    int m1, m2, m3;
    int total;
    double percentage;
    String grade;

    public Student(String name, int m1, int m2, int m3) {
        this.name = name;
        this.m1 = m1;
        this.m2 = m2;
        this.m3 = m3;
        this.total = m1 + m2 + m3;
        this.percentage = total / 3.0;
        this.grade = calculateGrade(this.percentage);
    }

    private String calculateGrade(double p) {
        if (p >= 90) return "A+";
        else if (p >= 75) return "A";
        else if (p >= 60) return "B";
        else if (p >= 40) return "C";
        else return "F";
    }
}

public class Main {
    static ArrayList<Student> studentList = new ArrayList<>();
    static HashMap<String, Student> studentMap = new HashMap<>();
    static final String FILE_NAME = "results.txt";

    public static void main(String[] args) {
        loadFromFile();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Result Analysis System ---");
            System.out.println("1. Add result");
            System.out.println("2. Search by name");
            System.out.println("3. Rank list");
            System.out.println("4. Topper & average");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addResult(sc);
                    break;
                case 2:
                    searchByName(sc);
                    break;
                case 3:
                    printRankList();
                    break;
                case 4:
                    showTopperAndAverage();
                    break;
                case 5:
                    saveToFile();
                    System.out.println("Data saved. Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    // 1. Add Result
    static void addResult(Scanner sc) {
        System.out.print("Enter Name: ");
        String name = sc.nextLine().trim();

        System.out.print("Enter marks of 3 subjects (space separated): ");
        int m1 = sc.nextInt();
        int m2 = sc.nextInt();
        int m3 = sc.nextInt();
        sc.nextLine(); // consume newline

        if (m1 < 0 || m1 > 100 || m2 < 0 || m2 > 100 || m3 < 0 || m3 > 100) {
            System.out.println("Invalid marks. Enter marks between 0 and 100.");
            return;
        }

        Student s = new Student(name, m1, m2, m3);
        studentList.add(s);
        studentMap.put(name.toLowerCase(), s);

        System.out.printf("Result added. %s | Total: %d | %.1f%% | Grade %s%n",
                s.name, s.total, s.percentage, s.grade);
    }

    // 2. Search by Name (using HashMap)
    static void searchByName(Scanner sc) {
        System.out.print("Enter student name to search: ");
        String name = sc.nextLine().trim();

        Student s = studentMap.get(name.toLowerCase());
        if (s != null) {
            System.out.printf("%s | Total: %d | %.1f%% | Grade %s%n",
                    s.name, s.total, s.percentage, s.grade);
        } else {
            System.out.println("Student not found.");
        }
    }

    // 3. Print Rank List (Sorted by total marks descending)
    static void printRankList() {
        if (studentList.isEmpty()) {
            System.out.println("No records available.");
            return;
        }

        // Sorting ArrayList by total marks (highest first)
        Collections.sort(studentList, new Comparator<Student>() {
            public int compare(Student s1, Student s2) {
                return s2.total - s1.total;
            }
        });

        for (int i = 0; i < studentList.size(); i++) {
            Student s = studentList.get(i);
            System.out.printf("%d. %s | %d | %.1f%%%n",
                    (i + 1), s.name, s.total, s.percentage);
        }
    }

    // 4. Show Topper & Class Average
    static void showTopperAndAverage() {
        if (studentList.isEmpty()) {
            System.out.println("No records available.");
            return;
        }

        Student topper = studentList.get(0);
        double totalPercentageSum = 0;

        for (Student s : studentList) {
            if (s.total > topper.total) {
                topper = s;
            }
            totalPercentageSum += s.percentage;
        }

        double classAverage = totalPercentageSum / studentList.size();
        System.out.printf("Topper: %s (%d)%n", topper.name, topper.total);
        System.out.printf("Class average: %.1f%%%n", classAverage);
    }

    // 5. Load Data from results.txt at start
    static void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String name = parts[0];
                    int m1 = Integer.parseInt(parts[1]);
                    int m2 = Integer.parseInt(parts[2]);
                    int m3 = Integer.parseInt(parts[3]);
                    Student s = new Student(name, m1, m2, m3);
                    studentList.add(s);
                    studentMap.put(name.toLowerCase(), s);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }

    // 6. Save Data to results.txt on exit
    static void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Student s : studentList) {
                bw.write(s.name + "," + s.m1 + "," + s.m2 + "," + s.m3);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
}