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
    
        
        Prog prog = new Prog();
        
        Idf a7 = new Idf("a",prog);
        
        Fonction f7 = new Fonction("f",prog);
        Idf i7 = new Idf("i",f7);
        Idf x7 = new Idf('x',f7);
        Idf y7 = new Idf('y',f7);
        Affectation aff7_1 = new Affectation(f7);
        Const c7_1 = new Const(1,aff7_1);
        aff7_1.setFilsGauche(x7);
        aff7_1.setFilsDroit(c7_1);
        
        
        Affectation aff7_2 = new Affectation(f7);
        Const c7_2 = new Const(1,aff7_2);
        aff7_2.setFilsGauche(y7);
        aff7_2.setFilsDroit(c7_2);
        
        Affectation aff7_3 = new Affectation(f7);
        aff7_3.setFilsGauche(a7);
        Plus add7 = new Plus(aff7_3);
        add7.setFilsGauche(i7);
        
        Plus add7_1 = new Plus(add7);
        add7_1.setFilsGauche(x7);
        add7_1.setFilsDroit(y7);
        add7.setFilsDroit(add7_1);
        
        aff7_3.setFilsDroit(add7);
        
        f7.ajouterUnFils(aff7_1);
        f7.ajouterUnFils(aff7_2);
        f7.ajouterUnFils(aff7_3);
        
        Fonction main = new Fonction("main",prog);
        
        Appel ap7 = new Appel(f7,main);
        Const c7_3 = new Const(3,ap7);
        ap7.ajouterUnFils(c7_3);
        Ecrire ec7 = new Ecrire(main);
        ec7.setLeFils(a7);
        main.ajouterUnFils(ap7);
        main.ajouterUnFils(ec7);
        
        prog.ajouterUnFils(f7);
        prog.ajouterUnFils(main);

        Afficheur.afficher(prog);
        
        
         Generateur g = new Generateur();
         System.out.println(g.generate(prog,tds));
    }

}
