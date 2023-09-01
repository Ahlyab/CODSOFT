package CustomGUIComponents;

import javax.swing.*;
import java.awt.*;

public class CustomButtons extends JButton {
    public CustomButtons() {
        super();
    }

    public CustomButtons(Icon icon) {
        super(icon);
        this.setCustomFont();

    }

    public CustomButtons(String text) {
        super(text);
        this.setCustomFont();
    }

    public CustomButtons(Action a) {
        super(a);
        this.setCustomFont();

    }

    public CustomButtons(String text, Icon icon) {
        super(text, icon);
        this.setCustomFont();

    }

    @Override
    public void setFont(Font font) {
        super.setFont(font);
    }

    private void setCustomFont() {
        this.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
    }
}
