public class FloeMap {
    public static final int numOne = 30;
    public static final int numTwo = 20;
    public static final int numThree = 10;
    private IceFloe[][] grid = new IceFloe[10][6];

    public IceFloe[][] getGrid() {
        return grid;
    }

    public void setGrid(IceFloe[][] grid) {
        this.grid = grid;
    }

    public FloeMap()
    {
        createMap();
    }

    public void createMap()
    {
        for (int i = 0; i < grid.length; i++)
        {
            for (int j = 0; j < grid[0].length; j++)
            {
                grid[i][j] = new IceFloe();
                grid[i][j].setHasPenguin(0);
                grid[i][j].setActivated(true);
            }
        }

        int i = 0;
        while (i < numOne)
        {
            int xRand = (int)(Math.random() * 10);
            int yRand = (int)(Math.random() * 6);

            if (grid[xRand][yRand].toString().equals("1"))
            {
                continue;
            }
            grid[xRand][yRand].setThree(false);
            grid[xRand][yRand].setTwo(false);
            grid[xRand][yRand].setOne(true);
            i++;
            System.out.print("Hi");
        }
        int k = 0;
        System.out.println();
        while (k < numTwo)
        {
            int xRand = (int)(Math.random() * 10);
            int yRand = (int)(Math.random() * 6);

            if (grid[xRand][yRand].toString().equals("1") || grid[xRand][yRand].toString().equals("2"))
            {
                continue;
            }
            grid[xRand][yRand].setOne(false);
            grid[xRand][yRand].setThree(false);
            grid[xRand][yRand].setTwo(true);
            k++;
            System.out.print("Hi2");
        }
        int j = 0;
        while (j < numThree)
        {
            int xRand = (int)(Math.random() * 10);
            int yRand = (int)(Math.random() * 6);

            if (grid[xRand][yRand].toString().equals("1") || grid[xRand][yRand].toString().equals("2") || grid[xRand][yRand].toString().equals("3"))
            {
                continue;
            }
            grid[xRand][yRand].setOne(false);
            grid[xRand][yRand].setThree(true);
            grid[xRand][yRand].setTwo(false);
            j++;
        }
    }
    public String toString()
    { return ""; }
}
