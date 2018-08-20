import org.newdawn.slick.Input;

public class TNT extends Blocks {
	private float delta_x = 0;
	private float delta_y = 0;
	private static boolean isExplosion = false;

	public TNT(float x, float y) {
		super("res/tnt.png", x, y);
	}

	public void update(Input input, int delta) {

		move(Player.getDirection());

	}

	/**
	 * moves the TNT when the player pushes it and the movement is possible
	 * @param delta stores the time since the last update
	 */
	public void move(int dir) {
		delta_x = 0;
		delta_y = 0;
		setMovement(dir);
		if (Player.getMoveBlock() && moveToDest(dir) && World.getImage_src().equals("res/tnt.png")) {
			/*
			 * The if checks if the TNT is moving onto a cracked wall then it sets the
			 * explosion to happen
			 */
			if (Loader.isCrackedWall(super.getX() + delta_x, super.getY() + delta_y)) {
				isExplosion = true;
				super.setX(0);
				super.setY(0);
				super.setIsRendering(false);
			}
			super.setX(super.getX() + delta_x);
			super.setY(super.getY() + delta_y);
			Player.setMoveBlock(false);
			coverTarget();// check if the TNT block is covering a target tile
		}
	}

	/**
	 * sets the value of delta_x and delta_y according to 'dir' 
	 * @param dir stores the direction the TNT is to be moved in
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

	public static boolean getIsExplosion() {
		return isExplosion;
	}

	public static void setISExplosion(boolean isExplosion) {
		TNT.isExplosion = isExplosion;
	}

	/**
	 * sets the value of delta_x and delta_y according to 'dir' 
	 * @param dir stores the direction the player is to be moved in
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
