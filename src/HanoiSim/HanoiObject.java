package HanoiSim;

public abstract class HanoiObject {
    private static int totalHanoiObjects;
    static final int TOTALNUMTOWER = 3;

    abstract String display();

    public String getDisplay(){
        return display();
    }

    static void increaseHanoiObjects(){
        totalHanoiObjects++;
    }

    public static int getHanoiObjectCount(){
        return totalHanoiObjects;
    }
}
