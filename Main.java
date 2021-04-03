import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    static double[][] points = {{20, 20}, {100, 20}, {20, 100}, {50, 50}};

    private final int wndWidth = 800, wndHeight = 600;

    public Main() {
        super("Window");
        setSize(wndWidth, wndHeight);
        setResizable(false);
        setBackground(Color.WHITE);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D gr = (Graphics2D) g;
        gr.setColor(Color.WHITE);
        gr.fillRect(0, 0, this.getWidth(), this.getHeight());
        gr.setStroke(new BasicStroke(2));
        gr.setColor(Color.BLACK);
        gr.drawLine(0, wndHeight / 2, wndWidth, wndHeight / 2);
        gr.drawLine(wndWidth / 2, 0, wndWidth / 2, wndHeight);
        gr.setStroke(new BasicStroke(1));
        int buf = 10;
        while (buf < wndHeight) {
            gr.drawLine(wndWidth / 2 - 3, buf, wndWidth / 2 + 3, buf);
            buf += 10;
        }
        buf = 10;
        while (buf < wndWidth) {
            gr.drawLine(buf, wndHeight / 2 - 3, buf, wndHeight / 2 + 3);
            buf += 10;
        }
        drawF(points, gr);
    }

    public static double[][] movePts(/*int[][] points, */int dx, int dy) {
        double[][] T = {{1, 0, 0}, {0, 1, 0}, {dx, dy, 1}};
        for (int i = 0; i < points.length; i++) {
            double point[][] = {{points[i][0], points[i][1], 1}};
            double p[][] = multiplyByMatrix(point, T);
            points[i][0] = p[0][0];
            points[i][1] = p[0][1];
        }
        return points;
    }

    public static double[][] scalePts(/*int[][] points, */int Sx, int Sy) {
        double[][] S = {{Sx, 0, 0}, {0, Sy, 0}, {0, 0, 1}};
        for (int i = 0; i < points.length; i++) {
            double point[][] = {{points[i][0], points[i][1], 1}};
            double p[][] = multiplyByMatrix(point, S);
            points[i][0] = p[0][0];
            points[i][1] = p[0][1];
        }
        return points;
    }

    public static double[][] scalePtsR(/*int[][] points, */double Sx, double Sy, int x, int y) {
        double[][] S = {
                {Sx, 0, 0},
                {0, Sy, 0},
                {0, 0, 1}};
        double[][] T = {{1, 0, 0}, {0, 1, 0}, {-x, -y, 1}};
        double[][] T1 = {{1, 0, 0}, {0, 1, 0}, {x, y, 1}};
        double[][] S1 = multiplyByMatrix(T, S);
        S1 = multiplyByMatrix(S1, T1);
        for (int i = 0; i < points.length; i++) {
            double point[][] = {{points[i][0], points[i][1], 1}};
            double p[][] = multiplyByMatrix(point, S1);
            points[i][0] = p[0][0];
            points[i][1] = p[0][1];
        }
        return points;
    }

    public static double[][] turnPts(/*int[][] points, */double a) {
        double[][] R = {{(int) Math.cos(a), (int) Math.sin(a), 0}, {(int) -Math.sin(a), (int) Math.cos(a), 0}, {0, 0, 1}};
        for (int i = 0; i < points.length; i++) {
            double point[][] = {{points[i][0], points[i][1], 1}};
            double p[][] = multiplyByMatrix(point, R);
            points[i][0] = p[0][0];
            points[i][1] = p[0][1];
        }
        return points;
    }

    public static double[][] turnPtsR(/*int[][] points, */double a, int x, int y) {
        double[][] R = {{Math.cos(a), Math.sin(a), 0},
                {-Math.sin(a), Math.cos(a), 0},
                {0, 0, 1}};
        double[][] T = {{1, 0, 0}, {0, 1, 0}, {-x, -y, 1}};
        double[][] T1 = {{1, 0, 0}, {0, 1, 0}, {x, y, 1}};
        double[][] R1 = multiplyByMatrix(T, R);
        R1 = multiplyByMatrix(R1, T1);
        for (int i = 0; i < points.length; i++) {
            double point[][] = {{points[i][0], points[i][1], 1}};
            double p[][] = multiplyByMatrix(point, R1);
            points[i][0] = p[0][0];
            points[i][1] = p[0][1];
        }
        return points;
    }

    public void drawF(double[][] points, Graphics2D gr) {
        for (int i = 0; i < points.length - 1; i++) {
            gr.drawLine((int) (wndWidth / 2 + points[i][0]), (int) (wndHeight / 2 - points[i][1]),
                    (int) (wndWidth / 2 + points[i + 1][0]), (int) (wndHeight / 2 - points[i + 1][1]));
        }
        gr.drawLine((int) (wndWidth / 2 + points[points.length - 1][0]), (int) (wndHeight / 2 - points[points.length - 1][1]),
                (int) (wndWidth / 2 + points[0][0]), (int) (wndHeight / 2 - points[0][1]));
    }

    public static double[][] multiplyByMatrix(double[][] m1, double[][] m2) {
        int m1ColLength = m1[0].length; // m1 columns length
        int m2RowLength = m2.length;    // m2 rows length
        if (m1ColLength != m2RowLength) return null; // matrix multiplication is not possible
        int mRRowLength = m1.length;    // m result rows length
        int mRColLength = m2[0].length; // m result columns length
        double[][] mResult = new double[mRRowLength][mRColLength];
        for (int i = 0; i < mRRowLength; i++) {         // rows from m1
            for (int j = 0; j < mRColLength; j++) {     // columns from m2
                for (int k = 0; k < m1ColLength; k++) { // columns from m1
                    mResult[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return mResult;
    }

    public static void main(String[] args) {
//        Scanner s = new Scanner(System.in);
//        int ptCount = s.nextInt();
//        points = new int[ptCount][2];
//        for (int i = 0; i < ptCount; i++) {
//            System.out.println("x" + (i + 1));
//            points[i][0] = s.nextInt();
//            System.out.println("y" + (i + 1));
//            points[i][1] = s.nextInt();
//        }

        Main m = new Main();
        m.setVisible(true);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        System.out.println("enter angle");
//        double buf = s.nextDouble();
        turnPtsR(Math.PI / 3, 20, 20);
        m.repaint();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        System.out.println("enter Sx");
//        buf = s.nextDouble();
//        System.out.println("enter Sy");
//        buf = s.nextDouble();
        scalePtsR(2, 2, 20, 20);
        m.repaint();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        System.out.println("enter dx");
//        int buf1 = s.nextInt();
//        System.out.println("enter dy");
//        buf1 = s.nextInt();
        movePts(100, 0);
        m.repaint();
    }
}
