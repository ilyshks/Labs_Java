import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class MyButton extends JButton {
    private final Color[] colors = new Color[]{Color.BLACK, Color.GREEN, Color.BLUE, Color.RED, Color.YELLOW, Color.MAGENTA};
    private Color UsedColor = Color.BLACK;
    MyButton(String label){
        super(label);
        setForeground(Color.WHITE);
        Dimension size = getPreferredSize();
        size.width = size.height = Math.max(size.width, size.height);
        setPreferredSize(size);
        setContentAreaFilled(false);

    }
    public void setColor(int indx){
        UsedColor = colors[indx];
    }
    public void makeMoving(int speed){
        setBounds(getX() + speed, getY(), getWidth(), getHeight());
    }

    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(Color.lightGray);
        } else {
            //g.setColor(getBackground());
            g.setColor(UsedColor);
        }
        g.fillOval(0, 0, getSize().width-1, getSize().height-1);
        super.paintComponent(g);
    }
    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        g.drawOval(0, 0, getSize().width-1, getSize().height-1);
    }
    Shape shape;
    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
        }
        return shape.contains(x, y);
    }
}
