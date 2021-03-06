package Presentation.View;

        import java.awt.*;
        import java.util.ArrayList;
        import java.util.List;
        import javax.swing.*;

/**
 * The type Draw graph.
 */
public class DrawGraph extends JPanel {
    private static final int MAX_SCORE = 20;
    private static final int PREF_W = 800;
    private static final int PREF_H = 650;
    private static final int BORDER_GAP = 30;
    private static final Color GRAPH_COLOR = Color.decode("#DF4B74");
    private static final Color GRAPH_POINT_COLOR = Color.decode("#E27B97");
    private static final Stroke GRAPH_STROKE = new BasicStroke(3f);
    private static final int GRAPH_POINT_WIDTH = 6;
    private static final int Y_HATCH_CNT = 10;
    private List<Integer> scores;
    private int axis_x;

    /**
     * Instantiates a new Draw graph.
     *
     * @param scores the scores
     */
    public DrawGraph(List<Integer> scores, int axis_x) {
        this.axis_x = axis_x;
        this.scores = scores;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        double xScale = ((double) getWidth() - 2 * BORDER_GAP) / (scores.size() - 1);
        double yScale = ((double) getHeight() - 2 * BORDER_GAP) / (MAX_SCORE - 1);

        List<Point> graphPoints = new ArrayList<Point>();
        for (int i = 0; i < scores.size(); i++) {
            int x1 = (int) (i * xScale + BORDER_GAP);
            int y1 = (int) ((MAX_SCORE - scores.get(i)) * yScale + BORDER_GAP);
            graphPoints.add(new Point(x1, y1));
        }

        // create x and y axes
        g2.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, BORDER_GAP, BORDER_GAP);
        g2.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, getWidth() - BORDER_GAP, getHeight() - BORDER_GAP);

        FontMetrics fm = g2.getFontMetrics();
        // create hatch marks for y axis.
        for (int i = 0; i < Y_HATCH_CNT; i++) {
            int x0 = BORDER_GAP;
            int x1 = GRAPH_POINT_WIDTH + BORDER_GAP;
            int y0 = getHeight() - (((i + 1) * (getHeight() - BORDER_GAP * 2)) / Y_HATCH_CNT + BORDER_GAP);
            int y1 = y0;
            g2.drawLine(x0, y0, x1, y1);
            String value = Integer.toString((i+1)*5);
            g2.drawString(value, x0 - fm.stringWidth(value), y0 + (fm.getAscent() / 2));
        }

        // and for x axis
        for (int i = 0; i < scores.size() ; i++) {
            int x0 = (i) * (getWidth() - BORDER_GAP * 2) / (scores.size() - 1) + BORDER_GAP;
            int x1 = x0;
            int y0 = getHeight() - BORDER_GAP;
            int y1 = y0 - GRAPH_POINT_WIDTH;
            g2.drawLine(x0, y0, x1, y1);
            String value = null;
            if(axis_x == 1){
                switch (i){
                    case 0:
                        value = "Dilluns";
                        break;
                    case 1:
                        value = "Dimarts";
                        break;
                    case 2:
                        value = "Dimecres";
                        break;
                    case 3:
                        value = "Dijous";
                        break;
                    case 4:
                        value = "Divendres";
                        break;
                    case 5:
                        value = "Dissabte";
                        break;
                    case 6:
                        value = "Diumnege";
                        break;
                }
            }else {
                value = Integer.toString(i);
            }
            g2.drawString(value, x0 - (fm.stringWidth(value) / 2), y0 + fm.getAscent());
        }

        Stroke oldStroke = g2.getStroke();
        g2.setColor(GRAPH_COLOR);
        g2.setStroke(GRAPH_STROKE);
        for (int i = 0; i < graphPoints.size() - 1; i++) {
            int x1 = graphPoints.get(i).x;
            int y1 = graphPoints.get(i).y;
            int x2 = graphPoints.get(i + 1).x;
            int y2 = graphPoints.get(i + 1).y;
            g2.drawLine(x1, y1, x2, y2);
        }

        g2.setStroke(oldStroke);
        g2.setColor(GRAPH_POINT_COLOR);
        for (int i = 0; i < graphPoints.size(); i++) {
            int x = graphPoints.get(i).x - GRAPH_POINT_WIDTH / 2;
            int y = graphPoints.get(i).y - GRAPH_POINT_WIDTH / 2;
            ;
            int ovalW = GRAPH_POINT_WIDTH;
            int ovalH = GRAPH_POINT_WIDTH;
            g2.fillOval(x, y, ovalW, ovalH);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(PREF_W, PREF_H);
    }
}