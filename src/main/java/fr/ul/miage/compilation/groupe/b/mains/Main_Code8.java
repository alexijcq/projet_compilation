package main.java.fr.ul.miage.compilation.groupe.b.mains;

import main.java.fr.ul.miage.compilation.groupe.b.generateur.*;
import main.java.fr.ul.miage.tds.*;
import main.java.fr.ul.miage.arbre.*;
import main.java.fr.ul.miage.arbre.Afficheur;

public class Main_Code8 {

    public static void main(String[] args) throws Exception {
        Tds tds = new Tds();

        Symbole s = tds.ajouter("main",Symbole.CAT_FONCTION,Symbole.CAT_GLOBAL,"void");
        Symbole s2 = tds.ajouter("a",Symbole.CAT_GLOBAL,Symbole.CAT_GLOBAL,"int");
        Symbole s3 = tds.ajouter("f",Symbole.CAT_FONCTION, Symbole.SCOPE_GLOBAL,"int");
        s3.set_nbparam(2);
        s3.set_nbloc(1);
        Symbole s4 = tds.ajouter("x",Symbole.CAT_LOCAL,"f","int");
        s4.set_rang(0);
        Symbole s5 = tds.ajouter("i",Symbole.CAT_PARAMETRE,"f","int");
        s5.set_rang(0);
        Symbole s6 = tds.ajouter("j",Symbole.CAT_PARAMETRE,"f","int");
        s6.set_rang(1);
        
        System.out.println(tds.toString());

        Noeud prog = new Prog();
        Idf a8 = new Idf("a",prog);
        
        Noeud f8 = new Fonction("f",prog);
        Idf i8 = new Idf("i",f8);
        Idf j8 = new Idf("j",f8);
        
        Affectation aff8 = new Affectation(f8);
        Idf x8 = new Idf("x",f8);
        Plus plus8 = new Plus(aff8);
        plus8.setFilsGauche(i8);
        plus8.setFilsDroit(j8);
        aff8.setFilsGauche(x8);
        aff8.setFilsDroit(plus8);

        Retour ret8 = new Retour("retourf",f8);
        Plus plus8_1 = new Plus(ret8);
        Const c8 = new Const(10,plus8_1);
        plus8_1.setFilsGauche(x8);
        plus8_1.setFilsDroit(c8);
        ret8.ajouterUnFils(plus8_1);

        f8.ajouterUnFils(aff8);
        f8.ajouterUnFils(ret8);


        Noeud main = new Fonction("main",prog);
        Affectation aff8_1 = new Affectation(main);
        Appel appel8 = new Appel(f8,aff8_1);
        Const c8_1 = new Const(1,appel8);
        Const c8_2 = new Const(2,appel8);
        appel8.ajouterUnFils(c8_1);
        appel8.ajouterUnFils(c8_2);
        aff8_1.setFilsGauche(a8);
        aff8_1.setFilsDroit(appel8);

        Ecrire e8 = new Ecrire(main);
        e8.ajouterUnFils(a8);

        main.ajouterUnFils(aff8_1);
        main.ajouterUnFils(e8);

        prog.ajouterUnFils(f8);
        prog.ajouterUnFils(main);

        Afficheur.afficher(prog);
        
        
        Generateur g = new Generateur();
        System.out.println(g.generate(prog,tds));
    }
}
