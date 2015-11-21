package Main;

import Modele.Graphe;
import Modele.Point;
import Utils.Cycle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Clement on 17/10/2015.
 */
public class Main {
    public static void main(String[] args){
        //Graphe g = Utils.Parseur.lectureFichierGraphe(args[0]);
        //testClone();
        Graphe g = Utils.Parseur.lectureFichierGraphe("Exemples/exemple0.graphe");
        System.out.println(g);
        try {
            System.out.println("Cycle "+Cycle.chercherCycle(g));
            Graphe g2 = Cycle.construireNouveauGraphe(g);
            System.out.println("Graphe nouveau :");
            System.out.println(g2);
            //System.out.println(Cycle.chercherCycles(g));
            System.out.println("Voici les fragments");
            System.out.println(Graphe.getFragments(g,g2));
            //System.out.println(Cycle.chercherCycle(g));
        } catch (Exception e){
            System.out.println("Pas de cycle lel");
        }
    }



    public static void testClone(){
        List<Point> test = new ArrayList<Point>();
        Point p = new Point("rrr");
        test.add(p);
        List<Point> test2 = new ArrayList(test);
        p.setNom("ff");
        test2.add(new Point("aaa"));
        System.out.println(test + "  et " + test2);
    }
}
