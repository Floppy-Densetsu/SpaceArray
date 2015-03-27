/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.altspace;

/**
 *
 * @author Flopp_000
 */
public class World implements Runnable{

    static int MAXZ = 2;
    static int MAXY = 690;
    static int MAXX = 690;
    static boolean[][][] space = new boolean[MAXZ][MAXY][MAXX];
    static Thread loop;
    static java.awt.Graphics2D g2d;
    static java.awt.image.BufferedImage display;
    static Frame frame;
    
    public static void main(String[] args) {
        frame = new Frame();
        frame.setSize(1424, 1424);
        frame.setTitle("Testing");
        frame.setDefaultCloseOperation(Frame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        display = new java.awt.image.BufferedImage(1400, 1400, java.awt.image.BufferedImage.TYPE_INT_RGB);
        g2d = display.createGraphics();

        for (int z = 0; z < MAXZ; z++) {
            for (int y = 0; y < MAXY; y++) {
                for (int x = 0; x < MAXX; x++) {
                    space[z][y][x] = false;
                }
            }
        }
        
        space[0][0][0] = true;
        
        //drawScreen();
        fireSpace();
        drawScreen();
        frame.repaint();
        //loop = new Thread(new World());
        //loop.start();
    }
    
    static class Frame extends javax.swing.JFrame{
        
    public void paint(java.awt.Graphics g){
        g.drawImage(display, 0, 0, rootPane);
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
                fireSpace();
                drawScreen();
                frame.repaint();
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void drawScreen(){
        g2d.clearRect(0, 0, display.getWidth(), display.getHeight());
        for(int z = 0; z < MAXZ; z++){
            for(int y = 0; y < MAXY; y++){
                for(int x = 0; x < MAXX; x++){
                    if(space[z][y][x] == true){
                        //g2d.setColor(java.awt.Color.CYAN);
                        //g2d.fillRect(20, 20, 200, 200);
                       // display.setRGB(200 + y, 200 + x, 12000);
                        display.setRGB(((z % 2) * (MAXX + 1) + x) + 15, (((z - (z % 2)) / 2) * (MAXY + 1) + y) + 50, java.awt.Color.CYAN.getRGB());
                    }
                }
            }
        }
    }
    
    public static void trigger(int z, int y, int x, boolean[] mods) {
        if (mods[0] == true) {
            if (mods[1] == true) {
                if (mods[2] == true) {
                    if (mods[3] == true) {
                        if (mods[4] == true) {
                            if (mods[5] == true) {
                                if (space[z + 1][y + 1][x + 1] == true) {
                                    space[z + 1][y + 1][x + 1] = false;
                                } else {
                                    space[z + 1][y + 1][x + 1] = true;
                                }

                                if (space[z + 1][y + 1][x - 1] == true) {
                                    space[z + 1][y + 1][x - 1] = false;
                                } else {
                                    space[z + 1][y + 1][x - 1] = true;
                                }

                                if (space[z + 1][y + 1][x] == true) {
                                    space[z + 1][y + 1][x] = false;
                                } else {
                                    space[z + 1][y + 1][x] = true;
                                }

                                if (space[z + 1][y - 1][x + 1] == true) {
                                    space[z + 1][y - 1][x + 1] = false;
                                } else {
                                    space[z + 1][y - 1][x + 1] = true;
                                }

                                if (space[z + 1][y - 1][x - 1] == true) {
                                    space[z + 1][y - 1][x - 1] = false;
                                } else {
                                    space[z + 1][y - 1][x - 1] = true;
                                }

                                if (space[z + 1][y - 1][x] == true) {
                                    space[z + 1][y - 1][x] = false;
                                } else {
                                    space[z + 1][y - 1][x] = true;
                                }

                                if (space[z + 1][y][x + 1] == true) {
                                    space[z + 1][y][x + 1] = false;
                                } else {
                                    space[z + 1][y][x + 1] = true;
                                }

                                if (space[z + 1][y][x - 1] == true) {
                                    space[z + 1][y][x - 1] = false;
                                } else {
                                    space[z + 1][y][x - 1] = true;
                                }

                                if (space[z + 1][y][x] == true) {
                                    space[z + 1][y][x] = false;
                                } else {
                                    space[z + 1][y][x] = true;
                                }

                                if (space[z - 1][y + 1][x + 1] == true) {
                                    space[z - 1][y + 1][x + 1] = false;
                                } else {
                                    space[z - 1][y + 1][x + 1] = true;
                                }

                                if (space[z - 1][y + 1][x - 1] == true) {
                                    space[z - 1][y + 1][x - 1] = false;
                                } else {
                                    space[z - 1][y + 1][x - 1] = true;
                                }

                                if (space[z - 1][y + 1][x] == true) {
                                    space[z - 1][y + 1][x] = false;
                                } else {
                                    space[z - 1][y + 1][x] = true;
                                }

                                if (space[z - 1][y - 1][x + 1] == true) {
                                    space[z - 1][y - 1][x + 1] = false;
                                } else {
                                    space[z - 1][y - 1][x + 1] = true;
                                }

                                if (space[z - 1][y - 1][x - 1] == true) {
                                    space[z - 1][y - 1][x - 1] = false;
                                } else {
                                    space[z - 1][y - 1][x - 1] = true;
                                }

                                if (space[z - 1][y - 1][x] == true) {
                                    space[z - 1][y - 1][x] = false;
                                } else {
                                    space[z - 1][y - 1][x] = true;
                                }

                                if (space[z - 1][y][x + 1] == true) {
                                    space[z - 1][y][x + 1] = false;
                                } else {
                                    space[z - 1][y][x + 1] = true;
                                }

                                if (space[z - 1][y][x - 1] == true) {
                                    space[z - 1][y][x - 1] = false;
                                } else {
                                    space[z - 1][y][x - 1] = true;
                                }

                                if (space[z - 1][y][x] == true) {
                                    space[z - 1][y][x] = false;
                                } else {
                                    space[z - 1][y][x] = true;
                                }

                                if (space[z][y + 1][x + 1] == true) {
                                    space[z][y + 1][x + 1] = false;
                                } else {
                                    space[z][y + 1][x + 1] = true;
                                }

                                if (space[z][y + 1][x - 1] == true) {
                                    space[z][y + 1][x - 1] = false;
                                } else {
                                    space[z][y + 1][x - 1] = true;
                                }

                                if (space[z][y + 1][x] == true) {
                                    space[z][y + 1][x] = false;
                                } else {
                                    space[z][y + 1][x] = true;
                                }

                                if (space[z][y - 1][x + 1] == true) {
                                    space[z][y - 1][x + 1] = false;
                                } else {
                                    space[z][y - 1][x + 1] = true;
                                }

                                if (space[z][y - 1][x - 1] == true) {
                                    space[z][y - 1][x - 1] = false;
                                } else {
                                    space[z][y - 1][x - 1] = true;
                                }

                                if (space[z][y - 1][x] == true) {
                                    space[z][y - 1][x] = false;
                                } else {
                                    space[z][y - 1][x] = true;
                                }

                                if (space[z][y][x + 1] == true) {
                                    space[z][y][x + 1] = false;
                                } else {
                                    space[z][y][x + 1] = true;
                                }

                                if (space[z][y][x - 1] == true) {
                                    space[z][y][x - 1] = false;
                                } else {
                                    space[z][y][x - 1] = true;
                                }
                            } else {
                                if (space[z + 1][y + 1][x + 1] == true) {
                                    space[z + 1][y + 1][x + 1] = false;
                                } else {
                                    space[z + 1][y + 1][x + 1] = true;
                                }


                                if (space[z + 1][y + 1][x] == true) {
                                    space[z + 1][y + 1][x] = false;
                                } else {
                                    space[z + 1][y + 1][x] = true;
                                }

                                if (space[z + 1][y - 1][x + 1] == true) {
                                    space[z + 1][y - 1][x + 1] = false;
                                } else {
                                    space[z + 1][y - 1][x + 1] = true;
                                }


                                if (space[z + 1][y - 1][x] == true) {
                                    space[z + 1][y - 1][x] = false;
                                } else {
                                    space[z + 1][y - 1][x] = true;
                                }

                                if (space[z + 1][y][x + 1] == true) {
                                    space[z + 1][y][x + 1] = false;
                                } else {
                                    space[z + 1][y][x + 1] = true;
                                }


                                if (space[z + 1][y][x] == true) {
                                    space[z + 1][y][x] = false;
                                } else {
                                    space[z + 1][y][x] = true;
                                }

                                if (space[z - 1][y + 1][x + 1] == true) {
                                    space[z - 1][y + 1][x + 1] = false;
                                } else {
                                    space[z - 1][y + 1][x + 1] = true;
                                }


                                if (space[z - 1][y + 1][x] == true) {
                                    space[z - 1][y + 1][x] = false;
                                } else {
                                    space[z - 1][y + 1][x] = true;
                                }

                                if (space[z - 1][y - 1][x + 1] == true) {
                                    space[z - 1][y - 1][x + 1] = false;
                                } else {
                                    space[z - 1][y - 1][x + 1] = true;
                                }


                                if (space[z - 1][y - 1][x] == true) {
                                    space[z - 1][y - 1][x] = false;
                                } else {
                                    space[z - 1][y - 1][x] = true;
                                }

                                if (space[z - 1][y][x + 1] == true) {
                                    space[z - 1][y][x + 1] = false;
                                } else {
                                    space[z - 1][y][x + 1] = true;
                                }


                                if (space[z - 1][y][x] == true) {
                                    space[z - 1][y][x] = false;
                                } else {
                                    space[z - 1][y][x] = true;
                                }

                                if (space[z][y + 1][x + 1] == true) {
                                    space[z][y + 1][x + 1] = false;
                                } else {
                                    space[z][y + 1][x + 1] = true;
                                }


                                if (space[z][y + 1][x] == true) {
                                    space[z][y + 1][x] = false;
                                } else {
                                    space[z][y + 1][x] = true;
                                }

                                if (space[z][y - 1][x + 1] == true) {
                                    space[z][y - 1][x + 1] = false;
                                } else {
                                    space[z][y - 1][x + 1] = true;
                                }


                                if (space[z][y - 1][x] == true) {
                                    space[z][y - 1][x] = false;
                                } else {
                                    space[z][y - 1][x] = true;
                                }

                                if (space[z][y][x + 1] == true) {
                                    space[z][y][x + 1] = false;
                                } else {
                                    space[z][y][x + 1] = true;
                                }

                            }
                        } else if (mods[5] == true) {
                            if (space[z + 1][y + 1][x + 1] == true) {
                                space[z + 1][y + 1][x + 1] = false;
                            } else {
                                space[z + 1][y + 1][x + 1] = true;
                            }

                            if (space[z + 1][y + 1][x - 1] == true) {
                                space[z + 1][y + 1][x - 1] = false;
                            } else {
                                space[z + 1][y + 1][x - 1] = true;
                            }

                            if (space[z + 1][y + 1][x] == true) {
                                space[z + 1][y + 1][x] = false;
                            } else {
                                space[z + 1][y + 1][x] = true;
                            }


                            if (space[z + 1][y][x + 1] == true) {
                                space[z + 1][y][x + 1] = false;
                            } else {
                                space[z + 1][y][x + 1] = true;
                            }

                            if (space[z + 1][y][x - 1] == true) {
                                space[z + 1][y][x - 1] = false;
                            } else {
                                space[z + 1][y][x - 1] = true;
                            }

                            if (space[z + 1][y][x] == true) {
                                space[z + 1][y][x] = false;
                            } else {
                                space[z + 1][y][x] = true;
                            }

                            if (space[z - 1][y + 1][x + 1] == true) {
                                space[z - 1][y + 1][x + 1] = false;
                            } else {
                                space[z - 1][y + 1][x + 1] = true;
                            }

                            if (space[z - 1][y + 1][x - 1] == true) {
                                space[z - 1][y + 1][x - 1] = false;
                            } else {
                                space[z - 1][y + 1][x - 1] = true;
                            }

                            if (space[z - 1][y + 1][x] == true) {
                                space[z - 1][y + 1][x] = false;
                            } else {
                                space[z - 1][y + 1][x] = true;
                            }


                            if (space[z - 1][y][x + 1] == true) {
                                space[z - 1][y][x + 1] = false;
                            } else {
                                space[z - 1][y][x + 1] = true;
                            }

                            if (space[z - 1][y][x - 1] == true) {
                                space[z - 1][y][x - 1] = false;
                            } else {
                                space[z - 1][y][x - 1] = true;
                            }

                            if (space[z - 1][y][x] == true) {
                                space[z - 1][y][x] = false;
                            } else {
                                space[z - 1][y][x] = true;
                            }

                            if (space[z][y + 1][x + 1] == true) {
                                space[z][y + 1][x + 1] = false;
                            } else {
                                space[z][y + 1][x + 1] = true;
                            }

                            if (space[z][y + 1][x - 1] == true) {
                                space[z][y + 1][x - 1] = false;
                            } else {
                                space[z][y + 1][x - 1] = true;
                            }

                            if (space[z][y + 1][x] == true) {
                                space[z][y + 1][x] = false;
                            } else {
                                space[z][y + 1][x] = true;
                            }


                            if (space[z][y][x + 1] == true) {
                                space[z][y][x + 1] = false;
                            } else {
                                space[z][y][x + 1] = true;
                            }

                            if (space[z][y][x - 1] == true) {
                                space[z][y][x - 1] = false;
                            } else {
                                space[z][y][x - 1] = true;
                            }
                        } else {
                            if (space[z + 1][y + 1][x + 1] == true) {
                                space[z + 1][y + 1][x + 1] = false;
                            } else {
                                space[z + 1][y + 1][x + 1] = true;
                            }


                            if (space[z + 1][y + 1][x] == true) {
                                space[z + 1][y + 1][x] = false;
                            } else {
                                space[z + 1][y + 1][x] = true;
                            }


                            if (space[z + 1][y][x + 1] == true) {
                                space[z + 1][y][x + 1] = false;
                            } else {
                                space[z + 1][y][x + 1] = true;
                            }


                            if (space[z + 1][y][x] == true) {
                                space[z + 1][y][x] = false;
                            } else {
                                space[z + 1][y][x] = true;
                            }

                            if (space[z - 1][y + 1][x + 1] == true) {
                                space[z - 1][y + 1][x + 1] = false;
                            } else {
                                space[z - 1][y + 1][x + 1] = true;
                            }


                            if (space[z - 1][y + 1][x] == true) {
                                space[z - 1][y + 1][x] = false;
                            } else {
                                space[z - 1][y + 1][x] = true;
                            }


                            if (space[z - 1][y][x + 1] == true) {
                                space[z - 1][y][x + 1] = false;
                            } else {
                                space[z - 1][y][x + 1] = true;
                            }


                            if (space[z - 1][y][x] == true) {
                                space[z - 1][y][x] = false;
                            } else {
                                space[z - 1][y][x] = true;
                            }

                            if (space[z][y + 1][x + 1] == true) {
                                space[z][y + 1][x + 1] = false;
                            } else {
                                space[z][y + 1][x + 1] = true;
                            }


                            if (space[z][y + 1][x] == true) {
                                space[z][y + 1][x] = false;
                            } else {
                                space[z][y + 1][x] = true;
                            }


                            if (space[z][y][x + 1] == true) {
                                space[z][y][x + 1] = false;
                            } else {
                                space[z][y][x + 1] = true;
                            }

                        }
                    } else if (mods[4] == true) {
                        if (mods[5] == true) {
                            if (space[z + 1][y + 1][x + 1] == true) {
                                space[z + 1][y + 1][x + 1] = false;
                            } else {
                                space[z + 1][y + 1][x + 1] = true;
                            }

                            if (space[z + 1][y + 1][x - 1] == true) {
                                space[z + 1][y + 1][x - 1] = false;
                            } else {
                                space[z + 1][y + 1][x - 1] = true;
                            }

                            if (space[z + 1][y + 1][x] == true) {
                                space[z + 1][y + 1][x] = false;
                            } else {
                                space[z + 1][y + 1][x] = true;
                            }

                            if (space[z + 1][y - 1][x + 1] == true) {
                                space[z + 1][y - 1][x + 1] = false;
                            } else {
                                space[z + 1][y - 1][x + 1] = true;
                            }

                            if (space[z + 1][y - 1][x - 1] == true) {
                                space[z + 1][y - 1][x - 1] = false;
                            } else {
                                space[z + 1][y - 1][x - 1] = true;
                            }

                            if (space[z + 1][y - 1][x] == true) {
                                space[z + 1][y - 1][x] = false;
                            } else {
                                space[z + 1][y - 1][x] = true;
                            }

                            if (space[z + 1][y][x + 1] == true) {
                                space[z + 1][y][x + 1] = false;
                            } else {
                                space[z + 1][y][x + 1] = true;
                            }

                            if (space[z + 1][y][x - 1] == true) {
                                space[z + 1][y][x - 1] = false;
                            } else {
                                space[z + 1][y][x - 1] = true;
                            }

                            if (space[z + 1][y][x] == true) {
                                space[z + 1][y][x] = false;
                            } else {
                                space[z + 1][y][x] = true;
                            }





                            if (space[z][y + 1][x + 1] == true) {
                                space[z][y + 1][x + 1] = false;
                            } else {
                                space[z][y + 1][x + 1] = true;
                            }

                            if (space[z][y + 1][x - 1] == true) {
                                space[z][y + 1][x - 1] = false;
                            } else {
                                space[z][y + 1][x - 1] = true;
                            }

                            if (space[z][y + 1][x] == true) {
                                space[z][y + 1][x] = false;
                            } else {
                                space[z][y + 1][x] = true;
                            }

                            if (space[z][y - 1][x + 1] == true) {
                                space[z][y - 1][x + 1] = false;
                            } else {
                                space[z][y - 1][x + 1] = true;
                            }

                            if (space[z][y - 1][x - 1] == true) {
                                space[z][y - 1][x - 1] = false;
                            } else {
                                space[z][y - 1][x - 1] = true;
                            }

                            if (space[z][y - 1][x] == true) {
                                space[z][y - 1][x] = false;
                            } else {
                                space[z][y - 1][x] = true;
                            }

                            if (space[z][y][x + 1] == true) {
                                space[z][y][x + 1] = false;
                            } else {
                                space[z][y][x + 1] = true;
                            }

                            if (space[z][y][x - 1] == true) {
                                space[z][y][x - 1] = false;
                            } else {
                                space[z][y][x - 1] = true;
                            }
                        } else {
                            if (space[z + 1][y + 1][x + 1] == true) {
                                space[z + 1][y + 1][x + 1] = false;
                            } else {
                                space[z + 1][y + 1][x + 1] = true;
                            }


                            if (space[z + 1][y + 1][x] == true) {
                                space[z + 1][y + 1][x] = false;
                            } else {
                                space[z + 1][y + 1][x] = true;
                            }

                            if (space[z + 1][y - 1][x + 1] == true) {
                                space[z + 1][y - 1][x + 1] = false;
                            } else {
                                space[z + 1][y - 1][x + 1] = true;
                            }


                            if (space[z + 1][y - 1][x] == true) {
                                space[z + 1][y - 1][x] = false;
                            } else {
                                space[z + 1][y - 1][x] = true;
                            }

                            if (space[z + 1][y][x + 1] == true) {
                                space[z + 1][y][x + 1] = false;
                            } else {
                                space[z + 1][y][x + 1] = true;
                            }


                            if (space[z + 1][y][x] == true) {
                                space[z + 1][y][x] = false;
                            } else {
                                space[z + 1][y][x] = true;
                            }




                            if (space[z][y + 1][x + 1] == true) {
                                space[z][y + 1][x + 1] = false;
                            } else {
                                space[z][y + 1][x + 1] = true;
                            }


                            if (space[z][y + 1][x] == true) {
                                space[z][y + 1][x] = false;
                            } else {
                                space[z][y + 1][x] = true;
                            }

                            if (space[z][y - 1][x + 1] == true) {
                                space[z][y - 1][x + 1] = false;
                            } else {
                                space[z][y - 1][x + 1] = true;
                            }


                            if (space[z][y - 1][x] == true) {
                                space[z][y - 1][x] = false;
                            } else {
                                space[z][y - 1][x] = true;
                            }

                            if (space[z][y][x + 1] == true) {
                                space[z][y][x + 1] = false;
                            } else {
                                space[z][y][x + 1] = true;
                            }


                        }
                    } else if (mods[5] == true) {
                        if (space[z + 1][y + 1][x + 1] == true) {
                            space[z + 1][y + 1][x + 1] = false;
                        } else {
                            space[z + 1][y + 1][x + 1] = true;
                        }

                        if (space[z + 1][y + 1][x - 1] == true) {
                            space[z + 1][y + 1][x - 1] = false;
                        } else {
                            space[z + 1][y + 1][x - 1] = true;
                        }

                        if (space[z + 1][y + 1][x] == true) {
                            space[z + 1][y + 1][x] = false;
                        } else {
                            space[z + 1][y + 1][x] = true;
                        }


                        if (space[z + 1][y][x + 1] == true) {
                            space[z + 1][y][x + 1] = false;
                        } else {
                            space[z + 1][y][x + 1] = true;
                        }

                        if (space[z + 1][y][x - 1] == true) {
                            space[z + 1][y][x - 1] = false;
                        } else {
                            space[z + 1][y][x - 1] = true;
                        }

                        if (space[z + 1][y][x] == true) {
                            space[z + 1][y][x] = false;
                        } else {
                            space[z + 1][y][x] = true;
                        }





                        if (space[z][y + 1][x + 1] == true) {
                            space[z][y + 1][x + 1] = false;
                        } else {
                            space[z][y + 1][x + 1] = true;
                        }

                        if (space[z][y + 1][x - 1] == true) {
                            space[z][y + 1][x - 1] = false;
                        } else {
                            space[z][y + 1][x - 1] = true;
                        }

                        if (space[z][y + 1][x] == true) {
                            space[z][y + 1][x] = false;
                        } else {
                            space[z][y + 1][x] = true;
                        }


                        if (space[z][y][x + 1] == true) {
                            space[z][y][x + 1] = false;
                        } else {
                            space[z][y][x + 1] = true;
                        }

                        if (space[z][y][x - 1] == true) {
                            space[z][y][x - 1] = false;
                        } else {
                            space[z][y][x - 1] = true;
                        }
                    } else {
                        if (space[z + 1][y + 1][x + 1] == true) {
                            space[z + 1][y + 1][x + 1] = false;
                        } else {
                            space[z + 1][y + 1][x + 1] = true;
                        }


                        if (space[z + 1][y + 1][x] == true) {
                            space[z + 1][y + 1][x] = false;
                        } else {
                            space[z + 1][y + 1][x] = true;
                        }


                        if (space[z + 1][y][x + 1] == true) {
                            space[z + 1][y][x + 1] = false;
                        } else {
                            space[z + 1][y][x + 1] = true;
                        }


                        if (space[z + 1][y][x] == true) {
                            space[z + 1][y][x] = false;
                        } else {
                            space[z + 1][y][x] = true;
                        }



                        if (space[z][y + 1][x + 1] == true) {
                            space[z][y + 1][x + 1] = false;
                        } else {
                            space[z][y + 1][x + 1] = true;
                        }


                        if (space[z][y + 1][x] == true) {
                            space[z][y + 1][x] = false;
                        } else {
                            space[z][y + 1][x] = true;
                        }


                        if (space[z][y][x + 1] == true) {
                            space[z][y][x + 1] = false;
                        } else {
                            space[z][y][x + 1] = true;
                        }

                    }
                } else if (mods[3] == true) {
                    if (mods[4] == true) {
                        if (mods[5] == true) {


                            if (space[z + 1][y + 1][x - 1] == true) {
                                space[z + 1][y + 1][x - 1] = false;
                            } else {
                                space[z + 1][y + 1][x - 1] = true;
                            }

                            if (space[z + 1][y + 1][x] == true) {
                                space[z + 1][y + 1][x] = false;
                            } else {
                                space[z + 1][y + 1][x] = true;
                            }


                            if (space[z + 1][y - 1][x - 1] == true) {
                                space[z + 1][y - 1][x - 1] = false;
                            } else {
                                space[z + 1][y - 1][x - 1] = true;
                            }

                            if (space[z + 1][y - 1][x] == true) {
                                space[z + 1][y - 1][x] = false;
                            } else {
                                space[z + 1][y - 1][x] = true;
                            }


                            if (space[z + 1][y][x - 1] == true) {
                                space[z + 1][y][x - 1] = false;
                            } else {
                                space[z + 1][y][x - 1] = true;
                            }

                            if (space[z + 1][y][x] == true) {
                                space[z + 1][y][x] = false;
                            } else {
                                space[z + 1][y][x] = true;
                            }


                            if (space[z - 1][y + 1][x - 1] == true) {
                                space[z - 1][y + 1][x - 1] = false;
                            } else {
                                space[z - 1][y + 1][x - 1] = true;
                            }

                            if (space[z - 1][y + 1][x] == true) {
                                space[z - 1][y + 1][x] = false;
                            } else {
                                space[z - 1][y + 1][x] = true;
                            }


                            if (space[z - 1][y - 1][x - 1] == true) {
                                space[z - 1][y - 1][x - 1] = false;
                            } else {
                                space[z - 1][y - 1][x - 1] = true;
                            }

                            if (space[z - 1][y - 1][x] == true) {
                                space[z - 1][y - 1][x] = false;
                            } else {
                                space[z - 1][y - 1][x] = true;
                            }


                            if (space[z - 1][y][x - 1] == true) {
                                space[z - 1][y][x - 1] = false;
                            } else {
                                space[z - 1][y][x - 1] = true;
                            }

                            if (space[z - 1][y][x] == true) {
                                space[z - 1][y][x] = false;
                            } else {
                                space[z - 1][y][x] = true;
                            }


                            if (space[z][y + 1][x - 1] == true) {
                                space[z][y + 1][x - 1] = false;
                            } else {
                                space[z][y + 1][x - 1] = true;
                            }

                            if (space[z][y + 1][x] == true) {
                                space[z][y + 1][x] = false;
                            } else {
                                space[z][y + 1][x] = true;
                            }


                            if (space[z][y - 1][x - 1] == true) {
                                space[z][y - 1][x - 1] = false;
                            } else {
                                space[z][y - 1][x - 1] = true;
                            }

                            if (space[z][y - 1][x] == true) {
                                space[z][y - 1][x] = false;
                            } else {
                                space[z][y - 1][x] = true;
                            }


                            if (space[z][y][x - 1] == true) {
                                space[z][y][x - 1] = false;
                            } else {
                                space[z][y][x - 1] = true;
                            }
                        } else {

                            if (space[z + 1][y + 1][x] == true) {
                                space[z + 1][y + 1][x] = false;
                            } else {
                                space[z + 1][y + 1][x] = true;
                            }


                            if (space[z + 1][y - 1][x] == true) {
                                space[z + 1][y - 1][x] = false;
                            } else {
                                space[z + 1][y - 1][x] = true;
                            }


                            if (space[z + 1][y][x] == true) {
                                space[z + 1][y][x] = false;
                            } else {
                                space[z + 1][y][x] = true;
                            }


                            if (space[z - 1][y + 1][x] == true) {
                                space[z - 1][y + 1][x] = false;
                            } else {
                                space[z - 1][y + 1][x] = true;
                            }


                            if (space[z - 1][y - 1][x] == true) {
                                space[z - 1][y - 1][x] = false;
                            } else {
                                space[z - 1][y - 1][x] = true;
                            }


                            if (space[z - 1][y][x] == true) {
                                space[z - 1][y][x] = false;
                            } else {
                                space[z - 1][y][x] = true;
                            }


                            if (space[z][y + 1][x] == true) {
                                space[z][y + 1][x] = false;
                            } else {
                                space[z][y + 1][x] = true;
                            }


                            if (space[z][y - 1][x] == true) {
                                space[z][y - 1][x] = false;
                            } else {
                                space[z][y - 1][x] = true;
                            }

                        }
                    } else if (mods[5] == true) {

                        if (space[z + 1][y + 1][x - 1] == true) {
                            space[z + 1][y + 1][x - 1] = false;
                        } else {
                            space[z + 1][y + 1][x - 1] = true;
                        }

                        if (space[z + 1][y + 1][x] == true) {
                            space[z + 1][y + 1][x] = false;
                        } else {
                            space[z + 1][y + 1][x] = true;
                        }



                        if (space[z + 1][y][x - 1] == true) {
                            space[z + 1][y][x - 1] = false;
                        } else {
                            space[z + 1][y][x - 1] = true;
                        }

                        if (space[z + 1][y][x] == true) {
                            space[z + 1][y][x] = false;
                        } else {
                            space[z + 1][y][x] = true;
                        }


                        if (space[z - 1][y + 1][x - 1] == true) {
                            space[z - 1][y + 1][x - 1] = false;
                        } else {
                            space[z - 1][y + 1][x - 1] = true;
                        }

                        if (space[z - 1][y + 1][x] == true) {
                            space[z - 1][y + 1][x] = false;
                        } else {
                            space[z - 1][y + 1][x] = true;
                        }



                        if (space[z - 1][y][x - 1] == true) {
                            space[z - 1][y][x - 1] = false;
                        } else {
                            space[z - 1][y][x - 1] = true;
                        }

                        if (space[z - 1][y][x] == true) {
                            space[z - 1][y][x] = false;
                        } else {
                            space[z - 1][y][x] = true;
                        }

                        if (space[z][y + 1][x - 1] == true) {
                            space[z][y + 1][x - 1] = false;
                        } else {
                            space[z][y + 1][x - 1] = true;
                        }

                        if (space[z][y + 1][x] == true) {
                            space[z][y + 1][x] = false;
                        } else {
                            space[z][y + 1][x] = true;
                        }


                        if (space[z][y][x - 1] == true) {
                            space[z][y][x - 1] = false;
                        } else {
                            space[z][y][x - 1] = true;
                        }
                    } else {

                        if (space[z + 1][y + 1][x] == true) {
                            space[z + 1][y + 1][x] = false;
                        } else {
                            space[z + 1][y + 1][x] = true;
                        }


                        if (space[z + 1][y][x] == true) {
                            space[z + 1][y][x] = false;
                        } else {
                            space[z + 1][y][x] = true;
                        }


                        if (space[z - 1][y + 1][x] == true) {
                            space[z - 1][y + 1][x] = false;
                        } else {
                            space[z - 1][y + 1][x] = true;
                        }


                        if (space[z - 1][y][x] == true) {
                            space[z - 1][y][x] = false;
                        } else {
                            space[z - 1][y][x] = true;
                        }

                        if (space[z][y + 1][x] == true) {
                            space[z][y + 1][x] = false;
                        } else {
                            space[z][y + 1][x] = true;
                        }
                    }
                } else if (mods[4] == true) {
                    if (mods[5] == true) {

                        if (space[z + 1][y + 1][x - 1] == true) {
                            space[z + 1][y + 1][x - 1] = false;
                        } else {
                            space[z + 1][y + 1][x - 1] = true;
                        }

                        if (space[z + 1][y + 1][x] == true) {
                            space[z + 1][y + 1][x] = false;
                        } else {
                            space[z + 1][y + 1][x] = true;
                        }


                        if (space[z + 1][y - 1][x - 1] == true) {
                            space[z + 1][y - 1][x - 1] = false;
                        } else {
                            space[z + 1][y - 1][x - 1] = true;
                        }

                        if (space[z + 1][y - 1][x] == true) {
                            space[z + 1][y - 1][x] = false;
                        } else {
                            space[z + 1][y - 1][x] = true;
                        }


                        if (space[z + 1][y][x - 1] == true) {
                            space[z + 1][y][x - 1] = false;
                        } else {
                            space[z + 1][y][x - 1] = true;
                        }

                        if (space[z + 1][y][x] == true) {
                            space[z + 1][y][x] = false;
                        } else {
                            space[z + 1][y][x] = true;
                        }



                        if (space[z][y + 1][x - 1] == true) {
                            space[z][y + 1][x - 1] = false;
                        } else {
                            space[z][y + 1][x - 1] = true;
                        }

                        if (space[z][y + 1][x] == true) {
                            space[z][y + 1][x] = false;
                        } else {
                            space[z][y + 1][x] = true;
                        }


                        if (space[z][y - 1][x - 1] == true) {
                            space[z][y - 1][x - 1] = false;
                        } else {
                            space[z][y - 1][x - 1] = true;
                        }

                        if (space[z][y - 1][x] == true) {
                            space[z][y - 1][x] = false;
                        } else {
                            space[z][y - 1][x] = true;
                        }


                        if (space[z][y][x - 1] == true) {
                            space[z][y][x - 1] = false;
                        } else {
                            space[z][y][x - 1] = true;
                        }
                    } else {

                        if (space[z + 1][y + 1][x] == true) {
                            space[z + 1][y + 1][x] = false;
                        } else {
                            space[z + 1][y + 1][x] = true;
                        }


                        if (space[z + 1][y - 1][x] == true) {
                            space[z + 1][y - 1][x] = false;
                        } else {
                            space[z + 1][y - 1][x] = true;
                        }


                        if (space[z + 1][y][x] == true) {
                            space[z + 1][y][x] = false;
                        } else {
                            space[z + 1][y][x] = true;
                        }


                        if (space[z][y + 1][x] == true) {
                            space[z][y + 1][x] = false;
                        } else {
                            space[z][y + 1][x] = true;
                        }


                        if (space[z][y - 1][x] == true) {
                            space[z][y - 1][x] = false;
                        } else {
                            space[z][y - 1][x] = true;
                        }

                    }
                } else if (mods[5] == true) {

                    if (space[z + 1][y + 1][x - 1] == true) {
                        space[z + 1][y + 1][x - 1] = false;
                    } else {
                        space[z + 1][y + 1][x - 1] = true;
                    }

                    if (space[z + 1][y + 1][x] == true) {
                        space[z + 1][y + 1][x] = false;
                    } else {
                        space[z + 1][y + 1][x] = true;
                    }


                    if (space[z + 1][y][x - 1] == true) {
                        space[z + 1][y][x - 1] = false;
                    } else {
                        space[z + 1][y][x - 1] = true;
                    }

                    if (space[z + 1][y][x] == true) {
                        space[z + 1][y][x] = false;
                    } else {
                        space[z + 1][y][x] = true;
                    }


                    if (space[z][y + 1][x - 1] == true) {
                        space[z][y + 1][x - 1] = false;
                    } else {
                        space[z][y + 1][x - 1] = true;
                    }

                    if (space[z][y + 1][x] == true) {
                        space[z][y + 1][x] = false;
                    } else {
                        space[z][y + 1][x] = true;
                    }


                    if (space[z][y][x - 1] == true) {
                        space[z][y][x - 1] = false;
                    } else {
                        space[z][y][x - 1] = true;
                    }
                } else {

                    if (space[z + 1][y + 1][x] == true) {
                        space[z + 1][y + 1][x] = false;
                    } else {
                        space[z + 1][y + 1][x] = true;
                    }


                    if (space[z + 1][y][x] == true) {
                        space[z + 1][y][x] = false;
                    } else {
                        space[z + 1][y][x] = true;
                    }


                    if (space[z][y + 1][x] == true) {
                        space[z][y + 1][x] = false;
                    } else {
                        space[z][y + 1][x] = true;
                    }

                }
            } else if (mods[2] == true) {
                if (mods[3] == true) {
                    if (mods[4] == true) {
                        if (mods[5] == true) {

                            if (space[z + 1][y - 1][x + 1] == true) {
                                space[z + 1][y - 1][x + 1] = false;
                            } else {
                                space[z + 1][y - 1][x + 1] = true;
                            }

                            if (space[z + 1][y - 1][x - 1] == true) {
                                space[z + 1][y - 1][x - 1] = false;
                            } else {
                                space[z + 1][y - 1][x - 1] = true;
                            }

                            if (space[z + 1][y - 1][x] == true) {
                                space[z + 1][y - 1][x] = false;
                            } else {
                                space[z + 1][y - 1][x] = true;
                            }

                            if (space[z + 1][y][x + 1] == true) {
                                space[z + 1][y][x + 1] = false;
                            } else {
                                space[z + 1][y][x + 1] = true;
                            }

                            if (space[z + 1][y][x - 1] == true) {
                                space[z + 1][y][x - 1] = false;
                            } else {
                                space[z + 1][y][x - 1] = true;
                            }

                            if (space[z + 1][y][x] == true) {
                                space[z + 1][y][x] = false;
                            } else {
                                space[z + 1][y][x] = true;
                            }


                            if (space[z - 1][y - 1][x + 1] == true) {
                                space[z - 1][y - 1][x + 1] = false;
                            } else {
                                space[z - 1][y - 1][x + 1] = true;
                            }

                            if (space[z - 1][y - 1][x - 1] == true) {
                                space[z - 1][y - 1][x - 1] = false;
                            } else {
                                space[z - 1][y - 1][x - 1] = true;
                            }

                            if (space[z - 1][y - 1][x] == true) {
                                space[z - 1][y - 1][x] = false;
                            } else {
                                space[z - 1][y - 1][x] = true;
                            }

                            if (space[z - 1][y][x + 1] == true) {
                                space[z - 1][y][x + 1] = false;
                            } else {
                                space[z - 1][y][x + 1] = true;
                            }

                            if (space[z - 1][y][x - 1] == true) {
                                space[z - 1][y][x - 1] = false;
                            } else {
                                space[z - 1][y][x - 1] = true;
                            }

                            if (space[z - 1][y][x] == true) {
                                space[z - 1][y][x] = false;
                            } else {
                                space[z - 1][y][x] = true;
                            }


                            if (space[z][y - 1][x + 1] == true) {
                                space[z][y - 1][x + 1] = false;
                            } else {
                                space[z][y - 1][x + 1] = true;
                            }

                            if (space[z][y - 1][x - 1] == true) {
                                space[z][y - 1][x - 1] = false;
                            } else {
                                space[z][y - 1][x - 1] = true;
                            }

                            if (space[z][y - 1][x] == true) {
                                space[z][y - 1][x] = false;
                            } else {
                                space[z][y - 1][x] = true;
                            }

                            if (space[z][y][x + 1] == true) {
                                space[z][y][x + 1] = false;
                            } else {
                                space[z][y][x + 1] = true;
                            }

                            if (space[z][y][x - 1] == true) {
                                space[z][y][x - 1] = false;
                            } else {
                                space[z][y][x - 1] = true;
                            }
                        } else {

                            if (space[z + 1][y - 1][x + 1] == true) {
                                space[z + 1][y - 1][x + 1] = false;
                            } else {
                                space[z + 1][y - 1][x + 1] = true;
                            }


                            if (space[z + 1][y - 1][x] == true) {
                                space[z + 1][y - 1][x] = false;
                            } else {
                                space[z + 1][y - 1][x] = true;
                            }

                            if (space[z + 1][y][x + 1] == true) {
                                space[z + 1][y][x + 1] = false;
                            } else {
                                space[z + 1][y][x + 1] = true;
                            }


                            if (space[z + 1][y][x] == true) {
                                space[z + 1][y][x] = false;
                            } else {
                                space[z + 1][y][x] = true;
                            }


                            if (space[z - 1][y - 1][x + 1] == true) {
                                space[z - 1][y - 1][x + 1] = false;
                            } else {
                                space[z - 1][y - 1][x + 1] = true;
                            }


                            if (space[z - 1][y - 1][x] == true) {
                                space[z - 1][y - 1][x] = false;
                            } else {
                                space[z - 1][y - 1][x] = true;
                            }

                            if (space[z - 1][y][x + 1] == true) {
                                space[z - 1][y][x + 1] = false;
                            } else {
                                space[z - 1][y][x + 1] = true;
                            }


                            if (space[z - 1][y][x] == true) {
                                space[z - 1][y][x] = false;
                            } else {
                                space[z - 1][y][x] = true;
                            }


                            if (space[z][y - 1][x + 1] == true) {
                                space[z][y - 1][x + 1] = false;
                            } else {
                                space[z][y - 1][x + 1] = true;
                            }


                            if (space[z][y - 1][x] == true) {
                                space[z][y - 1][x] = false;
                            } else {
                                space[z][y - 1][x] = true;
                            }

                            if (space[z][y][x + 1] == true) {
                                space[z][y][x + 1] = false;
                            } else {
                                space[z][y][x + 1] = true;
                            }

                        }
                    } else if (mods[5] == true) {

                        if (space[z + 1][y][x + 1] == true) {
                            space[z + 1][y][x + 1] = false;
                        } else {
                            space[z + 1][y][x + 1] = true;
                        }

                        if (space[z + 1][y][x - 1] == true) {
                            space[z + 1][y][x - 1] = false;
                        } else {
                            space[z + 1][y][x - 1] = true;
                        }

                        if (space[z + 1][y][x] == true) {
                            space[z + 1][y][x] = false;
                        } else {
                            space[z + 1][y][x] = true;
                        }



                        if (space[z - 1][y][x + 1] == true) {
                            space[z - 1][y][x + 1] = false;
                        } else {
                            space[z - 1][y][x + 1] = true;
                        }

                        if (space[z - 1][y][x - 1] == true) {
                            space[z - 1][y][x - 1] = false;
                        } else {
                            space[z - 1][y][x - 1] = true;
                        }

                        if (space[z - 1][y][x] == true) {
                            space[z - 1][y][x] = false;
                        } else {
                            space[z - 1][y][x] = true;
                        }



                        if (space[z][y][x + 1] == true) {
                            space[z][y][x + 1] = false;
                        } else {
                            space[z][y][x + 1] = true;
                        }

                        if (space[z][y][x - 1] == true) {
                            space[z][y][x - 1] = false;
                        } else {
                            space[z][y][x - 1] = true;
                        }
                    } else {


                        if (space[z + 1][y][x + 1] == true) {
                            space[z + 1][y][x + 1] = false;
                        } else {
                            space[z + 1][y][x + 1] = true;
                        }


                        if (space[z + 1][y][x] == true) {
                            space[z + 1][y][x] = false;
                        } else {
                            space[z + 1][y][x] = true;
                        }


                        if (space[z - 1][y][x + 1] == true) {
                            space[z - 1][y][x + 1] = false;
                        } else {
                            space[z - 1][y][x + 1] = true;
                        }


                        if (space[z - 1][y][x] == true) {
                            space[z - 1][y][x] = false;
                        } else {
                            space[z - 1][y][x] = true;
                        }



                        if (space[z][y][x + 1] == true) {
                            space[z][y][x + 1] = false;
                        } else {
                            space[z][y][x + 1] = true;
                        }

                    }
                } else if (mods[4] == true) {
                    if (mods[5] == true) {

                        if (space[z + 1][y - 1][x + 1] == true) {
                            space[z + 1][y - 1][x + 1] = false;
                        } else {
                            space[z + 1][y - 1][x + 1] = true;
                        }

                        if (space[z + 1][y - 1][x - 1] == true) {
                            space[z + 1][y - 1][x - 1] = false;
                        } else {
                            space[z + 1][y - 1][x - 1] = true;
                        }

                        if (space[z + 1][y - 1][x] == true) {
                            space[z + 1][y - 1][x] = false;
                        } else {
                            space[z + 1][y - 1][x] = true;
                        }

                        if (space[z + 1][y][x + 1] == true) {
                            space[z + 1][y][x + 1] = false;
                        } else {
                            space[z + 1][y][x + 1] = true;
                        }

                        if (space[z + 1][y][x - 1] == true) {
                            space[z + 1][y][x - 1] = false;
                        } else {
                            space[z + 1][y][x - 1] = true;
                        }

                        if (space[z + 1][y][x] == true) {
                            space[z + 1][y][x] = false;
                        } else {
                            space[z + 1][y][x] = true;
                        }




                        if (space[z][y - 1][x + 1] == true) {
                            space[z][y - 1][x + 1] = false;
                        } else {
                            space[z][y - 1][x + 1] = true;
                        }

                        if (space[z][y - 1][x - 1] == true) {
                            space[z][y - 1][x - 1] = false;
                        } else {
                            space[z][y - 1][x - 1] = true;
                        }

                        if (space[z][y - 1][x] == true) {
                            space[z][y - 1][x] = false;
                        } else {
                            space[z][y - 1][x] = true;
                        }

                        if (space[z][y][x + 1] == true) {
                            space[z][y][x + 1] = false;
                        } else {
                            space[z][y][x + 1] = true;
                        }

                        if (space[z][y][x - 1] == true) {
                            space[z][y][x - 1] = false;
                        } else {
                            space[z][y][x - 1] = true;
                        }
                    } else {

                        if (space[z + 1][y - 1][x + 1] == true) {
                            space[z + 1][y - 1][x + 1] = false;
                        } else {
                            space[z + 1][y - 1][x + 1] = true;
                        }


                        if (space[z + 1][y - 1][x] == true) {
                            space[z + 1][y - 1][x] = false;
                        } else {
                            space[z + 1][y - 1][x] = true;
                        }

                        if (space[z + 1][y][x + 1] == true) {
                            space[z + 1][y][x + 1] = false;
                        } else {
                            space[z + 1][y][x + 1] = true;
                        }


                        if (space[z + 1][y][x] == true) {
                            space[z + 1][y][x] = false;
                        } else {
                            space[z + 1][y][x] = true;
                        }



                        if (space[z][y - 1][x + 1] == true) {
                            space[z][y - 1][x + 1] = false;
                        } else {
                            space[z][y - 1][x + 1] = true;
                        }


                        if (space[z][y - 1][x] == true) {
                            space[z][y - 1][x] = false;
                        } else {
                            space[z][y - 1][x] = true;
                        }

                        if (space[z][y][x + 1] == true) {
                            space[z][y][x + 1] = false;
                        } else {
                            space[z][y][x + 1] = true;
                        }

                    }
                } else if (mods[5] == true) {


                    if (space[z + 1][y][x + 1] == true) {
                        space[z + 1][y][x + 1] = false;
                    } else {
                        space[z + 1][y][x + 1] = true;
                    }

                    if (space[z + 1][y][x - 1] == true) {
                        space[z + 1][y][x - 1] = false;
                    } else {
                        space[z + 1][y][x - 1] = true;
                    }

                    if (space[z + 1][y][x] == true) {
                        space[z + 1][y][x] = false;
                    } else {
                        space[z + 1][y][x] = true;
                    }






                    if (space[z][y][x + 1] == true) {
                        space[z][y][x + 1] = false;
                    } else {
                        space[z][y][x + 1] = true;
                    }

                    if (space[z][y][x - 1] == true) {
                        space[z][y][x - 1] = false;
                    } else {
                        space[z][y][x - 1] = true;
                    }
                } else {


                    if (space[z + 1][y][x + 1] == true) {
                        space[z + 1][y][x + 1] = false;
                    } else {
                        space[z + 1][y][x + 1] = true;
                    }


                    if (space[z + 1][y][x] == true) {
                        space[z + 1][y][x] = false;
                    } else {
                        space[z + 1][y][x] = true;
                    }





                    if (space[z][y][x + 1] == true) {
                        space[z][y][x + 1] = false;
                    } else {
                        space[z][y][x + 1] = true;
                    }

                }
            } else if (mods[3] == true) {
                if (mods[4] == true) {
                    if (mods[5] == true) {


                        if (space[z + 1][y - 1][x - 1] == true) {
                            space[z + 1][y - 1][x - 1] = false;
                        } else {
                            space[z + 1][y - 1][x - 1] = true;
                        }

                        if (space[z + 1][y - 1][x] == true) {
                            space[z + 1][y - 1][x] = false;
                        } else {
                            space[z + 1][y - 1][x] = true;
                        }


                        if (space[z + 1][y][x - 1] == true) {
                            space[z + 1][y][x - 1] = false;
                        } else {
                            space[z + 1][y][x - 1] = true;
                        }

                        if (space[z + 1][y][x] == true) {
                            space[z + 1][y][x] = false;
                        } else {
                            space[z + 1][y][x] = true;
                        }



                        if (space[z - 1][y - 1][x - 1] == true) {
                            space[z - 1][y - 1][x - 1] = false;
                        } else {
                            space[z - 1][y - 1][x - 1] = true;
                        }

                        if (space[z - 1][y - 1][x] == true) {
                            space[z - 1][y - 1][x] = false;
                        } else {
                            space[z - 1][y - 1][x] = true;
                        }


                        if (space[z - 1][y][x - 1] == true) {
                            space[z - 1][y][x - 1] = false;
                        } else {
                            space[z - 1][y][x - 1] = true;
                        }

                        if (space[z - 1][y][x] == true) {
                            space[z - 1][y][x] = false;
                        } else {
                            space[z - 1][y][x] = true;
                        }



                        if (space[z][y - 1][x - 1] == true) {
                            space[z][y - 1][x - 1] = false;
                        } else {
                            space[z][y - 1][x - 1] = true;
                        }

                        if (space[z][y - 1][x] == true) {
                            space[z][y - 1][x] = false;
                        } else {
                            space[z][y - 1][x] = true;
                        }

                        if (space[z][y][x - 1] == true) {
                            space[z][y][x - 1] = false;
                        } else {
                            space[z][y][x - 1] = true;
                        }
                    } else {


                        if (space[z + 1][y - 1][x] == true) {
                            space[z + 1][y - 1][x] = false;
                        } else {
                            space[z + 1][y - 1][x] = true;
                        }


                        if (space[z + 1][y][x] == true) {
                            space[z + 1][y][x] = false;
                        } else {
                            space[z + 1][y][x] = true;
                        }



                        if (space[z - 1][y - 1][x] == true) {
                            space[z - 1][y - 1][x] = false;
                        } else {
                            space[z - 1][y - 1][x] = true;
                        }


                        if (space[z - 1][y][x] == true) {
                            space[z - 1][y][x] = false;
                        } else {
                            space[z - 1][y][x] = true;
                        }



                        if (space[z][y - 1][x] == true) {
                            space[z][y - 1][x] = false;
                        } else {
                            space[z][y - 1][x] = true;
                        }

                    }
                } else if (mods[5] == true) {


                    if (space[z + 1][y][x - 1] == true) {
                        space[z + 1][y][x - 1] = false;
                    } else {
                        space[z + 1][y][x - 1] = true;
                    }

                    if (space[z + 1][y][x] == true) {
                        space[z + 1][y][x] = false;
                    } else {
                        space[z + 1][y][x] = true;
                    }



                    if (space[z - 1][y][x - 1] == true) {
                        space[z - 1][y][x - 1] = false;
                    } else {
                        space[z - 1][y][x - 1] = true;
                    }

                    if (space[z - 1][y][x] == true) {
                        space[z - 1][y][x] = false;
                    } else {
                        space[z - 1][y][x] = true;
                    }



                    if (space[z][y][x - 1] == true) {
                        space[z][y][x - 1] = false;
                    } else {
                        space[z][y][x - 1] = true;
                    }
                } else {


                    if (space[z + 1][y][x] == true) {
                        space[z + 1][y][x] = false;
                    } else {
                        space[z + 1][y][x] = true;
                    }


                    if (space[z - 1][y][x] == true) {
                        space[z - 1][y][x] = false;
                    } else {
                        space[z - 1][y][x] = true;
                    }


                }
            } else if (mods[4] == true) {
                if (mods[5] == true) {


                    if (space[z + 1][y - 1][x - 1] == true) {
                        space[z + 1][y - 1][x - 1] = false;
                    } else {
                        space[z + 1][y - 1][x - 1] = true;
                    }

                    if (space[z + 1][y - 1][x] == true) {
                        space[z + 1][y - 1][x] = false;
                    } else {
                        space[z + 1][y - 1][x] = true;
                    }

                    if (space[z + 1][y][x - 1] == true) {
                        space[z + 1][y][x - 1] = false;
                    } else {
                        space[z + 1][y][x - 1] = true;
                    }

                    if (space[z + 1][y][x] == true) {
                        space[z + 1][y][x] = false;
                    } else {
                        space[z + 1][y][x] = true;
                    }


                    if (space[z][y - 1][x - 1] == true) {
                        space[z][y - 1][x - 1] = false;
                    } else {
                        space[z][y - 1][x - 1] = true;
                    }

                    if (space[z][y - 1][x] == true) {
                        space[z][y - 1][x] = false;
                    } else {
                        space[z][y - 1][x] = true;
                    }


                    if (space[z][y][x - 1] == true) {
                        space[z][y][x - 1] = false;
                    } else {
                        space[z][y][x - 1] = true;
                    }
                } else {


                    if (space[z + 1][y - 1][x] == true) {
                        space[z + 1][y - 1][x] = false;
                    } else {
                        space[z + 1][y - 1][x] = true;
                    }


                    if (space[z + 1][y][x] == true) {
                        space[z + 1][y][x] = false;
                    } else {
                        space[z + 1][y][x] = true;
                    }


                    if (space[z][y - 1][x] == true) {
                        space[z][y - 1][x] = false;
                    } else {
                        space[z][y - 1][x] = true;
                    }

                }
            } else if (mods[5] == true) {


                if (space[z + 1][y][x - 1] == true) {
                    space[z + 1][y][x - 1] = false;
                } else {
                    space[z + 1][y][x - 1] = true;
                }

                if (space[z + 1][y][x] == true) {
                    space[z + 1][y][x] = false;
                } else {
                    space[z + 1][y][x] = true;
                }




                if (space[z][y][x - 1] == true) {
                    space[z][y][x - 1] = false;
                } else {
                    space[z][y][x - 1] = true;
                }
            } else {


                if (space[z + 1][y][x] == true) {
                    space[z + 1][y][x] = false;
                } else {
                    space[z + 1][y][x] = true;
                }

            }
        } else if (mods[1] == true) {
            if (mods[2] == true) {
                if (mods[3] == true) {
                    if (mods[4] == true) {
                        if (mods[5] == true) {

                            if (space[z - 1][y + 1][x + 1] == true) {
                                space[z - 1][y + 1][x + 1] = false;
                            } else {
                                space[z - 1][y + 1][x + 1] = true;
                            }

                            if (space[z - 1][y + 1][x - 1] == true) {
                                space[z - 1][y + 1][x - 1] = false;
                            } else {
                                space[z - 1][y + 1][x - 1] = true;
                            }

                            if (space[z - 1][y + 1][x] == true) {
                                space[z - 1][y + 1][x] = false;
                            } else {
                                space[z - 1][y + 1][x] = true;
                            }

                            if (space[z - 1][y - 1][x + 1] == true) {
                                space[z - 1][y - 1][x + 1] = false;
                            } else {
                                space[z - 1][y - 1][x + 1] = true;
                            }

                            if (space[z - 1][y - 1][x - 1] == true) {
                                space[z - 1][y - 1][x - 1] = false;
                            } else {
                                space[z - 1][y - 1][x - 1] = true;
                            }

                            if (space[z - 1][y - 1][x] == true) {
                                space[z - 1][y - 1][x] = false;
                            } else {
                                space[z - 1][y - 1][x] = true;
                            }

                            if (space[z - 1][y][x + 1] == true) {
                                space[z - 1][y][x + 1] = false;
                            } else {
                                space[z - 1][y][x + 1] = true;
                            }

                            if (space[z - 1][y][x - 1] == true) {
                                space[z - 1][y][x - 1] = false;
                            } else {
                                space[z - 1][y][x - 1] = true;
                            }

                            if (space[z - 1][y][x] == true) {
                                space[z - 1][y][x] = false;
                            } else {
                                space[z - 1][y][x] = true;
                            }

                            if (space[z][y + 1][x + 1] == true) {
                                space[z][y + 1][x + 1] = false;
                            } else {
                                space[z][y + 1][x + 1] = true;
                            }

                            if (space[z][y + 1][x - 1] == true) {
                                space[z][y + 1][x - 1] = false;
                            } else {
                                space[z][y + 1][x - 1] = true;
                            }

                            if (space[z][y + 1][x] == true) {
                                space[z][y + 1][x] = false;
                            } else {
                                space[z][y + 1][x] = true;
                            }

                            if (space[z][y - 1][x + 1] == true) {
                                space[z][y - 1][x + 1] = false;
                            } else {
                                space[z][y - 1][x + 1] = true;
                            }

                            if (space[z][y - 1][x - 1] == true) {
                                space[z][y - 1][x - 1] = false;
                            } else {
                                space[z][y - 1][x - 1] = true;
                            }

                            if (space[z][y - 1][x] == true) {
                                space[z][y - 1][x] = false;
                            } else {
                                space[z][y - 1][x] = true;
                            }

                            if (space[z][y][x + 1] == true) {
                                space[z][y][x + 1] = false;
                            } else {
                                space[z][y][x + 1] = true;
                            }

                            if (space[z][y][x - 1] == true) {
                                space[z][y][x - 1] = false;
                            } else {
                                space[z][y][x - 1] = true;
                            }
                        } else {


                            if (space[z - 1][y + 1][x + 1] == true) {
                                space[z - 1][y + 1][x + 1] = false;
                            } else {
                                space[z - 1][y + 1][x + 1] = true;
                            }


                            if (space[z - 1][y + 1][x] == true) {
                                space[z - 1][y + 1][x] = false;
                            } else {
                                space[z - 1][y + 1][x] = true;
                            }

                            if (space[z - 1][y - 1][x + 1] == true) {
                                space[z - 1][y - 1][x + 1] = false;
                            } else {
                                space[z - 1][y - 1][x + 1] = true;
                            }


                            if (space[z - 1][y - 1][x] == true) {
                                space[z - 1][y - 1][x] = false;
                            } else {
                                space[z - 1][y - 1][x] = true;
                            }

                            if (space[z - 1][y][x + 1] == true) {
                                space[z - 1][y][x + 1] = false;
                            } else {
                                space[z - 1][y][x + 1] = true;
                            }


                            if (space[z - 1][y][x] == true) {
                                space[z - 1][y][x] = false;
                            } else {
                                space[z - 1][y][x] = true;
                            }

                            if (space[z][y + 1][x + 1] == true) {
                                space[z][y + 1][x + 1] = false;
                            } else {
                                space[z][y + 1][x + 1] = true;
                            }


                            if (space[z][y + 1][x] == true) {
                                space[z][y + 1][x] = false;
                            } else {
                                space[z][y + 1][x] = true;
                            }

                            if (space[z][y - 1][x + 1] == true) {
                                space[z][y - 1][x + 1] = false;
                            } else {
                                space[z][y - 1][x + 1] = true;
                            }


                            if (space[z][y - 1][x] == true) {
                                space[z][y - 1][x] = false;
                            } else {
                                space[z][y - 1][x] = true;
                            }

                            if (space[z][y][x + 1] == true) {
                                space[z][y][x + 1] = false;
                            } else {
                                space[z][y][x + 1] = true;
                            }

                        }
                    } else if (mods[5] == true) {


                        if (space[z - 1][y + 1][x + 1] == true) {
                            space[z - 1][y + 1][x + 1] = false;
                        } else {
                            space[z - 1][y + 1][x + 1] = true;
                        }

                        if (space[z - 1][y + 1][x - 1] == true) {
                            space[z - 1][y + 1][x - 1] = false;
                        } else {
                            space[z - 1][y + 1][x - 1] = true;
                        }

                        if (space[z - 1][y + 1][x] == true) {
                            space[z - 1][y + 1][x] = false;
                        } else {
                            space[z - 1][y + 1][x] = true;
                        }


                        if (space[z - 1][y][x + 1] == true) {
                            space[z - 1][y][x + 1] = false;
                        } else {
                            space[z - 1][y][x + 1] = true;
                        }

                        if (space[z - 1][y][x - 1] == true) {
                            space[z - 1][y][x - 1] = false;
                        } else {
                            space[z - 1][y][x - 1] = true;
                        }

                        if (space[z - 1][y][x] == true) {
                            space[z - 1][y][x] = false;
                        } else {
                            space[z - 1][y][x] = true;
                        }

                        if (space[z][y + 1][x + 1] == true) {
                            space[z][y + 1][x + 1] = false;
                        } else {
                            space[z][y + 1][x + 1] = true;
                        }

                        if (space[z][y + 1][x - 1] == true) {
                            space[z][y + 1][x - 1] = false;
                        } else {
                            space[z][y + 1][x - 1] = true;
                        }

                        if (space[z][y + 1][x] == true) {
                            space[z][y + 1][x] = false;
                        } else {
                            space[z][y + 1][x] = true;
                        }


                        if (space[z][y][x + 1] == true) {
                            space[z][y][x + 1] = false;
                        } else {
                            space[z][y][x + 1] = true;
                        }

                        if (space[z][y][x - 1] == true) {
                            space[z][y][x - 1] = false;
                        } else {
                            space[z][y][x - 1] = true;
                        }
                    } else {


                        if (space[z - 1][y + 1][x + 1] == true) {
                            space[z - 1][y + 1][x + 1] = false;
                        } else {
                            space[z - 1][y + 1][x + 1] = true;
                        }


                        if (space[z - 1][y + 1][x] == true) {
                            space[z - 1][y + 1][x] = false;
                        } else {
                            space[z - 1][y + 1][x] = true;
                        }

                        if (space[z - 1][y][x + 1] == true) {
                            space[z - 1][y][x + 1] = false;
                        } else {
                            space[z - 1][y][x + 1] = true;
                        }


                        if (space[z - 1][y][x] == true) {
                            space[z - 1][y][x] = false;
                        } else {
                            space[z - 1][y][x] = true;
                        }

                        if (space[z][y + 1][x + 1] == true) {
                            space[z][y + 1][x + 1] = false;
                        } else {
                            space[z][y + 1][x + 1] = true;
                        }


                        if (space[z][y + 1][x] == true) {
                            space[z][y + 1][x] = false;
                        } else {
                            space[z][y + 1][x] = true;
                        }


                        if (space[z][y][x + 1] == true) {
                            space[z][y][x + 1] = false;
                        } else {
                            space[z][y][x + 1] = true;
                        }

                    }
                } else if (mods[4] == true) {
                    if (mods[5] == true) {


                        if (space[z][y + 1][x + 1] == true) {
                            space[z][y + 1][x + 1] = false;
                        } else {
                            space[z][y + 1][x + 1] = true;
                        }

                        if (space[z][y + 1][x - 1] == true) {
                            space[z][y + 1][x - 1] = false;
                        } else {
                            space[z][y + 1][x - 1] = true;
                        }

                        if (space[z][y + 1][x] == true) {
                            space[z][y + 1][x] = false;
                        } else {
                            space[z][y + 1][x] = true;
                        }

                        if (space[z][y - 1][x + 1] == true) {
                            space[z][y - 1][x + 1] = false;
                        } else {
                            space[z][y - 1][x + 1] = true;
                        }

                        if (space[z][y - 1][x - 1] == true) {
                            space[z][y - 1][x - 1] = false;
                        } else {
                            space[z][y - 1][x - 1] = true;
                        }

                        if (space[z][y - 1][x] == true) {
                            space[z][y - 1][x] = false;
                        } else {
                            space[z][y - 1][x] = true;
                        }

                        if (space[z][y][x + 1] == true) {
                            space[z][y][x + 1] = false;
                        } else {
                            space[z][y][x + 1] = true;
                        }

                        if (space[z][y][x - 1] == true) {
                            space[z][y][x - 1] = false;
                        } else {
                            space[z][y][x - 1] = true;
                        }
                    } else {


                        if (space[z][y + 1][x + 1] == true) {
                            space[z][y + 1][x + 1] = false;
                        } else {
                            space[z][y + 1][x + 1] = true;
                        }


                        if (space[z][y + 1][x] == true) {
                            space[z][y + 1][x] = false;
                        } else {
                            space[z][y + 1][x] = true;
                        }

                        if (space[z][y - 1][x + 1] == true) {
                            space[z][y - 1][x + 1] = false;
                        } else {
                            space[z][y - 1][x + 1] = true;
                        }


                        if (space[z][y - 1][x] == true) {
                            space[z][y - 1][x] = false;
                        } else {
                            space[z][y - 1][x] = true;
                        }

                        if (space[z][y][x + 1] == true) {
                            space[z][y][x + 1] = false;
                        } else {
                            space[z][y][x + 1] = true;
                        }

                    }
                } else if (mods[5] == true) {


                    if (space[z][y + 1][x + 1] == true) {
                        space[z][y + 1][x + 1] = false;
                    } else {
                        space[z][y + 1][x + 1] = true;
                    }

                    if (space[z][y + 1][x - 1] == true) {
                        space[z][y + 1][x - 1] = false;
                    } else {
                        space[z][y + 1][x - 1] = true;
                    }

                    if (space[z][y + 1][x] == true) {
                        space[z][y + 1][x] = false;
                    } else {
                        space[z][y + 1][x] = true;
                    }

                    if (space[z][y][x + 1] == true) {
                        space[z][y][x + 1] = false;
                    } else {
                        space[z][y][x + 1] = true;
                    }

                    if (space[z][y][x - 1] == true) {
                        space[z][y][x - 1] = false;
                    } else {
                        space[z][y][x - 1] = true;
                    }
                } else {


                    if (space[z][y + 1][x + 1] == true) {
                        space[z][y + 1][x + 1] = false;
                    } else {
                        space[z][y + 1][x + 1] = true;
                    }


                    if (space[z][y + 1][x] == true) {
                        space[z][y + 1][x] = false;
                    } else {
                        space[z][y + 1][x] = true;
                    }


                    if (space[z][y][x + 1] == true) {
                        space[z][y][x + 1] = false;
                    } else {
                        space[z][y][x + 1] = true;
                    }

                }
            } else if (mods[3] == true) {
                if (mods[4] == true) {
                    if (mods[5] == true) {



                        if (space[z - 1][y + 1][x - 1] == true) {
                            space[z - 1][y + 1][x - 1] = false;
                        } else {
                            space[z - 1][y + 1][x - 1] = true;
                        }

                        if (space[z - 1][y + 1][x] == true) {
                            space[z - 1][y + 1][x] = false;
                        } else {
                            space[z - 1][y + 1][x] = true;
                        }


                        if (space[z - 1][y - 1][x - 1] == true) {
                            space[z - 1][y - 1][x - 1] = false;
                        } else {
                            space[z - 1][y - 1][x - 1] = true;
                        }

                        if (space[z - 1][y - 1][x] == true) {
                            space[z - 1][y - 1][x] = false;
                        } else {
                            space[z - 1][y - 1][x] = true;
                        }


                        if (space[z - 1][y][x - 1] == true) {
                            space[z - 1][y][x - 1] = false;
                        } else {
                            space[z - 1][y][x - 1] = true;
                        }

                        if (space[z - 1][y][x] == true) {
                            space[z - 1][y][x] = false;
                        } else {
                            space[z - 1][y][x] = true;
                        }


                        if (space[z][y + 1][x - 1] == true) {
                            space[z][y + 1][x - 1] = false;
                        } else {
                            space[z][y + 1][x - 1] = true;
                        }

                        if (space[z][y + 1][x] == true) {
                            space[z][y + 1][x] = false;
                        } else {
                            space[z][y + 1][x] = true;
                        }


                        if (space[z][y - 1][x - 1] == true) {
                            space[z][y - 1][x - 1] = false;
                        } else {
                            space[z][y - 1][x - 1] = true;
                        }

                        if (space[z][y - 1][x] == true) {
                            space[z][y - 1][x] = false;
                        } else {
                            space[z][y - 1][x] = true;
                        }


                        if (space[z][y][x - 1] == true) {
                            space[z][y][x - 1] = false;
                        } else {
                            space[z][y][x - 1] = true;
                        }
                    } else {


                        if (space[z - 1][y + 1][x] == true) {
                            space[z - 1][y + 1][x] = false;
                        } else {
                            space[z - 1][y + 1][x] = true;
                        }


                        if (space[z - 1][y - 1][x] == true) {
                            space[z - 1][y - 1][x] = false;
                        } else {
                            space[z - 1][y - 1][x] = true;
                        }


                        if (space[z - 1][y][x] == true) {
                            space[z - 1][y][x] = false;
                        } else {
                            space[z - 1][y][x] = true;
                        }


                        if (space[z][y + 1][x] == true) {
                            space[z][y + 1][x] = false;
                        } else {
                            space[z][y + 1][x] = true;
                        }


                        if (space[z][y - 1][x] == true) {
                            space[z][y - 1][x] = false;
                        } else {
                            space[z][y - 1][x] = true;
                        }

                    }
                } else if (mods[5] == true) {



                    if (space[z - 1][y + 1][x - 1] == true) {
                        space[z - 1][y + 1][x - 1] = false;
                    } else {
                        space[z - 1][y + 1][x - 1] = true;
                    }

                    if (space[z - 1][y + 1][x] == true) {
                        space[z - 1][y + 1][x] = false;
                    } else {
                        space[z - 1][y + 1][x] = true;
                    }


                    if (space[z - 1][y][x - 1] == true) {
                        space[z - 1][y][x - 1] = false;
                    } else {
                        space[z - 1][y][x - 1] = true;
                    }

                    if (space[z - 1][y][x] == true) {
                        space[z - 1][y][x] = false;
                    } else {
                        space[z - 1][y][x] = true;
                    }


                    if (space[z][y + 1][x - 1] == true) {
                        space[z][y + 1][x - 1] = false;
                    } else {
                        space[z][y + 1][x - 1] = true;
                    }

                    if (space[z][y + 1][x] == true) {
                        space[z][y + 1][x] = false;
                    } else {
                        space[z][y + 1][x] = true;
                    }


                    if (space[z][y][x - 1] == true) {
                        space[z][y][x - 1] = false;
                    } else {
                        space[z][y][x - 1] = true;
                    }
                } else {



                    if (space[z - 1][y + 1][x] == true) {
                        space[z - 1][y + 1][x] = false;
                    } else {
                        space[z - 1][y + 1][x] = true;
                    }


                    if (space[z - 1][y][x] == true) {
                        space[z - 1][y][x] = false;
                    } else {
                        space[z - 1][y][x] = true;
                    }


                    if (space[z][y + 1][x] == true) {
                        space[z][y + 1][x] = false;
                    } else {
                        space[z][y + 1][x] = true;
                    }
                }
            } else if (mods[4] == true) {
                if (mods[5] == true) {



                    if (space[z][y + 1][x - 1] == true) {
                        space[z][y + 1][x - 1] = false;
                    } else {
                        space[z][y + 1][x - 1] = true;
                    }

                    if (space[z][y + 1][x] == true) {
                        space[z][y + 1][x] = false;
                    } else {
                        space[z][y + 1][x] = true;
                    }


                    if (space[z][y - 1][x - 1] == true) {
                        space[z][y - 1][x - 1] = false;
                    } else {
                        space[z][y - 1][x - 1] = true;
                    }

                    if (space[z][y - 1][x] == true) {
                        space[z][y - 1][x] = false;
                    } else {
                        space[z][y - 1][x] = true;
                    }


                    if (space[z][y][x - 1] == true) {
                        space[z][y][x - 1] = false;
                    } else {
                        space[z][y][x - 1] = true;
                    }
                } else {



                    if (space[z][y + 1][x] == true) {
                        space[z][y + 1][x] = false;
                    } else {
                        space[z][y + 1][x] = true;
                    }


                    if (space[z][y - 1][x] == true) {
                        space[z][y - 1][x] = false;
                    } else {
                        space[z][y - 1][x] = true;
                    }

                }
            } else if (mods[5] == true) {



                if (space[z][y + 1][x - 1] == true) {
                    space[z][y + 1][x - 1] = false;
                } else {
                    space[z][y + 1][x - 1] = true;
                }

                if (space[z][y + 1][x] == true) {
                    space[z][y + 1][x] = false;
                } else {
                    space[z][y + 1][x] = true;
                }


                if (space[z][y][x - 1] == true) {
                    space[z][y][x - 1] = false;
                } else {
                    space[z][y][x - 1] = true;
                }
            } else {



                if (space[z][y + 1][x] == true) {
                    space[z][y + 1][x] = false;
                } else {
                    space[z][y + 1][x] = true;
                }
            }
        } else if (mods[2] == true) {
            if (mods[3] == true) {
                if (mods[4] == true) {
                    if (mods[5] == true) {

                        if (space[z - 1][y - 1][x + 1] == true) {
                            space[z - 1][y - 1][x + 1] = false;
                        } else {
                            space[z - 1][y - 1][x + 1] = true;
                        }

                        if (space[z - 1][y - 1][x - 1] == true) {
                            space[z - 1][y - 1][x - 1] = false;
                        } else {
                            space[z - 1][y - 1][x - 1] = true;
                        }

                        if (space[z - 1][y - 1][x] == true) {
                            space[z - 1][y - 1][x] = false;
                        } else {
                            space[z - 1][y - 1][x] = true;
                        }

                        if (space[z - 1][y][x + 1] == true) {
                            space[z - 1][y][x + 1] = false;
                        } else {
                            space[z - 1][y][x + 1] = true;
                        }

                        if (space[z - 1][y][x - 1] == true) {
                            space[z - 1][y][x - 1] = false;
                        } else {
                            space[z - 1][y][x - 1] = true;
                        }

                        if (space[z - 1][y][x] == true) {
                            space[z - 1][y][x] = false;
                        } else {
                            space[z - 1][y][x] = true;
                        }

                        if (space[z][y - 1][x + 1] == true) {
                            space[z][y - 1][x + 1] = false;
                        } else {
                            space[z][y - 1][x + 1] = true;
                        }

                        if (space[z][y - 1][x - 1] == true) {
                            space[z][y - 1][x - 1] = false;
                        } else {
                            space[z][y - 1][x - 1] = true;
                        }

                        if (space[z][y - 1][x] == true) {
                            space[z][y - 1][x] = false;
                        } else {
                            space[z][y - 1][x] = true;
                        }

                        if (space[z][y][x + 1] == true) {
                            space[z][y][x + 1] = false;
                        } else {
                            space[z][y][x + 1] = true;
                        }

                        if (space[z][y][x - 1] == true) {
                            space[z][y][x - 1] = false;
                        } else {
                            space[z][y][x - 1] = true;
                        }
                    } else {

                        if (space[z - 1][y - 1][x + 1] == true) {
                            space[z - 1][y - 1][x + 1] = false;
                        } else {
                            space[z - 1][y - 1][x + 1] = true;
                        }


                        if (space[z - 1][y - 1][x] == true) {
                            space[z - 1][y - 1][x] = false;
                        } else {
                            space[z - 1][y - 1][x] = true;
                        }

                        if (space[z - 1][y][x + 1] == true) {
                            space[z - 1][y][x + 1] = false;
                        } else {
                            space[z - 1][y][x + 1] = true;
                        }


                        if (space[z - 1][y][x] == true) {
                            space[z - 1][y][x] = false;
                        } else {
                            space[z - 1][y][x] = true;
                        }

                        if (space[z][y - 1][x + 1] == true) {
                            space[z][y - 1][x + 1] = false;
                        } else {
                            space[z][y - 1][x + 1] = true;
                        }


                        if (space[z][y - 1][x] == true) {
                            space[z][y - 1][x] = false;
                        } else {
                            space[z][y - 1][x] = true;
                        }

                        if (space[z][y][x + 1] == true) {
                            space[z][y][x + 1] = false;
                        } else {
                            space[z][y][x + 1] = true;
                        }

                    }
                } else if (mods[5] == true) {

                    if (space[z - 1][y][x + 1] == true) {
                        space[z - 1][y][x + 1] = false;
                    } else {
                        space[z - 1][y][x + 1] = true;
                    }

                    if (space[z - 1][y][x - 1] == true) {
                        space[z - 1][y][x - 1] = false;
                    } else {
                        space[z - 1][y][x - 1] = true;
                    }

                    if (space[z - 1][y][x] == true) {
                        space[z - 1][y][x] = false;
                    } else {
                        space[z - 1][y][x] = true;
                    }


                    if (space[z][y][x + 1] == true) {
                        space[z][y][x + 1] = false;
                    } else {
                        space[z][y][x + 1] = true;
                    }

                    if (space[z][y][x - 1] == true) {
                        space[z][y][x - 1] = false;
                    } else {
                        space[z][y][x - 1] = true;
                    }
                } else {


                    if (space[z - 1][y][x + 1] == true) {
                        space[z - 1][y][x + 1] = false;
                    } else {
                        space[z - 1][y][x + 1] = true;
                    }


                    if (space[z - 1][y][x] == true) {
                        space[z - 1][y][x] = false;
                    } else {
                        space[z - 1][y][x] = true;
                    }

                    if (space[z][y][x + 1] == true) {
                        space[z][y][x + 1] = false;
                    } else {
                        space[z][y][x + 1] = true;
                    }

                }
            } else if (mods[4] == true) {
                if (mods[5] == true) {

                    if (space[z][y - 1][x + 1] == true) {
                        space[z][y - 1][x + 1] = false;
                    } else {
                        space[z][y - 1][x + 1] = true;
                    }

                    if (space[z][y - 1][x - 1] == true) {
                        space[z][y - 1][x - 1] = false;
                    } else {
                        space[z][y - 1][x - 1] = true;
                    }

                    if (space[z][y - 1][x] == true) {
                        space[z][y - 1][x] = false;
                    } else {
                        space[z][y - 1][x] = true;
                    }

                    if (space[z][y][x + 1] == true) {
                        space[z][y][x + 1] = false;
                    } else {
                        space[z][y][x + 1] = true;
                    }

                    if (space[z][y][x - 1] == true) {
                        space[z][y][x - 1] = false;
                    } else {
                        space[z][y][x - 1] = true;
                    }
                } else {


                    if (space[z][y - 1][x + 1] == true) {
                        space[z][y - 1][x + 1] = false;
                    } else {
                        space[z][y - 1][x + 1] = true;
                    }


                    if (space[z][y - 1][x] == true) {
                        space[z][y - 1][x] = false;
                    } else {
                        space[z][y - 1][x] = true;
                    }

                    if (space[z][y][x + 1] == true) {
                        space[z][y][x + 1] = false;
                    } else {
                        space[z][y][x + 1] = true;
                    }

                }
            } else if (mods[5] == true) {


                if (space[z][y][x + 1] == true) {
                    space[z][y][x + 1] = false;
                } else {
                    space[z][y][x + 1] = true;
                }

                if (space[z][y][x - 1] == true) {
                    space[z][y][x - 1] = false;
                } else {
                    space[z][y][x - 1] = true;
                }
            } else {


                if (space[z][y][x + 1] == true) {
                    space[z][y][x + 1] = false;
                } else {
                    space[z][y][x + 1] = true;
                }

            }
        } else if (mods[3] == true) {
            if (mods[4] == true) {
                if (mods[5] == true) {

                    if (space[z - 1][y - 1][x - 1] == true) {
                        space[z - 1][y - 1][x - 1] = false;
                    } else {
                        space[z - 1][y - 1][x - 1] = true;
                    }

                    if (space[z - 1][y - 1][x] == true) {
                        space[z - 1][y - 1][x] = false;
                    } else {
                        space[z - 1][y - 1][x] = true;
                    }


                    if (space[z - 1][y][x - 1] == true) {
                        space[z - 1][y][x - 1] = false;
                    } else {
                        space[z - 1][y][x - 1] = true;
                    }

                    if (space[z - 1][y][x] == true) {
                        space[z - 1][y][x] = false;
                    } else {
                        space[z - 1][y][x] = true;
                    }


                    if (space[z][y - 1][x - 1] == true) {
                        space[z][y - 1][x - 1] = false;
                    } else {
                        space[z][y - 1][x - 1] = true;
                    }

                    if (space[z][y - 1][x] == true) {
                        space[z][y - 1][x] = false;
                    } else {
                        space[z][y - 1][x] = true;
                    }

                    if (space[z][y][x - 1] == true) {
                        space[z][y][x - 1] = false;
                    } else {
                        space[z][y][x - 1] = true;
                    }
                } else {



                    if (space[z - 1][y - 1][x] == true) {
                        space[z - 1][y - 1][x] = false;
                    } else {
                        space[z - 1][y - 1][x] = true;
                    }



                    if (space[z - 1][y][x] == true) {
                        space[z - 1][y][x] = false;
                    } else {
                        space[z - 1][y][x] = true;
                    }


                    if (space[z][y - 1][x] == true) {
                        space[z][y - 1][x] = false;
                    } else {
                        space[z][y - 1][x] = true;
                    }

                }
            } else if (mods[5] == true) {




                if (space[z - 1][y][x - 1] == true) {
                    space[z - 1][y][x - 1] = false;
                } else {
                    space[z - 1][y][x - 1] = true;
                }

                if (space[z - 1][y][x] == true) {
                    space[z - 1][y][x] = false;
                } else {
                    space[z - 1][y][x] = true;
                }


                if (space[z][y][x - 1] == true) {
                    space[z][y][x - 1] = false;
                } else {
                    space[z][y][x - 1] = true;
                }
            } else {



                if (space[z - 1][y][x] == true) {
                    space[z - 1][y][x] = false;
                } else {
                    space[z - 1][y][x] = true;
                }



            }
        } else if (mods[4] == true) {
            if (mods[5] == true) {



                if (space[z][y - 1][x - 1] == true) {
                    space[z][y - 1][x - 1] = false;
                } else {
                    space[z][y - 1][x - 1] = true;
                }

                if (space[z][y - 1][x] == true) {
                    space[z][y - 1][x] = false;
                } else {
                    space[z][y - 1][x] = true;
                }

                if (space[z][y][x - 1] == true) {
                    space[z][y][x - 1] = false;
                } else {
                    space[z][y][x - 1] = true;
                }
            } else {


                if (space[z][y - 1][x] == true) {
                    space[z][y - 1][x] = false;
                } else {
                    space[z][y - 1][x] = true;
                }

            }
        } else if (mods[5] == true) {


            if (space[z][y][x - 1] == true) {
                space[z][y][x - 1] = false;
            } else {
                space[z][y][x - 1] = true;
            }
        }
        space[z][y][x] = false;
    }

    public static void fireSpace() {

        for (int z = 0; z < MAXZ; z++) {
            if (z == 0) {
                for (int y = 0; y < MAXY; y++) {
                    if (y == 0) {
                        for (int x = 0; x < MAXX; x++) {
                            if (x == 0) {
                                //z0,y0,x0
                                if (space[z][y][x] == true) {
                                    trigger(z, y, x, new boolean[]{true, true, true, false, false, false});
                                }
                            } else if (x == MAXX - 1) {
                                //z0,y0,x^
                                if (space[z][y][x] == true) {
                                    trigger(z, y, x, new boolean[]{true, true, false, false, false, true});
                                }
                            } else {
                                //z0,y0,x~
                                if (space[z][y][x] == true) {
                                    trigger(z, y, x, new boolean[]{true, true, true, false, false, true});
                                }
                            }
                        }
                    } else if (y == MAXY - 1) {
                        for (int x = 0; x < MAXX; x++) {
                            if (x == 0) {
                                //z0,y^,x0
                                if (space[z][y][x] == true) {
                                    trigger(z, y, x, new boolean[]{true, false, true, false, true, false});
                                }
                            } else if (x == MAXX - 1) {
                                //z0,y^,x^
                                if (space[z][y][x] == true) {
                                    trigger(z, y, x, new boolean[]{true, false, false, false, true, true});
                                }
                            } else {
                                //z0,y^,x~
                                if (space[z][y][x] == true) {
                                    trigger(z, y, x, new boolean[]{true, false, true, false, true, true});
                                }
                            }
                        }
                    } else {
                        for (int x = 0; x < MAXX; x++) {
                            if (x == 0) {
                                //z0,y~,x0
                                if (space[z][y][x] == true) {
                                    trigger(z, y, x, new boolean[]{true, true, true, false, true, false});
                                }
                            } else if (x == MAXX - 1) {
                                //z0,y~,x^
                                if (space[z][y][x] == true) {
                                    trigger(z, y, x, new boolean[]{true, true, false, false, true, true});
                                }
                            } else {
                                //z0,y~,x~
                                if (space[z][y][x] == true) {
                                    trigger(z, y, x, new boolean[]{true, true, true, false, true, true});
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
                                //z^,y0,x0
                                if (space[z][y][x] == true) {
                                    trigger(z, y, x, new boolean[]{false, true, true, true, false, false});
                                }
                            } else if (x == MAXX - 1) {
                                //z^,y0,x^                                
                                if (space[z][y][x] == true) {
                                    trigger(z, y, x, new boolean[]{false, true, false, true, false, true});
                                }
                            } else {
                                //z^,y0,x~
                                if (space[z][y][x] == true) {
                                    trigger(z, y, x, new boolean[]{false, true, true, true, false, true});
                                }
                            }
                        }
                    } else if (y == MAXY - 1) {
                        for (int x = 0; x < MAXX; x++) {
                            if (x == 0) {
                                //z^,y^,x0
                                if (space[z][y][x] == true) {
                                    trigger(z, y, x, new boolean[]{false, false, true, true, true, false});
                                }
                            } else if (x == MAXX - 1) {
                                //z^,y^,x^
                                if (space[z][y][x] == true) {
                                    trigger(z, y, x, new boolean[]{false, false, false, true, true, true});
                                }
                            } else {
                                //z^,y^,x~
                                if (space[z][y][x] == true) {
                                    trigger(z, y, x, new boolean[]{false, false, true, true, true, true});
                                }
                            }
                        }
                    } else {
                        for (int x = 0; x < MAXX; x++) {
                            if (x == 0) {
                                //z^,y~,x0
                                if (space[z][y][x] == true) {
                                    trigger(z, y, x, new boolean[]{false, true, true, true, true, false});
                                }
                            } else if (x == MAXX - 1) {
                                //z^,y~,x^
                                if (space[z][y][x] == true) {
                                    trigger(z, y, x, new boolean[]{false, true, false, true, true, true});
                                }
                            } else {
                                //z^,y~,x~
                                if (space[z][y][x] == true) {
                                    trigger(z, y, x, new boolean[]{false, true, true, true, true, true});
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
                                if (space[z][y][x] == true) {
                                    trigger(z, y, x, new boolean[]{true, true, true, true, false, false});
                                }
                            } else if (x == MAXX - 1) {
                                //z~,y0,x^
                                if (space[z][y][x] == true) {
                                    trigger(z, y, x, new boolean[]{true, true, false, true, false, true});
                                }
                            } else {
                                //z~,y0,x~
                                if (space[z][y][x] == true) {
                                    trigger(z, y, x, new boolean[]{true, true, true, true, false, true});
                                }
                            }
                        }
                    } else if (y == MAXY - 1) {
                        for (int x = 0; x < MAXX; x++) {
                            if (x == 0) {
                                //z~,y^,x0
                                if (space[z][y][x] == true) {
                                    trigger(z, y, x, new boolean[]{true, false, true, true, true, false});
                                }
                            } else if (x == MAXX - 1) {
                                //z~,y^,x^
                                if (space[z][y][x] == true) {
                                    trigger(z, y, x, new boolean[]{true, false, false, true, true, true});
                                }
                            } else {
                                //z~,y^,x~
                                if (space[z][y][x] == true) {
                                    trigger(z, y, x, new boolean[]{true, false, true, true, true, true});
                                }
                            }
                        }
                    } else {
                        for (int x = 0; x < MAXX; x++) {
                            if (x == 0) {
                                //z~,y~,x0
                                if (space[z][y][x] == true) {
                                    trigger(z, y, x, new boolean[]{true, true, true, true, true, false});
                                }
                            } else if (x == MAXX - 1) {
                                //z~,y~,x^
                                if (space[z][y][x] == true) {
                                    trigger(z, y, x, new boolean[]{true, true, false, true, true, true});
                                }
                            } else {
                                //z~,y~,x~                                
                                if (space[z][y][x] == true) {
                                    trigger(z, y, x, new boolean[]{true, true, true, true, true, true});
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
