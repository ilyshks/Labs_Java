import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class ButtonRunnable extends Thread {
    private final MyButton button;
    private static boolean stopWork = false;

    private static final int DELAY = 1000;
    public ButtonRunnable(MyButton b) {
        button = b;
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
                if (stopWork) return;
                sleep(DELAY);
                button.makeMoving(speed); //вызов метода перемещения кнопки
            }
            if (!stopWork){
                stopWork = true;
                JOptionPane.showConfirmDialog(null, "The winner is " + button.getText() + "!", "Winner!", JOptionPane.DEFAULT_OPTION);
            }
        }
        catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

    }
}