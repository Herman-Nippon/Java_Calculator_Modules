package View;

import Controller.EqualsButtonFunctionality;
import Controller.ExitButtonFunctionality;

import javax.swing.*;
import java.awt.*;

public class CalcWindow extends JFrame {
    private JTextField textField;

    public CalcWindow() {
        setTitle("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        textField = new JTextField();
        GridBagConstraints textFieldConstraints = new GridBagConstraints();
        textFieldConstraints.gridx = 0;
        textFieldConstraints.gridy = 0;
        textFieldConstraints.gridwidth = 2;
        textFieldConstraints.fill = GridBagConstraints.HORIZONTAL;
        textFieldConstraints.insets = new Insets(0, 0, 10, 0); // Padding
        panel.add(textField, textFieldConstraints);

        String[] buttonLabels = {"Exit", "="};
        for (int i = 0; i < buttonLabels.length; i++) {
            JButton button = new JButton(buttonLabels[i]);

            GridBagConstraints buttonConstraints = new GridBagConstraints();
            buttonConstraints.gridx = i;
            buttonConstraints.gridy = 1;
            buttonConstraints.fill = GridBagConstraints.HORIZONTAL;
            buttonConstraints.weightx = 0.5;

            if (button.getText().equals("Exit")) {
                button.addActionListener(new ExitButtonFunctionality());
            } else {
                button.addActionListener(new EqualsButtonFunctionality(textField));
            }

            // Padding
            buttonConstraints.insets = new Insets(
                    0,
                    (i == 0) ? 0 : 5,
                    0,
                    (i == 0) ? 5 : 0
            );

            panel.add(button, buttonConstraints);
        }

        getContentPane().add(panel);

        // Set preferred size of the frame
        setPreferredSize(new Dimension(300, 150));

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
