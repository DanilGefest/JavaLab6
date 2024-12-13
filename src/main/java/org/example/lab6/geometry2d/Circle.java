package org.example.lab6.geometry2d;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class Circle extends Figure {
    double radius;

    public Circle(double x, double y, double radius, Color color) throws IncorrectValueException {
        super(x, y, color);
        if (radius <= 0) {
            throw new IncorrectValueException("The radius is specified incorrectly");
        }
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }

    @Override
    public double perimeter() {
        return 2*Math.PI*radius;
    }

    @Override
    public double getLength() {
        return 2 * radius;  // Диаметр круга
    }

    @Override
    public double getHeight() {
        return 2 * radius;  // Диаметр круга
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillOval(x - radius, y - radius, 2 * radius, 2 * radius);
    }

    @Override
    public boolean contains(double px, double py) {
        double dx = px - x;
        double dy = py - y;
        return dx * dx + dy * dy <= radius * radius;
    }

}