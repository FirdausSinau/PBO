public class Employee extends Sortable {
    private String name;
    private double salary;
    private int hireDay;
    private int hireMonth;
    private int hireYear;

    public Employee(String name, double salary,
                    int day, int month, int year) {
        this.name = name;
        this.salary = salary;
        this.hireDay = day;
        this.hireMonth = month;
        this.hireYear = year;
    }

    public void print() {
        System.out.println(name + " " + salary + " " + hireYear());
    }

    public void raiseSalary(double byPercent) {
        salary *= 1 + byPercent / 100;
    }

    public int hireYear() {
        return hireYear;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public int compare(Sortable other) {
        Employee otherEmployee = (Employee) other;
        if (getSalary() < otherEmployee.getSalary()) return -1;
        if (getSalary() > otherEmployee.getSalary()) return 1;
        return 0;
    }
}
