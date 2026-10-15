public class Staff
{
    StaffMember[] staffList;

    public Staff()
    {
        staffList = new StaffMember[8];

        staffList[0] = new Executive("Sam", "123 Main Line",
                "555-0469", "123-45-6789", 2423.07);

        staffList[1] = new Employee("Carla", "456 Off Line",
                "555-0101", "987-65-4321", 1246.15);
        staffList[2] = new Employee("Woody", "789 Off Rocker",
                "555-0000", "010-20-3040", 1169.23);

        staffList[3] = new Hourly("Diane", "678 Fifth Ave.",
                "555-0690", "958-47-3625", 10.55);

        staffList[4] = new Volunteer("Norm", "987 Suds Blvd.",
                "555-8374");
        staffList[5] = new Volunteer("Cliff", "321 Duds Lane",
                "555-7282");

        Commission c6 = new Commission("Rina", "Jl. Merdeka 10",
                "555-1122", "111-22-3333", 6.25, 0.20);
        c6.addHours(35);
        c6.addSales(400);
        staffList[6] = c6;

        Commission c7 = new Commission("Bayu", "Jl. Sudirman 25",
                "555-3344", "444-55-6666", 9.75, 0.15);
        c7.addHours(40);
        c7.addSales(950);
        staffList[7] = c7;

        ((Executive) staffList[0]).awardBonus(500.00);

        ((Hourly) staffList[3]).addHours(40);
    }

    public void payday()
    {
        double amount;

        for (int count = 0; count < staffList.length; count++)
        {
            System.out.println(staffList[count]);

            amount = staffList[count].pay();

            if (amount == 0.0)
                System.out.println("Mksh y!");
            else
                System.out.println("Paid: " + amount);

            System.out.println("-----------------------------------");
        }
    }
}
