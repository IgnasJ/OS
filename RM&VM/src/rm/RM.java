package rm;

import core.Kernel;
import vm.VM;

import java.io.FileNotFoundException;
import java.io.IOException;

public class RM {

    public static RM rm;
    public VM vm;

    private Kernel kernel;

    public static int R1;
    public static int R2;
    private static int PTR;

    public static short IC;

    //CH1 - flash atmintine, CH2 - spausdintuvas, CH3 - kietasis diskas
    private static byte CH1, CH2, CH3;
    public static byte PI;
    private static byte SI;
    private static byte TI;
    private static byte IOI;

    private static byte MODE;
    //First bit - ZF, 2nd - SF, 3rd - OF
    private static byte C;

    //realioj masinoj susimapinti

    public static Memory memory;
    public static SupervisorMemory sMemory;
    public static HDD hdd;
    public static Printer printer;
    public static FlashMemory flashMemory;

    static{
        try {
            memory = new Memory();
            sMemory = new SupervisorMemory();
            hdd = new HDD();
            printer = new Printer();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public RM() {
        System.out.println("RM init.");
    }

    public static RM getInstance(){
        if(rm == null){
            rm = new RM();
        }
        return rm;
    }


    public void loadOS() throws IOException {
        this.kernel = Kernel.getInstance();
        this.kernel.start();
        this.vm = new VM();

       /* try {
            HDD hdd = new HDD();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        RM.readFromUSB("test.txt");
        RM.PD(0, 0);
        RM.moveMemory(0, 0); */
    }

    public void run() throws IOException {
       /* Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter MODE:");
        System.out.println("0. Supervisor mode");
        System.out.println("1. User mode");

        int choosen = keyboard.nextInt();

        if (choosen == 0) {
            RM.setMODE((byte)0);

        }
        else if (choosen == 1) {
            RM.setMODE((byte) 1);
        }
        else {
            System.out.println("EXIT. BAD NUMBER");
            System.exit(-1);
        }
        vm.processCommands(); */
       this.kernel.run();
    }


    public static void setZF() {
        C |= (1 << 6);
    }

    public static void clearZF() {
        C &= ~(1 << 6);
    }

    public static int getZF() {
        return (C >> 6) & 1;
    }

    public static void setSF() {
        C |= (1 << 5);
    }

    public static void clearSF() {
        C &= ~(1 << 5);
    }

    public static int getSF() {
        return (C >> 5) & 1;
    }

    public static void setOF() {
        C |= (1 << 4);
    }

    public static void clearOF() {
        C &= ~(1 << 4);
    }

    public static int getOF() {
        return (C >> 4) & 1;
    }

    public static int getR1() {
        return R1;
    }

    public static void setR1(int r1) {
        R1 = r1;
    }

    public static int getR2() {
        return R2;
    }

    public static void setR2(int r2) {
        R2 = r2;
    }

    public static int getPTR() {
        return PTR;
    }

    public static void setPTR(int ptr) {
        PTR = ptr;
    }

    public static short getIC() {
        return IC;
    }

    public static void setIC(short counter) {
        IC = counter;
    }

    public static byte getCH1() {
        return CH1;
    }

    public static void setCH1(byte state) {
        channelHelper(state, "CH1:0. FlashMemory channel freed",
                "CH1:1. FlashMemory channel busy - transferring data");
        if (state == 0 || state == 1)
            CH1 = state;
    }

    public static byte getCH2() {
        return CH2;
    }

    public static void setCH2(byte state) {
        channelHelper(state, "CH2:0. Printer channel freed",
                "CH2:1. Printer channel busy - transferring data");
        if (state == 0 || state == 1)
            CH2 = state;
    }

    public static byte getCH3() {
        return CH3;
    }

    public static void setCH3(byte state) {
        channelHelper(state, "CH3:0. Supervisor-HDD channel freed",
                "CH3:1. Supervisor-HDD channel busy - transferring data");
        if (state == 0 || state == 1)
            CH3 = state;
    }

    private static void channelHelper(byte state, String freedInterrupt, String busyInterrupt) {
        switch (state) {
            case 0:
                System.out.println(freedInterrupt);
                break;
            case 1:
                System.out.println(busyInterrupt);
                break;
            default:
                break;
        }
    }


    public static byte getPI() {
        return PI;
    }

    public static void setPI(byte state) {
        switch (state) {
            case 1:
                System.out.println("PI:1. OUT OF MEMORY RANGE Interrupt");
                break;
            case 2:
                System.out.println("PI:2. UNKNOWN OPERATION CODE Interrupt");
                break;
            case 3:
                System.out.println("PI:3. DIVISION BY ZERO Interrupt");
                break;
            case 4:
                System.out.println("PI:4. ARITHMETIC OPERATION ERROR Interrupt");
                break;
            default:
                break;
        }
        if (state == 1 || state == 2 || state == 3 || state == 4)
            PI = state;
    }

    public static byte getSI() {
        return SI;
    }

    public static void setSI(byte state) {
        switch (state) {
            case 1:
                System.out.println("SI:1. READ Command Interupt");
                break;
            case 2:
                System.out.println("SI:2. WRITE Command Interupt");
                break;
            case 3:
                System.out.println("SI:3. HALT Command Interupt");
                break;
            default:
                break;
        }
        if (state == 1 || state == 2 || state == 3)
            SI = state;
    }

    public static byte getTI() {
        return TI;
    }

    public static void setTI(byte state) {
        TI = state;
    }

    public static byte getIOI() {
        return IOI;
    }

    public static void setIOI(byte state) {
        switch (state) {
            case 1:
                System.out.println("IOI:1. CH1 Input/Output interrupt");
                break;
            case 2:
                System.out.println("IOI:2. CH2 Input/Output interrupt");
                break;
            case 3:
                System.out.println("IOI:3. CH3 Input/Output interrupt");
                break;
            default:
                break;
        }
        if (state == 1 || state == 2 || state == 3)
            IOI = state;
    }

    public static byte getMODE() {
        return MODE;
    }

    public static void setMODE(byte mode) {
        switch (mode) {
            case 0:
                System.out.println("Entering user mode");
                break;
            case 1:
                System.out.println("Entering supervizor mode");
                break;
            default:
                break;
        }
        if (mode == 0 || mode == 1)
            MODE = mode;
    }

    public static Memory getMemory() {
        return memory;
    }

    //reads from flash memory to HDD
    public static void readFromUSB(String filename) {
        setCH1((byte) 1);
        FlashMemory.readToHDD(filename);
        setCH1((byte) 0);
    }

    public static void writeToPrinter(Object o) {
        setCH2((byte) 1);
        Printer.print(o);
        setCH2((byte) 0);
    }

    //HALT - programos sustojimo taško komanda, t.y. programos valdymo pabaiga.
    public static void HALT() throws Exception {
        setSI((byte) 3);
        //throw new Exception("PROGRAMOS PABAIGA");
        setSI((byte) 0);
    }

    //PDx - SI tampa 1 ir valdymas perduodamas OS, duomenų kopijavimui iš kietojo disko į supervizorinės atminties vietą x.
    public static void PD(int offX1, int offX2) {
        setSI((byte) 1);
        setCH3((byte) 1);

        for (int i = 0; i < HDD.usedSectors.size(); ++i) {
            String hddSector = new String(HDD.read(HDD.usedSectors.get(i)));
            String[] blockString = hddSector.split("(?<=\\G....)");
            for (String s : blockString) {
                if (!s.equals("    ")) {
                    sMemory.writeBlock(s.toCharArray(), offX1, offX2);
                    offX2 += 1;
                    if (offX2 % 16 == 0) {
                        offX2 = 0;
                    }
                    offX1 += 1;
                }
            }
        }
        setCH3((byte) 0);
        setSI((byte) 0);
        System.out.println("Loaded from HDD su supervisor memory");
    }

    //Perkelia duomenis is supervizorines atminties i pagrindine
    public static int moveMemory(int x1, int x2) {
        System.out.println("move memory from supervisor to main memory");
        boolean dataSeg = false;
        boolean codeSeg = false;

        int programCount = 0;

        int codeOffset = 64;
        int currCodePos = 0;

        int commandOffset = 0;
        int currCommandPos = 0;

        int offX1 = x1;
        int offX2 = x2;

        for (int i = 0; i < sMemory.offset; i++) {
            String word = sMemory.getWord(offX1, offX2).toString();

            if (word.equals("DATA") && !dataSeg) {
                dataSeg = true;
            }
            if (word.equals("CODE") && !codeSeg) {
                codeSeg = true;
                dataSeg = false;
            }
            if (!codeSeg && !word.equals("DATA") && dataSeg) {
                memory.writeBlock(word.toCharArray(), codeOffset, currCodePos);
                memory.usedDATABlocks++;
                currCodePos += 1;
                codeOffset += 1;
                if (currCodePos == 16) {
                    currCodePos = 0;
                }
            }
            if (codeSeg && !word.equals("CODE")) {

                if (word.equals("HALT")) {
                    programCount++;
                    codeSeg = false;
                    dataSeg = false;
                }

                memory.writeBlock(word.toCharArray(), commandOffset, currCommandPos);
                memory.usedCODEBlocks++;
                currCommandPos += 1;
                commandOffset += 1;
                if (currCommandPos == 16) {
                    currCommandPos = 0;
                }
            }

            offX2 += 1;
            if (offX2 % 16 == 0) {
                offX2 = 0;
            }
            offX1 += 1;
        }
        return programCount;
    }

    //GDx - SI tampa 2 ir valdymas perduodamas OS, duomenų kopijavimui į kietąjį diską iš supervizorinės atminties vietos x.
    public static void GD(String address) {
        setSI((byte) 2);
        setCH1((byte) 1);
        //paimt is atminties address
        //ir irasyt i hdd
        setCH1((byte) 0);
        setSI((byte) 0);
    }

    //    @Override
    public static String getInfo() {
        return "+------------------+" + '\n' +
                "|       RM         |" + '\n' +
                "+------------------+" + '\n' +

                "R1: " + R1 + " R2: " + R2 + '\n' +
                "CH1: " + getCH1() + " CH2: " + getCH2() + " CH3: " + getCH3() + '\n' +
                "PI: " + getPI() + " SI: " + getSI() + " TI: " + getTI() + '\n' +
                "IOI: " + getIOI() + " IC: " + getIC() + " MODE: " + getMODE() + '\n' +
                "ZF: " + getZF() + " SF: " + getSF() + " OF: " + getOF() + '\n' +
                "+------------------+";
    }
}
