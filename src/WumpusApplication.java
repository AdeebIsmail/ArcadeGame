import javafx.application.Application;
import javafx.event.Event;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.event.EventHandler;

public class WumpusApplication extends Application {

    public static final int PLAYING = 0;
    public static final int DEAD = 1;
    public static final int WON = 2;

    private int status;
    private WumpusPlayer player;
    private WumpusMap map = new WumpusMap();
    private GraphicsContext gc;
    private Image floor = new Image("floor.gif");
    private Image arrow = new Image("arrow.gif");
    private Image fog = new Image("black.gif");
    private Image breeze = new Image("breeze.gif");
    private Image deadwumpus = new Image("deadwumpus.png");
    private Image gold = new Image("gold.gif");
    private Image ladder = new Image("ladder.gif");
    private Image pit = new Image("pit.gif");
    private Image playerDown = new Image("playerDown.png");
    private Image playerLeft = new Image("playerLeft.png");
    private Image playerRight = new Image("playerRight.png");
    private Image playerUp = new Image("playerUp.png");
    private Image stench = new Image("stench.gif");
    private Image wumpus = new Image("wumpus.png");

    boolean cheatMode = false;
    boolean cheatPressed = false;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Wumpus World");

        Group group = new Group();

        Scene scene = new Scene(group);

        Canvas canvas = new Canvas(600, 800);

        group.getChildren().add(canvas);

