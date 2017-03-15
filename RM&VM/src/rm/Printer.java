package rm;

public class Printer {

    public static void print(Object data) {
        if (data instanceof char[]) {
            System.out.println((char[]) data);
        } else {
            System.out.println(data);
        }
    }

}
