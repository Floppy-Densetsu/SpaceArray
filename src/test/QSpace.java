/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 * The four states are 0(buffered in), 1(core in), 2(buffered out), 3(core out)
 *
 * @author Flopp_000
 */
public class QSpace {

    static int STATES;
    static int BASE;
    static int BASE2;
    static int BASE3;
    boolean[][] face;

    public QSpace() {

        STATES = 4;
        BASE = 3;
        BASE2 = BASE * BASE;
        BASE3 = BASE * BASE * BASE;
        face = new boolean[BASE3][STATES];

        for (int i = 0; i < BASE3; i++) {
            for (int j = 0; j < STATES; j++) {
                face[i][j] = false;
            }
        }

    }

    public QSpace(int base) {

        STATES = 4;
        BASE = base;
        BASE2 = BASE * BASE;
        BASE3 = BASE * BASE * BASE;
        face = new boolean[BASE3][STATES];

        for (int i = 0; i < BASE3; i++) {
            for (int j = 0; j < STATES; j++) {
                face[i][j] = false;
            }
        }
    }

    public void passThru() {
        for (int i = 0; i < BASE3; i++) {
            if (face[i][1] == true) {
                face[i][1] = false;
                face[(BASE3 - 1) - i][2] = true;
            }
        }
    }

