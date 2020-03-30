package main.java.fr.ul.miage.compilation.groupe.b.mains;

import main.java.fr.ul.miage.compilation.groupe.b.generateur.*;
import main.java.fr.ul.miage.tds.*;
import main.java.fr.ul.miage.arbre.*;

public class Main_Code3 {

    public static void main(String[] args) throws Exception {
        Tds tds = new Tds();
        Symbole s = tds.ajouter("main",Symbole.TYPE_VOID,Symbole.CAT_FONCTION, "void");
        Symbole s2 = tds.ajouter("i",Symbole.CAT_GLOBAL,Symbole.SCOPE_GLOBAL,"int");
        s2.set_valeur(10);
        Symbole s3 = tds.ajouter("j",Symbole.CAT_GLOBAL,Symbole.SCOPE_GLOBAL,"int");
        s3.set_valeur(20);
        Symbole s4 = tds.ajouter("k",Symbole.CAT_GLOBAL,Symbole.SCOPE_GLOBAL,"int");
        Symbole s5 = tds.ajouter("l",Symbole.CAT_GLOBAL,Symbole.SCOPE_GLOBAL,"int");
        System.out.println(tds);
        
        Prog prog = new Prog();
        Idf i3 = new Idf("i",prog);
        Idf j3 = new Idf("j",prog);
        Idf k3 = new Idf("k",prog);
        Idf l3 = new Idf("l",prog);

        Fonction main = new Fonction("main",prog);
        Affectation aff3_2 = new Affectation(main);
        Const c3_2 = new Const(2,aff3_2);
        aff3_2.setFilsGauche(k3);
        aff3_2.setFilsDroit(c3_2);
        main.ajouterUnFils(aff3_2);

        Affectation aff3_3 = new Affectation(main);
        aff3_3.setFilsGauche(l3);
        Plus add3 = new Plus(aff3_3);
        add3.setFilsGauche(i3);
        Multiplication mul3 = new Multiplication(add3);
        Const c3_3 = new Const(3,mul3);
        mul3.setFilsGauche(c3_3);
        mul3.setFilsDroit(j3);
        add3.setFilsDroit(mul3);
        aff3_3.setFilsDroit(add3);
        main.ajouterUnFils(aff3_3);

        prog.ajouterUnFils(main);
        Afficheur.afficher(prog);
        
        Generateur g = new Generateur();
        System.out.println(g.generate(prog,tds));
    }

}
