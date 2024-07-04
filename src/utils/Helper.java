package utils;


import data.Constants;
import model.objectModel.frameModel.FrameModel;

import java.util.ArrayList;
import java.util.Random;

public class Helper {
    private static final Random random = new Random();
    public static ArrayList<Double> giveMaxMin(ArrayList<Double> a){
        ArrayList<Double> answer = new ArrayList<>();
        double min = a.get(0);
        double max = a.get(0);
        for (int i = 0 ;i < a.size() ;i++){
            if (a.get(i) < min){
                min = a.get(i);
            }
            if (a.get(i) > max){
                max = a.get(i);
            }
        }
        answer.add(min);
        answer.add(max);
        return answer;
    }

    public static Double PointDistanceWithLine(Vector vert1 ,Vector vert2 ,Vector point){
        Vector u = Math.VectorWithSize(Math.VectorAdd(vert2 ,Math.ScalarInVector(-1 ,vert1)) ,1);
        Vector a = Math.ScalarInVector(0.5 ,Math.VectorAdd(vert1 ,vert2));
        Vector aPoint = Math.VectorAdd(Math.ScalarInVector(-1 ,point) ,a);
        return Math.VectorSize(Math.CrossProduct(u ,aPoint));
    }

    public static String RandomStringGenerator(int n){
        byte[] str = new byte[n];
        new Random().nextBytes(str);
        return new String(str);
    }

    public static Vector createRandomPosition(FrameModel frameModel) {
        int x = random.nextInt(
                (int) frameModel.getPosition().x ,
                (int) frameModel.getPosition().x + frameModel.getSize().width
        );
        int y = random.nextInt(
                (int) frameModel.getPosition().y ,
                (int) frameModel.getPosition().y + frameModel.getSize().height
        );
        return new Vector(x ,y);
    }
}
