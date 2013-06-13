/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package langtonsant;

/**
 *
 * @author Alex
 */
public class AntGrid {

    private int width;
    private int height;
    private int pos_x;
    private int pos_y;
    private int direction; //I'm using 0=U, 1=R, 2=D, 4=L for internal representation
    private int state;
    private AntRuleSet rules;
    private int[][] grid;

    public AntGrid(int width, int height) {
        this.width = width;
        this.height = height;

        pos_x = width / 2;
        pos_y = height / 2; //Start approx in the centre

        direction = 0;
        state = 0;

        grid = new int[width][height];

        clear();
    }

//    public void addLRRuleString(String rules) {
//        int i;
//        for (i = 0; i < rules.length() - 1; i++) {
//            addRule(0, i, i + 1, getDir(rules.charAt(i)), 0);
//        }
//        addRule(0, i, 0, getDir(rules.charAt(i)), 0);
//    }
//
//    private int getDir(char c) {
//        int d = 0;
//        switch (c) {
//            case 'L':
//            case 'l':
//                d = 2;
//                break;
//            case 'R':
//            case 'r':
//                d = 8;
//                break;
//            case 'F':
//            case 'f':
//                d = 1;
//                break;
//            case 'B':
//            case 'b':
//                d = 4;
//                break;
//            default:
//                System.out.println(c + " does not represent a valid direction!");
//                System.exit(-2);
//        }
//        return d;
//    }
    public boolean step() {
        // get relevant rule
        int c = grid[pos_x][pos_y];
        Rule r = rules.getRule(state, c);

        if (r == null) {
            System.out.println("No rule for state " + state + ", colour " + c + "!");
            System.exit(-1);
        }

        //Change the colour and state, work out new direction, and move.
        grid[pos_x][pos_y] = r.getColour();
        state = r.getState();

        switch (r.getDirection()) {
            case 8:
                direction += 1;
                break;
            case 4:
                direction += 2;
                break;
            case 2:
                direction += 3;
                break;
            case 0:
                return false; //Halting state
        }//If it's 1, don't need to do anything. Can ignore other numbers.

        direction %= 4; //Make sure it's still in range

        switch (direction) {
            case 0:
                pos_y--;
                break;
            case 1:
                pos_x--;
                break;
            case 2:
                pos_y++;
                break;
            case 3:
                pos_x++;
                break;
        }
        pos_x = (pos_x + width) % width; //Wraps position around
        pos_y = (pos_y + height) % height;

        return true;
    }

    public int[][] getGrid() {
        return grid; //Make sure you behave yourself with this - I can't be bothered to make it deep-clone.
    }

    public void printGrid() {
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                sb.append(grid[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public void setRules(AntRuleSet rs) {
        rules = rs;
    }

    public AntRuleSet getRules() {
        return rules;
    }

    public final void clear() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                grid[i][j] = 0; //Initialise with zeros
            }
        }
    }
}
