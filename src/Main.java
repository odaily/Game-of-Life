
public class Main {
	public static void main(String args[]) {
		LifeWorld game = new LifeWorld();
		int size = Cell.SIZE * LifeWorld.SIZE;
		double tickRate = 0.5;
		
		game.bigBang(size, size, tickRate);
	}
}
