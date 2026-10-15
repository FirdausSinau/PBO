public class Commission extends Hourly
{
    private double totalSales;
    private double commissionRate;

    public Commission(String eName, String eAddress, String ePhone,
                      String socSecNumber, double rate, double commissionRate)
    {
        super(eName, eAddress, ePhone, socSecNumber, rate);

        this.commissionRate = commissionRate;
        totalSales = 0;
    }

    public void addSales(double sales)
    {
        totalSales += sales;
    }

    @Override
    public double pay()
    {
        double commission = totalSales * commissionRate;

        totalSales = 0;

        return super.pay() + commission;
    }

    @Override
    public String toString()
    {
        String result = super.toString();

        result += "\nTotal Sales: " + totalSales;

        return result;
    }
}
