package rm;

public class Memory {
    private final int BLOCK_COUNT = 16;
    protected final int BLOCK_SIZE = 16;

    /* --------------------------------------
        0 1  2  3  4  5  6 7 8 9 A B C D E F|
        -----------Programos komandos--------
       0            0-16                    |
       1            16-32                   |
       2            32-48                   |
       3            48-64                   |
       -----------Programos duomenys---------
       4            64-80                   |
       5            80-96                   |
       6            96-112                  |
       7            112-128                 |
       8            128-144                 |
       9            144-160                 |
       A            160-176                 |
       ----------Puslapiavimo lentele?------|
       B            176-192                 |
       C            192-208                 |
       D            208-224                 |
       E            224-240                 |
       F            240-256                 |
       --------------------------------------

     */

    protected char[][] memory;

    public Memory(){
        memory = new char[BLOCK_COUNT][BLOCK_SIZE];
    }

    public char[] getBlock(int block){
        if(block < 0 || block > (BLOCK_SIZE)){
            throw new IllegalArgumentException("Incorrect block specified!");
        }
        else{
            return memory[block];
        }
    }

    public void writeBlock(char[] data, int block){
        if(block < 0 || block > (BLOCK_SIZE)){
            throw new IllegalArgumentException("Incorrect block specified!");
        }
        else{
            memory[block] = data;
        }
    }

    public void display(){
        for(int x = 0; x < BLOCK_COUNT; ++x){
            for(int y = 0; y < BLOCK_SIZE; ++y){
                System.out.print(memory[x][y] + "|");
            }
            System.out.println();
        }
    }

}
