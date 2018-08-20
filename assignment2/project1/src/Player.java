
//package project1;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;


public class Player extends Units {

	private static int direction;
	private static boolean moveBlock;
	private float delta_x = 0;
	private float delta_y = 0;
	private static boolean moveRogueAndMage = false;

	public Player(float x, float y) {
		super("res/player_left.png", x, y);
	}

	@Override
	public void update(Input input, int delta) {
		int dir = DIR_NONE;

		if (input.isKeyPressed(Input.KEY_LEFT)) {
			dir = DIR_LEFT;
		} else if (input.isKeyPressed(Input.KEY_RIGHT)) {
			dir = DIR_RIGHT;
		} else if (input.isKeyPressed(Input.KEY_UP)) {
			dir = DIR_UP;
		} else if (input.isKeyPressed(Input.KEY_DOWN)) {
			dir = DIR_DOWN;
		}

		// Move to our destination
		// moveToDest(dir);
		direction = dir;

		if (dir != DIR_NONE) {
			World.setCountMoves((World.getCountMoves()) + 1);
			nextMove(dir);
			moveRogueAndMage = true;
		}
		/*
		 * This if checks if there another unit where the player is then it reloads the
		 * level
		 */
		if (World.isUnit(super.getX(), super.getY())) {
			World.reloadLevel();
		}
	}

	
	
	/**
	 * This function moves the player if the move is valid
	 * @param dir stores the direction the player is to be moved in
	 */
	public void nextMove(int dir) {

		delta_x = 0;
		delta_y = 0;
		setMovement(dir);
		if (moveToDest(dir)
				&& (!Loader.isCrackedWall(getX() + delta_x, getY() + delta_y) || CrackedWall.getShouldNotBlock())
				&& (!Loader.isDoor(getX() + delta_x, getY() + delta_y) || Door.getShouldNotBlock())) {

			if ((!World.isBlock(super.getX() + delta_x, super.getY() + delta_y))) {
				super.setX(super.getX() + delta_x);
				super.setY(super.getY() + delta_y);
				Mage.sendPosition(super.getX() - delta_x, super.getY() - delta_y);
			}

			else if (!World.isBlock(super.getX() + delta_x + delta_x, super.getY() + delta_y + delta_y)
					&& !Loader.isBlocked(super.getX() + delta_x + delta_x, super.getY() + delta_y + delta_y)) {
				moveBlock = true;
				super.setX(super.getX() + delta_x);
				super.setY(super.getY() + delta_y);
				Mage.sendPosition(super.getX() - delta_x, super.getY() - delta_y);
			}
		}
	}

	
	/**
	 * sets the value of delta_x and delta_y according to 'dir' 
	 * @param dir stores the direction the player is to be moved in
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

	public static boolean getMoveRogueAndMage() {
		return moveRogueAndMage;
	}

	public static void setMoveRogueAndMage(boolean moveRogueAndMage) {
		Player.moveRogueAndMage = moveRogueAndMage;
	}

	public static int getDirection() {
		return direction;
	}

	public static void setDirection(int direction) {
		Player.direction = direction;
	}

	public static boolean getMoveBlock() {
		return moveBlock;
	}

	public static void setMoveBlock(boolean moveBlock) {
		Player.moveBlock = moveBlock;
	}

}
