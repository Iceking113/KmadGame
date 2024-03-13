package org.cherepovskyi.game.entity;


import org.cherepovskyi.game.main.GamePanel;
import org.cherepovskyi.game.main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2;
        screenY = gp.screenHeight/2;

        setDefaultValue();
        getPlayerImage();
    }

    public void setDefaultValue() {
        worldX = gp.tileSize*25 - gp.tileSize/2;
        worldY = gp.tileSize*25 - gp.tileSize/2;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage(){
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("up.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("up2.png"));

            left1 = ImageIO.read(getClass().getResourceAsStream("left.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("left2.png"));

            right1 = ImageIO.read(getClass().getResourceAsStream("right.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("right2.png"));

            down1 = ImageIO.read(getClass().getResourceAsStream("down.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("down2.png"));

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void update() {
        if(keyH.upPressed || keyH.leftPressed || keyH.downPressed || keyH.rightPressed){
            if (keyH.upPressed) {
                direction = "up";
                worldY -= speed;
            }
            if (keyH.downPressed) {
                direction = "down";
                worldY += speed;
            }
            if (keyH.leftPressed) {
                direction = "left";
                worldX -= speed;
            }
            if (keyH.rightPressed) {
                direction = "right";
                worldX += speed;
            }
            // diagonal speed normalization
            if (keyH.upPressed && keyH.leftPressed || keyH.upPressed && keyH.rightPressed || keyH.downPressed && keyH.leftPressed || keyH.downPressed && keyH.rightPressed) {
                speed = 3;
            } else {
                speed = 4;
            }

            spriteCounter++;
            if(spriteCounter>10){
                if(spriteNum == 1){
                    spriteNum = 2;
                }else {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2) {

        //g2.setColor(Color.white);
        //g2.fillRect(x, y, qp.tileSize, qp.tileSize);

        BufferedImage image = null;
        switch (direction){
            case "up":
                if(spriteNum == 1){
                    image = up1;
                }else {
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum == 1){
                    image = down1;
                }else {
                    image = down2;
                }
                break;
            case "left":
                if(spriteNum == 1){
                    image = left1;
                }else {
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum == 1){
                    image = right1;
                }else {
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY,gp.tileSize,gp.tileSize, null);
    }
}
