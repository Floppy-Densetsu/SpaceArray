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

    int[] mapsBase5 = new int[] {
        0, 3, 2, 1, 4,
        15, 18, 17, 16, 19,
        10, 13, 12, 11, 14,
        15, 8, 7, 6, 9,
        20, 23, 22, 21, 24,
        75, 78, 77, 76, 79,
        90, -1, -1, -1, 94,
        85, -1, -1, -1, 89,
        80, -1, -1, -1, 84,
        95, 78, 77, 76, 79,
        50, 53, 52, 51, 54,
        65, -1, -1, -1, 69,
        60, -1, 62, -1, 64,
        55, -1, -1, -1, 59,
        70, 73, 72, 71, 74,
        25, 28, 27, 26, 29,
        40, -1, -1, -1, 44,
        35, -1, -1, -1, 39,
        30, -1, -1, -1, 34,
        45, 48, 47, 46, 49,
        100, 103, 102, 101, 104,
        115, 118, 117, 116, 119,
        110, 113, 112, 111, 114,
        105, 108, 107, 106, 109,
        120, 123, 122, 121, 124
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
            if (faceIn[i]) {
                faceIn[i] = false;

                if (!faceIn[(BASE3 - 1) - i]) {
                    faceOut[(BASE3 - 1) - i] = true;
                }

                faceIn[(BASE3 - 1) - i] = false;
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
                            faceOut[out] = true;
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

                        int out = mapsBase5[i];

                        if (out >= 0) {
                            faceOut[out] = true;
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
