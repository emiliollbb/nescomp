package net.emiliollbb.nescomp;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class BackgroundGenerator {
	public static void load(File file) throws Exception {
		final BufferedImage image = ImageIO.read(file);
		byte pixels[] = new byte[8192];
		int i=0;
		
		for(int row=0; row < image.getHeight(); row++) {
			for(int col=0; col<image.getWidth(); col++) {
				System.out.println(String.format("#%06X", image.getRGB(col, row)&0x00FFFFFF));
				//pixels[i++]=NESPalette.getColorByte(String.format("#%06X", image.getRGB(col, row)));				
			}			
		}
	}
	
	public static void main(String[] args) throws Exception{
		BackgroundGenerator.load(new File("/home/emilio/proyectos/nes_demos/controllers.png"));
	}
}
