/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
<<<<<<< HEAD
<<<<<<< HEAD
import java.util.HashMap;
import java.util.Iterator;
=======
>>>>>>> TNhan
=======
import java.util.HashMap;
import java.util.Iterator;
>>>>>>> Third
import java.util.Scanner;

/**
 *
 * @author Tu Nguyen, NhanTTSE63103
 */
public class Professor extends Person {

    private int experience;
    private int basicSalary = 1000;
    private PositionEnum pos;
    private EducationLevel edu;
    private ArrayList<Person> arr;
    private int count;
    private static int STEP = 3;
    private static double COF = 0.3;

    {
        // inital block
        code = "";
        experience = 0;
        pos = PositionEnum.PROFESSOR;
        edu = EducationLevel.BACHELOR;
        arr = new ArrayList<>();
        count = 0;
    }

    public Professor() {
    }

    public Professor(String code, String name, String address) {
        super(code, name, address);
    }

    public Professor(int experience, int basicSalary, PositionEnum pos, EducationLevel edu, ArrayList<Person> arr, int count, String code, String name, String address) {
        super(code, name, address);
        this.experience = experience;
        this.basicSalary = basicSalary;
        this.pos = pos;
        this.edu = edu;
        this.arr = arr;
        this.count = count;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(int basicSalary) {
        this.basicSalary = basicSalary;
    }

    public PositionEnum getPos() {
        return pos;
    }

    public void setPos(PositionEnum pos) {
        this.pos = pos;
    }

    public EducationLevel getEdu() {
        return edu;
    }

    public void setEdu(EducationLevel edu) {
        this.edu = edu;
    }

    public ArrayList<Person> getArr() {
        return arr;
    }

    public void setArr(ArrayList<Person> arr) {
        this.arr = arr;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return super.toString() + " | " + this.experience + " | " + getRealSalary(this);
    }

    // Class Method
    /**
     * Professor method, calculate real Salary of a professor
     *
     * @param p A professor object wanted to know it's real salary
     * @return Real salary of professor p
     */
    public static double getRealSalary(Professor p) {
        return p.basicSalary * (p.experience / STEP) * (1 + COF) * (1 + p.getPos().getSUPPOS()) * (1 + p.getEdu().getSUPLEV());
    }

    /**
     * Professor method, calculate annual income of a professor
     *
     * @param p A professor object wanted to know it's annual income
     * @return Annual income of professor p
     */
    public static double getAnnualIncome(Professor p) {
        return Professor.getRealSalary(p) * 12 * (1 - Professor.getTAX());
    }

    // Manage Student Methods
    /**
     * #1 Additional Professor method: Parse a line of professor data to this
     * professor's fields
     *
     * @param s Data string to parse to professor's fields
     * @return True if parsing successful
<<<<<<< HEAD
<<<<<<< HEAD
     * @throws project.FormatException wrong format - cannot parse to professor
=======
     * @throws project.FormatException
>>>>>>> TNhan
=======
     * @throws project.FormatException wrong format - cannot parse to professor
>>>>>>> Third
     */
    public boolean parseProfessor(String s) throws FormatException {
        if (s == null || s.length() == 0) {
            throw new FormatException("Empty professor data line");
        }
        String delimiter = "\\s+\\|\\s+"; // \s+: spaces; \|: vertical bar
        String[] spl = s.split(delimiter, 8); // take only 8 tokens, separated by '|'
        if (spl.length < 8) {
            throw new FormatException("Professor line has wrong format"); // wrong format string
        }
        if (Professor.isCodeStandard(spl[0], "PR")) {
            this.setCode(spl[0]); // may throw FormatException here
        }
        this.setName(spl[1]);
        this.setAddress(spl[2]);
        try {
            this.setExperience(Integer.parseInt(spl[3]));
            this.setBasicSalary(Integer.parseInt(spl[4]));
            this.setPos(PositionEnum.valueOf(spl[5]));
            this.setEdu(EducationLevel.valueOf(spl[6]));
            this.setCount(Integer.parseInt(spl[7])); // count is number of student in file, not in arr
        } catch (Exception e) {
            throw new FormatException("Error reading professor data!");
        }
        return true;
    }

    /**
     * Add a new student to Student List
     *
     * @param s New student to add to Student List
     * @return Return true if adding successful
     */
    public boolean addStudent(Student s) {
        if (s == null) {
            System.out.println("Error: Wrong data of student!");
            System.out.println("Add new student fail!");
            return false;
        } // not a student
        try {
            Professor.isExist(s.getCode(), this.arr);
        } catch (FormatException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Add new student fail!");
            return false;
        } // this code has been used
        if (!this.arr.add(s)) {
            return false; // add method of ArrayList returns a boolean value
        }
        System.out.println("New student has been added!");
        return true;
    }

    /**
     * Access input data file and load all data to professor object
     *
     * @param f Data file store data to be loaded
     * @return Return true if loading successful
     */
    public boolean addAllStudent(File f) {
        ArrayList<Person> tmp = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String pLine = br.readLine(); // read first line of file: information of professor
            this.parseProfessor(pLine); // parse pLine to this professor, may throw FormatException here 
            for (int i = 0; i < this.count; ++i) { // start reading student lines
<<<<<<< HEAD
<<<<<<< HEAD
                Student student = new Student();
                try { // check data of this student to add
                    String sLine = br.readLine(); // read student lines
                    student.parseStudent(sLine); // parse String sLine to Stduent student, may throw exception
                    student.setName(Person.trimName(student.getName()));
                    // check code of student
                    Professor.isExist(student.getCode(), tmp);
                    Professor.isExist(student.getCode(), this.arr);
                    // check date of student
                    SimpleDate.isValidDate(student.getValid());
                    if (!tmp.add(student)) {
                        return false; // adding error
                    }
                } catch (FormatException ex) {
                    System.out.println("Error data at line: " + (i+2) + "!");
                }
            }
            br.close(); // close file after reading
=======
                String sLine = br.readLine(); // read student lines
=======
>>>>>>> Third
                Student student = new Student();
                try { // check data of this student to add
                    String sLine = br.readLine(); // read student lines
                    student.parseStudent(sLine); // parse String sLine to Stduent student, may throw exception
                    student.setName(Person.trimName(student.getName()));
                    // check code of student
                    Professor.isExist(student.getCode(), tmp);
                    Professor.isExist(student.getCode(), this.arr);
                    // check date of student
                    SimpleDate.isValidDate(student.getValid());
                    if (!tmp.add(student)) {
                        return false; // adding error
                    }
                } catch (FormatException ex) {
                    System.out.println("Error data at line: " + (i+2) + "!");
                }
            }
<<<<<<< HEAD
>>>>>>> TNhan
=======
            br.close(); // close file after reading
>>>>>>> Third
        } catch (FileNotFoundException ex) { // catch file not found, compile exception
            System.out.println("File not found!");
            return false;
        } catch (IOException ex) { // catch br.readLine() error, compile exception
            System.out.println("Error reading file!");
            return false;
        } catch (FormatException ex) {
            System.out.println(ex.getMessage()); // error format of file, runtime exception
            return false;
        }

