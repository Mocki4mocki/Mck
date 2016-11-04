package Graphics;

import Utils.ResourceLoader;

import java.awt.image.BufferedImage;

/**
 * Created by Mocki on 02.11.2016.
 */
public class TextureAtlas {

    BufferedImage image;

    public TextureAtlas(String imageName) {
        image = ResourceLoader.loaderImage(imageName);
    }

    public BufferedImage cut(int x, int y, int w, int h) {
        return image.getSubimage(x, y, w, h);
    }
}
