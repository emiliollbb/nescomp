package net.emiliollbb.nescomp;

import java.io.File;
import java.util.Map;
import java.util.TreeMap;

public class TileGenerator {
	public static void generateTile(File f) throws Exception{
		int[][] pixels = PngDecoder.load(f);
		Map<Integer, Character> palette= new TreeMap<Integer, Character>();
		palette.put(pixels[0][0], (char)0);
		palette.put(pixels[0][1], (char)1);
		palette.put(pixels[0][2], (char)2);
		palette.put(pixels[0][3], (char)3);
		
		char[] tile = new char[64];
		int i=0;
		for(int row=1; row<pixels.length; row++) {
			for(int col=0; col<8; col++) {
				tile[i++]=palette.get(pixels[row][col]);
			}
		}
		
		for(i=0; i<tile.length; i++) {
			System.out.print((int)tile[i]+" ");
		}
		System.out.println();
		
		generateHex(tile);
	}
	
	public static void generateHex(char[] pixels) throws Exception {
		StringBuffer line1=new StringBuffer(100);
		StringBuffer line2=new StringBuffer(100);
		if(pixels.length!=64) {
			throw new Exception("Wrong size");
		}
		for(char c: pixels) {
			byte b = (byte) c;
			switch(b) {
				case 0:
					line1.append('0');
					line2.append('0');
					break;
				case 1:
					line1.append('1');
					line2.append('0');
					break;
				case 2:
					line1.append('0');
					line2.append('1');
					break;
				case 3:
					line1.append('1');
					line2.append('1');
					break;
			}
		}
		
		String full = line1.toString()+line2.toString();
		System.out.print(".byt ");
		for(int i=0; i<full.length(); i+=8) {
			System.out.print(String.format("$%02X,", Integer.parseInt(full.substring(i,i+8),2)));
		}
		System.out.println();
	}
	
	public static void main(String[] args) throws Exception{
		TileGenerator.generateTile(new File("/home/emilio/proyectos/wedNESdaysExamples/tree.png"));
	}
}
