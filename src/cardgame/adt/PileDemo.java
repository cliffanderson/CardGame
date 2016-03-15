package cardgame.adt;

import java.util.LinkedList;

public class PileDemo {

    public static void main(String[] args) {
        Pile pile = new Pile();
        pile.add(0, "a");
        pile.add(1, "b");
        pile.add(2, "c");
        pile.add(3, "d");

        Object[] arrayList = pile.toArray();

        for (int i = 0; i < arrayList.length; i++)
        System.out.println(arrayList[i]);

        LinkedList<String> list = new LinkedList<>();
    }
}
