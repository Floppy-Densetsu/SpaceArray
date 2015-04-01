/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @author Flopp_000
 */
import java.awt.image.*;
import java.awt.*;
import java.awt.Color;

public class Space {

    int BASE;
    int BASE2;
    int BASE3;
    int STATES;
    Boolean[] faceIn;
    Boolean[] faceOut;
    Boolean[][] face;

    int[] mapsBase3 = new int[] {
        0, 19, 20, 5, 4,
        23, 2, 1, 26, 11,
        10, 17, 12, 13, 14,
        9, 16, 15, 0, 25,
        24, 3, 22, 21, 6,
        7, 18
    };

    public Space() {
        this(3, 4);
    }

    public Space(int size) {
        this(size, 4);
    }

    public Space(int size, int states) {
        STATES = states;
        BASE = size;
        BASE2 = (int) Math.pow(BASE, 2);
        BASE3 = (int) Math.pow(BASE, 3);
        face = new Boolean[STATES][BASE3];
        faceIn = new Boolean[BASE3];
        faceOut = new Boolean[BASE3];
        for (int j = 0; j < STATES; j++) {
            for (int i = 0; i < BASE3; i++) {
                face[j][i] = false;
            }
        }
        for (int i = 0; i < BASE3; i++) {
            faceIn[i] = false;
            faceOut[i] = false;
        }
    }

    public void testStates() {
        faceOut[14] = true;
        faceOut[10] = true;
        faceOut[6] = true;
        faceOut[17] = true;
        faceOut[25] = true;
        faceOut[0] = true;
        faceOut[24] = true;
        faceOut[4] = true;
        faceOut[15] = true;
    }

    public void clearFaceOut(int i) {
        faceOut[i] = false;
    }

    public Boolean isEmpty() {
        for (int i = 0; i < BASE3; i++) {

            if (faceIn[i] == true || faceOut[i] == true) {
                return false;
            }
        }
        return true;
    }

    public void passThru() {
        for (int i = 0; i < BASE3; i++) {
            if (faceIn[i] == true) {
                if (faceIn[(BASE3 - 1) - i] == true) {
                    faceIn[i] = false;
                    faceIn[(BASE3 - 1) - i] = false;
                } else {
                    faceIn[i] = false;
                    faceOut[(BASE3 - 1) - i] = true;
                }
            }
        }
    }



