package com.epam.rd.autotasks.figures;

class Point {
    private final double x;
    private final double y;

    public Point(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point)) return false;
        Point point = (Point) o;
        return Math.abs(point.x - x) < 0.000000000000001 && Math.abs(point.y - y) < 0.000000000000001;
    }
}