    public void processCore3() {
        for (int i = 0; i < STATES; i++) {
            if (this.face[62][i]) {
                return;
            }
        }

        for (int f = 0; f < BASE3; f++) {
            if (this.face[f][1]) {
                this.face[f][1] = false;

                if (f < (BASE3 - BASE2) / 2) { //  f<50 top side
                    if (f % BASE <= 1) {  //left side
                        if (f % BASE2 <= BASE + 1) { //front side
                            if (this.face[(BASE2 + BASE + 1)][3]) { //31
                                this.face[f][2] = true;
                                this.face[0][2] = true;
                                this.face[((BASE2 + BASE) + 1)][3] = false;
                            } else if (this.face[((BASE3 - 1) - (BASE2 + BASE + 1))][3]) { //93
                                this.face[f][2] = true;
                                this.face[(BASE3 - 1)][2] = true;
                                this.face[((BASE3 - 1) - (BASE2 + BASE + 1))][3] = false;
                            }

                            //room for else pass through?

                        } else if (f % BASE2 >= (BASE * 3)) { //back side
                            if (this.face[BASE2 + (BASE * 3) + 1][3]) { //41
                                this.face[f][2] = true;
                                this.face[BASE2 - BASE][2] = true;
                                this.face[BASE2 + (BASE * 3) + 1][3] = false;
                            } else if (this.face[((BASE - 1) * 2) + (BASE2 * 3)][3]) { //83
                                //(this.face[(BASE3 - (BASE2 * 2)) + ((BASE - 1) * 2)][3]){
                                this.face[f][2] = true;
                                this.face[(BASE - 1) + (BASE3 - BASE2)][2] = true;
                                this.face[((BASE - 1) * 2) + (BASE2 * 3)][3] = false;
                            }

                            //room for else pass through?
                        } else if (this.face[BASE2 + (BASE * 2) + 1][3]) { //36
                            this.face[f][2] = true;
                            this.face[BASE * 2][2] = true;
                            this.face[BASE2 + (BASE * 2) + 1][3] = false;

                        } else if (this.face[((BASE - 1) * 3 + 1) + (BASE2 * 3)][3]) { //88
                            this.face[f][2] = true;
                            this.face[(BASE3 - 1) - (BASE * 2)][2] = true;
                            this.face[((BASE - 1) * 3) + 1 + (BASE2 * 3)][3] = false;
                        }
                    } else if (f % BASE >= (BASE - 2)) { //right side
                        if (f % BASE2 < BASE * 2) { //front side
                            if (this.face[BASE2 + ((BASE - 1) * 2)][3]) { //33
                                this.face[f][2] = true;
                                this.face[(BASE - 1)][2] = true;
                                this.face[BASE2 + ((BASE - 1) * 2)][3] = false;
                            } else if (this.face[(BASE3 - 1) - (BASE2 + ((BASE - 1) * 2))][3]) { //opposite of 33. 
                                this.face[f][2] = true;
                                this.face[(BASE3 - BASE)][2] = true;
                                this.face[(BASE3 - 1) - (BASE2 + ((BASE - 1) * 2))][3] = false;
                            }
                            //room for else?


                        } else if (f % BASE2 > (BASE * 3)) { //back side
                            if (this.face[BASE2 + (BASE * 3) + 3][3]) {  //43
                                this.face[f][2] = true;
                                this.face[(BASE2 - 1)][2] = true;
                                this.face[BASE2 + (BASE * 3) + 3][3] = false;
                            } else if (this.face[(BASE3 - 1) - (BASE2 + (BASE * 3) + 3)][3]) { //81
                                this.face[f][2] = true;
                                this.face[(BASE3 - BASE2)][2] = true;
                                this.face[(BASE3 - 1) - (BASE2 + (BASE * 3) + 3)][3] = false;
                            }
                        } else if (this.face[BASE2 + (BASE * 2) + 3][3]) { //thin side, 38
                            this.face[f][2] = true;
                            this.face[BASE * 2 + BASE - 1][2] = true;
                            this.face[BASE2 + (BASE * 2) + 3][3] = false;
                        } else if (this.face[BASE3 - (BASE2 + (BASE * 2) + 3)][3]) { //opposite, 86
                            this.face[f][2] = true;
                            this.face[(BASE3 - (BASE * 3))][2] = true;
                            this.face[BASE3 - (BASE2 + (BASE * 2) + 3)][3] = false;
                        }
                    } else if (f % BASE2 < (BASE * 2)) { //else if front side
                        if (this.face[BASE2 + BASE + 2][3]) { //32
                            this.face[f][2] = true;
                            this.face[2][2] = true;
                            this.face[(BASE2 + BASE + 2)][3] = false;
                        } else if (this.face[(BASE3 - (BASE2 + BASE + 3))][3]) { //92
                            this.face[f][2] = true;
                            this.face[BASE3 - 3][2] = true;
                            this.face[BASE3 - (BASE2 + BASE + 3)][3] = false;
                        }
                        //room for else
                    } else if (f % BASE2 > BASE * 3) { //if back side
                        if (this.face[(BASE2 * 2) - (BASE + 3)][3]) { //42
                            this.face[f][2] = true;
                            this.face[BASE2 - 3][2] = true;
                            this.face[(BASE2 * 2) - (BASE + 3)][3] = false;
                        } else if (this.face[(BASE3 - 1) - (BASE2 * 2) + (BASE + 2)][3]) //82
                        {
                            this.face[f][2] = true;
                        }
                        this.face[BASE3 - BASE2 + 2][2] = true;
                        this.face[(BASE3 - (BASE2 * 2)) + BASE + 2][3] = false;

                    }

                    if (f + 1 % BASE > 1) { // if between left and right sides
                        if (f > BASE && f < BASE2 - BASE) { //if between front and back
                            if (this.face[BASE2 + ((BASE - 1) * 3)][3]) { //37
                                this.face[f][2] = true;
                                this.face[(BASE - 1) * 3][2] = true;
                                this.face[BASE2 + ((BASE - 1) * 3)][3] = false;
                            } else if (this.face[(BASE3 - 1) - (BASE2 + ((BASE - 1) * 3))][3]) { //87
                                this.face[f][2] = true;
                                this.face[BASE3 - ((BASE - 1) * 3)][2] = true;
                                this.face[(BASE3 - 1) - (BASE2 + ((BASE - 1) * 3))][3] = false;
                            }
                        }
                    }
                } else if (f >= (BASE3 - (BASE2 * 2))) { //bottom side
                    if (f % BASE <= 1) { //left side
                        if (f % BASE2 < BASE * 2) {// front side
                            if (this.face[(BASE2 * 3) + BASE + 1][3]) { //81
                                this.face[f][2] = true;
                                this.face[BASE3 - BASE2][2] = true;
                                this.face[(BASE2 * 3) + BASE + 1][3] = false;
                            } else if (this.face[(BASE2 * 2) - (BASE + 2)][3]) { //43
                                this.face[f][2] = true;
                                this.face[BASE2 - 1][2] = true;
                                this.face[(BASE2 * 2) - (BASE + 2)][3] = false;
                            }

                            //room for else
                        } else if (f % BASE2 >= (BASE * 3)) { //back side
                            if (this.face[BASE3 - (BASE2 + (BASE * 2)) + 1][3]) { //91
                                this.face[f][2] = true;
                                this.face[BASE3 - BASE][2] = true;
                                this.face[BASE3 - (BASE2 + (BASE * 2)) + 1][3] = false;
                            } else if (this.face[BASE2 + ((BASE - 1) * 2)][3]) { //33
                                this.face[f][2] = true;
                                this.face[BASE - 1][2] = true;
                                this.face[BASE2 + ((BASE - 1) * 2)][3] = false;
                            }
                            //room for else
                        } else if (this.face[BASE3 - (BASE2 + (BASE * 3)) + 1][3]) { //86
                            this.face[f][2] = true;
                            this.face[BASE3 - (BASE * 3)][2] = true;
                            this.face[BASE3 - (BASE2 + (BASE * 3)) + 1][3] = false;
                        } else if (this.face[(BASE2 * 2) - ((BASE + 1) * 2)][3]) { //38
                            this.face[f][2] = true;
                            this.face[BASE2 - (BASE * 2) - 1][2] = true;
                            this.face[(BASE2 * 2) - ((BASE + 1) * 2)][3] = false;
                        }

                    } else if(f + 2 % BASE <= 1){ //Right Side
                        if(f % BASE2 < (BASE * 2)){ //front side
                            if(this.face[BASE3 - ((BASE2 - (BASE - 1)) * 2)][3]){ //83
                                this.face[f][2] = true;
                                this.face[BASE3 - (BASE2 - (BASE - 1))][2] = true;
                                this.face[BASE3 - ((BASE2 - (BASE - 1)) * 2)][3] = false;
                            } else if(this.face[(BASE2 * 2) - (BASE * 2) + 1][3]){ //41
                                this.face[f][2] = true;
                                this.face[BASE2 - BASE][2] = true;
                                this.face[(BASE2 * 2) - (BASE * 2) + 1][3] = false;
                            }
                            // room for else
                        } else if(f % BASE2 >= BASE * 3){ //back side 
                            if(this.face[BASE3 - (BASE2 + BASE + 2)][3]){ //93
                                this.face[f][2] = true;
                                this.face[BASE3 - 1][2] = true;
                                this.face[BASE3 - (BASE2 + BASE + 2)][3] = false;
                            } else if(this.face[BASE2 + BASE + 1][3]){ //31
                                this.face[f][2] = true;
                                this.face[0][2] = true;
                                this.face[BASE2 + BASE + 1][3] = false;
                            }
                            //room for else
                        } else if(this.face[BASE3 - (BASE2 * 2) + (BASE * 2) + 3][3]){ //88
                            this.face[f][2] = true;
                            this.face[BASE3 - ((BASE * 2) + 1)][2] = true;
                            this.face[BASE3 - (BASE2 * 2) + (BASE * 2) + 3][3] = false;
                        } else if(this.face[BASE2 + (BASE * 2) + 1][3]){ //36
                            this.face[f][2] = true;
                            this.face[BASE * 2][2] = true;
                            this.face[BASE2 + (BASE * 2) + 1][3] = false;
                        }
                        //room for else
                    } else if(f % BASE2 < (BASE * 2)){ //front center
                        if(this.face[(BASE3 - (BASE2 * 2)) + BASE + 2][3]){ //82
                            this.face[f][2] = true;
                            this.face[(BASE3 - BASE2) + 2][2] = true;
                            this.face[(BASE3 - (BASE2 * 2)) + BASE + 2][3] = false;
                        } else if(this.face[(BASE2 * 2) - (BASE + 3)][3]){ //42
                            this.face[f][2] = true;
                            this.face[BASE2 - 3][2] = true;
                            this.face[(BASE2 * 2) - (BASE + 3)][3] = false;
                        }
                        //room for else
                    } else if(f % BASE2 >= (BASE * 3)){ //back center
                        if(this.face[BASE3 - (BASE2 + BASE + 3)][3]){ //92
                            this.face[f][2] = true;
                            this.face[BASE3 - 3][2] = true;
                            this.face[BASE3 - (BASE2 + BASE + 3)][3] = false;
                        } else if(this.face[BASE2 + BASE + 2][3]){ //32
                            this.face[f][2] = true;
                            this.face[2][2] = true;
                            this.face[BASE2 + BASE + 2][3] = false;
                        }
                        //room for else
                    } else if(this.face[BASE3 - ((BASE - 1) * 3)][3]){ //112 last bottom possibility
                        this.face[f][2] = true;
                        this.face[BASE3 - ((BASE - 1) * 3)][3] = false; 
                    
                    }
                }else if(f % BASE <= 1){ //left center
                    if(f % BASE2 < BASE * 2){ //front
                        if(this.face[(BASE2 * 2) + BASE + 1][3]){ //56
                            this.face[f][2] = true;
                            this.face[BASE2 * 2][2] = true;
                            this.face[(BASE2 * 2) + BASE + 1][3] = false;
                        } else if(this.face[(BASE2 * 2) + (BASE * 3) + 3][3]){ //68
                            this.face[f][2] = true;
                            this.face[(BASE2 * 3) - 1][2] = true;
                            this.face[(BASE2 * 2) + (BASE * 3) + 3][3] = false;
                        }
                    } else if(f % BASE2 >= BASE * 3){ //back
                        if(this.face[(BASE2 * 2) + (BASE * 3) + 1][3]){ // 66
                            this.face[f][2] = true;
                            this.face[(BASE2 * 2) + (BASE * 4)][2] = true;
                            this.face[(BASE2 * 2) + (BASE *3) + 1][3] = false;
                        } else if(this.face[(BASE2 * 2) + BASE + 3][3]){ //58
                            this.face[f][2] = true;
                            this.face[(BASE2 * 2) + BASE - 1][2] = true;
                            this.face[(BASE2 * 2) + BASE + 3][3] = false;
                        }
                    } else if(this.face[((BASE3 - BASE) / 2) + 1][3]){ //61
                        this.face[f][2] = true;
                        this.face[((BASE3 - BASE) / 2) + 1][3] = false;
                    } else if(this.face[((BASE3 + BASE) / 2) - 2][3]){ //63
                        this.face[f][2] = false;
                        this.face[((BASE3 + BASE) / 2) - 2][3] = false;
                    }
                } else if(f + 2 % BASE <= 1){ //right center
                    if(f % BASE2 < BASE * 2){ //front
                        if(this.face[(BASE2 * 2) + ((BASE - 1) * 2)][3]){ //58
                            this.face[f][2] = true;
                            this.face[(BASE2 * 2) + (BASE - 1)][2] = true;
                            this.face[(BASE2 * 2) + ((BASE - 1) * 2)][3] = false;
                        } else if(this.face[(BASE2 * 3) - (BASE * 2) + 1][3]){ //66
                            this.face[f][2] = true;
                            this.face[(BASE2 * 3) - BASE][2] = true;
                            this.face[(BASE2 * 3) - (BASE * 2) + 1][3] = false;
                        }
                    } else if(f % BASE2 >= (BASE * 3)){ //rear
                        if(this.face[(BASE2 * 3) - (BASE + 2)][3]){ //68
                            this.face[f][2] = true;
                            this.face[(BASE2 * 3) - 1][2] = true;
                            this.face[(BASE2 * 3) - (BASE + 2)][3] = false;
                        } else if(this.face[(BASE2 * 2) + BASE + 1][3]){ //56
                            this.face[f][2] = true;
                            this.face[BASE2 * 2][2] = true;
                            this.face[(BASE2 * 2) + BASE + 1][3] = false;
                        }
                    } else if(this.face[(BASE2 * 2) + (BASE * 2) + 3][3]){ //63
                        this.face[f][2] = true;
                        this.face[(BASE2 * 2) + (BASE * 3) - 2][2] = false;
                    } else if(this.face[(BASE2 * 2) + (BASE * 2) + 1][3]){ //61
                        this.face[f][2] = true;
                        this.face[(BASE2 * 2) + (BASE * 2) + 1][3] = false;
                    }
                }
            }
        }
    }

