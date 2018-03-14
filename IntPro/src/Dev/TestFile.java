
/**
 *
 * @author 1630954
 */
import java.io.File;
public class TestFile {
    public static void main(String[] args){
        File file = new File("F:\\Programming\\Integrative Project\\Projectile Motion in Gravity Field\\Cannon_Renaissance.png");
        
        if (file.exists()) {
            System.out.print("This file exists");
        }
        else {
            System.out.print("Fuck  you it doesn't");
        }
    }
}
