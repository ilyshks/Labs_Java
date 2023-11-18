import java.awt.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class NewAuto
{
    private static int cntValidFields = 0;
    private static boolean isDone = false;
    private static final String patternInt = "\\d+"; // for int
    private static final String patternDouble = "^(?=[^\\.])\\d*\\.?((?=[^\\.])\\d*)$"; // for double
    private static final JFrame frame = new JFrame();
    private static final JPanel panel = new JPanel();
    private static final JButton sendButton = new JButton("Отправить");
    private static final String[] data = new String[5];
    public static String[] getData(){
        return data;
    }
    public static boolean getIsDone(){
        return isDone;
    }
    private static void createLabel(String text, GridBagConstraints c, int gridx, int gridy){
        c.gridx = gridx;
        c.gridy = gridy;
        JLabel label = new JLabel(text);
        label.setFont(new Font("Serif", Font.PLAIN, 20));
        panel.add(label, c);
    }

    private static JLabel createErrorLabel(GridBagConstraints c, int gridx, int gridy){
        c.gridx = gridx;
        c.gridy = gridy;
        JLabel label = new JLabel("Неверный формат");
        //label.setFont(new Font("Serif", Font.PLAIN, 14));
//        label.setForeground(Color.LIGHT_GRAY);
//        label.setBackground(Color.LIGHT_GRAY);
        label.setForeground(frame.getForeground());
        label.setBackground(frame.getBackground());
        panel.add(label, c);
        return label;
    }

    private static JTextField createTextField(GridBagConstraints c, int gridx, int gridy){
        c.gridx = gridx;
        c.gridy = gridy;
        JTextField field = new JTextField(20);
        field.setFont(new Font("Serif", Font.PLAIN, 20));
        panel.add(field, c);
        return field;
    }

    public static void createAndShowGui()
    {
        frame.setBackground(Color.LIGHT_GRAY);
        frame.setForeground(Color.LIGHT_GRAY);
        panel.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        // описание полей
        createLabel("Марка:", c, 0, 1);
        createLabel("Модель:", c, 0, 3);
        createLabel("Макс. грузоподъёмность:", c, 0, 5);
        createLabel("Количество пассажиров:", c, 0, 7);
        createLabel("Макс. скорость:", c, 0, 9);

        // сообщение об ошибке
        JLabel errMsgMark = createErrorLabel(c, 1, 0);
        JLabel errMsgModel = createErrorLabel(c, 1, 2);
        JLabel errMsgMaxWeight = createErrorLabel(c, 1, 4);
        JLabel errMsgCntPassengers = createErrorLabel(c, 1, 6);
        JLabel errMsgMaxSpeed = createErrorLabel(c, 1, 8);

        JTextField TextMark = createTextField(c, 1, 1);
        JTextField TextModel = createTextField(c, 1, 3);
        JTextField TextMaxWeight = createTextField(c, 1, 5);
        JTextField TextCntPassengers = createTextField(c, 1, 7);
        JTextField TextMaxSpeed = createTextField(c, 1, 9);
        JTextField[] fields = new JTextField[]{TextMark, TextModel, TextMaxWeight, TextCntPassengers, TextMaxSpeed};

        c.gridx = 1;
        c.gridy = 12;
        sendButton.setFont(new Font("Serif", Font.PLAIN, 20));
        sendButton.setSize(new Dimension(10, 20));
        sendButton.setVisible(true);
        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cntValidFields += stringValidateInput(TextMark, errMsgMark);
                cntValidFields += stringValidateInput(TextModel, errMsgModel);
                cntValidFields += validateInput(TextMaxWeight, errMsgMaxWeight, patternDouble);
                cntValidFields += validateInput(TextCntPassengers, errMsgCntPassengers, patternInt);
                cntValidFields += validateInput(TextMaxSpeed, errMsgMaxSpeed, patternDouble);
                if (cntValidFields == 5){

                    for(int i = 0; i < 5; i++){
                        data[i] = fields[i].getText();
                    }
                    isDone = true;
                    frame.dispose();
                }else{
                    cntValidFields = 0;
                }
            }
        });
        panel.add(sendButton, c);

//        panel.revalidate();
        TextMark.getDocument().addDocumentListener(new DocumentListener()
        {
            @Override
            public void removeUpdate(DocumentEvent e)
            {
                stringValidateInput(TextMark, errMsgMark);
            }

            @Override
            public void insertUpdate(DocumentEvent e)
            {
                stringValidateInput(TextMark, errMsgMark);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {} // Not needed for plain-text fields
        });
        TextMark.getDocument().addDocumentListener(new DocumentListener()
        {
            @Override
            public void removeUpdate(DocumentEvent e)
            {
                stringValidateInput(TextModel, errMsgModel);
            }

            @Override
            public void insertUpdate(DocumentEvent e)
            {
                stringValidateInput(TextModel, errMsgModel);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {} // Not needed for plain-text fields
        });
        TextMaxWeight.getDocument().addDocumentListener(new DocumentListener()
        {
            @Override
            public void removeUpdate(DocumentEvent e)
            {
                validateInput(TextMaxWeight, errMsgMaxWeight, patternDouble);
            }

            @Override
            public void insertUpdate(DocumentEvent e)
            {
                validateInput(TextMaxWeight, errMsgMaxWeight, patternDouble);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {} // Not needed for plain-text fields
        });

        TextCntPassengers.getDocument().addDocumentListener(new DocumentListener()
        {
            @Override
            public void removeUpdate(DocumentEvent e)
            {
                validateInput(TextCntPassengers, errMsgCntPassengers, patternInt);
            }

            @Override
            public void insertUpdate(DocumentEvent e)
            {
                validateInput(TextCntPassengers, errMsgCntPassengers, patternInt);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {} // Not needed for plain-text fields
        });

        TextMaxSpeed.getDocument().addDocumentListener(new DocumentListener()
        {
            @Override
            public void removeUpdate(DocumentEvent e)
            {
                validateInput(TextMaxSpeed, errMsgMaxSpeed, patternDouble);
            }

            @Override
            public void insertUpdate(DocumentEvent e)
            {
                validateInput(TextMaxSpeed, errMsgMaxSpeed, patternDouble);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {} // Not needed for plain-text fields
        });

        frame.add(panel);
        frame.setLocation(200, 200);
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private static int validateInput(JTextField field, JLabel errorMsg, String pattern)
    {
        String text = field.getText();
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(text);
        if (m.matches())
        {
            errorMsg.setForeground(frame.getBackground());
            return 1;
        }
        else
        {
            errorMsg.setForeground(Color.RED);
            return 0;
        }
    }

    private static int stringValidateInput(JTextField field, JLabel errorMsg)
    {
        String text = field.getText();
        if (!text.isEmpty())
        {
            errorMsg.setForeground(frame.getBackground());
            return 1;
        }
        else
        {
            errorMsg.setForeground(Color.RED);
            return 0;
        }
    }
}
