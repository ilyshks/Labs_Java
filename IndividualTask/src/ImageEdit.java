//import  java.awt.*;
//import  java.awt.geom.*;
//import  java.awt.event.*;
//import  java.io.*;
//import  javax.swing.*;
//import javax.swing.border.Border;
//import  javax.swing.event.*;
//import  java.awt.image.*;
//import java.util.Objects;
//import  javax.imageio.*;
//import  javax.swing.filechooser.FileFilter;
//
//public class ImageEdit
//{
//    int type = 0;
//    int x0;
//    int y0;
//
//    int xPrev;
//    int yPrev;
//    boolean pressed;
//    JFrame f;
//    MyPanel jpanel;
//    BufferedImage imag;
//    // если мы загружаем картинку
//    boolean loading=false;
//    String fileName;
//    public ImageEdit()
//    {
//        f = new JFrame("Конструктор молекулы");
//        f.setSize(600,600);
//        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        JMenuBar menuBar = new JMenuBar();
//        f.setJMenuBar(menuBar);
//        menuBar.setBounds(0,0,350,30);
//        JMenu fileMenu = new JMenu("Файл");
//        menuBar.add(fileMenu);
//
//        Action loadAction = new AbstractAction("Загрузить")
//        {
//            public void actionPerformed(ActionEvent event)
//            {
//                JFileChooser jf = new JFileChooser();
//                int  result = jf.showOpenDialog(null);
//                if(result==JFileChooser.APPROVE_OPTION)
//                {
//                    try
//                    {
//                        // при выборе изображения подстраиваем размеры формы
//                        // и панели под размеры данного изображения
//                        fileName = jf.getSelectedFile().getAbsolutePath();
//                        File iF= new File(fileName);
//                        jf.addChoosableFileFilter(new TextFileFilter(".png"));
//                        jf.addChoosableFileFilter(new TextFileFilter(".jpg"));
//                        imag = ImageIO.read(iF);
//                        loading=true;
//                        f.setSize(imag.getWidth()+40, imag.getWidth()+80);
//                        jpanel.setSize(imag.getWidth(), imag.getWidth());
//                        jpanel.repaint();
//                    } catch (FileNotFoundException ex) {
//                        JOptionPane.showMessageDialog(f, "Такого файла не существует");
//                    }
//                    catch (IOException ex) {
//                        JOptionPane.showMessageDialog(f, "Исключение ввода-вывода");
//                    }
//                    catch (Exception ex) {
//                        System.out.println("Ошибка!");
//                    }
//                }
//            }
//        };
//        JMenuItem loadMenu = new JMenuItem(loadAction);
//        fileMenu.add(loadMenu);
//
//        Action saveAction = new AbstractAction("Сохранить")
//        {
//            public void actionPerformed(ActionEvent event)
//            {
//                try
//                {
//                    JFileChooser jf= new  JFileChooser();
//                    // Создаем фильтры  файлов
//                    TextFileFilter pngFilter = new TextFileFilter(".png");
//                    TextFileFilter jpgFilter = new TextFileFilter(".jpg");
//                    if(fileName==null)
//                    {
//                        // Добавляем фильтры
//                        jf.addChoosableFileFilter(pngFilter);
//                        jf.addChoosableFileFilter(jpgFilter);
//                        int  result = jf.showSaveDialog(null);
//                        if(result==JFileChooser.APPROVE_OPTION)
//                        {
//                            fileName = jf.getSelectedFile().getAbsolutePath();
//                        }
//                    }
//                    // Смотрим какой фильтр выбран
//                    if(jf.getFileFilter() == pngFilter)
//                    {
//                        ImageIO.write(imag, "png", new File(fileName+".png"));
//                    }
//                    else
//                    {
//                        ImageIO.write(imag, "jpeg", new File(fileName+".jpg"));
//                    }
//                }
//                catch(IOException ex)
//                {
//                    JOptionPane.showMessageDialog(f, "Ошибка ввода-вывода");
//                }
//            }
//        };
//        JMenuItem saveMenu = new JMenuItem(saveAction);
//        fileMenu.add(saveMenu);
//
//        Action saveasAction = new AbstractAction("Сохранить как...")
//        {
//            public void actionPerformed(ActionEvent event)
//            {
//                try
//                {
//                    JFileChooser jf= new JFileChooser();
//                    // Создаем фильтры для файлов
//                    TextFileFilter pngFilter = new TextFileFilter(".png");
//                    TextFileFilter jpgFilter = new TextFileFilter(".jpg");
//                    // Добавляем фильтры
//                    jf.addChoosableFileFilter(pngFilter);
//                    jf.addChoosableFileFilter(jpgFilter);
//                    int  result = jf.showSaveDialog(null);
//                    if(result==JFileChooser.APPROVE_OPTION)
//                    {
//                        fileName = jf.getSelectedFile().getAbsolutePath();
//                    }
//                    // Смотрим какой фильтр выбран
//                    if(jf.getFileFilter()==pngFilter)
//                    {
//                        ImageIO.write(imag, "png", new File(fileName+".png"));
//                    }
//                    else
//                    {
//                        ImageIO.write(imag, "jpeg", new File(fileName+".jpg"));
//                    }
//                }
//                catch(IOException ex)
//                {
//                    JOptionPane.showMessageDialog(f, "Ошибка ввода-вывода");
//                }
//            }
//        };
//        JMenuItem saveasMenu = new JMenuItem(saveasAction);
//        fileMenu.add(saveasMenu);
//
//        jpanel = new MyPanel();
//        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//        double width = screenSize.getWidth();
//        double height = screenSize.getHeight();
//        jpanel.setBounds(160,30,(int)width-160,(int)height-30);
//        jpanel.setBackground(Color.white);
//        jpanel.setOpaque(true);
//        f.add(jpanel);
//
//        JToolBar toolbar = new JToolBar("Toolbar", JToolBar.VERTICAL);
//        //toolbar.setPreferredSize(new Dimension(120, 840));
//
//
//        JButton eraser = new JButton();
//        eraser.setPreferredSize(new Dimension(140, 90));
//        eraser.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource("/images/eraser.png"))));
//        eraser.addActionListener(new  ActionListener()
//        {
//            public void actionPerformed(ActionEvent event)
//            {
//                type=0;
//            }
//        });
//        toolbar.add(eraser);
//
//
//        JButton lineButton = new JButton();
//        lineButton.setPreferredSize(new Dimension(140, 90));
//        lineButton.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource("/images/line.png"))));
//        lineButton.addActionListener(new  ActionListener()
//        {
//            public void actionPerformed(ActionEvent event)
//            {
//                type=1;
//            }
//        });
//        toolbar.add(lineButton);
//
//        JButton hydrogen = makeButton(2, "/images/H.png", Color.CYAN);
//        toolbar.add(hydrogen);
//
//        JButton nitrogen = makeButton(3, "/images/N.png", Color.PINK);
//        toolbar.add(nitrogen);
//
//        JButton carbon = makeButton(4, "/images/C.png", Color.ORANGE);
//        toolbar.add(carbon);
//
//        JButton oxygen = makeButton(5, "/images/O.png", Color.BLUE);
//        toolbar.add(oxygen);
//
//        JButton radical = makeRadical(6, "/images/R.png");
//        toolbar.add(radical);
//
//        toolbar.setBounds(0, 0, 30, 300);
//        toolbar.setSize(148, 900);
//
//        f.add(toolbar);
//
//        jpanel.addMouseMotionListener(new MouseMotionAdapter()
//        {
//            public void mouseDragged(MouseEvent e)
//            {
//                if (pressed)
//                {
//                    Graphics g = imag.getGraphics();
//                    Graphics2D g2 = (Graphics2D)g;
//                    // установка цвета
//                    g2.setColor(Color.BLACK);
//                    // ластик
//                    if (type == 0) {
//                        g2.setStroke(new BasicStroke(10));
//                        g2.setColor(Color.WHITE);
//                        g2.drawLine(xPrev, yPrev, e.getX(), e.getY());
//                    }
//                    xPrev=e.getX();
//                    yPrev=e.getY();
//                }
//                jpanel.repaint();
//            }
//        });
//
//        jpanel.addMouseListener(new MouseAdapter()
//        {
//
//            public void mousePressed(MouseEvent e) {
//                x0=e.getX();
//                y0=e.getY();
//                xPrev=e.getX();
//                yPrev=e.getY();
//                pressed=true;
//            }
//            public void mouseReleased(MouseEvent e) {
//
//                Graphics g = imag.getGraphics();
//                Graphics2D g2 = (Graphics2D)g;
//                // установка цвета
//                g2.setColor(Color.BLACK);
//                switch (type) {
//                    // ластик
//                    case 0 -> {
//                        g2.setStroke(new BasicStroke(10));
//                        g2.setColor(Color.WHITE);
//                        g2.drawLine(x0, y0, e.getX(), e.getY());
//                    }
//                    // кисть
//                    case 1 -> {
//                        g2.setStroke(new BasicStroke(10));
//                        g2.drawLine(x0, y0, e.getX(), e.getY());
//                    }
//                }
//
//                x0=0; y0=0;
//                pressed=false;
//                jpanel.repaint();
//            }
//        });
//
//        f.addComponentListener(new ComponentAdapter() {
//            public void componentResized(java.awt.event.ComponentEvent evt) {
//                // если делаем загрузку, то изменение размеров формы
//                if(!loading)
//                {
//                    jpanel.setSize(f.getWidth()-40, f.getHeight()-80);
//                    BufferedImage tempImage = new BufferedImage(jpanel.getWidth(), jpanel.getHeight(), BufferedImage.TYPE_INT_RGB);
//                    Graphics2D d2 = (Graphics2D) tempImage.createGraphics();
//                    d2.setColor(Color.white);
//                    d2.fillRect(0, 0, jpanel.getWidth(), jpanel.getHeight());
//                    tempImage.setData(imag.getRaster());
//                    imag=tempImage;
//                    jpanel.repaint();
//                }
//                loading=false;
//            }
//        });
//        f.setLayout(null);
//        f.setVisible(true);
//    }
//
//    private JButton makeRadical(int t, String path){
//        JButton button = new JButton(path);
//        button.setPreferredSize(new Dimension(140, 90));
//        button.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource(path))));
//        button.addActionListener(new  ActionListener()
//        {
//            public void actionPerformed(ActionEvent event)
//            {
//                type=t;
//            }
//        });
//
//        button.addMouseListener(new MouseAdapter()
//            {
//                @Override
//                public void mouseReleased(MouseEvent e) {
//                    Graphics g = imag.getGraphics();
//                    try {
//                        BufferedImage myPicture = ImageIO.read(new File("D:\\Java\\IndividualTask\\src\\images\\R2.png"));
//                        g.drawImage(myPicture, button.getX() + e.getX() - 148 - 61, button.getY() + e.getY()-30 - 46, jpanel);
//                        jpanel.repaint();
//                    } catch (IOException ex) {
//                        System.out.println("Не загржена картинка R");
//                    }
//                }
//            }
//
//        );
//
//        return button;
//    }
//    private JButton makeButton(int t, String path, Color color){
//        JButton button = new JButton(path);
//        button.setPreferredSize(new Dimension(140, 90));
//        button.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource(path))));
//        button.addActionListener(new  ActionListener()
//        {
//            public void actionPerformed(ActionEvent event)
//            {
//                type=t;
//            }
//        });
//
//        button.addMouseListener(new MouseAdapter()
//            {
//                @Override
//                public void mouseReleased(MouseEvent e) {
//
//                    Graphics g = imag.getGraphics();
//                    Graphics2D g2 = (Graphics2D)g;
//                    // установка цвета
//                    g2.setColor(color);
//                    if (type == 2){
//                        g.fillOval(button.getX() + e.getX() - 148 - 30,button.getY() + e.getY()-30 - 30, 60, 60);
//                    }else {
//                        g.fillOval(button.getX() + e.getX() - 148 - 40, button.getY() + e.getY() - 30 - 40, 80, 80);
//                    }
//                    jpanel.repaint();
//                }
//            }
//
//        );
//
//        return button;
//    }
//    class MyPanel extends JPanel
//    {
//        public MyPanel()
//        { }
//        public void paintComponent (Graphics g)
//        {
//            if(imag==null)
//            {
//                imag = new  BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
//                Graphics2D d2 = (Graphics2D) imag.createGraphics();
//                d2.setColor(Color.white);
//                d2.fillRect(0, 0, this.getWidth(), this.getHeight());
//            }
//            super.paintComponent(g);
//            g.drawImage(imag, 0, 0,this);
//        }
//    }
//    // Фильтр картинок
//    class TextFileFilter extends FileFilter
//    {
//        private String ext;
//        public TextFileFilter(String ext)
//        {
//            this.ext=ext;
//        }
//        public boolean accept(java.io.File file)
//        {
//            if (file.isDirectory()) return true;
//            return (file.getName().endsWith(ext));
//        }
//        public String getDescription()
//        {
//            return "*"+ext;
//        }
//    }
//
//    public static void main(String[] args) {
//
//        SwingUtilities.invokeLater(new  Runnable() {
//            public void run() {
//                new  ImageEdit();
//            }
//        });
//    }
//}