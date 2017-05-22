package processes;

import core.Process;

/**
 * @author Lukas
 */
public class VirtualMachine extends Process{

    protected int priority = 50;

    public VirtualMachine(){
        this.pID = "VirtualMachine";
    }

    @Override
    public void execute() {

    }
}
