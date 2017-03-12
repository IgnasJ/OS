package vm;

import rm.Memory;

public class VM {

    private int R1;
    private int R2;
    private byte C;
    private short IC;

    private Memory memory;

    public VM(Memory memory) {
        this.memory = memory;
    }

    public void resolveCommand(String line) throws Exception {

        if (line.equals("HALT")) {
            HALT();
        } else if (line.substring(0, 3).equals("ADD")) {
            ADD();
        } else if (line.substring(0, 3).equals("SUB")) {
            SUB();
        } else if (line.substring(0, 3).equals("MUL")) {
            MUL();
        } else if (line.substring(0, 3).equals("DIV")) {
            DIV();
        } else if (line.substring(0, 3).equals("CMP")) {
            CMP();
        } else if (line.substring(0, 2).equals("LW")) {
            LW(line.substring(3, 4));
        } else if (line.substring(0, 2).equals("LE")) {
            LE(line.substring(3, 4));
        } else if (line.substring(0, 2).equals("LS")) {
            LS(line.substring(3, 4));
        } else if (line.substring(0, 2).equals("LX")) {
            LX(line.substring(3, 4));
        } else if (line.substring(0, 2).equals("LY")) {
            LY(line.substring(3, 4));
        } else if (line.substring(0, 2).equals("LL")) {
            LL(line.substring(3, 4));
        } else if (line.substring(0, 2).equals("LR")) {
            LR(line.substring(3, 4));
        } else if (line.substring(0, 2).equals("LD")) {
            LD(line.substring(3, 4));
        } else if (line.substring(0, 2).equals("JM")) {
            JM(line.substring(3, 4));
        } else if (line.substring(0, 2).equals("JE")) {
            JE(line.substring(3, 4));
        } else if (line.substring(0, 2).equals("JG")) {
            JG(line.substring(3, 4));
        } else if (line.substring(0, 2).equals("JL")) {
            JL(line.substring(3, 4));
        } else if (line.substring(0, 2).equals("IC")) {
            IC(line.substring(3, 4));
        } else if (line.substring(0, 2).equals("PD")) {
            PD();
        } else if (line.substring(0, 2).equals("GD")) {
            GD();
        } else {
            throw new Exception("PAKEIST I TINKAMA. NEATPAZINTA KOMANDA");
        }
    }

    // Sudeda R1 ir R2, įrašoma į R1. Jeigu rezultatas netelpa, OF = 1. Jeigu reikšmės ženklo bitas yra 1, SF = 1.
    public void ADD() {
        if (R1 + R2 > Integer.MAX_VALUE) {
            setOF();
            return;
        } else {
            R1 += R2;
        }
        if (((R1 >> 6) & 1) == 1) {
            setSF();
        }
        ++IC;
    }

    // Iš R1 atimama R2, įrašoma į R1. Jeigu rezultatas netelpa, OF = 1. Jeigu reikšmės ženklo bitas yra 1, SF = 1.
    public void SUB() {
        if (R1 - R2 < Integer.MIN_VALUE) {
            setOF();
            return;
        } else {
            R1 -= R2;
        }
        if (((R1 >> 6) & 1) == 1) {
            setSF();
        }
        ++IC;
    }

    // Sudaugina R1 ir R2, įrašoma į R1.Jeigu rezultatas netelpa, OF = 1.Jeigu reikšmės ženklo bitas yra 1, SF = 1.
    public void MUL() {
        if (R1 * R2 > Integer.MAX_VALUE) {
            setOF();
            return;
        } else {
            R1 *= R2;
        }
        if (((R1 >> 6) & 1) == 1) {
            setSF();
        }
        ++IC;
    }

    // Padalina R1 iš R2, įrašoma į R1. Jeigu reikšmės ženklo bitas yra 1, SF = 1.
    public void DIV() {
        R1 /= R2;
        if (((R1 >> 6) & 1) == 1) {
            setSF();
        }
        ++IC;
    }

    //Ši komanda palygina registre R1 ir R2 ęsančias reikšmes. Jeigu reikšmės lygios, ZF = 1, priešingu atveju ZF = 0.
    public void CMP() {
        if (R1 == R2) {
            setZF();
        } else {
            clearZF();
        }
        ++IC;
    }

    //LWx1x2 - į registrą R1 užkrauna žodį nurodytu adresu 16 * x1 + x2.
    public void LW(String address) {

        //R2 = memory.get(Integer.parseInt(address, 16));
        ++IC;
    }

    //LEx1x2 - į registrą R2 užkrauna skaičių, adresu 16 * x1 + x2.
    public void LE(String address) {
        ++IC;
    }

    //LSx1x2 - į atmintį adresu 16 * x1 + x2 rašo žodį ar skaičių.
    public void LS(String address) {
        ++IC;
    }

    //LXx1x2 - į R1 užkrauna bendrosios atminties srities adreso 16*x1 + x2 reikšmę.
    public void LX(String address) {
        ++IC;
    }

    //LYx1x2 - į R2 užkrauna bendrosios atminties srities adreso 16*x1 + x2 reikšmę.
    public void LY(String address) {
        ++IC;
    }

    //LLx1x2 - į bendrosios atminties sritį adresu 16 * x1 + x2 rašo žodį ar skaičių.
    public void LL(String address) {
        ++IC;
    }

    //LRx1x2 - nuskaito registrą R1
    public void LR(String address) {
        ++IC;
    }

    //LDx1x2 - nuskaito registrą R2
    public void LD(String address) {
        ++IC;
    }

    //JMx1x2 - besąlyginio valdymo perdavimo komanda. Ji reiškia, kad valdymas turi būti perduotas kodo segmento žodžiui, nurodytam adresu 16 * x1 + x2
    public void JM(String address) {
        ++IC;
    }

    //JEx1x2 - valdymas turi būti perduotas kodo segmento žodžiui, nurodytam adresu 16* x1 + x2 jeigu ZF = 1
    public void JE(String address) {
        if (getZF() == 1){

        }
        ++IC;
    }

    //JGx1x2 - valdymas turi būti perduotas kodo segmento žodžiui, nurodytam adresu 16* x1 + x2 jeigu ZF = 0 IR SF = OF
    public void JG(String address) {
        if(getZF() == 0 && getSF() == getOF()) {

        }
        ++IC;
    }

    //JLx1x2 - valdymas turi būti perduotas kodo segmento žodžiui, nurodytam adresu 16* x1 + x2 jeigu SF != OF
    public void JL(String address) {
        if (!(getSF() == getOF())) {
            Integer.parseInt(address, 16);
        }
        ++IC;
    }

    /// /IC - komandos skaitliukas. IC = 16 * x1 + x2;
    public void IC(String address) {
        IC = Short.parseShort(address, 16);
    }

    //HALT - programos sustojimo taško komanda, t.y. programos valdymo pabaiga.
    public void HALT() throws Exception {
        throw new Exception("PROGRAMOS PABAIGA");
    }

    //PDx - SI tampa 1 ir valdymas perduodamas OS, duomenų kopijavimui iš kietojo disko į supervizorinės atminties vietą x.
    public void PD() {

    }

    //GDx - SI tampa 2 ir valdymas perduodamas OS, duomenų kopijavimui į kietąjį diskąį iš supervizorinės atminties vietos x.
    public void GD() {

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
}
