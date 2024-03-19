package org.cherepovskyi.game.main;

import org.cherepovskyi.game.object.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_80B;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    double playTime;
    public String currentDialogue = "";

    DecimalFormat dFormat = new DecimalFormat("#0");

    public UI(GamePanel gp){
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);
    }
    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

    public void drow(Graphics2D g2){
        this.g2 = g2;
        g2.setFont(arial_40);
        g2.setColor(Color.white);
        //PAUSE STATE
        if(gp.gameState == gp.pauseState){
            drowPauseScreen();
        }
        //PLAY STATE
        if(gp.gameState == gp.pauseState){
            // do it later
        }
        //DIALOGUE STATE
        if(gp.gameState == gp.dialogueState){
            drawDialgueScreen();
        }
        if(messageOn) {
            messageCounter++;
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));
            int x = getXforCenterText(message);
            int y = gp.screenHeight - gp.screenHeight/8;

            g2.drawString(message,x,y);
            if(messageCounter > 5) {
                messageCounter = 0;
                messageOn = false;
            }

        }
    }
    public void drowPauseScreen(){

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSED";
        int x = getXforCenterText(text);
        int y = gp.screenHeight/2;

        g2.drawString(text,x,y);
    }
    public void  drawDialgueScreen() {
        messageOn = false;
        //WINDOW
        int x = 0;
        int y = gp.screenHeight - gp.screenHeight/4;
        int wigth = gp.screenWidth;
        int height = gp.screenHeight/4;
        drawSubWindow(x, y, wigth, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 36F));
        x += gp.tileSize;
        y += gp.tileSize;
        for(String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }
    }
    public void drawSubWindow(int x, int y, int wigth, int height) {
        Color c = new Color(0,0,0,200);
        g2.setColor(c);
        g2.fillRoundRect(x, y, wigth, height, 35, 35);
        c = new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, wigth-10, height-10, 25, 25);
    }
    public int getXforCenterText(String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
}
