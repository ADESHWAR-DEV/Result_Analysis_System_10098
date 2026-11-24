# Result Analysis System (PS No. 5)

A simple, console-based Java application developed to analyse student results. It allows entering marks for 3 subjects per student, automatically computes total marks, percentage, and grade, finds the class topper and average, searches records by name, prints a sorted rank list, and persists all data to a text file (`results.txt`).

**Institution:** Rungta International Skills University — School of Computer Science and Engineering

**Language:** Java

## Features

* **Add Result:** Enter a student's name and marks for 3 subjects (validated between `0` and `100`). Automatically calculates total, percentage, and grade.

* **Search by Name:** Fast, case-insensitive student lookup using a `HashMap`.

* **Rank List:** Displays all students sorted in descending order of total marks.

* **Topper & Class Average:** Identifies the highest-scoring student and computes the overall class average percentage.

* **File Persistence:** Automatically loads existing records from `results.txt` at startup and saves all records back to `results.txt` on exit.

## Concepts Used

* `ArrayList` — Dynamic storage of `Student` objects and rank-list sorting

* `HashMap` — Constant-time ($O(1)$) student lookup by name

* Sorting & Searching — `Collections.sort()` with a custom `Comparator` and `HashMap.get()`

* Methods — Modular static methods for each menu operation and file I/O

* File Handling — `BufferedReader` and `BufferedWriter` for reading/writing `results.txt`

## Grading Criteria

| Percentage Range | Grade | 
| ----- | ----- | 
| 90% and above | **A+** | 
| 75% – 89% | **A** | 
| 60% – 74% | **B** | 
| 40% – 59% | **C** | 
| Below 40% | **F** | 

## Project Structure

```
.
├── Main.java        # Complete Java source code (Student + Main classes)
├── results.txt      # Auto-generated on exit to store student records (CSV format)
└── README.md        # Project documentation

```

## How to Run

### Prerequisites

* Java Development Kit (JDK 8 or higher) installed and added to your system `PATH`.

### Steps

1. Clone or download this repository and open a terminal in the project directory.

2. Compile the program:

   ```
   javac Main.java
   
   ```

3. Run the application:

   ```
   java Main
   
   ```

4. Use the interactive console menu:

   ```
   --- Result Analysis System ---
   1. Add result
   2. Search by name
   3. Rank list
   4. Topper & average
   5. Exit
   
   ```

5. Select **Option 5 (Exit)** to save all records to `results.txt` in the current working directory.

## Sample Test Cases

| Test Case | Input | Expected Output | 
| ----- | ----- | ----- | 
| **1. Normal Input** | Option 1 → Name: `Riya`, Marks: `80 90 70` | `Result added. Riya | Total: 240 | 80.0% | Grade A` | 
| **2. Boundary / Minimum Input** | Only `Riya (240)` added → Option 4 | `Topper: Riya (240)`  `Class average: 80.0%` | 
| **3. Invalid Input** | Option 1 → Name: `Dev`, Marks: `105 60 70` | `Invalid marks. Enter marks between 0 and 100.` | 
| **4. Different Valid Scenario** | `Riya (240)` already added:  (a) Option 1 → `Aman`, Marks: `60 55 65`  (b) Option 3 (Rank list)  (c) Option 2 → `Aman` | (a) `Result added. Aman | Total: 180 | 60.0% | Grade B`  (b) `1. Riya | 240 | 80.0%`  `2. Aman | 180 | 60.0%`  (c) `Aman | Total: 180 | 60.0% | Grade B` | 

## Author

* **Name:** Aditya Kumar
