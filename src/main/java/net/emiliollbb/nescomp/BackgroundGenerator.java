package net.emiliollbb.nescomp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class BackgroundGenerator {
	public static void load(File file) throws Exception {
		int[][] pixels = PngDecoder.load(file);
		Map<Integer, Character> palette= new TreeMap<Integer, Character>();
		palette.put(pixels[pixels.length-1][0], (char)0);
		palette.put(pixels[pixels.length-1][1], (char)1);
		palette.put(pixels[pixels.length-1][2], (char)2);
		palette.put(pixels[pixels.length-1][3], (char)3);
		List<Tile> tiles=new ArrayList<>(920);
		
		for(int row=0; row<30; row++) {
			for(int col=0; col<32; col++) {
				tiles.add(new Tile(pixels, palette, row, col));		
			}			
		}
		
		for(int i=0; i<5; i++) {
			System.out.println(tiles.get(i));
		}
	}
	
	public static void main(String[] args) throws Exception{
		BackgroundGenerator.load(new File("/home/emilio/proyectos/nes_demos/pong.png"));
	}
}
