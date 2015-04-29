import javafx.scene.canvas.Canvas;

import java.io.*;

/**
 * Created by Alexander Goldberg (alexander.goldberg25@uga.edu) on 4/28/15.
 */
public class FileReader {
    private FileInputStream fileInputStream;
    private DataInputStream dataInputStream;
    private BufferedReader bufferedReader;

    FileReader(File name) {
        try {
            fileInputStream = new FileInputStream(name);
            dataInputStream = new DataInputStream(fileInputStream);
            bufferedReader = new BufferedReader(new InputStreamReader(dataInputStream));
        } catch (FileNotFoundException e) {

        }
    }

    public Representative[] readFile(Canvas canvas) {
        RepList list = new RepList();
        String[] tempObjectArray;
        try {
            while (true) {
                tempObjectArray = bufferedReader.readLine().split(",");

                String name = tempObjectArray[1];
                Party party = null;
                if (tempObjectArray[2].equalsIgnoreCase(Party.DEMOCRAT.toString())) {
                    party = Party.DEMOCRAT;
                } else if (tempObjectArray[2].equalsIgnoreCase(Party.MODERATE.toString())) {
                    party = Party.MODERATE;
                } else if (tempObjectArray[2].equalsIgnoreCase(Party.REPUBLICAN.toString())) {
                    party = Party.REPUBLICAN;
                }
                double x = Double.parseDouble(tempObjectArray[3]);
                double y = Double.parseDouble(tempObjectArray[4]);
                String notes = tempObjectArray[5];
                Stance stance = null;
                if (tempObjectArray[6].equalsIgnoreCase(Stance.YAY.toString())) {
                    stance = Stance.YAY;
                } else if (tempObjectArray[6].equalsIgnoreCase(Stance.NAY.toString())) {
                    stance = Stance.NAY;
                } else if (tempObjectArray[6].equalsIgnoreCase(Stance.ABS.toString())) {
                    stance = Stance.ABS;
                }


                list.add(new Representative(canvas, name, party, x, y, notes, stance));
            }
        } catch (IOException e) {

        } catch (NullPointerException e) {

        }
        return list.getList();
    }


}
