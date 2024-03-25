
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import org.junit.*;

public class RobotTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream standardOut = System.out;

    @Before
    public void setup() {
        System.setOut(new PrintStream(outputStream));
    }

    @After
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void multipleRuns() {
        Robot rob = new Robot();
        try {
            rob.readFile("test.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Wrong file");
            e.printStackTrace();
        }
        Assert.assertEquals("X: 0, Y: 1 , Direction: NORTH\n" + //
                "X: 0, Y: 0 , Direction: WEST\n" + //
                "X: 3, Y: 3 , Direction: NORTH\n" + //
                "Not on table", outputStream.toString().trim());
    }

    @Test
    public void testDoesNotFallOver() {
        Robot rob = new Robot();
        try {
            rob.readFile("test1.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Wrong file");
            e.printStackTrace();
        }

        Assert.assertEquals("X: 0, Y: 2 , Direction: WEST", outputStream.toString().trim());
    }

    @Test
    public void notPlaced() {
        Robot rob = new Robot();
        try {
            rob.readFile("test2.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Wrong file");
            e.printStackTrace();
        }

        Assert.assertEquals("Not on table", outputStream.toString().trim());
    }

    @Test
    public void movingOverTheBoard() {
        Robot rob = new Robot();
        try {
            rob.readFile("test4.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Wrong file");
            e.printStackTrace();
        }

        Assert.assertEquals("X: 4, Y: 4 , Direction: NORTH", outputStream.toString().trim());
    }

    @Test
    public void placeOutsideBoard() {
        Robot rob = new Robot();
        try {
            rob.readFile("test5.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Wrong file");
            e.printStackTrace();
        }

        Assert.assertEquals("Not on table", outputStream.toString().trim());
    }

    @Test
    public void directionTest() {
        Robot rob = new Robot();
        String str = "4,3,EAST";
        rob.place(str);
        rob.turnLeft();
        Assert.assertEquals("NORTH", rob.getDirection());
    }

    @Test
    public void turningTest() {
        Robot rob = new Robot();
        String str = "2,3,EAST";
        rob.place(str);
        rob.turnLeft();
        Assert.assertEquals("NORTH", rob.getDirection());
        rob.turnRight();
        Assert.assertEquals("EAST", rob.getDirection());
    }

    @Test
    public void cornerTestBottomLeft() {
        Robot rob = new Robot();
        String str = "0,0,WEST";
        rob.place(str);
        rob.move();
        rob.move();
        rob.report();
        Assert.assertEquals("X: 0, Y: 0 , Direction: WEST", outputStream.toString().trim());
    }

    @Test
    public void cornerTestTopRight() {
        Robot rob = new Robot();
        String str = "4,4,EAST";
        rob.place(str);
        rob.move();
        rob.move();
        rob.report();
        Assert.assertEquals("X: 4, Y: 4 , Direction: EAST", outputStream.toString().trim());
    }

    @Test
    public void invalidCoordinateInputs() {
        Robot rob = new Robot();
        String str = "invalid,1,NORTH";
        rob.place(str);
        rob.move();
        //rob.report();
        Assert.assertEquals("Invalid coordinates", outputStream.toString().trim());
    }

    @Test
    public void invalidDirectionInput(){
        Robot rob = new Robot();
        String str = "1,1,UP";
        rob.place(str);
        rob.move();
        Assert.assertEquals("Invalid direction", outputStream.toString().trim());
    }
}