        canvas.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                keyTyped(event);
            }
        });

        gc = canvas.getGraphicsContext2D();

        canvas.requestFocus();

        primaryStage.setScene(scene);

        reset();

        primaryStage.show();

    }


    public void paint(GraphicsContext gc) {

        if(status == DEAD || status == WON)
            return;
        for (int r = 0; r < map.getGrid().length; r++) {
            for (int c = 0; c < map.getGrid()[0].length; c++) {

                if (map.getGrid()[r][c].isLadder()) {
                    gc.drawImage(floor, r * 50 + 50, c * 50 + 50);
                    gc.drawImage(ladder, r * 50 + 50, c * 50 + 50);
                } else if (map.getGrid()[r][c].isGold()) {
                    if(player.isGold())
                    {
                        gc.drawImage(floor, r * 50 + 50, c * 50 + 50);
                    }
                    else
                    {
                        gc.drawImage(floor, r * 50 + 50, c * 50 + 50);
                        gc.drawImage(gold, r * 50 + 50, c * 50 + 50);
                    }
                } else if (map.getGrid()[r][c].isWumpus()) {
                    gc.drawImage(floor, r * 50 + 50, c * 50 + 50);
                    gc.drawImage(wumpus, r * 50 + 50, c * 50 + 50);
                }
                else if (map.getGrid()[r][c].isDeadWumpus()) {
                    gc.drawImage(floor, r * 50 + 50, c * 50 + 50);
                    gc.drawImage(deadwumpus, r * 50 + 50, c * 50 + 50);
                }
                else if (map.getGrid()[r][c].isPit()) {
                    gc.drawImage(floor, r * 50 + 50, c * 50 + 50);
                    gc.drawImage(pit, r * 50 + 50, c * 50 + 50);
                } else if (map.getGrid()[r][c].isStench()) {
                    gc.drawImage(floor, r * 50 + 50, c * 50 + 50);
                    gc.drawImage(stench, r * 50 + 50, c * 50 + 50);
                } else if (map.getGrid()[r][c].isBreeze()) {
                    gc.drawImage(floor, r * 50 + 50, c * 50 + 50);
                    gc.drawImage(breeze, r * 50 + 50, c * 50 + 50);
                } else if (map.getGrid()[r][c].toString().equals("*")) {
                    gc.drawImage(floor, r * 50 + 50, c * 50 + 50);
                }

                if(!map.getGrid()[r][c].isVisited())
                {
                    gc.drawImage(fog, r*50+50, c*50+50);
                }
            }
        }

    }

    public void paintCheatMode(GraphicsContext gc) {

        for(int r = 0; r < map.getGrid().length; r++)
        {
            for(int c = 0; c < map.getGrid()[0].length; c++)
            {
                if (map.getGrid()[r][c].isLadder()) {
                    gc.drawImage(floor, r * 50 + 50, c * 50 + 50);
                    gc.drawImage(ladder, r * 50 + 50, c * 50 + 50);
                }

                else if (map.getGrid()[r][c].isGold()) {
                    if(player.isGold())
                    {
                        gc.drawImage(floor, r * 50 + 50, c * 50 + 50);
                    }
                    else
                    {
                        gc.drawImage(floor, r * 50 + 50, c * 50 + 50);
                        gc.drawImage(gold, r * 50 + 50, c * 50 + 50);
                    }

                } else if (map.getGrid()[r][c].isWumpus()) {
                    gc.drawImage(floor, r * 50 + 50, c * 50 + 50);
                    gc.drawImage(wumpus, r * 50 + 50, c * 50 + 50);
                }
                else if (map.getGrid()[r][c].isDeadWumpus()) {
                    gc.drawImage(floor, r * 50 + 50, c * 50 + 50);
                    gc.drawImage(deadwumpus, r * 50 + 50, c * 50 + 50);
                }
                else if (map.getGrid()[r][c].isPit()) {
                    gc.drawImage(floor, r * 50 + 50, c * 50 + 50);
                    gc.drawImage(pit, r * 50 + 50, c * 50 + 50);
                } else if (map.getGrid()[r][c].isStench()) {
                    gc.drawImage(floor, r * 50 + 50, c * 50 + 50);
                    gc.drawImage(stench, r * 50 + 50, c * 50 + 50);
                } else if (map.getGrid()[r][c].isBreeze()) {
                    gc.drawImage(floor, r * 50 + 50, c * 50 + 50);
                    gc.drawImage(breeze, r * 50 + 50, c * 50 + 50);
                } else if (map.getGrid()[r][c].toString().equals("*")) {
                    gc.drawImage(floor, r * 50 + 50, c * 50 + 50);
                }
            }
        }

    }

    public void inventoryPaint(GraphicsContext gc)
    {
        //Inventory and Messages
        gc.setFill(Color.BLACK);
        gc.fillRect(0,600,600,200);

        gc.setFill(Color.GRAY);
        gc.fillRect(0,0,600,50);

        gc.setFill(Color.GRAY);
        gc.fillRect(0,550,600,50);

        gc.setFill(Color.GRAY);
        gc.fillRect(0,0,50,600);

        gc.setFill(Color.GRAY);
        gc.fillRect(550,0,50,600);

        gc.setFill(Color.RED);
        gc.setFont(Font.font(30));
        gc.fillText("Inventory", 100,625);

        if(player.isArrow())
        {
            gc.drawImage(arrow, 20, 650);
        }
        if(player.isGold())
        {
            gc.drawImage(gold, 100, 650);
        }


        gc.fillText("Messages", 350, 625);
        gc.setFill(Color.LIGHTBLUE);
        gc.setFont(Font.font(15));
        if(map.getGrid()[player.getRowPosition()][player.getColPosition()].isGold())
        {
            gc.fillText("You see a glimmer", 350,660);
        }
        if(map.getGrid()[player.getRowPosition()][player.getColPosition()].isBreeze())
        {
            gc.fillText("You feel a breeze", 350,680);
        }
        if(map.getGrid()[player.getRowPosition()][player.getColPosition()].isStench())
        {
            gc.fillText("You smell a stench", 350,700);
        }
        if(map.getGrid()[player.getRowPosition()][player.getColPosition()].isLadder())
        {
            gc.fillText("You bump into a ladder", 350,720);
        }
        if(map.getGrid()[player.getRowPosition()][player.getColPosition()].isWumpus())
        {
            gc.fillText("You were eaten by the Wumpus\nPress n to start a new game", 190,740);
            status = DEAD;
        }
        if(map.getGrid()[player.getRowPosition()][player.getColPosition()].isDeadWumpus())
        {
            gc.fillText("You smell a stench", 350,700);
        }
        if(map.getGrid()[player.getRowPosition()][player.getColPosition()].isPit())
        {
            gc.fillText("You fell down a pit to your death \nPress n to start a new game", 190,760);
            status = DEAD;
        }
    }


    public void reset() {
        status = PLAYING;
        map = new WumpusMap();
        player = new WumpusPlayer();
        player.setRowPosition(map.getLadderRow());
        player.setColPosition(map.getLadderCol());
        map.getGrid()[map.getLadderRow()][map.getLadderCol()].setVisited(true);
        if(!cheatMode){paint(gc);}
        else {paintCheatMode(gc);}
        inventoryPaint(gc);
        gc.drawImage(playerUp, map.getLadderRow()*50 +50, map.getLadderCol()*50 +50);

    }

    public void keyTyped(KeyEvent e) {

        gc.setFont(Font.font(15));
        gc.setFill(Color.LIGHTBLUE);

        if (e.getCode().getName().equals("N") && (status == DEAD || status == WON)) {
            reset();
        }

        else if(e.getCode().getName().equals("C") && player.isGold() && map.getGrid()[player.getRowPosition()][player.getColPosition()].isLadder())
        {

            gc.fillText("You Won!! \nPress n to start a new game", 190,770);
            status = WON;
        }

        if(status == DEAD || status == WON)
            return;






        if(e.getCode().getName().equals("W"))
        {
            if(player.getColPosition()-1 < 0)
            {
                return;
            }
            player.setColPosition(player.getColPosition()-1);
            map.getGrid()[player.getRowPosition()][player.getColPosition()].setVisited(true);

            if(!cheatMode){paint(gc);}
            else if(cheatMode){paintCheatMode(gc);}
            inventoryPaint(gc);
            gc.drawImage(playerUp, player.getRowPosition()*50+50, player.getColPosition()*50+50);
        }
        else if(e.getCode().getName().equals("S"))
        {
            if(player.getColPosition()+1 >= map.getGrid().length)
            {
                return;
            }
            player.setColPosition(player.getColPosition()+1);
            map.getGrid()[player.getRowPosition()][player.getColPosition()].setVisited(true);

            if(!cheatMode){paint(gc);}
            else if(cheatMode){paintCheatMode(gc);}
            inventoryPaint(gc);
            gc.drawImage(playerDown, player.getRowPosition()*50+50, player.getColPosition()*50+50);


        }
        else if(e.getCode().getName().equals("A"))
        {
            if(player.getRowPosition()-1 < 0)
            {
                return;
            }
            player.setRowPosition(player.getRowPosition()-1);
            map.getGrid()[player.getRowPosition()][player.getColPosition()].setVisited(true);

            if(!cheatMode){paint(gc);}
            else if(cheatMode){paintCheatMode(gc);}
            inventoryPaint(gc);
            gc.drawImage(playerLeft, player.getRowPosition()*50+50, player.getColPosition()*50+50);
        }
        else if(e.getCode().getName().equals("D"))
        {
            if(player.getRowPosition()+1 >= map.getGrid()[0].length)
            {
                return;
            }
            player.setRowPosition(player.getRowPosition()+1);
            map.getGrid()[player.getRowPosition()][player.getColPosition()].setVisited(true);
            if(!cheatMode){paint(gc);}
            else if(cheatMode){paintCheatMode(gc);}
            inventoryPaint(gc);
            gc.drawImage(playerRight, player.getRowPosition()*50+50, player.getColPosition()*50+50);
        }


        else if(e.getCode().getName().equals("P") && map.getGrid()[player.getRowPosition()][player.getColPosition()].isGold())
        {
            player.setGold(true);
            inventoryPaint(gc);
            if(!cheatMode){paint(gc);}
            else if(cheatMode){paintCheatMode(gc);}
            gc.drawImage(playerUp, player.getRowPosition()*50+50, player.getColPosition()*50+50);
        }



        else if(e.getCode().getName().equals("I"))
        {

            if(player.isArrow())
            {
                for(int x = 0; x < player.getColPosition(); x++)
                {
                    if(map.getGrid()[player.getRowPosition()][x].isWumpus())
                    {

                        map.getGrid()[player.getRowPosition()][x].setWumpus(false);

                        map.getGrid()[player.getRowPosition()][x].setDeadWumpus(true);
                    }

                }
                player.setArrow(false);
                if(!cheatMode){paint(gc);}
                else if(cheatMode){paintCheatMode(gc);}
                inventoryPaint(gc);
                for(int r = 0; r < map.getGrid().length; r ++)
                {
                    for(int c = 0; c < map.getGrid()[0].length; c ++)
                    {
                        if(map.getGrid()[player.getRowPosition()][c].isDeadWumpus())
                        {
                            gc.fillText("You hear a scream", 320 ,780);
                        }
                    }
                }
                gc.drawImage(playerUp, player.getRowPosition()*50 +50, player.getColPosition()*50+50);
            }


        }
        else if(e.getCode().getName().equals("K"))
        {
            if(player.isArrow())
            {
                for(int x = player.getColPosition(); x < map.getGrid()[0].length; x++)
                {
                    if(map.getGrid()[player.getRowPosition()][x].isWumpus())
                    {
                        map.getGrid()[player.getRowPosition()][x].setWumpus(false);

                        map.getGrid()[player.getRowPosition()][x].setDeadWumpus(true);

                    }

                }

                player.setArrow(false);
                if(!cheatMode){paint(gc);}
                else if(cheatMode){paintCheatMode(gc);}
                inventoryPaint(gc);
                for(int r = 0; r < map.getGrid().length; r ++)
                {
                    for(int c = 0; c < map.getGrid()[0].length; c ++)
                    {
                        if(map.getGrid()[player.getRowPosition()][c].isDeadWumpus())
                        {
                            gc.fillText("You hear a scream", 320 ,780);
                        }
                    }
                }
                gc.drawImage(playerDown, player.getRowPosition()*50 +50, player.getColPosition()*50+50);
            }

        }
        else if(e.getCode().getName().equals("J"))
        {
            if (player.isArrow()) {
                for(int x = 0; x < player.getRowPosition(); x++)
                {
                    if(map.getGrid()[x][player.getColPosition()].isWumpus())
                    {

                        map.getGrid()[x][player.getColPosition()].setWumpus(false);

                        map.getGrid()[x][player.getColPosition()].setDeadWumpus(true);


                    }
                }
                player.setArrow(false);
                if(!cheatMode){paint(gc);}
                else if(cheatMode){paintCheatMode(gc);}
                inventoryPaint(gc);
                for(int r = 0; r < map.getGrid().length; r ++)
                {
                    for(int c = 0; c < map.getGrid()[0].length; c ++)
                    {
                        if(map.getGrid()[r][player.getColPosition()].isDeadWumpus())
                        {
                            gc.fillText("You hear a scream", 320 ,780);
                        }
                    }
                }
                gc.drawImage(playerLeft, player.getRowPosition()*50 +50, player.getColPosition()*50+50);

            }

        }
        else if(e.getCode().getName().equals("L"))
        {
            if(player.isArrow())
            {
                for(int x = player.getRowPosition(); x < map.getGrid().length; x++)
                {
                    if(map.getGrid()[x][player.getColPosition()].isWumpus())
                    {

                        map.getGrid()[x][player.getColPosition()].setWumpus(false);

                        map.getGrid()[x][player.getColPosition()].setDeadWumpus(true);

                    }
                }
                player.setArrow(false);
                if(!cheatMode){paint(gc);}
                else if(cheatMode){paintCheatMode(gc);}
                inventoryPaint(gc);
                for(int r = 0; r < map.getGrid().length; r ++)
                {
                    for(int c = 0; c < map.getGrid()[0].length; c ++)
                    {
                        if(map.getGrid()[r][player.getColPosition()].isDeadWumpus())
                        {
                            gc.fillText("You hear a scream", 320 ,780);
                        }
                    }
                }
                gc.drawImage(playerRight, player.getRowPosition()*50 +50, player.getColPosition()*50+50);
            }

        }

        if(e.getCode().getName().equals("8") && !cheatPressed)
        {
            paintCheatMode(gc);
            gc.drawImage(playerUp, player.getRowPosition()*50 +50, player.getColPosition()*50 +50);
            cheatMode = true;
            cheatPressed = true;
        }
        else if(e.getCode().getName().equals("8") && cheatPressed)
        {
            paint(gc);
            gc.drawImage(playerUp, player.getRowPosition()*50 +50, player.getColPosition()*50 +50);
            cheatMode = false;
            cheatPressed = false;
        }
    }

}


