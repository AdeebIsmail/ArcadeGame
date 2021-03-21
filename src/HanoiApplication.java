//Kshitij Kapoor. Pd 4. 10/24/19. Computer Science II. Mr.Tully.
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.awt.*;
import java.util.*;

public class HanoiApplication extends Application
{
    private GraphicsContext gc;
    private static int numOfRings = 5;
    private MyStack<Integer> tower1 = new MyStack<>();
    private MyStack<Integer> tower2 = new MyStack<>();
    private MyStack<Integer> tower3 = new MyStack<>();
    private int diskWidth;
    private int diskHeight;
    private int totalDiskHeight = 200;
    private int maxDiskWidth = 150;
    private int minDiskWidth = 50;
    private int selectedTower;
    private boolean selected;
    private int moveCounter;
    private int status = 0;
    public static void main(String[] args)
    {

        launch();
    }
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        for (int i = numOfRings; i > 0; i--)
        {
            tower1.push(i);
        }

        Group group = new Group();
        Canvas canvas = new Canvas(500, 300);
        canvas.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (status == 0)
                    keyEvent(event);
            }
        });
        // adds a canvas to a group
        group.getChildren().add(canvas);
        // creates a new scene that takes in group
        Scene scene = new Scene(group);
        // Sets the scene
        primaryStage.setScene(scene);
        // Calls draw stuff
        gc = canvas.getGraphicsContext2D();
        draw(gc);
        canvas.requestFocus();
        // shows the window
        primaryStage.show();
    }

    private void draw(GraphicsContext gc)
    {
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, 1000, 1000);
        gc.setFill(Color.BLACK);
        if (selected) {
            if (selectedTower == 1) {
                gc.setFill(Color.RED);
                gc.fillRect(119, 40, 8, 400);
                gc.setFill(Color.BLACK);
                gc.fillRect(119 + 8 + 119, 40, 8, 400);
                gc.fillRect(3 * (119 + 8), 40, 8, 400);
            } else if (selectedTower == 2) {
                gc.setFill(Color.RED);
                gc.fillRect(119 + 8 + 119, 40, 8, 400);
                gc.setFill(Color.BLACK);
                gc.fillRect(119, 40, 8, 400);
                gc.fillRect(3 * (119 + 8), 40, 8, 400);
            } else if (selectedTower == 3) {
                gc.setFill(Color.RED);
                gc.fillRect(3 * (119 + 8), 40, 8, 400);
                gc.setFill(Color.BLACK);
                gc.fillRect(119, 40, 8, 400);
                gc.fillRect(119 + 8 + 119, 40, 8, 400);
            }
        }
        else
        {
            gc.setFill(Color.BLACK);
            gc.fillRect(119, 40, 8, 400);
            gc.fillRect(119 + 8 + 119, 40, 8, 400);
            gc.fillRect(3 * (119 + 8), 40, 8, 400);
        }
        gc.setFill(Color.LIGHTGREEN);
        gc.setStroke(Color.BLACK);
        diskHeight = totalDiskHeight / numOfRings;

        int diskWidthChange = (maxDiskWidth - minDiskWidth) / (numOfRings);
        for (int i = tower1.size() - 1; i >= 0; i--)
        {
            diskWidth = minDiskWidth + (i) * diskWidthChange;
            int temp = 105 - (tower1.get(i) * (50 / numOfRings));
            gc.fillRect(temp, 280 - ((tower1.size()-i) * diskHeight), (123 - temp) * 2, diskHeight);
            gc.strokeRect(temp, 280 - ((tower1.size() - i) * diskHeight), (123 - temp) * 2, diskHeight);
        }
        for (int i = tower2.size() - 1; i >= 0; i--)
        {
            diskWidth = minDiskWidth + (i) * diskWidthChange;
            int temp = 232 - (tower2.get(i) * (50 / numOfRings));
            gc.fillRect(temp, 280 - ((tower2.size() - i) * diskHeight), (250 - temp) * 2, diskHeight);
            gc.strokeRect(temp, 280 - ((tower2.size() - i) * diskHeight), (250 - temp) * 2, diskHeight);
        }
        for (int i = tower3.size() - 1; i >= 0; i--)
        {
            diskWidth = minDiskWidth + (i) * diskWidthChange;
            int temp = 367 - (tower3.get(i) * (50 / numOfRings));
            gc.fillRect(temp, 280 - ((tower3.size() - i) * diskHeight), (385 - temp) * 2, diskHeight);
            gc.strokeRect(temp, 280 - ((tower3.size() - i) * diskHeight), (385 - temp) * 2, diskHeight);
        }
        gc.setFill(Color.BLACK);
        gc.fillRect(0,280,1000,1000);
        gc.setFont(new Font("Times New Roman", 22));
        gc.setFill(Color.BLUE);
        if (status == 1)
        {
            gc.fillText("Congratulations! You won in " + moveCounter + " move(s)!", 63 , 20);
        }
    }

    public void keyEvent(KeyEvent e) {
        String s = e.getCharacter();
        if (s.equals("1"))
        {
            if (!selected)
            {
                selectedTower = 1;
                selected = true;
            }
            else if (selectedTower == 2)
            {
                if (!tower2.empty() && (tower1.empty() || tower1.peek() > tower2.peek()))
                {
                    tower1.push(tower2.pop());
                    selectedTower = 0;
                    selected = false;
                    moveCounter++;

                }
                else
                {
                    selectedTower = 1;
                    selected = true;
                }
            }
            else if (selectedTower == 3)
            {
                if (!tower3.empty() && (tower1.empty() || tower1.peek() > tower3.peek()))
                {
                    tower1.push(tower3.pop());
                    selectedTower = 0;
                    selected = false;
                    moveCounter++;
                }
                else
                {
                    selectedTower = 1;
                    selected = true;
                }
            }
        }
        else if (s.equals("2"))
        {
            if (!selected)
            {
                selectedTower = 2;
                selected = true;
            }
            else if (selectedTower == 1)
            {
                if (!tower1.empty() && (tower2.empty() || tower2.peek() > tower1.peek()))
                {
                    tower2.push(tower1.pop());
                    selectedTower = 0;
                    selected = false;
                    moveCounter++;
                }
                else
                {
                    selectedTower = 2;
                    selected = true;
                }
            }
            else if (selectedTower == 3)
            {
                if (!tower3.empty() && (tower2.empty() || tower2.peek() > tower3.peek()))
                {
                    tower2.push(tower3.pop());
                    selectedTower = 0;
                    selected = false;
                    moveCounter++;
                }
                else
                {
                    selectedTower = 2;
                    selected = true;
                }
            }
        }
        else if (s.equals("3"))
        {
            if (!selected)
            {
                selectedTower = 3;
                selected = true;
            }
            else if (selectedTower == 1)
            {
                if (!tower1.empty() && (tower3.empty() || tower3.peek() > tower1.peek()))
                {
                    tower3.push(tower1.pop());
                    selectedTower = 0;
                    selected = false;
                    moveCounter++;
                }
                else
                {
                    selectedTower = 3;
                    selected = true;
                }
            }
            else if (selectedTower == 2)
            {
                if (!tower2.empty() && (tower3.empty() || tower3.peek() > tower2.peek()))
                {
                    tower3.push(tower2.pop());
                    selectedTower = 0;
                    selected = false;
                    moveCounter++;
                }
                else
                {
                    selectedTower = 3;
                    selected = true;
                }
            }
        }
        if (tower3.size() == numOfRings)
            status = 1;
        draw(gc);
    }
}