    public void processCore2() {
        for (int i = 0; i < STATES; i++) {
            if (this.face[(int) ((BASE3 - 1) / 2)][i]) {
                return;
            }
        }

        for (int f = 0; f < BASE3; f++) {
            if (this.face[f][1]) {
                this.face[f][1] = false;

                if (f < (BASE3 - BASE2) / 2) { //  f<50 top side
                    if (f % BASE <= 1) {  //left side
                        if (f % BASE2 <= BASE + 1) { //front side
                            if (this.face[(BASE2 + BASE + 1)][3]) { //31
                                this.face[f][2] = true;
                                this.face[0][2] = true;
                                this.face[((BASE2 + BASE) + 1)][3] = false;
                            } else if (this.face[((BASE3 - 1) - (BASE2 + BASE + 1))][3]) { //93
                                this.face[f][2] = true;
                                this.face[(BASE3 - 1)][2] = true;
                                this.face[((BASE3 - 1) - (BASE2 + BASE + 1))][3] = false;
                            }

                            //room for else pass through?

                        } else if (f % BASE2 >= (BASE * 3)) { //back side
                            if (this.face[BASE2 + (BASE * 3) + 1][3]) { //41
                                this.face[f][2] = true;
                                this.face[BASE2 - BASE][2] = true;
                                this.face[BASE2 + (BASE * 3) + 1][3] = false;
                            } else if (this.face[((BASE - 1) * 2) + (BASE2 * 3)][3]) { //83
                                //(this.face[(BASE3 - (BASE2 * 2)) + ((BASE - 1) * 2)][3]){
                                this.face[f][2] = true;
                                this.face[(BASE - 1) + (BASE3 - BASE2)][2] = true;
                                this.face[((BASE - 1) * 2) + (BASE2 * 3)][3] = false;
                            }

                            //room for else pass through?
                        } else if (this.face[BASE2 + (BASE * 2) + 1][3]) { //36
                            this.face[f][2] = true;
                            this.face[BASE * 2][2] = true;
                            this.face[BASE2 + (BASE * 2) + 1][3] = false;

                        } else if (this.face[((BASE - 1) * 3 + 1) + (BASE2 * 3)][3]) { //88
                            this.face[f][2] = true;
                            this.face[(BASE3 - 1) - (BASE * 2)][2] = true;
                            this.face[((BASE - 1) * 3) + 1 + (BASE2 * 3)][3] = false;
                        }
                    } else if (f % BASE >= (BASE - 2)) { //right side
                        if (f % BASE2 < BASE * 2) { //front side
                            if (this.face[BASE2 + ((BASE - 1) * 2)][3]) { //33
                                this.face[f][2] = true;
                                this.face[(BASE - 1)][2] = true;
                                this.face[BASE2 + ((BASE - 1) * 2)][3] = false;
                            } else if (this.face[(BASE3 - 1) - (BASE2 + ((BASE - 1) * 2))][3]) { //opposite of 33. 
                                this.face[f][2] = true;
                                this.face[(BASE3 - BASE)][2] = true;
                                this.face[(BASE3 - 1) - (BASE2 + ((BASE - 1) * 2))][3] = false;
                            }
                            //room for else?


                        } else if (f % BASE2 > (BASE * 3)) { //back side
                            if (this.face[BASE2 + (BASE * 3) + 3][3]) {  //43
                                this.face[f][2] = true;
                                this.face[(BASE2 - 1)][2] = true;
                                this.face[BASE2 + (BASE * 3) + 3][3] = false;
                            } else if (this.face[(BASE3 - 1) - (BASE2 + (BASE * 3) + 3)][3]) { //81
                                this.face[f][2] = true;
                                this.face[(BASE3 - BASE2)][2] = true;
                                this.face[(BASE3 - 1) - (BASE2 + (BASE * 3) + 3)][3] = false;
                            }
                        } else if (this.face[BASE2 + (BASE * 2) + 3][3]) { //thin side, 38
                            this.face[f][2] = true;
                            this.face[BASE * 2 + BASE - 1][2] = true;
                            this.face[BASE2 + (BASE * 2) + 3][3] = false;
                        } else if (this.face[BASE3 - (BASE2 + (BASE * 2) + 3)][3]) { //opposite, 86
                            this.face[f][2] = true;
                            this.face[(BASE3 - (BASE * 3))][2] = true;
                            this.face[BASE3 - (BASE2 + (BASE * 2) + 3)][3] = false;
                        }
                    } else if (f % BASE2 < (BASE * 2)) { //else if front side
                        if (this.face[BASE2 + BASE + 2][3]) { //32
                            this.face[f][2] = true;
                            this.face[2][2] = true;
                            this.face[(BASE2 + BASE + 2)][3] = false;
                        } else if (this.face[(BASE3 - (BASE2 + BASE + 3))][3]) { //92
                            this.face[f][2] = true;
                            this.face[BASE3 - 3][2] = true;
                            this.face[BASE3 - (BASE2 + BASE + 3)][3] = false;
                        }
                        //room for else
                    } else if (f % BASE2 > BASE * 3) { //if back side
                        if (this.face[(BASE2 * 2) - (BASE + 3)][3]) { //42
                            this.face[f][2] = true;
                            this.face[BASE2 - 3][2] = true;
                            this.face[(BASE2 * 2) - (BASE + 3)][3] = false;
                        } else if (this.face[(BASE3 - 1) - (BASE2 * 2) + (BASE + 2)][3]) //82
                        {
                            this.face[f][2] = true;
                        }
                        this.face[BASE3 - BASE2 + 2][2] = true;
                        this.face[(BASE3 - (BASE2 * 2)) + BASE + 2][3] = false;

                    }

                    if (f + 1 % BASE > 1) { // if between left and right sides
                        if (f > BASE && f < BASE2 - BASE) { //if between front and back
                            if (this.face[BASE2 + ((BASE - 1) * 3)][3]) { //37
                                this.face[f][2] = true;
                                this.face[(BASE - 1) * 3][2] = true;
                                this.face[BASE2 + ((BASE - 1) * 3)][3] = false;
                            } else if (this.face[(BASE3 - 1) - (BASE2 + ((BASE - 1) * 3))][3]) { //87
                                this.face[f][2] = true;
                                this.face[BASE3 - ((BASE - 1) * 3)][2] = true;
                                this.face[(BASE3 - 1) - (BASE2 + ((BASE - 1) * 3))][3] = false;
                            }
                        }
                    }
                } else if (f >= (BASE3 - (BASE2 * 2))) { //bottom side
                    if (f % BASE <= 1) { //left side
                        if (f % BASE2 < BASE * 2) {// front side
                            if (this.face[(BASE2 * 3) + BASE + 1][3]) { //81
                                this.face[f][2] = true;
                                this.face[BASE3 - BASE2][2] = true;
                                this.face[(BASE2 * 3) + BASE + 1][3] = false;
                            } else if (this.face[(BASE2 * 2) - (BASE + 2)][3]) { //43
                                this.face[f][2] = true;
                                this.face[BASE2 - 1][2] = true;
                                this.face[(BASE2 * 2) - (BASE + 2)][3] = false;
                            }

                            //room for else
                        } else if (f % BASE2 >= (BASE * 3)) { //back side
                            if (this.face[BASE3 - (BASE2 + (BASE * 2)) + 1][3]) { //91
                                this.face[f][2] = true;
                                this.face[BASE3 - BASE][2] = true;
                                this.face[BASE3 - (BASE2 + (BASE * 2)) + 1][3] = false;
                            } else if (this.face[BASE2 + ((BASE - 1) * 2)][3]) { //33
                                this.face[f][2] = true;
                                this.face[BASE - 1][2] = true;
                                this.face[BASE2 + ((BASE - 1) * 2)][3] = false;
                            }
                            //room for else
                        } else if (this.face[BASE3 - (BASE2 + (BASE * 3)) + 1][3]) { //86
                            this.face[f][2] = true;
                            this.face[BASE3 - (BASE * 3)][2] = true;
                            this.face[BASE3 - (BASE2 + (BASE * 3)) + 1][3] = false;
                        } else if (this.face[(BASE2 * 2) - ((BASE + 1) * 2)][3]) { //38
                            this.face[f][2] = true;
                            this.face[BASE2 - (BASE * 2) - 1][2] = true;
                            this.face[(BASE2 * 2) - ((BASE + 1) * 2)][3] = false;
                        }

                    }
                }
            }
        }
    }

