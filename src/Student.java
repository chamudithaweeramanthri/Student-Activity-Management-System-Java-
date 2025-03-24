public class Student {

    private String studentName;
    private String studentId;
    private int Mark1;
    private int Mark2;
    private int Mark3;

    public Student(String studentId, String studentName,int Mark1,int Mark2,int Mark3){
        this.studentId = studentId;
        this.studentName = studentName;
        this.Mark1 = Mark1;
        this.Mark2 = Mark2;
        this.Mark3 = Mark3;

    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {

        this.studentId = studentId;
    }

    public int getMark1() {
        return Mark1;
    }

    public void setMark1(int mark1) {
        Mark1 = mark1;
    }

    public int getMark2() {
        return Mark2;
    }

    public void setMark2(int mark2) {
        Mark2 = mark2;
    }

    public int getMark3() {
        return Mark3;
    }

    public void setMark3(int mark3) {
        Mark3 = mark3;
    }
}
