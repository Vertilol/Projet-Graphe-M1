package Utils;

import Modele.Graphe;
import Modele.Point;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * Created by Vladimir on 21/10/2015.
 */
public class Cycle {

    //version qui cherche un seul cycle
    public static List<Point> chercherCycle(Graphe graphe) throws Exception {
        int source = 0;
        for(source = 0 ; source < graphe.getTaille() ; source++) {
            List<Point> cycle = null;
            Stack<Point> pile = new Stack<Point>();
            Point pointCourant = null;
            Point pointVisite = null;

            //Initialisation parcours
            graphe.inialiserParcours();
            Point p = graphe.getPoint(source);
            p.setEtatParcoursVisite();
            pile.push(p);

            //Parcours
            while (pile.size() != 0) {
                pointCourant = pile.pop();
                Iterator<Point> it = pointCourant.getVoisins().iterator();
                while (it.hasNext()) {
                    pointVisite = it.next();
                    if (pointVisite.estNonAtteint()) {
                        pointVisite.setEtatParcoursVisite();
                        pointVisite.setParent(pointCourant);
                        pile.push(pointVisite);
                    } else if (pointVisite.estVisite() && pointVisite != pointCourant.getParent()) {
                        //cycle detecte
                        //on recupere le cycle que l'on vient de trouver
                        return pointCourant.remonterCycle(pointVisite);
                    }
                }
                pointCourant.setEtatParcoursTraite();
            }
        }


        return null;
    }

    //version qui cherche tout les cycles
    public static List<List<Point>> chercherCycles(Graphe graphe) throws Exception {
        int source = 0;
        List<List<Point>> cycleListe = new ArrayList<List<Point>>();
        for(source = 0 ; source < graphe.getTaille() ; source++) {
            List<Point> cycle = null;
            Stack<Point> pile = new Stack<Point>();
            Point pointCourant = null;
            Point pointVisite = null;

            //Initialisation parcours
            graphe.inialiserParcours();
            Point p = graphe.getPoint(source);
            p.setEtatParcoursVisite();
            pile.push(p);


            //Parcours
            while (pile.size() != 0) {
                pointCourant = pile.pop();
                Iterator<Point> it = pointCourant.getVoisins().iterator();
                while (it.hasNext()) {
                    pointVisite = it.next();
                    if (pointVisite.estNonAtteint()) {
                        pointVisite.setEtatParcoursVisite();
                        pointVisite.setParent(pointCourant);
                        pile.push(pointVisite);
                    } else if (pointVisite.estVisite() && pointVisite != pointCourant.getParent()) {
                        //cycle detecte
                        //on recupere le cycle que l'on vient de trouver
                        List<List<Point>> nouvelleListeCycle = pointCourant.remonterCycles(pointVisite);
                        Iterator<List<Point>> it2 = nouvelleListeCycle.iterator();
                        while(it2.hasNext()){
                            cycle = it2.next();
                            if (cycleDejaPresent(cycleListe, cycle)) {
                                cycleListe.add(cycle);
                            }
                        }

                    }
                }
                pointCourant.setEtatParcoursTraite();
            }
        }


        return cycleListe;
    }

    private static boolean cycleDejaPresent(List<List<Point>> listeCycle, List<Point> cycle){
        Iterator<List<Point>> it = listeCycle.iterator();
        while(it.hasNext()){
            if(cycleEqualsCycle(it.next(),cycle)){
                return false;
            }
        }
        return true;
    }
    private static boolean cycleEqualsCycle(List<Point> liste1, List<Point> liste2){
        Iterator<Point> it = liste1.iterator();
        if(liste1.size() != liste2.size())
            return false;
        while(it.hasNext()){
            Point p = it.next();
            if(!liste2.contains(p)){
                return false;
            }
        }
        return true;
    }

}