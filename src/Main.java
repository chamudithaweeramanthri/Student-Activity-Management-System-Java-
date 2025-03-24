import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Main {

    public static Student[] studentArray = new Student[100];


    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.println("Student Activity Management System");
            System.out.println();
            System.out.println("1. Check available seats.");
            System.out.println("2. Register student (with ID).");
            System.out.println("3. Delete student.");
            System.out.println("4. Find student (with student ID).");
            System.out.println("5. Store student details.");
            System.out.println("6. Load student details.");
            System.out.println("7. View the list of students.");
            System.out.println("8. more controls.");
            System.out.println("9. Exit");

            try {

                System.out.print("Enter Your Choice : ");

                int choice = input.nextInt();
                input.nextLine();

                if (choice < 1 || choice > 9) {
                    System.out.println("Enter Choice between 1-9");
                    //continue;

                } else if (choice == 1) {
                    available_Seats(studentArray);
                } else if (choice == 2) {
                    stu_id(studentArray);
                } else if (choice == 3) {
                    Del_Student(studentArray);
                } else if (choice == 4) {
                    find_student(studentArray);
                } else if (choice == 5) {
                    save_Details(studentArray);
                } else if (choice == 6) {
                    load_file(studentArray);
                } else if (choice == 7) {
                    view_Student();
                } else if (choice == 8){
                    more_controls();
                } else {
                    break;
                }

            } catch (InputMismatchException e) {

                System.out.println("Enter A Valid Number");
                input.nextLine();
                continue;
            }


        }


    }
    // Check available seats
    public static void available_Seats(Student[] studentArray){
        int Available = 0;
        for (int x = 0; x < studentArray.length; x++) {
            if (studentArray[x] == null) {
                Available += 1;
            }
        }
        System.out.println(Available + " Seats Are Available.");

    }

    // Student register
    public static void stu_id(Student [] Student  ){

        Scanner input = new Scanner(System.in);
        while (true){

            System.out.print("Enter Student ID : ");
            String stu_id = input.next().toUpperCase();

            if(check_id(stu_id)){
                System.out.println("UOW ID Exists.");
                continue;
            }
            if(id_characters(stu_id)) {
                for (int x = 0; x < studentArray.length; x++) {
                    if (studentArray[x] == null) {
                        //Student student = new Student(stu_id, name, mark1, mark2, mark3);
                        Student student = new Student(stu_id,"", 0, 0, 0);
                        studentArray[x] = student;
                        System.out.println("Student registered Successfully");
                        return;
                        //break;

                    }

                }

            }
            System.out.println("Enter Valid UOW Id Format.");

        }

    }

    // Delete Student
    public static void Del_Student(Student [] Student){
        boolean id_exist = false;
        Scanner input = new Scanner(System.in);
        while (!id_exist){
            System.out.print("Enter UOW ID You Want To Remove : ");
            String id_Del = input.next().toUpperCase();
            //id_characters(id_Del);

            if (id_characters(id_Del)) {

                for (int x = 0; x < Student.length; x++) {

                    if (studentArray[x] != null && id_Del.equals(Student[x].getStudentId())) {

                        id_exist = true;
                        Student[x] = null;
                        System.out.println("Id Removed Successfully.");
                        break;
                    }
                }

                if(!id_exist){
                    System.out.println("ID not in the list. Please re-enter the ID.");
                }
            }
            else {
                System.out.println("Enter correct UOW ID.");

            }
        }


    }

    //find student position

    public static void find_student(Student [] Student){
        boolean id_exist = false;
        Scanner input = new Scanner(System.in);
        while (!id_exist){
            System.out.print("Enter UOW ID You Want To Find : ");
            String stu_id = input.next().toUpperCase();
            //id_characters(id_Del);

            if (id_characters(stu_id)) {

                for (int x = 0; x < Student.length; x++) {

                    if (studentArray[x] != null && stu_id.equals(Student[x].getStudentId())) {

                        id_exist = true;
                        System.out.println(stu_id+" is in Seat No : "+(x+1));
                        break;
                    }
                }

                if(!id_exist){
                    System.out.println("ID not in the list. Please re-enter the ID.");
                }
            }
            else {
                System.out.println("Enter correct UOW ID.");

            }
        }


    }

    //check Student Id Exists or not
    public static boolean check_id(String stu_id){
        for (int x = 0; x< studentArray.length; x++){
            if(studentArray[x] != null && stu_id.equals(studentArray[x].getStudentId())){
                //System.out.println("UOW Id Exists");
                return true;
            }
        }
        return false;

    }

    // check student id characters count and format
    public static boolean id_characters(String stu_id ){

        if(stu_id.charAt(0)=='W' && stu_id.length()==8){

            return true;

        }
        return false;

    }

    // View Student list
    public static void view_Student(){
        //load_file(Student);
        for(int y = 0; y< studentArray.length; y++) {
            if (studentArray[y] != null) {
                System.out.println((y+1)+" "+ studentArray[y].getStudentName());
                //System.out.println((y+1)+" "+ studentArray[y].getStudentId());
                //y++;
            }
        }

    }


    // Load File
    public static void load_file(Student[] studentArray) {
        try {
            File file = new File("Students.txt");
            Scanner file_reader = new Scanner(file);

            int index = 0;
            while (file_reader.hasNextLine() && index < studentArray.length) {
                String line = file_reader.nextLine();
                String[] parts = line.split(","); // Assuming your data is comma-separated

                if (parts.length >= 5) { // Ensure all necessary fields are present
                    String stu_id = parts[0].trim();
                    String name = parts[1].trim();
                    int mark1 = Integer.parseInt(parts[2].trim());
                    int mark2 = Integer.parseInt(parts[3].trim());
                    int mark3 = Integer.parseInt(parts[4].trim());

                    Student student = new Student(stu_id, name, mark1, mark2, mark3);
                    studentArray[index] = student;
                    index++;
                }
            }

            file_reader.close();

        } catch (IOException e) {
            System.out.println("Error while reading file");
        } catch (NumberFormatException e) {
            System.out.println("Error in parsing marks from file");
        }
    }

    // Save File
    public static void save_Details(Student[] studentArray) {
        try {
            FileWriter writer = new FileWriter("Students.txt");

            for (Student student : studentArray) {
                if (student != null) {
                    String studentData = student.getStudentId() + "," +
                            student.getStudentName() + "," +
                            student.getMark1() + "," +
                            student.getMark2() + "," +
                            student.getMark3() + System.lineSeparator();
                    writer.write(studentData);
                    //writer.newLine();

                }
            }

            writer.close();
            System.out.println("Student details saved successfully.");

        } catch (IOException e) {
            System.out.println("Error while saving file");
        }
    }

    //Additional Menu
    public static void more_controls(){
        Scanner input = new Scanner(System.in);
        while (true){
            System.out.println();
            System.out.println("a. Add Name");
            System.out.println("b. Add Module Marks. ");
            System.out.println("c. Summary. ");
            System.out.println("d. Generate Report ");

            System.out.print("Enter Choice (a,b,c,d) : ");
            String choice = input.next();

            if(choice.equals("a")){
                addName();
                break;
            } else if (choice.equals("b")) {
                addMarks();
                break;
            } else if (choice.equals("c")){
                summary();
                break;
            } else if (choice.equals("d")){
                report();
                break;
            }
            else{
                System.out.println("Invalid Choice.Enter(a,b,c or d)");
            }
        }
    }

    // Method to add student name
    public static void addName() {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter UOW ID to add Name : ");
        String stu_id = input.next().toUpperCase();

        if (check_id(stu_id)) {
            for (int x = 0; x < studentArray.length; x++) {
                if (studentArray[x] != null && stu_id.equals(studentArray[x].getStudentId())) {
                    System.out.print("Enter Name : ");
                    String name = input.next();
                    studentArray[x].setStudentName(name);
                    System.out.println("Name updated successfully.");
                    return;
                }
            }
        } else {
            System.out.println("ID not in the list. Please re-enter the ID.");
            addName();
        }
    }

    // Method to add module marks
    public static void addMarks() {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter UOW ID : ");
        String stu_id = input.next().toUpperCase();

        if (check_id(stu_id)) {
            for (int x = 0; x < studentArray.length; x++) {
                if (studentArray[x] != null && stu_id.equals(studentArray[x].getStudentId())) {
                    System.out.print("Enter Mark 1 : ");
                    //int mark1 = input.nextInt();
                    int mark1 = filterMarks();
                    System.out.print("Enter Mark 2 : ");
                    //int mark2 = input.nextInt();
                    int mark2 = filterMarks();
                    System.out.print("Enter mark 3 : ");
                    //int mark3 = input.nextInt();
                    int mark3 = filterMarks();

                    studentArray[x].setMark1(mark1);
                    studentArray[x].setMark2(mark2);
                    studentArray[x].setMark3(mark3);

                    System.out.println("marks updated successfully.");
                    return;
                }
            }
        } else {
            System.out.println("UOW ID does not exist.");
        }
    }

    //filter for Marks
    public static int filterMarks(){
        Scanner input = new Scanner(System.in);
        while (true){
            try{
                int mark = input.nextInt();
                if (mark >= 1 && mark <= 100) {
                    return mark;
                }
                else {
                    System.out.print("Enter a valid mark (1-100) : ");
                }
            }
            catch (InputMismatchException  e){
                System.out.print("Enter valid number : ");
                input.nextLine();
            }
        }
    }

    //generate Summary
    public static void summary(){
        int count = 0;
        for (int x = 0; x < studentArray.length; x++) {
            if (studentArray[x] != null) {
                count += 1;
            }
        }
        System.out.println("Total Student registrations = "+count);

        // Total no of students who are scored more than 40 marks in Module 1, 2 and 3.
        int stu_count =0;
        for(int y =0 ;y<studentArray.length;y++){
            if(studentArray[y] != null && studentArray[y].getMark1()>40 && studentArray[y].getMark2()>40 && studentArray[y].getMark3()>40 ){
                stu_count ++;
            }
        }
        System.out.println(stu_count+"  students scored than 40 marks.");
    }

    // calculate grade
    public static void grade(double average){
        if (average>=80){
            System.out.println("Distinction");
        } else if (average >= 70) {
            System.out.println("Merit");

        } else if (average >= 40) {
            System.out.println("Pass");

        }else {
            System.out.println("Fail");
        }
    }

    // create report
    public static void report(){
        for (int x=0;x<studentArray.length;x++){
            if(studentArray[x] != null){
                int total = studentArray[x].getMark1() + studentArray[x].getMark2() + studentArray[x].getMark3();
                double average = total/3.0;
                System.out.println("Id : "+studentArray[x].getStudentId());
                System.out.println("Name : "+studentArray[x].getStudentName());
                System.out.println("Module 01 : "+studentArray[x].getMark1()+"%");
                System.out.println("Module 02 : "+studentArray[x].getMark2()+"%");
                System.out.println("Module 03 : "+studentArray[x].getMark3()+"%");
                System.out.println("Total : "+total);
                System.out.println("Average : "+average+"%");
                grade(average);
                System.out.println();
            }
        }
    }
}
