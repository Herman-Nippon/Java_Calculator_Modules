package View;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public abstract class AbstractWindow extends JFrame {
    protected JPanel mainPanel;

    public AbstractWindow(String title) {
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create main panel
        mainPanel = new JPanel();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void addToWindow(Component component) {
        mainPanel.add(component);
    }

    public void applyBorder(Border border) {
        mainPanel.setBorder(border);
    }

    public void applyLayout(LayoutManager mng) {
        mainPanel.setLayout(mng);
    }

    public void applySize(Dimension dimension) {
        setPreferredSize(dimension);
    }
}
