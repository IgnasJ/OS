package processes;

import core.Process;

/**
 * @author Lukas
 */
public class PrintLine extends Process{

    public PrintLine(){
        this.pID = "PrintLine";
        this.priority = 93;
    }

    @Override
    public void execute() {

    }
}
