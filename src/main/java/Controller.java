import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.util.ResourceBundle;


/**
 * Created by Alexander Goldberg (alexander.goldberg25@uga.edu) on 4/23/15.
 */
public class Controller implements Initializable {


    @FXML
    protected Canvas displayCanvas;

    @FXML
    protected Pane specialPane;

    @FXML
    protected BorderPane borderPane;

    @FXML
    protected Pane graphicsPane;

    //DEBUG
    @FXML
    protected void canvasClicked(MouseEvent event) {
       /* double x = event.getX();
        double y = event.getY();
        System.out.println("(" + x + "," + y + ")");
        */
        Representative temp;
        temp = Register.getRepresentativeOnDrag(event.getX(), event.getY());
        if (temp != null) {

        }
    }

    @FXML
    protected void canvasDragged(MouseEvent event) {
        Representative temp;
        temp = Register.getRepresentativeOnDrag(event.getX(), event.getY());
        if (temp != null) {
            temp.setX(event.getX());
            temp.setY(event.getY());
            Draw.reset(displayCanvas);
            //temp.draw();
        }
        event.consume();
    }

    @Override
    public void initialize(java.net.URL args0, ResourceBundle args1) {
        Draw.fill(displayCanvas);
        Draw.grid(displayCanvas);
        Register.add(new Representative(displayCanvas));
        Register.add(new Representative(displayCanvas));
        Register.add(new Representative(displayCanvas));


    }

}
