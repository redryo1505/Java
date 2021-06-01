/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package btl_nhom2;

import java.util.ArrayList;

/**
 *
 * @author Hoàng
 */
public class Segment {
    private int width, height;
    Sensor sensor;
    ArrayList<Point> listLocation;
    private int k;
    public Segment(int width, int height, Sensor sensor) {
        this.width = width;
        this.height = height;
        this.sensor = sensor;
    }
    public Segment() {
    }

    public int getHeight() {
        return height;
    }

    public void setListLocation(ArrayList<Point> listLocation) {
        this.listLocation = listLocation;
    }

   

    public int getWidth() {
        return width;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public ArrayList<Point> getListLocation() {
        return listLocation;
    }

    public Sensor getSensor() {
        return sensor;
    }

    @Override
    public String toString() {
        //return "Segment{" + "width=" + width + ", height=" + height + ", sensor=" + sensor + ", listLocation=" + listLocation + '}';
        String str = width + " " + height + "\nSố lượng cảm biến: "  + listLocation.size()
                + "\nTọa độ các cảm biến:";
        for(Point i: listLocation){
            str += "\n(" + i.getX() + ";  " + i.getY()+")";
        }
        return str;
    }

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }
    
}
