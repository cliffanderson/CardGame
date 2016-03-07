package cardgame.simulation;

/**
 * Created by lawrencew on 3/3/2016.
 */
public class ADTTest {

    public static void main(String[] args)
    {
        Pile p = new Pile();
        p.add(1);
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
        System.out.println("get: "+p.getEntry(3));
        System.out.println("contains 15: "+p.contains(15));
        System.out.println("\n"+p.getLength());

    }





}