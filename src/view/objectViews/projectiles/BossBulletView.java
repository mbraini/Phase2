package view.objectViews.projectiles;

import data.Constants;
import utils.Vector;

import java.awt.*;

public class BossBulletView extends BulletView{

    public BossBulletView(Vector position ,String id){
        this.position = position;
        this.id = id;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        g2d.fillOval(
                (int) (position.x - Constants.BOSS_BULLET_RADIOS) + Constants.SCREEN_SIZE.width,
                (int) (position.y - Constants.BOSS_BULLET_RADIOS) + Constants.SCREEN_SIZE.height,
                (int) Constants.BOSS_BULLET_RADIOS * 2,
                (int) Constants.BOSS_BULLET_RADIOS * 2
        );
    }
}
