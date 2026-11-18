public class Student {
    private String studentID;
    private String name;
    private Object department;
    private String address;

    public Student(String studentID, String name,
                   Object department, String address) {
        this.studentID = studentID;
        this.name = name;
        this.department = department;
        this.address = address;
    }

    public String getStudentID() { return studentID; }
    public String getName() { return name; }
    public Object getDepartment() { return department; }
    public String getAddress() { return address; }

    @Override
    public String toString() {
        return studentID + " | " + name + " | "
            + department + " | " + address;
    }
}
