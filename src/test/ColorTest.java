/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @author Flopp_000
 * 
 * Gonna try to design this one to show things in a particular way
 * and do some stuff based on user input.
 * 
 * It doesn't do anything different right now.
 */
import java.awt.image.*;
import java.awt.*;

public class ColorTest implements Runnable {

    /**
     * @param args the command line arguments
     */
    static int BASE = 5;
    static int BASE2 = (int) Math.pow(BASE, 2);
    static int BASE3 = (int) Math.pow(BASE, 3);
    static int MAXZ = 5;
    static int MAXY = 13;
    static int MAXX = 6;
    static BufferedImage spaceMap = new BufferedImage(1900, 1000, BufferedImage.TYPE_INT_RGB);
    static BufferedImage finalImage = new BufferedImage(800, 800, BufferedImage.TYPE_INT_RGB);
    static BufferedImage[] display = new BufferedImage[MAXZ];
    static Space[][][] space = new Space[MAXZ][MAXY][MAXX];
    static Graphics2D g2d;
    static Graphics g;
    static MyFrame frame;
    static int blipSize = 3;
    static Thread loop;
    static Color newColor;
    static Color inColor = new Color(150, 90, 40);
    static Color outColor = new Color(100, 90, 150);
    static Font font = new Font("monospaced", Font.PLAIN, 10);
    static boolean drawSwitch = true;
    static int facetest = 0;
    static int loopcounter = 0;
    static int testZ = 0;
    static int testY = 0;
    static int testX = 0;
    static boolean firedswitch = false;
    static int[] counter;
    static int blah;
    static private int[] workFaces = new int[BASE3];

    static class MyFrame extends javax.swing.JFrame {

        public void paint(Graphics g) {

            g.drawImage(spaceMap, 0, 0, rootPane);
        }
    }

    public static void main(String[] args) {

        for(int i = 0; i < 6; i++){
            System.out.println((int)(i / 2)*2 + " " + i);
        }

        newColor = new Color(0);

        g2d = spaceMap.createGraphics();
        g2d.setFont(font);
        frame = new MyFrame();
        frame.setTitle("Testing");
        frame.setVisible(true);
        frame.setSize(1900, 1000);
        frame.setDefaultCloseOperation(MyFrame.EXIT_ON_CLOSE);


        initialize();
        //makeBoxes(MAXZ, MAXY, MAXX, BASE3);
        
        

        for (int i = 0; i < workFaces.length; i++) {
            workFaces[i] = 0;
        }

        //for (int y = 0; y < MAXY; y++) {
        //    space[2][y][2].faceIn[64] = true;
        //}

       // space[2][8][1].faceIn[112] = true;
        counter = new int[4];
        java.util.Arrays.fill(counter, 0);
        //space[MAXZ - 1][4][1].faceIn[19] = true;
        // space[0][3][1].faceIn[11] = true;
        loop = new Thread(new ColorTest());
        loop.start();

    }

    public static void counterloop() {


        if (counter[0] < MAXZ) {
            if (counter[1] < MAXY) {
                if (counter[2] < MAXX) {
                    if (counter[3] < (BASE3 * 100)) {
                        //for (int f = 0; f < BASE3; f++) {
                        if (counter[3] == 0) {
                            clearAll();
                            space[counter[0]][counter[1]][counter[2]].faceOut[counter[3]] = true;
                            System.out.println("Switching on space[" + counter[0] + "][" + counter[1] + "][" + counter[2] + "].faceOut[" + counter[3] + "]");

                        } else if (counter[3] % 100 == 0) {
                            clearAll();
                            space[counter[0]][counter[1]][counter[2]].faceOut[counter[3] / 100] = true;
                            System.out.println("Switching on space[" + counter[0] + "][" + counter[1] + "][" + counter[2] + "].faceOut[" + counter[3] / 100 + "]");
                            //counter[3]++;
                        }
                        // }
                    } else {
                        if (counter[2] >= MAXX - 1) {
                            if (counter[1] >= MAXY - 1) {
                                if (counter[0] >= MAXZ - 1) {
                                    java.util.Arrays.fill(counter, 0);
                                } else {
                                    counter[0]++;
                                    counter[1] = 0;
                                    counter[2] = 0;
                                    counter[3] = 0;
                                }
                            } else {
                                counter[1]++;
                                counter[2] = 0;
                                counter[3] = 0;
                            }
                        } else {
                            counter[2]++;
                            counter[3] = 0;
                        }
                    }
                }
            }
        }
        counter[3]++;
    }

    public static void fireFaces(int z, int y, int x) {
        loopcounter = 0;
        clearAll();
        //System.out.println("Now Firing space[" + z + "][" + testY + "][" + testX + "]");
        for (int i = 0; i < BASE3; i++) {
            if (loopcounter == i * 1000) {
                clearAll();
                space[z][y][x].faceOut[i] = true;
            }
        }
    }

    public static void sortAttractProcess() {

        /* the goal here is to write it so that if two are "colliding" as in the
         * space has more than one input turned on, then they will alter their 
         * path to pass one step closer to eachother. They don't actually 
         * collide, they just make a tighter turn towards where the other one
         * was.
         * */
        int activeFaces = 0;
        int rand = 0;
        for(int i = 0; i < workFaces.length; i++){
            workFaces[i] = 0;
        }

        for (int z = 0; z < MAXZ; z++) {
            for (int y = 0; y < MAXY; y++) {
                for (int x = 0; x < MAXX; x++) {
                     activeFaces = 0;
         rand = 0;
        for(int i = 0; i < workFaces.length; i++){
            workFaces[i] = 0;
        }
        
                    for (int f = 0; f < BASE3; f++) {

                        if (space[z][y][x].faceIn[f] == true) {
                            
                            workFaces[activeFaces] = f;
                            
                            //System.out.println("WorkFaces[" + activeFaces + 
                            //        "] filled with: " + f + ".");
                            
                            activeFaces++;
                        }
                    }


                    for (int f = 0; f < (int)((activeFaces + 1) / 2); f++) {
                        if (activeFaces - (f * 2) == 1) {

                            passThrough(workFaces[f], z, y, x);
                        } else {
                            rand = ((int) (Math.random() * (activeFaces - 2 - (f*2) + f))) + 1 ;
                            passBy(workFaces[f], workFaces[rand], z, y, x);
                            
                            //System.out.println("ActiveFaces: " + activeFaces + 
                            //        ", rand: " + rand + ", f: " + f + ".");
                                    
                            
                            workFaces[f] = 0;
                            workFaces[rand] = 0;
                            
                            for (int temp = rand; temp < workFaces.length; temp++) {
                                if (workFaces[temp + 1] == 0) {
                                    if (workFaces[temp + 2] == 0) {
                                        break;
                                    } else {
                                        workFaces[temp] = workFaces[temp + 2];
                                        break;
                                    }
                                } else {
                                    workFaces[temp] = workFaces[temp + 1];
                                }
                            }
                        }
                    }

                    for (int i = 0; i < activeFaces + 1; i++) {
                        workFaces[i] = 0;
                    }
                    activeFaces = 0;

                }
            }
        }
    }

    public static void passThrough(int faceIn, int thisZ, int thisY, int thisX) {
        space[thisZ][thisY][thisX].faceIn[faceIn] = false;
        space[thisZ][thisY][thisX].faceOut[(BASE3 - 1) - faceIn] = true;
        
        //System.out.println("Pass Space[" + thisZ + "][" + thisY + "][" + thisX +
        //        "]: In: " + faceIn + ", To Out: " + ((BASE3 - 1) - faceIn) + ".");
                
    }

    public static boolean[] checkPull(int testFace) {
        boolean[] list = new boolean[6];
        //Arrays.fill(list, false);

        if (testFace < BASE2) {//In from top. (y- side)
            //z-, z+, y-, y+, x-, x+
            list[2] = true;
        } else if (testFace > ((BASE3 - 1) - BASE2)) {//In from bottom.(y+ side)
            list[3] = true;
        } else {
            list[2] = false;
            list[3] = false;
        }

        if (testFace % BASE2 < BASE) {//in from front. (z- side)
            //z-, z+, y-, y+, x-, x+
            list[0] = true;
        } else if ((testFace + BASE) % BASE2 < BASE) {//in from rear (z+ side)
            list[1] = true;
        } else {
            list[0] = false;
            list[1] = false;
        }
//System.out.println("testFace == " + testFace + " list[5] == " + list[5]);

        if (testFace % BASE == 0) {//in from left (x- side)
            list[4] = true;
        } else if ((testFace + 1) % BASE == 0) {//in from right (x+ side)
            list[5] = true;
        } else {
            list[4] = false;
            list[5] = false;
        }
        //System.out.println("list[5] == " + list[5]);

        return list;
    }

    public static void passBy(int faceA, int faceB, int thisZ, int thisY, int thisX) {
        boolean[] aList = new boolean[6];
        boolean[] bList = new boolean[6];
        //Arrays.fill(aList, false);
        //Arrays.fill(bList, false);

        int faceOutA = ((BASE3 - 1) - faceA);
        int faceOutB = ((BASE3 - 1) - faceB);

        aList = checkPull(faceA);
        bList = checkPull(faceB);
        /*
        for(int n = 0; n < aList.length; n++){
            System.out.println("aList[" + n + "] == " + aList[n]);
            System.out.println("bList[" + n + "] == " + bList[n]);
        }
        */

        if (faceOutA >= BASE2) { //if it's not in the top row(the y- face)
            if (bList[2] == true) { //if the force of B came from the top row and draws A up,
                faceOutA = (faceOutA - BASE2);//faceOutA moves up one y-layer.
            }
        }
        if (faceOutA < (BASE3 - BASE2)) {//if faceOutA not on the bottom face
            if (bList[3] == true) {//if force of B came from bottom face, drawing A down
                faceOutA = (faceOutA + BASE2); //faceOutA moves down one y-layer
            }
        }

        if (faceOutA % BASE != 0) {//if faceOutA not on x- face
            if (bList[4] == true) {//if B draws to x-
                faceOutA -= 1; //faceOutA moves one x-layer to the left
            }
        }
        if ((faceOutA + 1) % BASE != 0) {//if faceOutA not on x+ face
            if (bList[5] == true) {//if B draws A towards x+
                faceOutA += 1;//faceOutA moves one step x+
            }
        }

        if (faceOutA % BASE2 >= BASE) {//if faceOutA not on z- front face
            if (bList[0] == true) {//if B draws A towards z-
                faceOutA = (faceOutA - BASE);//faceOutA moves one layer z-
            }
        }
        if ((faceOutA + BASE) % BASE2 >= BASE) {//if not on rear z+ face
            if (bList[1] == true) {//if B draws A towards z+
                faceOutA = (faceOutA + BASE);//faceOutA move one layer z+
            }
        }


        //Do it for B

        if (faceOutB % BASE2 >= BASE) {//if faceOutB not on front z- face
            if (aList[0] == true) {//if A draws B towards z-
                faceOutB = (faceOutB - BASE);//faceOutB moves one layer z-
            }
        }
        if ((faceOutB + BASE) % BASE >= BASE) {//if faceOutB not on rear z+ face
            if (aList[1] == true) {//if A draws B towards z+
                faceOutB = (faceOutB + BASE);//move faceOutB on layer z+
            }
        }
        if (faceOutB >= BASE2) {//if faceOutB not on top y- face
            if (aList[2] == true) {//if A draws B towards y-
                faceOutB = (faceOutB - BASE2);//move faceOutB one layer y-
            }
        } 
        if (faceOutB < (BASE3 - BASE2)) {//if faceOutB not on bottom y+ face
            if (aList[3] == true) {//if A draws B towards y+
                faceOutB = (faceOutB + BASE2);//move faceOutB one layer y+
            }
        }

        if (faceOutB % BASE != 0) {//if not on left x- face
            if (aList[4] == true) {//if A draws B towards x-
                faceOutB = faceOutB - 1;//move faceOutB one layer x-
            }
        }
        
        if ((faceOutB + 1) % BASE != 0) {//if faceOutB not on right x+ face
            if (aList[5] == true) {//if A draws B towards x+
                faceOutB = faceOutB + 1;//move faceOutB one layer x+
            }
        }


        space[thisZ][thisY][thisX].faceIn[faceA] = false;
        space[thisZ][thisY][thisX].faceIn[faceB] = false;
        if(space[thisZ][thisY][thisX].faceOut[faceOutA] == true){
            for(int l = 0; l < BASE3; l++){
                System.out.println("l == " + l);
                if(space[thisZ][thisY][thisX].faceOut[l] == false){
                    space[thisZ][thisY][thisX].faceOut[l] = true;
                    System.out.println("Space[" + thisZ + "][" + thisY + "][" +
                            thisX + "].faceOut[" + l + "] == true");
                    break;
                }
            }
        } else {
        space[thisZ][thisY][thisX].faceOut[faceOutA] = true;
        }
        if(space[thisZ][thisY][thisX].faceOut[faceOutB] == true){
            for(int k = 0; k < BASE3; k++){
                System.out.println(" k == " + k);
                if(space[thisZ][thisY][thisX].faceOut[k] == false){
                    space[thisZ][thisY][thisX].faceOut[k] = true;
                    System.out.println("Space[" + thisZ + "][" + thisY + "][" +
                            thisX + "].faceOut[" + k + "] == true");
                    break;
                } 
            }
        } else {
        space[thisZ][thisY][thisX].faceOut[faceOutB] = true;
        }/*
        System.out.println("Space[" + thisZ + "][" + thisY + "][" + thisX + 
                "]: In: " + faceA + ", To Out: " + faceOutA + ", Instead Of: " +
                ((BASE3 - 1) - faceA) + ".");
        System.out.println("Space[" + thisZ + "][" + thisY + "][" + thisX +
                "] FaceA List: " + aList[0] + aList[1] + aList[2] + aList[3] +
                aList[4] + aList[5]);

        System.out.println("Space[" + thisZ + "][" + thisY + "][" + thisX + 
                "]: In: " + faceB + ", To Out: " + faceOutB + ", Instead Of: " +
                ((BASE3 - 1) - faceB) + ".");
        System.out.println("Space[" + thisZ + "][" + thisY + "][" + thisX +
                "] FaceB List: " + bList[0] + bList[1] + bList[2] + bList[3] +
                bList[4] + bList[5]);
                */
    }

