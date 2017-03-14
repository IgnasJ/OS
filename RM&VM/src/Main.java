import gui.GUI;
import rm.FlashMemory;
import rm.HDD;
import rm.RM;
import vm.VM;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        RM rm = new RM();
        VM vm = new VM(rm.getMemory());
        HDD hdd = new HDD();

        FlashMemory fm = new FlashMemory(hdd);
        fm.readToHDD("test.txt");

        try {
            vm.resolveCommand("ADD");
        } catch (Exception e) {
            e.printStackTrace();
        }
        GUI gui = new GUI(/*cia gal paduota kazkuria masina?*/);

    }
}
