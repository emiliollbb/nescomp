package net.emiliollbb.nescomp;

import java.util.Map;

public class Tile implements Comparable<Tile>  {
	char[][] pixels;
	char palette;
	
	public Tile() {
		pixels = new char[8][8];
		palette=0;
	}

	public Tile(int[][] img, Map<Integer, Character> palette, int tileRow, int tileCol) {
		this();
		for(int row=0; row<pixels.length; row++) {
			for(int col=0; col<8; col++) {
				Character c = palette.get(img[tileRow*8+row][tileCol*8+col]);
				if(c==null) {
					System.out.println("Pixel: "+(tileRow*8+row)+", "+(tileCol*8+col)+" not found");
					c=0;
				}
				char v = (char)(c%4);
				pixels[row][col]=v;
				if(v!=0) {
					this.palette=(char)(v/4);
				}
			}
		}
	}
	
	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append("===============================\n");
		for(int row=0; row<pixels.length; row++) {
			for(int col=0; col<8; col++) {
				sb.append('[').append((int)pixels[row][col]).append(']').append(' ');
			}
			sb.append('\n');
		}
		sb.append("===============================\n");
		return sb.toString();
	}
	
	public String getHexString() throws Exception {
		char[] tile = new char[64];
		int i=0;
		for(int row=0; row<pixels.length; row++) {
			for(int col=0; col<8; col++) {
				tile[i++]=pixels[row][col];
			}
		}
		return TileGenerator.generateHex(tile);
	}

	@Override
	public int compareTo(Tile o) {
		return this.toString().compareTo(o.toString());
	}
	
	@Override
	public boolean equals(Object o) {
		return this.compareTo((Tile)o)==0;
	}
}
