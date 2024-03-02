package org.cherepovskyi.game.entity;


import org.cherepovskyi.game.main.GamePanel;
import org.cherepovskyi.game.main.KeyHandler;

import java.awt.*;

public class Player extends Entity {
    GamePanel qp;
    KeyHandler keyH;

    public Player(GamePanel qp, KeyHandler keyH) {
        this.qp = qp;
        this.keyH = keyH;

        setDefaultValue();
    }

    public void setDefaultValue() {
        x = 100;
        y = 100;
        speed = 4;
    }

    public void update() {
        if (keyH.upPressed) {
            y -= speed;
        }
        if (keyH.downPressed) {
            y += speed;
        }
        if (keyH.leftPressed) {
            x -= speed;
        }
        if (keyH.rightPressed) {
            x += speed;
        }
        // diagonal speed normalization
        if (keyH.upPressed && keyH.downPressed || keyH.upPressed && keyH.leftPressed || keyH.upPressed == true && keyH.rightPressed || keyH.downPressed && keyH.leftPressed || keyH.downPressed && keyH.rightPressed || keyH.leftPressed && keyH.rightPressed) {
            speed = 3;
        } else {
            speed = 4;
        }

    }

    public void paintComponent(Graphics2D g2) {

        g2.setColor(Color.white);
        g2.fillRect(x, y, qp.tileSize, qp.tileSize);
    }
}
