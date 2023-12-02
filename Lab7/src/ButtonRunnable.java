import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class ButtonRunnable extends Thread {
    private final MyButton button;
    private static boolean stopWork = false;
    private static MyButton winner;
    private static final int DELAY = 1000;
    public ButtonRunnable(MyButton b) {
        button = b;
    }
    public static boolean getStopWork(){
        return stopWork;
    }

    public static MyButton getWinner(){
        return winner;
    }

    public static void setStopWork(boolean flag) {
        stopWork = flag;
    }

    public void run() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double screenWidth = screenSize.getWidth();
        int speed = new Random().nextInt(10, 100);
        try {
            while (!stopWork && (button.getX() < screenWidth - button.getWidth())) {
                if (stopWork) interrupt();
                sleep(DELAY);
                button.makeMoving(speed); //вызов метода перемещения кнопки
            }
            if (!stopWork){
                stopWork = true;
                winner = button;
            }
            interrupt();
        }
        catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

    }
}