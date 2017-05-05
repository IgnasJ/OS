package core;

import java.util.ArrayList;
import java.util.Arrays;
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
        execute();
    }

    public abstract void execute();

    public void addCreatedResources(Resource... resources) {
        Collections.addAll(this.createdResources, resources);
    }

    public void removeCreatedResources(Resource ... resources){
        this.createdResources.removeAll(Arrays.asList(resources));
    }

    public void addOwnedResources(Resource... resources) {
        Collections.addAll(this.ownedResources, resources);
    }

    public void releaseOwnedResources(Resource... resources) {
        this.ownedResources.removeAll(Arrays.asList(resources));
    }

    public void destroyChildren(){
        //kreiptis i kernel
    }

    public void destroyResourses() {
        //kreiptis i kernel
    }

    public void releaseAllResources() {
        //kreiptis i kernel
    }

    public void addChildren(Process... children) {
        Collections.addAll(this.children, children);
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
