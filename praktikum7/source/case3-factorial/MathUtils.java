public class MathUtils {
    public static int factorial(int n) throws IllegalArgumentException {
        if (n < 0) {
            throw new IllegalArgumentException(
                "nilai tidak boleh negatif: " + n);
        }
        if (n > 16) {
            throw new IllegalArgumentException(
                "nilai terlalu besar untuk factorial berbasis int: " + n);
        }

        int fac = 1;
        for (int i = n; i > 0; i--)
            fac *= i;
        return fac;
    }
}
