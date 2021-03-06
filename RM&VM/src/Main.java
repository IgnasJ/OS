import rm.FlashMemory;
import rm.HDD;
import rm.RM;
import vm.VM;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        RM rm = RM.getInstance();
        rm.loadOS();
        rm.run();
    }

    public static void oldStartup() throws IOException {
        RM rm = new RM();
        VM vm = new VM();

        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter MODE:");
        System.out.println("0. User mode");
        System.out.println("1. Supervisor mode");

        int choosen = keyboard.nextInt();

        if (choosen == 0) {
            RM.setMODE((byte) 0);

            try {
                HDD hdd = new HDD();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            RM.readFromUSB("test.txt");
            System.out.println(FlashMemory.sector);
            System.in.read();
            RM.PD(0, 0);
            System.in.read();
            RM.moveMemory(0, 0);

        }
        else if (choosen == 1) {

            RM.setMODE((byte) 1);
            try {
                HDD hdd = new HDD();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            RM.readFromUSB("test.txt");
            RM.readFromUSB("test.txt");
            RM.PD(0, 0);
            RM.sMemory.display();
            RM.moveMemory(0, 0);
            RM.memory.display();
        }
        else {
            System.out.println("EXIT. BAD NUMBER");
            System.exit(-1);
        }
        vm.processCommands();

        //GUI gui = new GUI(/*cia gal paduota kazkuria masina?*/);
    }

}