package objects;

import inputs.KeyboardListener;
//import main.GamePanel;
import main.GameScreen;
//import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{
    GameScreen gamePanel;
    KeyboardListener keyHandler;

    public final int screenX;
    public final int screenY;


    public Player(GameScreen gamePanel, KeyboardListener keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        screenX = gamePanel.screenWidth/2 - (gamePanel.tileSize/2);
        screenY = gamePanel.screenHeight/2 - (gamePanel.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getplayerImage();
        direction = "down";
    }

    public void setDefaultValues(){
        worldX = gamePanel.tileSize * 23;
        worldY = gamePanel.tileSize * 21;
        speed = 4;
    }

    public void getplayerImage(){
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_right_2.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void update(){

        if (keyHandler.upPressed == true || keyHandler.downPressed == true
        || keyHandler.leftPressed == true || keyHandler.rightPressed == true){
            if (keyHandler.upPressed == true){
                direction = "up";
            }
            else if (keyHandler.downPressed == true){
                direction = "down";
            }
            else if (keyHandler.leftPressed == true){
                direction = "left";
            }
            else if (keyHandler.rightPressed == true){
                direction = "right";
            }

            //Check tile colision
            collisionOn = false;
//            gamePanel.cChecker.checkTile(this);

            // if collision is false, player can mmove

            if(collisionOn == false){
                switch (direction){
                    case "up" :  worldY -= speed; break;
                    case "down": worldY += speed; break;
                    case "left":  worldX -= speed; break;
                    case "right": worldX += speed; break;
                }
            }


            spriteCounter++;
            if (spriteCounter > 12){
                if (spriteNum == 1){
                    spriteNum = 2;
                }else if (spriteNum == 2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics2D graphics2D){
//        graphics2D.setColor(Color.white);
//        graphics2D.fillRect(x,y,gamePanel.tileSize,gamePanel.tileSize);

        BufferedImage image = null;

        switch (direction){
            case "up":
                if (spriteNum == 1){
                    image = up1;
                }
                if (spriteNum == 2){
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1){
                    image = down1;
                }
                if (spriteNum == 2){
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1){
                    image = left1;
                }
                if (spriteNum == 2){
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1){
                    image = right1;
                }
                if (spriteNum == 2){
                    image = right2;
                }
                break;
        }

        graphics2D.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
    }



}
