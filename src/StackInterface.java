public interface StackInterface<E>
{
    public void push(E o);
    public E peek();
    public E pop();
    public E get(int x);
    public int size();
    public boolean empty();
    public void clear();
}