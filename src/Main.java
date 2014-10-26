import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public enum StartPoint {
        TopLeft, TopRight, BottomLeft, BottomRight
    }

    public static void main(String[] args) {
        System.out.println(work(3, 4, 1, 1));
        System.out.println(work(2, 5, 2, 1));
        System.out.println(work(5, 2, 5, 2));
        System.out.println(work(23, 12, 1, 12));
    }

    public static String work(int x, int y, int f1, int f2) {
        String result = "";
        Field field = new Field(x, y, f1, f2);
        StartPoint startPoint = getStartPoint(x, y, f1, f2);
        result = workHard(field, startPoint);
        return result;
    }

    private static String workHard(Field field, StartPoint startPoint) {
        String result = "";
        boolean toTheLeft = false;
        //if(startPoint==StartPoint.BottomLeft||startPoint==StartPoint.TopLeft)toTheLeft=false;
        if (startPoint == StartPoint.BottomRight || startPoint == StartPoint.TopRight) toTheLeft = true;
        boolean up = false;
        if (startPoint == StartPoint.BottomRight || startPoint == StartPoint.BottomLeft) up = true;

        boolean finished = false;
        result += field.getNumber() + " ";
        int doneRows = 0;
        int lastNumber=-1;
        while (doneRows < field.getHeight()) {
            if (toTheLeft) {
                if (!field.left()) {
                    toTheLeft = false;
                    doneRows++;
                    if (up) {
                        if (!field.up());
                    } else {
                        if (!field.down());
                    }
                }
            } else {
                if (!field.right()) {
                    toTheLeft = true;
                    doneRows++;
                    if (up) {
                        if (!field.up());
                    } else {
                        if (!field.down());
                    }
                }
            }
            if(lastNumber!=field.getNumber()) {//Avoid the last duplicate number
                lastNumber = field.getNumber();
                result += field.getNumber() + " ";
            }
        }
        result = result.substring(0, result.length()-1);//remove last blank
        return result;
    }


    public static StartPoint getStartPoint(int x, int y, int f1, int f2) {
        if (x == f1 && 1 == f2) return StartPoint.BottomLeft;
        if (x == f1 && y == f2) return StartPoint.BottomRight;
        if (1 == f1 && 1 == f2) return StartPoint.TopLeft;
        if (1 == f1 && y == f2) return StartPoint.TopRight;
        return null;
    }

    public static ArrayList<Integer> readIntInput(String filePath) {
        File f = new File(filePath);
        ArrayList<Integer> result = new ArrayList<Integer>();
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
