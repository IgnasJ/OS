import gui.GUI;
import rm.HDD;
import rm.RM;
import vm.VM;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        HDD hdd = new HDD();
        RM rm = new RM();
        VM vm = new VM();
        RM.readFromUSB();
        RM.PD("0");
        RM.moveMemory("0");
        vm.processCommands();

        GUI gui = new GUI(/*cia gal paduota kazkuria masina?*/);
    }
}