import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

public class Race extends JFrame{
    private static final Race racebuttons = new Race();
    private static MyButton button1;
    private static MyButton button2;
    private static MyButton button3;
    private static MyButton button4;
    private static MyButton button5;
    private static MyButton button6;

    public MyButton ConfigButton(MyButton button, String name, int x, int y){
        button = new MyButton(name);
        button.setBounds(x, y,100,100);  // отвечает за размер кнопки и расположение
        return button;
    }
    public static MyButton getButton(int number){
        return switch (number) {
            case 1 -> button1;
            case 2 -> button2;
            case 3 -> button3;
            case 4 -> button4;
            case 5 -> button5;
            case 6 -> button6;
            default -> button1;
        };
    }
    public Race(){
        JPanel panel = new JPanel();
        panel.setLayout(null);

        button1 = ConfigButton(button1, "Button 1", 40, 40);
        button1.setColor(Color.BLACK);
        button2 = ConfigButton(button2, "Button 2", 40, 150);
        button2.setColor(Color.GREEN);
        button3 = ConfigButton(button3, "Button 3", 40, 260);
        button3.setColor(Color.RED);
        button4 = ConfigButton(button4, "Button 4", 40, 370);
        button4.setColor(Color.BLUE);
        button5 = ConfigButton(button5, "Button 5", 40, 480);
        button5.setColor(Color.MAGENTA);
        button6 = ConfigButton(button6, "Button 6", 40, 590);
        button6.setColor(Color.YELLOW);

        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);
        panel.add(button5);
        panel.add(button6);

        getContentPane().add(panel);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);

    }
    public static void showWinner(MyButton button){
        JDialog d = new JDialog(racebuttons, "Winner!");
        JPanel panel = new JPanel(new FlowLayout());
        JLabel l1 = new JLabel("The winner is " + button.getText() + "!");
        JLabel l2 = new JLabel("Congratulations!");
        panel.add(l1);
        panel.add(l2);

        JButton restart = new JButton("Restart");
        restart.setBounds(100, 300, 50, 20);
        restart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae){
                d.dispose();
                ButtonRunnable.setStopWork(false);
                start();
            }
        });
        panel.add(restart);
        d.add(panel);
        d.setSize(400, 400);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - racebuttons.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - racebuttons.getHeight()) / 2);
        d.setLocation(x, y);

        //d.getContentPane().setBackground(button.getBackground());
        d.getContentPane().setBackground( Color.YELLOW);

        d.setVisible(true);
    }
    public static void start(){
        button1.setBounds(40, 40, 100, 100);
        button2.setBounds(40, 150, 100, 100);
        button3.setBounds(40, 260, 100, 100);
        button4.setBounds(40, 370, 100, 100);
        button5.setBounds(40, 480, 100, 100);
        button6.setBounds(40, 590, 100, 100);

        ButtonRunnable thread1 = new ButtonRunnable(button1);
        ButtonRunnable thread2 = new ButtonRunnable(button2);
        ButtonRunnable thread3 = new ButtonRunnable(button3);
        ButtonRunnable thread4 = new ButtonRunnable(button4);
        ButtonRunnable thread5 = new ButtonRunnable(button5);
        ButtonRunnable thread6 = new ButtonRunnable(button6);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();

        while (!ButtonRunnable.getStopWork()){
            try {
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException e){
                System.out.println(e.getMessage());
            }
        }

        showWinner(ButtonRunnable.getWinner());
    }

    public static void main(String...args){
        start();

        while (!ButtonRunnable.getStopWork()){
            try {
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException e){
                System.out.println(e.getMessage());
            }
        }

        showWinner(ButtonRunnable.getWinner());


    }
}
