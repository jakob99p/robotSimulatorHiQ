import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Robot {
    private int x;
    private int y;
    private boolean placed;
    private Direction direction;
    // private int[][] tableTop;

    private Scanner input;

    public Robot() {
        placed = false;
        // tableTop = new int[5][5];
    }

    public boolean placedCorrectly(int x, int y) {
        return (0 <= x && x < 5) && (0 <= y && y < 5) ? true : false;
    }

    public enum Direction {
        NORTH, SOUTH, EAST, WEST
    }

    private enum Command {
        MOVE, REPORT, PLACE, LEFT, RIGHT
    }

    public void move() {
        if (placed) {
            int newX = x, newY = y;
            switch (direction) {
                case NORTH -> newY++;
                case EAST -> newX++;
                case SOUTH -> newY--;
                case WEST -> newX--;
            }
            if (placedCorrectly(newX, newY)) {
                x = newX;
                y = newY;
            }
        }
    }

    public void place(String direction) {
        String[] arg = direction.split(",");
        try {
            int nx = Integer.parseInt(arg[0]);
            int ny = Integer.parseInt(arg[1]);
            if (placedCorrectly(nx, ny)) {
                this.x = nx;
                this.y = ny;
                this.direction = Direction.valueOf(arg[2]);
                placed = true;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid coordinates");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid direction");
        }

    }

    public void turnLeft() {
        if (placed) {// Turn the robot to the left
            direction = switch (direction) {
                case NORTH -> Direction.WEST;
                case EAST -> Direction.NORTH;
                case SOUTH -> Direction.EAST;
                case WEST -> Direction.SOUTH;
            };
        }
    }

    public void turnRight() {
        if (placed) {
            direction = switch (direction) {
                case NORTH -> Direction.EAST;
                case EAST -> Direction.SOUTH;
                case SOUTH -> Direction.WEST;
                case WEST -> Direction.NORTH;
            };
        }
    }

    public void report() {
        if (placed) {
            System.out.println("X: " + x + ", Y: " + y + " , Direction: " + direction);
        } else {
            System.out.println("Not on table");
        }
    }

    public void reset() {
        placed = false;
    }

    public void readFile(String fileName) throws FileNotFoundException {

        File myObj = new File(fileName);
        input = new Scanner(myObj);
        while (input.hasNextLine()) {
            String line = input.nextLine();
            if (!line.isEmpty()) {
                String[] data = line.split(" ");
                switch (Command.valueOf(data[0])) {
                    case PLACE -> place(data[1]);
                    case MOVE -> move();
                    case REPORT -> report();
                    case LEFT -> turnLeft();
                    case RIGHT -> turnRight();
                }
            } else {
                reset();
            }
            // System.out.println(data[0]);
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getDirection() {
        return String.valueOf(direction);
    }

}
