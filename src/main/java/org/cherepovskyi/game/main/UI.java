package org.cherepovskyi.game.main;

import org.cherepovskyi.game.object.OBJ_health;

import javax.imageio.ImageIO;
import javax.swing.text.html.parser.Entity;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Locale;

import static javax.swing.plaf.basic.BasicGraphicsUtils.drawString;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    BufferedImage healthPart_Full, healthPart_Half, healthPart_Blank;
    Font arial_40, arial_80B, retganon_40;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    double playTime;
    public String currentDialogue = "";
    int commandNumb = 0;
    int commandArrowMoveIndex = 0;
    int commandArrowMoveDirection = 0;
    int titleScreenState = 0;
    int subState = 0;
    BufferedImage image;
    // CLOUD MOVEMENTS
    /*BufferedImage backgroundImage1, backgroundImage2, cloud1, cloud2, cloud3;
    int cloudIndex = 0;*/
    DecimalFormat dFormat = new DecimalFormat("#0");

    public UI(GamePanel gp){
        this.gp = gp;
        this.image = image("Manu2.png");
        // CLOUD MOVEMENTS
        /*this.backgroundImage1 = image("MainScrinImage/background1.png");
        this.backgroundImage2 = image("MainScrinImage/background2.png");
        this.cloud1 = image2("MainScrinImage/cloud1.png");
        this.cloud2 = image2("MainScrinImage/cloud2.png");
        this.cloud3 = image2("MainScrinImage/cloud3.png");*/

        // Font set from file
        arial_40 = new Font("System Bold", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);
        try {
            InputStream is = getClass().getResourceAsStream("Font/retganon.ttf");
            retganon_40 = Font.createFont(Font.TRUETYPE_FONT, is);
        }catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        gp.playMusic(0);

        // create hud OBJ
        OBJ_health health = new OBJ_health(gp); // OBJ_health -> Entity
        healthPart_Full = health.image;
        healthPart_Half = health.image_2;
        healthPart_Blank = health.image_3;

    }
    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2){
        this.g2 = g2;
        g2.setFont(retganon_40);
        g2.setColor(Color.white);

        //TITLE STATE
        if(gp.gameState == gp.titleState){
            drawTitleScreen();
        }
        //OPTIONS STATE
        if(gp.gameState == gp.optionsState){
            drawOptionsScreen();
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
            g2.setColor(Color.blue);
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 40F));
            int x = getXforCenterText(message);
            int y = gp.screenHeight - gp.screenHeight/10;

            g2.drawString(message,x,y);
            if(messageCounter > 5) {
                messageCounter = 0;
                messageOn = false;
            }

        }
        //FIGHT STATE
        if(gp.gameState == gp.fightState){
            drawPlayerHealth();
        }
    }

    public void drawTitleScreen(){

        if(titleScreenState == 0) {
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
            g2.setFont(g2.getFont().deriveFont(Font.BOLD ,45F));

            // New game
            text = "New game";
            x = getXforCenterText(text);
            y += gp.tileSize*2;
            g2.setColor(Color.gray);
            g2.drawString(text, x+3, y+3);
            g2.setColor(Color.white);
            g2.drawString(text, x, y);
            if(commandNumb == 0){
                arrowMove(30);
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
                arrowMove(30);
                g2.drawString(">",x-gp.tileSize + commandArrowMoveIndex,y);
            }
            // Quit
            text = "Quit";
            x = getXforCenterText(text);
            y += gp.tileSize;
            g2.setColor(Color.gray);
            g2.drawString(text, x+3, y+3);
            g2.setColor(Color.white);
            g2.drawString(text, x, y);
            if(commandNumb == 2){
                arrowMove(30);
                g2.drawString(">",x-gp.tileSize + commandArrowMoveIndex,y);
            }
        }else if(titleScreenState == 1) {

            g2.drawImage(image, 0, 0, null);
            g2.setColor(Color.white);
            int x = 0;
            int y = 0;
            int wigth = gp.screenWidth;
            int height = gp.screenHeight;
            Color c = new Color(0,0,0,220);
            drawSubWindow(x, y, wigth, height, c);

            g2.setFont(g2.getFont().deriveFont(Font.BOLD ,45F));
            String text = "Hello, here is the beginning of my story";
            x = getXforCenterText(text);
            y += gp.tileSize*2;
            g2.setColor(Color.gray);
            g2.drawString(text, x+3, y+3);
            g2.setColor(Color.white);
            g2.drawString(text, x, y);
        }

    }

    // ARROW FOR TITLE
    void arrowMove(int arrowMoveIndexMax){
        if (commandArrowMoveDirection == 0) {
            this.commandArrowMoveIndex++;
            if (this.commandArrowMoveIndex > arrowMoveIndexMax) {
                this.commandArrowMoveDirection = 1;

            }
        }else {
            this.commandArrowMoveIndex--;
            if (this.commandArrowMoveIndex < 0) {
                this.commandArrowMoveDirection = 0;
            }
        }
    }
    public void drawOptionsScreen(){

        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));

        //WINDOW
        int frameX = gp.screenWidth/3;
        int frameY = gp.screenHeight/4;
        int framWwigth =  gp.screenWidth/3;
        int frameHeight = gp.screenHeight/2;
        Color c = new Color(0,0,0,220);
        drawSubWindow(frameX, frameY, framWwigth, frameHeight, c);

        switch(subState){
            case 0: options_top(frameX, frameY); break;
            case 1: options_control(frameX, frameY);break;
            case 2: break;
            case 3: break;
            case 4: break;
        }
    }

    public  void options_control(int frameX, int frameY){
        int textX;
        int textY;

        // TITLE
        String text = "Control";
        textX = getXforCenterText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        // RETURN
        textX = frameX + gp.tileSize*2;
        textY += gp.tileSize;
        g2.drawString("MOVE", textX, textY); textY += gp.tileSize;
        g2.drawString("ATTACK", textX, textY); textY += gp.tileSize;
        g2.drawString("INTERACT", textX, textY); textY += gp.tileSize;
        g2.drawString("OPTIONS", textX, textY); textY += gp.tileSize;
        textX = getXforCenterText(text);
        g2.drawString("RETURN", textX, textY);
        arrowMove(15);
        g2.drawString(">",textX-gp.tileSize/2 + commandArrowMoveIndex,textY);

        textX = frameX + gp.tileSize*5;
        textY = frameY + gp.tileSize*2;
        g2.drawString("WASD", textX, textY); textY += gp.tileSize;
        g2.drawString("\"\"", textX, textY); textY += gp.tileSize;
        g2.drawString("E", textX, textY); textY += gp.tileSize;
        g2.drawString("ESC", textX, textY); textY += gp.tileSize;

    }
    public  void options_top(int frameX, int frameY){
        int textX;
        int textY;

        // TITLE
        String text = "Options";
        textX = getXforCenterText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        // RETURN
        textX = frameX + gp.tileSize;
        textY += gp.tileSize;
        g2.drawString("RETURN", textX, textY);
        if(commandNumb == 0){
            arrowMove(15);
            g2.drawString(">",textX-gp.tileSize/2 + commandArrowMoveIndex,textY);
        }

        // MUSIC
        textY += gp.tileSize/2;
        g2.drawString("MUSIC", textX, textY);
        if(commandNumb == 1){
            arrowMove(15);
            g2.drawString(">",textX-gp.tileSize/2 + commandArrowMoveIndex,textY);
        }
        // SE
        textY += gp.tileSize/2;
        g2.drawString("SE", textX, textY);
        if(commandNumb == 2){
            arrowMove(15);
            g2.drawString(">",textX-gp.tileSize/2 + commandArrowMoveIndex,textY);
        }
        // CONTROL
        textY += gp.tileSize/2;
        g2.drawString("CONTROL", textX, textY);
        if(commandNumb == 3){
            arrowMove(15);
            g2.drawString(">",textX-gp.tileSize/2 + commandArrowMoveIndex,textY);
        }
        // EXIT TO MENU
        textY += gp.tileSize/2;
        /*g2.drawString("EXIT TO MENU", textX, textY);
        if(commandNumb == 4){
            arrowMove(15);
            g2.drawString(">",textX-gp.tileSize/2 + commandArrowMoveIndex,textY);
        }*/
        // EXIT
        textY += gp.tileSize/2;
        g2.drawString("EXIT", textX, textY);
        if(commandNumb == 4){
            arrowMove(15);
            g2.drawString(">",textX-gp.tileSize/2 + commandArrowMoveIndex,textY);
        }

        //

        // MUSIC
        textX += gp.tileSize*3;
        textY = frameY + gp.tileSize*2;
        g2.drawRect(textX,textY, 150, 24);
        int volumeWight = 30 * gp.music.volumeScale;
        g2.fillRect(textX, textY, volumeWight, 24);
        // SE
        textY += gp.tileSize/2;
        g2.drawRect(textX,textY, 150, 24);
        volumeWight = 30 * gp.se.volumeScale;
        g2.fillRect(textX, textY, volumeWight, 24);
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
    public void drawPlayerHealth(){
        int x = gp.tileSize / 2;
        int y = gp.tileSize / 2;
        int i = 0;

        //draw max life
        while(i < gp.player.maxLife/2) {
            g2.drawImage(healthPart_Blank, x, y, null);
            i++;
            x += gp.tileSize;
        }


        //draw current life
        x = gp.tileSize / 2;
        y = gp.tileSize / 2;
        i = 0;
        while(i < gp.player.life) {
            g2.drawImage(healthPart_Half, x, y, null);
            i++;
            if(i < gp.player.life) {
                g2.drawImage(healthPart_Full, x, y, null);
            }
            i++;
            x += gp.tileSize;
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
