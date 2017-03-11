package vm;

public class VM {

    private int R1;
    private int R2;
    private byte C;
    private short IC;

    public VM() {
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
            LW();
        } else if (line.substring(0, 2).equals("LE")) {
            LE();
        } else if (line.substring(0, 2).equals("LS")) {
            LS();
        } else if (line.substring(0, 2).equals("LX")) {
            LX();
        } else if (line.substring(0, 2).equals("LY")) {
            LY();
        } else if (line.substring(0, 2).equals("LL")) {
            LL();
        } else if (line.substring(0, 2).equals("LR")) {
            LR();
        } else if (line.substring(0, 2).equals("LD")) {
            LD();
        } else if (line.substring(0, 2).equals("JM")) {
            JM();
        } else if (line.substring(0, 2).equals("JE")) {
            JE();
        } else if (line.substring(0, 2).equals("JG")) {
            JG();
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
    }

    // Padalina R1 iš R2, įrašoma į R1. Jeigu reikšmės ženklo bitas yra 1, SF = 1.
    public void DIV() {
        R1 /= R2;
        if (((R1 >> 6) & 1) == 1) {
            setSF();
        }
    }

    //Ši komanda palygina registre R1 ir R2 ęsančias reikšmes. Jeigu reikšmės lygios, ZF = 1, priešingu atveju ZF = 0.
    public void CMP() {
        if (R1 == R2) {
            setZF();
        } else {
            clearZF();
        }
    }

    //LWx1x2 - į registrą R1 užkrauna žodį nurodytu adresu 16 * x1 + x2.
    public void LW() {


    }

    //LEx1x2 - į registrą R2 užkrauna skaičių, adresu 16 * x1 + x2.
    public void LE() {

    }

    //LSx1x2 - į atmintį adresu 16 * x1 + x2 rašo žodį ar skaičių.
    public void LS() {

    }

    //LXx1x2 - į R1 užkrauna bendrosios atminties srities adreso 16*x1 + x2 reikšmę.
    public void LX() {

    }

    //LYx1x2 - į R2 užkrauna bendrosios atminties srities adreso 16*x1 + x2 reikšmę.
    public void LY() {

    }

    //LLx1x2 - į bendrosios atminties sritį adresu 16 * x1 + x2 rašo žodį ar skaičių.
    public void LL() {

    }

    //LRx1x2 - nuskaito registrą R1
    public void LR() {

    }

    //LDx1x2 - nuskaito registrą R2
    public void LD() {

    }

    //JMx1x2 - besąlyginio valdymo perdavimo komanda. Ji reiškia, kad valdymas turi būti perduotas kodo segmento žodžiui, nurodytam adresu 16 * x1 + x2
    public void JM() {

    }

    //JEx1x2 - valdymas turi būti perduotas kodo segmento žodžiui, nurodytam adresu 16* x1 + x2 jeigu ZF = 1
    public void JE() {
        if (getZF() == 1){

        }
    }

    //JGx1x2 - valdymas turi būti perduotas kodo segmento žodžiui, nurodytam adresu 16* x1 + x2 jeigu ZF = 0 IR SF = OF
    public void JG() {
        if(getZF() == 0 && getSF() == getOF()) {

        }
    }

    //JLx1x2 - valdymas turi būti perduotas kodo segmento žodžiui, nurodytam adresu 16* x1 + x2 jeigu SF != OF
    public void JL(String address) {
        if (!(getSF() == getOF())) {
            Integer.parseInt(address, 16);
        }

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
