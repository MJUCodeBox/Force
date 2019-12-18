package Animation;

public class Time {

    public static int fps;
    public static double delta;
    
    private static long frameStartTime = System.nanoTime();
    private static long frameLastTime = System.nanoTime();
    private static int framesCount;
    
    public static void update() {
        long currentFrameTime = System.nanoTime();
        
        // check FPS
        if (currentFrameTime - frameStartTime >= 1000000000) {//1sec
            fps = framesCount;
            framesCount = 0;
            frameStartTime = currentFrameTime;
        } else {
            framesCount++;
        }
        
        delta = currentFrameTime - frameLastTime;
        frameLastTime = currentFrameTime;
    }
    
}