    public void process2() {
        if (BASE == 3) {
            if (faceIn[(int) ((BASE3 - 1) / 2)] == true) {
                for (int i = 0; i < BASE3; i++) {
                    if (faceIn[i] == true) {
                        faceIn[i] = false;

                        int out = mapsBase3[i];

                        if (out >= 0) {
                            faceOut[] = true;
                        }
                    }
                }
            } else {
                for (int i = 0; i < BASE3; i++) {
                    if (faceIn[i] == true) {
                        faceIn[i] = false;
                        faceOut[(BASE3 - 1) - i] = true;
                    }
                }
            }
        } else if (BASE == 5) {
            if (faceIn[(int) ((BASE3 - 1) / 2)] == true) {
                for (int i = 0; i < BASE3; i++) {
                    if (faceIn[i] == true) {
                        faceIn[i] = false;
                        switch (i) {
                            case 0:
                                faceOut[0] = true;
                                break;
                            case 1:
                                faceOut[3] = true;
                                break;
                            case 2:
                                faceOut[2] = true;
                                break;
                            case 3:
                                faceOut[1] = true;
                                break;
                            case 4:
                                faceOut[4] = true;
                                break;
                            case 5:
                                faceOut[15] = true;
                                break;
                            case 6:
                                faceOut[18] = true;
                                break;
                            case 7:
                                faceOut[17] = true;
                                break;
                            case 8:
                                faceOut[16] = true;
                                break;
                            case 9:
                                faceOut[19] = true;
                                break;
                            case 10:
                                faceOut[10] = true;
                                break;
                            case 11:
                                faceOut[13] = true;
                                break;
                            case 12:
                                faceOut[12] = true;
                                break;
                            case 13:
                                faceOut[11] = true;
                                break;
                            case 14:
                                faceOut[14] = true;
                                break;
                            case 15:
                                faceOut[5] = true;
                                break;
                            case 16:
                                faceOut[8] = true;
                                break;
                            case 17:
                                faceOut[7] = true;
                                break;
                            case 18:
                                faceOut[6] = true;
                                break;
                            case 19:
                                faceOut[9] = true;
                                break;
                            case 20:
                                faceOut[20] = true;
                                break;
                            case 21:
                                faceOut[23] = true;
                                break;
                            case 22:
                                faceOut[22] = true;
                                break;
                            case 23:
                                faceOut[21] = true;
                                break;
                            case 24:
                                faceOut[24] = true;
                                break;
                            case 25:
                                faceOut[75] = true;
                                break;
                            case 26:
                                faceOut[78] = true;
                                break;
                            case 27:
                                faceOut[77] = true;
                                break;
                            case 28:
                                faceOut[76] = true;
                                break;
                            case 29:
                                faceOut[79] = true;
                                break;
                            case 30:
                                faceOut[90] = true;
                                break;
                            //ignore for now cases 31, 32, and 33
                            case 34:
                                faceOut[94] = true;
                                break;
                            case 35:
                                faceOut[85] = true;
                                break;
                            //ignore for now cases 36, 37, and 38
                            case 39:
                                faceOut[89] = true;
                                break;
                            case 40:
                                faceOut[80] = true;
                                break;
                            //ignore for now cases 41, 42, and 43
                            case 44:
                                faceOut[84] = true;
                                break;
                            case 45:
                                faceOut[95] = true;
                                break;
                            case 46:
                                faceOut[78] = true;
                                break;
                            case 47:
                                faceOut[77] = true;
                                break;
                            case 48:
                                faceOut[76] = true;
                                break;
                            case 49:
                                faceOut[79] = true;
                                break;
                            case 50:
                                faceOut[50] = true;
                                break;
                            case 51:
                                faceOut[53] = true;
                                break;
                            case 52:
                                faceOut[52] = true;
                                break;
                            case 53:
                                faceOut[51] = true;
                                break;
                            case 54:
                                faceOut[54] = true;
                                break;
                            case 55:
                                faceOut[65] = true;
                                break;
                            //ignore for now cases 56, 57, and 58
                            case 59:
                                faceOut[69] = true;
                                break;

                            case 60:
                                faceOut[60] = true;
                                break;
                            //ignore for now case 61
                            case 62:
                                faceOut[62] = true;
                                break;
                            //ignore for now case 63
                            case 64:
                                faceOut[64] = true;
                                break;

                            case 65:
                                faceOut[55] = true;
                                break;
                            //ignore for now cases 66, 67, and 68
                            case 69:
                                faceOut[59] = true;
                                break;
                            case 70:
                                faceOut[70] = true;
                                break;
                            case 71:
                                faceOut[73] = true;
                                break;
                            case 72:
                                faceOut[72] = true;
                                break;
                            case 73:
                                faceOut[71] = true;
                                break;
                            case 74:
                                faceOut[74] = true;
                                break;
                            case 75:
                                faceOut[25] = true;
                                break;
                            case 76:
                                faceOut[28] = true;
                                break;
                            case 77:
                                faceOut[27] = true;
                                break;
                            case 78:
                                faceOut[26] = true;
                                break;
                            case 79:
                                faceOut[29] = true;
                                break;
                            case 80:
                                faceOut[40] = true;
                                break;
                            //ignore for now cases 81, 82, and 83
                            case 84:
                                faceOut[44] = true;
                                break;
                            case 85:
                                faceOut[35] = true;
                                break;
                            //ignore for now cases 86, 87, and 88
                            case 89:
                                faceOut[39] = true;
                                break;
                            case 90:
                                faceOut[30] = true;
                                break;
                            //ignore for now cases 91, 92, and 93
                            case 94:
                                faceOut[34] = true;
                                break;
                            case 95:
                                faceOut[45] = true;
                                break;
                            case 96:
                                faceOut[48] = true;
                                break;
                            case 97:
                                faceOut[47] = true;
                                break;
                            case 98:
                                faceOut[46] = true;
                                break;
                            case 99:
                                faceOut[49] = true;
                                break;
                            case 100:
                                faceOut[100] = true;
                                break;
                            case 101:
                                faceOut[103] = true;
                                break;
                            case 102:
                                faceOut[102] = true;
                                break;
                            case 103:
                                faceOut[101] = true;
                                break;
                            case 104:
                                faceOut[104] = true;
                                break;
                            case 105:
                                faceOut[115] = true;
                                break;
                            case 106:
                                faceOut[118] = true;
                                break;
                            case 107:
                                faceOut[117] = true;
                                break;
                            case 108:
                                faceOut[116] = true;
                                break;
                            case 109:
                                faceOut[119] = true;
                                break;
                            case 110:
                                faceOut[110] = true;
                                break;
                            case 111:
                                faceOut[113] = true;
                                break;
                            case 112:
                                faceOut[112] = true;
                                break;
                            case 113:
                                faceOut[111] = true;
                                break;
                            case 114:
                                faceOut[114] = true;
                                break;
                            case 115:
                                faceOut[105] = true;
                                break;
                            case 116:
                                faceOut[108] = true;
                                break;
                            case 117:
                                faceOut[107] = true;
                                break;
                            case 118:
                                faceOut[106] = true;
                                break;
                            case 119:
                                faceOut[109] = true;
                                break;
                            case 120:
                                faceOut[120] = true;
                                break;
                            case 121:
                                faceOut[123] = true;
                                break;
                            case 122:
                                faceOut[122] = true;
                                break;
                            case 123:
                                faceOut[121] = true;
                                break;
                            case 124:
                                faceOut[124] = true;
                                break;


                        }
                    }
                }
            } else {
                for (int i = 0; i < BASE3; i++) {
                    if (faceIn[i] == true) {
                        faceIn[i] = false;
                        faceOut[(BASE3 - 1) - i] = true;
                    }
                }
            }
        }

    }

    public void process() {
        for (int i = 0; i < BASE3; i++) {

            if (faceIn[i] == true) {
                faceIn[i] = false;
                if (faceIn[(BASE3 - 1) - i] == true) {
                    faceIn[(BASE3 - 1) - i] = false;
                    if (i < BASE3 / 2) {
                        faceOut[i + 1] = true;
                        faceOut[((BASE3 - 1) - i) - 1] = true;
                    } else if (i > BASE3 / 2) {
                        faceOut[i - 1] = true;
                        faceOut[((BASE3 - 1) - i) + 1] = true;

                    } else {
                        faceOut[0] = true;
                        faceOut[BASE3 - 1] = true;
                    }
                } else {
                    faceOut[(BASE3 - 1) - i] = true;
                }
            }
        }
    }
}
