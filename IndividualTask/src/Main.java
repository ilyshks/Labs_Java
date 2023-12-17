import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Data model = new Data();
        DataView view = new DataView();
        Controller controller = new Controller(model, view);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            controller.println(e.getMessage());
        }

        controller.createFrame();
    }
}