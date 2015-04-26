/**
 * Created by Alexander Goldberg (alexander.goldberg25@uga.edu) on 4/25/15.
 */
public class Register {
    public static Representative[] representatives = new Representative[0];

    public static void add(Representative rep) {
        Representative[] temp;
        temp = new Representative[representatives.length + 1];
        for (int i = 0; i < representatives.length; i++) {
            temp[i] = representatives[i];
        }
        temp[temp.length - 1] = rep;
        representatives = temp;
    }

    public static Representative[] getList() {
        return representatives; //TODO Make Secure
    }

    public static Representative getRepresentative(double x, double y) {
        for (int i = 0; i < representatives.length; i++) {
            if ((representatives[i].getX() < x && representatives[i].getX() + representatives[i].getWidth() > x) && (representatives[i].getY() < y && representatives[i].getY() + representatives[i].getHeight() > y)) {
                return representatives[i];
            }
        }
        return null;
    }

    //This adds a margin of error around the Visual Tag // TODO Find A Better Solution to getRepresentativeOnDrag
    public static Representative getRepresentativeOnDrag(double x, double y) {
        for (int i = 0; i < representatives.length; i++) {
            if (((representatives[i].getX() < x + representatives[i].getWidth()) && (representatives[i].getX() + representatives[i].getWidth() > x))) {
                if (((representatives[i].getY() < y + representatives[i].getHeight()) && representatives[i].getY() + representatives[i].getHeight() > y)) {
                    return representatives[i];
                }
            }
        }
        return null;
    }

}
