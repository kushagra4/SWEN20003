import org.newdawn.slick.Input;

public class Ice extends Blocks {
	private boolean firstMove = true; // indicates if ice is pushed from rest
	private int moveTime = 0;
	private final static int SPEED = 400;
	private static float delta_x;
	private static float delta_y;

	public Ice(float x, float y) {
		super("res/ice.png", x, y);
	}

	public void update(Input input, int delta) {

		move(delta);
	}

	
	/**
	 * moves the ice when the player pushes it and the movement is possible
	 * @param delta stores the time since the last update
	 */
	public void move(int delta) {

		// checks if the ice can the ice be pushed from rest, that is if it not blocked
		// by something
		if (Player.getMoveBlock() && moveToDest(Player.getDirection()) && World.getImage_src().equals("res/ice.png")
				&& (!Loader.isDoor(getX() + delta_x, getY() + delta_y) || Door.getShouldNotBlock())) {

			setMovement(Player.getDirection()); // sets the direction the ice has to move in
			firstMove = false;
			super.setX(super.getX() + delta_x);
			super.setY(super.getY() + delta_y);
			Player.setMoveBlock(false);
			coverTarget();

		}

		// check if the ice has already been pushed then it should continue moving with
		// a constant speed till its movement gets blocked
		else if (!firstMove) {

			if (World.isBlock(super.getX() + delta_x, super.getY() + delta_y)
					|| Loader.isBlocked(super.getX() + delta_x, super.getY() + delta_y)
					|| (Loader.isDoor(getX() + delta_x, getY() + delta_y))) {
				firstMove = true;
				delta_x = 0;
				delta_y = 0;
				moveTime = 0;
				coverTarget(); // check if it covering a target tile
			} else if (!World.isBlock(super.getX() + delta_x, super.getY() + delta_y)
					&& !Loader.isBlocked(super.getX() + delta_x, super.getY() + delta_y)
					&& (!Loader.isDoor(getX() + delta_x, getY() + delta_y) || Door.getShouldNotBlock())) {
				moveTime += delta;
				if (moveTime > SPEED) {

					super.setX(super.getX() + delta_x);
					super.setY(super.getY() + delta_y);
					coverTarget();
					moveTime = 0;

				}
			}

		}
	}

	/**
	 * sets the value of delta_x and delta_y according to 'dir' 
	 * @param dir stores the direction the ice is to be moved in
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
	 * checks if the ice is covering a target or not
	 */
	public void coverTarget() {
		// check if the target was covered by the block tile at its previous position
		// and it is again covering a new target tile
		if (World.isTargetCovered(super.getX() - delta_x, super.getY() - delta_y)
				&& World.isTargetCovered(super.getX(), super.getY())) {
			// do nothing
		}
		// check if a block has moved to the target tile for the first time then
		// increase the count if the number of targets covered
		else if (World.isTargetCovered(super.getX(), super.getY())) {
			Loader.setNumberOfTargetsCovered(Loader.getNumberOfTargetsCovered() + 1);
		}
		// check if a block tile was previously covering a target and is not covering
		// now then decrease the count of the number of targets covered
		else if (World.isTargetCovered(super.getX() - delta_x, super.getY() - delta_y)
				&& !World.isTargetCovered(super.getX(), super.getY())) {
			Loader.setNumberOfTargetsCovered(Loader.getNumberOfTargetsCovered() - 1);
		}

	}
}