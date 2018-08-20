import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

//package project1;

public class Stone extends Blocks {
	private float delta_x = 0;
	private float delta_y = 0;

	public Stone(float x, float y) {
		super("res/stone.png", x, y);
	}

	public void update(Input input, int delta) {

		// check if the player has pushed the stone then move it in the rogue's
		// direction
		if (Player.getMoveBlock()) {
			move(Player.getDirection());
		}
		// check if the rogue has pushed the stone then move it in the rogues's
		// direction
		if (Rogue.getMoveBlock()) {
			move(Rogue.getDirection());
		}
	}

	/**
	 * moves the stone when the player pushes it and the movement is possible
	 * @param delta stores the time since the last update
	 */
	public void move(int dir) {
		float speed = App.TILE_SIZE;
		// Translate the direction to an x and y displacement
		delta_x = 0;
		delta_y = 0;
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

		if ((Rogue.getMoveBlock() || Player.getMoveBlock()) && moveToDest(dir)
				&& World.getImage_src().equals("res/stone.png") && super.getX() == World.getXCoordinate()
				&& super.getY() == World.getYCoordinate()) {

			super.setX(super.getX() + delta_x);
			super.setY(super.getY() + delta_y);

			Player.setMoveBlock(false);
			Rogue.setMoveBlock(false);
			coverTarget();// check if the stone is covering a target tile
		}

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
