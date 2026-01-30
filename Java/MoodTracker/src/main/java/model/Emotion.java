package model;


public enum Emotion {
    VERYSAD, SAD, NEUTRAL, HAPPY, VERYHAPPY;

    public static Emotion fromInt(int i) {
        Emotion[]values=Emotion.values();
        if(i<0||i>=values.length)return null;
        return values[i];
    }
    public static int toInt(Emotion e) {
        return e==null?-1:e.ordinal();
    }
}
