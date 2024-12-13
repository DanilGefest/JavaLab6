package org.example.lab6;

import javafx.fxml.FXML;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import org.example.lab6.geometry2d.Circle;
import org.example.lab6.geometry2d.IncorrectValueException;
import org.example.lab6.geometry2d.Rectangle;
import org.example.lab6.geometry2d.Figure;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DrawingController {

    @FXML
    private Canvas canvas;

    @FXML
    private Button drawCircleButton;

    @FXML
    private Button drawRectangleButton;

    private final List<Figure> figures = new ArrayList<>();
    private Figure selectedFigure = null;
    private double offsetX, offsetY;

    private final Random random = new Random();

    @FXML
    public void initialize() {

        drawCircleButton.setOnAction(e -> {
            try {
                drawRandomCircle();
            } catch (IncorrectValueException ex) {
                throw new RuntimeException(ex);
            }
        });
        drawRectangleButton.setOnAction(e -> drawRandomRectangle());

        canvas.setOnMousePressed(this::handleMousePressed);
        canvas.setOnMouseDragged(this::handleMouseDragged);
        canvas.setOnMouseReleased(this::handleMouseReleased);
        canvas.setOnMouseClicked(this::handleMouseClicked);
    }

    private void drawRandomCircle() throws IncorrectValueException {
        double radius = 10 + random.nextDouble() * 50;
        double x = random.nextDouble() * (canvas.getWidth() - 2 * radius); // Учитываем размеры круга
        double y = random.nextDouble() * (canvas.getHeight() - 2 * radius); // Учитываем размеры круга
        Color color = generateRandomColor();

        Circle circle = new Circle(x, y, radius, color);
        figures.add(circle);
        redraw();
    }

    private void drawRandomRectangle() {
        double width = 10 + random.nextDouble() * 80;
        double height = 10 + random.nextDouble() * 80;
        double x = random.nextDouble() * (canvas.getWidth() - width); // Учитываем размеры прямоугольника
        double y = random.nextDouble() * (canvas.getHeight() - height); // Учитываем размеры прямоугольника
        Color color = generateRandomColor();

        Rectangle rectangle = new Rectangle(x, y, width, height, color);
        figures.add(rectangle);
        redraw();
    }

    private void redraw() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        for (Figure figure : figures) {
            figure.draw(gc);
        }
    }

    private Color generateRandomColor() {
        return Color.color(random.nextDouble(), random.nextDouble(), random.nextDouble());
    }

    private void handleMousePressed(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            double mouseX = event.getX();
            double mouseY = event.getY();

            selectedFigure = null;
            for (int i = figures.size() - 1; i >= 0; i--) {
                Figure figure = figures.get(i);
                if (figure.contains(mouseX, mouseY)) {
                    selectedFigure = figure;
                    offsetX = mouseX - figure.x;
                    offsetY = mouseY - figure.y;
                    break;
                }
            }

            if (selectedFigure != null) {
                figures.remove(selectedFigure);
                figures.add(selectedFigure);
                redraw();
            }
        }
    }

    private void handleMouseDragged(MouseEvent event) {
        if (selectedFigure != null && event.getButton() == MouseButton.PRIMARY) {
            double newX = event.getX() - offsetX;
            double newY = event.getY() - offsetY;

            double minX = 0;
            double minY = 0;
            double maxX = canvas.getWidth() - selectedFigure.getLength();
            double maxY = canvas.getHeight() - selectedFigure.getHeight();

            selectedFigure.x = Math.max(minX, Math.min(newX, maxX));
            selectedFigure.y = Math.max(minY, Math.min(newY, maxY));

            figures.remove(selectedFigure);
            figures.add(selectedFigure);

            redraw();
        }
    }

    private void handleMouseReleased(MouseEvent event) {
        selectedFigure = null;
    }

    private void handleMouseClicked(MouseEvent event) {
        if (event.getButton() == MouseButton.SECONDARY) {
            double mouseX = event.getX();
            double mouseY = event.getY();

            for (int i = figures.size() - 1; i >= 0; i--) {
                Figure figure = figures.get(i);
                if (figure.contains(mouseX, mouseY)) {
                    figure.setColor(generateRandomColor());
                    redraw();
                    return;
                }
            }
        }
    }
}
