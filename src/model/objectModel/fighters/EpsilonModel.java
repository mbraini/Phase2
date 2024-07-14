package model.objectModel.fighters;


import controller.configs.Configs;
import controller.enums.ModelType;
import data.Constants;
import model.collision.Collision;
import model.interfaces.*;
import model.objectModel.FighterModel;
import utils.Math;
import utils.Vector;

import java.util.ArrayList;

public class EpsilonModel extends FighterModel implements MoveAble, IsCircle, HasVertices , ImpactAble {
    private static ArrayList<EpsilonVertexModel> vertices = new ArrayList<>();
    private boolean isImpacted = false;
    private int epsilonBulletDamage;
    private int epsilonDamageOnCollision;
    private int chanceOfSurvival;
    private int lifeSteal;
    public EpsilonModel(Vector position , String id){
        this.position = position;
        this.velocity = new Vector();
        this.acceleration = new Vector(0 ,0);
        this.id =  id;
        this.HP = 100;
        this.epsilonBulletDamage = Constants.INITIAL_EPSILON_DAMAGE;
        this.meleeAttack = Constants.INITIAL_EPSILON_DAMAGE;
        this.isSolid = true;
        type = ModelType.epsilon;
        vertices = new ArrayList<>();
    }

    @Override
    public void move() {
        velocity = Math.VectorAdd(velocity ,Math.ScalarInVector(Constants.UPS ,acceleration));
        double xMoved = ((2 * velocity.x - acceleration.x * Constants.UPS) / 2) * Constants.UPS;
        double yMoved = ((2 * velocity.y - acceleration.y * Constants.UPS) / 2) * Constants.UPS;
        setPosition(position.x + xMoved ,position.y + yMoved);
        ((HasVertices) this).UpdateVertices(xMoved ,yMoved ,omega);
        checkMaxSpeed();
    }

    void checkMaxSpeed(){
        double currentSpeed = java.lang.Math.sqrt(java.lang.Math.pow(velocity.x ,2)
                + java.lang.Math.pow(velocity.y ,2));
        assert currentSpeed != 0;
        if (currentSpeed > Configs.EPSILON_MAX_SPEED){
            setVelocity(
                    getVelocity().x * Configs.EPSILON_MAX_SPEED / currentSpeed ,
                    getVelocity().y * Configs.EPSILON_MAX_SPEED / currentSpeed
            );
        }
    }

    @Override
    public double getRadios() {
        return Constants.EPSILON_DIMENSION.height / 2d;
    }

    @Override
    public Vector getCenter() {
        return position;
    }

    public void addVertex(){
        theta = 0;
        vertices = new ArrayList<>();
        double theta = 2 * java.lang.Math.PI / Configs.VERTICES;
        for (int i = 0; i < Configs.VERTICES ;i++){
            vertices.add(new EpsilonVertexModel(this ,theta * i));
        }
    }

    @Override
    public void UpdateVertices(double xMoved ,double yMoved ,double theta) {
        for (int i = 0 ;i < vertices.size() ;i++){
            vertices.get(i).setTheta(vertices.get(i).getTheta() + theta);
            vertices.get(i).Update();
        }
    }

    public static ArrayList<EpsilonVertexModel> getVertices(){
        return vertices;
    }
    public void Rotate(double theta){
        UpdateVertices(0 ,0 ,theta - this.theta);
        this.theta = theta;
    }

    @Override
    public boolean isImpacted() {
        return isImpacted;
    }

    @Override
    public void setImpacted(boolean impact) {
        isImpacted = impact;
    }

    public static void setVertices(ArrayList<EpsilonVertexModel> vertices) {
        EpsilonModel.vertices = vertices;
    }

    @Override
    public void die() {
        //////////////// endGame
        //////todo
    }

    public void meleeAttack(EnemyModel enemyModel){
        enemyModel.setHP(enemyModel.getHP() - epsilonDamageOnCollision);
        if (!enemyModel.isVulnerableToEpsilonMelee())
            return;
        for (EpsilonVertexModel vertex : vertices){
            if (Collision.IsColliding(vertex ,enemyModel)){
                enemyModel.setHP(enemyModel.getHP() - meleeAttack);
                setHP(getHP() + lifeSteal);
                return;
            }
        }
    }

    public int getEpsilonBulletDamage() {
        return epsilonBulletDamage;
    }

    public void setEpsilonBulletDamage(int epsilonBulletDamage) {
        this.epsilonBulletDamage = epsilonBulletDamage;
    }

    public int getEpsilonDamageOnCollision() {
        return epsilonDamageOnCollision;
    }

    public void setEpsilonDamageOnCollision(int epsilonDamageOnCollision) {
        this.epsilonDamageOnCollision = epsilonDamageOnCollision;
    }

    public int getChanceOfSurvival() {
        return chanceOfSurvival;
    }

    public void setChanceOfSurvival(int chanceOfSurvival) {
        this.chanceOfSurvival = chanceOfSurvival;
    }

    public int getLifeSteal() {
        return lifeSteal;
    }

    public void setLifeSteal(int lifeSteal) {
        this.lifeSteal = lifeSteal;
    }
}
