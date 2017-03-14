package rm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FlashMemory {

    public static void readToHDD(String sourceFile) {
        try(FileReader fr = new FileReader(sourceFile);
            BufferedReader br = new BufferedReader(fr)){

            String line = "";
            int c;
            int sector = 0;

            while((c = br.read()) != -1){
                if(line.length() == 16){
                    HDD.write(line.toCharArray(), sector);
                    sector++;
                    line = "";
                }
                //ignoruojam siuksles
                if(c != 10 && c != 13){
                    line+= (char)c;
                }
            }
            HDD.write(line.toCharArray(), sector);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
