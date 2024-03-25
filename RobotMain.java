import java.io.FileNotFoundException;

public class RobotMain {

    
    public static void main(String[] args) {
        RobotMain rbMain = new RobotMain();
        rbMain.runApp();

    }

    private void runApp() {
        Robot rob = new Robot();
        try {
            rob.readFile("test4.txt");
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    
}
