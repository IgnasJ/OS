package rm;

public class RM {

    private int R1;
    private int R2;
    private int PTR;

    private short IC;

    private byte CH1, CH2, CH3;
    private byte PI;
    private byte SI;
    private byte TI;
    private byte IOI;

    private byte MODE;
    //First bit - ZF, 2nd - SF, 3rd - OF
    private byte C;

    private Memory memory;

    public RM() {
    }


    public void setZF() {
        C |= (1 << 6);
    }

    public void clearZF() {
        C &= ~(1 << 6);
    }

    public int getZF() {
        return (C >> 6) & 1;
    }

    public void setSF() {
        C |= (1 << 5);
    }

    public void clearSF() {
        C &= ~(1 << 5);
    }

    public int getSF() {
        return (C >> 5) & 1;
    }

    public void setOF() {
        C |= (1 << 4);
    }

    public void clearOF() {
        C &= ~(1 << 4);
    }

    public int getOF() {
        return (C >> 4) & 1;
    }

    public int getR1() {
        return R1;
    }

    public void setR1(int r1) {
        R1 = r1;
    }

    public int getR2() {
        return R2;
    }

    public void setR2(int r2) {
        R2 = r2;
    }

    public int getPTR() {
        return PTR;
    }

    public void setPTR(int PTR) {
        this.PTR = PTR;
    }

    public short getIC() {
        return IC;
    }

    public void setIC(short IC) {
        this.IC = IC;
    }

    public byte getCH1() {
        return CH1;
    }

    public void setCH1(byte CH1) {
        this.CH1 = CH1;
    }

    public byte getCH2() {
        return CH2;
    }

    public void setCH2(byte CH2) {
        this.CH2 = CH2;
    }

    public byte getCH3() {
        return CH3;
    }

    public void setCH3(byte CH3) {
        this.CH3 = CH3;
    }

    public byte getPI() {
        return PI;
    }

    public void setPI(byte PI) {
        this.PI = PI;
    }

    public byte getSI() {
        return SI;
    }

    public void setSI(byte SI) {
        this.SI = SI;
    }

    public byte getTI() {
        return TI;
    }

    public void setTI(byte TI) {
        this.TI = TI;
    }

    public byte getIOI() {
        return IOI;
    }

    public void setIOI(byte IOI) {
        this.IOI = IOI;
    }

    public byte getMODE() {
        return MODE;
    }

    public void setMODE(byte MODE) {
        this.MODE = MODE;
    }

    public Memory getMemory() {
        return memory;
    }

    public void setMemory(Memory memory) {
        this.memory = memory;
    }
}
