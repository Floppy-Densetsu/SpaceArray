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
import java.awt.event.*;
import java.util.*;

public class PushTest implements Runnable {

    /**
     * @param args the command line arguments
     */
    static int BASE = 3;
    static int BASE2 = (int) Math.pow(BASE, 2);
    static int BASE3 = (int) Math.pow(BASE, 3);
    static int MAXZ = 8;
    static int MAXY = 3;
    static int MAXX = 3;
    static BufferedImage spaceMap = new BufferedImage(1700, 1200, BufferedImage.TYPE_INT_RGB);
    static BufferedImage finalImage = new BufferedImage(800, 800, BufferedImage.TYPE_INT_RGB);
    static BufferedImage[] display = new BufferedImage[MAXZ];
    static PushSpace[][][] space = new PushSpace[MAXZ][MAXY][MAXX];
    static Graphics2D g2d;
    static Graphics g;
    static MyFrame frame;
    static Thread loop;
    static Color newColor;
    static Color inColor = new Color(150, 90, 40);
    static Color outColor = new Color(100, 90, 150);
    static Font f = new Font("monospaced", Font.PLAIN, 10);
    static int facetest = 0;
    static int loopcounter = 0;
    static int testZ = 2;
    static int testY = 2;
    static int testX = 2;
    static boolean drawswitch = false;

    static class MyFrame extends javax.swing.JFrame {

        public void paint(Graphics g) {

            g.drawImage(spaceMap, 0, 0, rootPane);
        }
    }

    public static void main(String[] args) {

        newColor = new Color(0);

        g2d = spaceMap.createGraphics();
        g2d.setFont(f);
        frame = new MyFrame();
        frame.setTitle("Testing");
        frame.setVisible(true);
        frame.setSize(1700, 1200);
        frame.setDefaultCloseOperation(MyFrame.EXIT_ON_CLOSE);

        initialize();

        for(int i = 0; i < 5; i++){
            randomTrue();
            randomTrue();
        }
        loop = new Thread(new PushTest());
        loop.start();

    }
    public static void updateB() {
        g2d.clearRect(0, 0, spaceMap.getWidth(), spaceMap.getHeight());
        //randomTrue();
        processSpace();
        //drawMap3B(20, 40);
        g2d.drawString("Processed", 100, 600);
        frame.repaint();
    }
    public static void update() {
        g2d.clearRect(0, 0, spaceMap.getWidth(), spaceMap.getHeight());
        randomTrue();
       
        fire3();
        //drawMap3B(20, 40);
            
        
        g2d.drawString("Fired", 100, 600);
        //drawMap2(20, 40);
        frame.repaint();

    }
    public static int randomFace(int z, int y, int x){
        int rand = (int)(Math.random() * BASE3);
        if(space[z][y][x].face[rand][0] == false){
            return rand;
        } else {
            return randomFace(z, y, x);
        }
    }

    public static void processSpace() {
        for (int z = 0; z < MAXZ; z++) {
            for (int y = 0; y < MAXY; y++) {
                for (int x = 0; x < MAXX; x++) {
                    for(int i = 0; i < BASE3; i++){
                        if(space[z][y][x].face[i][1] == true){
                            space[z][y][x].face[i][1] = false;
                            space[z][y][x].face[(BASE3 - 1) - i][0] = true;
                        }
                    }
                }
            }
        }
    }

    public static void randomTrue() {
        int randZ = (int) (Math.random() * MAXZ);
        int randY = (int) (Math.random() * MAXY);
        int randX = (int) (Math.random() * MAXX);
        int randF = (int) (Math.random() * BASE3);

        if (space[randZ][randY][randX].face[randF][0] == false) {
            space[randZ][randY][randX].face[randF][0] = true;
        } else {
            randomTrue();
        }
    }
    
    public static void clearMap(int thisX, int thisY) {
        //g2d = spaceMap.createGraphics();
        g2d.clearRect(thisX, thisY, 750, 338);
    }

    public static void drawMap2(int startX, int startY) {
        g2d.clearRect(0, 0, spaceMap.getWidth(), spaceMap.getHeight());

        int drawX = startX;
        int drawY = startY + 12;
        int q = 0;
        //int counter = 1;
        //Color newColor = new Color(0, 0, 0);
        // g2d.drawString("BLAHBLAH", 100, 100);
        // g2d.fillRect(50, 50, 100, 100);
        for (int y = 0; y < MAXY; y++) {

            for (int i = 0; i < (BASE); i++) {
                for (int z = MAXZ - 1; z >= 0; z--) {
                    for (int x = 0; x < MAXX; x++) {
                        for (int j = (i * BASE2); j < (i * BASE2) + BASE2; j++) {
                            // g2d.fillRect(drawX + ((x*100)+((j%9)*10)), drawY, 5, 5);
                            //q = space[z][y][x].face[j] ? 35 : (64 + j);
                            //q = space[z][y][x].face[j] ? 35 : (32);
                            //newColor = new Color(newColor.getRGB() + 1);
                            //counter += 1;
                            if (q == 35) {
                                // newColor = new Color(newColor.getRGB() + 1);
                                g2d.setColor(Color.CYAN);

                                //g2d.drawString("Space[" + z + "][" + y + "][" + x + "].face[" + j + "]", 1525, 500 + (counter * 10));
                                //counter++;
                            } else {
                                g2d.setColor(inColor);
                            }
                            g2d.drawString("" + (char) q, drawX + ((x * 108) + ((j % BASE2) * 12)), drawY);


                            //counter++;

                        }
                    }
                    drawY += 7;
                }
                //drawY += 7;
            }
        }
    }

