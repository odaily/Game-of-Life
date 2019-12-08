import java.util.ArrayList;
import java.util.Random;

import tester.*;
import javalib.impworld.*;
import java.awt.Color;
import javalib.worldimages.*;

public class Cell {
	// Neighbor Cells
	Cell topLeft;
	Cell top;
	Cell topRight;
	Cell left;
	Cell right;
	Cell botLeft;	
	Cell bot;
	Cell botRight;
	// ^^^ DONT NEED ^^
	// don't need to know which neighbor is which, just need to know
	// number of alive neighbors, so use list of neighbors
	// List of neighbor cells
	ArrayList<Cell> neighbors;
	
	// Is cell alive or not
	boolean alive;
	
	boolean nextLife;
	
	// Coordinates
	int x;
	int y;
	
	static int SIZE = 20;
	static int GAP = Cell.SIZE / 2;
	
	Cell(int x, int y) {
		this.x = x;
		this.y = y;
		this.alive = false;
		this.neighbors = new ArrayList<Cell>();
		nextLife = false;
	}
	
	WorldImage draw() {
		if (this.alive) {
		    return new FrameImage(new RectangleImage(
			        Cell.SIZE, Cell.SIZE, OutlineMode.SOLID, Color.BLACK), Color.WHITE);
			        
		}
		else {
		    return new FrameImage(new RectangleImage(
			        Cell.SIZE, Cell.SIZE, OutlineMode.SOLID, Color.WHITE), Color.BLACK);
		}
	}
	
	public int aliveNeighbors() {
		return (new ArrayUtils()).neighborsAlive(this.neighbors);
	}
	
	public Cell copy() {
		Cell temp = new Cell(this.x, this.y);
		temp.alive = this.alive;
		temp.neighbors = this.neighbors;
		return temp;
	}
}
