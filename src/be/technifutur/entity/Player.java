package be.technifutur.entity;

import be.technifutur.main.GamePanel;
import be.technifutur.main.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    public int hasKey = 0;


    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);

        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle();
        solidArea.x = 5;
        solidArea.y = 10;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 20;
        solidArea.height = 20;

        setDefaultValues();
        getPlayerImage();
    }


    public void setDefaultValues() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {

        up1 = setUp("/player/girl_up_1");
        up2 = setUp("/player/girl_up_2");
        down1 = setUp("/player/girl_down_1");
        down2 = setUp("/player/girl_down_2");
        left1 = setUp("/player/girl_left_1");
        left2 = setUp("/player/girl_left_2");
        right1 = setUp("/player/girl_right_1");
        right2 = setUp("/player/girl_right_2");

    }

    public void update() {
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {

            if (keyH.upPressed) {
                direction = "up";

            } else if (keyH.downPressed) {
                direction = "down";

            } else if (keyH.leftPressed) {
                direction = "left";

            } else {
                direction = "right";
            }

            // if collision id false, player can move

            if (!collisionOn) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }
            //check tile collision
            collisionOn = false;
            gp.ColChecker.checkTile(this);

            //check object collision
            int objIndex = gp.ColChecker.checkObject(this, true);
            pickUpObject(objIndex);


            //warning !!
            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void pickUpObject(int i) {
        if (i != 999) {
            String objectName = gp.obj[i].name;

            switch (objectName) {
                case "Key":
                    gp.playSE(1);
                    hasKey++;
                    gp.obj[i] = null;
                    gp.ui.showMessage(" you get a key ! ");
                    break;

                case "Door":
                    if (hasKey > 0) {
                        gp.playSE(3);
                        gp.obj[i] = null;
                        hasKey--;
                        gp.ui.showMessage(" you opened the door ! ");
                    } else {
                        gp.ui.showMessage("you need a key ! ");
                    }

                    System.out.println("Key: " + hasKey);
                    break;

                case "Boots":
                    gp.playSE(2);
                    speed += 1;
                    gp.obj[i] = null;
                    gp.ui.showMessage(" Speed up ! ");
                    break;

                case "Chest":

                    gp.obj[i] = null;
                    gp.ui.gameFinished = true;
                    gp.stopMusic();
                    gp.playSE(4);
                    break;
            }
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }

}
