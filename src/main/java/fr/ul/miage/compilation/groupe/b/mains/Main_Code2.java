package main.java.fr.ul.miage.compilation.groupe.b.mains;

import main.java.fr.ul.miage.compilation.groupe.b.generateur.*;
import main.java.fr.ul.miage.tds.*;
import main.java.fr.ul.miage.arbre.*;
import main.java.fr.ul.miage.arbre.Afficheur;

public class Main_Code2 {
    public static void main(String[] args) throws Exception {
        Tds tds = new Tds();
        Symbole s = tds.ajouter("main",Symbole.CAT_FONCTION,Symbole.SCOPE_GLOBAL);
        Symbole s2 = tds.ajouter("i",Symbole.CAT_GLOBAL,Symbole.SCOPE_GLOBAL);
        s2.set_valeur(10);
        Symbole s3 = tds.ajouter("J",Symbole.CAT_GLOBAL,Symbole.SCOPE_GLOBAL);
        s3.set_valeur(20);
        Symbole s4 = tds.ajouter("k",Symbole.CAT_GLOBAL,Symbole.SCOPE_GLOBAL);
        Symbole s5 = tds.ajouter("l",Symbole.CAT_GLOBAL,Symbole.SCOPE_GLOBAL);
        System.out.println(tds.toString());
        
        Prog prog = new Prog();
        Fonction main = new Fonction("main");
        Affectation aff1 = new Affectation();
        Idf i1 = new Idf("i");
        Const c1 = new Const(10);
        aff1.setFilsGauche(i1);
        aff1.setFilsDroit(c1);

        Affectation aff2 = new Affectation();
        Idf j1 = new Idf("j");
        Const c2 = new Const(20);
        aff2.setFilsGauche(j1);
        aff2.setFilsDroit(c2);

        Idf k1 = new Idf("k");
        Idf l1 = new Idf("l");

        prog.ajouterUnFils(main);
        Afficheur.afficher(prog);
        
        Generateur g = new Generateur();
        System.out.println(g.generate(prog,tds));
    }

}
