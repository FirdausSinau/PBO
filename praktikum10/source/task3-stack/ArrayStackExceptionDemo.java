public class ArrayStackExceptionDemo {

    public static void main(String[] args) {
        ArrayStack<Integer> numbers = new ArrayStack<>(3);
        numbers.push(1);
        numbers.push(2);
        numbers.push(3);

        try {
            numbers.push(4);
        } catch (ArrayStack.ArrayStackException e) {
            System.out.println("Overflow: " + e.getMessage());
        }

        System.out.println(numbers.pop());
        System.out.println(numbers.pop());
        System.out.println(numbers.pop());

        try {
            numbers.pop();
        } catch (ArrayStack.ArrayStackException e) {
            System.out.println("Underflow: " + e.getMessage());
        }
    }
}
