public class Salesperson implements Comparable
{
    private String firstName, lastName;
    private int totalSales;

    public Salesperson(String first, String last, int sales)
    {
        firstName = first;
        lastName = last;
        totalSales = sales;
    }

    public String toString()
    {
        return lastName + ", " + firstName + ": \t" + totalSales;
    }

    public boolean equals(Object other)
    {
        return (lastName.equals(((Salesperson) other).getLastName()) &&
                firstName.equals(((Salesperson) other).getFirstName()));
    }

    public int compareTo(Object other)
    {
        Salesperson otherSales = (Salesperson) other;

        int result;

        if (getSales() < otherSales.getSales())
            result = -1;
        else if (getSales() > otherSales.getSales())
            result = 1;
        else
        {
            result = getLastName().compareTo(otherSales.getLastName());

            if (result == 0)
                result = getFirstName().compareTo(otherSales.getFirstName());
        }

        return result;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public int getSales()
    {
        return totalSales;
    }
}