        this.arr.addAll(tmp);
<<<<<<< HEAD
<<<<<<< HEAD
        System.out.println("All students from " + f.getName() + " have been added successfully!");
=======
        System.out.println("All students from " + f.getName() + " have been added successful!");
>>>>>>> TNhan
=======
        System.out.println("All students from " + f.getName() + " have been added successfully!");
>>>>>>> Third
        return true;
    }

    /**
     * Remove a specific student by given code
     *
     * @param code Code of student to delete
     */
    public void removeStudent(String code) {
        Student st = findStudent(code); //find student to remove
        if (st == null) {
            System.out.println("No student " + code + " found!");
<<<<<<< HEAD
<<<<<<< HEAD
        } else {
=======
        }
        else {
>>>>>>> TNhan
=======
        } else {
>>>>>>> Third
            System.out.println("Do you want to remove student " + code + " (Y/N)?");//confirm
            Scanner sc = new Scanner(System.in);
            char cf;
            cf = sc.nextLine().charAt(0);
            switch (cf) {
                case 'Y':
                case 'y':
                    this.arr.remove(st);//remove student
                    System.out.println("Student " + code + " successfully remove!");
                    break;
                case 'N':
                case 'n':
                    System.out.println("Cancel, student " + code + " still in list ");//remove cancel by user
                    break;
                default:
                    System.out.println("Invalid choice! ");
                    break;
            }
        }
    }
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> Third

    /**
     * Remove Invalid Student in list
     *
<<<<<<< HEAD
     */
    public void removeInvalid() {
=======
    /**Remove Invalid Student in list
     * 
     */
    public void removeInvalid(){
>>>>>>> TNhan
=======
     */
    public void removeInvalid() {
>>>>>>> Third
        int count = 0;
        System.out.println("Do you want to remove all invalid date students in list (Y/N)?");
        char c;
        Scanner sc = new Scanner(System.in);
        c = sc.nextLine().charAt(0);
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> Third
        if (c == 'Y' || c == 'y') {
            for (int i = 0; i < arr.size(); ++i) {
                if (!((Student) arr.get(i)).isValid()) {
                    System.out.println("Student " + ((Student) arr.get(i)).name + " is not valid: " + ((Student) arr.get(i)).getValid());
                    arr.remove(arr.get(i));
                    --i;
                    count++;
                }
<<<<<<< HEAD
            }
            System.out.println("Remove " + count + " invalid date students in list successful");
        } else if (c == 'N' || c == 'n') {
            System.out.println("Cancel, all students still in list");
        } else {
            System.out.println("Invalid choice!");
        }
    }

    /**
     * Find a specific student by given code
     *
     * @param code Code of student to find
     * @return Found student or null
     */
    public Student findStudent(String code) {
        for (int i = 0; i < this.arr.size(); ++i) {
            if (arr.get(i).code.equals(code)) {
                return (Student) arr.get(i);
            }
        }
        return null;
    }

    /**
     * Update a student with given code
     *
     * @param code Code of student to update
     */
    public void updateStudent(String code) {
        Student st = findStudent(code); // student to be updated
        if (st == null) {
            System.out.println("No student " + code + " found!");
            return;
        }
        Student clone = new Student(); // clone student st, in case update fail
        clone.setCode(st.getCode());
        if (Student.updateStudent(clone, st)) {
            st.update(clone); // move all data from clone to st
            System.out.println("Update student " + st.getCode() + " successful!");
        } else {
            System.out.println("Update student " + st.getCode() + " fail!");
        }
    }

    /**
     * Display the student list
     */
    public void displayAllStudents() {
        System.out.println("*** All students in the list of professor " + this.name + " ***");
        for (int i = 0; i < arr.size(); ++i) {
            System.out.println("No." + (i + 1) + " || " + arr.get(i).toString());
        }
        System.out.println("*** End of list");
    }

    /**
     * #2 Additional Professor Method: Sort student list by code
     */
    public void sortByCode() {
        Collections.sort(arr, Student.compareCode);
    }

    /**
     * #3 Additional Professor Method: Sort student list by name
     */
    public void sortByName() {
        Collections.sort(arr, Student.compareName);
    }

    /**
     * Sort student list by grade
     */
    public void sortByGrade() {
        Collections.sort(arr, Student.compareGrade);
    }

    /**
     * Display student who have highest grade
     */
    public void displayHigestGradeStudents() {
        System.out.println("Students who has highest grade: ");
        Student st = (Student) Collections.min(arr, Student.compareGrade);//find max grade ( array sort descending)
        for (int i = 0; i<arr.size(); ++i){
            if ( st.getGrade() == ((Student)arr.get(i)).getGrade()){
                System.out.println(((Student)arr.get(i)).toString());//Check if any others student have the same highest grade
            }
        }
    }

    /**
     * Display student who have lowest grade
     */
    public void displayLowestGradeStudents() {
        Student st = (Student) Collections.max(arr, Student.compareGrade);//find min grade ( array sort descending)
        for (int i = 0; i<arr.size(); ++i){
            if ( st.getGrade() == ((Student)arr.get(i)).getGrade()){
                System.out.println(((Student)arr.get(i)).toString());//Check if any others student have the same highest grade
            }
        }
    }
    
    /**
     * Display average grade
     */
    public void displayAverageGrade(){
        System.out.print("Average grade of all students: ");
        double sum = 0;
        for ( int i = 0; i<arr.size(); ++i){
            sum += ((Student)arr.get(i)).getGrade();//sum all grade of students
        }
        System.out.format("%.2f\n", sum/(arr.size())); //and then divide to number of students
    }
    
    /**
     * Display distribution of grade
     */
    public void displayDistributionOfGrade(){
        System.out.println("Distribution of grade: ");
        HashMap<Double, Integer> grades = new HashMap();
        for ( int i = 0; i<arr.size(); ++i){
            grades.put(((Student)arr.get(i)).getGrade(), 0);//Put all grades in the HashMap
        }
        for ( int j = 0; j<arr.size(); ++j){
            grades.put(((Student)arr.get(j)).getGrade(), grades.get(((Student)arr.get(j)).getGrade()) +1);//Count the frequency of each grade
        }
        Iterator it = grades.keySet().iterator();
        while(it.hasNext()){
            double key = (Double)(it.next());
            int value = (Integer)(grades.get(key));
            System.out.println("Grade " + key + " appears " + value + " times.");//Print out grades and its frequency
        }
    }
    
/**
 * Display distinct grade
 */
    public void displayDistinctGrade(){
        System.out.println("Distinct grade: ");
        HashMap<Double, Integer> grades = new HashMap();
        for ( int i = 0; i<arr.size(); ++i){
            grades.put(((Student)arr.get(i)).getGrade(), 0);//Put all grades in the HashMap
        }
        for ( int j = 0; j<arr.size(); ++j){
            grades.put(((Student)arr.get(j)).getGrade(), grades.get(((Student)arr.get(j)).getGrade()) +1);//Count the frequency of each grade
        }
        Iterator it = grades.keySet().iterator();
        while(it.hasNext()){
            double key = (Double)(it.next());
            int value = (Integer)(grades.get(key));
            if ( value == 1 ){
                System.out.println("Grade: " + key + " is distinct");//If a grade appear only 1 time, print it out
            }
        }
    }
    /**
     * Display duplicate student name
     */
    public void dupStudentName(){
        System.out.println("Duplicate student name in list: ");
        HashMap<String, Integer> name = new HashMap();
        for ( int i = 0; i<arr.size(); ++i){
            name.put(arr.get(i).getName(), 0);//Put all name in the HashMap
        }
        for ( int j = 0; j<arr.size(); ++j){
            name.put(arr.get(j).getName(), name.get(arr.get(j).getName()) +1);//Count the frequency of each name
        }
        Iterator it = name.keySet().iterator();
        while(it.hasNext()){
            String key = (String)(it.next());
            int value = (Integer)(name.get(key));
            if ( value >1){
                System.out.println("Name: "  + key + " is duplicate " + value + " times.");//Print out which name appear more than 1 time
            }
        }
    }
    
    /**
     * Display unique student name
     */
    public void uniqueStudentName(){
        System.out.println("Unique student name in list: ");
        HashMap<String, Integer> name = new HashMap();
        for ( int i = 0; i<arr.size(); ++i){
            name.put(arr.get(i).getName(), 0);//Put all name in the HashMap
        }
        for ( int j = 0; j<arr.size(); ++j){
            name.put(arr.get(j).getName(), name.get(arr.get(j).getName()) +1);//Count the frequency of each name
        }
        Iterator it = name.keySet().iterator();
        while(it.hasNext()){
            String key = (String)(it.next());
            int value = (Integer)(name.get(key));
            if ( value == 1){
                System.out.println("Name: "  + key + " is unique");//Print out which name appear more than 1 time
            }
        }
    }
    /**
     * #4 Additional Professor Method: Display students who have grades higher
     * or equal to 5 (average grade)
     */
    public void displayHigher5() {
        System.out.println("*** Students with grades higher than 5 ***");
        int count = 0;
        for (int i = 0; i < arr.size(); ++i) {
            Student st = (Student) arr.get(i);
            if (st.getGrade() >= 5) {
                System.out.println("No." + (++count) + " || " + st.toString());
            }
        }
        System.out.println("*** End of list");
    }

    /**
     * #5 Additional Professor Method: Display students who have grades lower
     * than 5 (average grade)
=======
        if ( c == 'Y' || c == 'y'){
        for (int i=0; i<arr.size(); ++i){
            if (!((Student)arr.get(i)).isValid() ){
                System.out.println("Student " + ((Student)arr.get(i)).name + " is not valid: " + ((Student)arr.get(i)).getValid());
                arr.remove(arr.get(i));
                --i;
                count++;
=======
>>>>>>> Third
            }
            System.out.println("Remove " + count + " invalid date students in list successful");
        } else if (c == 'N' || c == 'n') {
            System.out.println("Cancel, all students still in list");
        } else {
            System.out.println("Invalid choice!");
<<<<<<< HEAD
    }
    
=======
        }
    }

>>>>>>> Third
    /**
     * Find a specific student by given code
     *
     * @param code Code of student to find
     * @return Found student or null
     */
    public Student findStudent(String code) {
        for (int i = 0; i < this.arr.size(); ++i) {
            if (arr.get(i).code.equals(code)) {
                return (Student) arr.get(i);
            }
        }
        return null;
    }

    /**
     * Update a student with given code
     *
     * @param code Code of student to update
     */
    public void updateStudent(String code) {
        Student st = findStudent(code); // student to be updated
        if (st == null) {
            System.out.println("No student " + code + " found!");
            return;
        }
        Student clone = new Student(); // clone student st, in case update fail
        clone.setCode(st.getCode());
        if (Student.updateStudent(clone, st)) {
            st.update(clone); // move all data from clone to st
            System.out.println("Update student " + st.getCode() + " successful!");
        } else {
            System.out.println("Update student " + st.getCode() + " fail!");
        }
    }

    /**
     * Display the student list
     */
    public void displayAllStudents() {
        System.out.println("*** All students in the list of professor " + this.name + " ***");
        for (int i = 0; i < arr.size(); ++i) {
            System.out.println("No." + (i + 1) + " || " + arr.get(i).toString());
        }
        System.out.println("*** End of list");
    }

    /**
     * #2 Additional Professor Method: Sort student list by code
     */
    public void sortByCode() {
        Collections.sort(arr, Student.compareCode);
    }

    /**
     * #3 Additional Professor Method: Sort student list by name
     */
    public void sortByName() {
        Collections.sort(arr, Student.compareName);
    }

    /**
     * Sort student list by grade
     */
    public void sortByGrade() {
        Collections.sort(arr, Student.compareGrade);
    }

    /**
     * Display student who have highest grade
     */
    public void displayHigestGradeStudents() {
        System.out.println("Students who has highest grade: ");
        Student st = (Student) Collections.min(arr, Student.compareGrade);//find max grade ( array sort descending)
        for (int i = 0; i<arr.size(); ++i){
            if ( st.getGrade() == ((Student)arr.get(i)).getGrade()){
                System.out.println(((Student)arr.get(i)).toString());//Check if any others student have the same highest grade
            }
        }
    }

    /**
     * Display student who have lowest grade
     */
    public void displayLowestGradeStudents() {
        Student st = (Student) Collections.max(arr, Student.compareGrade);//find min grade ( array sort descending)
        for (int i = 0; i<arr.size(); ++i){
            if ( st.getGrade() == ((Student)arr.get(i)).getGrade()){
                System.out.println(((Student)arr.get(i)).toString());//Check if any others student have the same highest grade
            }
        }
    }
    
    /**
     * Display average grade
     */
    public void displayAverageGrade(){
        System.out.print("Average grade of all students: ");
        double sum = 0;
        for ( int i = 0; i<arr.size(); ++i){
            sum += ((Student)arr.get(i)).getGrade();//sum all grade of students
        }
        System.out.format("%.2f\n", sum/(arr.size())); //and then divide to number of students
    }
    
    /**
     * Display distribution of grade
     */
    public void displayDistributionOfGrade(){
        System.out.println("Distribution of grade: ");
        HashMap<Double, Integer> grades = new HashMap();
        for ( int i = 0; i<arr.size(); ++i){
            grades.put(((Student)arr.get(i)).getGrade(), 0);//Put all grades in the HashMap
        }
        for ( int j = 0; j<arr.size(); ++j){
            grades.put(((Student)arr.get(j)).getGrade(), grades.get(((Student)arr.get(j)).getGrade()) +1);//Count the frequency of each grade
        }
        Iterator it = grades.keySet().iterator();
        while(it.hasNext()){
            double key = (Double)(it.next());
            int value = (Integer)(grades.get(key));
            System.out.println("Grade " + key + " appears " + value + " times.");//Print out grades and its frequency
        }
    }
    
/**
 * Display distinct grade
 */
    public void displayDistinctGrade(){
        System.out.println("Distinct grade: ");
        HashMap<Double, Integer> grades = new HashMap();
        for ( int i = 0; i<arr.size(); ++i){
            grades.put(((Student)arr.get(i)).getGrade(), 0);//Put all grades in the HashMap
        }
        for ( int j = 0; j<arr.size(); ++j){
            grades.put(((Student)arr.get(j)).getGrade(), grades.get(((Student)arr.get(j)).getGrade()) +1);//Count the frequency of each grade
        }
        Iterator it = grades.keySet().iterator();
        while(it.hasNext()){
            double key = (Double)(it.next());
            int value = (Integer)(grades.get(key));
            if ( value == 1 ){
                System.out.println("Grade: " + key + " is distinct");//If a grade appear only 1 time, print it out
            }
        }
    }
    /**
     * Display duplicate student name
     */
    public void dupStudentName(){
        System.out.println("Duplicate student name in list: ");
        HashMap<String, Integer> name = new HashMap();
        for ( int i = 0; i<arr.size(); ++i){
            name.put(arr.get(i).getName(), 0);//Put all name in the HashMap
        }
        for ( int j = 0; j<arr.size(); ++j){
            name.put(arr.get(j).getName(), name.get(arr.get(j).getName()) +1);//Count the frequency of each name
        }
        Iterator it = name.keySet().iterator();
        while(it.hasNext()){
            String key = (String)(it.next());
            int value = (Integer)(name.get(key));
            if ( value >1){
                System.out.println("Name: "  + key + " is duplicate " + value + " times.");//Print out which name appear more than 1 time
            }
        }
    }
    
    /**
     * Display unique student name
     */
    public void uniqueStudentName(){
        System.out.println("Unique student name in list: ");
        HashMap<String, Integer> name = new HashMap();
        for ( int i = 0; i<arr.size(); ++i){
            name.put(arr.get(i).getName(), 0);//Put all name in the HashMap
        }
        for ( int j = 0; j<arr.size(); ++j){
            name.put(arr.get(j).getName(), name.get(arr.get(j).getName()) +1);//Count the frequency of each name
        }
        Iterator it = name.keySet().iterator();
        while(it.hasNext()){
            String key = (String)(it.next());
            int value = (Integer)(name.get(key));
            if ( value == 1){
                System.out.println("Name: "  + key + " is unique");//Print out which name appear more than 1 time
            }
        }
    }
    /**
     * #4 Additional Professor Method: Display students who have grades higher
     * or equal to 5 (average grade)
     */
    public void displayHigher5() {
        System.out.println("*** Students with grades higher than 5 ***");
        int count = 0;
        for (int i = 0; i < arr.size(); ++i) {
            Student st = (Student) arr.get(i);
            if (st.getGrade() >= 5) {
                System.out.println("No." + (++count) + " || " + st.toString());
            }
        }
        System.out.println("*** End of list");
    }

    /**
<<<<<<< HEAD
     * #5 Additional Professor Method: Display students who have grades lower than 5 (average grade)
>>>>>>> TNhan
=======
     * #5 Additional Professor Method: Display students who have grades lower
     * than 5 (average grade)
>>>>>>> Third
     */
    public void displayLower5() {
        System.out.println("*** Students with grades lower than 5 ***");
        int count = 0;
        for (int i = 0; i < arr.size(); ++i) {
            Student st = (Student) arr.get(i);
<<<<<<< HEAD
<<<<<<< HEAD
            if (st.getGrade() < 5) {
                System.out.println("No." + (++count) + " || " + st.toString());
            }
        }
        System.out.println("*** End of list");
    }

    /**
     * #6 Additional Professor Method: Display students who is in n-th at
     * university
     *
=======
            if (st.getGrade()<5) System.out.println("No." + (++count) + " || " + st.toString());
=======
            if (st.getGrade() < 5) {
                System.out.println("No." + (++count) + " || " + st.toString());
            }
>>>>>>> Third
        }
        System.out.println("*** End of list");
    }

    /**
<<<<<<< HEAD
     * #6 Additional Professor Method: 
     * Display students who is in n-th at university
>>>>>>> TNhan
=======
     * #6 Additional Professor Method: Display students who is in n-th at
     * university
     *
>>>>>>> Third
     * @param sNth N-th year students to display
     */
    public void displayNthYearStudent(String sNth) {
        int nth = 0;
        try {
            nth = Integer.parseInt(sNth);
        } catch (NumberFormatException ex) {
            System.out.println("Require number!");
            return;
        }
        System.out.println("*** " + nth + "-th year students ***");
        int count = 0;
        LocalDateTime now = LocalDateTime.now();
        int year = now.getYear();
        for (int i = 0; i < arr.size(); ++i) {
            Student st = (Student) arr.get(i);
<<<<<<< HEAD
<<<<<<< HEAD
            if (year - st.getValid().getYear() + 1 == nth) {
                System.out.println("No." + (++count) + " || " + st.toString());
            }
        }
        System.out.println("*** End of list");
    }

    /**
     * #7 Additional Professor Method: Get the number of students enrolled in a
     * given year
     *
     * @param sYear Year to find number of enrolled students
     * @return Number of enrolled students in 'year'
     */
=======
            if (year - st.getValid().getYear() + 1 == nth) System.out.println("No." + (++count) + " || " + st.toString());
=======
            if (year - st.getValid().getYear() + 1 == nth) {
                System.out.println("No." + (++count) + " || " + st.toString());
            }
>>>>>>> Third
        }
        System.out.println("*** End of list");
    }

    /**
     * #7 Additional Professor Method: Get the number of students enrolled in a
     * given year
     *
     * @param sYear Year to find number of enrolled students
     * @return Number of enrolled students in 'year'
<<<<<<< HEAD
     */ 
