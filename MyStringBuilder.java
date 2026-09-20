import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class MyStringBuilder {
    private char[] value = new char[16];
    private int count;
    private final Deque<Snapshot> history = new ArrayDeque<>();

    private static class Snapshot {
        private final char[] value;
        private final int count;

        Snapshot(char[] value, int count) {
            // TODO: сохрани КОПИЮ массива, не ссылку
            this.value = Arrays.copyOf(value, value.length);
            this.count = count;
        }
    }

    private void saveSnapshot() {
        // TODO: положить слепок текущего состояния в history
        history.push(new Snapshot(value, count));
    }
    

    /**
     * Откатывает последнее изменение.
     * Если история пуста, бросает IllegalStateException.
     */
    public void undo() {
        // TODO: если истории нет - решить, что делать
        // TODO: достать слепок и восстановить value и count
        if(history.isEmpty()){
            throw new IllegalStateException();
        }
        Snapshot snapshot = history.pop();
        this.value = snapshot.value;
        this.count = snapshot.count;
    }


    public MyStringBuilder append(char c) {
        saveSnapshot();
        // TODO: ensureCapacity, копирование символа, обновление count
        ensureCapacity(count + 1);
        value[count] = c;
        count++;
        return this;
    }

    public MyStringBuilder append(String str) {
        saveSnapshot();
        // TODO: ensureCapacity, копирование символов, обновление count
        ensureCapacity(count + str.length());
        str.getChars(0, str.length(), value, count);
        count += str.length();
        return this;
    }

    @Override public String toString() {return new String(value, 0, count);}
    
    public int length() {return count;}

    private void ensureCapacity(int minCapacity) {
        if(minCapacity > value.length){
            int newCapacity =  Math.max(minCapacity, value.length * 2 + 2);
            value = Arrays.copyOf(value, newCapacity);
        }
    }

    // insert, delete, toString, length ...

}