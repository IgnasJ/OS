package rm;

public class RM {

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

    private static Memory memory = new Memory();
    private static SupervisorMemory sMemory = new SupervisorMemory();

    public RM() {
        readFromUSB();
        readFromHDD();
    }

    /*
        getCommand(int IC){
            memory[nuo_to_bloko_kuris skirtas programos kodui]
        }
    */
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
        CH1 = state;
    }

    public static byte getCH2() {
        return CH2;
    }

    public static void setCH2(byte state) {
        CH2 = state;
    }

    public static byte getCH3() {
        return CH3;
    }

    public static void setCH3(byte state) {
        CH3 = state;
    }

    public static byte getPI() {
        return PI;
    }

    public static void setPI(byte state) {
        PI = state;
    }

    public static byte getSI() {
        return SI;
    }

    public static void setSI(byte state) {
        switch (state) {
            case 1:
                System.out.println("SI:1. READ Interupt");
                break;
            case 2:
                System.out.println("SI:2. WRITE Interupt");
                break;
            case 3:
                System.out.println("SI:3. HALT Interupt");
                break;
            default:
                break;
        }
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
        IOI = state;
    }

    public static byte getMODE() {
        return MODE;
    }

    public static void setMODE(byte mode) {
        MODE = mode;
    }

    public static Memory getMemory() {
        return memory;
    }


    public static void writeToHDD(int address) {
        if (getSI() == 2) {
            CH3 = 1;
            //paimt is atminties address
            //ir irasyt i hdd
            CH3 = 0;
        }
    }

    public static void readFromHDD() {
        if (getSI() == 1) {
            CH3 = 1;
            //
            CH3 = 0;
        }
    }

    //reads from flash memory to HDD
    public static void readFromUSB() {
        CH1 = 1;
        FlashMemory.readToHDD("test.txt");
        CH1 = 0;
    }

    public static void writeToPrinter(Object o) {
        CH2 = 1;
        Printer.print(o);
        CH2 = 0;
    }

}
