/**
 * Created by Alexander Goldberg (alexander.goldberg25@uga.edu) on 4/25/15.
 */

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.text.DecimalFormat;

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
        double BLOCK_SIZE = 10;
        gc.setLineWidth(.5);
        gc.setStroke(Color.GREY);
        for (double i = BLOCK_SIZE; i < width; i = i + BLOCK_SIZE) {
            gc.strokeLine(i, 0, i, height);
        }
        for (double i = BLOCK_SIZE; i < height; i = i + BLOCK_SIZE) {
            gc.strokeLine(0, i, width, i);
        }
    }

    public static void stats(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        double var;
        double yay, nay;
        try {
            yay = Register.getYAYCount();
            nay = Register.getNAYCount();
            var = yay / (yay + nay);
            gc.setGlobalAlpha(.9);
            gc.fillText("Vote: " + new DecimalFormat("#.###").format(var), canvas.getWidth() - 60, canvas.getWidth() - 85);
        } catch (ArithmeticException e) {

        }

    }

    public static void label(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.save();
        gc.setFill(Color.WHITE);
        gc.setStroke(Color.BLACK);
        gc.setEffect(new DropShadow());
        gc.setGlobalAlpha(.3);
        gc.setFont(new Font(50));

        if (Register.majority()) {
            gc.setGlobalAlpha(.8);
        }
        gc.strokeText("YEA", 100, canvas.getHeight() / 2 - 50);
        gc.fillText("YEA", 100, canvas.getHeight() / 2 - 50);
        gc.setGlobalAlpha(.3);

        if (!Register.majority()) {
            gc.setGlobalAlpha(.8);
        }
        gc.strokeText("NAY", canvas.getWidth() - 200, canvas.getHeight() / 2 - 50);
        gc.fillText("NAY", canvas.getWidth() - 200, canvas.getHeight() / 2 - 50);


        gc.restore();

    }

    public static void reset(Canvas canvas) {
        fill(canvas);
        grid(canvas);
        stats(canvas);
        label(canvas);
        Representative[] representatives = Register.getList();
        for (int i = 0; i < representatives.length; i++) {
            representatives[i].draw();
        }
    }


}
