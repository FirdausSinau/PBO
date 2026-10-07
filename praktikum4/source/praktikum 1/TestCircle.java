public class TestCircle {
    public static void main(String[] args) {
        Circle a = new Circle();
        Circle b = new Circle(2.5);
        Circle c = new Circle(3.0, "blue");
        System.out.println("a: radius=" + a.getRadius() + " area=" + a.getArea());
        System.out.println("b: radius=" + b.getRadius() + " area=" + b.getArea());
        System.out.println("a: " + a);
        System.out.println("b: " + b);
        System.out.println("\n-- sebelum setter --");
        System.out.println("c: radius=" + c.getRadius() + " area=" + c.getArea() + " color=" + c.getColor());
        c.setRadius(5.0);
        c.setColor("green");
        System.out.println("-- sesudah setter --");
        System.out.println("c: radius=" + c.getRadius() + " area=" + c.getArea() + " color=" + c.getColor());
    }
}