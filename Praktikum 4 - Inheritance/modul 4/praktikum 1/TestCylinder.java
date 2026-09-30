public class TestCylinder {
    public static void main(String[] args) {
        Cylinder c1 = new Cylinder();
        Cylinder c2 = new Cylinder(10.0);
        Cylinder c3 = new Cylinder(2.0, 10.0);
        printCylinder("c1", c1);
        printCylinder("c2", c2);
        printCylinder("c3", c3);
        System.out.println(c3);
    }

    private static void printCylinder(String name, Cylinder c) {
        System.out.println(name);
        System.out.println("radius    = " + c.getRadius());
        System.out.println("height    = " + c.getHeight());
        System.out.println("base area = " + c.getArea());
        System.out.println("volume    = " + c.getVolume());
        System.out.println();
    }
}