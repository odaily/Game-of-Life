import java.util.ArrayList;
import java.util.Random;

import tester.*;
import javalib.impworld.*;
import java.awt.Color;
import javalib.worldimages.*;

public class LifeWorld extends World {
	// 2D matrix of cells
	ArrayList<ArrayList<Cell>> cells;
	
	// Utilities
	BoardUtils bUT;
	ArrayUtils aUT;

	// Size of board
	public static int SIZE;

	// Boolean for whether life should be updating or not
	boolean lifeInAction;

	LifeWorld(int size) {
		LifeWorld.SIZE = size;
		this.bUT = new BoardUtils(this);
		this.aUT = new ArrayUtils();
		this.cells = this.bUT.makeCells();
		this.bUT.linkNeighbors(this.cells);
		this.lifeInAction = false;
	}

	// Create the scene of the game
	public WorldScene makeScene() {
		WorldScene scene = this.getEmptyScene();
		this.bUT.drawCells(cells, scene);
		return scene;
	}

	// clicking and creating life in a cell
	public void onMouseClicked(Posn pos) {
		int c = pos.x / Cell.SIZE;
		int r = pos.y / Cell.SIZE;

		if ((c < LifeWorld.SIZE) && (r < LifeWorld.SIZE)) {
			Cell frakenCell = this.cells.get(r).get(c);
			frakenCell.alive = !frakenCell.alive;
		}
	}

	public void onKeyEvent(String key) {
		if (key.equals("r")) {
			this.bUT.reset(this.cells);
			this.lifeInAction = false;
		}
		if (key.equals(" ")) {
			this.lifeInAction = !this.lifeInAction;
		}
	}

	public void onTick() {
		if (this.lifeInAction) {
			this.bUT.processLife(this.cells);
		}
	}

}
