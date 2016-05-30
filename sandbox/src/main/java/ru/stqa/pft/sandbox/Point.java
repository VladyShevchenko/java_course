package ru.stqa.pft.sandbox;

public class Point {
    public double x;
    public double y;

    public Point(double x, double y) {
        this.x=x;
        this.y=y;
    }
    //Способ 1. Через функцию
    public static double distance(Point p1, Point p2) {
        double x = p1.x - p2.x;
        double y = p1.y - p2.y;
        return Math.sqrt(x*x + y*y);
    }

    //Способ 2. Через метод
    public double distance(Point p) {
        double distance;
        distance = Math.sqrt(((this.x - p.x) * (this.x - p.x)) + ((this.y - p.y) * (this.y - p.y)));
        return distance;
    }
    public static void main(String[] args){
        Point p1 = new Point(8,5);
        Point p2 = new Point(6,4);
    // Функция:
        System.out.println("Distance between point a and point b is "+ distance(p1,p2) );
     // Meтод:
        System.out.println("Distance between point a and point b is "+ p1.distance(p2));
    }
}