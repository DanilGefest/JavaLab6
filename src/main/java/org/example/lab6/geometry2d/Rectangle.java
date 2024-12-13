package org.example.lab6.geometry2d;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Rectangle extends Figure {
    double length, height;

    public Rectangle(double x, double y, double length, double height, Color color) {
        super(x, y, color);
        this.length = length;
        this.height = height;
    }

    @Override
    public double area() {
        return length * height;
    }

    @Override
    public double perimeter() {
        return (length + height) * 2;
    }

    @Override
    public double getLength() {
        return length;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillRect(x, y, length, height);
    }

    @Override
    public boolean contains(double px, double py) {
        return px >= x && px <= x + length && py >= y && py <= y + height;
    }
}