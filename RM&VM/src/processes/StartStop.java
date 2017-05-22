package processes;

import core.Logger;
import core.Process;

/**
 * @author Lukas
 */
public class StartStop extends Process {
    /*
    šakninis procesas, sukuriantis bei naikinantis sisteminius procesus ir resursus;
     */

    protected int priority = 100;

    public StartStop() {
        this.pID = "StartStop";
    }

    @Override
    public void execute() {
        Logger.log("Executing process: " + this);

        switch (this.IC) {
            case 0:
                //inicializuoti sisteminius procesus
                kernel.createProcess(this, new MainProc());
                kernel.createProcess(this, new GetLine());
                //kernel.createProcess(this, new JobGovernor());
                kernel.createProcess(this, new MemoryGovernor());
                kernel.createProcess(this, new Interrupt());
                kernel.createProcess(this, new PrintLine());
                kernel.createProcess(this, new ReadFromKey());
                //inicializuoti sisteminius resursus

                //prideti semaforus

                //laukti sistemos pabaigos resurso
                kernel.requestResource(this, "MOSFinal");
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
