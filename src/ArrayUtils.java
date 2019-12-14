import java.util.ArrayList;
import java.util.Random;

import tester.*;
import javalib.impworld.*;
import java.awt.Color;
import javalib.worldimages.*;

public class ArrayUtils {
	public int neighborsAlive(ArrayList<Cell> neighbors) {
		int alive = 0;
		for (Cell c : neighbors) {
			alive += (c.alive ? 1 : 0);
		}
		return alive;
	}
}
