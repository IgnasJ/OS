import gui.GUI;
import rm.FlashMemory;
import rm.RM;
import vm.VM;

public class Main {

    public static void main(String[] args) {

        FlashMemory fm = new FlashMemory();

        //fm.readToHDD("test.txt");

        RM rm = new RM();
        VM vm = new VM();
        try {
            vm.resolveCommand("ADD");
        } catch (Exception e) {
            e.printStackTrace();
        }
        GUI gui = new GUI(/*cia gal paduota kazkuria masina?*/);

    }
}
