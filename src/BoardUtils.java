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

				int left = ((c - 1) < 0 ? max : c - 1);
				int right = ((c + 1) == this.limit ? 0 : c + 1);
				int up = ((r - 1) < 0 ? max : r - 1);
				int down = ((r + 1) == this.limit ? 0 : r + 1);

				current.topLeft = cells.get(up).get(left);
				current.top = cells.get(up).get(c);
				current.topRight = cells.get(up).get(right);
				current.left = cells.get(r).get(left);
				current.right = cells.get(r).get(right);
				current.botLeft = cells.get(down).get(left);
				current.bot = cells.get(down).get(c);
				current.botRight = cells.get(down).get(right);

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
