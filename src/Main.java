
public class Main {
	public static void main(String args[]) {
		LifeWorld game = new LifeWorld(30);
		int size = Cell.SIZE * LifeWorld.SIZE;
		double tickRate = 0.25;
		
		game.bigBang(size, size, tickRate);
	}
}
