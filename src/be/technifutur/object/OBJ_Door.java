package be.technifutur.object;

import be.technifutur.main.GamePanel;

import javax.imageio.ImageIO;
import java.util.Objects;

public class OBJ_Door extends SuperObject{
    GamePanel gp;

    public OBJ_Door(GamePanel gp) {
        this.gp = gp;

        name = "Door";
        try{

            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/door.png")));
            uTool.scaleImage(image,gp.tileSize,gp.tileSize);

        }catch (Exception e){
            System.out.println("object Door not found");
        }
        collision = true;
    }
}
