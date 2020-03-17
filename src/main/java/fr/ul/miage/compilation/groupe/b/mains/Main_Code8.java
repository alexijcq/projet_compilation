package fr.ul.miage.compilation.groupe.b.mains;

import fr.ul.miage.compilation.groupe.b.generateur.*;
import fr.ul.miage.arbre.Afficheur;
import fr.ul.miage.arbre.*;
import fr.ul.miage.tds.*;

public class Main_Code8 {

    public static void main(String[] args) throws Exception {
        Tds tds = new Tds();

        Symbole s = tds.ajouter("main",Symbole.CAT_FONCTION,Symbole.CAT_GLOBAL);
        Symbole s2 = tds.ajouter("a",Symbole.CAT_GLOBAL,Symbole.CAT_GLOBAL);
        Symbole s3 = tds.ajouter("f",Symbole.CAT_FONCTION, Symbole.SCOPE_GLOBAL);
        s3.set_nbparam(2);
        s3.set_nbloc(1);
        Symbole s4 = tds.ajouter("x",Symbole.CAT_LOCAL,"f");
        s4.set_rang(0);
        Symbole s5 = tds.ajouter("i",Symbole.CAT_PARAMETRE,"f");
        s5.set_rang(0);
        Symbole s6 = tds.ajouter("j",Symbole.CAT_PARAMETRE,"f");
        s6.set_rang(1);
        
        System.out.println(tds.toString());

        Noeud prog = new Prog();
        Noeud f8 = new Fonction("f");
        Noeud main8 = new Fonction("main");
        Affectation aff8 = new Affectation();
        Idf x8 = new Idf("x");
        Plus plus8 = new Plus();
        Idf i8 = new Idf("i");
        Idf j8 = new Idf("j");
        plus8.setFilsGauche(i8);
        plus8.setFilsDroit(j8);
        aff8.setFilsGauche(x8);
        aff8.setFilsDroit(plus8);

        Retour ret8 = new Retour(f8);
        Plus plus8_1 = new Plus();
        Const c8 = new Const(10);
        plus8_1.setFilsGauche(x8);
        plus8_1.setFilsDroit(c8);
        ret8.ajouterUnFils(plus8_1);

        f8.ajouterUnFils(aff8);
        f8.ajouterUnFils(ret8);

        Affectation aff8_1 = new Affectation();
        Idf a8 = new Idf("a");
        Appel appel8 = new Appel(f8);
        Const c8_1 = new Const(1);
        Const c8_2 = new Const(2);
        appel8.ajouterUnFils(c8_1);
        appel8.ajouterUnFils(c8_2);
        aff8_1.setFilsGauche(a8);
        aff8_1.setFilsDroit(appel8);

        Ecrire e8 = new Ecrire();
        e8.ajouterUnFils(a8);

        main8.ajouterUnFils(aff8_1);
        main8.ajouterUnFils(e8);

        prog.ajouterUnFils(f8);
        prog.ajouterUnFils(main8);

        Afficheur.afficher(prog);
        
        
        Generateur g = new Generateur();
        g.generate(prog,tds);
        System.out.println(g.toString());
    }
}