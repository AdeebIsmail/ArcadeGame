//Kshitij Kapoor. Tully. 4th.
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.security.Key;

public class FishApplication extends Application {

    private Image one_fish = new Image("FImages/one_fish.png");
    private Image orange = new Image("FImages/orange.png");
    private Image three_fish = new Image("FImages/three_fish.png");
    private Image two_fish = new Image("FImages/two_fish.png");
    private Image yellow = new Image("FImages/yellow.png");
    private FloeMap map = new FloeMap();
    private GraphicsContext gc;
    private boolean turn; //True for Orange, False for Yellow
    private boolean beginningPlacement = true;
    private int numOfTurns = 0;
    private int[] selectedData = {-1, -1};
    private double x, y;
    private int oscore, yscore = 0;
    private int numOfPeng = 0;
    private int numOfOrange;
    private int numOfYellow;
    private boolean onlyOrange;
    private boolean onlyYellow;


    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Sets the title for the scene
        primaryStage.setTitle("Driving along");
        // Creates a new group
        Group group = new Group();
        // Creates the canvas
        Canvas canvas = new Canvas(800, 600);
        canvas.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mouseEvent(event);
            }
        });
        canvas.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                String s = keyEvent.getCharacter();
                if (s.equals("n"))
                {
                    map.createMap();
                    yscore = 0;
                    oscore = 0;
                    beginningPlacement = true;
                    numOfTurns = 0;
                    selectedData[0] = -1;
                    selectedData[1] = -1;
                    turn = false;
                    onlyOrange = false;
                    onlyYellow = false;
                    numOfOrange = 0;
                    numOfYellow = 0;
                    numOfPeng = 0;
                    draw(gc);
                }
                else if (s.equals("p")) {
                    try {
                        if (selectedData[0] != -1 && selectedData[1] != -1) {
                            if (map.getGrid()[selectedData[0]][selectedData[1]].isHasPenguin() == 1)
                            {
                                numOfOrange++;

                            }
                            else if (map.getGrid()[selectedData[0]][selectedData[1]].isHasPenguin() == 2)
                            {
                                numOfYellow++;
                            }
                            if (numOfOrange == 4)
                            {
                                onlyYellow = true;
                                numOfOrange = 0;
                                turn = !turn;

                            }
                            if (numOfYellow == 4)
                            {
                                System.out.println(numOfYellow);
                                onlyOrange = true;
                                turn = !turn;


                            }
                            numOfPeng++;
                            map.getGrid()[selectedData[0]][selectedData[1]].setActivated(false);
                            draw(gc);
                            if (map.getGrid()[selectedData[0]][selectedData[1]].isHasPenguin() == 1) {
                                if (map.getGrid()[selectedData[0]][selectedData[1]].isOne()) {
                                    oscore++;
                                }
                            }
                            if (map.getGrid()[selectedData[0]][selectedData[1]].isHasPenguin() == 1) {
                                if (map.getGrid()[selectedData[0]][selectedData[1]].isTwo()) {
                                    oscore += 2;
                                }
                            }
                            if (map.getGrid()[selectedData[0]][selectedData[1]].isHasPenguin() == 1) {
                                if (map.getGrid()[selectedData[0]][selectedData[1]].isThree()) {
                                    oscore += 3;
                                }
                            }
                            if (map.getGrid()[selectedData[0]][selectedData[1]].isHasPenguin() == 2) {
                                if (map.getGrid()[selectedData[0]][selectedData[1]].isOne()) {
                                    yscore++;
                                }
                            }
                            if (map.getGrid()[selectedData[0]][selectedData[1]].isHasPenguin() == 2) {
                                if (map.getGrid()[selectedData[0]][selectedData[1]].isTwo()) {
                                    yscore += 2;
                                }
                            }
                            if (map.getGrid()[selectedData[0]][selectedData[1]].isHasPenguin() == 2) {
                                if (map.getGrid()[selectedData[0]][selectedData[1]].isThree()) {
                                    yscore += 3;
                                }
                            }
                            map.getGrid()[selectedData[0]][selectedData[1]].setHasPenguin(0);
                            selectedData[0] = -1;
                            selectedData[1] = -1;
                            draw(gc);
                        }
                    } catch (Exception E) {}
                }
            }
        });
        // Adds the children to the canvas
        group.getChildren().add(canvas);
        // Creates the scene
        Scene scene = new Scene(group);
        // Sets the scene to the stage
        primaryStage.setScene(scene);
        gc = canvas.getGraphicsContext2D();
        canvas.requestFocus();
        draw(gc);
        primaryStage.show();
    }

    private void mouseEvent(MouseEvent e) {

        try {
            if (numOfTurns > 7)
                beginningPlacement = false;

            x = e.getX() / 80;
            y = e.getY() / 80;
            if (beginningPlacement) {
                if (!turn && map.getGrid()[(int) x][(int) y].isHasPenguin() == 0 && map.getGrid()[(int) x][(int) y].isOne()) {
                    map.getGrid()[(int) x][(int) y].setHasPenguin(1);
                    if (!(onlyOrange || onlyYellow))
                    {
                        turn = !turn;
                    }
                    draw(gc);
                    numOfTurns++;
                } else if (turn && map.getGrid()[(int) x][(int) y].isHasPenguin() == 0 && map.getGrid()[(int) x][(int) y].isOne()) {
                    map.getGrid()[(int) x][(int) y].setHasPenguin(2);
                    if (!(onlyOrange || onlyYellow))
                    {
                        turn = !turn;
                    }
                    draw(gc);
                    numOfTurns++;
                }
            }
            if (map.getGrid()[(int) x][(int) y].isHasPenguin() == 2 && !turn && !beginningPlacement && map.getGrid()[(int)x][(int)y].isActivated()) {
                map.getGrid()[(int) x][(int) y].setSelected(true);
                selectedData[0] = (int) x;
                selectedData[1] = (int) y;
                draw(gc);

            }
            if (map.getGrid()[(int) x][(int) y].isHasPenguin() == 1 && turn && !beginningPlacement && map.getGrid()[(int)x][(int)y].isActivated()) {
                map.getGrid()[(int) x][(int) y].setSelected(true);
                selectedData[0] = (int) x;
                selectedData[1] = (int) y;
                draw(gc);

            }

        } catch (Exception E) { System.out.println("Out of Bounds"); }
            if (!beginningPlacement) {

                if (map.getGrid()[(int) x][(int) y].isHasPenguin() != 0) {
                    return;
                }
                if (selectedData[0] == (int) x && selectedData[1] == (int) y) {
                    selectedData[0] = -1;
                    selectedData[1] = -1;
                    map.getGrid()[(int)x][(int)y].setActivated(false);
                    draw(gc);
                    return;
                }
                if (selectedData[0] != -1 && selectedData[1] != -1) {
                    if ((int) x == selectedData[0] && (int) y == selectedData[1]) {
                        selectedData[0] = -1;
                        selectedData[1] = -1;
                    } else {
                        boolean good = true;
                        if ((int) y - selectedData[1] == -1 * ((int) x - selectedData[0]) && (int) x < selectedData[0]) {
                            for (int m = 1; m <= (int) x - selectedData[0]; m++) {
                                if (map.getGrid()[selectedData[0] - m][selectedData[1] + m].isHasPenguin() != 0 || !map.getGrid()[selectedData[0] - m][selectedData[1] + m].isActivated()) {
                                    good = false;
                                }
                            }
                            if (good) {
                                map.getGrid()[selectedData[0]][selectedData[1]].setActivated(false);
                                map.getGrid()[selectedData[0]][selectedData[1]].setHasPenguin(0);

                                if (!turn) {
                                    map.getGrid()[(int) x][(int) y].setHasPenguin(2);
                                    if (map.getGrid()[selectedData[0]][selectedData[1]].isOne()) {
                                        yscore++;
                                    } else if (map.getGrid()[selectedData[0]][selectedData[1]].isTwo()) {
                                        yscore += 2;
                                    } else {
                                        yscore += 3;
                                    }
                                } else {
                                    map.getGrid()[(int) x][(int) y].setHasPenguin(1);
                                    if (map.getGrid()[selectedData[0]][selectedData[1]].isOne()) {
                                        oscore++;
                                    } else if (map.getGrid()[selectedData[0]][selectedData[1]].isTwo()) {
                                        oscore += 2;
                                    } else {
                                        oscore += 3;
                                    }
                                }
                                map.getGrid()[selectedData[0]][selectedData[1]].setHasPenguin(0);
                                if (!(onlyOrange || onlyYellow))
                                {
                                    turn = !turn;
                                }
                                selectedData[0] = -1;
                                selectedData[1] = -1;

                            }
                        }
                        if ((int) y - selectedData[1] == -1 * ((int) x - selectedData[0]) && (int) x > selectedData[0]) {
                            for (int m = 1; m <= (int) x - selectedData[0]; m++) {
                                if (map.getGrid()[selectedData[0] + m][selectedData[1] - m].isHasPenguin() != 0 || !map.getGrid()[selectedData[0] + m][selectedData[1] - m].isActivated()) {
                                    good = false;
                                }
                            }
                            if (good) {
                                map.getGrid()[selectedData[0]][selectedData[1]].setActivated(false);
                                map.getGrid()[selectedData[0]][selectedData[1]].setHasPenguin(0);
                                if (!turn) {
                                    map.getGrid()[(int) x][(int) y].setHasPenguin(2);
                                    if (map.getGrid()[selectedData[0]][selectedData[1]].isOne()) {
                                        yscore++;
                                    } else if (map.getGrid()[selectedData[0]][selectedData[1]].isTwo()) {
                                        yscore += 2;
                                    } else {
                                        yscore += 3;
                                    }
                                } else {
                                    map.getGrid()[(int) x][(int) y].setHasPenguin(1);
                                    if (map.getGrid()[selectedData[0]][selectedData[1]].isOne()) {
                                        oscore++;
                                    } else if (map.getGrid()[selectedData[0]][selectedData[1]].isTwo()) {
                                        oscore += 2;
                                    } else {
                                        oscore += 3;
                                    }
                                }
                                map.getGrid()[selectedData[0]][selectedData[1]].setHasPenguin(0);
                                if (!(onlyOrange || onlyYellow))
                                {
                                    turn = !turn;
                                }
                                selectedData[0] = -1;
                                selectedData[1] = -1;
                            }
                        }

                        if ((int) y - selectedData[1] == ((int) x - selectedData[0]) && (int) y > selectedData[1]) {
                            for (int m = 1; m <= (int) x - selectedData[0]; m++) {
                                if (map.getGrid()[selectedData[0] + m][selectedData[1] + m].isHasPenguin() != 0 || !map.getGrid()[selectedData[0] + m][selectedData[1] + m].isActivated()) {
                                    good = false;
                                }
                            }
                            if (good) {
                                map.getGrid()[selectedData[0]][selectedData[1]].setActivated(false);
                                map.getGrid()[selectedData[0]][selectedData[1]].setHasPenguin(0);
                                if (!turn) {
                                    map.getGrid()[(int) x][(int) y].setHasPenguin(2);
                                    if (map.getGrid()[selectedData[0]][selectedData[1]].isOne()) {
                                        yscore++;
                                    } else if (map.getGrid()[selectedData[0]][selectedData[1]].isTwo()) {
                                        yscore += 2;
                                    } else {
                                        yscore += 3;
                                    }
                                } else {
                                    map.getGrid()[(int) x][(int) y].setHasPenguin(1);
                                    if (map.getGrid()[selectedData[0]][selectedData[1]].isOne()) {
                                        oscore++;
                                    } else if (map.getGrid()[selectedData[0]][selectedData[1]].isTwo()) {
                                        oscore += 2;
                                    } else {
                                        oscore += 3;
                                    }
                                }
                                map.getGrid()[selectedData[0]][selectedData[1]].setHasPenguin(0);
                                if (!(onlyOrange || onlyYellow))
                                {
                                    turn = !turn;
                                }
                                selectedData[0] = -1;
                                selectedData[1] = -1;
                            }
                        }
                        if ((int) y - selectedData[1] == ((int) x - selectedData[0]) && (int) y < selectedData[1]) {

                            for (int m = 1; m <= ((int) x - selectedData[0]) * -1; m++) {

                                if (map.getGrid()[selectedData[0] - m][selectedData[1] - m].isHasPenguin() != 0 || !map.getGrid()[selectedData[0] - m][selectedData[1] - m].isActivated()) {
                                    good = false;
                                }
                            }
                            if (good) {
                                map.getGrid()[selectedData[0]][selectedData[1]].setActivated(false);

                                map.getGrid()[selectedData[0]][selectedData[1]].setHasPenguin(0);
                                if (!turn) {
                                    map.getGrid()[(int) x][(int) y].setHasPenguin(2);
                                    if (map.getGrid()[selectedData[0]][selectedData[1]].isOne()) {
                                        yscore++;
                                    } else if (map.getGrid()[selectedData[0]][selectedData[1]].isTwo()) {
                                        yscore += 2;
                                    } else {
                                        yscore += 3;
                                    }
                                } else {
                                    map.getGrid()[(int) x][(int) y].setHasPenguin(1);
                                    if (map.getGrid()[selectedData[0]][selectedData[1]].isOne()) {
                                        oscore++;
                                    } else if (map.getGrid()[selectedData[0]][selectedData[1]].isTwo()) {
                                        oscore += 2;
                                    } else {
                                        oscore += 3;
                                    }
                                }
                                map.getGrid()[selectedData[0]][selectedData[1]].setHasPenguin(0);
                                if (!(onlyOrange || onlyYellow))
                                {
                                    turn = !turn;
                                }
                                selectedData[0] = -1;
                                selectedData[1] = -1;
                            }
                        }
                        if ((int) x == selectedData[0] && (int) y < selectedData[1]) {
                            for (int m = 1; m <= ((int) y - selectedData[1]) * -1; m++) {
                                if (map.getGrid()[selectedData[0]][selectedData[1] - m].isHasPenguin() != 0 || !map.getGrid()[selectedData[0]][selectedData[1] - m].isActivated()) {
                                    good = false;
                                }
                            }
                            if (good) {
                                map.getGrid()[selectedData[0]][selectedData[1]].setActivated(false);

                                map.getGrid()[selectedData[0]][selectedData[1]].setHasPenguin(0);
                                if (!turn) {
                                    map.getGrid()[(int) x][(int) y].setHasPenguin(2);
                                    if (map.getGrid()[selectedData[0]][selectedData[1]].isOne()) {
                                        yscore++;
                                    } else if (map.getGrid()[selectedData[0]][selectedData[1]].isTwo()) {
                                        yscore += 2;
                                    } else {
                                        yscore += 3;
                                    }
                                } else {
                                    map.getGrid()[(int) x][(int) y].setHasPenguin(1);
                                    if (map.getGrid()[selectedData[0]][selectedData[1]].isOne()) {
                                        oscore++;
                                    } else if (map.getGrid()[selectedData[0]][selectedData[1]].isTwo()) {
                                        oscore += 2;
                                    } else {
                                        oscore += 3;
                                    }
                                }
                                map.getGrid()[selectedData[0]][selectedData[1]].setHasPenguin(0);
                                if (!(onlyOrange || onlyYellow))
                                {
                                    turn = !turn;
                                }
                                selectedData[0] = -1;
                                selectedData[1] = -1;
                            }
                        }
                        if ((int) x == selectedData[0] && (int) y > selectedData[1]) {
                            System.out.print((int) y - selectedData[1]);
                            for (int m = 1; m <= ((int) y - selectedData[1]); m++) {

                                if (map.getGrid()[selectedData[0]][selectedData[1] + m].isHasPenguin() != 0 || !map.getGrid()[selectedData[0]][selectedData[1] + m].isActivated()) {
                                    good = false;
                                }
                            }
                            if (good) {
                                map.getGrid()[selectedData[0]][selectedData[1]].setHasPenguin(0);
                                map.getGrid()[selectedData[0]][selectedData[1]].setActivated(false);

                                if (!turn) {
                                    map.getGrid()[(int) x][(int) y].setHasPenguin(2);
                                    if (map.getGrid()[selectedData[0]][selectedData[1]].isOne()) {
                                        yscore++;
                                    } else if (map.getGrid()[selectedData[0]][selectedData[1]].isTwo()) {
                                        yscore += 2;
                                    } else {
                                        yscore += 3;
                                    }
                                } else {
                                    map.getGrid()[(int) x][(int) y].setHasPenguin(1);
                                    if (map.getGrid()[selectedData[0]][selectedData[1]].isOne()) {
                                        oscore++;
                                    } else if (map.getGrid()[selectedData[0]][selectedData[1]].isTwo()) {
                                        oscore += 2;
                                    } else {
                                        oscore += 3;
                                    }
                                }
                                map.getGrid()[selectedData[0]][selectedData[1]].setHasPenguin(0);
                                if (!(onlyOrange || onlyYellow))
                                {
                                    turn = !turn;
                                }
                                selectedData[0] = -1;
                                selectedData[1] = -1;
                            }
                        }
                        if ((int) y == selectedData[1] && (int) x > selectedData[0]) {
                            System.out.print((int) y - selectedData[1]);
                            for (int m = 1; m <= ((int) x - selectedData[0]); m++) {

                                if (map.getGrid()[selectedData[0] + m][selectedData[1]].isHasPenguin() != 0 || !map.getGrid()[selectedData[0] + m][selectedData[1]].isActivated()) {
                                    good = false;
                                }
                            }
                            if (good) {
                                map.getGrid()[selectedData[0]][selectedData[1]].setHasPenguin(0);
                                map.getGrid()[selectedData[0]][selectedData[1]].setActivated(false);

                                if (!turn) {
                                    map.getGrid()[(int) x][(int) y].setHasPenguin(2);
                                    if (map.getGrid()[selectedData[0]][selectedData[1]].isOne()) {
                                        yscore++;
                                    } else if (map.getGrid()[selectedData[0]][selectedData[1]].isTwo()) {
                                        yscore += 2;
                                    } else {
                                        yscore += 3;
                                    }
                                } else {
                                    map.getGrid()[(int) x][(int) y].setHasPenguin(1);
                                    if (map.getGrid()[selectedData[0]][selectedData[1]].isOne()) {
                                        oscore++;
                                    } else if (map.getGrid()[selectedData[0]][selectedData[1]].isTwo()) {
                                        oscore += 2;
                                    } else {
                                        oscore += 3;
                                    }
                                }
                                map.getGrid()[selectedData[0]][selectedData[1]].setHasPenguin(0);
                                if (!(onlyOrange || onlyYellow))
                                {
                                    turn = !turn;
                                }
                                selectedData[0] = -1;
                                selectedData[1] = -1;
                            }
                        }
                        if ((int) y == selectedData[1] && (int) x < selectedData[0]) {
                            System.out.print((int) y - selectedData[1]);
                            for (int m = 1; m <= ((int) x - selectedData[0]) * -1; m++) {

                                if (map.getGrid()[selectedData[0] - m][selectedData[1]].isHasPenguin() != 0 || !map.getGrid()[selectedData[0] - m][selectedData[1]].isActivated()) {
                                    good = false;
                                }
                            }
                            if (good) {
                                map.getGrid()[selectedData[0]][selectedData[1]].setHasPenguin(0);
                                map.getGrid()[selectedData[0]][selectedData[1]].setActivated(false);
                                map.getGrid()[selectedData[0]][selectedData[1]].setHasPenguin(0);

                                if (!turn) {
                                    map.getGrid()[(int) x][(int) y].setHasPenguin(2);
                                    if (map.getGrid()[selectedData[0]][selectedData[1]].isOne()) {
                                        yscore++;
                                    } else if (map.getGrid()[selectedData[0]][selectedData[1]].isTwo()) {
                                        yscore += 2;
                                    } else {
                                        yscore += 3;
                                    }
                                } else {
                                    map.getGrid()[(int) x][(int) y].setHasPenguin(1);
                                    if (map.getGrid()[selectedData[0]][selectedData[1]].isOne()) {
                                        oscore++;
                                    } else if (map.getGrid()[selectedData[0]][selectedData[1]].isTwo()) {
                                        oscore += 2;
                                    } else {
                                        oscore += 3;
                                    }
                                }
                                map.getGrid()[selectedData[0]][selectedData[1]].setHasPenguin(0);
                                if (!(onlyOrange || onlyYellow))
                                {
                                    turn = !turn;
                                }
                                selectedData[0] = -1;
                                selectedData[1] = -1;
                            }
                        }
                        draw(gc);

                    }

                }

            }

        draw(gc);
    }

    public void draw(GraphicsContext gc) {
        gc.setFill(Color.MIDNIGHTBLUE);
        gc.fillRect(0, 0, 800, 600);
        gc.setFont(new Font("Times New Roman", 20));
        gc.setFill(Color.WHITE);
        if (beginningPlacement) {
            if (!turn) {
                gc.fillText("Orange's Turn", 20, 510);
            }
            if (turn) {
                gc.fillText("Yellow's Turn", 20, 510);
            }
        } else {
            if (turn) {
                gc.fillText("Orange's Turn", 20, 510);;
            }
            if (!turn) {
                gc.fillText("Yellow's Turn", 20, 510);
            }
        }
        gc.drawImage(orange, 200, 510, 31, 64);
        gc.fillText("" + oscore, 230, 510);
        gc.drawImage(yellow, 500, 510, 31,64);
        gc.fillText("" + yscore, 530, 510);

        for (int i = 0; i < map.getGrid().length; i++) {
            for (int j = 0; j < map.getGrid()[0].length; j++) {
                if (map.getGrid()[i][j].isActivated()) {
                    if (map.getGrid()[i][j].isOne())
                        gc.drawImage(one_fish, i * 80, j * 80, 80, 80);
                    if (map.getGrid()[i][j].isTwo())
                        gc.drawImage(two_fish, i * 80, j * 80, 80, 80);
                    if (map.getGrid()[i][j].isThree())
                        gc.drawImage(three_fish, i * 80, j * 80, 80, 80);
                    if (map.getGrid()[i][j].isHasPenguin() == 1) {
                        gc.drawImage(orange, i * 80 + 24.5, j * 80 + 8, 31, 64);
                    } else if (map.getGrid()[i][j].isHasPenguin() == 2) {
                        gc.drawImage(yellow, i * 80 + 24.5, j * 80 + 8, 31, 64);
                    }
                }
                if (map.getGrid()[i][j].isHasPenguin() != 0)
                {

                }
                if (map.getGrid()[i][j].isSelected()) {
                    gc.setLineWidth(6);
                    gc.setStroke(Color.YELLOW);
                    if (selectedData[0] == i && selectedData[1] == j) {
                        if (turn && map.getGrid()[i][j].isHasPenguin() == 1) {
                            gc.strokeOval(i * 80 + 5, j * 80 + 5, 70, 70);
                        } else if (!turn && map.getGrid()[i][j].isHasPenguin() == 2) {
                            gc.strokeOval(i * 80 + 5, j * 80 + 5, 70, 70);
                        }
                    }
                }
            }
        }
        System.out.println(numOfPeng);
        if (numOfPeng == 8)
        {
            if (yscore > oscore)
            {
                gc.drawImage(yellow, 200, 50, 500, 500);
            }
            else if (oscore > yscore)
            {
                gc.drawImage(orange, 200, 50, 500,500);
            }
            else if (oscore == yscore)
            {
                gc.drawImage(orange, 50, 50, 350, 500);
                gc.drawImage(yellow, 410, 50, 350, 500);
            }
        }
    }
}