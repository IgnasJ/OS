package interupts;

/**
 * Created by Ignas on 2017-02-28.
 */
public class Interrupt extends Exception {
    String name;
    public Interrupt(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
