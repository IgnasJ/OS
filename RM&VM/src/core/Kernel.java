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

    public static boolean shutdown = false;

    public void start(){
        Logger.log("Starting kernel");
        Process startStop = new StartStop();
        this.createProcess(null, startStop);
        planner();
    }

    public void run(){
        Process p = this.getCurrentProcess();
        if(p == null){
            Logger.log("No processes are running");
        }
        else{
            p.run();
        }
    }

    public void createProcess(Process parent, Process createdProc){
        Logger.log("Creating process: " + createdProc + " ...");
        this.allProcesses.add(createdProc);
        this.readyProcesses.add(createdProc);
        createdProc.setState(ProcState.READY);
        if(parent != null){
            parent.addChild(createdProc);
            createdProc.setParent(parent);
        }
        Logger.log("Finished creating process: " + createdProc);
    }

    public void destroyProcess(Process p){
        Logger.log("Destroying process: " + p + " ...");
        if(p.getParent() != null){
            p.getParent().removeChild(p);
        }

        p.destroyChildren();
        p.releaseResources();
        p.destroyResources();

        allProcesses.remove(p);
        readyProcesses.remove(p);
        blockedProcesses.remove(p);

        Logger.log("Finished destroying process: " + p);

    }

    public void activateProcess(Process p){
        Logger.log("Activating process: " + p);
        switch(p.getState()){
            case BLOCKEDWAITING:
                p.setState(ProcState.BLOCKED);
                break;
            case READYSWAITING:
                p.setState(ProcState.READY);
                break;
            default:
                Logger.log("Error activating process: " + p);
                break;
        }
        planner();
    }

    public void stopProcess(Process p){
        Logger.log("Stopping process: " + p);
        switch(p.getState()){
            case BLOCKED:
                p.setState(ProcState.BLOCKEDWAITING);
                break;
            case READY:
                p.setState(ProcState.READYSWAITING);
                break;
            default:
                Logger.log("Error stopping process: " + p);
                break;
        }
        planner();
    }

    public void runProcess(Process p){
        Logger.log("Running process: " + p.pID);
        p.setState(ProcState.RUNNING);
        this.readyProcesses.remove(p);
        this.currentProcess = p;
    }

    public void createResource(Process owner, Resource resource){
        Logger.log("Creating resource: " + resource);
        resource.setCreator(owner);
        owner.addCreatedResources(resource);
        this.resources.add(resource);
    }

    public void deleteResource(Process process, Resource resource){
        Logger.log("Deleting resource: " + resource + " from process: " + process + " ...");
        this.resources.remove(resource);
        process.removeCreatedResource(resource);
    }

    public void freeResource(Process process, Resource resource){
        Logger.log("Freeing resource: " + resource + " ...");
        Resource r = this.getResource(resource.getrIDI());
        if(r != null){
            process.releaseResource(resource);
            //???
            planner();
        }
        Logger.log("Finished freeing resource: " + resource);
    }

    public void requestResource(Process askingProc, String resExtName){
        this.requestResources(askingProc, resExtName, 1);
    }

    public void requestResources(Process askingProc, String resExtName, int amount){
        Logger.log("Process: " + askingProc + " requested " + amount + " of resource: " + resExtName);
        Resource r = this.getResource(resExtName);
        askingProc.setState(ProcState.BLOCKED);
        //askingProc.setwaitingforresource
        //r.addwaitingproc
        //r.distribute
        planner();
    }

    public void giveResource(Process process, String resExtName){

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
                Logger.log("All processes are blocked");
            }
            else{
                Logger.log("Planner changing process");
                firstReady = this.readyProcesses.poll();
                firstReady.setState(ProcState.RUNNING);
                this.runProcess(firstReady);
            }
        }
        else{
            if(firstReady == null){
                Logger.log("No other ready processes, starting current one");
            }
            else if(firstReady.priority > current.priority){
                Logger.log("Current process has lower priority");
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

    public void shutDown(){
        shutdown = true;
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
