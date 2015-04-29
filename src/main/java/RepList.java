/**
 * Created by Alexander Goldberg (alexander.goldberg25@uga.edu) on 4/28/15.
 */
public class RepList {
    private Representative[] representatives = new Representative[0];

    public void add(Representative rep) {
        Representative[] temp;
        temp = new Representative[representatives.length + 1];
        for (int i = 0; i < representatives.length; i++) {
            temp[i] = representatives[i];
        }
        temp[temp.length - 1] = rep;
        representatives = temp;
    }

    public Representative[] getList() {
        return representatives; //TODO Make Secure
    }


}
