package core;

import processes.StartStop;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author Lukas
 */
public class Kernel {

    private List<Resource> resources = new ArrayList<>();
    private List<Process> allProcesses = new ArrayList<>();

    private PriorityQueue<Process> readyProcesses = new PriorityQueue<>();
    private PriorityQueue<Process> blockedProcesses = new PriorityQueue<>();

    private Process currentProcess;

    private static Kernel kernel;

    public void start(){
        System.out.println("Starting kernel");
        Process startStop = new StartStop();
        this.createProcess(null, startStop);
        planner();
    }

    public void run(){
        Process p = this.getCurrentProcess();
        if(p == null){
            System.out.println("No processes are running");
        }
        else{
            p.run();
        }
    }

    public void createProcess(Process parent, Process createdProc){
        System.out.println("Creating process: " + createdProc.pID + " ...");
        this.allProcesses.add(createdProc);
        this.readyProcesses.add(createdProc);
        createdProc.setState(ProcState.READY);
        if(parent != null){
            parent.addChild(createdProc);
            createdProc.setParent(parent);
        }
        System.out.println("Finished creating process: " + createdProc.pID);
    }

    public void destroyProcess(Process p){
        System.out.println("Destroying process: " + p.pID + " ...");
        if(p.getParent() != null){
            p.getParent().removeChild(p);
        }

        p.destroyChildren();
        p.releaseResources();
        p.destroyResources();

        allProcesses.remove(p);
        readyProcesses.remove(p);
        blockedProcesses.remove(p);

        System.out.println("Finished destroying process: " + p.pID);

    }

    public void activateProcess(Process p){

    }

    public void stopProcess(Process p){

    }

    public void runProcess(Process p){

    }

    public void createResource(Process owner, Resource resource){

    }

    public void deleteResource(Process process, Resource resource){
        System.out.println("Deleting resource: " + resource.getrIDI() + " from process: " + process.getExternalName() + " ...");
        this.resources.remove(resource);
        process.removeCreatedResource(resource);
    }

    public void freeResource(Process process, Resource resource){
        System.out.println("Freeing resource: " + resource.getrIDI() + " ...");
        Resource r = this.getResource(resource.getrIDI());
        if(r != null){
            process.releaseResource(resource);
            //???
            planner();
        }
        System.out.println("Finished freeing resource: " + resource.getrIDI());
    }

    public void requestResource(Process askingProc, String resExtName){

    }

    public void requestResources(Process askignProc, String resExtName, int amount){

    }

    public void giveResource(Process process, Resource resource){

    }

    public Resource getResource(String resExtName){
        return null;
    }

    public void planner(){
        Process firstReady = this.readyProcesses.peek();
        Process current = this.getCurrentProcess();

        if(current != null && current.getState() != ProcState.RUNNING){
            this.blockedProcesses.add(current);
            this.currentProcess = null;
            current = null;
        }
        if(current == null){
            if(firstReady == null){
                System.out.println("All processes are blocked");
            }
            else{
                System.out.println("Planner changing process");
                firstReady = this.readyProcesses.poll();
                firstReady.setState(ProcState.RUNNING);
                this.runProcess(firstReady);
            }
        }
        else{
            if(firstReady == null){
                System.out.println("No other ready processes, starting current one");
            }
            else if(firstReady.priority > current.priority){
                System.out.println("Current process has lower priority");
                firstReady = this.readyProcesses.poll();
                firstReady.setState(ProcState.RUNNING);
                this.runProcess(firstReady);
                current.setState(ProcState.READY);
                this.readyProcesses.add(current);
            }
        }
    }

    public static Kernel getInstance(){
        if(kernel == null){
            kernel = new Kernel();
        }
        return kernel;
    }



    public List<Resource> getResources(){
        return this.resources;
    }

    public List<Process> getProcesses(){
        return this.allProcesses;
    }

    public PriorityQueue<Process> getReadyProcesses() {
        return readyProcesses;
    }

    public PriorityQueue<Process> getBlockedProcesses() {
        return blockedProcesses;
    }

    public Process getCurrentProcess() {
        return currentProcess;
    }
}
