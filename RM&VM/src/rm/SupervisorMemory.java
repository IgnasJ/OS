package rm;

/**
 * @author Lukas
 */
public class SupervisorMemory extends Memory{

    private final int BLOCK_COUNT = 1000;

    public SupervisorMemory(){
        this.memory = new char[BLOCK_COUNT][BLOCK_SIZE];
    }

}
