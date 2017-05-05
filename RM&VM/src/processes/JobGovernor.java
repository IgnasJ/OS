package processes;

import core.Process;

/**
 * @author Lukas
 */
public class JobGovernor extends Process{

    /*
    virtualios mašinos proceso tėvas, valdantis virtualios mašinos proceso darbą;
     */

    public JobGovernor(){
        this.pID = "Job Governor";
    }

    @Override
    public void execute() {

    }
}
