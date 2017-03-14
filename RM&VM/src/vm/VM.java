package vm;

import rm.Memory;
import rm.RM;

public class VM {

    private byte C;
    private short IC;

    private Memory memory;

    public VM() {
        this.memory = RM.getMemory();
    }

    public void resolveCommand(String line) throws Exception {
        if (line.equals("HALT")) {
            RM.HALT();
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
        } /*else if (line.substring(0, 2).equals("LS")) {
            LS(line.substring(3, 4));
        } else if (line.substring(0, 2).equals("LX")) {
            LX(line.substring(3, 4));
        } else if (line.substring(0, 2).equals("LY")) {
            LY(line.substring(3, 4));*
        } else if (line.substring(0, 2).equals("LL")) {
            LL(line.substring(3, 4));
        } */else if (line.substring(0, 2).equals("LR")) {
            LR(line.substring(3, 4));
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
            GD(line.substring(3, 4));
        } else {
            // 2 - neatpažintas operacijos kodas
            RM.setPI((byte)2);
            throw new Exception("PAKEIST I TINKAMA. NEATPAZINTA KOMANDA");
        }
    }

    // Sudeda R1 ir R2, įrašoma į R1. Jeigu rezultatas netelpa, OF = 1. Jeigu reikšmės ženklo bitas yra 1, SF = 1.
    public void ADD() {
        if (RM.R1 + RM.R2 > Integer.MAX_VALUE) {
            setOF();
            return;
        } else {
            RM.R1 += RM.R2;
        }
        if (((RM.R1 >> 6) & 1) == 1) {
            setSF();
        }
        ++IC;
    }

    // Iš R1 atimama R2, įrašoma į R1. Jeigu rezultatas netelpa, OF = 1. Jeigu reikšmės ženklo bitas yra 1, SF = 1.
    public void SUB() {
        if (RM.R1 - RM.R2 < Integer.MIN_VALUE) {
            setOF();
            return;
        } else {
            RM.R1 -= RM.R2;
        }
        if (((RM.R1 >> 6) & 1) == 1) {
            setSF();
        }
        ++IC;
    }

    // Sudaugina R1 ir R2, įrašoma į R1.Jeigu rezultatas netelpa, OF = 1.Jeigu reikšmės ženklo bitas yra 1, SF = 1.
    public void MUL() {
        if (RM.R1 * RM.R2 > Integer.MAX_VALUE) {
            setOF();
            return;
        } else {
            RM.R1 *= RM.R2;
        }
        if (((RM.R1 >> 6) & 1) == 1) {
            setSF();
        }
        ++IC;
    }

    // Padalina R1 iš R2, įrašoma į R1. Jeigu reikšmės ženklo bitas yra 1, SF = 1.
    public void DIV() {
        RM.R1 /= RM.R2;
        if (((RM.R1 >> 6) & 1) == 1) {
            setSF();
        }
        ++IC;
    }

    //Ši komanda palygina registre R1 ir R2 ęsančias reikšmes. Jeigu reikšmės lygios, ZF = 1, priešingu atveju ZF = 0.
    public void CMP() {
        if (RM.R1 == RM.R2) {
            setZF();
        } else {
            clearZF();
        }
        ++IC;
    }

    //LWx1x2 - į registrą R1 užkrauna žodį nurodytu adresu 16 * x1 + x2.
    public void LW(String address) {
        RM.R1 = memory.getBlock(address.charAt(0) - '0')[address.charAt(1) - '0'];
        ++IC;
    }

    //LEx1x2 - į registrą R2 užkrauna skaičių, adresu 16 * x1 + x2.
    public void LE(String address) {
        //zodis 4 baitai, skaicius 2?
        RM.R2 = (short)memory.getBlock(address.charAt(0) - '0')[address.charAt(1) - '0'];
        ++IC;
        ++IC;
    }

    //LSx1x2 - į atmintį adresu 16 * x1 + x2 rašo žodį ar skaičių.
    //Duomenu ivedimui is failo naudosim
    public void LS(String address) {
        ++IC;
    }

   /*
    * Bendroji atminties sritis lygtais jau MOS dalis
    *
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
    */

    //LRXX- išveda į printerį XX registrą (R1 arba R2)
    public void LR(String register) { ++IC; }

    //LDx1x2 - nuskaito registrą R2
    //public void LD(String address) {
    //    ++IC;
    //}

    //JMx1x2 - besąlyginio valdymo perdavimo komanda. Ji reiškia, kad valdymas turi būti perduotas kodo segmento žodžiui, nurodytam adresu 16 * x1 + x2
    public void JM(String address) {
        ++IC;
    }

    //JEx1x2 - valdymas turi būti perduotas kodo segmento žodžiui, nurodytam adresu 16* x1 + x2 jeigu ZF = 1
    public void JE(String address) {
        if (getZF() == 1) {

        }
        ++IC;
    }

    //JGx1x2 - valdymas turi būti perduotas kodo segmento žodžiui, nurodytam adresu 16* x1 + x2 jeigu ZF = 0 IR SF = OF
    public void JG(String address) {
        if (getZF() == 0 && getSF() == getOF()) {

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

    /* //HALT - programos sustojimo taško komanda, t.y. programos valdymo pabaiga.
    public void HALT() throws Exception {
        RM.setSI((byte) 3);
        //throw new Exception("PROGRAMOS PABAIGA");
        RM.setSI((byte)0);
    }

    //PDx - SI tampa 1 ir valdymas perduodamas OS, duomenų kopijavimui iš kietojo disko į supervizorinės atminties vietą x.
    public void PD() {
        RM.setSI((byte) 1);
        RM.readFromHDD();
        RM.setSI((byte)0);
    }

    //GDx - SI tampa 2 ir valdymas perduodamas OS, duomenų kopijavimui į kietąjį diską iš supervizorinės atminties vietos x.
    public void GD(String address) {
        RM.setSI((byte) 2);
        RM.writeToHDD(Integer.parseInt(address, 16));
        RM.setSI((byte)0);
    } */

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
