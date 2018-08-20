import org.newdawn.slick.Input;

public class Rogue extends Units {
	private static int direction = Sprite.DIR_LEFT;
	private static float delta_x;
	private static float delta_y;
	private static boolean moveBlock = false;

	public Rogue(float x, float y) {
		super("res/rogue.png", x, y);
	}

	public void update(Input input, int delta) {
		/* check if the player has moved then set rogue's direction of movement */
		if (Player.getMoveRogueAndMage()) {
			setMovement(direction);
			/*
			 * check if the movement if possible then move the rogue else if it is blocked
			 * by a wall then change its direction of movement
			 */
			if (moveToDest(direction)) {

				if ((!World.isBlock(super.getX() + delta_x, super.getY() + delta_y))) {
					super.setX(super.getX() + delta_x);
					super.setY(super.getY() + delta_y);
					Player.setMoveRogueAndMage(false);
				}

				else if (!World.isBlock(super.getX() + delta_x + delta_x, super.getY() + delta_y + delta_y)
						&& !Loader.isBlocked(super.getX() + delta_x + delta_x, super.getY() + delta_y + delta_y)) {
					moveBlock = true;
					super.setX(super.getX() + delta_x);
					super.setY(super.getY() + delta_y);
					Player.setMoveRogueAndMage(false);
				} else {
					changeDirection();
				}

			} else {
				changeDirection();
			}
		}
	}

	public static boolean getMoveBlock() {
		return moveBlock;
	}

	public static void setMoveBlock(boolean moveBlock) {
		Rogue.moveBlock = moveBlock;
	}

	/**
	 * sets the value of delta_x and delta_y according to 'dir' 
	 * @param dir stores the direction the rogue is to be moved in
	 */
	public void setMovement(int dir) {
		float speed = App.TILE_SIZE;
		// Translate the direction to an x and y displacement

		switch (dir) {
		case DIR_LEFT:
			delta_x = -speed;
			break;
		case DIR_RIGHT:
			delta_x = speed;
			break;
		case DIR_UP:
			delta_y = -speed;
			break;
		case DIR_DOWN:
			delta_y = speed;
			break;
		}
	}

	/**
	 * reverses the direction of the rogue
	 */
	public void changeDirection() {
		switch (direction) {
		case DIR_LEFT:
			direction = DIR_RIGHT;
			break;
		case DIR_RIGHT:
			direction = DIR_LEFT;
			break;

		}
	}

	public static int getDirection() {
		return direction;
	}

	public static void setDirection(int direction) {
		Rogue.direction = direction;
	}
}