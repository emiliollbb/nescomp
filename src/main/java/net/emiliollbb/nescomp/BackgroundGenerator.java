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
		for(int i=0; i<16; i++) {
			palette.put(pixels[pixels.length-1][i], (char)i);
		}
		// Full image in tiles, 960 tiles
		List<Tile> tiles=new ArrayList<>(960);
		// List of unique tiles, aka bank
		List<Tile> tileBank=new ArrayList<>();
		// Index to find tiles. Key=full tile as string, value=Index in bank
		TreeMap<String, Integer> tileMap = new TreeMap<>();
		// Image map, list of tile indexes from bank
		List<Integer> tilesIndexes=new ArrayList<>(960);
		// Full image in bidimensional array of tiles
		Tile[][] tileArray = new Tile[30][32];
		
		// Convert image to tiles
		for(int row=0; row<30; row++) {
			for(int col=0; col<32; col++) {
				tiles.add(new Tile(pixels, palette, row, col));		
			}			
		}
		// Generate a bidimensional version of image in tiles
		for(int row=0; row<30; row++) {
			for(int col=0; col<32; col++) {
				tileArray[row][col]=tiles.get(row*32+col);
			}
		}
		
		// Generate bank from tiled image
		tileBank.add(new Tile());
		for(Tile t: tiles) {
			if(!tileBank.contains(t)) {
				tileBank.add(t);
			}
		}
		// Generate tile index, for easy find
		for(int i=0; i<tileBank.size(); i++) {
			tileMap.put(tileBank.get(i).toString(), i);
		}
		// Generate tile map of indexes
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
		//BackgroundGenerator.load(new File("/home/emilio/proyectos/nes_demos/pong.png"));
		BackgroundGenerator.load(new File("/home/emilio/proyectos/wedNESdaysExamples/desierto.png"));
	}
}
