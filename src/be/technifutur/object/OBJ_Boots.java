package be.technifutur.object;

import be.technifutur.main.GamePanel;

import javax.imageio.ImageIO;
import java.util.Objects;

public class OBJ_Boots extends SuperObject {
    GamePanel gp;

    public OBJ_Boots(GamePanel gp) {

        this.gp = gp;

        name = "Boots";
        try {

            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/purpleBoot.png")));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        } catch (
                Exception e) {
            System.out.println("object Boots not found");
        }
    }
}
