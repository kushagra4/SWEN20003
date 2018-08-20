import org.newdawn.slick.Input;

public class Skeleton extends Units {
	private int direction=Sprite.DIR_UP;
	private static float delta_x;
	private static float delta_y;
	private int time=0;
	public Skeleton(float x, float y) {
		super("res/skull.png", x, y);
	}

	
	public void update(Input input, int delta) {
		
		
		
	    setMovement(direction);
	    /*check if there is no wall or blocked tile then keep moving the skeleton each second else change its direction*/
		if(moveToDest(direction) && (!World.isBlock(super.getX() + delta_x, super.getY() + delta_y)))
		{
			time+=delta;
			if(time>1000) {
				super.setX(super.getX() + delta_x);
				super.setY(super.getY() + delta_y);
				time=0;
			}
			
		}
		else
		{
			switch(direction)
			{
			case DIR_UP: 
				direction=DIR_DOWN;
				break;
			case DIR_DOWN:
				direction=DIR_UP;
				break;
			
			}
		}
		
	}
	
	/**
	 * sets the value of delta_x and delta_y according to 'dir' 
	 * @param dir stores the direction the skeleton is to be moved in
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
}