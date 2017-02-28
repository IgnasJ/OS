package registers;

/**
 * Created by Ignas on 2017-02-28.
 */
public class Register {
    String name;
    int size;
    char[] content;

    public Register(String name, int size) {
        this.name = name;
        this.size = size;
        this.content = new char[size];
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public char[] getContent() {
        return content;
    }

    public void setContent(char[] content) {
        this.content = content;
    }
}
