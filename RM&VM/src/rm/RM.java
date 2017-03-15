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

    public static Memory memory = new Memory();
    public static SupervisorMemory sMemory = new SupervisorMemory();

    public RM() {
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

    //reads from flash memory to HDD
    public static void readFromUSB(){
        CH1 = 1;
        FlashMemory.readToHDD("test.txt");
        CH1 = 0;
    }

    public static void writeToPrinter(Object o){
        CH2 = 1;
        Printer.print(o);
        CH2 = 0;
    }

    //HALT - programos sustojimo taško komanda, t.y. programos valdymo pabaiga.
    public static void HALT() throws Exception {
        RM.setSI((byte) 3);
        //throw new Exception("PROGRAMOS PABAIGA");
        RM.setSI((byte)0);
    }

    //PDx - SI tampa 1 ir valdymas perduodamas OS, duomenų kopijavimui iš kietojo disko į supervizorinės atminties vietą x.
    public static void PD(String address) {
        RM.setSI((byte) 1);
        CH3 = 1;

        int block = Integer.parseInt(address);
        for(int i = 0; i < HDD.usedSectors.size(); ++i){
            sMemory.writeBlock(HDD.read(HDD.usedSectors.get(i)), block);
            block++;
        }
        CH3 = 0;
        RM.setSI((byte)0);
    }

    //Perkelia duomenis is supervizorines atminties i pagrindine
    public static void moveMemory(String address){

        boolean dataSeg = false;
        boolean codeSeg = false;

        int codeOffset = 64;
        int currCodePos = 0;

        int commandOffset = 0;
        int currCommandPos = 0;

        for(Integer bID : sMemory.usedBlocks){
            char[] block = sMemory.getBlock(bID);
            //Splitting every 4 'bytes'
            String[] blockString = new String(block).split("(?<=\\G....)");
            for(String s : blockString){
                if(s.equals("DATA") && !dataSeg){
                    dataSeg = true;
                }
                if(s.equals("CODE")){
                    codeSeg = true;
                }
                if(!codeSeg && !s.equals("DATA")){
                    memory.writeBlockOffset(s.toCharArray(), codeOffset, currCodePos);
                    currCodePos+=4;
                    codeOffset+=4;
                    if(currCodePos == 16){
                        currCodePos = 0;
                    }
                }
                if(codeSeg && ! s.equals("CODE")){
                    memory.writeBlockOffset(s.toCharArray(), commandOffset, currCommandPos);
                    currCommandPos+=4;
                    commandOffset+=4;
                    if(currCommandPos == 16){
                        currCommandPos = 0;
                    }
                }
            }
        }
    }

    //GDx - SI tampa 2 ir valdymas perduodamas OS, duomenų kopijavimui į kietąjį diską iš supervizorinės atminties vietos x.
    public static void GD(String address) {
        RM.setSI((byte) 2);
        CH3 = 1;
        //paimt is atminties address
        //ir irasyt i hdd
        CH3 = 0;
        RM.setSI((byte)0);
    }

}
