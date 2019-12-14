import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import tester.*;
import javalib.impworld.*;
import java.awt.Color;
import javalib.worldimages.*;

public class BoardUtils {
	// int for limit of the for loops
	int limit;
	
	BoardUtils(LifeWorld world) {
		this.limit = world.SIZE;
	}
	
	// Create 2D matrix of cells
	public ArrayList<ArrayList<Cell>> makeCells() {
		ArrayList<ArrayList<Cell>> tempCells = new ArrayList<ArrayList<Cell>>();
		for (int r = 0; r < this.limit; r ++) {
			ArrayList<Cell> tempRow = new ArrayList<Cell>();
			for (int c = 0; c < this.limit; c ++) {
				tempRow.add(new Cell(c, r));
			}
			tempCells.add(tempRow);
		}
		return tempCells;
	}
	
	// Links cells to their neighbors
	public void linkNeighbors(ArrayList<ArrayList<Cell>> cells) {
		int max = this.limit - 1;
		for (int r = 0; r < this.limit; r ++) {
			for (int c = 0; c < this.limit; c ++) {
				Cell current = cells.get(r).get(c);
				if (c != 0) {
					current.left = cells.get(r).get(c - 1);
					
					if (r != 0) {
						current.topLeft = cells.get(r - 1).get(c - 1);
					}
					if (r + 1 != this.limit) {
						current.botLeft = cells.get(r + 1).get(c - 1);
					}
				}
				
				if (c != max) {
					current.right = cells.get(r).get(c + 1);
					
					if (r != 0) {
						current.topRight = cells.get(r - 1).get(c + 1);
					}
					if (r + 1 != this.limit) {
						current.botRight = cells.get(r + 1).get(c + 1);
					}
				}
				
				if (r != 0) {
					current.top = cells.get(r - 1).get(c);
				}
				else {
					current.top = cells.get(max).get(c);
				}
				
				if (r + 1 != this.limit) {
					current.bot = cells.get(r + 1).get(c);
				}
				else {
					current.bot = cells.get(0).get(c);
				}
				
				int mirrorLeft = ((c - 1) < 0 ? max : c - 1);
				int mirrorRight = ((c + 1) == this.limit ? 0 : c + 1);
				int mirrorTop = ((r - 1) < 0 ? max : r - 1);
				int mirrorBot = ((r + 1) == this.limit ? 0 : r + 1);

				if (current.topLeft == null) {
					current.topLeft = cells.get(mirrorTop).get(mirrorLeft);
				}
				if (current.top == null) {
					current.top = cells.get(max).get(c);
				}
				if (current.topRight == null) {
					current.topRight = cells.get(mirrorTop).get(mirrorRight);
				}
				if (current.left == null) {
					current.left = cells.get(r).get(max);
				}
				if (current.right == null) {
					current.right = cells.get(r).get(0);
				}
				if (current.botLeft == null) {
					current.botLeft = cells.get(mirrorBot).get(mirrorLeft);
				}
				if (current.bot == null) {
					current.bot = cells.get(0).get(c);
				}
				if (current.botRight == null) {
					current.botRight = cells.get(mirrorBot).get(mirrorRight);
				}
				
				ArrayList<Cell> neighborList = current.neighbors;
				neighborList.add(current.topLeft);
				neighborList.add(current.top);
				neighborList.add(current.topRight);
				neighborList.add(current.left);
				neighborList.add(current.right);
				neighborList.add(current.botLeft);
				neighborList.add(current.bot);
				neighborList.add(current.botRight);
			}
		}
	}

	// Draws cells onto given scene
	public void drawCells(ArrayList<ArrayList<Cell>> cells, WorldScene background) {
		for (int r = 0; r < this.limit; r ++) {
			for (int c = 0; c < this.limit; c ++) {
				Cell draw_cell = cells.get(r).get(c);
				
				background.placeImageXY(draw_cell.draw(),
						(c * Cell.SIZE) + Cell.GAP,
						(r * Cell.SIZE) + Cell.GAP);
			}
		}
	}
	
	// resets the cells
	public void reset(ArrayList<ArrayList<Cell>> cells) {
		for (ArrayList<Cell> row : cells) {
			for (Cell c : row) {
				c.alive = false;
				c.nextLife = false;
			}
		}
	}
	
	// Update whether or not the cell's should be alive or dead in the next tick
	public void processLife(ArrayList<ArrayList<Cell>> cells) {
		for (ArrayList<Cell> row : cells) {
			for (Cell c : row) {
				if (((c.aliveNeighbors() == 2) || (c.aliveNeighbors() == 3)) && c.alive) { 
					c.nextLife = true;
				}
				else if ((c.aliveNeighbors() == 3) && !c.alive) {
					c.nextLife = true;
				}
				else {
					c.nextLife = false;
				}
			}
		}
		
		for (ArrayList<Cell> row : cells) {
			for (Cell c : row) {
				c.alive = c.nextLife;
				c.nextLife = false;
			}
		}
	}
}
