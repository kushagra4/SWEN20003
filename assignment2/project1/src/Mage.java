import org.newdawn.slick.Input;

public class Mage extends Units {
	private static float playerX;
	private static float playerY;
	private static float distX;
	private static float distY;

	public Mage(float x, float y) {
		super("res/mage.png", x, y);
	}

	
	
	/**
	 * This function records the current x,y position of the player
	 * @param x stores the x coordinates of the player
	 * @param y stores the y coordinates of the player
	 */
	public static void sendPosition(float x, float y) {
		playerX = x;
		playerY = y;
	}

	public void update(Input input, int delta) {
		/* Check if the player has moved and if the move is valid then move the mage */
		if (Player.getMoveRogueAndMage()) {
			distX = ((playerX - super.getX()));
			distY = ((playerY - super.getY()));
			if (Math.abs(distX) > Math.abs(distY)) {
				if (!Loader.isBlocked((super.getX() + (App.TILE_SIZE * sgn(distX))), super.getY())) {
					super.setX(super.getX() + (App.TILE_SIZE * sgn(distX)));
					Player.setMoveRogueAndMage(false);
				}

			} else {
				if (!Loader.isBlocked(super.getX(), (super.getY() + (App.TILE_SIZE * sgn(distY))))) {

					super.setY(super.getY() + (App.TILE_SIZE * sgn(distY)));
					Player.setMoveRogueAndMage(false);
				}
			}
		}
	}

	/**
	 * sets the direction of the movement of the mage based on the value of 'x'
	 * @param x stores the greater of 'distX' or 'distY' 
	 * @return int -1 if x is less than 0 otherwise 1
	 */
	public int sgn(float x) {
		if (x < 0) {
			return -1;
		}
		return 1;
	}

}