package processes;

import core.Process;

/**
 * @author Lukas
 */
public class MemoryGovernor extends Process{

    public MemoryGovernor(){
        this.pID = "Memory Governor";
        this.priority = 96;
    }

    @Override
    public void execute() {

    }
}
