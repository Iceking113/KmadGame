package org.cherepovskyi.game.main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UtilityTool {
    public BufferedImage scaleImage(BufferedImage origenal, int widgth, int height) {
        BufferedImage scaleImage = new BufferedImage(widgth, height, origenal.getType());
        Graphics2D g2 = scaleImage.createGraphics();
        g2.drawImage(origenal, 0, 0, widgth, height, null);
        g2.dispose();

        return scaleImage;
    }
}
