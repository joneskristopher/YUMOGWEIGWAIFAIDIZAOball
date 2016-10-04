package com.example.student.yumogweigwaifaidizaoball;

public class Predictions {
    private static Predictions predictions;
    private String[] answers;
    private Predictions(){
        answers = new String[]{
            "come back later",
            "do it now"
        };
    }
    public static Predictions get(){
        if(predictions == null){
            predictions = new Predictions();
        }
        return predictions;
    }
    public String getPrediction(){
        return answers[1];
    }
}
