package core;

import java.util.*;

/**
 * @author Lukas
 */
public abstract class Resource {

    protected static int IDs = 0;

    //Vidinis vardas
    protected int rID;
    //Isorinis vardas
    protected String rIDI;
    //Procesas, kuris sukure sita resursa
    protected Process creator;
    //Resurso elementu sarasas
    protected List<Resource> resourceElements = new ArrayList<>();
    //Resurso laukianciu procesu sarasas
    protected List<Process> waitingProcesses = new ArrayList<>();
    //Nuoroda i sio resurso laukianciu procesu paprasytu resurso kiekiu sarasas (tipo kiek sio resurso nori??)
    protected Map<Resource, Integer> waitingProcCounts = new HashMap<>();
    //Nuoroda i visu resursu sarasa
    protected List<Resource> allResources;
    //Nuoroda į šio resurso laukiančių procesų resurso elemento rodyklių sąrašą (???????????????????)

    //Resurso pozymis
    protected String rTxt;


    public Resource() {
        this.rID = IDs++;
    }

    /*
    Kurti resursą
        Resursus kuria tik procesas. Resurso kūrimo metu perduodami kaip parametrai: nuoroda į
        proceso kūrėją, resurso išorinis vardas. Resursas kūrimo metu yra: pridedamas prie bendro
        resursų sąrašo, pridedamas prie tėvo suskurtų resursų sąrašo, jam priskiriamas unikalus vidinis
        vardas, sukuriamas resurso elementų sąrašas ir sukuriamas laukiančių procesų sąrašas.
     */
    public Resource(Process creator, String externalName, List<Resource> allResources) {
        this();
        this.rIDI = externalName;
        this.creator = creator;

        this.allResources = allResources;
        this.allResources.add(this);

        this.creator.addCreatedResources(this);
    }

    /*
    Naikinti resursą
        Resurso deskriptorius išmetamas iš jo tėvo sukurtų resursų sąrašo, naikinamas jo elementų
        sąrašas, atblokuojami procesai, laukiantys šio resurso, išmetamas iš bendro resursų sąrašo, ir,
        galiausiai naikinamas pats deskriptorius.
     */
    public void destroyResource(){
        this.creator.removeCreatedResources(this);
        //naikinamas elementu sarasas
        //atblokuojami procesai, laukiantis sio resurso
        this.allResources.remove(this);
    }

    /*
    Prašyti resurso
        Šį primityvą kartu su primityvu “atlaisvinti resursą” procesai naudoja labai dažnai. Procesas,
        iškvietęs šį primityvą, yra užblokuojamas ir įtraukiamas į to resurso laukiančių procesų sąrašą.
        Sekantis šio primityvo žingsnis yra kviesti resurso paskirstytoją.
     */
    public void requestResource(){

    }

    /*
    Atlaisvinti resursą
        Šį primityvą kviečia procesas, kuris nori atlaisvinti jam nereikalingą resursą arba tiesiog perduoti
        pranešimą ar informaciją kitam procesui. Resurso elementas, primityvui perduotas kaip
        funkcijos parametras, yra pridedamas prie resurso elementų sąrašo. Šio primityvo pabaigoje
        yra kviečiamas resursų paskirstytojas
     */
    public void freeResource(Resource element){
        this.resourceElements.add(element);
        //????
    }

    public String getrIDI() {
        return rIDI;
    }

    public void setrIDI(String rIDI) {
        this.rIDI = rIDI;
    }

    public Process getCreator() {
        return creator;
    }

    public void setCreator(Process creator) {
        this.creator = creator;
    }

    public List<Resource> getResourceElements() {
        return resourceElements;
    }

    public List<Process> getWaitingProcesses() {
        return waitingProcesses;
    }

    public Map<Resource, Integer> getWaitingProcCounts() {
        return waitingProcCounts;
    }

    public List<Resource> getAllResources() {
        return allResources;
    }

    public String getrTxt() {
        return rTxt;
    }

    public void setrTxt(String rTxt) {
        this.rTxt = rTxt;
    }
}
