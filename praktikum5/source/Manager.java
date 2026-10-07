import java.util.Calendar;
import java.util.GregorianCalendar;

public class Manager extends Employee {
    private String secretaryName;

    public Manager(String name, double salary, int day, int month, int year) {
        super(name, salary, day, month, year);
        secretaryName = "";
    }

    @Override
    public void raiseSalary(double byPercent) {
        GregorianCalendar today = new GregorianCalendar();
        int currentYear = today.get(Calendar.YEAR);
        double bonus = 0.5 * (currentYear - hireYear());
        super.raiseSalary(byPercent + bonus);
    }

    public String getSecretaryName() {
        return secretaryName;
    }
}
