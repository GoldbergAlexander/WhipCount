import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.File;
import java.util.ResourceBundle;


/**
 * Created by Alexander Goldberg (alexander.goldberg25@uga.edu) on 4/23/15.
 */
public class Controller implements Initializable {


    @FXML
    protected Canvas displayCanvas;


    @FXML
    protected BorderPane borderPane;

    @FXML
    protected Pane graphicsPane;

    @FXML
    protected Button infoWindowExit;

    @FXML
    protected TextField infoWindowName;

    @FXML
    protected TextArea infoWindowNotes;

    @FXML
    protected Pane infoWindow;

    @FXML
    protected Label infoWindowVotingLabel;

    @FXML
    protected ToggleGroup PartyGroup;

    @FXML
    protected RadioButton radioRepublican, radioDemocrat, radioModerate;

    protected String fileSaveLocation = "data.csv";

    protected Representative tempRep;

    @FXML
    protected void menuLoad() {
        File file = new File(fileSaveLocation);
        Representative[] list = new FileReader(file).readFile(displayCanvas);
        for (int i = 0; i < list.length; i++) {
            Register.add(list[i]);
        }
    }

    @FXML
    protected void menuImport() {
        File file = new File(fileSaveLocation);
        Representative[] tmp = new FileReader(file).readFile(displayCanvas);
        for (int i = 0; i < tmp.length; i++) {
            tmp[i].setStance(tmp[i].getStance());
            tmp[i].setY(i * 35);
        }

        for (int i = 0; i < tmp.length; i++) {

            Register.add(tmp[i]);
        }
        Draw.reset(displayCanvas);

    }

    @FXML
    protected void menuClear() {
        Register.clear();
        Draw.reset(displayCanvas);
    }

    @FXML
    protected void menuSave() {
        System.out.println("DEBUG:Saving");
        fileSaveLocation = "newCSV.csv";
        File file = new File(fileSaveLocation);
        new FileWriter(file).write(Register.getList());
    }

    @FXML
    protected void menuPoll() {

    }

    @FXML
    protected void menuAdd() {
        Register.add(new Representative(displayCanvas));
    }


    @FXML
    protected void canvasClicked(MouseEvent event) {
        if (event.getClickCount() == 2) {
            Representative temp;
            temp = Register.getRepresentative(event.getX(), event.getY());
            if (temp != null) {
                infoWindow.setMouseTransparent(false);
                infoWindow.setOpacity(1);
                infoWindowName.setText(temp.getName());
                switch (temp.getParty()) {
                    case DEMOCRAT:
                        PartyGroup.selectToggle(radioDemocrat);
                        break;
                    case REPUBLICAN:
                        PartyGroup.selectToggle(radioRepublican);
                        break;
                    case MODERATE:
                        PartyGroup.selectToggle(radioModerate);
                        break;

                }
                infoWindowVotingLabel.setText(temp.getStance().toString());
                tempRep = temp;
            }
        }
    }

    @FXML
    protected void infoWindowExit(ActionEvent event) {
        infoWindow.setOpacity(0);
        infoWindow.setMouseTransparent(true);
        tempRep.setNotes(infoWindowNotes.getText());
        tempRep.setName(infoWindowName.getText());

        if (PartyGroup.getSelectedToggle() == radioDemocrat) {
            tempRep.setParty(Party.DEMOCRAT);
        } else if (PartyGroup.getSelectedToggle() == radioModerate) {
            tempRep.setParty(Party.MODERATE);
        } else if (PartyGroup.getSelectedToggle() == radioRepublican) {
            tempRep.setParty(Party.REPUBLICAN);
        }

        Draw.reset(displayCanvas);
    }

    @FXML
    protected void canvasDragged(MouseEvent event) {
        Representative temp;
        temp = Register.getRepresentativeOnDrag(event.getX(), event.getY());
        if (temp != null) {
            temp.setX(event.getX());
            temp.setY(event.getY());
            Draw.reset(displayCanvas);
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
        infoWindowInit();

    }

    private void infoWindowInit() {
        infoWindow.setOpacity(0);
        infoWindow.setMouseTransparent(true);
        infoWindow.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, new CornerRadii(3.0), new Insets(0))));
        infoWindow.setEffect(new DropShadow());
        PartyGroup.selectToggle(radioModerate);
    }


}
