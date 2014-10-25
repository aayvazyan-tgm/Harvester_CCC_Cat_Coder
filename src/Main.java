import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println(work(3,4));
        System.out.println(work(2,5));
        System.out.println(work(5,2));
        System.out.println(work(23,12));
    }
    public static String work(int x, int y){
        String result="";
        boolean back=false;
        int cur=0;
        for (int i = 0; i < x; i++) {
            if(back==false) {
                for (int j = 0; j < y; j++) {
                    cur++;
                    result+=cur+" ";
                }
                back=true;
            }else {
                cur+=y+1;
                for (int j = y; j > 0; j--) {
                    cur--;
                    result+=cur+" ";
                }
                back=false;
                cur+=y-1;
            }

        }
        result=result.substring(0,result.length()-1);
        return result;
    }
    public static ArrayList<Integer> readIntInput(String filePath){
        File f = new File(filePath);
        ArrayList<Integer> result=new ArrayList<Integer>();
        if (f.exists()) {
            Scanner scanner = null;
            try {
                scanner = new Scanner(f);
                while (scanner.hasNextInt()) {
                    result.add(scanner.nextInt());
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            scanner.close();
        } else {
            System.out.println("File not found!");
        }
        return result;
    }
}
