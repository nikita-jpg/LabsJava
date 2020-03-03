public class Stack <Item> {
    private int size, top;
    private Item[] stackArray;

    public Stack(int size)
    {
        this.size = size;
        stackArray = (Item[])new Object[size];
        top = -1;
    }

    public Stack()
    {
        size = 10;
        stackArray = (Item[])new Object[size];
        top = -1;
    }

    public void push(Item el)
    {
        if (top + 1 == size) resize(size * 2);
        stackArray[++top] = el;
    }

    public Item pop()
    {
        if (top+1 <= size / 4) resize(size / 2);
        return stackArray[top--];
    }

    private void resize(int newSize) {
        Item[] copy = (Item[]) new Object[newSize];
        System.arraycopy(stackArray, 0, copy, 0, top+1);
        stackArray = copy;
    }

    public Item showTop()
    {
        return stackArray[top];
    }

    public boolean isEmpty()
    {
        return top == -1;
    }

}