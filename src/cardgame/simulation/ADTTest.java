package cardgame.simulation;

import cardgame.adt.TeamLinkedList;

/**
 * Created by lawrencew on 3/3/2016.
 */
public class ADTTest {

    public static void main(String[] args)
    {
        TeamLinkedList p = new TeamLinkedList();
        p.add("poo");
        p.add(2);
        p.add(3);
        p.add(4);
        //p.add(3,10);
        p.add(1,40);
        p.replace(5,15);
        //p.replace(3,5);
        Object[] list = p.toArray();
        for(Object obj :list)
        {
            System.out.println(obj);
        }
        System.out.println("get: "+p.getEntry(0));
        System.out.println("contains 15: "+p.contains(15));
        System.out.println("\n"+p.getLength());

    }





}
