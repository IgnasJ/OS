import gui.GUI;
import rm.FlashMemory;
import rm.RM;
import vm.VM;

public class Main {

    public static void main(String[] args) {
        FlashMemory fm = new FlashMemory();
        fm.readToHDD("test.txt");
        RM rm = new RM();
        VM vm = new VM();
        GUI gui = new GUI(/*cia gal paduota kazkuria masina?*/);
    }
}
