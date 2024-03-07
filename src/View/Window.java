package View;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Window extends AbstractWindow {

    private List<JButton> buttonList;
    private LayoutManager layout;

    public Window() {
        super("Calculator");

        JTextField textField = new JTextField();
        buttonList = new ArrayList<>();

        String[] buttons = {"Exit", "="};
        for (String buttonText : buttons) {
            buttonList.add(new JButton(buttonText));
        }


    }
}
