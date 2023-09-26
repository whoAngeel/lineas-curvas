package bezier;


import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

/**
 *
 * @author tow_e
 */

public class Curva_Bezier extends JPanel {

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(3));

        // Puntos de control
        int x1 = 50, y1 = 200;
        int x2 = 150, y2 = 50;
        int x3 = 250, y3 = 300;
        int x4 = 350, y4 = 200;

        // Dibuja la curva de Bézier
        CubicCurve2D c = new CubicCurve2D.Double();
        c.setCurve(x1, y1, x2, y2, x3, y3, x4, y4);
        g2d.draw(c);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Curva de Bézier");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Curva_Bezier());
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

