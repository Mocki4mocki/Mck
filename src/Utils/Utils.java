package Utils;

import java.awt.image.BufferedImage;

/**
 * Created by Mocki on 02.11.2016.
 */
public class Utils {

	public static BufferedImage resize(BufferedImage image, int width, int hiegth) {

		BufferedImage newImage = new BufferedImage(width, hiegth, BufferedImage.TYPE_INT_RGB);
		newImage.getGraphics().drawImage(image, 0, 0, width, hiegth, null);

		return newImage;
	}

}
