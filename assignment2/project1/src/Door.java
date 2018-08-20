import org.newdawn.slick.Input;

public class Door extends Tiles {
	private static boolean shouldNotBlock = false;
	private float x;
	private float y;
	private int counter=0;
	public Door(float x, float y) {
		super("res/door.png", x, y);
		shouldNotBlock = false; /*
								 * this indicates that when the door has been destroyed it / should not block
								 * the movement of the other sprites
								 */
		this.x=x;
		this.y=y;
	}

	public void update(Input input, int delta) {
		/*
		 * check if the switch is covered by a block tile then stop rendering the door
		 */
		if (Switch.getSwitchIsCovered()) {
			
			super.setX(0);
			super.setY(0);
			super.setIsRendering(false);
			shouldNotBlock = true;
			Switch.setSwitchIsCovered(false);
			
			
			
		}

	}

	public static boolean getShouldNotBlock() {
		return shouldNotBlock;
	}

	public static void setShouldNotBlock(boolean shouldNotBlock) {
		Door.shouldNotBlock = shouldNotBlock;
	}
}