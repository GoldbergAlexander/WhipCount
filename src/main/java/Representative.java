/**
 * Created by Alexander Goldberg (alexander.goldberg25@uga.edu) on 4/25/15.
 */

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

public class Representative {
    private double x, y, width, height, radius;
    private Canvas canvas;
    private GraphicsContext gc;
    private Color color;
    private String name;


    Representative(Canvas canvas) {
        this(canvas, "Default", Color.GREY);
    }

    Representative(Canvas canvas, String name, Color color) {
        this.canvas = canvas;
        gc = this.canvas.getGraphicsContext2D();
        width = 150;
        height = 30;
        radius = 3;
        x = canvas.getWidth() / 2 - (width / 2); //Center;
        y = 50; //Arbitrary //TODO Check for existing reps in area
        this.name = name;
        this.color = color;
        draw(); //Draw to board
    }

    public void draw() {
        gc.setFill(Color.DARKGREY);
        gc.setStroke(color);
        gc.setEffect(new DropShadow());
        gc.strokeRoundRect(x, y, width, height, radius, radius);
        gc.fillRoundRect(x, y, width, height, radius, radius);
        gc.setEffect(null);
        gc.setStroke(Color.BLACK);
        gc.strokeText(name, x, y + height);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        if (x >= 0 && x <= canvas.getWidth() - width) {
            this.x = x;
        }
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        if (y >= 0 && y <= canvas.getHeight() - height) {
            this.y = y;
        }
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public String getName() {
        return name;
    }

}