>>>>>>> TNhan
=======
     */
>>>>>>> Third
    public int getNumberStudentsOfYear(String sYear) {
        int count = 0, year = 0;
        try {
            year = Integer.parseInt(sYear);
        } catch (NumberFormatException ex) {
            System.out.println("Required a number!");
            return -1;
        }
<<<<<<< HEAD
<<<<<<< HEAD
        for (int i = 0; i < arr.size(); ++i) {
            Student st = (Student) arr.get(i);
            if (st.getValid().getYear() == year) {
                ++count;
            }
        }
        return count;
    }

    /**
     * #8 Additional Professor Method: Update information of professor
     *
=======
        for (int i=0; i<arr.size(); ++i) {
=======
        for (int i = 0; i < arr.size(); ++i) {
>>>>>>> Third
            Student st = (Student) arr.get(i);
            if (st.getValid().getYear() == year) {
                ++count;
            }
        }
        return count;
    }

    /**
<<<<<<< HEAD
     * #8 Additional Professor Method: 
     * Update information of professor
>>>>>>> TNhan
=======
     * #8 Additional Professor Method: Update information of professor
     *
>>>>>>> Third
     * @return Return true if update successful
     */
    public boolean updateProfessor() {
        Professor tmp = new Professor();
        Scanner sc = new Scanner(System.in);
        boolean skipPos = false, skipEdu = false, skipExp = false, skipBS = false;
        try {
            if (this.code.length() == 0) {
                System.out.print("Enter professor code (PRxxx): ");
                tmp.code = sc.nextLine();
                Person.isCodeStandard(tmp.code, "PR");
            }
            System.out.println("NOTE: type 'skip' to skip updating that information!");
            System.out.print("Enter professor name: ");
            tmp.name = sc.nextLine();
            System.out.print("Enter professor address: ");
            tmp.address = sc.nextLine();
            System.out.print("Enter professor position: ");
            String pos = sc.nextLine().toUpperCase();
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> Third
            if (!pos.equalsIgnoreCase("skip")) {
                tmp.pos = PositionEnum.valueOf(pos);
            } else {
                skipPos = true;
            }
<<<<<<< HEAD
            System.out.print("Enter professor edu: ");
            String edu = sc.nextLine().toUpperCase();
            if (!edu.equalsIgnoreCase("skip")) {
                tmp.edu = EducationLevel.valueOf(edu);
            } else {
                skipEdu = true;
            }
            System.out.print("Enter professor experience (int): ");
            String exp = sc.nextLine();
            if (!exp.equalsIgnoreCase("skip")) {
                tmp.experience = Integer.parseInt(exp);
            } else {
                skipExp = true;
            }
            System.out.print("Enter professor basic salary: ");
            String bs = sc.nextLine();
            if (!bs.equalsIgnoreCase("skip")) {
                tmp.basicSalary = Integer.parseInt(bs);
            } else {
                skipBS = true;
            }
=======
            if (!pos.equalsIgnoreCase("skip")) tmp.pos = PositionEnum.valueOf(pos);
            else skipPos = true;
=======
>>>>>>> Third
            System.out.print("Enter professor edu: ");
            String edu = sc.nextLine().toUpperCase();
            if (!edu.equalsIgnoreCase("skip")) {
                tmp.edu = EducationLevel.valueOf(edu);
            } else {
                skipEdu = true;
            }
            System.out.print("Enter professor experience (int): ");
            String exp = sc.nextLine();
            if (!exp.equalsIgnoreCase("skip")) {
                tmp.experience = Integer.parseInt(exp);
            } else {
                skipExp = true;
            }
            System.out.print("Enter professor basic salary: ");
            String bs = sc.nextLine();
<<<<<<< HEAD
            if (!bs.equalsIgnoreCase("skip")) tmp.basicSalary = Integer.parseInt(bs);
            else skipBS = true;
>>>>>>> TNhan
=======
            if (!bs.equalsIgnoreCase("skip")) {
                tmp.basicSalary = Integer.parseInt(bs);
            } else {
                skipBS = true;
            }
>>>>>>> Third
        } catch (FormatException | IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Error update professor!");
            return false;
        }
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> Third
        if (this.code.length() == 0) {
            this.code = tmp.code;
        }
        if (!tmp.name.equalsIgnoreCase("skip")) {
            this.name = tmp.name;
        }
        if (!tmp.address.equalsIgnoreCase("skip")) {
            this.address = tmp.address;
        }
        if (!skipBS) {
            this.basicSalary = tmp.basicSalary;
        }
        if (!skipEdu) {
            this.edu = tmp.edu;
        }
        if (!skipPos) {
            this.pos = tmp.pos;
        }
        if (!skipExp) {
            this.experience = tmp.experience;
        }
<<<<<<< HEAD
        return true;
    }

    /**
     * #9 Additional Professor Method: Display information of professor
=======
        if (this.code.length() == 0) this.code = tmp.code;
        if (!tmp.name.equalsIgnoreCase("skip")) this.name = tmp.name;
        if (!tmp.address.equalsIgnoreCase("skip")) this.address = tmp.address;
        if (!skipBS) this.basicSalary = tmp.basicSalary;
        if (!skipEdu) this.edu = tmp.edu;
        if (!skipPos) this.pos = tmp.pos;
        if (!skipExp) this.experience = tmp.experience;
=======
>>>>>>> Third
        return true;
    }

    /**
<<<<<<< HEAD
     * #9 Additional Professor Method: 
     * Display information of professor
>>>>>>> TNhan
=======
     * #9 Additional Professor Method: Display information of professor
>>>>>>> Third
     */
    public void displayProfessor() {
        System.out.println("*** Information of professor ***");
        System.out.println("Professor code: " + this.code);
        System.out.println("Professor name: " + this.name);
        System.out.println("Professor address: " + this.address);
        System.out.println("Professor postition: " + this.pos);
        System.out.println("Professor education level: " + this.edu);
        System.out.println("Professor experience: " + this.experience);
        System.out.println("Professor basic salary: " + this.basicSalary);
        System.out.format("Professor real salary: %.0f\n", Professor.getRealSalary(this));
        System.out.format("Professor annual income: %.0f\n", Professor.getAnnualIncome(this));
        System.out.println("Professor number of students: " + this.arr.size());
        System.out.println("*** ***");
    }
