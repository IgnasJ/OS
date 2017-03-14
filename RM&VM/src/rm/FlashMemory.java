package rm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FlashMemory {

    private HDD hdd;

    public FlashMemory(HDD hdd) {
        this.hdd = hdd;
    }

    public void readToHDD(String sourceFile) {

        try(FileReader fr = new FileReader(sourceFile);
            BufferedReader br = new BufferedReader(fr)){
            String line = "";
            int sector = 0;
            while((line = br.readLine()) != null){
                hdd.write(line.toCharArray(), sector);
                sector++;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
