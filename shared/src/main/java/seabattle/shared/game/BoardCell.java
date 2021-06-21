package seabattle.shared.game;

import java.awt.*;

public class BoardCell {
    int r, g, b;

    public BoardCell(Color color) {
        setColor(color);
    }

    public void setColor(Color color) {
        this.r = color.getRed();
        this.g = color.getGreen();
        this.b = color.getBlue();
    }

    public Color getColor() {
        return new Color(r, g, b);
    }
}
