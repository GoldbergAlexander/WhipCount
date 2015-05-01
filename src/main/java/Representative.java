/**
 * Created by Alexander Goldberg (alexander.goldberg25@uga.edu) on 4/25/15.
 */

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

public class Representative {
    private double x, y, width = 150, height = 20, radius = 1;
    private Canvas canvas;
    private GraphicsContext gc;
    private Color color;
    private String name;
    private String notes;
    private Stance stance;
    private Party party;


    Representative(Canvas canvas) {
        this(canvas, "Default", Party.MODERATE, canvas.getWidth() / 2 - 150 / 2, 30, "", Stance.ABS);
    }

    Representative(Canvas canvas, String name, Party party, double x, double y, String notes, Stance stance) {
        this.canvas = canvas;
        gc = this.canvas.getGraphicsContext2D();
        this.x = x;
        this.y = y;
        this.name = name;
        this.notes = notes;
        this.stance = stance;
        this.party = party;
        if (party == Party.DEMOCRAT) {
            this.color = Color.DARKBLUE;
        } else if (party == Party.REPUBLICAN) {
            this.color = Color.DARKRED;
        } else if (party == Party.MODERATE) {
            this.color = Color.PURPLE;
        }
        draw(); //Draw to board
    }

    public void draw() {
        gc.setGlobalAlpha(.9);
        gc.setLineWidth(1);
        gc.setFill(color);
        gc.setStroke(Color.DARKGREY);
        gc.setEffect(new DropShadow());
        gc.strokeRoundRect(x, y, width, height, radius, radius);
        gc.fillRoundRect(x, y, width, height, radius, radius);
        gc.setEffect(null);
        gc.setStroke(Color.DARKGREY);
        gc.setFill(Color.DARKGREY);
        gc.fillText(name, x + 4, y + height - 5); //TODO make Dynamic
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        if (x + width > canvas.getWidth()) {
            x = canvas.getWidth() - width;
        }
        if (x >= 0 && x <= canvas.getWidth() - width) {
            this.x = x;
            x = x + (width / 2);
            if (x > canvas.getWidth() * .80) {
                stance = Stance.NAY;
            } else if (x < canvas.getWidth() * .20) {
                stance = Stance.YAY;
            } else if (x > canvas.getWidth() * .40 && x < canvas.getWidth() * .60) {
                stance = Stance.ABS;
            }
        }
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        if (y > canvas.getHeight() - height) {
            y = canvas.getHeight() - height;
        }
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

    public void setName(String string) {
        name = string;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String string) {
        notes = string;
    }

    @Override
    public String toString() {
        String tmp;
        tmp = "X=" + x + ",";
        tmp = tmp + "Y=" + y + ",";
        tmp = tmp + "NAME=" + name + ",";
        tmp = tmp + "NOTES=" + notes + ",";
        tmp = tmp + "COLOR=" + color.toString() + ",";
        tmp = tmp + "PARTY=" + party.toString() + ",";
        tmp = tmp + "STANCE=" + stance.toString() + ",";
        return tmp;
    }

    public Stance getStance() {
        return stance;
    }

    public void setStance(Stance stance) {
        this.stance = stance;
        if (stance == Stance.ABS) {
            x = canvas.getWidth() / 2 - width / 2; // set to the center
        } else if (stance == Stance.YAY) {
            x = 2; // set to the left
        } else if (stance == Stance.NAY) {
            x = canvas.getWidth() - width - 2; // set to right;
        }
        draw();
    }

    public Party getParty() {
        return party;
    }

    public void setParty(Party party) {
        this.party = party;
        if (this.party == Party.DEMOCRAT) {
            this.color = Color.DARKBLUE;
        } else if (this.party == Party.REPUBLICAN) {
            this.color = Color.DARKRED;
        } else if (this.party == Party.MODERATE) {
            this.color = Color.PURPLE;
        }
        draw(); //Draw to board
    }


}