    public void processCore() {
        if (BASE == 3) {
            if (face[(int) ((BASE3 - 1) / 2)][1] == true) {
                for (int i = 0; i < BASE3; i++) {
                    if (face[i][1] == true) {
                        face[i][1] = false;
                        switch (i) {
                            case 0:
                                face[0][2] = true;
                                break;
                            case 1:
                                face[19][2] = true;
                                break;
                            case 2:
                                face[20][2] = true;
                                break;
                            case 3:
                                face[5][2] = true;
                                break;
                            case 4:
                                face[4][2] = true;
                                break;
                            case 5:
                                face[23][23] = true;
                                break;
                            case 6:
                                face[2][2] = true;
                                break;
                            case 7:
                                face[1][2] = true;
                                break;
                            case 8:
                                face[26][2] = true;
                                break;
                            case 9:
                                face[11][2] = true;
                                break;
                            case 10:
                                face[10][2] = true;
                                break;
                            case 11:
                                face[17][2] = true;
                                break;
                            case 12:
                                face[12][2] = true;
                                break;
                            case 13:
                                face[13][2] = true;
                                break;
                            case 14:
                                face[14][2] = true;
                                break;
                            case 15:
                                face[9][2] = true;
                                break;
                            case 16:
                                face[16][2] = true;
                                break;
                            case 17:
                                face[15][2] = true;
                                break;
                            case 18:
                                face[0][2] = true;
                                break;
                            case 19:
                                face[25][2] = true;
                                break;
                            case 20:
                                face[24][2] = true;
                                break;
                            case 21:
                                face[3][2] = true;
                                break;
                            case 22:
                                face[22][2] = true;
                                break;
                            case 23:
                                face[21][2] = true;
                                break;
                            case 24:
                                face[6][2] = true;
                                break;
                            case 25:
                                face[7][2] = true;
                                break;
                            case 26:
                                face[18][2] = true;
                                break;
                        }


                    }
                }
            } else {
                for (int i = 0; i < BASE3; i++) {
                    if (face[i][1] == true) {
                        face[i][1] = false;
                        face[(BASE3 - 1) - i][2] = true;
                    }
                }
            }
        } else if (BASE == 5) {
            if (face[(int) ((BASE3 - 1) / 2)][1] == true) {
                for (int i = 0; i < BASE3; i++) {
                    if (face[i][1] == true) {
                        face[i][1] = false;
                        switch (i) {
                            case 0:
                                face[0][2] = true;
                                break;
                            case 1:
                                face[3][2] = true;
                                break;
                            case 2:
                                face[2][2] = true;
                                break;
                            case 3:
                                face[1][2] = true;
                                break;
                            case 4:
                                face[4][2] = true;
                                break;
                            case 5:
                                face[15][2] = true;
                                break;
                            case 6:
                                face[18][2] = true;
                                break;
                            case 7:
                                face[17][2] = true;
                                break;
                            case 8:
                                face[16][2] = true;
                                break;
                            case 9:
                                face[19][2] = true;
                                break;
                            case 10:
                                face[10][2] = true;
                                break;
                            case 11:
                                face[13][2] = true;
                                break;
                            case 12:
                                face[12][2] = true;
                                break;
                            case 13:
                                face[11][2] = true;
                                break;
                            case 14:
                                face[14][2] = true;
                                break;
                            case 15:
                                face[5][2] = true;
                                break;
                            case 16:
                                face[8][2] = true;
                                break;
                            case 17:
                                face[7][2] = true;
                                break;
                            case 18:
                                face[6][2] = true;
                                break;
                            case 19:
                                face[9][2] = true;
                                break;
                            case 20:
                                face[20][2] = true;
                                break;
                            case 21:
                                face[23][2] = true;
                                break;
                            case 22:
                                face[22][2] = true;
                                break;
                            case 23:
                                face[21][2] = true;
                                break;
                            case 24:
                                face[24][2] = true;
                                break;
                            case 25:
                                face[75][2] = true;
                                break;
                            case 26:
                                face[78][2] = true;
                                break;
                            case 27:
                                face[77][2] = true;
                                break;
                            case 28:
                                face[76][2] = true;
                                break;
                            case 29:
                                face[79][2] = true;
                                break;
                            case 30:
                                face[90][2] = true;
                                break;
                            //ignore for now cases 31, 32, and 33
                            case 34:
                                face[94][2] = true;
                                break;
                            case 35:
                                face[85][2] = true;
                                break;
                            //ignore for now cases 36, 37, and 38
                            case 39:
                                face[89][2] = true;
                                break;
                            case 40:
                                face[80][2] = true;
                                break;
                            //ignore for now cases 41, 42, and 43
                            case 44:
                                face[84][2] = true;
                                break;
                            case 45:
                                face[95][2] = true;
                                break;
                            case 46:
                                face[78][2] = true;
                                break;
                            case 47:
                                face[77][2] = true;
                                break;
                            case 48:
                                face[76][2] = true;
                                break;
                            case 49:
                                face[79][2] = true;
                                break;
                            case 50:
                                face[50][2] = true;
                                break;
                            case 51:
                                face[53][2] = true;
                                break;
                            case 52:
                                face[52][2] = true;
                                break;
                            case 53:
                                face[51][2] = true;
                                break;
                            case 54:
                                face[54][2] = true;
                                break;
                            case 55:
                                face[65][2] = true;
                                break;
                            //ignore for now cases 56, 57, and 58
                            case 59:
                                face[69][2] = true;
                                break;

                            case 60:
                                face[60][2] = true;
                                break;
                            //ignore for now case 61
                            case 62:
                                face[62][2] = true;
                                break;
                            //ignore for now case 63
                            case 64:
                                face[64][2] = true;
                                break;

                            case 65:
                                face[55][2] = true;
                                break;
                            //ignore for now cases 66, 67, and 68
                            case 69:
                                face[59][2] = true;
                                break;
                            case 70:
                                face[70][2] = true;
                                break;
                            case 71:
                                face[73][2] = true;
                                break;
                            case 72:
                                face[72][2] = true;
                                break;
                            case 73:
                                face[71][2] = true;
                                break;
                            case 74:
                                face[74][2] = true;
                                break;
                            case 75:
                                face[25][2] = true;
                                break;
                            case 76:
                                face[28][2] = true;
                                break;
                            case 77:
                                face[27][2] = true;
                                break;
                            case 78:
                                face[26][2] = true;
                                break;
                            case 79:
                                face[29][2] = true;
                                break;
                            case 80:
                                face[40][2] = true;
                                break;
                            //ignore for now cases 81, 82, and 83
                            case 84:
                                face[44][2] = true;
                                break;
                            case 85:
                                face[35][2] = true;
                                break;
                            //ignore for now cases 86, 87, and 88
                            case 89:
                                face[39][2] = true;
                                break;
                            case 90:
                                face[30][2] = true;
                                break;
                            //ignore for now cases 91, 92, and 93
                            case 94:
                                face[34][2] = true;
                                break;
                            case 95:
                                face[45][2] = true;
                                break;
                            case 96:
                                face[48][2] = true;
                                break;
                            case 97:
                                face[47][2] = true;
                                break;
                            case 98:
                                face[46][2] = true;
                                break;
                            case 99:
                                face[49][2] = true;
                                break;
                            case 100:
                                face[100][2] = true;
                                break;
                            case 101:
                                face[103][2] = true;
                                break;
                            case 102:
                                face[102][2] = true;
                                break;
                            case 103:
                                face[101][2] = true;
                                break;
                            case 104:
                                face[104][2] = true;
                                break;
                            case 105:
                                face[115][2] = true;
                                break;
                            case 106:
                                face[118][2] = true;
                                break;
                            case 107:
                                face[117][2] = true;
                                break;
                            case 108:
                                face[116][2] = true;
                                break;
                            case 109:
                                face[119][2] = true;
                                break;
                            case 110:
                                face[110][2] = true;
                                break;
                            case 111:
                                face[113][2] = true;
                                break;
                            case 112:
                                face[112][2] = true;
                                break;
                            case 113:
                                face[111][2] = true;
                                break;
                            case 114:
                                face[114][2] = true;
                                break;
                            case 115:
                                face[105][2] = true;
                                break;
                            case 116:
                                face[108][2] = true;
                                break;
                            case 117:
                                face[107][2] = true;
                                break;
                            case 118:
                                face[106][2] = true;
                                break;
                            case 119:
                                face[109][2] = true;
                                break;
                            case 120:
                                face[120][2] = true;
                                break;
                            case 121:
                                face[123][2] = true;
                                break;
                            case 122:
                                face[122][2] = true;
                                break;
                            case 123:
                                face[121][2] = true;
                                break;
                            case 124:
                                face[124][2] = true;
                                break;


                        }
                    }
                }
            } else {
                for (int i = 0; i < BASE3; i++) {
                    if (face[i][1] == true) {
                        face[i][1] = false;
                        face[(BASE3 - 1) - i][2] = true;
                    }
                }
            }
        }

    }
}
