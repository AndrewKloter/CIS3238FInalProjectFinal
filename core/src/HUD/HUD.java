package HUD;

import java.util.concurrent.TimeUnit;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import FarmSimulator.StartParams;
import Entity.Player;
import Util.Res;

public class HUD {

    private Player player;

    private BitmapFont font;
    private float globalTime;

    private TextureRegion pixel;
    private int height = 70;
    private Color bgColor = new Color(0.6f, 0.5f, 0.2f, 1);
    private Color borderColor = new Color(0.3f, 0.2f, 0.1f, 1);

    private StringBuilder builder;

    public HUD(Player player) {
        this.player = player;
        font = Res.i().getFont("font");
        pixel = new TextureRegion(Res.i().getTexture("pixel"));
        builder = new StringBuilder();
    }

    public void setGlobalTime(float globalTime) {
        this.globalTime = globalTime;
    }

    public void render(SpriteBatch sb) {
        System.out.println("IN RENDER OF HUD");
        sb.setColor(bgColor);
        sb.draw(pixel, 0, 0, StartParams.WIDTH, height);
        sb.setColor(borderColor);
        sb.draw(pixel, 0, height, StartParams.WIDTH, 1);
        Player.Action action = player.getAction();
        if (action != null) {
            font.draw(sb, action.toString(), 10, StartParams.HEIGHT - 25);
        }
        int seconds = (int) globalTime;
        int day = (int) TimeUnit.SECONDS.toDays(seconds);
        long hours = TimeUnit.SECONDS.toHours(seconds) - (day * 24);
        long minute = TimeUnit.SECONDS.toMinutes(seconds) - (TimeUnit.SECONDS.toHours(seconds) * 60);
        long second = TimeUnit.SECONDS.toSeconds(seconds) - (TimeUnit.SECONDS.toMinutes(seconds) * 60);
        
        builder.setLength(0);
        builder.append(day).append(" days, ");
        if (hours < 10) {
            builder.append("0");
        }
        builder.append(hours).append(":");
        if (minute < 10) {
            builder.append("0");
        }
        builder.append(minute).append(":");
        if (second < 10) {
            builder.append("0");
        }
        builder.append(second);
        font.draw(sb, builder.toString(), 10, StartParams.HEIGHT - 10);
        font.draw(sb, "Money: $" + player.getMoney(), 200, 65);
        font.draw(sb, "# of Seeds: " + player.getNumSeeds(), 200, 50);
        font.draw(sb, "# of Crops: " + player.getNumCrops(), 200, 35);
        font.draw(sb, "Total profit: $" + player.getTotalMoney(), 200, 20);
        font.draw(sb, StartParams.DayLimit + " day goal: $" + StartParams.MoneyToReach + " total profit", 10, 85);
        font.draw(sb, "H for help", StartParams.WIDTH - 100, StartParams.HEIGHT - 10);
        font.draw(sb, "S for shop", StartParams.WIDTH - 100, StartParams.HEIGHT - 25);
    }

}
