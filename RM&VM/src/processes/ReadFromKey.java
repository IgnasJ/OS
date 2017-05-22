package processes;

import core.Process;
import resources.ProgramInSupervMemoryResource;
import rm.RM;

/**
 * @author Lukas
 */
public class ReadFromKey extends Process{

    public ReadFromKey(){
        this.pID = "ReadFromKey";
    }

    @Override
    public void execute() {
        switch(IC){
            case 0:
                //blokavimasis laukiant resurso "vartotojo sasaja"
                kernel.requestResource(this, "InputStreamResource");
                break;
            case 1:
                //programos nuskaitymas
                //Resource toMemResource = this.ownedResources.get(0);
                RM.readFromUSB("test.txt");
                //blokavimasis laukiant resurso supervizorine atminties dalis is 100 zodziu?
                kernel.requestResource(this, "InputToSupervMemory");
                break;
            case 2:
                //eiluciu kopijavimas i supervizorine atminti
                RM.PD(0, 0);
                //resurso superv atminties dalis is 100 zodziu atlaisvinimas
                kernel.freeResource(this, this.ownedResources.get(0));
                //sukuriamas ir atlaisvinamas resursas uzduotis supervizorineje atmintyje skirtas mainproc
                ProgramInSupervMemoryResource r = new ProgramInSupervMemoryResource();
                kernel.createResource(this, r);
                kernel.freeResource(this, r);
                break;
        }
    }
}
