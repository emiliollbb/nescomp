package net.emiliollbb.nescomp;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
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
		List<Tile> tileBank=new ArrayList<>();
		TreeMap<String, Integer> tileMap = new TreeMap<>();
		List<Integer> tilesIndexes=new ArrayList<>(920);
		
		for(int row=0; row<30; row++) {
			for(int col=0; col<32; col++) {
				tiles.add(new Tile(pixels, palette, row, col));		
			}			
		}
		
		tileBank.add(new Tile());
		for(Tile t: tiles) {
			if(!tileBank.contains(t)) {
				tileBank.add(t);
			}
		}
		
		for(int i=0; i<tileBank.size(); i++) {
			tileMap.put(tileBank.get(i).toString(), i);
		}
		
		for(Tile t: tiles) {
			tilesIndexes.add(tileMap.get(t.toString()));
		}
		
		System.out.println("TILE MAP\n---------------------------");
		for(int row=0; row<30; row++) {
			System.out.print(".byt ");
			for(int col=0; col<32; col++) {
				System.out.print(String.format("$%02X,", tilesIndexes.get(row*32+col)));
			}
			System.out.println();
		}
		for(int i=0; i<64; i++) {
			System.out.print(String.format("$%02X,", 0));
		}
		System.out.println("-------------------------------------\n\n");
		
		
		System.out.println("TILE BANK\n---------------------------");
		for(int i=0; i<tileBank.size(); i++) {
			System.out.println(tileBank.get(i).getHexString()+" ; Tile "+String.format("$%02X,",i));
		}
		System.out.println("-------------------------------------\n\n");
	}
	
	public static void main(String[] args) throws Exception{
		BackgroundGenerator.load(new File("/home/emilio/proyectos/nes_demos/pong.png"));
	}
}
