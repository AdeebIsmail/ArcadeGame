public class IceFloe
{
    private boolean isWater;
    private boolean isOne = false;
    private boolean isTwo = false;
    private boolean isThree = false;
    private int hasPenguin = 0; //0 is false     |     1 is Orange     |     2 is Yellow
    private boolean isActivated;

    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean activated) {
        isActivated = activated;
    }

    public int getHasPenguin() {
        return hasPenguin;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    private boolean selected;

    public boolean isWater() {
        return isWater;
    }

    public void setWater(boolean water) {
        isWater = water;
    }

    public boolean isOne() {
        return isOne;
    }

    public void setOne(boolean one) {
        isOne = one;
    }

    public boolean isTwo() {
        return isTwo;
    }

    public void setTwo(boolean two) {
        isTwo = two;
    }

    public boolean isThree() {
        return isThree;
    }

    public void setThree(boolean three) {
        isThree = three;
    }

    public int isHasPenguin() {
        return hasPenguin;
    }

    public void setHasPenguin(int hasPenguin) {
        this.hasPenguin = hasPenguin;
    }

    public String toString()
    {
        if (isOne)
            return "1";
        else if (isTwo)
            return "2";
        else if (isThree)
            return "3";
        return "*";
    }
}
