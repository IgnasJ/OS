package processes;

import core.Process;

/**
 * @author Lukas
 */
public class MemoryGovernor extends Process{

    protected int priority = 96;

    public MemoryGovernor(){
        this.pID = "Memory Governor";
        this.priority = 96;
    }

    @Override
    public void execute() {

    }
}
