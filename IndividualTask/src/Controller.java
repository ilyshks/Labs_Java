import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

public class Controller {
    private static Data model;
    private static DataView view;

    public Controller(Data m, DataView v) {
        model = m;
        view = v;
    }

    public void createFrame(){
        JFrame f = new JFrame("Конструктор молекулы");
        f.setSize(600,600);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        f.setJMenuBar(menuBar);
        model.setFrame(f);
        menuBar.setBounds(0,0,350,30);
        JMenu fileMenu = new JMenu("Файл");
        menuBar.add(fileMenu);

        Action loadAction = new AbstractAction("Загрузить")
        {
            public void actionPerformed(ActionEvent event)
            {
                JFileChooser jf = new JFileChooser();
                int  result = jf.showOpenDialog(null);
                if(result==JFileChooser.APPROVE_OPTION)
                {
                    try
                    {
                        // при выборе изображения подстраиваем размеры формы
                        // и панели под размеры данного изображения
                        model.setFileName(jf.getSelectedFile().getAbsolutePath());
                        File iF= new File(model.getFileName());
                        jf.addChoosableFileFilter(new TextFileFilter(".png"));
                        jf.addChoosableFileFilter(new TextFileFilter(".jpg"));
                        model.setImage(ImageIO.read(iF));
                        BufferedImage imag = model.getImage();
                        model.setLoading(true);
                        model.getFrame().setSize(imag.getWidth()+40, imag.getWidth()+80);
                        model.getJpanel().setSize(imag.getWidth(), imag.getWidth());
                        model.getJpanel().repaint();
                    } catch (FileNotFoundException ex) {
                        view.showMessageDialog(model.getFrame(), "Такого файла не существует");
                    }
                    catch (IOException ex) {
                        view.showMessageDialog(model.getFrame(), "Исключение ввода-вывода");
                    }
                    catch (Exception ex) {
                        view.println("Ошибка!");
                    }
                }
            }
        };
        JMenuItem loadMenu = new JMenuItem(loadAction);
        fileMenu.add(loadMenu);

        Action saveAction = new AbstractAction("Сохранить")
        {
            public void actionPerformed(ActionEvent event)
            {
                try
                {
                    JFileChooser jf = new JFileChooser();
                    // Создаем фильтры  файлов
                    TextFileFilter pngFilter = new TextFileFilter(".png");
                    TextFileFilter jpgFilter = new TextFileFilter(".jpg");
                    if(model.getFileName()==null)
                    {
                        // Добавляем фильтры
                        jf.addChoosableFileFilter(pngFilter);
                        jf.addChoosableFileFilter(jpgFilter);
                        int  result = jf.showSaveDialog(null);
                        if(result==JFileChooser.APPROVE_OPTION)
                        {
                            model.setFileName(jf.getSelectedFile().getAbsolutePath());
                        }
                    }
                    // Смотрим какой фильтр выбран
                    if(jf.getFileFilter() == pngFilter)
                    {
                        view.writeImage(model.getImage(), "png", new File(model.getFileName()+".png"));
                    }
                    else
                    {
                        view.writeImage(model.getImage(), "jpeg", new File(model.getFileName()+".jpg"));
                    }
                }
                catch(IOException ex)
                {
                    view.showMessageDialog(f, "Ошибка ввода-вывода");
                }
            }
        };
        JMenuItem saveMenu = new JMenuItem(saveAction);
        fileMenu.add(saveMenu);

        Action saveasAction = new AbstractAction("Сохранить как...")
        {
            public void actionPerformed(ActionEvent event)
            {
                try
                {
                    JFileChooser jf= new JFileChooser();
                    // Создаем фильтры для файлов
                    TextFileFilter pngFilter = new TextFileFilter(".png");
                    TextFileFilter jpgFilter = new TextFileFilter(".jpg");
                    // Добавляем фильтры
                    jf.addChoosableFileFilter(pngFilter);
                    jf.addChoosableFileFilter(jpgFilter);
                    int  result = jf.showSaveDialog(null);
                    if(result==JFileChooser.APPROVE_OPTION)
                    {
                        model.setFileName(jf.getSelectedFile().getAbsolutePath());
                    }
                    // Смотрим какой фильтр выбран
                    if(jf.getFileFilter()==pngFilter)
                    {
                        view.writeImage(model.getImage(), "png", new File(model.getFileName()+".png"));
                    }
                    else
                    {
                        view.writeImage(model.getImage(), "jpeg", new File(model.getFileName()+".jpg"));
                    }
                }
                catch(IOException ex)
                {
                    view.showMessageDialog(f, "Ошибка ввода-вывода");
                }
            }
        };
        JMenuItem saveasMenu = new JMenuItem(saveasAction);
        fileMenu.add(saveasMenu);

        Data.MyPanel jpanel = new Data.MyPanel();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        jpanel.setBounds(160,30,(int)width-160,(int)height-30);
        jpanel.setBackground(Color.white);
        jpanel.setOpaque(true);
        f.add(jpanel);
        model.setJpanel(jpanel);

        JToolBar toolbar = new JToolBar("Toolbar", JToolBar.VERTICAL);
        //toolbar.setPreferredSize(new Dimension(120, 840));


        JButton eraser = new JButton();
        eraser.setPreferredSize(new Dimension(140, 90));
        eraser.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource("/images/eraser.png"))));
        eraser.addActionListener(new  ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                model.setType(0);
            }
        });
        toolbar.add(eraser);


        JButton lineButton = new JButton();
        lineButton.setPreferredSize(new Dimension(140, 90));
        lineButton.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource("/images/line.png"))));
        lineButton.addActionListener(new  ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                model.setType(1);
            }
        });
        toolbar.add(lineButton);

        JButton hydrogen = makeButton(2, "/images/H.png", Color.CYAN);
        toolbar.add(hydrogen);

        JButton nitrogen = makeButton(3, "/images/N.png", Color.PINK);
        toolbar.add(nitrogen);

        JButton carbon = makeButton(4, "/images/C.png", Color.ORANGE);
        toolbar.add(carbon);

        JButton oxygen = makeButton(5, "/images/O.png", Color.BLUE);
        toolbar.add(oxygen);

        JButton radical = makeRadical(6, "/images/R.png");
        toolbar.add(radical);

        toolbar.setBounds(0, 0, 30, 300);
        toolbar.setSize(148, 900);

        f.add(toolbar);

        jpanel.addMouseMotionListener(new MouseMotionAdapter()
        {
            public void mouseDragged(MouseEvent e)
            {
                if (model.isPressed())
                {
                    Graphics g = model.getImage().getGraphics();
                    Graphics2D g2 = (Graphics2D)g;
                    // установка цвета
                    g2.setColor(Color.BLACK);
                    // ластик
                    if (model.getType() == 0) {
                        g2.setStroke(new BasicStroke(10));
                        g2.setColor(Color.WHITE);
                        g2.drawLine(model.getxPrev(), model.getyPrev(), e.getX(), e.getY());
                    }
                    model.setxPrev(e.getX());
                    model.setyPrev(e.getY());
                }
                jpanel.repaint();
            }
        });

        jpanel.addMouseListener(new MouseAdapter()
        {

            public void mousePressed(MouseEvent e) {
                model.setX0(e.getX());
                model.setY0(e.getY());
                model.setxPrev(e.getX());
                model.setyPrev(e.getY());
                model.setPressed(true);
            }
            public void mouseReleased(MouseEvent e) {

                Graphics g = model.getImage().getGraphics();
                Graphics2D g2 = (Graphics2D)g;
                // установка цвета
                g2.setColor(Color.BLACK);
                switch (model.getType()) {
                    // ластик
                    case 0 -> {
                        g2.setStroke(new BasicStroke(10));
                        g2.setColor(Color.WHITE);
                        g2.drawLine(model.getX0(), model.getY0(), e.getX(), e.getY());
                    }
                    // кисть
                    case 1 -> {
                        g2.setStroke(new BasicStroke(10));
                        g2.drawLine(model.getX0(), model.getY0(), e.getX(), e.getY());
                    }
                }

                model.setX0(0);
                model.setY0(0);
                model.setPressed(false);
                jpanel.repaint();
            }
        });

        f.addComponentListener(new ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                // если делаем загрузку, то изменение размеров формы
                if(!model.isLoading())
                {
                    jpanel.setSize(f.getWidth()-40, f.getHeight()-80);
                    BufferedImage tempImage = new BufferedImage(jpanel.getWidth(), jpanel.getHeight(), BufferedImage.TYPE_INT_RGB);
                    Graphics2D d2 = (Graphics2D) tempImage.createGraphics();
                    d2.setColor(Color.white);
                    view.fillRect(d2, 0, 0, jpanel.getWidth(), jpanel.getHeight());
                    tempImage.setData(model.getImage().getRaster());
                    model.setImage(tempImage);
                    jpanel.repaint();
                }
                model.setLoading(false);
            }
        });
        f.setLayout(null);
        f.setVisible(true);
    }
    private JButton makeRadical(int t, String path) {
        JButton button = new JButton(path);
        button.setPreferredSize(new Dimension(140, 90));
        button.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource(path))));
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                model.setType(t);
            }
        });

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                Graphics g = model.getImage().getGraphics();
                try {
                    BufferedImage myPicture = model.readImage(new File("D:\\Java\\IndividualTask\\src\\images\\R2.png"));
                    view.drawImage(g, myPicture, button.getX() + e.getX() - 148 - 61, button.getY() + e.getY() - 30 - 46, model.getJpanel());
                    model.getJpanel().repaint();
                } catch (IOException ex) {
                    view.println("Не загржена картинка R");
                }
            }
        }

        );

        return button;
    }

    private JButton makeButton(int t, String path, Color color){
        JButton button = new JButton(path);
        button.setPreferredSize(new Dimension(140, 90));
        button.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource(path))));
        button.addActionListener(new  ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                model.setType(t);
            }
        });

        button.addMouseListener(new MouseAdapter()
            {
                @Override
                public void mouseReleased(MouseEvent e) {

                    Graphics g = model.getImage().getGraphics();
                    Graphics2D g2 = (Graphics2D)g;
                    // установка цвета
                    g2.setColor(color);
                    if (model.getType() == 2){
                        view.fillOval(g, button.getX() + e.getX() - 148 - 30,button.getY() + e.getY()-30 - 30, 60, 60);
                    }else {
                        view.fillOval(g, button.getX() + e.getX() - 148 - 40, button.getY() + e.getY() - 30 - 40, 80, 80);
                    }
                    model.getJpanel().repaint();
                }
            }

        );

        return button;
    }

    public void println(String message) {
        view.println(message);
    }
}
