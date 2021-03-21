    import java.util.ArrayList;
    import java.util.Iterator;

    public class Set<E> implements SetInterface<E> {
        private ArrayList<E> Data = new ArrayList<>();

        @Override
        public boolean add(E o) {
            if(Data.contains(o))
            {
                return false;
            }
            else{
                Data.add(o);
                return true;
            }

        }

        @Override
        public void clear() {
            Data.clear();
        }

        @Override
        public boolean contains(E o) {
            if (Data.contains(o))
                return true;
            else
                return false;
        }

        @Override
        public boolean isEmpty() {
            if (Data.size() == 0)
                return true;
            else
                return false;
        }

        @Override
        public Iterator<E> iterator() {
            return Data.iterator();
        }


        @Override
        public boolean remove(E o) {
            if (Data.contains(o)) {
                Data.remove(o);
                return true;
            } else {
                return false;
            }
        }

        @Override
        public int size() {
            return Data.size();
        }

        @Override
        public Object[] toArray() {
            Object Obj[] = new Object[Data.size()];
            for(int i = 0; i < Data.size(); i++)
            {
                Obj[i] = Data.get(i);
            }
            return Obj;

        }
    }

