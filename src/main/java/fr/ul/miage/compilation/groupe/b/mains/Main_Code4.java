package main.java.fr.ul.miage.compilation.groupe.b.mains;

import main.java.fr.ul.miage.compilation.groupe.b.generateur.*;
import main.java.fr.ul.miage.tds.*;
import main.java.fr.ul.miage.arbre.*;
import main.java.fr.ul.miage.arbre.Afficheur;

public class Main_Code4 {

    public static void main(String[] args) throws Exception  {
        Tds tds = new Tds();
        Symbole s = tds.ajouter("main",Symbole.CAT_FONCTION,Symbole.SCOPE_GLOBAL);
        Symbole s2 = tds.ajouter("i",Symbole.CAT_GLOBAL,Symbole.SCOPE_GLOBAL);
        Symbole s3 = tds.ajouter("j",Symbole.CAT_GLOBAL,Symbole.SCOPE_GLOBAL);
        s3.set_valeur(20);
        System.out.println(tds);
        
        Prog prog = new Prog();
        Idf i4= new Idf("i");
        Idf j4= new Idf("j");
        Const c4=new Const(20);
        Fonction main = new Fonction("main");
        Affectation aff4 = new Affectation();
        aff4.setFilsGauche(j4);
        aff4.setFilsDroit(c4);


        Affectation aff4_1 = new Affectation();
        Lire lire4 = new Lire();
        aff4_1.setFilsGauche(i4);
        aff4_1.setFilsDroit(lire4);

        main.ajouterUnFils(aff4_1);
        Ecrire ec4 = new Ecrire();
        Plus add4=new Plus();
        add4.setFilsGauche(i4);
        add4.setFilsDroit(j4);
        ec4.setLeFils(add4);
        main.ajouterUnFils(ec4);
        prog.ajouterUnFils(main);
        Afficheur.afficher(prog);
        
        Generateur g = new Generateur();
        g.generate(prog,tds);

    }

}
