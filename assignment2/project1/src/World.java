
//package project1;

import java.util.ArrayList;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;


public class World {
	private static ArrayList<Sprite> sprites;
	private static int LAST_LEVEL=5;
	private static int FIRST_LEVEL=0;
	private static String image_src;
	private static float xCoordinate;
	private static float yCoordinate;
	private static int countMoves = 0;

	private static int currentLevel = 4;

	public World() {
		sprites = Loader.loadSprites("res/levels/4.lvl");

	}

	public void update(Input input, int delta) throws SlickException {
		for (Sprite sprite : sprites) {
			if (sprite != null) {
				sprite.update(input, delta);
			}
		}

		loadNextLevel();
		if (input.isKeyPressed(Input.KEY_R)) {
			reloadLevel();
		}
	}

	public void render(Graphics g) {
		for (Sprite sprite : sprites) {
			if (sprite != null && sprite.getIsRendering()) {

				sprite.render(g);
			}
		}
		g.drawString("Moves: " + countMoves, 0, 0);
	}

	
	/**
	 * This function checks if there is a block tile at the given x,y coordinate
	 * @param x stores the x coordinate of the sprite
	 * @param y stores the y coordinate of the sprite
	 * @return boolean if there is a block tile at that position
	 */
	public static boolean isBlock(float x, float y) {
		for (int i = 0; i < sprites.size(); i++) {

			if (sprites.get(i).getX() == x && sprites.get(i).getY() == y
					&& (sprites.get(i).getImage_src().equals("res/stone.png")
							|| sprites.get(i).getImage_src().equals("res/tnt.png")
							|| sprites.get(i).getImage_src().equals("res/ice.png")))

			{
				image_src = sprites.get(i).getImage_src();
				xCoordinate = sprites.get(i).getX();
				yCoordinate = sprites.get(i).getY();
				return true;
			}

		}
		return false;
	}

	
	
	/**
	 * This function checks if there is a unit sprite at the given x,y coordinate
	 * @param x stores the x coordinate of the sprite
	 * @param y stores the y coordinate of the sprite
	 * @return boolean if there is a unit at that position
	 */
	public static boolean isUnit(float x, float y) {
		for (int i = 0; i < sprites.size(); i++) {
			if (sprites.get(i).getX() == x && sprites.get(i).getY() == y
					&& (sprites.get(i).getImage_src().equals("res/rogue.png")
							|| sprites.get(i).getImage_src().equals("res/mage.png")
							|| sprites.get(i).getImage_src().equals("res/skull.png"))) {
				return true;
			}
		}
		return false;
	}

	/*  */
	
	/**
	 * This function checks if all the targets in the level are covered
	 * @param x stores the x coordinate of the sprite
	 * @param y stores the y coordinate of the sprite
	 * @return boolean if all targets are covered 
	 */
	public static boolean isTargetCovered(float x, float y) {
		for (int i = 0; i < sprites.size(); i++) {
			if (sprites.get(i).getX() == x && sprites.get(i).getY() == y
					&& sprites.get(i).getImage_src().equals("res/Target.png")) {
				return true;
			}

		}
		return false;
	}

	
	/**
	 * This function loads the next level
	 */
	public static void loadNextLevel() {
		if (Loader.getNumberOfTargets() == Loader.getNumberOfTargetsCovered()) {

			if (currentLevel <LAST_LEVEL) {
				currentLevel++;
				sprites = Loader.loadSprites("res/levels/" + currentLevel + ".lvl");
				Loader.setNumberOfTargetsCovered(0);
			}
		}
	}

	public static void reloadLevel() {
		sprites = Loader.loadSprites("res/levels/" + currentLevel + ".lvl");
	}

	public static float getXCoordinate() {
		return xCoordinate;
	}

	public static void setXCoordinate(float xCoordinate) {
		World.xCoordinate = xCoordinate;
	}

	public static float getYCoordinate() {
		return yCoordinate;
	}

	public static void setYCoordinate(float yCoordinate) {
		World.yCoordinate = yCoordinate;
	}

	public static String getImage_src() {
		return image_src;
	}

	public static int getCountMoves() {
		return countMoves;
	}

	public static void setCountMoves(int countMoves) {
		World.countMoves = countMoves;
	}

}
