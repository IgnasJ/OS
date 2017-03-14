package rm;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class HDD {

    private final int SECTORS = 100;
    private final int WORDS_PER_SECTOR = 16;
    private final String EMPTY_SECTOR = "                ";

    private RandomAccessFile file;

    public HDD() throws FileNotFoundException {
        file = new RandomAccessFile("HDD", "rw");
        try {
            for (int i = 0; i < 100; ++i) {
                file.seek(i * WORDS_PER_SECTOR * 2);
                file.writeChars(EMPTY_SECTOR);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(char[] data, int sector){
        if(sector < 0 || sector > SECTORS){
            throw new IllegalArgumentException("Incorrect sector");
        }
        try {
            file.seek(sector * WORDS_PER_SECTOR * 2);
            file.writeChars(new String(data));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public char[] read(int sector){
        if(sector < 0 || sector > SECTORS){
            throw new IllegalArgumentException("Incorrect sector");
        }
        try {
            file.seek(sector*WORDS_PER_SECTOR*2);
            char[] data = new char[WORDS_PER_SECTOR];
            for(int i = 0; i < WORDS_PER_SECTOR; ++i){
                data[i] = file.readChar();
            }
            return data;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isEmpty(int sector){
        if(sector < 0 || sector > SECTORS){
            throw new IllegalArgumentException("Incorrect sector");
        }
        return new String(read(sector)).equals(EMPTY_SECTOR);
    }
}
