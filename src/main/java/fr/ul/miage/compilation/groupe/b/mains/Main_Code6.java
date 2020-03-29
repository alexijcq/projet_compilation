package main.java.fr.ul.miage.compilation.groupe.b.mains;

import main.java.fr.ul.miage.compilation.groupe.b.generateur.*;
import main.java.fr.ul.miage.tds.*;

import java.util.ArrayList;

import main.java.fr.ul.miage.arbre.*;
import main.java.fr.ul.miage.arbre.Afficheur;

public class Main_Code6 {

    public static void main(String[] args) throws Exception {
        Tds tds = new Tds();
        Symbole s = tds.ajouter("main",Symbole.CAT_FONCTION,Symbole.SCOPE_GLOBAL,"void");
        Symbole s2 = tds.ajouter("i",Symbole.CAT_GLOBAL,Symbole.SCOPE_GLOBAL,"int");
        Symbole s3 = tds.ajouter("n",Symbole.CAT_GLOBAL,Symbole.SCOPE_GLOBAL,"int");
        s3.set_valeur(5);
        System.out.println(tds);
        
        Noeud prog = new Prog();
        Noeud main6 = new Fonction("main");
        Affectation aff6 = new Affectation();
        Idf i6 = new Idf("i");
        Const c6 = new Const(0);
        aff6.setFilsGauche(i6);
        aff6.setFilsDroit(c6);

        TantQue tq6 = new TantQue(1);
        Inferieur inf6 = new Inferieur();
        Idf n6 = new Idf("n");
        inf6.setFilsGauche(i6);
        inf6.setFilsDroit(n6);


        Bloc b6 = new Bloc();
        tq6.ajouterUnFils(inf6);
        tq6.ajouterUnFils(b6);
        Ecrire e6 = new Ecrire();
        e6.setLeFils(i6);
        Affectation aff6_1 = new Affectation();
        Plus plus6 = new Plus();
        Const c6_1 = new Const(1);
        plus6.setFilsGauche(i6);
        plus6.setFilsDroit(c6_1);
        aff6_1.setFilsGauche(i6);
        aff6_1.setFilsDroit(plus6);

        ArrayList<Noeud> listeNoeud6 = new ArrayList<Noeud>();
        listeNoeud6.add(inf6);
        listeNoeud6.add(b6);
        tq6.setFils(listeNoeud6);

        b6.ajouterUnFils(e6);
        b6.ajouterUnFils(aff6_1);

        main6.ajouterUnFils(aff6);
        main6.ajouterUnFils(tq6);

        prog.ajouterUnFils(main6);
        Afficheur.afficher(prog);
        
        Generateur g = new Generateur();
        System.out.println(g.generate(prog,tds));
    }

}
