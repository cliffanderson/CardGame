package cardgame.adt;

import java.util.LinkedList;

public class PileDemo {

    public static void main(String[] args) {
        Pile pile = new Pile();
        pile.add("a");
        pile.add("b");
        pile.add("c");
        pile.add("d");
        pile.add(4, "virus");
        pile.add("heaven");
        pile.add(0, "first");
        pile.add(1, "second");
        pile.add("last");
        pile.add(3, "yoo");
        pile.add(2, "third");

        //pile.remove(4);
        //pile.replace(6, "domination");

        System.out.println(pile.contains("yoo"));

        Object[] arrayList = pile.toArray();

        for (int i = 0; i < arrayList.length; i++)
        System.out.println(arrayList[i]);

        LinkedList<String> list = new LinkedList<>();
    }
}
