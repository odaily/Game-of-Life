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
	BoardUtils bUT = new BoardUtils();
	ArrayUtils aUT = new ArrayUtils();

	// Size of board
	public static int SIZE = 20;

	// Boolean for weather life should be updating or not
	boolean lifeInAction;

	LifeWorld() {
		this.cells = this.bUT.makeCells();
		this.bUT.linkCells(this.cells);
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
			LifeWorld resetWorld = new LifeWorld();
			this.cells = resetWorld.cells;
			this.lifeInAction = resetWorld.lifeInAction;
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
