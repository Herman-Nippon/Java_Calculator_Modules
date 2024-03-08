package Controller;

import View.CalcWindow;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EqualsButtonFunctionality implements ActionListener {

    private String mathExpression;
    private CalcWindow win;

    public EqualsButtonFunctionality(CalcWindow win) {
        this.win = win;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mathExpression = win.retrieveExpression();
    }
}
