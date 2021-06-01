/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package btl_nhom2;

/**
 *
 * @author Hoàng
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Visualization extends JFrame {
    private JFrame jf = this;
    Segment s;
    int times;
   
     ArrayList<ArrayList<Point>> listCluster=new ArrayList<ArrayList<Point>>();
     ArrayList<Point> listCentroids=new ArrayList<Point>();
     private Grid grid;

    public Visualization(Segment s, String str) {
        
        this.s=s;
        Change xl=new Change();
        xl.kMean(s.getListLocation(), s.getK());
        listCentroids=xl.getListCentroids();
        listCluster=xl.getListCluster();
        setTitle(str);
        this.setLayout(new BorderLayout());
        
        this.setSize(s.getWidth()+50, s.getHeight()+50);
        
        grid = new Grid(s, str);
        add(grid);
    
    }

        class Grid extends JPanel {
        Segment s;
        int times;
         public Grid(Segment s, int t) {
            this.s = s;
            times = t;

        }
        private Color[] colors = {Color.BLACK, Color.BLUE, Color.CYAN, Color.DARK_GRAY, Color.GREEN, Color.MAGENTA, Color.ORANGE,
                Color.PINK, Color.RED, Color.YELLOW,
                Color.LIGHT_GRAY, Color.GRAY
        };
        
        private Color c = Color.BLACK;
        private JButton jButton1;
        private JTextArea jTextArea;
        
        private double minX, minY;
        private double scaleFactorX, scaleFactorY;
     
        public Grid(Segment s, String str) {
         
            double maxX = Double.MIN_VALUE;
            double maxY = Double.MIN_VALUE;
            minX = Double.MAX_VALUE;
            minY = Double.MAX_VALUE;
            
            for (ArrayList<Point> c : listCluster) {
                for (Point p : c) {
                    if (p.getX() > maxX) maxX = p.getX();
                    if (p.getY() > maxY) maxY = p.getY();
                    if (p.getX() < minX) minX = p.getX();
                    if (p.getY() < minY) minY = p.getY();
                }
            }
            
            scaleFactorX = 500.0 / (maxX - minX);
            scaleFactorY = 500.0 / (maxY - minY);
            
            setLayout(new FlowLayout());
            jButton1 = new JButton("Close");
            add(jButton1);
            repaint();
            
            jTextArea = new JTextArea();
            add(jTextArea);
            repaint();
            
            jButton1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton1ActionPerformed(evt);
                }
            });
        
        }

        private void jButton1ActionPerformed(ActionEvent evt) {
            jf.dispose();
        }
         @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (int i = 0; i < listCluster.size(); i++) {
                g.setColor(colors[i%12]);
                ArrayList<Point> c = listCluster.get(i);
                for (Point p : c) {
                    g.fillRect( (int)((p.getX()-minX)*(scaleFactorX)) + 30, (int)((p.getY()-minY)*(scaleFactorY)) + 50, 5, 5);
                }
                //g.setColor(colors[(i+1)%12]);
                g.drawLine((int)((listCentroids.get(i).getX()-minX)*(scaleFactorX)) + 15, (int)((listCentroids.get(i).getY()-minY)*(scaleFactorY)) + 50,
                        (int)((listCentroids.get(i).getX()-minX)*(scaleFactorX)) + 45, (int)((listCentroids.get(i).getY()-minY)*(scaleFactorY)) + 50);
                g.drawLine((int)((listCentroids.get(i).getX()-minX)*(scaleFactorX)) + 30, (int)((listCentroids.get(i).getY()-minY)*(scaleFactorY)) + 35,
                        (int)((listCentroids.get(i).getX()-minX)*(scaleFactorX)) + 30, (int)((listCentroids.get(i).getY()-minY)*(scaleFactorY)) + 65);
            }
        }


    }
}

