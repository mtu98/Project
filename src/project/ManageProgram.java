/*
 * To change this license header, choose License Headers in ManageProgram Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.io.File;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author Tu Nguyen
 */
public class ManageProgram {

    public static void loadFile(Professor pr) {
        File f = new File("initial data.txt");
        pr.addAllStudent(f);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // test add all student OK - just need to check duplicate student code

        Menu menu = new Menu();
        menu.add("1. Add student");
        menu.add("2. Remove student");
        menu.add("3. Update student");
        menu.add("4. Find student");
        menu.add("5. Display students");
        menu.add("6. Display statistic");
        menu.add("7. Export student list to file");
        menu.add("8. About professor");
        menu.add("9. More display options");
        menu.add("10. Quit");
        menu.addSubMenu(1, "1. Add a new student");
        menu.addSubMenu(1, "2. Import student list from file");
        menu.addSubMenu(2, "1. Remove a student");
        menu.addSubMenu(2, "2. Remove invalid students");
        menu.addSubMenu(5, "1. Display students sorted by student code");
        menu.addSubMenu(5, "2. Display students sorted by student name");
        menu.addSubMenu(5, "3. Display students sorted by student grade");
        menu.addSubMenu(5, "4. Display highest grade students");
        menu.addSubMenu(5, "5. Display lowest grade students");
        menu.addSubMenu(6, "1. Display average grade");
        menu.addSubMenu(6, "2. Display distribution of grade");
        menu.addSubMenu(6, "3. Display distinct grade");
        menu.addSubMenu(6, "4. Display duplicate student names");
        menu.addSubMenu(6, "5. Display unique student names");
        menu.addSubMenu(8, "1. Update informantion of professor");
        menu.addSubMenu(8, "2. Display informantion of professor");
        menu.addSubMenu(9, "1. Display students have grades higher than average");
        menu.addSubMenu(9, "2. Display students have grades lower than average");
        menu.addSubMenu(9, "3. Display n-th year students");
        menu.addSubMenu(9, "4. Display number of students enrolled in a specific year");

        Scanner sc = new Scanner(System.in);

        Professor pr = new Professor();

        ManageProgram.loadFile(pr); // load initial data to run program
        System.out.println("\n============ Manage Student Program ============");

        do {
            menu.displayMenu();
            switch (menu.getChoice()) {
                case 1:
                    System.out.println("\n=== Add student ===");
                    menu.displaySubMenu(1);
                    switch (menu.getChoice()) {
                        case 1:
                            System.out.println("\n--> Add new student from keyboard");
                            pr.addStudent(Student.newStudent());
                            break;
                        case 2:
                            System.out.println("\n--> Import students from file");
                            System.out.print("Enter file name to import (Ex. in_students.txt): ");
                            File f = new File(sc.nextLine());
                            if (!pr.addAllStudent(f)) {
                                System.out.println("Import students fail!");
                            }
                            break;
                        default:
                            System.out.println("\nNo option found");
                    }
                    break;
                case 2:
                    System.out.println("\n=== Remove student ===");
                    menu.displaySubMenu(2);
                    switch (menu.getChoice()) {
                        case 1:
                            System.out.println("\n--> Remove a student by student code");
                            System.out.print("Enter student code to remove: ");
                            String code = sc.nextLine();
                            pr.removeStudent(code);
                            break;
                        case 2:
                            System.out.println("\n--> Remove invalid students");
                            pr.removeInvalid();
                            break;
                        default:
                            System.out.println("\nNo option found");
                    }
                    break;
                case 3:
                    System.out.println("\n=== Update a student ===");
                    System.out.print("Enter student code to update: ");
                    pr.updateStudent(sc.nextLine());
                    break;
                case 4:
                    System.out.println("\n=== Find a student ===");
                    System.out.print("Enter student code to find: ");
                    Student st = pr.findStudent(sc.nextLine());
                    if (st != null) {
                        System.out.println("Found: " + st.toString());
                    } else {
                        System.out.println("No student found!");
                    }
                    break;
                case 5:
                    System.out.println("\n=== Display students ===");
                    menu.displaySubMenu(5);
                    switch (menu.getChoice()) {
                        case 1:
                            System.out.println("\n--> Display students sorted by student code"); // ascending
                            pr.sortByCode();
                            pr.displayAllStudents();
                            break;
                        case 2:
                            System.out.println("\n--> Display students sorted by student name"); // ascending
                            pr.sortByName();
                            pr.displayAllStudents();
                            break;
                        case 3:
                            System.out.println("\n--> Display students sorted by student grade"); // descending
                            pr.sortByGrade();
                            pr.displayAllStudents();
                            break;
                        case 4:
                            System.out.println("\n--> Display highest grade students");
                            pr.displayHigestGradeStudents();
                            break;
                        case 5:
                            System.out.println("\n--> Display lowest grade students");
                            pr.displayLowestGradeStudents();
                            break;
                        default:
                            System.out.println("\nNo option found");
                    }
                    break;
                case 6:
                    System.out.println("\n=== Display statistic ===");
                    menu.displaySubMenu(6);
                    switch (menu.getChoice()) {
                        case 1:
                            System.out.println("\n--> Display average grade");
                            pr.displayAverageGrade();
                            break;
                        case 2:
                            System.out.println("\n--> Display distribution of grade");
                            pr.sortByGrade();
                            pr.displayDistributionOfGrade();
                            break;
                        case 3:
                            System.out.println("\n--> Display distinct grade");
                            pr.sortByGrade();
                            pr.displayDistinctGrade();
                            break;
                        case 4:
                            System.out.println("\n--> Display duplicate student names");
                            pr.sortByName();
                            pr.dupStudentName();
                            break;
                        case 5:
                            System.out.println("\n--> Display unique student names");
                            pr.sortByName();
                            pr.uniqueStudentName();
                            break;
                        default:
                            System.out.println("\nNo option found");
                    }
                    break;
                case 7:
                    System.out.println("\n=== Export student list to file ===");
                    System.out.print("\nEnter file name to export (Ex. out_students.txt): ");
                    File f = new File(sc.nextLine());
                    pr.sortByCode(); // sort by code before exporting
                    pr.exportTo(f);
                    break;
                case 8:
                    System.out.println("\n=== About professor ===");
                    menu.displaySubMenu(8);
                    switch (menu.getChoice()) {
                        case 1:
                            System.out.println("\n--> Update information of professor");
                            if (pr.updateProfessor()) {
                                System.out.println("Data of professor has been updated successful!");
                            }
                            break;
                        case 2:
                            System.out.println("\n--> Display information of professor");
                            pr.displayProfessor();
                            break;
                        default:
                            System.out.println("\nNo option found");
                    }
                    break;
                case 9:
                    System.out.println("\n=== More display options ===");
                    menu.displaySubMenu(9);
                    switch (menu.getChoice()) {
                        case 1:
                            System.out.println("\n--> Display students have grades higher than 5");
                            pr.sortByCode();
                            pr.displayHigher5();
                            break;
                        case 2:
                            System.out.println("\n--> Display students have grades lower than 5");
                            pr.sortByCode();
                            pr.displayLower5();
                            break;
                        case 3:
                            System.out.println("\n--> Display n-th year students");
                            System.out.print("Enter n: ");
                            pr.sortByCode();
                            pr.displayNthYearStudent(sc.nextLine());
                            break;
                        case 4:
                            System.out.println("\n--> Display number of students enrolled in a specific year");
                            System.out.print("Enter year: ");
                            int count = pr.getNumberStudentsOfYear(sc.nextLine());
                            if (count > -1) {
                                System.out.println("Found: " + count + " students.");
                            } else {
                                System.out.println("No student found!");
                            }
                            break;
                        default:
                            System.out.println("\nNo option found");
                    }
                    break;
                case 10:
                    System.out.println("\n============ Good Bye ============");
                    return;
                default:
                    System.out.println("\nNo option found");
            }
        } while (true);
    }
}
