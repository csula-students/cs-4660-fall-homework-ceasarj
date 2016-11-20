package csula.cs4660.games.models;

/**
 * Created by eric on 10/30/16.
 */
public class MiniMaxState {
    private  int index;
    private  int value;

    public MiniMaxState(int index, int value) {
        this.index = index;
        this.value = value;
    }

    public void set(int value){
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MiniMaxState)) return false;

        MiniMaxState that = (MiniMaxState) o;

        return getIndex() == that.getIndex();

    }

    @Override
    public int hashCode() {
        return getIndex();
    }
}
