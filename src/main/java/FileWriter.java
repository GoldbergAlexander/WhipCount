import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;


/**
 * Created by Alexander Goldberg (alexander.goldberg25@uga.edu) on 4/28/15.
 */
public class FileWriter {

    java.io.FileWriter fileWriter;
    BufferedWriter bufferedWriter;

    FileWriter(File string) {
        try {
            fileWriter = new java.io.FileWriter(string);
            bufferedWriter = new BufferedWriter(fileWriter);
        } catch (IOException e) {

        }

    }

    public void write(Representative[] reps) {
        String string = "";
        for (int i = 0; i < reps.length; i++) {
            string = "," + reps[i].getName() + "," + reps[i].getParty().toString() + "," + reps[i].getX() + "," + reps[i].getY() + "," + reps[i].getNotes() + "," + reps[i].getStance().toString() + ",";
            try {
                bufferedWriter.write(string);
                bufferedWriter.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