    public static void drawMap(int startX, int startY) {
        g2d.clearRect(startX, startY, startX + 755, startY + 390);
        int drawX = startX;
        int drawY = startY + 12;
        //g2d = spaceMap.createGraphics();
        //g2d.setFont(f);
        /// g2d.setColor(Color.LIGHT_GRAY);
        // g2d.fillRect(0, 0, 200, 100);
        int q = 0;
        // g2d.drawString("BLAHBLAH", 100, 100);
        // g2d.fillRect(50, 50, 100, 100);
        for (int y = 0; y < MAXY; y++) {

            for (int i = 0; i < (BASE); i++) {
                for (int z = MAXZ - 1; z >= 0; z--) {
                    for (int x = 0; x < MAXX; x++) {
                        for (int j = (i * BASE2); j < (i * BASE2) + BASE2; j++) {
                            // g2d.fillRect(drawX + ((x*100)+((j%9)*10)), drawY, 5, 5);
                           // q = space[z][y][x].face[j] ? 35 : (64 + j);
                            if (q == 35) {
                                g2d.setColor(Color.CYAN);
                            } else {
                                g2d.setColor(inColor);
                            }
                            g2d.drawString("" + (char) q, drawX + ((x * 240) + ((j % BASE2) * 15)), drawY);
                           // q = space[z][y][x].face[j] ? 35 : (64 + j);
                            if (q == 35) {
                                g2d.setColor(Color.CYAN);
                            } else {
                                g2d.setColor(outColor);
                            }
                            g2d.drawString("" + (char) q, drawX + ((x * 240) + ((j % BASE2) * 15) + 7), drawY);

                        }
                    }
                    drawY += 7;
                }
                drawY += 7;
            }
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

                Thread.sleep(100);
                updateB();
                
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void initialize() {

        for (int i = 0; i < MAXZ; i++) {
            for (int j = 0; j < MAXY; j++) {
                for (int k = 0; k < MAXX; k++) {
                    space[i][j][k] = new PushSpace(BASE);
                }
            }
        }
    }
public void newFire(){
    int modz = 0;
    int mody = 0;
    int modx = 0;
    
    
    
}


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
                                if (space[z][y][x].face[i][0] == true) {
                                    System.out.println("Now processing space[" + z + "][" + y + "][" + x + "].face[" + i + "].");
                                    space[z][y][x].face[i][0] = false;
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

                                        if (i % BASE2 < BASE) { //Front
                                            if (i < BASE) { // Front Top
                                                if (i % BASE == 0) { //0
                                                    space[z][y][x].face[i][1] = true;

                                                } else if ((i + 1) % BASE == 0) { //2

                                                    if (space[z + modz][y + mody][x + modx].face[(BASE - 1) - i][0] == false) {
                                                        space[z + modz][y + mody][x + modx].face[(BASE - 1) - i][1] = true;
                                                    } else {
                                                        space[z][y][x].face[i][1] = true;
                                                    }
                                                } else { //1
                                                    space[z][y][x].face[(BASE3 - 1) - i][1] = true;
                                                    
                                                } 
                                                /* else { //Other
                                                    if (space[z][y][x].face[(BASE3 - 1) - i][0] == false) {
                                                        space[z][y][x].face[(BASE3 - 1) - i] = true;
                                                    } else {
                                                        space[z][y][x].face[i] = true;
                                                    }
                                                }
                                                * */
                                            } else if (i % BASE == 0) { //Front Left
                                                if (i == (BASE3 - BASE2)) { //18
                                                    if (space[z + modz][y + mody][x + modx].face[(BASE3 - BASE2) - i][0] == false) {
                                                        space[z + modz][y + mody][x + modx].face[(BASE3 - BASE2) - i][1] = true;
                                                    } else {
                                                        space[z][y][x].face[i][1] = true;
                                                    }
                                                } else { //9
                                                    space[z][y][x].face[i][1] = true;
                                                } 
                                            } else if (i == (BASE3 - BASE2) + (BASE - 1) - i) { //Front Center: 10
                                                space[z][y][x].face[i][1] = true;
                                            } else { //Front 
                                                if (space[z + modz][y + mody][x + modx].face[(BASE3 - BASE2) + (BASE - 1) - i][0] == false) {
                                                    space[z + modz][y + mody][x + modx].face[(BASE3 - BASE2) + (BASE - 1) - i][1] = true;
                                                } else {
                                                    space[z][y][x].face[i][1] = true;
                                                }
                                            }
                                        } else if (i < BASE2) { //Top
                                            if (i % BASE == 0) { //Left
                                                if (i == 0 || i == (BASE2 - BASE) - i){
                                                    space[z][y][x].face[i][1] = true;


                                                } else { //6 or 0

                                                    if (space[z + modz][y + mody][x + modx].face[(BASE2 - BASE) - i][0] == false) {
                                                        space[z + modz][y + mody][x + modx].face[(BASE2 - BASE) - i][1] = true;
                                                    } else {
                                                        space[z][y][x].face[i][1] = true;
                                                    }
                                                }
                                            } else if (i == (BASE2 - 1) - i) { //4
                                                space[z][y][x].face[i][1] = true;
                                            } else if (space[z + modz][y + mody][x + modx].face[(BASE2 - 1) - i][0] == false) { //5,8,7
                                                space[z + modz][y + mody][x + modx].face[(BASE2 - 1) - i][1] = true;
                                            } else {
                                                space[z][y][x].face[i][1] = true;
                                            }
                                        } else if (i % BASE == 0) { //Left
                                            if (i == ((BASE3 - BASE) / 2) - i) {
                                                
                                                space[z][y][x].face[i][1] = true;

                                            } else {
                                                if (space[z + modz][y + mody][x + modx].face[(BASE3 - BASE) - i][0] == false) {
                                                    space[z + modz][y + mody][x + modx].face[(BASE3 - BASE) - i][1] = true;
                                                } else {
                                                    space[z][y][x].face[i][1] = true;
                                                }
                                            }
                                        } else {
                                            if (space[z + modz][y + mody][x + modx].face[(BASE3 - 1) - i][0] == false) {
                                                space[z + modz][y + mody][x + modx].face[(BASE3 - 1) - i][1] = true;
                                            } else {
                                                space[z][y][x].face[i][1] = true;
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
                                                    if (space[z + modz][y + mody][x + modx].face[i][0] == false) {
                                                        space[z + modz][y + mody][x + modx].face[i][1] = true;
                                                    } else {
                                                        space[z][y][x].face[i][1] = true;
                                                    }
                                                } else {
                                                    if (space[z + modz][y + mody][x + modx].face[(BASE - 1) - i][0] == false) {
                                                        space[z + modz][y + mody][x + modx].face[(BASE - 1) - i][1] = true;
                                                    } else {
                                                        space[z][y][x].face[i][1] = true;
                                                    }
                                                }
                                            } else if ((i + 1) % BASE == 0) {
                                                if (space[z + modz][y + mody][x + modx].face[(BASE3 - BASE2) + ((BASE - 1) * 2) - i][0] == false) {
                                                    space[z + modz][y + mody][x + modx].face[(BASE3 - BASE2) + ((BASE - 1) * 2) - i][1] = true;
                                                } else {
                                                    space[z][y][x].face[i][1] = true;
                                                }
                                            } else {
                                                if (space[z + modz][y + mody][x + modx].face[(BASE3 - BASE2) + (BASE - 1) - i][0] == false) {
                                                    space[z + modz][y + mody][x + modx].face[(BASE3 - BASE2) + (BASE - 1) - i][1] = true;
                                                } else {
                                                    space[z][y][x].face[i][1] = true;
                                                }
                                            }
                                        } else if (i < BASE2) {
                                            if ((i + 1) % BASE == 0) {
                                                if (space[z + modz][y + mody][x + modx].face[(BASE2 - 1) + (BASE - 1) - i][0] == false) {
                                                    space[z + modz][y + mody][x + modx].face[(BASE2 - 1) + (BASE - 1) - i][1] = true;
                                                } else {
                                                    space[z][y][x].face[i][1] = true;
                                                }
                                            } else {
                                                if (space[z + modz][y + mody][x + modx].face[(BASE2 - 1) - i][0] == false) {
                                                    space[z + modz][y + mody][x + modx].face[(BASE2 - 1) - i][1] = true;
                                                } else {
                                                    space[z][y][x].face[i][1] = true;
                                                }
                                            }
                                        } else if ((i + 1) % BASE == 0) {
                                            if (space[z + modz][y + mody][x + modx].face[(BASE3 - 1) + (BASE - 1) - i][0] == false) {
                                                space[z + modz][y + mody][x + modx].face[(BASE3 - 1) + (BASE - 1) - i][1] = true;
                                            } else {
                                                space[z][y][x].face[i][1] = true;
                                            }
                                        } else {
                                            if (space[z + modz][y + mody][x + modx].face[(BASE3 - 1) - i][0] == false) {
                                                space[z + modz][y + mody][x + modx].face[(BASE3 - 1) - i][1] = true;
                                            } else {
                                                space[z][y][x].face[i][1] = true;
                                            }
                                        }
                                    } else {
                                        //code here for z0,y0,x~
                                        //mody
                                        if (i < BASE2) { //y-
                                            mody = 0;
                                        } else if (i >= (BASE3 - BASE2)) { //y+
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

                                        if (i % BASE2 < BASE) { //front
                                            if (i < BASE){
                                                if(space[z + modz][y + mody][x + modx].face[(BASE - 1) - i][0] == false){
                                                space[z + modz][y + mody][x + modx].face[(BASE - 1) - i][1] = true;
                                                } else {
                                                    space[z][y][x].face[i][1] = true;
                                                }
                                            }else { //rest of front
                                                if (space[z + modz][y + mody][x + modx].face[(BASE3 - BASE2) + (BASE - 1) - i][0] == false) {
                                                    space[z + modz][y + mody][x + modx].face[(BASE3 - BASE2) + (BASE - 1) - i][1] = true;
                                                } else {
                                                    space[z][y][x].face[i][1] = true;
                                                }
                                            }
                                        } else if (i < BASE2) { //top
                                            System.out.println(" " + (z + modz) + " " + (y + mody) + " " + (x + modx) + " " + ((BASE2 - 1) - i));
                                            if (space[z + modz][y + mody][x + modx].face[(BASE2 - 1) - i][0] == false) {
                                                space[z + modz][y + mody][x + modx].face[(BASE2 - 1) - i][1] = true;
                                            } else {
                                                space[z][y][x].face[i][1] = true;

                                            }

                                        } else {
                                            if (space[z + modz][y + mody][x + modx].face[(BASE3 - 1) - i][0] == false) {
                                                space[z + modz][y + mody][x + modx].face[(BASE3 - 1) - i][1] = true;
                                            } else {
                                                space[z][y][x].face[i][1] = true;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } else if (y == MAXY - 1) {
                        for (int x = 0; x < MAXX; x++) {
                            for (int i = 0; i < BASE3; i++) {
                                if (space[z][y][x].face[i][0] == true) {
                                    System.out.println("Now processing space[" + z + "][" + y + "][" + x + "].face[" + i + "].");
                                    space[z][y][x].face[i][0] = false;
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
                                                    if (space[z + modz][y + mody][x + modx].face[i][0] == false) {
                                                        space[z + modz][y + mody][x + modx].face[i][1] = true;
                                                    } else {
                                                        space[z][y][x].face[i][1] = true;
                                                    }
                                                } else {
                                                    if (space[z + modz][y + mody][x + modx].face[(BASE3 - BASE2) - i][0] == false) {
                                                        space[z + modz][y + mody][x + modx].face[(BASE3 - BASE2) - i][1] = true;
                                                    } else {
                                                        space[z][y][x].face[i][1] = true;
                                                    }
                                                }
                                            } else if (i > BASE3 - BASE2) {
                                                if (space[z + modz][y + mody][x + modx].face[((BASE3 - BASE2) * 2) + (BASE - 1) - i][0] == false) {
                                                    space[z + modz][y + mody][x + modx].face[((BASE3 - BASE2) * 2) + (BASE - 1) - i][1] = true;
                                                } else {
                                                    space[z][y][x].face[i][1] = true;
                                                }
                                            } else {
                                                if (space[z + modz][y + mody][x + modx].face[(BASE3 - BASE2) + (BASE - 1) - i][0] == false) {
                                                    space[z + modz][y + mody][x + modx].face[(BASE3 - BASE2) + (BASE - 1) - i][1] = true;
                                                } else {
                                                    space[z][y][x].face[i][1] = true;
                                                }
                                            }
                                        } else if (i % BASE == 0) {
                                            if (i > BASE3 - BASE2) {
                                                if (space[z + modz][y + mody][x + modx].face[((BASE3 - BASE2) * 2) + (BASE2 - BASE) - i][0] == false) {
                                                    space[z + modz][y + mody][x + modx].face[((BASE3 - BASE2) * 2) + (BASE2 - BASE) - i][1] = true;
                                                } else {
                                                    space[z][y][x].face[i][1] = true;
                                                }
                                            } else {
                                                if (space[z + modz][y + mody][x + modx].face[(BASE3 - BASE) - i][0] == false) {
                                                    space[z + modz][y + mody][x + modx].face[(BASE3 - BASE) - i][1] = true;
                                                } else {
                                                    space[z][y][x].face[i][1] = true;
                                                }
                                            }
                                        } else if (i > BASE3 - BASE2) {
                                            if (space[z + modz][y + mody][x + modx].face[(BASE3 - 1) + (BASE3 - BASE2) - i][0] == false) {
                                                space[z + modz][y + mody][x + modx].face[(BASE3 - 1) + (BASE3 - BASE2) - i][1] = true;
                                            } else {
                                                space[z][y][x].face[i][1] = true;
                                            }
                                        } else {
                                            if (space[z + modz][y + mody][x + modx].face[(BASE3 - 1) - i][0] == false) {
                                                space[z + modz][y + mody][x + modx].face[(BASE3 - 1) - i][1] = true;
                                            } else {
                                                space[z][y][x].face[i][1] = true;
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
                                                    if (space[z + modz][y + mody][x + modx].face[i][0] == false) {
                                                        space[z + modz][y + mody][x + modx].face[i][1] = true;
                                                    } else {
                                                        space[z][y][x].face[i][1] = true;
                                                    }

                                                } else {
                                                    if (space[z + modz][y + mody][x + modx].face[(BASE3 - BASE2) + ((BASE - 1) * 2) - i][0] == false) {
                                                        space[z + modz][y + mody][x + modx].face[(BASE3 - BASE2) + ((BASE - 1) * 2) - i][1] = true;
                                                    } else {
                                                        space[z][y][x].face[i][1] = true;
                                                    }

                                                }
                                            } else if (i >= BASE3 - BASE2) {
                                                if (space[z + modz][y + mody][x + modx].face[((BASE3 - BASE2) * 2) + (BASE - 1) - i][0] == false) {
                                                    space[z + modz][y + mody][x + modx].face[((BASE3 - BASE2) * 2) + (BASE - 1) - i][1] = true;
                                                } else {
                                                    space[z][y][x].face[i][1] = true;
                                                }

                                            } else {
                                                if (space[z + modz][y + mody][x + modx].face[(BASE3 - BASE2) + (BASE - 1) - i][0] == false) {
                                                    space[z + modz][y + mody][x + modx].face[(BASE3 - BASE2) + (BASE - 1) - i][1] = true;
                                                } else {
                                                    space[z][y][x].face[i][1] = true;
                                                }

                                            }
                                        } else if ((i + 1) % BASE == 0) {
                                            if (i > BASE3 - BASE2) {
                                                if (space[z + modz][y + mody][x + modx].face[(BASE3 - 1) + ((BASE3 - BASE2) + (BASE - 1)) - i][0] == false) {
                                                    space[z + modz][y + mody][x + modx].face[(BASE3 - 1) + ((BASE3 - BASE2) + (BASE - 1)) - i][1] = true;
                                                } else {
                                                    space[z][y][x].face[i][1] = true;
                                                }

                                            } else {
                                                if (space[z + modz][y + mody][x + modx].face[(BASE3 - 1) + (BASE - 1) - i][0] == false) {
                                                    space[z + modz][y + mody][x + modx].face[(BASE3 - 1) + (BASE - 1) - i][1] = true;
                                                } else {
                                                    space[z][y][x].face[i][1] = true;
                                                }

                                            }
                                        } else if (i >= BASE3 - BASE2) {
                                            if (space[z + modz][y + mody][x + modx].face[(BASE3 - BASE2) + (BASE3 - 1) - i][0] == false) {
                                                space[z + modz][y + mody][x + modx].face[(BASE3 - BASE2) + (BASE3 - 1) - i][1] = true;
                                            } else {
                                                space[z][y][x].face[i][1] = true;
                                            }

                                        } else {
                                            if (space[z + modz][y + mody][x + modx].face[(BASE3 - 1) - i][0] == false) {
                                                space[z + modz][y + mody][x + modx].face[(BASE3 - 1) - i][1] = true;
                                            } else {
                                                space[z][y][x].face[i][1] = true;
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
                                                if (space[z + modz][y + mody][x + modx].face[((BASE3 - BASE2) * 2) + (BASE - 1) - i][0] == false) {
                                                    space[z + modz][y + mody][x + modx].face[((BASE3 - BASE2) * 2) + (BASE - 1) - i][1] = true;
                                                } else {
                                                    space[z][y][x].face[i][1] = true;
                                                }

                                            } else {
                                                if (space[z + modz][y + mody][x + modx].face[(BASE3 - BASE2) + (BASE - 1) - i][0] == false) {
                                                    space[z + modz][y + mody][x + modx].face[(BASE3 - BASE2) + (BASE - 1) - i][1] = true;
                                                } else {
                                                    space[z][y][x].face[i][1] = true;
                                                }

                                            }
                                        } else if (i > BASE3 - BASE2) {
                                            if (space[z + modz][y + mody][x + modx].face[(BASE3 - BASE2) + (BASE3 - 1) - i][0] == false) {
                                                space[z + modz][y + mody][x + modx].face[(BASE3 - BASE2) + (BASE3 - 1) - i][1] = true;
                                            } else {
                                                space[z][y][x].face[i][1] = true;
                                            }

                                        } else {
                                            if (space[z + modz][y + mody][x + modx].face[(BASE3 - 1) - i][0] == false) {
                                                space[z + modz][y + mody][x + modx].face[(BASE3 - 1) - i][1] = true;
                                            } else {
                                                space[z][y][x].face[i][1] = true;
                                            }

                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        for (int x = 0; x < MAXX; x++) {
                            for (int i = 0; i < BASE3; i++) {
                                if (space[z][y][x].face[i][0] == true) {
                                    System.out.println("Now processing space[" + z + "][" + y + "][" + x + "].face[" + i + "].");
                                    space[z][y][x].face[i][0] = false;
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
                                                if (space[z + modz][y + mody][x + modx].face[(BASE3 - BASE2) - i][0] == false) {
                                                    space[z + modz][y + mody][x + modx].face[(BASE3 - BASE2) - i][1] = true;
                                                } else {
                                                    space[z][y][x].face[i][1] = true;
                                                }

                                            } else {
                                                if (space[z + modz][y + mody][x + modx].face[(BASE3 - BASE2) + (BASE - 1) - i][0] == false) {
                                                    space[z + modz][y + mody][x + modx].face[(BASE3 - BASE2) + (BASE - 1) - i][1] = true;
                                                } else {
                                                    space[z][y][x].face[i][1] = true;
                                                }

                                            }
                                        } else if (i % BASE == 0) {
                                            if (space[z + modz][y + mody][x + modx].face[(BASE3 - BASE) - i][0] == false) {
                                                space[z + modz][y + mody][x + modx].face[(BASE3 - BASE) - i][1] = true;
                                            } else {
                                                space[z][y][x].face[i][1] = true;
                                            }

                                        } else {
                                            if (space[z + modz][y + mody][x + modx].face[(BASE3 - 1) - i][0] == false) {
                                                space[z + modz][y + mody][x + modx].face[(BASE3 - 1) - i][1] = true;
                                            } else {
                                                space[z][y][x].face[i][1] = true;
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
                                                if (space[z + modz][y + mody][x + modx].face[(BASE3 - BASE2) + ((BASE - 1) * 2) - i][0] == false) {
                                                    space[z + modz][y + mody][x + modx].face[(BASE3 - BASE2) + ((BASE - 1) * 2) - i][1] = true;
                                                } else {
                                                    space[z][y][x].face[i][1] = true;
                                                }

                                            } else {
                                                if (space[z + modz][y + mody][x + modx].face[(BASE3 - BASE2) + (BASE - 1) - i][0] == false) {
                                                    space[z + modz][y + mody][x + modx].face[(BASE3 - BASE2) + (BASE - 1) - i][1] = true;
                                                } else {
                                                    space[z][y][x].face[i][1] = true;
                                                }

                                            }
                                        } else if ((i + 1) % BASE == 0) {
                                            if (space[z + modz][y + mody][x + modx].face[(BASE3 - 1) + (BASE - 1) - i][0] == false) {
                                                space[z + modz][y + mody][x + modx].face[(BASE3 - 1) + (BASE - 1) - i][1] = true;
                                            } else {
                                                space[z][y][x].face[i][1] = true;
                                            }

                                        } else {
                                            if (space[z + modz][y + mody][x + modx].face[(BASE3 - 1) - i][0] == false) {
                                                space[z + modz][y + mody][x + modx].face[(BASE3 - 1) - i][1] = true;
                                            } else {
                                                space[z][y][x].face[i][1] = true;
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
                                            if (space[z + modz][y + mody][x + modx].face[(BASE3 - BASE2) + (BASE - 1) - i][0] == false) {
                                                space[z + modz][y + mody][x + modx].face[(BASE3 - BASE2) + (BASE - 1) - i][1] = true;
                                            } else {
                                                space[z][y][x].face[i][1] = true;
                                            }

                                        } else {
                                            if (space[z + modz][y + mody][x + modx].face[(BASE3 - 1) - i][0] == false) {
                                                space[z + modz][y + mody][x + modx].face[(BASE3 - 1) - i][1] = true;
                                            } else {
                                                space[z][y][x].face[i][1] = true;
                                            }

                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } else if (z == MAXZ
                    - 1) {
                for (int y = 0; y < MAXY; y++) {
                    if (y == 0) {
                        for (int x = 0; x < MAXX; x++) {
                            for (int i = 0; i < BASE3; i++) {
                                if (space[z][y][x].face[i][0] == true) {
                                    System.out.println("Now processing space[" + z + "][" + y + "][" + x + "].face[" + i + "].");
                                    space[z][y][x].face[i][0] = false;
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
                                                    if (space[z + modz][y + mody][x + modx].face[i][0] == false) {
                                                        space[z + modz][y + mody][x + modx].face[i][1] = true;
                                                    } else {
                                                        space[z][y][x].face[i][1] = true;
                                                    }

                                                } else {
                                                    if (space[z + modz][y + mody][x + modx].face[(BASE2 - 1) + (BASE2 - BASE) - i][0] == false) {
                                                        space[z + modz][y + mody][x + modx].face[(BASE2 - 1) + (BASE2 - BASE) - i][1] = true;
                                                    } else {
                                                        space[z][y][x].face[i][1] = true;
                                                    }

                                                }
                                            } else if (i % BASE == 0) {
                                                if (space[z + modz][y + mody][x + modx].face[(BASE3 - BASE) + (BASE2 - BASE) - i][0] == false) {
                                                    space[z + modz][y + mody][x + modx].face[(BASE3 - BASE) + (BASE2 - BASE) - i][1] = true;
                                                } else {
                                                    space[z][y][x].face[i][1] = true;
                                                }

                                            } else {
                                                if (space[z + modz][y + mody][x + modx].face[(BASE3 - 1) + (BASE2 - BASE) - i][0] == false) {
                                                    space[z + modz][y + mody][x + modx].face[(BASE3 - 1) + (BASE2 - BASE) - i][1] = true;
                                                } else {
                                                    space[z][y][x].face[i][1] = true;
                                                }

                                            }
                                        } else if (i < BASE) {
                                            if (i % BASE == 0) {
                                                if (space[z + modz][y + mody][x + modx].face[(BASE2 - BASE) - i][0] == false) {
                                                    space[z + modz][y + mody][x + modx].face[(BASE2 - BASE) - i][1] = true;
                                                } else {
                                                    space[z][y][x].face[i][1] = true;
                                                }

                                            } else {
                                                if (i == (BASE2 - 1) / 2) {
                                                    space[z][y][x].face[i][1] = true;
                                                } else if (space[z + modz][y + mody][x + modx].face[(BASE2 - 1) - i][0] == false) {
                                                    space[z + modz][y + mody][x + modx].face[(BASE2 - 1) - i][1] = true;
                                                } else {
                                                    space[z][y][x].face[i][1] = true;
                                                }

                                            }
                                        } else if (i % BASE == 0) {
                                            if (space[z + modz][y + mody][x + modx].face[(BASE3 - BASE) - i][0] == false) {
                                                space[z + modz][y + mody][x + modx].face[(BASE3 - BASE) - i][1] = true;
                                            } else {
                                                space[z][y][x].face[i][1] = true;
                                            }

                                        } else {
                                            if (space[z + modz][y + mody][x + modx].face[(BASE3 - 1) - i][0] == false) {
                                                space[z + modz][y + mody][x + modx].face[(BASE3 - 1) - i][1] = true;
                                            } else {
                                                space[z][y][x].face[i][1] = true;
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
                                                    if (space[z][y][x].face[i][0] == false) {
                                                        space[z][y][x].face[i][1] = true;
                                                    } else {
                                                        space[z][y][x].face[i][1] = true;
                                                    }

                                                } else {
                                                    if (space[z + modz][y + mody][x + modx].face[(BASE2 - BASE) + (BASE2 - 1) - i][0] == false) {
                                                        space[z + modz][y + mody][x + modx].face[(BASE2 - BASE) + (BASE2 - 1) - i][1] = true;
                                                    } else {
                                                        space[z][y][x].face[i][1] = true;
                                                    }

                                                }
                                            } else if ((i + 1) % BASE == 0) {
                                                if (space[z + modz][y + mody][x + modx].face[(BASE3 - 1) + (BASE2 - 1) - i][0] == false) {
                                                    space[z + modz][y + mody][x + modx].face[(BASE3 - 1) + (BASE2 - 1) - i][1] = true;
                                                } else {
                                                    space[z][y][x].face[i][1] = true;
                                                }

                                            } else {
                                                if (space[z + modz][y + mody][x + modx].face[(BASE3 - BASE) + (BASE2 - 1) - i][0] == false) {
                                                    space[z + modz][y + mody][x + modx].face[(BASE3 - BASE) + (BASE2 - 1) - i][1] = true;
                                                } else {
                                                    space[z][y][x].face[i][1] = true;
                                                }

                                            }
                                        } else if (i < BASE2) {
                                            if ((i + 1) % BASE == 0) {
                                                if (space[z + modz][y + mody][x + modx].face[(BASE2 - 1) + (BASE - 1) - i][0] == false) {
                                                    space[z + modz][y + mody][x + modx].face[(BASE2 - 1) + (BASE - 1) - i][1] = true;
                                                } else {
                                                    space[z][y][x].face[i][1] = true;
                                                }

                                            } else {
                                                if (space[z + modz][y + mody][x + modx].face[(BASE2 - 1) - i][0] == false) {
                                                    space[z + modz][y + mody][x + modx].face[(BASE2 - 1) - i][1] = true;
                                                } else {
                                                    space[z][y][x].face[i][1] = true;
                                                }

                                            }
                                        } else if ((i + 1) % BASE == 0) {
                                            if (space[z + modz][y + mody][x + modx].face[(BASE3 - 1) + (BASE - 1) - i][0] == false) {
                                                space[z + modz][y + mody][x + modx].face[(BASE3 - 1) + (BASE - 1) - i][1] = true;
                                            } else {
                                                space[z][y][x].face[i][1] = true;
                                            }

                                        } else {
                                            if (space[z + modz][y + mody][x + modx].face[(BASE3 - 1) - i][0] == false) {
                                                space[z + modz][y + mody][x + modx].face[(BASE3 - 1) - i][1] = true;
                                            } else {
                                                space[z][y][x].face[i][1] = true;
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
                                                if (space[z + modz][y + mody][x + modx].face[(BASE2 - BASE) + (BASE2 - 1) - i][0] == false) {
                                                    space[z + modz][y + mody][x + modx].face[(BASE2 - BASE) + (BASE2 - 1) - i][1] = true;
                                                } else {
                                                    space[z][y][x].face[i][1] = true;
                                                }

                                            } else {
                                                if (space[z + modz][y + mody][x + modx].face[(BASE3 - BASE) + (BASE2 - 1) - i][0] == false) {
                                                    space[z + modz][y + mody][x + modx].face[(BASE3 - BASE) + (BASE2 - 1) - i][1] = true;
                                                } else {
                                                    space[z][y][x].face[i][1] = true;
                                                }

                                            }
                                        } else if (i < BASE2) {
                                            if (space[z + modz][y + mody][x + modx].face[(BASE2 - 1) - i][0] == false) {
                                                space[z + modz][y + mody][x + modx].face[(BASE2 - 1) - i][1] = true;
                                            } else {
                                                space[z][y][x].face[i][1] = true;
                                            }

                                        } else {
                                            if (space[z + modz][y + mody][x + modx].face[(BASE3 - 1) - i][0] == false) {
                                                space[z + modz][y + mody][x + modx].face[(BASE3 - 1) - i][1] = true;
                                            } else {
                                                space[z][y][x].face[i][1] = true;
                                            }

                                        }
                                    }
                                }
                            }
                        }
                    } else if (y == MAXY - 1) {
                        for (int x = 0; x < MAXX; x++) {
                            for (int i = 0; i < BASE3; i++) {
                                if (space[z][y][x].face[i][0] == true) {
                                    System.out.println("Now processing space[" + z + "][" + y + "][" + x + "].face[" + i + "].");
                                    space[z][y][x].face[i][0] = false;
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
                                                    if (space[z + modz][y + mody][x + modx].face[i][0] == false) {
                                                        space[z + modz][y + mody][x + modx].face[i][1] = true;
                                                    } else {
                                                        space[z][y][x].face[i][1] = true;
                                                    }

                                                } else {
                                                    if (space[z + modz][y + mody][x + modx].face[(BASE3 - 1) + (BASE3 - BASE) - i][0] == false) {
                                                        space[z + modz][y + mody][x + modx].face[(BASE3 - 1) + (BASE3 - BASE) - i][1] = true;
                                                    } else {
                                                        space[z][y][x].face[i][1] = true;
                                                    }

                                                }
                                            } else if (i % BASE == 0) {
                                                if (space[z + modz][y + mody][x + modx].face[(BASE3 - BASE) + (BASE2 - BASE) - i][0] == false) {
                                                    space[z + modz][y + mody][x + modx].face[(BASE3 - BASE) + (BASE2 - BASE) - i][1] = true;
                                                } else {
                                                    space[z][y][x].face[i][1] = true;
                                                }

                                            } else {
                                                if (space[z + modz][y + mody][x + modx].face[(BASE3 - BASE) + (BASE2 - 1) - i][0] == false) {
                                                    space[z + modz][y + mody][x + modx].face[(BASE3 - BASE) + (BASE2 - 1) - i][1] = true;
                                                } else {
                                                    space[z][y][x].face[i][1] = true;
                                                }

                                            }
                                        } else if (i >= BASE3 - BASE2) {
                                            if (i % BASE == 0) {
                                                if (space[z + modz][y + mody][x + modx].face[(BASE3 - BASE2) + (BASE3 - BASE) - i][0] == false) {
                                                    space[z + modz][y + mody][x + modx].face[(BASE3 - BASE2) + (BASE3 - BASE) - i][1] = true;
                                                } else {
                                                    space[z][y][x].face[i][1] = true;
                                                }

                                            } else {
                                                if (space[z + modz][y + mody][x + modx].face[(BASE3 - 1) + (BASE3 - BASE2) - i][0] == false) {
                                                    space[z + modz][y + mody][x + modx].face[(BASE3 - 1) + (BASE3 - BASE2) - i][1] = true;
                                                } else {
                                                    space[z][y][x].face[i][1] = true;
                                                }

                                            }
                                        } else if (i % BASE == 0) {
                                            if (space[z + modz][y + mody][x + modx].face[(BASE3 - BASE2) + (BASE2 - BASE) - i][0] == false) {
                                                space[z + modz][y + mody][x + modx].face[(BASE3 - BASE2) + (BASE2 - BASE) - i][1] = true;
                                            } else {
                                                space[z][y][x].face[i][1] = true;
                                            }

                                        } else {
                                            if (space[z + modz][y + mody][x + modx].face[(BASE3 - 1) - i][0] == false) {
                                                space[z + modz][y + mody][x + modx].face[(BASE3 - 1) - i][1] = true;
                                            } else {
                                                space[z][y][x].face[i][1] = true;
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
                                                    if (space[z][y][x].face[i][0] == false) {
                                                        space[z][y][x].face[i][1] = true;
                                                    } else {
                                                        space[z][y][x].face[i][1] = true;
                                                    }//wat?

                                                } else {
                                                    if (space[z + modz][y + mody][x + modx].face[(BASE3 - 1) + (BASE3 - BASE) - i][0] == false) {
                                                        space[z + modz][y + mody][x + modx].face[(BASE3 - 1) + (BASE3 - BASE) - i][1] = true;
                                                    } else {
                                                        space[z][y][x].face[i][1] = true;
                                                    }

                                                }
                                            } else if ((i + 1) % BASE == 0) {
                                                if (space[z + modz][y + mody][x + modx].face[(BASE3 - 1) + (BASE2 - 1) - i][0] == false) {
                                                    space[z + modz][y + mody][x + modx].face[(BASE3 - 1) + (BASE2 - 1) - i][1] = true;
                                                } else {
                                                    space[z][y][x].face[i][1] = true;
                                                }

                                            } else {
                                                if (space[z + modz][y + mody][x + modx].face[(BASE3 - 1) + (BASE2 - BASE) - i][0] == false) {
                                                    space[z + modz][y + mody][x + modx].face[(BASE3 - 1) + (BASE2 - BASE) - i][1] = true;
                                                } else {
                                                    space[z][y][x].face[i][1] = true;
                                                }


                                            }
                                        } else if (i >= (BASE3 - BASE2)) {
                                            if ((i + 1) % BASE == 0) {
                                                if (space[z + modz][y + mody][x + modx].face[(BASE3 - 1) + (BASE3 - BASE2) + (BASE - 1) - i][0] == false) {
                                                    space[z + modz][y + mody][x + modx].face[(BASE3 - 1) + (BASE3 - BASE2) + (BASE - 1) - i][1] = true;
                                                } else {
                                                    space[z][y][x].face[i][1] = true;
                                                }

                                            } else {
                                                if (space[z + modz][y + mody][x + modx].face[(BASE3 - 1) + (BASE3 - BASE2) - i][0] == false) {
                                                    space[z + modz][y + mody][x + modx].face[(BASE3 - 1) + (BASE3 - BASE2) - i][1] = true;
                                                } else {
                                                    space[z][y][x].face[i][1] = true;
                                                }

                                            }
                                        } else if ((i + 1) % BASE == 0) {
                                            if (space[z + modz][y + mody][x + modx].face[(BASE3 - 1) + (BASE - 1) - i][0] == false) {
                                                space[z + modz][y + mody][x + modx].face[(BASE3 - 1) + (BASE - 1) - i][1] = true;
                                            } else {
                                                space[z][y][x].face[i][1] = true;
                                            }

                                        } else {
                                            if (space[z + modz][y + mody][x + modx].face[(BASE3 - 1) - i][0] == false) {
                                                space[z + modz][y + mody][x + modx].face[(BASE3 - 1) - i][1] = true;
                                            } else {
                                                space[z][y][x].face[i][1] = true;
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
                                                if (space[z + modz][y + mody][x + modx].face[(BASE3 - 1) + (BASE3 - BASE) - i][0] == false) {
                                                    space[z + modz][y + mody][x + modx].face[(BASE3 - 1) + (BASE3 - BASE) - i][1] = true;
                                                } else {
                                                    space[z][y][x].face[i][1] = true;
                                                }

                                            } else {
                                                if (space[z + modz][y + mody][x + modx].face[(BASE3 - 1) + (BASE2 - BASE) - i][0] == false) {
                                                    space[z + modz][y + mody][x + modx].face[(BASE3 - 1) + (BASE2 - BASE) - i][1] = true;
                                                } else {
                                                    space[z][y][x].face[i][1] = true;
                                                }

                                            }
                                        } else if (i >= BASE3 - BASE2) {
                                            if (space[z + modz][y + mody][x + modx].face[(BASE3 - 1) + (BASE3 - BASE2) - i][0] == false) {
                                                space[z + modz][y + mody][x + modx].face[(BASE3 - 1) + (BASE3 - BASE2) - i][1] = true;
                                            } else {
                                                space[z][y][x].face[i][1] = true;
                                            }

                                        } else {
                                            if (space[z + modz][y + mody][x + modx].face[(BASE3 - 1) - i][0] == false) {
                                                space[z + modz][y + mody][x + modx].face[(BASE3 - 1) - i][1] = true;
                                            } else {
                                                space[z][y][x].face[i][1] = true;
                                            }

                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        for (int x = 0; x < MAXX; x++) {
                            for (int i = 0; i < BASE3; i++) {
                                if (space[z][y][x].face[i][0] == true) {
                                    System.out.println("Now processing space[" + z + "][" + y + "][" + x + "].face[" + i + "].");
                                    space[z][y][x].face[i][0] = false;
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
                                                if (space[z + modz][y + mody][x + modx].face[(BASE3 - BASE) + (BASE2 - BASE) - i][0] == false) {
                                                    space[z + modz][y + mody][x + modx].face[(BASE3 - BASE) + (BASE2 - BASE) - i][1] = true;
                                                } else {
                                                    space[z][y][x].face[i][1] = true;
                                                }

                                            } else {
                                                if (space[z + modz][y + mody][x + modx].face[(BASE3 - 1) + (BASE2 - BASE) - i][0] == false) {
                                                    space[z + modz][y + mody][x + modx].face[(BASE3 - 1) + (BASE2 - BASE) - i][1] = true;
                                                } else {
                                                    space[z][y][x].face[i][1] = true;
                                                }

                                            }
                                        } else if (i % BASE == 0) {
                                            if (space[z + modz][y + mody][x + modx].face[(BASE3 - BASE) - i][0] == false) {
                                                space[z + modz][y + mody][x + modx].face[(BASE3 - BASE) - i][1] = true;
                                            } else {
                                                space[z][y][x].face[i][1] = true;
                                            }

                                        } else {
                                            if (space[z + modz][y + mody][x + modx].face[(BASE3 - 1) - i][0] == false) {
                                                space[z + modz][y + mody][x + modx].face[(BASE3 - 1) - i][1] = true;
                                            } else {
                                                space[z][y][x].face[i][1] = true;
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
                                                if (space[z + modz][y + mody][x + modx].face[(BASE3 - 1) + (BASE2 - 1) - i][0] == false) {
                                                    space[z + modz][y + mody][x + modx].face[(BASE3 - 1) + (BASE2 - 1) - i][1] = true;
                                                } else {
                                                    space[z][y][x].face[i][1] = true;
                                                }

                                            } else {
                                                if (space[z + modz][y + mody][x + modx].face[(BASE3 - 1) + (BASE2 - BASE) - i][0] == false) {
                                                    space[z + modz][y + mody][x + modx].face[(BASE3 - 1) + (BASE2 - BASE) - i][1] = true;
                                                } else {
                                                    space[z][y][x].face[i][1] = true;
                                                }

                                            }
                                        } else if ((i + 1) % BASE == 0) {
                                            if (space[z + modz][y + mody][x + modx].face[(BASE3 - 1) + (BASE - 1) - i][0] == false) {
                                                space[z + modz][y + mody][x + modx].face[(BASE3 - 1) + (BASE - 1) - i][1] = true;
                                            } else {
                                                space[z][y][x].face[i][1] = true;
                                            }

                                        } else {
                                            if (space[z + modz][y + mody][x + modx].face[(BASE3 - 1) - i][0] == false) {
                                                space[z + modz][y + mody][x + modx].face[(BASE3 - 1) - i][1] = true;
                                            } else {
                                                space[z][y][x].face[i][1] = true;
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
                                            if (space[z + modz][y + mody][x + modx].face[(BASE3 - 1) + (BASE2 - BASE) - i][0] == false) {
                                                space[z + modz][y + mody][x + modx].face[(BASE3 - 1) + (BASE2 - BASE) - i][1] = true;
                                            } else {
                                                space[z][y][x].face[i][1] = true;
                                            }

                                        } else {
                                            if (space[z + modz][y + mody][x + modx].face[(BASE3 - 1) - i][0] == false) {
                                                space[z + modz][y + mody][x + modx].face[(BASE3 - 1) - i][1] = true;
                                            } else {
                                                space[z][y][x].face[i][1] = true;
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
                                if (space[z][y][x].face[i][0] == true) {
                                    System.out.println("Now processing space[" + z + "][" + y + "][" + x + "].face[" + i + "].");
                                    space[z][y][x].face[i][0] = false;
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
                                                if (space[z + modz][y + mody][x + modx].face[(BASE2 - BASE) - i][0] == false) {
                                                    space[z + modz][y + mody][x + modx].face[(BASE2 - BASE) - i][1] = true;
                                                } else {
                                                    space[z][y][x].face[i][1] = true;
                                                }

                                            } else {
                                                if (space[z + modz][y + mody][x + modx].face[(BASE2 - 1) - i][0] == false) {
                                                    space[z + modz][y + mody][x + modx].face[(BASE2 - 1) - i][1] = true;
                                                } else {
                                                    space[z][y][x].face[i][1] = true;
                                                }

                                            }
                                        } else if (i % BASE == 0) {
                                            if (space[z + modz][y + mody][x + modx].face[(BASE3 - BASE) - i][0] == false) {
                                                space[z + modz][y + mody][x + modx].face[(BASE3 - BASE) - i][1] = true;
                                            } else {
                                                space[z][y][x].face[i][1] = true;
                                            }

                                        } else {
                                            if (space[z + modz][y + mody][x + modx].face[(BASE3 - 1) - i][0] == false) {
                                                space[z + modz][y + mody][x + modx].face[(BASE3 - 1) - i][1] = true;
                                            } else {
                                                space[z][y][x].face[i][1] = true;
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
                                                if (space[z + modz][y + mody][x + modx].face[(BASE2 - 1) + (BASE - 1) - i][0] == false) {
                                                    space[z + modz][y + mody][x + modx].face[(BASE2 - 1) + (BASE - 1) - i][1] = true;
                                                } else {
                                                    space[z][y][x].face[i][1] = true;
                                                }

                                            } else {
                                                if (space[z + modz][y + mody][x + modx].face[(BASE2 - 1) - i][0] == false) {
                                                    space[z + modz][y + mody][x + modx].face[(BASE2 - 1) - i][1] = true;
                                                } else {
                                                    space[z][y][x].face[i][1] = true;
                                                }

                                            }
                                        } else if ((i + 1) % BASE == 0) {
                                            if (space[z + modz][y + mody][x + modx].face[(BASE3 - 1) + (BASE - 1) - i][0] == false) {
                                                space[z + modz][y + mody][x + modx].face[(BASE3 - 1) + (BASE - 1) - i][1] = true;
                                            } else {
                                                space[z][y][x].face[i][1] = true;
                                            }

                                        } else {
                                            if (space[z + modz][y + mody][x + modx].face[(BASE3 - 1) - i][0] == false) {
                                                space[z + modz][y + mody][x + modx].face[(BASE3 - 1) - i][1] = true;
                                            } else {
                                                space[z][y][x].face[i][1] = true;
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
                                            if (space[z + modz][y + mody][x + modx].face[(BASE2 - 1) - i][0] == false) {
                                                space[z + modz][y + mody][x + modx].face[(BASE2 - 1) - i][1] = true;
                                            } else {
                                                space[z][y][x].face[i][1] = true;
                                            }

                                        } else {
                                            if (space[z + modz][y + mody][x + modx].face[(BASE3 - 1) - i][0] == false) {
                                                space[z + modz][y + mody][x + modx].face[(BASE3 - 1) - i][1] = true;
                                            } else {
                                                space[z][y][x].face[i][1] = true;
                                            }

                                        }
                                    }
                                }
                            }
                        }
                    } else if (y == MAXY - 1) {
                        for (int x = 0; x < MAXX; x++) {
                            for (int i = 0; i < BASE3; i++) {
                                if (space[z][y][x].face[i][0] == true) {
                                    System.out.println("Now processing space[" + z + "][" + y + "][" + x + "].face[" + i + "].");
                                    space[z][y][x].face[i][0] = false;
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
                                                if (space[z + modz][y + mody][x + modx].face[(BASE3 - BASE) + (BASE3 - BASE2) - i][0] == false) {
                                                    space[z + modz][y + mody][x + modx].face[(BASE3 - BASE) + (BASE3 - BASE2) - i][1] = true;
                                                } else {
                                                    space[z][y][x].face[i][1] = true;
                                                }

                                            } else {
                                                if (space[z + modz][y + mody][x + modx].face[(BASE3 - 1) + (BASE3 - BASE2) - i][0] == false) {
                                                    space[z + modz][y + mody][x + modx].face[(BASE3 - 1) + (BASE3 - BASE2) - i][1] = true;
                                                } else {
                                                    space[z][y][x].face[i][1] = true;
                                                }

                                            }
                                        } else if (i % BASE == 0) {
                                            if (space[z + modz][y + mody][x + modx].face[(BASE3 - BASE) - i][0] == false) {
                                                space[z + modz][y + mody][x + modx].face[(BASE3 - BASE) - i][1] = true;
                                            } else {
                                                space[z][y][x].face[i][1] = true;
                                            }

                                        } else {
                                            if (space[z + modz][y + mody][x + modx].face[(BASE3 - 1) - i][0] == false) {
                                                space[z + modz][y + mody][x + modx].face[(BASE3 - 1) - i][1] = true;
                                            } else {
                                                space[z][y][x].face[i][1] = true;
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
                                                if (space[z + modz][y + mody][x + modx].face[(BASE3 - 1) + (BASE3 - BASE2) + (BASE - 1) - i][0] == false) {
                                                    space[z + modz][y + mody][x + modx].face[(BASE3 - 1) + (BASE3 - BASE2) + (BASE - 1) - i][1] = true;
                                                } else {
                                                    space[z][y][x].face[i][1] = true;
                                                }

                                            } else {
                                                if (space[z + modz][y + mody][x + modx].face[(BASE3 - 1) + (BASE3 - BASE2) - i][0] == false) {
                                                    space[z + modz][y + mody][x + modx].face[(BASE3 - 1) + (BASE3 - BASE2) - i][1] = true;
                                                } else {
                                                    space[z][y][x].face[i][1] = true;
                                                }

                                            }
                                        } else if ((i + 1) % BASE == 0) {
                                            if (space[z + modz][y + mody][x + modx].face[(BASE3 - 1) + (BASE - 1) - i][0] == false) {
                                                space[z + modz][y + mody][x + modx].face[(BASE3 - 1) + (BASE - 1) - i][1] = true;
                                            } else {
                                                space[z][y][x].face[i][1] = true;
                                            }

                                        } else {
                                            if (space[z + modz][y + mody][x + modx].face[(BASE3 - 1) - i][0] == false) {
                                                space[z + modz][y + mody][x + modx].face[(BASE3 - 1) - i][1] = true;
                                            } else {
                                                space[z][y][x].face[i][1] = true;
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
                                            if (space[z + modz][y + mody][x + modx].face[(BASE3 - 1) + (BASE3 - BASE2) - i][0] == false) {
                                                space[z + modz][y + mody][x + modx].face[(BASE3 - 1) + (BASE3 - BASE2) - i][1] = true;
                                            } else {
                                                space[z][y][x].face[i][1] = true;
                                            }

                                        } else {
                                            if (space[z + modz][y + mody][x + modx].face[(BASE3 - 1) - i][0] == false) {
                                                space[z + modz][y + mody][x + modx].face[(BASE3 - 1) - i][1] = true;
                                            } else {
                                                space[z][y][x].face[i][1] = true;
                                            }

                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        for (int x = 0; x < MAXX; x++) {
                            for (int i = 0; i < BASE3; i++) {
                                if (space[z][y][x].face[i][0] == true) {
                                    System.out.println("Now processing space[" + z + "][" + y + "][" + x + "].face[" + i + "].");
                                    space[z][y][x].face[i][0] = false;
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
                                            if (space[z + modz][y + mody][x + modx].face[(BASE3 - BASE) - i][0] == false) {
                                                space[z + modz][y + mody][x + modx].face[(BASE3 - BASE) - i][1] = true;
                                            }
                                            
                                            /*else {
                                                space[z][y][x].face[i][1] = true;
                                            }
                                            */
                                        } else {
                                            if (space[z + modz][y + mody][x + modx].face[(BASE3 - 1) - i][0] == false) {
                                                space[z + modz][y + mody][x + modx].face[(BASE3 - 1) - i][1] = true;
                                            } else {
                                                space[z][y][x].face[i][1] = true;
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
                                            if (space[z + modz][y + mody][x + modx].face[(BASE3 - 1) + (BASE - 1) - i][0] == false) {
                                                space[z + modz][y + mody][x + modx].face[(BASE3 - 1) + (BASE - 1) - i][1] = true;
                                            } else {
                                                space[z][y][x].face[i][1] = true;
                                            }

                                        } else {
                                            if (space[z + modz][y + mody][x + modx].face[(BASE3 - 1) - i][0] == false) {
                                                space[z + modz][y + mody][x + modx].face[(BASE3 - 1) - i][1] = true;
                                            } else {
                                                space[z][y][x].face[i][1] = true;
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
                                        if (space[z + modz][y + mody][x + modx].face[(BASE3 - 1) - i][0] == false) {
                                            space[z + modz][y + mody][x + modx].face[(BASE3 - 1) - i][1] = true;
                                        } else {
                                            space[z][y][x].face[i][1] = true;
                                        }

                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

