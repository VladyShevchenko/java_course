package ru.stqa.pft.sandbox;

public class Point {
    public double x;
    public double y;

    public Point(double x, double y) {
        this.x=x;
        this.y=y;
    }

    public static double distance(Point p1, Point p2) {
        double x = p1.x - p2.x;
        double y = p1.y - p2.y;
        return Math.sqrt(x*x + y*y);
    }

    public static void main(String[] args){
        Point a = new Point(8,5);
        Point b = new Point(6,4);
        System.out.println("Distance between point a and point b is "+ distance(a,b) );
    }
}