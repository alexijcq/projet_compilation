package main.java.fr.ul.miage.compilation.groupe.b.mains;

import main.java.fr.ul.miage.compilation.groupe.b.generateur.*;
import main.java.fr.ul.miage.tds.*;
import main.java.fr.ul.miage.arbre.*;
import main.java.fr.ul.miage.arbre.Afficheur;

public class Main_Code5 {

    public static void main(String[] args) throws Exception {
        Tds tds = new Tds();
        Symbole s = tds.ajouter("main",Symbole.CAT_FONCTION,Symbole.SCOPE_GLOBAL, "void");
        Symbole s2 = tds.ajouter("i",Symbole.CAT_GLOBAL,Symbole.SCOPE_GLOBAL,"int");
        System.out.println(tds);
        
        
        Noeud prog = new Prog();
        Idf i5 = new Idf("i",prog);
        
        Noeud main = new Fonction("main",prog);
        
        Affectation aff5 = new Affectation(main);
        Lire lire5 = new Lire(aff5);
        aff5.setFilsGauche(i5);
        aff5.setFilsDroit(lire5);
        
        Si si5 = new Si(1,main);
        Superieur inf5 = new Superieur(si5);
        Noeud c5 = new Const(10,inf5);
        inf5.setFilsGauche(i5);
        inf5.setFilsDroit(c5);
        si5.setCondition(inf5);
        
        Bloc bl5 = new Bloc(si5);
        Ecrire ec5 = new Ecrire(bl5);
        Const c5_1 = new Const(1,bl5);
        ec5.setLeFils(c5_1);
        bl5.ajouterUnFils(ec5);
        si5.setBlocAlors(bl5);
        
        Bloc bl5_1 = new Bloc(si5);
        Ecrire ec5_1 = new Ecrire(bl5_1);
        Const c5_2 = new Const(2,bl5_1);
        ec5_1.setLeFils(c5_2);
        bl5_1.ajouterUnFils(ec5_1);
        si5.setBlocSinon(bl5_1);
        
        main.ajouterUnFils(aff5);
        main.ajouterUnFils(si5);
        prog.ajouterUnFils(main);
        Afficheur.afficher(prog);
        
        Generateur g = new Generateur();
        System.out.println(g.generate(prog,tds));
    }

}
