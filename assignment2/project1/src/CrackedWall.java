
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class CrackedWall extends Tiles {
	private int MAXTIME = 400; // maximum time
	private int renderingTime = 0; // keeps a check of how long the explosion should render
	private static boolean shouldNotBlock = false; /*
													 * this indicates that when the cracked wall has been destroyed it /
													 * should not block the movement of the other sprites
													 */

	public CrackedWall(float x, float y) {
		super("res/cracked_wall.png", x, y);
		shouldNotBlock = false;
	}

	public void update(Input input, int delta) throws SlickException {
		/*
		 * Check if the TNT is moved onto the cracked wall then render the explosion
		 * instead of the cracked wall for the specific time period
		 */
		if (TNT.getIsExplosion()) {
			super.setImage(new Image("res/explosion.png"));
			renderingTime += delta;
			if (renderingTime > MAXTIME) {
				super.setX(0);
				super.setY(0);
				super.setIsRendering(false);
				shouldNotBlock = true;
				TNT.setISExplosion(false);
			}

		}
	}

	public static boolean getShouldNotBlock() {
		return shouldNotBlock;
	}

	public static void setShouldNotBlock(boolean shouldNotBlock) {
		CrackedWall.shouldNotBlock = shouldNotBlock;
	}
}
