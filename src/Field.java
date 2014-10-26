/**
 * Created by Ari Ayvazyan on 26.10.2014.
 */
public class Field {
    int[][] spielfeld;
    int farmerStartPositionNumber=-1;
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
        this.farmerStartPositionNumber=getNumber();
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

    public void turnField() {
        spielfeld=rotateCW(spielfeld);
        this.farmerPosition=this.findNumber(farmerStartPositionNumber);
    }
    public Point findNumber(int number){
        for (int i = 0; i < spielfeld.length; i++) {
            for (int j = 0; j < spielfeld[0].length; j++) {
                if(spielfeld[i][j]==number)
                    return new Point(i,j);
            }
        }
        return null;
    }
    static int[][] rotateCW(int[][] mat) {
        final int M = mat.length;
        final int N = mat[0].length;
        int[][] ret = new int[N][M];
        for (int r = 0; r < M; r++) {
            for (int c = 0; c < N; c++) {
                ret[c][M-1-r] = mat[r][c];
            }
        }
        return ret;
    }
}
