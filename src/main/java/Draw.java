/**
 * Created by Alexander Goldberg (alexander.goldberg25@uga.edu) on 4/25/15.
 */

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Draw {
    public static void fill(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    public static void grid(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        double width = canvas.getWidth();
        double height = canvas.getHeight();
        double BLOCK_SIZE = 20;
        gc.setStroke(Color.GREY);
        for (double i = BLOCK_SIZE; i < width; i = i + BLOCK_SIZE) {
            gc.strokeLine(i, 0, i, height);
        }
        for (double i = BLOCK_SIZE; i < height; i = i + BLOCK_SIZE) {
            gc.strokeLine(0, i, width, i);
        }
    }

    public static void reset(Canvas canvas) {
        fill(canvas);
        grid(canvas);
        Representative[] representatives = Register.getList();
        for (int i = 0; i < representatives.length; i++) {
            representatives[i].draw();
        }
    }


}
