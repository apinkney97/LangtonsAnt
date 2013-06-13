/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package langtonsant;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Alex
 */
public class AntViewer extends JFrame {

    private int x;
    private int y;
    private AntGrid antgrid;

    public AntViewer(int x, int y, AntGrid antgrid) {
        this.x = x;
        this.y = y;
        this.antgrid = antgrid;

        setVisible(true);
        setSize(x, y);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(panel);

    }

    public JPanel panel = new JPanel() {



        @Override
        protected void paintComponent(Graphics g) {
            //System.out.println("HELLO!");
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            BufferedImage bi = (BufferedImage) (panel.createImage(x, y));
            int[][] grid = antgrid.getGrid();
            for (int i = 0; i < x; i++) {
                for (int j = 0; j < y; j++) {
                    bi.setRGB(i, j, getColour(grid[i][j]).getRGB());
                }
            }
            g2d.drawImage(bi, null, 0, 0);
            //System.out.println("REPAINTING");
        }
        
    };

    private Color getColour(int i) {
        //System.out.print(i+ " ");
        int numcolours = 13;
        i = (i + numcolours) % numcolours;
        //System.out.println(i);
        switch (i) {
            case 0:
                return Color.BLACK;
            case 1:
                return Color.WHITE;
            case 2:
                return Color.RED;
            case 3:
                return Color.BLUE;
            case 4:
                return Color.YELLOW;
            case 5:
                return Color.ORANGE;
            case 6:
                return Color.CYAN;
            case 7:
                return Color.MAGENTA;
            case 8:
                return Color.GREEN;
            case 9:
                return Color.PINK;
            case 10:
                return Color.DARK_GRAY;
            case 11:
                return Color.LIGHT_GRAY;
            default:
                return Color.GRAY;
        }
    }// end getColour
}// End AntViewer class

