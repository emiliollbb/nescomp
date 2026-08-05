package net.emiliollbb.nescomp;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class PngDecoder {
	public static int[][] load(File file) throws Exception {
		final BufferedImage image = ImageIO.read(file);
		int pixels[][] = new int[image.getHeight()][image.getWidth()];
		int i=0;
		
		for(int row=0; row < image.getHeight(); row++) {
			for(int col=0; col<image.getWidth(); col++) {
				pixels[row][col]=image.getRGB(col, row);
			}			
		}
		
		return pixels;
	}
}
