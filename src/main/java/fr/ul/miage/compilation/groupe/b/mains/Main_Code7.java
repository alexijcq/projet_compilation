package main.java.fr.ul.miage.compilation.groupe.b.mains;

import main.java.fr.ul.miage.compilation.groupe.b.generateur.*;
import main.java.fr.ul.miage.tds.*;
import main.java.fr.ul.miage.arbre.*;
import main.java.fr.ul.miage.arbre.Afficheur;

public class Main_Code7 {

    public static void main(String[] args) throws Exception {       
        Tds tds = new Tds();

        Symbole s = tds.ajouter("main",Symbole.CAT_FONCTION,Symbole.CAT_GLOBAL,"void");
        Symbole s2 = tds.ajouter("a",Symbole.CAT_GLOBAL,Symbole.CAT_GLOBAL,"int");
        s2.set_valeur(10);
        Symbole s3 = tds.ajouter("f",Symbole.CAT_FONCTION, Symbole.SCOPE_GLOBAL,"int");
        s3.set_nbparam(1);
        s3.set_nbloc(2);
        Symbole s4 = tds.ajouter("i",Symbole.CAT_PARAMETRE,"f","int");
        s4.set_rang(0);
        Symbole s5 = tds.ajouter("x",Symbole.CAT_LOCAL,"f","int");
        s5.set_rang(0);
        Symbole s6 = tds.ajouter("y",Symbole.CAT_LOCAL,"f","int");
        s6.set_rang(1);
        System.out.println(tds.toString());
    
        
        Prog prog7 = new Prog();
        
        Idf a7 = new Idf("a");
        Affectation aff7 = new Affectation();
        Const c7 = new Const(10);
        aff7.setFilsGauche(a7);
        aff7.setFilsDroit(c7);
        
        Fonction f7 = new Fonction("f");
        Idf i7 = new Idf("i");
        Idf x7 = new Idf('x');
        Affectation aff7_1 = new Affectation();
        Const c7_1 = new Const(1);
        aff7_1.setFilsGauche(x7);
        aff7_1.setFilsDroit(c7_1);
        
        Idf y7 = new Idf('y');
        Affectation aff7_2 = new Affectation();
        Const c7_2 = new Const(1);
        aff7_2.setFilsGauche(y7);
        aff7_2.setFilsDroit(c7_2);
        
        Affectation aff7_3 = new Affectation();
        aff7_3.setFilsGauche(a7);
        Plus add7 = new Plus();
        add7.setFilsGauche(i7);
        
        Plus add7_1 = new Plus();
        add7_1.setFilsGauche(x7);
        add7_1.setFilsDroit(y7);
        add7.setFilsDroit(add7_1);
        
        aff7_3.setFilsDroit(add7);
        
        f7.ajouterUnFils(aff7_1);
        f7.ajouterUnFils(aff7_2);
        f7.ajouterUnFils(aff7_3);
        
        Fonction main7 = new Fonction("main");
        
        Appel ap7 = new Appel(f7);
        Const c7_3 = new Const(3);
        ap7.ajouterUnFils(c7_3);
        Ecrire ec7 = new Ecrire();
        ec7.setLeFils(a7);
        main7.ajouterUnFils(ap7);
        main7.ajouterUnFils(ec7);
        
        prog7.ajouterUnFils(f7);
        prog7.ajouterUnFils(main7);

        Afficheur.afficher(prog7);
        
        
         Generateur g = new Generateur();
         g.generate(prog7,tds);
         System.out.println(g.toString());
    }

}
