package model.logics.aoe;

import model.objectModel.ObjectModel;

import java.util.ArrayList;

public abstract class AOE {
    protected double damage;
    abstract void dealDamage(ArrayList<ObjectModel> models);
}
