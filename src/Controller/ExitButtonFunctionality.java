package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * @apiNote
 * "Exit" button class;
 * It is called when the button is pressed.
 */
public class ExitButtonFunctionality implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}
