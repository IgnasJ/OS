package interupts;


public class Interrupt extends Exception {

    private String name;

    public Interrupt(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
