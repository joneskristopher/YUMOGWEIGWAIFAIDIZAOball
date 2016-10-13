package com.example.student.yumogweigwaifaidizaoball;

import java.util.Random;

public class Predictions {
    private static Predictions predictions;
    private String[] answers;
    private Random random = new Random();
    private int rnd;

    private Predictions(){
        answers = new String[]{
            "come back later",
            "do it now",
            "dont do it",
            "do it later",
            "do it tomorrow",
            "dont ask me"
        };
    }
    public static Predictions get(){
        if(predictions == null){
            predictions = new Predictions();
        }
        return predictions;
    }
    public String getPrediction(){
        rnd = random.nextInt(answers.length);
        return answers[rnd];
    }

}
