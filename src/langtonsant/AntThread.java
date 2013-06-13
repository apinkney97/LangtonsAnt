/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package langtonsant;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author azp
 */
public class AntThread implements Runnable {

    private int skipsteps;
    private int pausetime;
    private int pausesteps;
    private AntGrid grid;
    private boolean isStopped;

    public AntThread(int x, int y) {
        skipsteps = 1;
        pausetime = 0;
        pausesteps = 1;
        isStopped = false;
        grid = new AntGrid(x, y);
    }

    public void setSkipSteps(int n) {
        skipsteps = n;
    }

    public void setPauseTime(int n) {
        pausetime = n;
    }

    public void setPauseSteps(int n) {
        pausesteps = n;
    }

    public void setRules(AntRuleSet rs) {
        grid.setRules(rs);
    }


    public void run() {

        int x = 450;
        int y = x;
        isStopped = false;

        //grid.addLRRuleString("RL");
        //grid.addLRRuleString("LLRR");
        //grid.addLRRuleString("RLR");
        //grid.addLRRuleString("LRRRRRLLR");
        //grid.addLRRuleString("LLRRRLRLRLLR");
        //grid.addLRRuleString("RRLLLRLLLRRR");
        //grid.addLRRuleString("RRLRLLRLRR");
        //grid.addLRRuleString("RRLLLRRRLRRR");

        //grid.addLRRuleString("RRLRLRR");
        //grid.addLRRuleString("RRLLLRRRRRLR");
        //grid.addLRRuleString("RRLRLLRRRRRR");
        //grid.addLRRuleString("RRLRR");
        //grid.addLRRuleString("LRRRRLLLRRR"); //Spiral inside a square
        //grid.addLRRuleString("RLLR");
        //grid.addLRRuleString("RRRRLRRRLLRR");
        //grid.addLRRuleString("RLLLLRRRLLLR"); //Another spiral in a square
        //grid.addLRRuleString("LLRLLLRRRRR");
        //grid.addLRRuleString("LLRRRLRRRRR");
        //grid.addLRRuleString("LRLLRRRRR");
        //grid.addLRRuleString("LRLRLLLLLLLR");
        //grid.addLRRuleString("LLRRLRRRRRRR");

        //grid.addLRRuleString("LRRLL");



        /*grid.addRule(0, 0, 1, 2, 0); //Loopy(ish)
        grid.addRule(0, 1, 1, 2, 1);
        grid.addRule(1, 0, 0, 1, 0);
        grid.addRule(1, 1, 0, 1, 1);*/

        /*grid.addRule(0, 0, 1, 1, 1); //Spiral
        grid.addRule(0, 1, 1, 8, 0);
        grid.addRule(1, 0, 1, 2, 1);
        grid.addRule(1, 1, 0, 1, 0);*/

        /*grid.addRule(0, 0, 1, 2, 1); //Highway builder
        grid.addRule(0, 1, 0, 2, 1);
        grid.addRule(1, 0, 1, 1, 0);
        grid.addRule(1, 1, 1, 1, 1);*/

        /*grid.addRule(0, 0, 1, 2, 1); //Squiggles
        grid.addRule(0, 1, 1, 8, 1);
        grid.addRule(1, 0, 1, 2, 1);
        grid.addRule(1, 1, 0, 2, 0);*/

        /*grid.addRule(0, 0, 1, 8, 0); //2d barcode?
        grid.addRule(0, 1, 1, 2, 1);
        grid.addRule(1, 0, 0, 2, 0);
        grid.addRule(1, 1, 0, 8, 1);*/

        /*grid.addRule(0, 0, 1, 8, 1); //Spiral
        grid.addRule(0, 1, 1, 8, 1);
        grid.addRule(1, 0, 1, 2, 1);
        grid.addRule(1, 1, 0, 1, 0);*/

        /*grid.addRule(0, 0, 1, 8, 0); // 1 pixel square spiral
        grid.addRule(0, 1, 0, 2, 1);
        grid.addRule(1, 0, 1, 2, 0);
        grid.addRule(1, 1, 0, 8, 1);*/

        /*grid.addRule(0, 0, 1, 2, 0); // Spread
        grid.addRule(0, 1, 2, 4, 1);
        grid.addRule(0, 2, 2, 1, 0);
        grid.addRule(1, 0, 1, 0, 0);
        grid.addRule(1, 1, 2, 2, 0);
        grid.addRule(1, 2, 2, 1, 1);*/


        grid.getRules().printRules();
        grid.clear();


        AntViewer viewer = new AntViewer(x, y, grid);

        int i = 0;
        do {
            grid.step();
            if (i++ % skipsteps == 0) {
                viewer.setTitle("Iterations: " + (i-1));
                viewer.repaint();
            }
            if (pausetime > 0) {
                if (i % pausesteps == 0) {
                    try {
                        Thread.sleep(pausetime);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } while (grid.step() && !isStopped);
    }

    void stop() {
        isStopped = true;
    }
}
