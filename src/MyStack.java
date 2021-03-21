import java.util.ArrayList;

public class MyStack<E> implements StackInterface<E>
{
    ArrayList<E> x = new ArrayList<E>();
    @Override
    public void push(E o) {
        x.add(0, o);
    }

    @Override
    public E peek() {
        if (x.size() != 0)
            return x.get(0);
        else
            return null;
    }

    @Override
    public E pop() {
        if (x.size() > 0)
            return x.remove(0);
        return null;
    }

    @Override
    public boolean empty() {
        if (x.isEmpty())
            return true;
        else
            return false;
    }

    @Override
    public int size() {
        return x.size();
    }

    @Override
    public E get(int y) {
        if (x.size() > y && y >= 0)
            return x.get(y);
        else
            return null;
    }

    public void clear()
    {
        x.clear();
    }
}
