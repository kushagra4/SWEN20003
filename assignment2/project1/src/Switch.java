import org.newdawn.slick.Input;

public class Switch extends Tiles {
	private static boolean switchIsCovered = false;

	public Switch(float x, float y) {
		super("res/switch.png", x, y);
	}

	public void update(Input input, int delta) {
		/* check if the switch is covered by a block tile */
		if (World.isBlock(super.getX(), super.getY())) {
			switchIsCovered = true;
		}
	}

	public static boolean getSwitchIsCovered() {
		return switchIsCovered;
	}

	public static void setSwitchIsCovered(boolean switchIsCovered) {
		Switch.switchIsCovered = switchIsCovered;
	}
}