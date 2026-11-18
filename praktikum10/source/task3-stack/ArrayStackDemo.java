public class ArrayStackDemo {

    public static void main(String[] args) {
        ArrayStack<Integer> numbers = new ArrayStack<>(3);
        numbers.push(1);
        numbers.push(2);
        numbers.push(3);
        System.out.println(numbers.pop());
        System.out.println(numbers.pop());
        System.out.println(numbers.pop());

        ArrayStack<String> texts = new ArrayStack<>(2);
        texts.push("PBO");
        texts.push("Collection");
        System.out.println(texts.pop());
        System.out.println(texts.pop());
    }
}
