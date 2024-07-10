package model.objectModel.projectiles;

import data.Constants;
import utils.Vector;

public class SlaughterBulletModel extends EpsilonBulletModel {
    public SlaughterBulletModel(Vector position, Vector direction, String id) {
        super(position, direction, id);
        damage = Constants.SLAUGHTER_DAMAGE;
    }

    @Override
    public double getRadios() {
        return Constants.SLAUGHTER_BULLET_RADIOS;
    }


}
