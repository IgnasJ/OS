package processes;

import core.Logger;
import core.Process;
import resources.InterruptResource;
import resources.TaskInExternalMemoryResource;
import resources.WaitForInputResource;
import resources.WaitForOutputResource;
import rm.RM;

/**
 * @author Lukas
 */
public class JobGovernor extends Process{

    /*
    virtualios mašinos proceso tėvas, valdantis virtualios mašinos proceso darbą;
     */

    protected int priority = 97;

    public JobGovernor(){
        this.pID = "Job Governor";
        this.priority = 97;
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
                if(interrupt.getType().equals("SI")){
                    //nuskaitymo pertraukimas
                    if(interrupt.getValue() == 1){
                        Logger.log("WaitForInputResource");
                        kernel.freeResource(this, new WaitForInputResource());

                    }
                    //isvedimo pertraukimas
                    else if(interrupt.getValue() == 2){
                        Logger.log("WaitForOutputResource");
                        kernel.freeResource(this, new WaitForOutputResource());
                    }
                    //HALT?
                    else if(interrupt.getValue() == 3){

                    }
                }
                else if(interrupt.getType().equals("TI")){
                    Logger.log("InterruptResource");
                    kernel.requestResource(this, "InterruptResource");
                    resetIC();
                }
                else if(interrupt.getType().equals("PI")){
                    Logger.log("PI thrown. Killing VM...");
                    this.destroyChildren();
                    kernel.freeResource(this, new TaskInExternalMemoryResource());

                }
                kernel.freeResource(this, interrupt);
                break;
            case 3:
                kernel.activateProcess(this.children.get(0));
                //Semaforai or someshit
                break;
        }
    }
}
