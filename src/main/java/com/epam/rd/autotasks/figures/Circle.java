package com.epam.rd.autotasks.figures;

import java.util.Objects;

class Circle extends Figure {

    private final Point CENTER;
    private final double RADIUS;

    public Circle(Point center, double radius) {
        if (center == null || radius <= 0)
            throw new IllegalArgumentException();
        this.CENTER = center;
        this.RADIUS = radius;
    }

    @Override
    public Point centroid() {
        return CENTER;
    }

    @Override
    public boolean isTheSame(Figure figure) {
        if (this == figure) return true;
        if (!(figure instanceof Circle)) return false;
        Circle circle = (Circle) figure;
        return Math.abs(circle.RADIUS - RADIUS) < 0.0000000000001 && Objects.equals(CENTER, circle.CENTER);
    }
}