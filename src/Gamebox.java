import javafx.application.Application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Gamebox {


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String game = reader.readLine();
        if (game.equals("Wumpus"))
        {
            new Thread(() -> Application.launch(WumpusApplication.class)).start();
        }
        else if (game.equals("Hanoi"))
        {
            new Thread(() -> Application.launch(HanoiApplication.class)).start();
        }
        else if (game.equals("Fish"))
        {
            new Thread(() -> Application.launch(FishApplication.class)).start();
        }
        else if (game.equals("Shape"))
        {
            new Thread(() -> Application.launch(ShapeApplication.class)).start();
        }
        else
        {
            System.out.println("That is not a game!");
        }

    }


}