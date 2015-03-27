/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @author Flopp_000
 */
public class PushSpace {
    
    int BASE;
    int BASE2;
    int BASE3;
    boolean[][] face;
    
    public PushSpace(int b){
        BASE = b;
        BASE2 = b*b;
        BASE3 = b*b*b;
        
        face = new boolean[BASE3][2];
        java.util.Arrays.fill(face, false);
    }
    
    public void passthru(){
        for(int i = 0; i < face.length; i++){
            if(this.face[i][1] == true){
                this.face[i][1] = false;
                this.face[(BASE3 - 1) - i][0] = true;
            }
        }
    }
}
