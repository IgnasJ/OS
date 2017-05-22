package processes;

import core.Logger;
import core.Process;
import core.Resource;
import resources.InterruptResource;
import rm.RM;

/**
 * @author Lukas
 */
public class Interrupt extends Process {

    public Interrupt() {
        this.pID = "Interrupt";
        this. priority = 99;
    }

    @Override
    public void execute() {
        Logger.log("Executing process: " + this);
        switch(this.IC){
            case 0:
                //laukia pertraukimas resurso, kuri siuncia virtualmachine
                kernel.requestResource(this, "InterruptResource");
                break;
            case 1:
                //nustatomas pertraukimo tipas
                Resource res = this.ownedResources.get(0);
                String intType = "";
                int value = -1;
                if(RM.getPI() != 0){
                    intType = "PI";
                    value = RM.getPI();
                }
                else if(RM.getSI() != 0){
                    intType = "SI";
                    value = RM.getSI();
                }
                else if (RM.getTI() <= 0){
                    intType = "TI";
                    value = RM.getTI();
                }
                else if (RM.getIOI() != 0){
                    intType = "IOI";
                    value = RM.getIOI();
                }
                else{
                    Logger.log("Could not determine interrupt type");
                }
                kernel.freeResource(this, new InterruptResource(intType, value));
                kernel.freeResource(this, res);
                this.resetIC();
                break;
        }
    }
}
