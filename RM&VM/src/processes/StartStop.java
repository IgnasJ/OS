package processes;

import core.Logger;
import core.Process;
import resources.*;

/**
 * @author Lukas
 */
public class StartStop extends Process {
    /*
    šakninis procesas, sukuriantis bei naikinantis sisteminius procesus ir resursus;
     */

    public StartStop() {
        this.pID = "StartStop";
        this.priority = 100;
    }

    @Override
    public void execute() {
        Logger.log("Executing process: " + this);

        switch (this.IC) {
            case 0:
                //inicializuoti sisteminius procesus
                kernel.createProcess(this, new MainProc());
                kernel.createProcess(this, new GetLine());
                kernel.createProcess(this, new MemoryGovernor());
                kernel.createProcess(this, new Interrupt());
                kernel.createProcess(this, new PrintLine());
                kernel.createProcess(this, new ReadFromKey());
                //inicializuoti sisteminius resursus
                kernel.createResource(this, new InputStreamResource());
                kernel.createResource(this, new InputToSupervMemory());
                kernel.createResource(this, new InterruptResource());
                kernel.createResource(this, new MainMemoryResource());
                kernel.createResource(this, new ProgramInSupervMemoryResource());
                kernel.createResource(this, new TaskInExternalMemoryResource());
                kernel.createResource(this, new WaitForInputResource());
                kernel.createResource(this, new WaitForOutputResource());
                
                //prideti semaforus

                //laukti sistemos pabaigos resurso. Prideti kai visos programos ivykdytos

                //kernel.requestResource(this, "MOSFinal");
                break;
            case 1:
                //sunaikinti sisteminius procesus?
                for (Process p : kernel.getProcesses()) {
                    p.destroyResources();
                }
                kernel.destroyProcess(this);
                kernel.shutDown();
                break;
        }
    }

}
