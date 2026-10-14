package net.emiliollbb.nescomp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TiledImage {
	/**
	 * Full image in int pixels
	 */
	private int[][] pixels;
	/** 
	 * Full image in tiles, 960 tiles
	 */
	private List<Tile> tiles;
	/**
	 * NES Background palettes. Conversion from pixel in int format to palette index
	 * and color.
	 */
	private Map<Integer, Character> palette;
	/**
	 * Image map, list of tile indexes from bank
	 */
	private List<Integer> tilesIndexes;

	private TiledImage() {
		tiles = new ArrayList<>(960);
		palette = new TreeMap<Integer, Character>();
		tilesIndexes = new ArrayList<>(960);
	}

	public TiledImage(File f) throws Exception {
		this();
		pixels = PngDecoder.load(f);
		for (int i = 0; i < 16; i++) {
			palette.put(pixels[pixels.length - 1][i], (char) i);
		}
		// Convert image to tiles
		for (int row = 0; row < 30; row++) {
			for (int col = 0; col < 32; col++) {
				tiles.add(new Tile(pixels, palette, row, col));
			}
		}
	}

	public String getHexString(TileBank bank) {
		// Generate tile map of indexes
		for (Tile t : tiles) {
			tilesIndexes.add(bank.getTileIndex(t));
		}

		StringBuilder sb = new StringBuilder();
		for (int row = 0; row < 30; row++) {
			sb.append(".byt ");
			for (int col = 0; col < 32; col++) {
				sb.append(String.format("$%02X,", tilesIndexes.get(row * 32 + col)));
			}
			sb.append('\n');
		}
		// TODO: Missing generate palettes
//		for (int row = 0; row < 8; row++) {
//			for (int col = 0; col < 8; col++) {
//				sb.append(String.format("$%02X,", 0));
//			}
//		}

		return sb.toString();
	}
}