    public void attractProcess(int z, int y, int x) {
        //cf could be made to equal math.Random() * BASE3 or whatever would give
        //a random start point, then progress from there and loop around to 
        //complte the list maybe. Or keep a random list that takes used options
        //out so the same face doesn't trigger twice in one turn.

        //I used "cf" to denote "current face". I would have just used "f",
        //but I don't know if that would cause the value from the previously
        //ran sortAttractProcess to overlap with this one.
        for (int cf = 0; cf < BASE3 - 1; cf++) {
            for (int nf = cf + 1; nf < BASE3; nf++) {
                if (space[z][y][x].faceIn[nf] == true) {
                }
            }
        }

    }

    public static void update() {

             if (loopcounter < 20) {
                 
                 space[(int) Math.floor(Math.random() * MAXZ)][(int) Math.floor(Math.random() * MAXY)][(int) Math.floor(Math.random() * MAXX)].faceIn[(int) Math.floor(Math.random() * BASE3)] = true;
             } else if(loopcounter > 1000){
                 loopcounter = 0;
             }
             loopcounter++;
             /*
                 for(int t = 0; t < BASE2; t++){
                     space[2][4][0].faceOut[t] = true;
                     
                     space[2][4][0].faceOut[(t * BASE) + 4] = true;
                     
                     space[2][4][0].faceOut[((BASE3 - 1) - t)] = true;
                 }
                 */
  //               space[3][3][3].faceOut[(int)(Math.floor(Math.random() * BASE3))] = true;
  //               loopcounter++;
  //           }
    
             /*
             if(loopcounter >= 40){
                 if(loopcounter % 10 == 0){
                 space[(int) Math.floor(Math.random() * MAXZ)][(int) Math.floor(Math.random() * MAXY)][(int) Math.floor(Math.random() * MAXX)].faceIn[(int) Math.floor(Math.random() * BASE3)] = true;
                 
                 //System.out.println("" + loopcounter);
                 loopcounter++;
                 } else {
                     loopcounter++;
                 }
                
             }
              
             if(loopcounter > 200000){
                 loopcounter = 50;
                 
             }
*/
        if (drawSwitch == true) {
            //space[0][0][0].faceOut[(int)(Math.random() * BASE3)] = true;
            //space[(int)Math.random() * MAXZ][(int)Math.random() * MAXY][(int)Math.random() * MAXX].faceOut[(int)Math.random() * BASE2] = true;
            fire3();
            //fire3();
        } else {
            // space[0][0][0].faceIn[(int)(Math.random() * BASE3)] = true;
            //space[(int)Math.floor(Math.random() * MAXZ)][(int)Math.floor(Math.random() * MAXY)][(int)Math.floor(Math.random() * MAXX)].faceIn[(int)Math.floor(Math.random() * BASE2)] = true;
            sortAttractProcess();
        }

        drawMap3(20, 40);
        flipDrawSwitch();

        frame.repaint();
        //System.out.println("loopcounter == " + loopcounter);
    }
    
    public static void drawMap4(int startX, int startY) {
        g2d.clearRect(0, 0, spaceMap.getWidth(), spaceMap.getHeight());
        int drawX = startX;
        int drawY = startY + 12;
        int q = 0;

        for (int y = 0; y < MAXY; y++) {

            for (int i = 0; i < (BASE); i++) {
                for (int z = MAXZ - 1; z >= 0; z--) {
                    for (int x = 0; x < MAXX; x++) {
                        for (int j = (i * BASE2); j < (i * BASE2) + BASE2; j++) {
                            //q = space[z][y][x].faceIn[j] ? 35 : (64 + j);
                            //g2d.setColor(Color.DARK_GRAY);
                            //g2d.drawRect(drawX + ((x * (BASE2 * BASE2)) + ((j % BASE2) * (MAXZ + 1))), drawY, MAXZ + 1, MAXZ + 1);
                            //g2d.setColor(Color.BLUE);
                            //g2d.drawLine(0, (drawY - (MAXZ + 4) * i) - 1, 1500, (drawY - (MAXZ + 4) * i) - 1);
                            //g2d.drawLine(drawX + (x * 90) - 5, 0, drawX + (x * 90) - 5, 800);
                            q = space[z][y][x].faceIn[j] ? 35 : 32;

                            if (q == 35) {
                                g2d.setColor(Color.CYAN);
                                g2d.drawRect(drawX + ((x * (BASE2 * BASE2)) + ((j % BASE2) * (MAXZ + 1))), drawY, z + 1, z + 1);
                            }
                            //g2d.drawRect(drawX + ((x * 120) + ((j % BASE2) * 12)), drawY, 12, 7);
                            //q = space[z][y][x].faceOut[j] ? 35 : (64 + j);
                            q = space[z][y][x].faceOut[j] ? 35 : (32);
                            if (q == 35) {
                                g2d.setColor(Color.ORANGE);
                                g2d.drawRect(drawX + ((x * (BASE2 * BASE2)) + ((j % BASE2) * (MAXZ + 1))), drawY, z + 1, z + 1);
                            }

                        }
                    }
                    // drawY += 7;
                }
                drawY += MAXZ + 4;
            }
        }
        g2d.setColor(Color.CYAN);

    }

    public static void flipDrawSwitch() {
        if (drawSwitch == true) {
            drawSwitch = false;
        } else {
            drawSwitch = true;
        }
    }

    public static void processSpace() {
        for (int z = 0; z < MAXZ; z++) {
            for (int y = 0; y < MAXY; y++) {
                for (int x = 0; x < MAXX; x++) {
                    space[z][y][x].passThru();
                    /*
                     for (int f = 0; f < BASE3; f++) {
                     if (space[z][y][x].faceIn[f] == true) {
                     space[z][y][x].faceIn[f] = false;
                     space[z][y][x].faceOut[(BASE3 - 1) - f] = true;
                     // System.out.println("Now processing: \n  space[" + z + "][" + y + "][" + x + "].faceIn[" + f + "] to: \n  "
                     //         + "space[" + z + "][" + y + "][" + x + "].faceOut[" + ((BASE3 - 1) - f) + "]");
                     }
                     }
                     */
                }
            }
        }
    }

    public static void clearAll() {
        for (int z = 0; z < MAXZ; z++) {
            for (int y = 0; y < MAXY; y++) {
                for (int x = 0; x < MAXX; x++) {
                    java.util.Arrays.fill(space[z][y][x].faceIn, false);
                    java.util.Arrays.fill(space[z][y][x].faceOut, false);
                }
            }
        }
    }

    public static void clearMap(int thisX, int thisY) {
        //g2d = spaceMap.createGraphics();
        g2d.clearRect(thisX, thisY, 750, 338);
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.drawLine(thisX, thisY, thisX + 750, thisY);
        g2d.drawLine(thisX + 750, thisY, thisX + 750, thisY + 338);
        g2d.drawLine(thisX, thisY, thisX, thisY + 338);
        g2d.drawLine(thisX, thisY + 338, thisX + 750, thisY + 338);
    }

    public static void drawMap3(int startX, int startY) {
        g2d.clearRect(0, 0, spaceMap.getWidth(), spaceMap.getHeight());
        int drawX = startX;
        int drawY = startY + 12;
        int q = 0;

        for (int y = 0; y < MAXY; y++) {

            for (int i = 0; i < (BASE); i++) {
                for (int z = MAXZ - 1; z >= 0; z--) {
                    for (int x = 0; x < MAXX; x++) {
                        for (int j = (i * BASE2); j < (i * BASE2) + BASE2; j++) {
                            //q = space[z][y][x].faceIn[j] ? 35 : (64 + j);
                            //g2d.setColor(Color.DARK_GRAY);
                            //g2d.drawRect(drawX + ((x * (BASE2 * BASE2)) + ((j % BASE2) * (MAXZ + 1))), drawY, MAXZ + 1, MAXZ + 1);
                            //g2d.setColor(Color.BLUE);
                            //g2d.drawLine(0, (drawY - (MAXZ + 4) * i) - 1, 1500, (drawY - (MAXZ + 4) * i) - 1);
                            //g2d.drawLine(drawX + (x * 90) - 5, 0, drawX + (x * 90) - 5, 800);
                            if (drawSwitch == true) {
                                q = space[z][y][x].faceIn[j] ? 35 : 32;
                            } else {
                                q = space[z][y][x].faceOut[j] ? 35 : 32;
                            }
                            // q = space[z][y][x].faceIn[j] ? 35 : 32;

                            //test draw for 5x5x5 on 3-29-2014
                            if (q == 35) {

                                if (drawSwitch == true) {
                                    g2d.setColor(Color.CYAN);
                                } else {
                                    g2d.setColor(Color.ORANGE);
                                }
                                g2d.drawRect(drawX + ((((((MAXZ * 2) - 1) * BASE2) + (BASE2 * 3 + 10)) * x)) + ((j % BASE2) * ((MAXZ * 2) + 2)) + z,
                                        drawY + z, ((MAXZ * 2) - 1) - (z * 2), ((MAXZ * 2) - 1) - (z * 2));
                            } else {
                                g2d.setColor(Color.BLACK);
                                g2d.drawRect(drawX + ((((((MAXZ * 2) - 1) * BASE2) + (BASE2 * 3 + 10)) * x)) + ((j % BASE2) * ((MAXZ * 2) + 2)) + z,
                                        drawY + z, ((MAXZ * 2) - 1) - (z * 2), ((MAXZ * 2) - 1) - (z * 2));
                            }
                            //end test
                            /*
                             if (q == 35) {
                             g2d.setColor(Color.CYAN);
                             g2d.drawRect(drawX + ((x * (BASE2 * BASE2)) + ((j % BASE2) * (MAXZ + 1))), drawY, z + 1, z + 1);
                             } else {
                             g2d.setColor(Color.GRAY);
                             g2d.drawRect(drawX + ((x * (BASE2 * BASE2)) + ((j % BASE2) * (MAXZ + 1))), drawY, z + 1, z + 1);
                             }
                             */
                            //g2d.drawRect(drawX + ((x * 120) + ((j % BASE2) * 12)), drawY, 12, 7);
                            //q = space[z][y][x].faceOut[j] ? 35 : (64 + j);
                            /*
                             q = space[z][y][x].faceOut[j] ? 35 : (32);
                             if (q == 35) {
                             g2d.setColor(Color.ORANGE);
                             g2d.drawRect(drawX + ((x * (BASE2 * BASE2)) + ((j % BASE2) * (MAXZ + 1))), drawY, z + 1, z + 1);
                             } else 
                             g2d.setColor(Color.GRAY);
                             g2d.drawRect(drawX + ((x * (BASE2 * BASE2)) + ((j % BASE2) * (MAXZ + 1))), drawY, z + 1, z + 1);
                             }
                             */


                        }
                        // drawY += 7;
                    }
                    //drawY += MAXZ * 2;
                }
                drawY += (MAXZ * 2) + 2;
            }
            //g2d.setColor(Color.CYAN);
            drawY += 10;
        }
    }

