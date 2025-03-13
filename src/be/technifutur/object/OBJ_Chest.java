package be.technifutur.object;

import be.technifutur.main.GamePanel;

import javax.imageio.ImageIO;
import java.util.Objects;

public class OBJ_Chest extends SuperObject {
    GamePanel gp;

    public OBJ_Chest(GamePanel gp) {

        this.gp = gp;

        name = "Chest";
        try {

            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/chest.png")));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (Exception e) {
            System.out.println("object chest not found");

        }
    }
}
