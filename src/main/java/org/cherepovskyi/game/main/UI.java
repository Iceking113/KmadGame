package org.cherepovskyi.game.main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
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
    int commandNumb = 0;
    int commandArrowMoveIndex = 0;
    int commandArrowMoveDirection = 0;

    BufferedImage image;
    // CLOUD MOVEMENTS
    /*BufferedImage backgroundImage1, backgroundImage2, cloud1, cloud2, cloud3;
    int cloudIndex = 0;*/

    DecimalFormat dFormat = new DecimalFormat("#0");

    public UI(GamePanel gp){
        this.gp = gp;
        this.image = image("MainScrin.png");
        // CLOUD MOVEMENTS
        /*this.backgroundImage1 = image("MainScrinImage/background1.png");
        this.backgroundImage2 = image("MainScrinImage/background2.png");
        this.cloud1 = image2("MainScrinImage/cloud1.png");
        this.cloud2 = image2("MainScrinImage/cloud2.png");
        this.cloud3 = image2("MainScrinImage/cloud3.png");*/
        arial_40 = new Font("System Bold", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);
        gp.playMusic(0);

    }
    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2){
        this.g2 = g2;
        g2.setFont(arial_40);
        g2.setColor(Color.white);
        //TITLE STATE
        if(gp.gameState == gp.titleState){
            drawTitleScreen();
        }
        //PAUSE STATE
        if(gp.gameState == gp.pauseState){
            drawPauseScreen();
        }
        //PLAY STATE
        if(gp.gameState == gp.playState){
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

    public void drawTitleScreen(){

        g2.drawImage(image, 0, 0, null);

        // CLOUD MOVEMENTS

        /*g2.drawImage(backgroundImage1, 0, 0, null);
        cloudIndex = cloudIndex + 2;

        g2.drawImage(cloud1, gp.tileSize*8-cloudIndex, 0, null);
        g2.drawImage(cloud2, gp.tileSize*16-cloudIndex, gp.tileSize*2, null);
        g2.drawImage(cloud3, gp.tileSize-cloudIndex, gp.tileSize*3, null);
        if(cloudIndex > gp.screenHeight) {
            cloudIndex = -gp.screenHeight;
        }

        g2.drawImage(backgroundImage2, 0, 0, null);
*/

        //WINDOW
        g2.setColor(Color.white);
        int x = gp.screenWidth/3;
        int y = gp.screenHeight/3;
        int wigth = gp.screenWidth/3;
        int height = gp.screenHeight/2;
        Color c = new Color(0,0,0,220);
        drawSubWindow(x, y, wigth, height, c);


        //g2.setFont(arial_40);
        String text = "";
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,34F));

        // New game
        text = "New game";
        x = getXforCenterText(text);
        y += gp.tileSize*2;
        g2.setColor(Color.gray);
        g2.drawString(text, x+3, y+3);
        g2.setColor(Color.white);
        g2.drawString(text, x, y);
        if(commandNumb == 0){
            arrowMove();
            g2.drawString(">",x-gp.tileSize + commandArrowMoveIndex,y);
        }

        // Load game
        text = "Load game";
        x = getXforCenterText(text);
        y += gp.tileSize;
        g2.setColor(Color.gray);
        g2.drawString(text, x+3, y+3);
        g2.setColor(Color.white);
        g2.drawString(text, x, y);
        if(commandNumb == 1){
            arrowMove();
            g2.drawString(">",x-gp.tileSize + commandArrowMoveIndex,y);        }
        // Quit
        text = "Quit";
        x = getXforCenterText(text);
        y += gp.tileSize;
        g2.setColor(Color.gray);
        g2.drawString(text, x+3, y+3);
        g2.setColor(Color.white);
        g2.drawString(text, x, y);
        if(commandNumb == 2){
            arrowMove();
            g2.drawString(">",x-gp.tileSize + commandArrowMoveIndex,y);        }
    }
    void arrowMove(){
        if (commandArrowMoveDirection == 0) {
            this.commandArrowMoveIndex++;
            if (this.commandArrowMoveIndex > 30) {
                this.commandArrowMoveDirection = 1;

            }
        }else {
            this.commandArrowMoveIndex--;
            if (this.commandArrowMoveIndex < 0) {
                this.commandArrowMoveDirection = 0;
            }
        }
    }
    public void drawPauseScreen(){

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
        Color c = new Color(0,0,0,200);
        drawSubWindow(x, y, wigth, height, c);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 36F));
        x += gp.tileSize;
        y += gp.tileSize;
        for(String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }
    }
    public void drawSubWindow(int x, int y, int wigth, int height, Color c) {
        g2.setColor(c);
        g2.fillRoundRect(x, y, wigth, height, 35, 35);
        c = new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, wigth-10, height-10, 25, 25);
    }
    public int getXforCenterText(String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        return gp.screenWidth/2 - length/2;
    }
    public BufferedImage image(String imagePath) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        try{
            image = ImageIO.read(getClass().getResourceAsStream(imagePath));
            image = uTool.scaleImage(image, gp.screenWidth, gp.screenHeight);
        }catch(IOException e){
            e.printStackTrace();
        }
        return image;
    }
    /*public BufferedImage image2(String imagePath) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        try{
            image = ImageIO.read(getClass().getResourceAsStream(imagePath));
            image = uTool.scaleImage(image, gp.tileSize*6, gp.tileSize*5);
        }catch(IOException e){
            e.printStackTrace();
        }
        return image;
    }*/
}
