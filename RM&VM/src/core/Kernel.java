package core;

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

    }

    public void createProcess(Process parent, Process createdProc){

    }

    public void destroyProcess(Process p){

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

    }

    public void freeResource(Process process, Resource resource){

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
