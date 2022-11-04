package game;

import javax.swing.*;
import java.awt.*;

/**
 * Inherits from JButton. To set initial look of button at creation.
 * */
public class TileButton extends JButton {
    public TileButton(String text) {
        super(text);
        setBackground(new Color(120, 10,10));
        setForeground(Color.WHITE);
        if (text.equals("0")) {
            setBackground(Color.WHITE);
        }
    }
}
