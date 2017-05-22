package processes;

import core.Process;
import resources.InterruptResource;
import rm.RM;

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
        switch(this.IC){
            case 0:
                //blokuojasi laukdamas vartotojo atminties resurso
                kernel.requestResources(this, "MainMemoryResource", 16);
                break;
            case 1:
                //blokuokasi laukdamas uzduoties Vm atmintyje resurso
                kernel.requestResource(this, "ProgramInSupervMemoryResource");
                //programa irasoma i vartotojo atminti
                RM.moveMemory(0, 0);
                //sukuriamas VM procesas
                kernel.createProcess(this, new VirtualMachine());
                //blokuojasi laukdamas interrupt resource
                kernel.requestResource(this, "InterruptResource");
                break;
            case 2:
                InterruptResource interrupt = (InterruptResource)this.ownedResources.get(0);
                if(interrupt.getType().equals("IOI")){

                }
                break;
            case 3:

        }
    }
}