    public static void start() {
        loop = new Thread();
        loop.start();
    }

    public void run() {
        Thread t = Thread.currentThread();

        while (t == loop) {
            try {
                update();
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void initialize() {

        for (int i = 0; i < MAXZ; i++) {
            for (int j = 0; j < MAXY; j++) {
                for (int k = 0; k < MAXX; k++) {
                    space[i][j][k] = new Space(BASE);
                }
            }
        }
    }

    public static void reboundSpace(int a, int z, int y, int x) {
        if (space[z][y][x].faceIn[a] == false) {
            space[z][y][x].faceIn[a] = true;
        }
        /*
         if(z == 0){
         if(y == 0){
         if(x == 0){
         //z0, y0, x0
         //face code here
                    
         } else if (x == MAXX - 1){
         //z0, y0, x∞
         //face code here
         } else {
         //z0, y0, x~
         //face code here
         }
         } else if (y == MAXY - 1){
         if(x == 0){
         //z0, y∞, x0
         } else if (x == MAXX - 1){
         //z0, y∞, x∞
         } else {
         //z0, y∞, x~
         }
         } else {
         if(x == 0){
         //z0, y~, x0
         } else if (x == MAXX - 1){
         //z0, y~, x∞
         } else {
         //z0, y~, x~
         }
         }
         } else if (z == MAXZ - 1){
         if(y == 0){
         if(x == 0){
         //z∞, y0, x0
         } else if (x == MAXX - 1){
         //z∞, y0, x∞
         } else {
         //z∞, y0, x~
         }
         } else if (y == MAXY - 1){
         if(x == 0){
         //z∞, y∞, x0
         } else if (x == MAXX - 1){
         //z∞, y∞, x∞
         } else {
         //z∞, y∞, x~
         }
         } else {
         if(x == 0){
         //z∞, y~, x0
         } else if (x == MAXX - 1){
         //z∞, y~, x∞
         } else {
         //z∞, y~, x~
         }
         }
         } else {
         if(y == 0){
         if(x == 0){
         //z~, y0, x0
         } else if (x == MAXX - 1){
         //z~, y0, x∞
         } else {
         //z~, y0, x~
         }
         } else if (y == MAXY - 1){
         if(x == 0){
         //z~, y∞, x0
         } else if (x == MAXX - 1){
         //z~, y∞, x∞
         } else {
         //z~, y∞, x~
         }
         } else {
         if(x == 0){
         //z~, y~, x0
         } else if (x == MAXX - 1){
         //z~, y~, x∞
         } else {
         //z~, y~, x~
         }
         }
         }
         /*
         if (space[z][y][x].faceIn[a] == false) {
         space[z][y][x].faceIn[a] = true;
         }
         /* 
         } else {
         for (int c = 1; c <= (BASE3 - 1) - a; c++) {
         System.out.println("Count = " + c + " for space[" + z + "][" + y + "][" + x + "].faceOut[" + a + "]");
         if (space[z][y][x].faceIn[(BASE3 - 1) - (a + c)] == false) {
         space[z][y][x].faceOut[(BASE3 - 1) - (a + c)] = true;
         return;
         } else if (space[z][y][x].faceIn[(BASE3 - 1) - ((BASE3 - 1) - (a + c))] == false) {
         space[z][y][x].faceOut[(BASE3 - 1) - ((BASE3 - 1) - (a + c))] = true;
         return;
         } else {
         space[0][0][0].faceOut[(int) (Math.random() * BASE3)] = true;
         return;
         }

         }
         }
         */
    }

    public static void suck() {
        for (int z = 0; z < MAXZ; z++) {
            if (z == 0) {
                for (int y = 0; y < MAXY; y++) {
                    if (y == 0) {
                        for (int x = 0; x < MAXX; x++) {
                            if (x == 0) {
                                for (int f = (BASE3 - 1); f >= 0; f--) {
                                }
                            }
                        }
                    }
                }
            }
        }
    }
/*
    public static void fire5() {
        int modz = 0;
        int mody = 0;
        int modx = 0;
        int boff = (BASE - (BASE % 2)) / 2; //Base Offset - The number of layers from the center to the outside. In the case of BASE 5, the offset would be 2. BASE 7 would have 3.


        for (int z = 0; z < MAXZ; z++) {
            if (z == 0) {
                for (int y = 0; y < MAXY; y++) {
                    if (y == 0) {
                        for (int x = 0; x < MAXX; x++) {
                            if (x == 0) {
                                //z0,y0,x0
                                for (int f = 0; f < BASE3; f++) {
                                    if (f <) {
                                    }
                                }
                            } else if (x == MAXX - 1) {
                                //z0,y0,x!
                                for (int f = 0; f < BASE3; f++) {
                                }
                            } else {
                                //z0,y0,x~
                                for (int f = 0; f < BASE3; f++) {
                                }
                            }
                        }
                    } else if (y == MAXY - 1) {
                        for (int x = 0; x < MAXX; x++) {
                            if (x == 0) {
                                //z0,y!,x0
                                for (int f = 0; f < BASE3; f++) {
                                }
                            } else if (x == MAXX - 1) {
                                //z0,y!,x!
                                for (int f = 0; f < BASE3; f++) {
                                }
                            } else {
                                //z0,y!,x~
                                for (int f = 0; f < BASE3; f++) {
                                }
                            }
                        }
                    } else {
                        for (int x = 0; x < MAXX; x++) {
                            if (x == 0) {
                                //z0,y~,x0
                                for (int f = 0; f < BASE3; f++) {
                                }
                            } else if (x == MAXX - 1) {
                                //z0,y~,x!
                                for (int f = 0; f < BASE3; f++) {
                                }
                            } else {
                                //z0,y~,x~
                                for (int f = 0; f < BASE3; f++) {
                                }
                            }
                        }
                    }
                }
            } else if (z == MAXZ - 1) {
                for (int y = 0; y < MAXY; y++) {
                    if (y == 0) {
                        for (int x = 0; x < MAXX; x++) {
                            if (x == 0) {
                                //z!,y0,x0
                                for (int f = 0; f < BASE3; f++) {
                                }
                            } else if (x == MAXX - 1) {
                                //z!,y0,x!
                                for (int f = 0; f < BASE3; f++) {
                                }
                            } else {
                                //z!,y0,x~
                                for (int f = 0; f < BASE3; f++) {
                                }
                            }
                        }
                    } else if (y == MAXY - 1) {
                        for (int x = 0; x < MAXX; x++) {
                            if (x == 0) {
                                //z!,y!,x0
                                for (int f = 0; f < BASE3; f++) {
                                }
                            } else if (x == MAXX - 1) {
                                //z!,y!,x!
                                for (int f = 0; f < BASE3; f++) {
                                }
                            } else {
                                //z!,y!,x~
                                for (int f = 0; f < BASE3; f++) {
                                }
                            }
                        }
                    } else {
                        for (int x = 0; x < MAXX; x++) {
                            if (x == 0) {
                                //z!,y~,x0
                                for (int f = 0; f < BASE3; f++) {
                                }
                            } else if (x == MAXX - 1) {
                                //z!,y~,x!
                                for (int f = 0; f < BASE3; f++) {
                                }
                            } else {
                                //z!,y~,x~
                                for (int f = 0; f < BASE3; f++) {
                                }
                            }
                        }
                    }
                }
            } else {
                for (int y = 0; y < MAXY; y++) {
                    if (y == 0) {
                        for (int x = 0; x < MAXX; x++) {
                            if (x == 0) {
                                //z~,y0,x0
                                for (int f = 0; f < BASE3; f++) {
                                }
                            } else if (x == MAXX - 1) {
                                //z~,y0,x!
                                for (int f = 0; f < BASE3; f++) {
                                }
                            } else {
                                //z~,y0,x~
                                for (int f = 0; f < BASE3; f++) {
                                }
                            }
                        }
                    } else if (y == MAXY - 1) {
                        for (int x = 0; x < MAXX; x++) {
                            if (x == 0) {
                                //z~,y!,x0
                                for (int f = 0; f < BASE3; f++) {
                                }
                            } else if (x == MAXX - 1) {
                                //z~,y!,x!
                                for (int f = 0; f < BASE3; f++) {
                                }
                            } else {
                                //z~,y!,x~
                                for (int f = 0; f < BASE3; f++) {
                                }
                            }
                        }
                    } else {
                        for (int x = 0; x < MAXX; x++) {
                            if (x == 0) {
                                //z~,y~,x0
                                for (int f = 0; f < BASE3; f++) {
                                }
                            } else if (x == MAXX - 1) {
                                //z~,y~,x!
                                for (int f = 0; f < BASE3; f++) {
                                }
                            } else {
                                //z~,y~,x~
                                for (int f = 0; f < BASE3; f++) {
                                }
                            }
                        }
                    }
                }
            }
        }
    }
*/
    
    
    
    public static void fire3() {
        int modz = 0;
        int mody = 0;
        int modx = 0;

        for (int z = 0; z < MAXZ; z++) {
            if (z == 0) {
                for (int y = 0; y < MAXY; y++) {
                    if (y == 0) {
                        for (int x = 0; x < MAXX; x++) {
                            for (int i = 0; i < BASE3; i++) {
                                if (space[z][y][x].faceOut[i] == true) {
                                    //System.out.println("Now processing space[" + z + "][" + y + "][" + x + "].faceOut[" + i + "].");
                                    space[z][y][x].faceOut[i] = false;
                                    if (x == 0) {
                                        //code here for z0,y0,x0
                                        //mody
                                        if (i < BASE2) { //y-
                                            mody = 0;
                                        } else if (i >= (BASE3 - BASE2)) { //y+
                                            mody = 1;
                                        } else {
                                            mody = 0;
                                        }

                                        //modx
                                        if ((i % BASE) == 0) { //x-
                                            modx = 0;
                                        } else if ((i + 1) % BASE == 0) { //x+
                                            modx = 1;
                                        } else {
                                            modx = 0;
                                        }

                                        //modz
                                        if (i % BASE2 < BASE) { //z-
                                            modz = 0;
                                        } else if ((i + BASE) % BASE2 < BASE) { //z+
                                            modz = 1;
                                        } else {
                                            modz = 0;
                                        }

                                        if (i % BASE2 < BASE) {
                                            if (i < BASE) {
                                                if (i % BASE == 0) {
                                                    space[z][y][x].faceOut[i] = false;
                                                    space[z][y][x].faceIn[i] = true;

                                                } else if ((i + 1) % BASE == 0) {

                                                    if (space[z + modz][y + mody][x + modx].faceOut[(BASE - 1) - i] == false) {
                                                        space[z + modz][y + mody][x + modx].faceIn[(BASE - 1) - i] = true;
                                                    } else {
                                                        reboundSpace(i, z, y, x);
                                                    }
                                                } else if (i == (BASE - 1) - i) {
                                                    space[z][y][x].faceOut[i] = false;
                                                    space[z][y][x].faceIn[i] = true;
                                                } else {
                                                    if (space[z][y][x].faceOut[(BASE3 - 1) - i] == false) {
                                                        space[z][y][x].faceIn[(BASE3 - 1) - i] = true;
                                                    } else {
                                                        reboundSpace(i, z, y, x);
                                                    }
                                                }
                                            } else if (i % BASE == 0) {
                                                if (i == (BASE3 - BASE2)) {
                                                    if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - BASE2) - i] == false) {
                                                        space[z + modz][y + mody][x + modx].faceIn[(BASE3 - BASE2) - i] = true;
                                                    } else {
                                                        reboundSpace(i, z, y, x);
                                                    }
                                                } else if (i == (BASE3 - BASE2) - i) {
                                                    space[z][y][x].faceIn[i] = true;
                                                } else {
                                                    if (space[z][y][x].faceOut[(BASE3 - BASE2) - i] == false) {
                                                        space[z][y][x].faceIn[(BASE3 - BASE2) - i] = true;
                                                    } else {
                                                        reboundSpace(i, z, y, x);
                                                    }
                                                }
                                            } else if (i == (BASE3 - BASE2) + (BASE - 1) - i) {
                                                space[z][y][x].faceIn[i] = true;
                                            } else {
                                                if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - BASE2) + (BASE - 1) - i] == false) {
                                                    space[z + modz][y + mody][x + modx].faceIn[(BASE3 - BASE2) + (BASE - 1) - i] = true;
                                                } else {
                                                    reboundSpace(i, z, y, x);
                                                }
                                            }
                                        } else if (i < BASE2) {
                                            if (i % BASE == 0) {
                                                if (i == (BASE2 - BASE) - i) {
                                                    space[z][y][x].faceIn[i] = true;


                                                } else {

                                                    if (space[z + modz][y + mody][x + modx].faceOut[(BASE2 - BASE) - i] == false) {
                                                        space[z + modz][y + mody][x + modx].faceIn[(BASE2 - BASE) - i] = true;
                                                    } else {
                                                        reboundSpace(i, z, y, x);
                                                    }
                                                }
                                            } else if (i == (BASE2 - 1) - i) {
                                                space[z][y][x].faceIn[i] = true;
                                                if (space[z + modz][y + mody][x + modx].faceOut[(BASE2 - 1) - i] == false) {
                                                    space[z + modz][y + mody][x + modx].faceIn[(BASE2 - 1) - i] = true;
                                                } else {
                                                    reboundSpace(i, z, y, x);
                                                }
                                            } else {
                                                if (space[z + modz][y + mody][x + modx].faceOut[(BASE2 - 1) - i] == false) {
                                                    space[z + modz][y + mody][x + modx].faceIn[(BASE2 - 1) - i] = true;
                                                } else {
                                                    reboundSpace(i, z, y, x);
                                                }
                                            }
                                        } else if (i % BASE == 0) {
                                            if (i == (BASE3 - BASE) - i) {
                                                space[z][y][x].faceIn[i] = true;

                                            } else {
                                                if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - BASE) - i] == false) {
                                                    space[z + modz][y + mody][x + modx].faceIn[(BASE3 - BASE) - i] = true;
                                                } else {
                                                    reboundSpace(i, z, y, x);
                                                }
                                            }
                                        } else {
                                            if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - 1) - i] == false) {
                                                space[z + modz][y + mody][x + modx].faceIn[(BASE3 - 1) - i] = true;
                                            } else {
                                                reboundSpace(i, z, y, x);
                                            }
                                        }
                                    } else if (x == MAXX - 1) {
                                        //code here for z0,y0,x∞
                                        //mody
                                        if (i < BASE2) { //y-
                                            mody = 0;
                                        } else if (i >= BASE3 - BASE2) { //y+
                                            mody = 1;
                                        } else {
                                            mody = 0;
                                        }

                                        //modx
                                        if (i % BASE == 0) { //x-
                                            modx = -1;
                                        } else if ((i + 1) % BASE == 0) { //x+
                                            modx = 0;
                                        } else {
                                            modx = 0;
                                        }

                                        //modz
                                        if (i % BASE2 < BASE) { //z-
                                            modz = 0;
                                        } else if ((i + BASE) % BASE2 < BASE) { //z+
                                            modz = 1;
                                        } else {
                                            modz = 0;
                                        }

                                        if (i % BASE2 < BASE) {
                                            if (i < BASE2) {
                                                if ((i + 1) % BASE == 0) {
                                                    if (space[z + modz][y + mody][x + modx].faceOut[i] == false) {
                                                        space[z + modz][y + mody][x + modx].faceIn[i] = true;
                                                    } else {
                                                        reboundSpace(i, z, y, x);
                                                    }
                                                } else {
                                                    if (space[z + modz][y + mody][x + modx].faceOut[(BASE - 1) - i] == false) {
                                                        space[z + modz][y + mody][x + modx].faceIn[(BASE - 1) - i] = true;
                                                    } else {
                                                        reboundSpace(i, z, y, x);
                                                    }
                                                }
                                            } else if ((i + 1) % BASE == 0) {
                                                if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - BASE2) + ((BASE - 1) * 2) - i] == false) {
                                                    space[z + modz][y + mody][x + modx].faceIn[(BASE3 - BASE2) + ((BASE - 1) * 2) - i] = true;
                                                } else {
                                                    reboundSpace(i, z, y, x);
                                                }
                                            } else {
                                                if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - BASE2) + (BASE - 1) - i] == false) {
                                                    space[z + modz][y + mody][x + modx].faceIn[(BASE3 - BASE2) + (BASE - 1) - i] = true;
                                                } else {
                                                    reboundSpace(i, z, y, x);
                                                }
                                            }
                                        } else if (i < BASE2) {
                                            if ((i + 1) % BASE == 0) {
                                                if (space[z + modz][y + mody][x + modx].faceOut[(BASE2 - 1) + (BASE - 1) - i] == false) {
                                                    space[z + modz][y + mody][x + modx].faceIn[(BASE2 - 1) + (BASE - 1) - i] = true;
                                                } else {
                                                    reboundSpace(i, z, y, x);
                                                }
                                            } else {
                                                if (space[z + modz][y + mody][x + modx].faceOut[(BASE2 - 1) - i] == false) {
                                                    space[z + modz][y + mody][x + modx].faceIn[(BASE2 - 1) - i] = true;
                                                } else {
                                                    reboundSpace(i, z, y, x);
                                                }
                                            }
                                        } else if ((i + 1) % BASE == 0) {
                                            if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - 1) + (BASE - 1) - i] == false) {
                                                space[z + modz][y + mody][x + modx].faceIn[(BASE3 - 1) + (BASE - 1) - i] = true;
                                            } else {
                                                reboundSpace(i, z, y, x);
                                            }
                                        } else {
                                            if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - 1) - i] == false) {
                                                space[z + modz][y + mody][x + modx].faceIn[(BASE3 - 1) - i] = true;
                                            } else {
                                                reboundSpace(i, z, y, x);
                                            }
                                        }
                                    } else {
                                        //code here for z0,y0,x~
                                        //mody
                                        if (i < BASE2) { //y-
                                            mody = 0;
                                        } else if (i >= BASE3 - BASE2) { //y+
                                            mody = 1;
                                        } else {
                                            mody = 0;
                                        }

                                        //modx
                                        if (i % BASE == 0) { //x-
                                            modx = -1;
                                        } else if ((i + 1) % BASE == 0) { //x+
                                            modx = 1;
                                        } else {
                                            modx = 0;
                                        }

                                        //modz
                                        if (i % BASE2 < BASE) { //z-
                                            modz = 0;
                                        } else if ((i + BASE) % BASE2 < BASE) { //z+
                                            modz = 1;
                                        } else {
                                            modz = 0;
                                        }

                                        if (i % BASE2 < BASE) {
                                            if (i < BASE2) {
                                                if (space[z + modz][y + mody][x + modx].faceOut[(BASE - 1) - i] == false) {
                                                    space[z + modz][y + mody][x + modx].faceIn[(BASE - 1) - i] = true;
                                                } else {
                                                    reboundSpace(i, z, y, x);
                                                }
                                            } else {
                                                if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - BASE2) + (BASE - 1) - i] == false) {
                                                    space[z + modz][y + mody][x + modx].faceIn[(BASE3 - BASE2) + (BASE - 1) - i] = true;
                                                } else {
                                                    reboundSpace(i, z, y, x);
                                                }
                                            }
                                        } else if (i < BASE2) {
                                            if (space[z + modz][y + mody][x + modx].faceOut[(BASE2 - 1) - i] == false) {
                                                space[z + modz][y + mody][x + modx].faceIn[(BASE2 - 1) - i] = true;
                                            } else {
                                                reboundSpace(i, z, y, x);

                                            }
                                        } else {
                                            if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - 1) - i] == false) {
                                                space[z + modz][y + mody][x + modx].faceIn[(BASE3 - 1) - i] = true;
                                            } else {
                                                reboundSpace(i, z, y, x);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } else if (y == MAXY - 1) {
                        for (int x = 0; x < MAXX; x++) {
                            for (int i = 0; i < BASE3; i++) {
                                if (space[z][y][x].faceOut[i] == true) {
                                    //System.out.println("Now processing space[" + z + "][" + y + "][" + x + "].faceOut[" + i + "].");
                                    space[z][y][x].faceOut[i] = false;
                                    if (x == 0) {
                                        //code here for z0,y∞,x0
                                        //mody
                                        if (i < BASE2) { //y-
                                            mody = -1;
                                        } else if (i >= BASE3 - BASE2) { //y+
                                            mody = 0;
                                        } else {
                                            mody = 0;
                                        }

                                        //modx
                                        if (i % BASE == 0) { //x-
                                            modx = 0;
                                        } else if ((i + 1) % BASE == 0) { //x+
                                            modx = 1;
                                        } else {
                                            modx = 0;
                                        }

                                        //modz
                                        if (i % BASE2 < BASE) { //z-
                                            modz = 0;
                                        } else if ((i + BASE) % BASE2 < BASE) { //z+
                                            modz = 1;
                                        } else {
                                            modz = 0;
                                        }

                                        if (i % BASE2 < BASE) {
                                            if (i % BASE == 0) {
                                                if (i >= BASE3 - BASE2) {
                                                    if (space[z + modz][y + mody][x + modx].faceIn[i] == false) {
                                                        space[z + modz][y + mody][x + modx].faceIn[i] = true;
                                                    } else {
                                                        reboundSpace(i, z, y, x);
                                                    }
                                                } else {
                                                    if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - BASE2) - i] == false) {
                                                        space[z + modz][y + mody][x + modx].faceIn[(BASE3 - BASE2) - i] = true;
                                                    } else {
                                                        reboundSpace(i, z, y, x);
                                                    }
                                                }
                                            } else if (i > BASE3 - BASE2) {
                                                if (space[z + modz][y + mody][x + modx].faceOut[((BASE3 - BASE2) * 2) + (BASE - 1) - i] == false) {
                                                    space[z + modz][y + mody][x + modx].faceIn[((BASE3 - BASE2) * 2) + (BASE - 1) - i] = true;
                                                } else {
                                                    reboundSpace(i, z, y, x);
                                                }
                                            } else {
                                                if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - BASE2) + (BASE - 1) - i] == false) {
                                                    space[z + modz][y + mody][x + modx].faceIn[(BASE3 - BASE2) + (BASE - 1) - i] = true;
                                                } else {
                                                    reboundSpace(i, z, y, x);
                                                }
                                            }
                                        } else if (i % BASE == 0) {
                                            if (i > BASE3 - BASE2) {
                                                if (space[z + modz][y + mody][x + modx].faceOut[((BASE3 - BASE2) * 2) + (BASE2 - BASE) - i] == false) {
                                                    space[z + modz][y + mody][x + modx].faceIn[((BASE3 - BASE2) * 2) + (BASE2 - BASE) - i] = true;
                                                } else {
                                                    reboundSpace(i, z, y, x);
                                                }
                                            } else {
                                                if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - BASE) - i] == false) {
                                                    space[z + modz][y + mody][x + modx].faceIn[(BASE3 - BASE) - i] = true;
                                                } else {
                                                    reboundSpace(i, z, y, x);
                                                }
                                            }
                                        } else if (i > BASE3 - BASE2) {
                                            if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - 1) + (BASE3 - BASE2) - i] == false) {
                                                space[z + modz][y + mody][x + modx].faceIn[(BASE3 - 1) + (BASE3 - BASE2) - i] = true;
                                            } else {
                                                reboundSpace(i, z, y, x);
                                            }
                                        } else {
                                            if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - 1) - i] == false) {
                                                space[z + modz][y + mody][x + modx].faceIn[(BASE3 - 1) - i] = true;
                                            } else {
                                                reboundSpace(i, z, y, x);
                                            }

                                        }
                                    } else if (x == MAXX - 1) {
                                        //code here for z0,y∞,x∞
                                        //mody
                                        if (i < BASE2) { //y-
                                            mody = -1;
                                        } else if (i >= BASE3 - BASE2) { //y+
                                            mody = 0;
                                        } else {
                                            mody = 0;
                                        }

                                        //modx
                                        if (i % BASE == 0) { //x-
                                            modx = -1;
                                        } else if ((i + 1) % BASE == 0) { //x+
                                            modx = 0;
                                        } else {
                                            modx = 0;
                                        }

                                        //modz
                                        if (i % BASE2 < BASE) { //z-
                                            modz = 0;
                                        } else if ((i + BASE) % BASE2 < BASE) { //z+
                                            modz = 1;
                                        } else {
                                            modz = 0;
                                        }

                                        if (i % BASE2 < BASE) {
                                            if ((i + 1) % BASE == 0) {
                                                if (i > BASE3 - BASE2) {
                                                    if (space[z + modz][y + mody][x + modx].faceIn[i] == false) {
                                                        space[z + modz][y + mody][x + modx].faceIn[i] = true;
                                                    } else {
                                                        reboundSpace(i, z, y, x);
                                                    }

                                                } else {
                                                    if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - BASE2) + ((BASE - 1) * 2) - i] == false) {
                                                        space[z + modz][y + mody][x + modx].faceIn[(BASE3 - BASE2) + ((BASE - 1) * 2) - i] = true;
                                                    } else {
                                                        reboundSpace(i, z, y, x);
                                                    }

                                                }
                                            } else if (i >= BASE3 - BASE2) {
                                                if (space[z + modz][y + mody][x + modx].faceOut[((BASE3 - BASE2) * 2) + (BASE - 1) - i] == false) {
                                                    space[z + modz][y + mody][x + modx].faceIn[((BASE3 - BASE2) * 2) + (BASE - 1) - i] = true;
                                                } else {
                                                    reboundSpace(i, z, y, x);
                                                }

                                            } else {
                                                if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - BASE2) + (BASE - 1) - i] == false) {
                                                    space[z + modz][y + mody][x + modx].faceIn[(BASE3 - BASE2) + (BASE - 1) - i] = true;
                                                } else {
                                                    reboundSpace(i, z, y, x);
                                                }

                                            }
                                        } else if ((i + 1) % BASE == 0) {
                                            if (i > BASE3 - BASE2) {
                                                if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - 1) + ((BASE3 - BASE2) + (BASE - 1)) - i] == false) {
                                                    space[z + modz][y + mody][x + modx].faceIn[(BASE3 - 1) + ((BASE3 - BASE2) + (BASE - 1)) - i] = true;
                                                } else {
                                                    reboundSpace(i, z, y, x);
                                                }

                                            } else {
                                                if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - 1) + (BASE - 1) - i] == false) {
                                                    space[z + modz][y + mody][x + modx].faceIn[(BASE3 - 1) + (BASE - 1) - i] = true;
                                                } else {
                                                    reboundSpace(i, z, y, x);
                                                }

                                            }
                                        } else if (i >= BASE3 - BASE2) {
                                            if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - BASE2) + (BASE3 - 1) - i] == false) {
                                                space[z + modz][y + mody][x + modx].faceIn[(BASE3 - BASE2) + (BASE3 - 1) - i] = true;
                                            } else {
                                                reboundSpace(i, z, y, x);
                                            }

                                        } else {
                                            if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - 1) - i] == false) {
                                                space[z + modz][y + mody][x + modx].faceIn[(BASE3 - 1) - i] = true;
                                            } else {
                                                reboundSpace(i, z, y, x);
                                            }

                                        }
                                    } else {
                                        //code here for z0,y∞,x~
                                        //mody
                                        if (i < BASE2) { //y-
                                            mody = -1;
                                        } else if (i >= BASE3 - BASE2) { //y+
                                            mody = 0;
                                        } else {
                                            mody = 0;
                                        }

                                        //modx
                                        if (i % BASE == 0) { //x-
                                            modx = -1;
                                        } else if ((i + 1) % BASE == 0) { //x+
                                            modx = 1;
                                        } else {
                                            modx = 0;
                                        }

                                        //modz
                                        if (i % BASE2 < BASE) { //z-
                                            modz = 0;
                                        } else if ((i + BASE) % BASE2 < BASE) { //z+
                                            modz = 1;
                                        } else {
                                            modz = 0;
                                        }

                                        if (i % BASE2 < BASE) {
                                            if (i >= BASE3 - BASE2) {
                                                if (space[z + modz][y + mody][x + modx].faceOut[((BASE3 - BASE2) * 2) + (BASE - 1) - i] == false) {
                                                    space[z + modz][y + mody][x + modx].faceIn[((BASE3 - BASE2) * 2) + (BASE - 1) - i] = true;
                                                } else {
                                                    reboundSpace(i, z, y, x);
                                                }

                                            } else {
                                                if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - BASE2) + (BASE - 1) - i] == false) {
                                                    space[z + modz][y + mody][x + modx].faceIn[(BASE3 - BASE2) + (BASE - 1) - i] = true;
                                                } else {
                                                    reboundSpace(i, z, y, x);
                                                }

                                            }
                                        } else if (i > BASE3 - BASE2) {
                                            if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - BASE2) + (BASE3 - 1) - i] == false) {
                                                space[z + modz][y + mody][x + modx].faceIn[(BASE3 - BASE2) + (BASE3 - 1) - i] = true;
                                            } else {
                                                reboundSpace(i, z, y, x);
                                            }

                                        } else {
                                            if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - 1) - i] == false) {
                                                space[z + modz][y + mody][x + modx].faceIn[(BASE3 - 1) - i] = true;
                                            } else {
                                                reboundSpace(i, z, y, x);
                                            }

                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        for (int x = 0; x < MAXX; x++) {
                            for (int i = 0; i < BASE3; i++) {
                                if (space[z][y][x].faceOut[i] == true) {
                                    //System.out.println("Now processing space[" + z + "][" + y + "][" + x + "].faceOut[" + i + "].");
                                    space[z][y][x].faceOut[i] = false;
                                    if (x == 0) {
                                        //code here for z0,y~,x0
                                        //mody
                                        if (i < BASE2) { //y-
                                            mody = -1;
                                        } else if (i >= BASE3 - BASE2) { //y+
                                            mody = 1;
                                        } else {
                                            mody = 0;
                                        }

                                        //modx
                                        if (i % BASE == 0) { //x-
                                            modx = 0;
                                        } else if ((i + 1) % BASE == 0) { //x+
                                            modx = 1;
                                        } else {
                                            modx = 0;
                                        }

                                        //modz
                                        if (i % BASE2 < BASE) { //z-
                                            modz = 0;
                                        } else if ((i + BASE) % BASE2 < BASE) { //z+
                                            modz = 1;
                                        } else {
                                            modz = 0;
                                        }

                                        if (i % BASE2 < BASE) {
                                            if (i % BASE == 0) {
                                                if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - BASE2) - i] == false) {
                                                    space[z + modz][y + mody][x + modx].faceIn[(BASE3 - BASE2) - i] = true;
                                                } else {
                                                    reboundSpace(i, z, y, x);
                                                }

                                            } else {
                                                if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - BASE2) + (BASE - 1) - i] == false) {
                                                    space[z + modz][y + mody][x + modx].faceIn[(BASE3 - BASE2) + (BASE - 1) - i] = true;
                                                } else {
                                                    reboundSpace(i, z, y, x);
                                                }

                                            }
                                        } else if (i % BASE == 0) {
                                            if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - BASE) - i] == false) {
                                                space[z + modz][y + mody][x + modx].faceIn[(BASE3 - BASE) - i] = true;
                                            } else {
                                                reboundSpace(i, z, y, x);
                                            }

                                        } else {
                                            if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - 1) - i] == false) {
                                                space[z + modz][y + mody][x + modx].faceIn[(BASE3 - 1) - i] = true;
                                            } else {
                                                reboundSpace(i, z, y, x);
                                            }

                                        }
                                    } else if (x == MAXX - 1) {
                                        //code here for z0,y~,x∞
                                        //mody
                                        if (i < BASE2) { //y-
                                            mody = -1;
                                        } else if (i >= BASE3 - BASE2) { //y+
                                            mody = 1;
                                        } else {
                                            mody = 0;
                                        }

                                        //modx
                                        if (i % BASE == 0) { //x-
                                            modx = -1;
                                        } else if ((i + 1) % BASE == 0) { //x+
                                            modx = 0;
                                        } else {
                                            modx = 0;
                                        }

                                        //modz
                                        if (i % BASE2 < BASE) { //z-
                                            modz = 0;
                                        } else if ((i + BASE) % BASE2 < BASE) { //z+
                                            modz = 1;
                                        } else {
                                            modz = 0;
                                        }

                                        if (i % BASE2 < BASE) {
                                            if ((i + 1) % BASE == 0) {
                                                if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - BASE2) + ((BASE - 1) * 2) - i] == false) {
                                                    space[z + modz][y + mody][x + modx].faceIn[(BASE3 - BASE2) + ((BASE - 1) * 2) - i] = true;
                                                } else {
                                                    reboundSpace(i, z, y, x);
                                                }

                                            } else {
                                                if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - BASE2) + (BASE - 1) - i] == false) {
                                                    space[z + modz][y + mody][x + modx].faceIn[(BASE3 - BASE2) + (BASE - 1) - i] = true;
                                                } else {
                                                    reboundSpace(i, z, y, x);
                                                }

                                            }
                                        } else if ((i + 1) % BASE == 0) {
                                            if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - 1) + (BASE - 1) - i] == false) {
                                                space[z + modz][y + mody][x + modx].faceIn[(BASE3 - 1) + (BASE - 1) - i] = true;
                                            } else {
                                                reboundSpace(i, z, y, x);
                                            }

                                        } else {
                                            if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - 1) - i] == false) {
                                                space[z + modz][y + mody][x + modx].faceIn[(BASE3 - 1) - i] = true;
                                            } else {
                                                reboundSpace(i, z, y, x);
                                            }

                                        }
                                    } else {
                                        //code here for z0,y~,x~
                                        //mody
                                        if (i < BASE2) { //y-
                                            mody = -1;
                                        } else if (i >= BASE3 - BASE2) { //y+
                                            mody = 1;
                                        } else {
                                            mody = 0;
                                        }

                                        //modx
                                        if (i % BASE == 0) { //x-
                                            modx = -1;
                                        } else if ((i + 1) % BASE == 0) { //x+
                                            modx = 1;
                                        } else {
                                            modx = 0;
                                        }

                                        //modz
                                        if (i % BASE2 < BASE) { //z-
                                            modz = 0;
                                        } else if ((i + BASE) % BASE2 < BASE) { //z+
                                            modz = 1;
                                        } else {
                                            modz = 0;
                                        }

                                        if (i % BASE2 < BASE) {
                                            if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - BASE2) + (BASE - 1) - i] == false) {
                                                space[z + modz][y + mody][x + modx].faceIn[(BASE3 - BASE2) + (BASE - 1) - i] = true;
                                            } else {
                                                reboundSpace(i, z, y, x);
                                            }

                                        } else {
                                            if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - 1) - i] == false) {
                                                space[z + modz][y + mody][x + modx].faceIn[(BASE3 - 1) - i] = true;
                                            } else {
                                                reboundSpace(i, z, y, x);
                                            }

                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } else if (z == MAXZ - 1) {
                for (int y = 0; y < MAXY; y++) {
                    if (y == 0) {
                        for (int x = 0; x < MAXX; x++) {
                            for (int i = 0; i < BASE3; i++) {
                                if (space[z][y][x].faceOut[i] == true) {
                                    //System.out.println("Now processing space[" + z + "][" + y + "][" + x + "].faceOut[" + i + "].");
                                    space[z][y][x].faceOut[i] = false;
                                    if (x == 0) {
                                        //code here for z∞,y0,x0
                                        //mody
                                        if (i < BASE2) { //y-
                                            mody = 0;
                                        } else if (i >= BASE3 - BASE2) { //y+
                                            mody = 1;
                                        } else {
                                            mody = 0;
                                        }

                                        //modx
                                        if (i % BASE == 0) { //x-
                                            modx = 0;
                                        } else if ((i + 1) % BASE == 0) { //x+
                                            modx = 1;
                                        } else {
                                            modx = 0;
                                        }

                                        //modz
                                        if (i % BASE2 < BASE) { //z-
                                            modz = -1;
                                        } else if ((i + BASE) % BASE2 < BASE) { //z+
                                            modz = 0;
                                        } else {
                                            modz = 0;
                                        }

                                        if ((i + BASE) % BASE2 < BASE) {
                                            if (i < BASE2) {
                                                if (i % BASE == 0) {
                                                    if (space[z + modz][y + mody][x + modx].faceIn[i] == false) {
                                                        space[z + modz][y + mody][x + modx].faceIn[i] = true;
                                                    } else {
                                                        reboundSpace(i, z, y, x);
                                                    }

                                                } else {
                                                    if (space[z + modz][y + mody][x + modx].faceOut[(BASE2 - 1) + (BASE2 - BASE) - i] == false) {
                                                        space[z + modz][y + mody][x + modx].faceIn[(BASE2 - 1) + (BASE2 - BASE) - i] = true;
                                                    } else {
                                                        reboundSpace(i, z, y, x);
                                                    }

                                                }
                                            } else if (i % BASE == 0) {
                                                if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - BASE) + (BASE2 - BASE) - i] == false) {
                                                    space[z + modz][y + mody][x + modx].faceIn[(BASE3 - BASE) + (BASE2 - BASE) - i] = true;
                                                } else {
                                                    reboundSpace(i, z, y, x);
                                                }

                                            } else {
                                                if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - 1) + (BASE2 - BASE) - i] == false) {
                                                    space[z + modz][y + mody][x + modx].faceIn[(BASE3 - 1) + (BASE2 - BASE) - i] = true;
                                                } else {
                                                    reboundSpace(i, z, y, x);
                                                }

                                            }
                                        } else if (i < BASE) {
                                            if (i % BASE == 0) {
                                                if (space[z + modz][y + mody][x + modx].faceOut[(BASE2 - BASE) - i] == false) {
                                                    space[z + modz][y + mody][x + modx].faceIn[(BASE2 - BASE) - i] = true;
                                                } else {
                                                    reboundSpace(i, z, y, x);
                                                }

                                            } else {
                                                if (i == (BASE2 - 1) / 2) {
                                                    space[z][y][x].faceIn[i] = true;
                                                } else if (space[z + modz][y + mody][x + modx].faceOut[(BASE2 - 1) - i] == false) {
                                                    space[z + modz][y + mody][x + modx].faceIn[(BASE2 - 1) - i] = true;
                                                } else {
                                                    reboundSpace(i, z, y, x);
                                                }

                                            }
                                        } else if (i % BASE == 0) {
                                            if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - BASE) - i] == false) {
                                                space[z + modz][y + mody][x + modx].faceIn[(BASE3 - BASE) - i] = true;
                                            } else {
                                                reboundSpace(i, z, y, x);
                                            }

                                        } else {
                                            if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - 1) - i] == false) {
                                                space[z + modz][y + mody][x + modx].faceIn[(BASE3 - 1) - i] = true;
                                            } else {
                                                reboundSpace(i, z, y, x);
                                            }

                                        }
                                    } else if (x == MAXX - 1) {
                                        //code here for z∞,y0,x∞
                                        //mody
                                        if (i < BASE2) { //y-
                                            mody = 0;
                                        } else if (i >= BASE3 - BASE2) { //y+
                                            mody = 1;
                                        } else {
                                            mody = 0;
                                        }

                                        //modx
                                        if (i % BASE == 0) { //x-
                                            modx = -1;
                                        } else if ((i + 1) % BASE == 0) { //x+
                                            modx = 0;
                                        } else {
                                            modx = 0;
                                        }

                                        //modz
                                        if (i % BASE2 < BASE) { //z-
                                            modz = -1;
                                        } else if ((i + BASE) % BASE2 < BASE) { //z+
                                            modz = 0;
                                        } else {
                                            modz = 0;
                                        }

                                        if ((i + BASE) % BASE2 < BASE) {
                                            if (i < BASE2) {
                                                if ((i + 1) % BASE == 0) {
                                                    if (space[z][y][x].faceIn[i] == false) {
                                                        space[z][y][x].faceIn[i] = true;
                                                    } else {
                                                        reboundSpace(i, z, y, x);
                                                    }

                                                } else {
                                                    if (space[z + modz][y + mody][x + modx].faceOut[(BASE2 - BASE) + (BASE2 - 1) - i] == false) {
                                                        space[z + modz][y + mody][x + modx].faceIn[(BASE2 - BASE) + (BASE2 - 1) - i] = true;
                                                    } else {
                                                        reboundSpace(i, z, y, x);
                                                    }

                                                }
                                            } else if ((i + 1) % BASE == 0) {
                                                if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - 1) + (BASE2 - 1) - i] == false) {
                                                    space[z + modz][y + mody][x + modx].faceIn[(BASE3 - 1) + (BASE2 - 1) - i] = true;
                                                } else {
                                                    reboundSpace(i, z, y, x);
                                                }

                                            } else {
                                                if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - BASE) + (BASE2 - 1) - i] == false) {
                                                    space[z + modz][y + mody][x + modx].faceIn[(BASE3 - BASE) + (BASE2 - 1) - i] = true;
                                                } else {
                                                    reboundSpace(i, z, y, x);
                                                }

                                            }
                                        } else if (i < BASE2) {
                                            if ((i + 1) % BASE == 0) {
                                                if (space[z + modz][y + mody][x + modx].faceOut[(BASE2 - 1) + (BASE - 1) - i] == false) {
                                                    space[z + modz][y + mody][x + modx].faceIn[(BASE2 - 1) + (BASE - 1) - i] = true;
                                                } else {
                                                    reboundSpace(i, z, y, x);
                                                }

                                            } else {
                                                if (space[z + modz][y + mody][x + modx].faceOut[(BASE2 - 1) - i] == false) {
                                                    space[z + modz][y + mody][x + modx].faceIn[(BASE2 - 1) - i] = true;
                                                } else {
                                                    reboundSpace(i, z, y, x);
                                                }

                                            }
                                        } else if ((i + 1) % BASE == 0) {
                                            if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - 1) + (BASE - 1) - i] == false) {
                                                space[z + modz][y + mody][x + modx].faceIn[(BASE3 - 1) + (BASE - 1) - i] = true;
                                            } else {
                                                reboundSpace(i, z, y, x);
                                            }

                                        } else {
                                            if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - 1) - i] == false) {
                                                space[z + modz][y + mody][x + modx].faceIn[(BASE3 - 1) - i] = true;
                                            } else {
                                                reboundSpace(i, z, y, x);
                                            }

                                        }
                                    } else {
                                        //code here for z∞,y0,x~
                                        //mody
                                        if (i < BASE2) { //y-
                                            mody = 0;
                                        } else if (i >= BASE3 - BASE2) { //y+
                                            mody = 1;
                                        } else {
                                            mody = 0;
                                        }

                                        //modx
                                        if (i % BASE == 0) { //x-
                                            modx = -1;
                                        } else if ((i + 1) % BASE == 0) { //x+
                                            modx = 1;
                                        } else {
                                            modx = 0;
                                        }

                                        //modz
                                        if (i % BASE2 < BASE) { //z-
                                            modz = -1;
                                        } else if ((i + BASE) % BASE2 < BASE) { //z+
                                            modz = 0;
                                        } else {
                                            modz = 0;
                                        }

                                        if ((i + BASE) % BASE2 < BASE) {
                                            if (i < BASE2) {
                                                if (space[z + modz][y + mody][x + modx].faceOut[(BASE2 - BASE) + (BASE2 - 1) - i] == false) {
                                                    space[z + modz][y + mody][x + modx].faceIn[(BASE2 - BASE) + (BASE2 - 1) - i] = true;
                                                } else {
                                                    reboundSpace(i, z, y, x);
                                                }

                                            } else {
                                                if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - BASE) + (BASE2 - 1) - i] == false) {
                                                    space[z + modz][y + mody][x + modx].faceIn[(BASE3 - BASE) + (BASE2 - 1) - i] = true;
                                                } else {
                                                    reboundSpace(i, z, y, x);
                                                }

                                            }
                                        } else if (i < BASE2) {
                                            if (space[z + modz][y + mody][x + modx].faceOut[(BASE2 - 1) - i] == false) {
                                                space[z + modz][y + mody][x + modx].faceIn[(BASE2 - 1) - i] = true;
                                            } else {
                                                reboundSpace(i, z, y, x);
                                            }

                                        } else {
                                            if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - 1) - i] == false) {
                                                space[z + modz][y + mody][x + modx].faceIn[(BASE3 - 1) - i] = true;
                                            } else {
                                                reboundSpace(i, z, y, x);
                                            }

                                        }
                                    }
                                }
                            }
                        }
                    } else if (y == MAXY - 1) {
                        for (int x = 0; x < MAXX; x++) {
                            for (int i = 0; i < BASE3; i++) {
                                if (space[z][y][x].faceOut[i] == true) {
                                    //System.out.println("Now processing space[" + z + "][" + y + "][" + x + "].faceOut[" + i + "].");
                                    space[z][y][x].faceOut[i] = false;
                                    if (x == 0) {
                                        //code here for z∞,y∞,x0
                                        //mody
                                        if (i < BASE2) { //y-
                                            mody = -1;
                                        } else if (i >= BASE3 - BASE2) { //y+
                                            mody = 0;
                                        } else {
                                            mody = 0;
                                        }

                                        //modx
                                        if (i % BASE == 0) { //x-
                                            modx = 0;
                                        } else if ((i + 1) % BASE == 0) { //x+
                                            modx = 1;
                                        } else {
                                            modx = 0;
                                        }

                                        //modz
                                        if (i % BASE2 < BASE) { //z-
                                            modz = -1;
                                        } else if ((i + BASE) % BASE2 < BASE) { //z+
                                            modz = 0;
                                        } else {
                                            modz = 0;
                                        }

                                        if ((i + BASE) % BASE2 < BASE) {
                                            if (i > BASE3 - BASE2) {
                                                if (i % BASE == 0) {
                                                    if (space[z + modz][y + mody][x + modx].faceOut[i] == false) {
                                                        space[z + modz][y + mody][x + modx].faceIn[i] = true;
                                                    } else {
                                                        reboundSpace(i, z, y, x);
                                                    }

                                                } else {
                                                    if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - 1) + (BASE3 - BASE) - i] == false) {
                                                        space[z + modz][y + mody][x + modx].faceIn[(BASE3 - 1) + (BASE3 - BASE) - i] = true;
                                                    } else {
                                                        reboundSpace(i, z, y, x);
                                                    }

                                                }
                                            } else if (i % BASE == 0) {
                                                if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - BASE) + (BASE2 - BASE) - i] == false) {
                                                    space[z + modz][y + mody][x + modx].faceIn[(BASE3 - BASE) + (BASE2 - BASE) - i] = true;
                                                } else {
                                                    reboundSpace(i, z, y, x);
                                                }

                                            } else {
                                                if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - BASE) + (BASE2 - 1) - i] == false) {
                                                    space[z + modz][y + mody][x + modx].faceIn[(BASE3 - BASE) + (BASE2 - 1) - i] = true;
                                                } else {
                                                    reboundSpace(i, z, y, x);
                                                }

                                            }
                                        } else if (i >= BASE3 - BASE2) {
                                            if (i % BASE == 0) {
                                                if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - BASE2) + (BASE3 - BASE) - i] == false) {
                                                    space[z + modz][y + mody][x + modx].faceIn[(BASE3 - BASE2) + (BASE3 - BASE) - i] = true;
                                                } else {
                                                    reboundSpace(i, z, y, x);
                                                }

                                            } else {
                                                if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - 1) + (BASE3 - BASE2) - i] == false) {
                                                    space[z + modz][y + mody][x + modx].faceIn[(BASE3 - 1) + (BASE3 - BASE2) - i] = true;
                                                } else {
                                                    reboundSpace(i, z, y, x);
                                                }

                                            }
                                        } else if (i % BASE == 0) {
                                            if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - BASE2) + (BASE2 - BASE) - i] == false) {
                                                space[z + modz][y + mody][x + modx].faceIn[(BASE3 - BASE2) + (BASE2 - BASE) - i] = true;
                                            } else {
                                                reboundSpace(i, z, y, x);
                                            }

                                        } else {
                                            if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - 1) - i] == false) {
                                                space[z + modz][y + mody][x + modx].faceIn[(BASE3 - 1) - i] = true;
                                            } else {
                                                reboundSpace(i, z, y, x);
                                            }

                                        }
                                    } else if (x == MAXX - 1) {
                                        //code here for z∞,y∞,x∞
                                        //mody
                                        if (i < BASE2) { //y-
                                            mody = -1;
                                        } else if (i >= BASE3 - BASE2) { //y+
                                            mody = 0;
                                        } else {
                                            mody = 0;
                                        }

                                        //modx
                                        if (i % BASE == 0) { //x-
                                            modx = -1;
                                        } else if ((i + 1) % BASE == 0) { //x+
                                            modx = 0;
                                        } else {
                                            modx = 0;
                                        }

                                        //modz
                                        if (i % BASE2 < BASE) { //z-
                                            modz = -1;
                                        } else if ((i + BASE) % BASE2 < BASE) { //z+
                                            modz = 0;
                                        } else {
                                            modz = 0;
                                        }

                                        if (((i + BASE) % BASE2) < BASE) {//back
                                            if (i >= BASE3 - BASE2) {//bottom
                                                if ((i + 1) % BASE == 0) {//right
                                                    if (space[z][y][x].faceIn[i] == false) {
                                                        space[z][y][x].faceIn[i] = true;
                                                    } else {
                                                        reboundSpace(i, z, y, x);
                                                    }

                                                } else {
                                                    if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - 1) + (BASE3 - BASE) - i] == false) {
                                                        space[z + modz][y + mody][x + modx].faceIn[(BASE3 - 1) + (BASE3 - BASE) - i] = true;
                                                    } else {
                                                        reboundSpace(i, z, y, x);
                                                    }

                                                }
                                            } else if ((i + 1) % BASE == 0) {
                                                if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - 1) + (BASE2 - 1) - i] == false) {
                                                    space[z + modz][y + mody][x + modx].faceIn[(BASE3 - 1) + (BASE2 - 1) - i] = true;
                                                } else {
                                                    reboundSpace(i, z, y, x);
                                                }

                                            } else {
                                                if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - 1) + (BASE2 - BASE) - i] == false) {
                                                    space[z + modz][y + mody][x + modx].faceIn[(BASE3 - 1) + (BASE2 - BASE) - i] = true;
                                                } else {
                                                    reboundSpace(i, z, y, x);
                                                }


                                            }
                                        } else if (i >= (BASE3 - BASE2)) {
                                            if ((i + 1) % BASE == 0) {
                                                if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - 1) + (BASE3 - BASE2) + (BASE - 1) - i] == false) {
                                                    space[z + modz][y + mody][x + modx].faceIn[(BASE3 - 1) + (BASE3 - BASE2) + (BASE - 1) - i] = true;
                                                } else {
                                                    reboundSpace(i, z, y, x);
                                                }

                                            } else {
                                                if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - 1) + (BASE3 - BASE2) - i] == false) {
                                                    space[z + modz][y + mody][x + modx].faceIn[(BASE3 - 1) + (BASE3 - BASE2) - i] = true;
                                                } else {
                                                    reboundSpace(i, z, y, x);
                                                }

                                            }
                                        } else if ((i + 1) % BASE == 0) {
                                            if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - 1) + (BASE - 1) - i] == false) {
                                                space[z + modz][y + mody][x + modx].faceIn[(BASE3 - 1) + (BASE - 1) - i] = true;
                                            } else {
                                                reboundSpace(i, z, y, x);
                                            }

                                        } else {
                                            if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - 1) - i] == false) {
                                                space[z + modz][y + mody][x + modx].faceIn[(BASE3 - 1) - i] = true;
                                            } else {
                                                reboundSpace(i, z, y, x);
                                            }

                                        }
                                    } else {
                                        //code here for z∞,y∞,x~
                                        //mody
                                        if (i < BASE2) { //y-
                                            mody = -1;
                                        } else if (i >= BASE3 - BASE2) { //y+
                                            mody = 0;
                                        } else {
                                            mody = 0;
                                        }

                                        //modx
                                        if (i % BASE == 0) { //x-
                                            modx = -1;
                                        } else if ((i + 1) % BASE == 0) { //x+
                                            modx = 1;
                                        } else {
                                            modx = 0;
                                        }

                                        //modz
                                        if (i % BASE2 < BASE) { //z-
                                            modz = -1;
                                        } else if ((i + BASE) % BASE2 < BASE) { //z+
                                            modz = 0;
                                        } else {
                                            modz = 0;
                                        }

                                        if ((i + BASE) % BASE2 < BASE) {
                                            if (i >= BASE3 - BASE2) {
                                                if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - 1) + (BASE3 - BASE) - i] == false) {
                                                    space[z + modz][y + mody][x + modx].faceIn[(BASE3 - 1) + (BASE3 - BASE) - i] = true;
                                                } else {
                                                    reboundSpace(i, z, y, x);
                                                }

                                            } else {
                                                if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - 1) + (BASE2 - BASE) - i] == false) {
                                                    space[z + modz][y + mody][x + modx].faceIn[(BASE3 - 1) + (BASE2 - BASE) - i] = true;
                                                } else {
                                                    reboundSpace(i, z, y, x);
                                                }

                                            }
                                        } else if (i >= BASE3 - BASE2) {
                                            if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - 1) + (BASE3 - BASE2) - i] == false) {
                                                space[z + modz][y + mody][x + modx].faceIn[(BASE3 - 1) + (BASE3 - BASE2) - i] = true;
                                            } else {
                                                reboundSpace(i, z, y, x);
                                            }

                                        } else {
                                            if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - 1) - i] == false) {
                                                space[z + modz][y + mody][x + modx].faceIn[(BASE3 - 1) - i] = true;
                                            } else {
                                                reboundSpace(i, z, y, x);
                                            }

                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        for (int x = 0; x < MAXX; x++) {
                            for (int i = 0; i < BASE3; i++) {
                                if (space[z][y][x].faceOut[i] == true) {
                                    //System.out.println("Now processing space[" + z + "][" + y + "][" + x + "].faceOut[" + i + "].");
                                    space[z][y][x].faceOut[i] = false;
                                    if (x == 0) {
                                        //code here for z∞,y~,x0
                                        //mody
                                        if (i < BASE2) { //y-
                                            mody = -1;
                                        } else if (i >= BASE3 - BASE2) { //y+
                                            mody = 1;
                                        } else {
                                            mody = 0;
                                        }

                                        //modx
                                        if (i % BASE == 0) { //x-
                                            modx = 0;
                                        } else if ((i + 1) % BASE == 0) { //x+
                                            modx = 1;
                                        } else {
                                            modx = 0;
                                        }

                                        //modz
                                        if (i % BASE2 < BASE) { //z-
                                            modz = -1;
                                        } else if ((i + BASE) % BASE2 < BASE) { //z+
                                            modz = 0;
                                        } else {
                                            modz = 0;
                                        }

                                        if ((i + BASE) % BASE2 < BASE) {
                                            if (i % BASE == 0) {
                                                if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - BASE) + (BASE2 - BASE) - i] == false) {
                                                    space[z + modz][y + mody][x + modx].faceIn[(BASE3 - BASE) + (BASE2 - BASE) - i] = true;
                                                } else {
                                                    reboundSpace(i, z, y, x);
                                                }

                                            } else {
                                                if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - 1) + (BASE2 - BASE) - i] == false) {
                                                    space[z + modz][y + mody][x + modx].faceIn[(BASE3 - 1) + (BASE2 - BASE) - i] = true;
                                                } else {
                                                    reboundSpace(i, z, y, x);
                                                }

                                            }
                                        } else if (i % BASE == 0) {
                                            if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - BASE) - i] == false) {
                                                space[z + modz][y + mody][x + modx].faceIn[(BASE3 - BASE) - i] = true;
                                            } else {
                                                reboundSpace(i, z, y, x);
                                            }

                                        } else {
                                            if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - 1) - i] == false) {
                                                space[z + modz][y + mody][x + modx].faceIn[(BASE3 - 1) - i] = true;
                                            } else {
                                                reboundSpace(i, z, y, x);
                                            }

                                        }
                                    } else if (x == MAXX - 1) {
                                        //code here for z∞,y~,x∞
                                        //mody
                                        if (i < BASE2) { //y-
                                            mody = -1;
                                        } else if (i >= BASE3 - BASE2) { //y+
                                            mody = 1;
                                        } else {
                                            mody = 0;
                                        }

                                        //modx
                                        if (i % BASE == 0) { //x-
                                            modx = -1;
                                        } else if ((i + 1) % BASE == 0) { //x+
                                            modx = 0;
                                        } else {
                                            modx = 0;
                                        }

                                        //modz
                                        if (i % BASE2 < BASE) { //z-
                                            modz = -1;
                                        } else if ((i + BASE) % BASE2 < BASE) { //z+
                                            modz = 0;
                                        } else {
                                            modz = 0;
                                        }

                                        if ((i + BASE) % BASE2 < BASE) {
                                            if ((i + 1) % BASE == 0) {
                                                if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - 1) + (BASE2 - 1) - i] == false) {
                                                    space[z + modz][y + mody][x + modx].faceIn[(BASE3 - 1) + (BASE2 - 1) - i] = true;
                                                } else {
                                                    reboundSpace(i, z, y, x);
                                                }

                                            } else {
                                                if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - 1) + (BASE2 - BASE) - i] == false) {
                                                    space[z + modz][y + mody][x + modx].faceIn[(BASE3 - 1) + (BASE2 - BASE) - i] = true;
                                                } else {
                                                    reboundSpace(i, z, y, x);
                                                }

                                            }
                                        } else if ((i + 1) % BASE == 0) {
                                            if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - 1) + (BASE - 1) - i] == false) {
                                                space[z + modz][y + mody][x + modx].faceIn[(BASE3 - 1) + (BASE - 1) - i] = true;
                                            } else {
                                                reboundSpace(i, z, y, x);
                                            }

                                        } else {
                                            if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - 1) - i] == false) {
                                                space[z + modz][y + mody][x + modx].faceIn[(BASE3 - 1) - i] = true;
                                            } else {
                                                reboundSpace(i, z, y, x);
                                            }

                                        }
                                    } else {
                                        //code here for z∞,y~,x~
                                        //mody
                                        if (i < BASE2) { //y-
                                            mody = -1;
                                        } else if (i >= BASE3 - BASE2) { //y+
                                            mody = 1;
                                        } else {
                                            mody = 0;
                                        }

                                        //modx
                                        if (i % BASE == 0) { //x-
                                            modx = -1;
                                        } else if ((i + 1) % BASE == 0) { //x+
                                            modx = 1;
                                        } else {
                                            modx = 0;
                                        }

                                        //modz
                                        if (i % BASE2 < BASE) { //z-
                                            modz = -1;
                                        } else if ((i + BASE) % BASE2 < BASE) { //z+
                                            modz = 0;
                                        } else {
                                            modz = 0;
                                        }

                                        if ((i + BASE) % BASE2 < BASE) {
                                            if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - 1) + (BASE2 - BASE) - i] == false) {
                                                space[z + modz][y + mody][x + modx].faceIn[(BASE3 - 1) + (BASE2 - BASE) - i] = true;
                                            } else {
                                                reboundSpace(i, z, y, x);
                                            }

                                        } else {
                                            if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - 1) - i] == false) {
                                                space[z + modz][y + mody][x + modx].faceIn[(BASE3 - 1) - i] = true;
                                            } else {
                                                reboundSpace(i, z, y, x);
                                            }

                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                for (int y = 0; y < MAXY; y++) {
                    if (y == 0) {
                        for (int x = 0; x < MAXX; x++) {
                            for (int i = 0; i < BASE3; i++) {
                                if (space[z][y][x].faceOut[i] == true) {
                                    //System.out.println("Now processing space[" + z + "][" + y + "][" + x + "].faceOut[" + i + "].");
                                    space[z][y][x].faceOut[i] = false;
                                    if (x == 0) {
                                        //code here for z~,y0,x0
                                        //mody
                                        if (i < BASE2) { //y-
                                            mody = 0;
                                        } else if (i >= BASE3 - BASE2) { //y+
                                            mody = 1;
                                        } else {
                                            mody = 0;
                                        }

                                        //modx
                                        if (i % BASE == 0) { //x-
                                            modx = 0;
                                        } else if ((i + 1) % BASE == 0) { //x+
                                            modx = 1;
                                        } else {
                                            modx = 0;
                                        }

                                        //modz
                                        if (i % BASE2 < BASE) { //z-
                                            modz = -1;
                                        } else if ((i + BASE) % BASE2 < BASE) { //z+
                                            modz = 1;
                                        } else {
                                            modz = 0;
                                        }

                                        if (i < BASE2) {
                                            if (i % BASE == 0) {
                                                if (space[z + modz][y + mody][x + modx].faceOut[(BASE2 - BASE) - i] == false) {
                                                    space[z + modz][y + mody][x + modx].faceIn[(BASE2 - BASE) - i] = true;
                                                } else {
                                                    reboundSpace(i, z, y, x);
                                                }

                                            } else {
                                                if (space[z + modz][y + mody][x + modx].faceOut[(BASE2 - 1) - i] == false) {
                                                    space[z + modz][y + mody][x + modx].faceIn[(BASE2 - 1) - i] = true;
                                                } else {
                                                    reboundSpace(i, z, y, x);
                                                }

                                            }
                                        } else if (i % BASE == 0) {
                                            if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - BASE) - i] == false) {
                                                space[z + modz][y + mody][x + modx].faceIn[(BASE3 - BASE) - i] = true;
                                            } else {
                                                reboundSpace(i, z, y, x);
                                            }

                                        } else {
                                            if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - 1) - i] == false) {
                                                space[z + modz][y + mody][x + modx].faceIn[(BASE3 - 1) - i] = true;
                                            } else {
                                                reboundSpace(i, z, y, x);
                                            }

                                        }
                                    } else if (x == MAXX - 1) {
                                        //code here for z~,y0,x∞
                                        //mody
                                        if (i < BASE2) { //y-
                                            mody = 0;
                                        } else if (i >= BASE3 - BASE2) { //y+
                                            mody = 1;
                                        } else {
                                            mody = 0;
                                        }

                                        //modx
                                        if (i % BASE == 0) { //x-
                                            modx = -1;
                                        } else if ((i + 1) % BASE == 0) { //x+
                                            modx = 0;
                                        } else {
                                            modx = 0;
                                        }

                                        //modz
                                        if (i % BASE2 < BASE) { //z-
                                            modz = -1;
                                        } else if ((i + BASE) % BASE2 < BASE) { //z+
                                            modz = 1;
                                        } else {
                                            modz = 0;
                                        }

                                        if (i < BASE2) {
                                            if ((i + 1) % BASE == 0) {
                                                if (space[z + modz][y + mody][x + modx].faceOut[(BASE2 - 1) + (BASE - 1) - i] == false) {
                                                    space[z + modz][y + mody][x + modx].faceIn[(BASE2 - 1) + (BASE - 1) - i] = true;
                                                } else {
                                                    reboundSpace(i, z, y, x);
                                                }

                                            } else {
                                                if (space[z + modz][y + mody][x + modx].faceOut[(BASE2 - 1) - i] == false) {
                                                    space[z + modz][y + mody][x + modx].faceIn[(BASE2 - 1) - i] = true;
                                                } else {
                                                    reboundSpace(i, z, y, x);
                                                }

                                            }
                                        } else if ((i + 1) % BASE == 0) {
                                            if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - 1) + (BASE - 1) - i] == false) {
                                                space[z + modz][y + mody][x + modx].faceIn[(BASE3 - 1) + (BASE - 1) - i] = true;
                                            } else {
                                                reboundSpace(i, z, y, x);
                                            }

                                        } else {
                                            if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - 1) - i] == false) {
                                                space[z + modz][y + mody][x + modx].faceIn[(BASE3 - 1) - i] = true;
                                            } else {
                                                reboundSpace(i, z, y, x);
                                            }

                                        }
                                    } else {
                                        //code here for z~,y0,x~
                                        if (i < BASE2) {
                                            mody = 0;
                                        } else if (i >= BASE3 - BASE2) {
                                            mody = 1;
                                        } else {
                                            mody = 0;
                                        }

                                        if (i % BASE == 0) {
                                            modx = -1;
                                        } else if ((i + 1) % BASE == 0) {
                                            modx = 1;
                                        } else {
                                            modx = 0;
                                        }

                                        if (i % BASE2 < BASE) {
                                            modz = -1;
                                        } else if ((i + BASE) % BASE2 < BASE) {
                                            modz = 1;
                                        } else {
                                            modz = 0;
                                        }

                                        if (i < BASE2) {
                                            if (space[z + modz][y + mody][x + modx].faceOut[(BASE2 - 1) - i] == false) {
                                                space[z + modz][y + mody][x + modx].faceIn[(BASE2 - 1) - i] = true;
                                            } else {
                                                reboundSpace(i, z, y, x);
                                            }

                                        } else {
                                            if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - 1) - i] == false) {
                                                space[z + modz][y + mody][x + modx].faceIn[(BASE3 - 1) - i] = true;
                                            } else {
                                                reboundSpace(i, z, y, x);
                                            }

                                        }
                                    }
                                }
                            }
                        }
                    } else if (y == MAXY - 1) {
                        for (int x = 0; x < MAXX; x++) {
                            for (int i = 0; i < BASE3; i++) {
                                if (space[z][y][x].faceOut[i] == true) {
                                    //System.out.println("Now processing space[" + z + "][" + y + "][" + x + "].faceOut[" + i + "].");
                                    space[z][y][x].faceOut[i] = false;
                                    if (x == 0) {
                                        //code here for z~,y∞,x0
                                        if (i < BASE2) {
                                            mody = -1;
                                        } else if (i >= BASE3 - BASE2) {
                                            mody = 0;
                                        } else {
                                            mody = 0;
                                        }

                                        if (i % BASE == 0) {
                                            modx = 0;
                                        } else if ((i + 1) % BASE == 0) {
                                            modx = 1;
                                        } else {
                                            modx = 0;
                                        }

                                        if (i % BASE2 < BASE) {
                                            modz = -1;
                                        } else if ((i + BASE) % BASE2 < BASE) {
                                            modz = 1;
                                        } else {
                                            modz = 0;
                                        }

                                        if (i >= BASE3 - BASE2) {
                                            if (i % BASE == 0) {
                                                if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - BASE) + (BASE3 - BASE2) - i] == false) {
                                                    space[z + modz][y + mody][x + modx].faceIn[(BASE3 - BASE) + (BASE3 - BASE2) - i] = true;
                                                } else {
                                                    reboundSpace(i, z, y, x);
                                                }

                                            } else {
                                                if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - 1) + (BASE3 - BASE2) - i] == false) {
                                                    space[z + modz][y + mody][x + modx].faceIn[(BASE3 - 1) + (BASE3 - BASE2) - i] = true;
                                                } else {
                                                    reboundSpace(i, z, y, x);
                                                }

                                            }
                                        } else if (i % BASE == 0) {
                                            if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - BASE) - i] == false) {
                                                space[z + modz][y + mody][x + modx].faceIn[(BASE3 - BASE) - i] = true;
                                            } else {
                                                reboundSpace(i, z, y, x);
                                            }

                                        } else {
                                            if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - 1) - i] == false) {
                                                space[z + modz][y + mody][x + modx].faceIn[(BASE3 - 1) - i] = true;
                                            } else {
                                                reboundSpace(i, z, y, x);
                                            }

                                        }
                                    } else if (x == MAXX - 1) {
                                        //code here for z~,y∞,x∞

                                        if (i < BASE2) {
                                            mody = -1;
                                        } else if (i >= BASE3 - BASE2) {
                                            mody = 0;
                                        } else {
                                            mody = 0;
                                        }

                                        if (i % BASE == 0) {
                                            modx = -1;
                                        } else if ((i + 1) % BASE == 0) {
                                            modx = 0;
                                        } else {
                                            modx = 0;
                                        }

                                        if (i % BASE2 < BASE) {
                                            modz = -1;
                                        } else if ((i + BASE) % BASE2 < BASE) {
                                            modz = 1;
                                        } else {
                                            modz = 0;
                                        }


                                        if (i >= BASE3 - BASE2) {
                                            if ((i + 1) % BASE == 0) {
                                                if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - 1) + (BASE3 - BASE2) + (BASE - 1) - i] == false) {
                                                    space[z + modz][y + mody][x + modx].faceIn[(BASE3 - 1) + (BASE3 - BASE2) + (BASE - 1) - i] = true;
                                                } else {
                                                    reboundSpace(i, z, y, x);
                                                }

                                            } else {
                                                if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - 1) + (BASE3 - BASE2) - i] == false) {
                                                    space[z + modz][y + mody][x + modx].faceIn[(BASE3 - 1) + (BASE3 - BASE2) - i] = true;
                                                } else {
                                                    reboundSpace(i, z, y, x);
                                                }

                                            }
                                        } else if ((i + 1) % BASE == 0) {
                                            if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - 1) + (BASE - 1) - i] == false) {
                                                space[z + modz][y + mody][x + modx].faceIn[(BASE3 - 1) + (BASE - 1) - i] = true;
                                            } else {
                                                reboundSpace(i, z, y, x);
                                            }

                                        } else {
                                            if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - 1) - i] == false) {
                                                space[z + modz][y + mody][x + modx].faceIn[(BASE3 - 1) - i] = true;
                                            } else {
                                                reboundSpace(i, z, y, x);
                                            }

                                        }
                                    } else {
                                        //code here for z~,y∞,x~
                                        if (i < BASE2) {
                                            mody = -1;
                                        } else if (i >= BASE3 - BASE2) {
                                            mody = 0;
                                        } else {
                                            mody = 0;
                                        }

                                        if (i % BASE == 0) {
                                            modx = -1;
                                        } else if ((i + 1) % BASE == 0) {
                                            modx = 1;
                                        } else {
                                            modx = 0;
                                        }


                                        if (i % BASE2 < BASE) {
                                            modz = -1;
                                        } else if ((i + BASE) % BASE2 < BASE) {
                                            modz = 1;
                                        } else {
                                            modz = 0;
                                        }

                                        if (i >= BASE3 - BASE2) {
                                            if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - 1) + (BASE3 - BASE2) - i] == false) {
                                                space[z + modz][y + mody][x + modx].faceIn[(BASE3 - 1) + (BASE3 - BASE2) - i] = true;
                                            } else {
                                                reboundSpace(i, z, y, x);
                                            }

                                        } else {
                                            if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - 1) - i] == false) {
                                                space[z + modz][y + mody][x + modx].faceIn[(BASE3 - 1) - i] = true;
                                            } else {
                                                reboundSpace(i, z, y, x);
                                            }

                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        for (int x = 0; x < MAXX; x++) {
                            for (int i = 0; i < BASE3; i++) {
                                if (space[z][y][x].faceOut[i] == true) {
                                    //System.out.println("Now processing space[" + z + "][" + y + "][" + x + "].faceOut[" + i + "].");
                                    space[z][y][x].faceOut[i] = false;
                                    if (x == 0) {
                                        //code here for z~,y~,x0

                                        if (i < BASE2) {
                                            mody = -1;
                                        } else if (i >= BASE3 - BASE2) {
                                            mody = 1;
                                        } else {
                                            mody = 0;
                                        }

                                        if (i % BASE == 0) {
                                            modx = 0;
                                        } else if ((i + 1) % BASE == 0) {
                                            modx = 1;
                                        } else {
                                            modx = 0;
                                        }

                                        if (i % BASE2 < BASE) {
                                            modz = -1;
                                        } else if ((i + BASE) % BASE2 < BASE) {
                                            modz = 1;
                                        } else {
                                            modz = 0;
                                        }


                                        if (i % BASE == 0) {
                                            if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - BASE) - i] == false) {
                                                space[z + modz][y + mody][x + modx].faceIn[(BASE3 - BASE) - i] = true;
                                            } else {
                                                reboundSpace(i, z, y, x);
                                            }

                                        } else {
                                            if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - 1) - i] == false) {
                                                space[z + modz][y + mody][x + modx].faceIn[(BASE3 - 1) - i] = true;
                                            } else {
                                                reboundSpace(i, z, y, x);
                                            }

                                        }
                                    } else if (x == MAXX - 1) {
                                        //code here for z~,y~,x∞
                                        if (i < BASE2) {
                                            mody = -1;
                                        } else if (i >= BASE3 - BASE2) {
                                            mody = 1;
                                        } else {
                                            mody = 0;
                                        }

                                        if (i % BASE == 0) {
                                            modx = -1;
                                        } else if ((i + 1) % BASE == 0) {
                                            modx = 0;
                                        } else {
                                            modx = 0;
                                        }

                                        if (i % BASE2 < BASE) {
                                            modz = -1;
                                        } else if ((i + BASE) % BASE2 < BASE) {
                                            modz = 1;
                                        } else {
                                            modz = 0;
                                        }
                                        if ((i + 1) % BASE == 0) {
                                            if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - 1) + (BASE - 1) - i] == false) {
                                                space[z + modz][y + mody][x + modx].faceIn[(BASE3 - 1) + (BASE - 1) - i] = true;
                                            } else {
                                                reboundSpace(i, z, y, x);
                                            }

                                        } else {
                                            if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - 1) - i] == false) {
                                                space[z + modz][y + mody][x + modx].faceIn[(BASE3 - 1) - i] = true;
                                            } else {
                                                reboundSpace(i, z, y, x);
                                            }

                                        }
                                    } else {
                                        //code here for z~,y~,x~
                                        if (i < BASE2) {
                                            mody = -1;
                                        } else if (i >= BASE3 - BASE2) {
                                            mody = 1;
                                        } else {
                                            mody = 0;
                                        }

                                        if (i % BASE == 0) {
                                            modx = -1;
                                        } else if ((i + 1) % BASE == 0) {
                                            modx = 1;
                                        } else {
                                            modx = 0;
                                        }

                                        if (i % BASE2 < BASE) {
                                            modz = -1;
                                        } else if ((i + BASE) % BASE2 < BASE) {
                                            modz = 1;
                                        } else {
                                            modz = 0;
                                        }
                                        if (space[z + modz][y + mody][x + modx].faceOut[(BASE3 - 1) - i] == false) {
                                            space[z + modz][y + mody][x + modx].faceIn[(BASE3 - 1) - i] = true;
                                        } else {
                                            reboundSpace(i, z, y, x);
                                        }

                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        /*
         for(int z = 0; z < MAXZ; z++){
         for(int y = 0; y < MAXY; y++){
         for(int x = 0; x < MAXX; x++){
         java.util.Arrays.fill(space[z][y][x].faceOut, false);
         }
         }
         }
         */
    }
}