<<<<<<< HEAD
<<<<<<< HEAD

    /**
     * #10 Additional Professor Method: Check if student code 'sCode' is used in
     * student list 'arr'
     *
=======
    
    /**
     * #10 Additional Professor Method: 
     * Check if student code 'sCode' is used in student list 'arr'
>>>>>>> TNhan
=======

    /**
     * #10 Additional Professor Method: Check if student code 'sCode' is used in
     * student list 'arr'
     *
>>>>>>> Third
     * @param sCode Student code to check exist
     * @param arr Domain students to check
     */
    public static void isExist(String sCode, ArrayList<Person> arr) throws FormatException {
<<<<<<< HEAD
<<<<<<< HEAD
        for (int i = 0; i < arr.size(); ++i) {
            Student st = (Student) arr.get(i);
            if (st.getCode().equals(sCode)) {
                throw new FormatException("Error: Duplicate student code found!");
            }
        }
    }

    /**
     * Export professor and student list data to file
     *
=======
        for (int i=0; i<arr.size(); ++i) {
=======
        for (int i = 0; i < arr.size(); ++i) {
>>>>>>> Third
            Student st = (Student) arr.get(i);
            if (st.getCode().equals(sCode)) {
                throw new FormatException("Error: Duplicate student code found!");
            }
        }
    }

    /**
     * Export professor and student list data to file
<<<<<<< HEAD
>>>>>>> TNhan
=======
     *
>>>>>>> Third
     * @param f Destination file to export data
     */
    public void exportTo(File f) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            // write professor line
            bw.write(code + " | " + name + " | " + address + " | " + experience + " | " + basicSalary + " | " + pos + " | " + edu + " | " + arr.size());
            bw.newLine();
            // write student lines
<<<<<<< HEAD
<<<<<<< HEAD
            for (Person p : arr) {
=======
            for (Person p: arr) {
>>>>>>> TNhan
=======
            for (Person p : arr) {
>>>>>>> Third
                Student st = (Student) p;
                bw.write(st.toString() + " | " + st.getValid().toString());
                bw.newLine();
            }
            bw.close();
            System.out.println("Export to file '" + f.getName() + "' successful!");
        } catch (IOException ex) {
            System.out.println("Error writing file '" + f.getName() + "'!");
        }
    }
}
