//You cannot use % / *  operator, how will you find the value of x/y upto two decimal places?
public class DivisionWithoutOp {
    public static void division(int x, int y) {
        int firstNumber = 0;
        while (x >= y) {
            int m = 0;
            while (x >= (y << m)) {
                m++;
            }
            m--;
            firstNumber += (1 << m);
            x -= (y << m);
        }
        int secondNumber = 0;
        x = (x << 6) + (x << 5) + (x << 2);
        while (x >= y) {
            int m = 0;
            while (x >= (y << m)) {
                m++;
            }
            m--;
            secondNumber += (1 << m);
            x -= (y << m);
        }
        System.out.println(firstNumber + "." + secondNumber);
    }

    public static void main(String[] args) {
        division(67, 7);
    }
}
