package game.first.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import game.first.GameMain;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = GameMain.WIDTH;
		config.height = GameMain.HEIGHT;
		config.title = GameMain.TITLE;

		new LwjglApplication(new GameMain(), config);
	}
}
