package view.objectViews.projectiles;

import constants.SizeConstants;
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
                (int) (position.x - SizeConstants.BOSS_BULLET_RADIOS) + SizeConstants.SCREEN_SIZE.width,
                (int) (position.y - SizeConstants.BOSS_BULLET_RADIOS) + SizeConstants.SCREEN_SIZE.height,
                (int) SizeConstants.BOSS_BULLET_RADIOS * 2,
                (int) SizeConstants.BOSS_BULLET_RADIOS * 2
        );
    }
}
