import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Ari Ayvazyan on 26.10.2014.
 */
public class Main {
    public enum StartPoint {
        TopLeft, TopRight, BottomLeft, BottomRight
    }

    public enum Direction {
        North, East, South, West
    }

    public enum Workflow {
        Circular, Serpentine
    }

    public static void main(String[] args) {
        Long before = System.nanoTime();
        System.out.println(work(3, 4, 1, 4, Direction.South, Workflow.Circular));
        System.out.println(work(2, 5, 2, 1, Direction.North, Workflow.Serpentine));
        System.out.println(work(5, 2, 5, 2, Direction.North, Workflow.Circular));
        System.out.println(work(23, 12, 23, 1, Direction.North, Workflow.Circular));
        System.out.println((System.nanoTime() - before) / 100000d);
    }

    public static String work(int x, int y, int f1, int f2, Direction direction, Workflow workflow) {
        String result = "";
        Field field = new Field(x, y, f1, f2);
        //we need to swap x and y if the farmer wants to plow the field in a vertical way
        if (direction == Direction.South || direction == Direction.North) {
            field.turnField();
        }
        StartPoint startPoint = getStartPoint(field.getHeight(), field.getWidth(), field.farmerPosition.x + 1, field.farmerPosition.y + 1);
        result = workHard(field, startPoint, workflow);
        return result;
    }

    private static String workHard(Field field, StartPoint startPoint, Workflow workflow) {
        String result = "";
        boolean toTheLeft = false;
        if (startPoint == StartPoint.BottomRight || startPoint == StartPoint.TopRight) toTheLeft = true;
        boolean up = false;
        if (startPoint == StartPoint.BottomRight || startPoint == StartPoint.BottomLeft) up = true;

        result += field.getNumber() + " ";
        int doneRows = 0;
        int lastNumber = -1;
        while (doneRows < field.getHeight()) {
            if (toTheLeft) {
                if (!field.left()) {
                    doneRows++;
                    toTheLeft = false;
                    if (up) {
                        if (workflow == Workflow.Circular) {
                            int goToRow = field.farmerPosition.x+(doneRows - field.getHeight());
                            field.farmerPosition = new Point(goToRow, 0);
                            up=false;
                        }else {
                            if (!field.up()) ;
                        }
                    } else {
                        if (workflow == Workflow.Circular) {
                            int goToRow = field.farmerPosition.x+(field.getHeight() - doneRows);
                            field.farmerPosition = new Point(goToRow, field.getWidth() - 1);
                            up=true;
                        }else {
                            if (!field.down()) ;
                        }
                    }
                }
            } else {
                if (!field.right()) {
                    doneRows++;
                    toTheLeft = true;
                    if (up) {
                        if (workflow == Workflow.Circular) {
                            int goToRow = field.farmerPosition.x+(doneRows - field.getHeight());
                            field.farmerPosition = new Point(goToRow, 0);
                            up=false;
                        }else {
                            if (!field.up()) ;
                        }
                    } else {
                        if (workflow == Workflow.Circular) {
                            int goToRow = field.farmerPosition.x+(field.getHeight() - doneRows);
                            field.farmerPosition = new Point(goToRow, field.getWidth() - 1);
                            up=true;
                        }else {
                            if (!field.down()) ;
                        }
                    }
                }
            }
            if (lastNumber != field.getNumber()) {//Avoid the last duplicate number
                lastNumber = field.getNumber();
                result += field.getNumber() + " ";
            }
        }

        result = result.substring(0, result.length() - 1);//remove last blank
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
