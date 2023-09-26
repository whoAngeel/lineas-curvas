package bsplines;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;

public class Spline2 extends JPanel {
    private Vector<Point> points = new Vector<>();
    private boolean completed = true;

    public Spline2() {
        // Agrega tus puntos directamente aquí o en un método
        points.add(new Point(93, 122));
        points.add(new Point(170, 222));
        points.add(new Point(248, 139));
        points.add(new Point(351, 272));
        points.add(new Point(420,170));
        points.add(new Point(410,100));
        points.add(new Point(351,160));


        // Establece completed en true para que no se necesite doble clic
        completed = true;
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.RED);
        for (Point punto : points) {
            g2.fillOval(punto.x, punto.y, 6, 6);
            String etiqueta = "(" + punto.x + ", " + punto.y + ")";
            g2.setColor(Color.BLACK);
            g2.drawString(etiqueta, punto.x + 10, punto.y - 10);
        }
        g2.setColor(Color.black);

        Point p0 = null;
        Point p1 = null;
        Point p2 = null;
        Point p3 = null;
        float x1, y1, x2, y2, x3, y3, x4, y4;
        Iterator<Point> it = points.iterator();

        if (it.hasNext()) {
            p1 = it.next();
        }
        while (it.hasNext()) {
            p2 = it.next();
            g2.drawLine(p1.x, p1.y, p2.x, p2.y);
            p1 = p2;
        }
        GeneralPath spline = new GeneralPath();
        int n = points.size();
        if (n == 0)
            return;
        p1 = points.get(0);
        spline.moveTo(p1.x, p1.y);
        g2.drawRect(p1.x - 3, p1.y - 3, 6, 6);
        p1 = points.get(1);
        p2 = points.get(2);
        p3 = points.get(3);
        x1 = p1.x;
        y1 = p1.y;
        x2 = (p1.x + p2.x) / 2.0f;
        y2 = (p1.y + p2.y) / 2.0f;
        x4 = (2.0f * p2.x + p3.x) / 3.0f;
        y4 = (2.0f * p2.y + p3.y) / 3.0f;
        x3 = (x2 + x4) / 2.0f;
        y3 = (y2 + y4) / 2.0f;
        spline.curveTo(x1, y1, x2, y2, x3, y3);
        g2.drawRect((int) x1 - 3, (int) y1 - 3, 6, 6);
        g2.drawRect((int) x2 - 3, (int) y2 - 3, 6, 6);
        g2.drawRect((int) x3 - 3, (int) y3 - 3, 6, 6);
        for (int i = 2; i < n - 4; i++) {
            p1 = p2;
            p2 = p3;
            p3 = points.get(i + 2);
            x1 = x4;
            y1 = y4;
            x2 = (p1.x + 2.0f * p2.x) / 3.0f;
            y2 = (p1.y + 2.0f * p2.y) / 3.0f;
            x4 = (2.0f * p2.x + p3.x) / 3.0f;
            y4 = (2.0f * p2.y + p3.y) / 3.0f;
            x3 = (x2 + x4) / 2.0f;
            y3 = (y2 + y4) / 2.0f;
            spline.curveTo(x1, y1, x2, y2, x3, y3);
            g2.drawRect((int) x1 - 3, (int) y1 - 3, 6, 6);
            g2.drawRect((int) x2 - 3, (int) y2 - 3, 6, 6);
            g2.drawRect((int) x3 - 3, (int) y3 - 3, 6, 6);
        }
        p1 = p2;
        p2 = p3;
        p3 = points.get(n - 2);
        x1 = x4;
        y1 = y4;
        x2 = (p1.x + 2.0f * p2.x) / 3.0f;
        y2 = (p1.y + 2.0f * p2.y) / 3.0f;
        x4 = (p2.x + p3.x) / 2.0f;
        y4 = (p2.y + p3.y) / 2.0f;
        x3 = (x2 + x4) / 2.0f;
        y3 = (y2 + y4) / 2.0f;
        spline.curveTo(x1, y1, x2, y2, x3, y3);
        g2.drawRect((int) x1 - 3, (int) y1 - 3, 6, 6);
        g2.drawRect((int) x2 - 3, (int) y2 - 3, 6, 6);
        g2.drawRect((int) x3 - 3, (int) y3 - 3, 6, 6);
        p2 = p3;
        p3 = points.get(n - 1);
        x1 = x4;
        y1 = y4;
        x2 = p2.x;
        y2 = p2.y;
        x3 = p3.x;
        y3 = p3.y;
        spline.curveTo(x1, y1, x2, y2, x3, y3);
        g2.setColor(Color.MAGENTA);
        g2.drawRect((int) x1 - 3, (int) y1 - 3, 6, 6);
        g2.drawRect((int) x2 - 3, (int) y2 - 3, 6, 6);
        g2.drawRect((int) x3 - 3, (int) y3 - 3, 6, 6);
        g2.setStroke(new BasicStroke(2));
        g2.draw(spline);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("B-spline Demo");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new Spline2());
            frame.setSize(500, 400);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
