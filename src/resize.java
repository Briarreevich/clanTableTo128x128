import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Color;
import java.util.ArrayList;

public class resize {
    public static ArrayList<BufferedImage> make11to128 (ArrayList<BufferedImage> icons11){
        ArrayList<BufferedImage> icons128 = new ArrayList<>();
        for(int size = 0; size < icons11.size(); size++) {
            BufferedImage source = icons11.get(size);
            BufferedImage result = new BufferedImage(128,128, 6);
            for(int x = 0; x < 128; x++){
                for(int y = 0; y < 128; y++){
                    if(((x<=2) && (x>=124))||((y<=2) && (y>=124))){
                        result.setRGB(x,y,new Color(0, 0, 0).getRGB());
                    }
                }
            }
            for (int x = 0; x < 11; x++){
                for (int y = 0; y < 11; y++){
                    int color = source.getRGB(x,y);
                    for(int i = x*11+3; i <(x+1)*11+3; i++){
                        for(int j = y*11+3; j <(y+1)*11+3; j++){
                            result.setRGB(i,j,color);
                        }
                    }
                }
            }
            icons128.add(result);
        }
        return icons128;
    }

    public static ArrayList<BufferedImage> make11x11 (BufferedImage source){
        ArrayList<BufferedImage> icons11 = new ArrayList<>();
        for(int i = 0; i < 22; i++){
            for (int j = 0; j < 10; j++){
                BufferedImage result = new BufferedImage(11,11, 6);
                for(int x = 13*j+1; x < 13*(j+1)-1; x++) {
                    for(int y = 13*i+1; y < 13*(i+1)-1; y++) {
                        result.setRGB(x % 13 - 1, y % 13 - 1, source.getRGB(x, y));
                    }
                }
                icons11.add(result);
            }
        }
        return icons11;
    }

    public static void main(String args[]) throws IOException {
        File file = new File("clans.png");
        BufferedImage source = ImageIO.read(file);
        ArrayList<BufferedImage> icons11 = make11x11(source);
        ArrayList<BufferedImage> icons128 = make11to128(icons11);
        int i = 1;
        for(BufferedImage result: icons128){
            File output = new File(i+"_128.png");
            ImageIO.write(result, "png", output);
            i++;
        }
    }
}
