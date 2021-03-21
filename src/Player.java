public class Player
{
    private boolean orangeOrYellow;
    private int score = 0;
    private int penguins = 0;

    public Player(boolean orangeOrYellow)
    {
        this.orangeOrYellow = orangeOrYellow;
    }

    public boolean isOrangeOrYellow() {
        return orangeOrYellow;
    }

    public void setOrangeOrYellow(boolean orangeOrYellow) {
        this.orangeOrYellow = orangeOrYellow;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getPenguins() {
        return penguins;
    }

    public void setPenguins(int penguins) {
        this.penguins = penguins;
    }
}
