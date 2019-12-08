import java.util.ArrayList;
import java.util.Random;

import tester.*;
import javalib.impworld.*;
import java.awt.Color;
import javalib.worldimages.*;

public class BoardUtils {
	// int for limit of the for loops
	int limit = LifeWorld.SIZE;
	
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
	public void linkCells(ArrayList<ArrayList<Cell>> cells) {
		for (int r = 0; r < this.limit; r ++) {
			for (int c = 0; c < this.limit; c ++) {
				Cell current = cells.get(r).get(c);
				if (c != 0) {
					current.left = cells.get(r).get(c - 1);
					current.neighbors.add(current.left);
					
					if (r != 0) {
						current.topLeft = cells.get(r - 1).get(c - 1);
						current.neighbors.add(current.topLeft);
					}
					if (r + 1 != this.limit) {
						current.botLeft = cells.get(r + 1).get(c - 1);
						current.neighbors.add(current.botLeft);
					}
				}
				
				if (c + 1 != this.limit) {
					current.right = cells.get(r).get(c + 1);
					current.neighbors.add(current.right);
					
					if (r != 0) {
						current.topRight = cells.get(r - 1).get(c + 1);
						current.neighbors.add(current.topRight);
					}
					if (r + 1 != this.limit) {
						current.botRight = cells.get(r + 1).get(c + 1);
						current.neighbors.add(current.botRight);
					}
				}
				
				if (r != 0) {
					current.top = cells.get(r - 1).get(c);
					current.neighbors.add(current.top);
				}
				
				if (r + 1 != this.limit) {
					current.bot = cells.get(r + 1).get(c);
					current.neighbors.add(current.bot);
				}
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
