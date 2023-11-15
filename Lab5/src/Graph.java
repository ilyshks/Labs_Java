import java.awt.*;
import javax.swing.*;
import java.awt.geom.*;
public class Graph extends JPanel{
    private final int number;
    private final String[] LABELS = new String[] {"ArrayList", "LinkedList", "HashMap"};
    private final Color[] COLORS = new Color[] {Color.BLUE, Color.GREEN, Color.ORANGE};
    private final int[] addTotalTimeArrayList = new int[]{0, 8200, 33900, 173700, 514800, 3388000};
    private final int[] addTotalTimeLinkedList = new int[]{0, 9000, 59400, 271600, 513100, 3963600};
    private final int[] addTotalTimeHashMap = new int[]{0, 15700, 55500, 278400, 952900, 8089700};

    private final int[] removeTotalTimeArrayList = new int[]{0, 2500, 8600, 47700, 945796, 43817601};
    private final int[] removeTotalTimeLinkedList = new int[]{0, 5100, 17100, 292995, 23697702, 603042477};
    private final int[] removeTotalTimeHashMap = new int[]{0, 3200, 11800, 45904, 539201, 2870820};
    int[] coordinates={0, 100, 200, 300, 400, 500};

    int x_offset = 50;
    int y_offset = 50;

    public Graph(int num){
        number = num;
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g1=(Graphics2D)g;
        g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

        // возвращаем размеры окна
        int width=getWidth();
        int height=getHeight();

        // рисуем оси координат относительно верхнего левого угла
        g1.draw(new Line2D.Double(x_offset, y_offset, x_offset,height-y_offset));
        g1.draw(new Line2D.Double(x_offset,height-y_offset,width-x_offset,height-y_offset));

        // расставляем точки на оси абсцисс и подпишем значения
        g1.setPaint(Color.BLACK);
        g1.drawString("0", x_offset, height - y_offset + 20);
        g1.fill(new Ellipse2D.Double(x_offset - 2, height - y_offset - 2, 4, 4));
        for (int i=1; i < coordinates.length; i++) {
            double x = x_offset + coordinates[i];
            double y = height - y_offset;
            g1.drawString(Integer.toString((int)Math.pow(10, i)), (int)x-5, (int)y+20);
            g1.fill(new Ellipse2D.Double(x - 2, y - 2, 4, 4));
        }
        // обозначения осей
        g1.drawString("Количество", width - x_offset*2, height - y_offset - 30);
        g1.drawString("элементов, шт", width - x_offset*2, height - y_offset - 10);

        g1.drawString("Время, нс", x_offset, y_offset - 10);

        // рисуем график
        if (number <= 2) drawGraph(addTotalTimeArrayList, addTotalTimeLinkedList, addTotalTimeHashMap, g1);
        if (number > 2) drawGraph(removeTotalTimeArrayList, removeTotalTimeLinkedList, removeTotalTimeHashMap, g1);

        // создаём легенду
        for (int i = 0; i < LABELS.length; i++) {
            g1.setPaint(COLORS[i]);
            g1.drawString(LABELS[i], width - x_offset*2, y_offset + i*20);
        }
    }
    private void drawPoints(int[] x_coords, int[] y_coords, Graphics2D graphics2D){
        int width=getWidth();
        int height=getHeight();

        int k = 1;
        if (number % 2 == 0) k = 10;

        for(int i=1;i<x_coords.length;i++){
            double x = x_offset + x_coords[i];
            double y = height - y_offset - Math.log10((int)(y_coords[i] / k))*50;
            graphics2D.fill(new Ellipse2D.Double(x-2,y-2,4,4));
        }
    }
    private void drawLines(int[] x_coords, int[] y_coords, Graphics2D graphics2D){
        int width=getWidth();
        int height=getHeight();

        int k = 1;
        if (number % 2 == 0) k = 10;

        for(int i=1;i<x_coords.length;i++){
            double x0 = x_offset + x_coords[i-1];
            double y0 = height - y_offset - Math.log10((int)(y_coords[i-1] / k))*50;
            double x1 = x_offset + x_coords[i];
            double y1 = height - y_offset - Math.log10((int)(y_coords[i] / k))*50;
            graphics2D.fill(new Ellipse2D.Double(x1-2,y1-2,4,4));
            graphics2D.drawLine((int)x0, (int)y0, (int)x1, (int)y1);
        }
    }
    private void drawGraph(int[] AList, int[] LList, int[] HMap, Graphics2D g1){
        int width=getWidth();
        int height=getHeight();
        // расставим точки на оси ординат
        int k = 1;
        if (number % 2 == 0) k = 10;
        int y1 = (int)(height - y_offset - Math.log10((int)(AList[1] / k))*50);
        g1.drawString(Integer.toString(AList[1] / k), x_offset - 20, y1 - 10);
        g1.fill(new Ellipse2D.Double(x_offset - 2, y1 - 2, 4, 4));

        int y2 = (int)(height - y_offset - Math.log10((int)(HMap[3] / k))*50);
        g1.drawString(Integer.toString(HMap[3] / k), x_offset - 20, y2 - 10);
        g1.fill(new Ellipse2D.Double(x_offset - 2, y2 - 2, 4, 4));

        int y3 = (int)(height - y_offset - Math.log10((int)(HMap[5] / k))*50);
        g1.drawString(Integer.toString(HMap[5] / k), x_offset - 20, y3 - 10);
        g1.fill(new Ellipse2D.Double(x_offset - 2, y3 - 2, 4, 4));

        if (number > 2) {
            int y4 = (int) (height - y_offset - Math.log10((int) (LList[5] / k)) * 50);
            g1.drawString(Integer.toString(LList[5] / k), x_offset - 20, y4 - 10);
            g1.fill(new Ellipse2D.Double(x_offset - 2, y4 - 2, 4, 4));
        }

        // график для ArrayList
        g1.setPaint(Color.BLUE);
        drawPoints(coordinates, AList, g1);
        drawLines(coordinates, AList, g1);

        // график для LinkedList
        g1.setPaint(Color.GREEN);
        drawPoints(coordinates, LList, g1);
        drawLines(coordinates, LList, g1);

        // график для HashMap
        g1.setPaint(Color.ORANGE);
        drawPoints(coordinates, HMap, g1);
        drawLines(coordinates, HMap, g1);
    }
    private static void createAndShowGui(String title, int x, int y, int num){
        JFrame frame = new JFrame();
        frame.setTitle(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Graph panel = new Graph(num);
        frame.getContentPane().add(panel);
        frame.setSize(700,600);
        frame.setLocation(x,y);
        frame.setVisible(true);
    }

    public static void main(String[] args){
        createAndShowGui("AddTotalTime", 100, 100, 1);
        createAndShowGui("AddMedianTime", 700, 100, 2);
        createAndShowGui("RemoveTotalTime", 100, 700, 3);
        createAndShowGui("RemoveMedianTime", 700, 700, 4);
    }
}

