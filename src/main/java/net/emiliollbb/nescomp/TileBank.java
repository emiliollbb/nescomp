package net.emiliollbb.nescomp;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class TileBank {
	/**
	 * List of unique tiles, aka bank
	 */
	private List<Tile> tileBank;
	/**
	 * Index to find tiles. Key=full tile as string, value=Index in bank
	 */
	private TreeMap<String, Integer> tilesIndex;

	public TileBank() {
		tileBank = new ArrayList<>();
		tilesIndex = new TreeMap<>();
		addTile(new Tile());
	}

	public Integer getTileIndex(Tile t) {
		// Find tile
		Integer tileIndex = findTile(t);
		// Tile already in bank
		if (tileIndex != null) {
			return tileIndex;
		}
		// New tile for bank
		else {
			// Generate tile index, for easy find
			return addTile(t);
		}
	}

	/**
	 * Adds a new tile to bank
	 * 
	 * @param t tile to add
	 */
	private Integer addTile(Tile t) {
		// First add tile to bank
		tileBank.add(t);
		// Second add entry to index
		int currentTileIndex = tileBank.size() - 1;
		tilesIndex.put(t.toString(), currentTileIndex);
		return currentTileIndex;
	}

	private Integer findTile(Tile t) {
		return tilesIndex.get(t.toString());
	}

	public String getHexString() throws Exception {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < tileBank.size(); i++) {
			sb.append(tileBank.get(i).getHexString() + " ; Tile " + String.format("$%02X,", i)).append('\n');
		}
		return sb.toString();
	}
}
