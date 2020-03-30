package main.java.fr.ul.miage.compilation.groupe.b.mains;

import main.java.fr.ul.miage.compilation.groupe.b.generateur.*;
import main.java.fr.ul.miage.tds.*;
import main.java.fr.ul.miage.arbre.*;

public class Main_Code2 {
    public static void main(String[] args) throws Exception {
        Tds tds = new Tds();
        Symbole s = tds.ajouter("main",Symbole.CAT_FONCTION,Symbole.SCOPE_GLOBAL,"void");
        Symbole s2 = tds.ajouter("i",Symbole.CAT_GLOBAL,Symbole.SCOPE_GLOBAL,"int");
        s2.set_valeur(10);
        Symbole s3 = tds.ajouter("j",Symbole.CAT_GLOBAL,Symbole.SCOPE_GLOBAL,"int");
        s3.set_valeur(20);
        Symbole s4 = tds.ajouter("k",Symbole.CAT_GLOBAL,Symbole.SCOPE_GLOBAL,"int");
        Symbole s5 = tds.ajouter("l",Symbole.CAT_GLOBAL,Symbole.SCOPE_GLOBAL,"int");
        System.out.println(tds.toString());
        
        Prog prog = new Prog();
        Fonction main = new Fonction("main", prog);
        
        Idf i3 = new Idf("i",prog);
        Idf j3 = new Idf("j",prog);
        Idf k3 = new Idf("k",prog);
        Idf l3 = new Idf("l",prog);

        prog.ajouterUnFils(main);
        Afficheur.afficher(prog);
        
        Generateur g = new Generateur();
        System.out.println(g.generate(prog,tds));
    }

}
