
//package project1;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Graphics;

public class Sprite {
	// Used to decide what direction an object is moving
	// Look up enums to find a more elegant solution!
	public static final int DIR_NONE = 0;
	public static final int DIR_LEFT = 1;
	public static final int DIR_RIGHT = 2;
	public static final int DIR_UP = 3;
	public static final int DIR_DOWN = 4;

	private Image image = null;
	private float x;
	private float y;
	private String image_src;
	private Boolean isRendering;

	public Sprite(String image_src, float x, float y) {
		try {
			image = new Image(image_src);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		this.image_src = image_src;
		this.x = x;
		this.y = y;
		this.isRendering = true;
		snapToGrid();
	}

	public float getX() {
		return this.x;
	}

	public float getY() {
		return this.y;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public String getImage_src() {
		return this.image_src;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public void update(Input input, int delta) throws SlickException {

	}

	public void render(Graphics g) {
		image.drawCentered(x, y);
	}

	// Forces this sprite to align to the grid
	public void snapToGrid() {
		x /= App.TILE_SIZE;
		y /= App.TILE_SIZE;
		// x = Math.round(x);
		// y = Math.round(y);
		x *= App.TILE_SIZE;
		y *= App.TILE_SIZE;
	}

	
	/**
	 * This function checks if there is a wall or not where the sprite is about to move
	 * @param dir stores the direction of the movement of the sprite
	 * @return boolean if there is a wall or not where the sprite is about to move 
	 */
	public boolean moveToDest(int dir) {
		float speed = App.TILE_SIZE;
		// Translate the direction to an x and y displacement
		float delta_x = 0, delta_y = 0;
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

		if (!Loader.isBlocked(getX() + delta_x, getY() + delta_y)) {
			return true;
		} else {
			return false;
		}
	}

	public void setIsRendering(Boolean isRendering) {
		this.isRendering = isRendering;
	}

	public boolean getIsRendering() {
		// TODO Auto-generated method stub
		return this.isRendering;
	}

}
