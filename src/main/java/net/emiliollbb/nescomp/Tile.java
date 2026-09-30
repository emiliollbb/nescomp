package net.emiliollbb.nescomp;

import java.util.Map;

public class Tile implements Comparable<Tile>  {
	char[][] pixels;
	
	public Tile() {
		pixels = new char[8][8];
	}

	public Tile(int[][] img, Map<Integer, Character> palette, int tileRow, int tileCol) {
		this();
		for(int row=0; row<pixels.length; row++) {
			for(int col=0; col<8; col++) {
				Character v = palette.get(img[tileRow*8+row][tileCol*8+col]);
				pixels[row][col]=v;
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
	
	public String getHexString() {
		StringBuilder sb=new StringBuilder();
		sb.append(".byt ");
		for(int row=0; row<pixels.length; row++) {
			for(int col=0; col<8; col++) {
				sb.append(String.format("$%02X,", (int)pixels[row][col]));
			}
		}
		return sb.toString();
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
