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
    Boolean[] zyxBlock = new Boolean[3];
    Boolean[] faceIn;
    Boolean[] faceOut;
   // Boolean[][] face;

    int[] mapsBase3 = new int[]{
        8, 19, 20,
        5, 4, 23,
        2, 1, 26,
        11, 10, 17,
        12, 13, 14,
        9, 16, 15,
        0, 25, 24,
        3, 22, 21,
        6, 7, 18
    };
    int[] mapsBase5RotFFF = new int[]{
        /*
         I'm trying to implement rotation tendencies. Think of it as if the core 
         were spinning along the three axiis. For lack of an appropriate method
         of naming the various possibilities, I have chosen to go with denoting
         z, y, and x with F for forward/clockwise and R for reverse/counter-clockwise
        
         I have also chosen an arbitrary point of view to percieve this from, 
         since there are no orientations in empty space to tell you what is what.
         For X, I am looking at the cube from a -X position, so it's like standing
         to the left and then facing it. Clockwise would result in face 2 bouncing
         off the core and out of face 102, while counter-clockwise would result
         in face 2 bouncing out of face 22. I'm calling it this because the order
         of faces rotates around the X axis.
        
         For the Y axis, I am supposing that the viewer is looking down at the 
         cube from above so that clockwise would result in something entering 
         through face 50 to bounce out of face 70, and counter-clockwise would
         cause face 50 to bounce out of face 54.
        
         For Z, I am assuming that the viewer is looking from what I call the 
         front, and have assigned as the Z- position. So clockwise would cause
         face 10 to bounce out of face 14, while counter-clockwise will cause
         face 10 to bounce out of face 110.
        
         So, I am thinking of writing a few of these up just to get them written.
         There is more detail to add later too. The composite-axiis such as ZY,
         ZX, and YX can be given their own rotations too, but for now I am going
         to set them up so that they flow with the current, rather than against it.
         */
        //damnit. this is way more complicated than I expected. Try to imagine
        //a ball rotating in every way.
        0, 103, 102, 101, 0,
        95, 0, 77, 0, 75,
        70, 0, 0, 0, 0,
        0, 0, 0, 0, 0,
        0, 0, 0, 0, 0,
        0, 0, 0, 0, 0,
        0, 0, 0, 0, 0,
        0, 0, 0, 0, 0,
        0, 0, 0, 0, 0,
        0, 0, 0, 0, 0,
        0, 0, 0, 0, 0,
        0, 0, 0, 0, 0,
        0, 0, 0, 0, 0,
        0, 0, 0, 0, 0,
        0, 0, 0, 0, 0,
        0, 0, 0, 0, 0,
        0, 0, 0, 0, 0,
        0, 0, 0, 0, 0,
        0, 0, 0, 0, 0,
        0, 0, 0, 0, 0,
        0, 0, 0, 0, 0,
        0, 0, 0, 0, 0,
        0, 0, 0, 0, 0,
        0, 0, 0, 0, 0,
        0, 0, 0, 0, 0,};

    public static int[] mapsBase5YXWall = new int[]{
        104, 103, 102, 101, 100,
        109, 108, 107, 106, 105,
        114, 113, 112, 111, 110,
        119, 118, 117, 116, 115,
        124, 123, 122, 121, 120,
        
        79, 78, 77, 76, 75,
        84, 83, 82, 81, 80,
        89, 88, 87, 86, 85,
        94, 93, 92, 91, 90,
        99, 98, 97, 96, 95,
        
        54, 53, 52, 51, 50,
        59, 58, 57, 56, 55,
        64, 63, 62, 61, 60,
        69, 68, 67, 66, 65,
        74, 73, 72, 71, 70,
        
        29, 28, 27, 26, 25,
        34, 33, 32, 31, 30,
        39, 38, 37, 36, 35,
        44, 43, 42, 41, 40,
        49, 48, 47, 46, 45,
        
        4, 3, 2, 1, 0,
        9, 8, 7, 6, 5,
        14, 13, 12, 11, 10,
        19, 18, 17, 16, 15,
        24, 23, 22, 21, 20,
        
    };
    public static int[] mapsBase5ZYWall = new int[]{
        120, 121, 122, 123, 124,
        115, 116, 117, 118, 119,
        110, 111, 112, 113, 114,
        105, 106, 107, 108, 109,
        100, 101, 102, 103, 104,
        
        95, 96, 97, 98, 99,
        90, 91, 92, 93, 94,
        85, 86, 87, 88, 89,
        80, 81, 82, 83, 84,
        75, 76, 77, 78, 79,
        
        70, 71, 72, 73, 74,
        65, 66, 67, 68, 69,
        60, 61, 62, 63, 64,
        55, 56, 57, 58, 59,
        50, 51, 52, 53, 54,
        
        45, 46, 47, 48, 49,
        40, 41, 42, 43, 44,
        35, 36, 37, 38, 39,
        30, 31, 32, 33, 34,
        25, 26, 27, 28, 29,
        
        20, 21, 22, 23, 24,
        15, 16, 17, 18, 19,
        10, 11, 12, 13, 14,
        5, 6, 7, 8, 9, 
        0, 1, 2, 3, 4
    };
    
    public static int[] mapsBase5ZXWall = new int[]{
        /*
        As if there were a solid platform extending through this space along the
        Z and X planes. Meant to simulate so that incoming forces skip or bounce
        off of this wall.
        */
        
        24, 23, 22, 21, 20,
        19, 18, 17, 16, 15,
        14, 13, 12, 11, 10,
        9, 8, 7, 6, 5,
        4, 3, 2, 1, 0,
        
        49, 48, 47, 46, 45,
        44, 43, 42, 41, 40,
        39, 38, 37, 36, 35,
        34, 33, 32, 31, 30,
        29, 28, 27, 26, 25,
        
        //These are just gonna pass through as if they barely miss hitting.
        74, 73, 72, 71, 70,
        69, 68, 67, 66, 65,
        64, 63, 62, 61, 60,
        59, 58, 57, 56, 55, 
        54, 53, 52, 51, 50,
        
        99, 98, 97, 96, 95,
        94, 93, 92, 91, 90,
        89, 88, 87, 86, 85,
        84, 83, 82, 81, 80,
        79, 78, 77, 76, 75,
        
        124, 123, 122, 121, 120,
        119, 118, 117, 116, 115, 
        114, 113, 112, 111, 110,
        109, 108, 107, 106, 105,
        104, 103, 102, 101, 100
        
    };
    public static int[] mapsBase5 = new int[]{
        0, 3, 2, 1, 4,
        15, 18, 17, 16, 19,
        10, 13, 12, 11, 14,
        5, 8, 7, 6, 9,
        20, 23, 22, 21, 24,
        75, 78, 77, 76, 79,
        90, 31, 32, 33, 94,
        85, 36, 37, 38, 89,
        80, 41, 42, 43, 84,
        95, 98, 97, 96, 99,
        50, 53, 52, 51, 54,
        65, 56, 57, 58, 69,
        60, 61, 62, 63, 64,
        55, 66, 67, 68, 59,
        70, 73, 72, 71, 74,
        25, 28, 27, 26, 29,
        40, 81, 82, 83, 44,
        35, 86, 87, 88, 39,
        30, 91, 92, 93, 34,
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
        //face = new Boolean[STATES][BASE3];
        faceIn = new Boolean[BASE3];
        faceOut = new Boolean[BASE3];
        zyxBlock[0] = false;
        zyxBlock[1] = false;
        zyxBlock[2] = false;
        /*
        for (int j = 0; j < STATES; j++) {
            for (int i = 0; i < BASE3; i++) {
                face[j][i] = false;
            }
        }
                */
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
                } else {

                    faceIn[(BASE3 - 1) - i] = false;
                }
            }
        }
    }

    public void processBase5() {
        if (faceIn[(int) ((BASE3 - 1) / 2)] == true) {
            for (int i = 0; i < BASE3; i++) {
                if (faceIn[i] == true) {
                    faceIn[i] = false;
                    //faceOut[mapsBase5[i]] = true;
                    
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
