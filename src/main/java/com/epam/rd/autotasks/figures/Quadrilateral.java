package com.epam.rd.autotasks.figures;

import java.util.Arrays;
import java.util.Objects;

class Quadrilateral extends Figure {

    private final Point A, B, C, D;

    public Quadrilateral(Point a, Point b, Point c, Point d) {
        if (a == null || b == null || c == null || d == null || isDegenerative(a, b, c, d)
                || isNonConvex(a, b, c, d) || isPlain(a, b, c, d))
            throw new IllegalArgumentException();
        this.A = a;
        this.B = b;
        this.C = c;
        this.D = d;
    }

    private boolean isDegenerative(Point a, Point b, Point c, Point d) {
        return (Triangle.isDegenerative(a, b, c) || Triangle.isDegenerative(d, b, c)
                || Triangle.isDegenerative(a, d, c) || Triangle.isDegenerative(a, b, d));
    }

    private boolean isNonConvex(Point a, Point b, Point c, Point d) {
        double[] triangles = {new Triangle(a, b, c).area(), new Triangle(d, b, c).area(),
                new Triangle(a, b, d).area(), new Triangle(a, c, d).area()};
        Arrays.sort(triangles);
        return (Math.abs(triangles[3] - triangles[0] - triangles[1] - triangles[2]) < 0.01);
    }

    private boolean isPlain(Point a, Point b, Point c, Point d) {
        Point middleAB = new Point((a.getX() + b.getX()) / 2, (a.getY() + b.getY()) / 2);
        Point middleBC = new Point((b.getX() + c.getX()) / 2, (b.getY() + c.getY()) / 2);
        Point middleCD = new Point((c.getX() + d.getX()) / 2, (c.getY() + d.getY()) / 2);
        Point middleDA = new Point((d.getX() + a.getX()) / 2, (d.getY() + a.getY()) / 2);
        return (middleCD.equals(middleAB) || middleBC.equals(middleDA));
    }

    @Override
    public Point centroid() {
        Point a = new Triangle(B, C, D).centroid();
        Point b = new Triangle(C, D, A).centroid();
        Point c = new Triangle(D, A, B).centroid();
        Point d = new Triangle(A, B, C).centroid();

        return intersection(a, b, c, d);
    }

    private Point intersection(Point a, Point b, Point c, Point d) {
        double x1x3 = a.getX() - b.getX();
        double y3y4 = b.getY() - d.getY();
        double y1y3 = a.getY() - b.getY();
        double x3x4 = b.getX() - d.getX();
        double x1x2 = a.getX() - c.getX();
        double y1y2 = a.getY() - c.getY();

        double t = (x1x3 * y3y4 - y1y3 * x3x4) / (x1x2 * y3y4 - y1y2 * x3x4);

        double xPoint = a.getX() + t * (-x1x2);
        double yPoint = a.getY() + t * (-y1y2);

        return new Point(xPoint, yPoint);
    }

    @Override
    public boolean isTheSame(Figure figure) {
        if (this == figure) return true;
        if (!(figure instanceof Quadrilateral)) return false;
        Quadrilateral quadrilateral = (Quadrilateral) figure;
        return (Objects.equals(A, quadrilateral.A) || Objects.equals(A, quadrilateral.B)
                || Objects.equals(A, quadrilateral.C) || Objects.equals(A, quadrilateral.D))
                && (Objects.equals(B, quadrilateral.A) || Objects.equals(B, quadrilateral.B)
                || Objects.equals(B, quadrilateral.C) || Objects.equals(B, quadrilateral.D))
                && (Objects.equals(C, quadrilateral.A) || Objects.equals(C, quadrilateral.B)
                || Objects.equals(C, quadrilateral.C) || Objects.equals(C, quadrilateral.D))
                && (Objects.equals(D, quadrilateral.A) || Objects.equals(D, quadrilateral.B)
                || Objects.equals(D, quadrilateral.C) || Objects.equals(D, quadrilateral.D));
    }
}