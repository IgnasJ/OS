package rm;

import core.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FlashMemory {

    public static int sector = 0;

    public static void readToHDD(String sourceFile) {
        try (FileReader fr = new FileReader(sourceFile);
             BufferedReader br = new BufferedReader(fr)) {

            StringBuilder line = new StringBuilder();
            int c;

            while ((c = br.read()) != -1) {
                if (line.length() == 16) {
                    HDD.write(line.toString().toCharArray(), sector);
                    sector++;
                    line = new StringBuilder();
                }
                //ignoruojam siuksles
                if (c != 10 && c != 13) {
                    line.append((char) c);
                }
            }
            if (!line.toString().equals("")) {
                HDD.write(line.toString().toCharArray(), sector);
                sector++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Logger.log("Read from FLASH to HDD.");
    }
}
