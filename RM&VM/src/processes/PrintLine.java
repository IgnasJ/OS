package processes;

import core.Process;

/**
 * @author Lukas
 */
public class PrintLine extends Process{

    protected int priority = 93;

    public PrintLine(){
        this.pID = "PrintLine";
        this.priority = 93;
    }

    @Override
    public void execute() {

    }
}
