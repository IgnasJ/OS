package rm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Ignas on 2017-03-02.
 */
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
