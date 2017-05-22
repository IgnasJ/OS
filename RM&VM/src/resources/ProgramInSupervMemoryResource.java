package resources;

import core.Resource;

/**
 * @author Lukas
 */
public class ProgramInSupervMemoryResource extends Resource {

    private int status;

    public ProgramInSupervMemoryResource(){
        this.rIDI = "ProgramInSupervMemoryResource";
    }

    public void setStatus(int status){
        this.status = status;
    }

    public int getStatus(){
        return this.status;
    }
}
