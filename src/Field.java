import java.awt.*;

/**
 * Created by Ari on 26.10.2014.
 */
public class Field {
    int[][] spielfeld;
    Point farmerPosition;
    public Field(int x, int y, int farmX, int farmY){
        this.spielfeld=new int[x][y];
        int curn=0;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                curn++;
                this.spielfeld[i][j]=curn;
            }
        }
        this.farmerPosition=new Point(farmX-1,farmY-1);
    }
    public boolean isUpFree(){
        if(farmerPosition.x-1>=0){
            return true;
        }else {
            return false;
        }
    }
    public boolean isLeftFree(){
        if(farmerPosition.y-1>=0){
            return true;
        }else {
            return false;
        }
    }
    public boolean isDownFree(){
        if(farmerPosition.x+1<=spielfeld.length-1){
            return true;
        }else {
            return false;
        }
    }
    public boolean isRightFree(){
        if(farmerPosition.y+1<=spielfeld[0].length-1){
            return true;
        }else {
            return false;
        }
    }
    public boolean up(){
        if(isUpFree()){
            farmerPosition.x=farmerPosition.x-1;
            return true;
        }else {
            return false;
        }
    }
    public boolean left(){
        if(isLeftFree()){
            farmerPosition.y=farmerPosition.y-1;
            return true;
        }else {
            return false;
        }
    }
    public boolean down(){
        if(isDownFree()){
            farmerPosition.x=farmerPosition.x+1;
            return true;
        }else {
            return false;
        }
    }
    public boolean right(){
        if(isRightFree()){
            farmerPosition.y=farmerPosition.y+1;
            return true;
        }else {
            return false;
        }
    }
    public int getNumber(){
        return spielfeld[farmerPosition.x][farmerPosition.y];
    }

    public int getHeight() {
        return this.spielfeld.length;
    }
    public int getWidth() {
        return this.spielfeld[0].length;
    }
}
