import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Race extends JFrame{
    private static final MyButton[] buttons = new MyButton[6];
    private static final ButtonRunnable[] threads = new ButtonRunnable[6];

    public static MyButton getButton(int number){
        return buttons[number];
    }
    public Race(){
        JPanel panel = new JPanel();
        panel.setLayout(null);
        for (int i = 0; i < 6; i++){
            MyButton button = new MyButton("Button " + (i + 1));
            button.setColor(i);
            buttons[i] = button;
            panel.add(button);
        }


        JButton restart = new JButton("Restart");
        restart.setBounds(1500 / 2 - 25, 720, 200, 50);
        restart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae){
                for(ButtonRunnable thread: threads){
                    if (thread.isAlive())
                        thread.interrupt();
                }
                ButtonRunnable.setStopWork(false);
                start();
            }
        });

        panel.add(restart);

        getContentPane().add(panel);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);

    }

    public void start(){
        for(int i = 0; i < 6; i++)
            getButton(i).setBounds(40, 40 + i*110, 100, 100);

        for(int i = 0; i < 6; i++)
            threads[i] = new ButtonRunnable(getButton(i));

        for(int i = 0; i < 6; i++)
            threads[i].start();

    }


}
