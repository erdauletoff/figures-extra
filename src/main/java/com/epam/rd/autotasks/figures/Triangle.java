package com.epam.rd.autotasks.figures;

import java.util.Objects;

class Triangle extends Figure {
    private final Point A, B, C;

    public Triangle(Point a, Point b, Point c) {
        if (a == null || b == null || c == null || isDegenerative(a, b, c))
            throw new IllegalArgumentException();
        this.A = a;
        this.B = b;
        this.C = c;
    }

    static boolean isDegenerative(Point a, Point b, Point c) {
        double ab = length(a, b),
                bc = length(b, c),
                ca = length(c, a);
        double delta = 0.00000000001;
        return ab + bc <= ca + delta || ab + ca <= bc + delta || bc + ca <= ab + delta;
    }

    private static double length(Point start, Point end) {
        double deltaX = start.getX() - end.getX();
        double deltaY = start.getY() - end.getY();
        return Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
    }

    public double area() {
        double ab = length(A, B);
        double bc = length(B, C);
        double ca = length(C, A);
        double s = (ab + bc + ca) / 2;
        return Math.sqrt(s * (s - ab) * (s - bc) * (s - ca));
    }

    @Override
    public Point centroid() {
        double cartesianX = (A.getX() + B.getX() + C.getX()) / 3;
        double cartesianY = (A.getY() + B.getY() + C.getY()) / 3;
        return new Point(cartesianX, cartesianY);
    }

    @Override
    public boolean isTheSame(Figure figure) {
        if (this == figure) return true;
        if (!(figure instanceof Triangle)) return false;
        Triangle triangle = (Triangle) figure;
        return (Objects.equals(A, triangle.A) || Objects.equals(A, triangle.B) || Objects.equals(A, triangle.C))
                && (Objects.equals(B, triangle.A) || Objects.equals(B, triangle.B) || Objects.equals(B, triangle.C))
                && (Objects.equals(C, triangle.A) || Objects.equals(C, triangle.B) || Objects.equals(C, triangle.C));
    }
}