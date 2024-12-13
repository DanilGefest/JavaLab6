package org.example.lab6.geometry2d;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Figure {
    public double x;
    public double y;
    public Color color;

    public Figure(double x, double y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public abstract double area();

    public abstract double perimeter();

    public abstract void draw(GraphicsContext gc);

    public abstract boolean contains(double px, double py);

    public double getLength() {
        return 0;
    }

    public abstract double getHeight();
}
