package net.emiliollbb.nescomp;

public class TileBlock {
	Tile[][] tiles;
	
	public TileBlock() {
		tiles=new Tile[4][4];
	}

	public TileBlock(Tile[][] tileArray, int r, int c) {
		this();
		for(int row=0; row<tiles.length && row+r<30; row++) {
			for(int col=0; col<4; col++) {
				tiles[row][col] = tileArray[r*4+row][c*4+col];				
			}
		}
	}
	
	public char getPalette() {
		switch(tiles[0][0].getPalette()) {
		case 0:
			return 0x00;
		case 1:
			return 0x55;
		case 2:
			return 0xaa;
		case 3:
			return 0xff;
		default:
			return 0x00;
		}
	}
}
