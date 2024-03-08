package Controller;

import Model.Calculations;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

public class EqualsButtonFunctionality implements ActionListener {
    private JTextField textField;

    public EqualsButtonFunctionality(JTextField textField) {
        this.textField = textField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Get the expression from the text field
        String expression = textField.getText();

        // Process the expression using the Calculations class
        Calculations calculations = new Calculations();
        String result = calculations.processExpression(expression);

        // Update the text field with the result
        textField.setText(result);
    }
}

