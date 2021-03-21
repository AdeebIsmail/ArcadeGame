public class WumpusMap {

    public static final int NUM_ROWS = 10;

    public static final int NUM_COLUMNS = 10;

    public static final int NUM_PITS = 10;

    private WumpusSquare[][] grid = new WumpusSquare[10][10];

    private int ladderC;

    private int ladderR;

    public WumpusMap() {
        createMap();
    }

    public void createMap() {
        grid = new WumpusSquare[10][10];
        int pitCount = 0;
        boolean goldPlaced = false;
        boolean ladderPlaced = false;
        boolean wumpusPlaced = false;


        for (int r1 = 0; r1 < grid.length; r1++) {
            for (int c1 = 0; c1 < grid[0].length; c1++) {
                grid[r1][c1] = new WumpusSquare();
            }
        }



        for (int x1 = 0; x1 < NUM_PITS;) {
            int r = (int) (Math.random() * 10);
            int c = (int) (Math.random() * 10);
            if (!grid[r][c].isGold() && !grid[r][c].isWumpus() && !grid[r][c].isLadder() && !grid[r][c].isPit() && pitCount <= NUM_PITS) {
                pitCount++;
                grid[r][c].setPit(true);
                if (r + 1 < 10) {
                    grid[r + 1][c].setBreeze(true);
                }
                if (r - 1 >= 0) {
                    grid[r - 1][c].setBreeze(true);
                }
                if (c + 1 < 10) {
                    grid[r][c + 1].setBreeze(true);
                }
                if (c - 1 >= 0) {
                    grid[r][c - 1].setBreeze(true);
                }

                x1++;
            }
        }
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[0].length; y++) {
                int r = (int) (Math.random() * 10);
                int c = (int) (Math.random() * 10);

                if (!grid[r][c].isPit() && !grid[r][c].isLadder() && !grid[r][c].isWumpus() && !goldPlaced) {
                    grid[r][c].setGold(true);
                    goldPlaced = true;
                }
                if (!grid[r][c].isGold() && !grid[r][c].isWumpus() && !grid[r][c].isPit() && !ladderPlaced) {

                    grid[r][c].setLadder(true);
                    ladderC = c;
                    ladderR = r;
                    ladderPlaced = true;
                }
                if (!grid[r][c].isPit() && !grid[r][c].isLadder() &&!grid[r][c].isGold() && !wumpusPlaced)
                {
                    grid[r][c].setWumpus(true);
                    wumpusPlaced = true;

                    if (r + 1 < 10) {
                        grid[r + 1][c].setStench(true);
                    }
                    if (r - 1 >= 0) {
                        grid[r - 1][c].setStench(true);
                    }
                    if (c + 1 < 10) {
                        grid[r][c + 1].setStench(true);
                    }
                    if (c - 1 >= 0) {
                        grid[r][c - 1].setStench(true);
                    }
                }
            }
        }
    }


    public int getLadderCol() {
        return ladderC;
    }

    public int getLadderRow() {
        return ladderR;
    }

    public WumpusSquare getSquare(int col, int row) {
        return grid[row][col];
    }

    public String toString() {
        String stringGrid = "";
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (c == grid[0].length - 1)
                    stringGrid += "\n";

                stringGrid += grid[r][c].toString();
            }
        }

        return stringGrid;
    }

    public WumpusSquare[][] getGrid() {
        return grid;
    }
}
