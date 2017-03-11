package rm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FlashMemory {

    public FlashMemory() {
    }

    public void readToHDD(String sourceCodeFileName) {
        try(FileReader fr = new FileReader(sourceCodeFileName)) {
            System.out.println(fr.read());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
