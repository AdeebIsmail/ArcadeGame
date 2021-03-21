public class Piece
{
    private int xPos;
    private int yPos;
    private int size; // Ranges from 1-7, 1 is smallest 7 is largest
    private boolean selected; // True if top piece on column selected
    private int tower; // Number corresponds to tower #. 1, 2, 3
    private int width;
    private int height;

    public Piece(int xPos, int yPos, int size, int tower, int width, int height) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.size = size;
        this.tower = tower;
        this.width = width;
        this.height = height;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTower() {
        return tower;
    }

    public void setTower(int tower) {
        this.tower = tower;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
