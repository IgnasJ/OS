package core;

import rm.RM;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Lukas
 */
public abstract class Process {

    protected static int IDs = 0;

    //Proceso vidinis vardas
    protected int pD;
    //Proceso isorinis vardas
    protected String pID;
    //Proceso busena
    protected ProcState state;
    //Proceso vaikai
    protected List<Process> children = new ArrayList<>();
    //Proceso tevas
    protected Process parent;
    //Proceso prioritetas 0 - 100//Proceso tevas
    protected int priority = 0;
    //Proceso sukurti resursai
    protected List<Resource> ownedResources = new ArrayList<>();
    //Proceso kurimo metu perduoti resursai
    protected List<Resource> createdResources = new ArrayList<>();
    //Procesu sarasas, kuriam priklauso procesas
    protected List<Process> processList;

    protected int IC = 0;

    protected static Kernel kernel = Kernel.getInstance();
    protected static RM rm = RM.getInstance();


    public Process() {
        this.pD = IDs++;
        this.IC++;
        state = ProcState.READY;
    }

    public Process(List<Process> processList) {
        this();
        this.processList = processList;
    }

    public void run() {
        System.out.println("Running process: " + this.pID);
        execute();
    }

    public abstract void execute();

    public void addCreatedResources(Resource... resources) {
        Collections.addAll(this.createdResources, resources);
    }

    public void removeCreatedResource(Resource resource){
        this.createdResources.remove(resource);
    }

    public void addOwnedResource(Resource resource) {
        this.ownedResources.add(resource);
    }


    public void destroyChildren(){
        for(Process child : this.children){
            kernel.destroyProcess(child);
        }
    }

    public void destroyResources() {
        for(Resource resource : this.ownedResources){
            kernel.deleteResource(this, resource);
        }
    }

    public void releaseResource(Resource resource){
        this.ownedResources.remove(resource);
    }


    public void releaseResources() {
        for(Resource resource : this.ownedResources){
            kernel.freeResource(this, resource);
        }
    }

    public void addChild(Process child) {
        this.children.add(child);
    }

    public void removeChild(Process p){
        this.children.remove(p);
    }

    public List<Process> getChildren(){
        return this.children;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return this.priority;
    }

    public void setParent(Process parent) {
        this.parent = parent;
    }

    public Process getParent() {
        return this.parent;
    }


    public String getExternalName() {
        return this.pID;
    }

    public int getID() {
        return this.pD;
    }

    public int getIC() {
        return IC;
    }

    public void setState(ProcState state) {
        this.state = state;
    }

    public ProcState getState() {
        return state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Process process = (Process) o;

        //Nebus dvieju procesu su vienodais id cj?
        return this.pD == process.pD;
    }

    @Override
    public int hashCode() {
        //Nebus dvieju procesu su vienodais id cj?
        return this.pD;
    }
}
