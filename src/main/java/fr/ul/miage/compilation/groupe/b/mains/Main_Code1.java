package main.java.fr.ul.miage.compilation.groupe.b.mains;

import main.java.fr.ul.miage.compilation.groupe.b.generateur.*;
import main.java.fr.ul.miage.tds.*;
import main.java.fr.ul.miage.arbre.*;
import main.java.fr.ul.miage.arbre.Afficheur;

public class Main_Code1 {

    public static void main(String[] args) throws Exception{
        Tds tds = new Tds();
        Symbole s = tds.ajouter("main",Symbole.CAT_FONCTION, Symbole.SCOPE_GLOBAL,"void");
        System.out.println(tds.toString());
        
        Prog prog = new Prog();
        Fonction main = new Fonction("main",prog);
        prog.ajouterUnFils(main);
        Afficheur.afficher(prog);
        
        Generateur g = new Generateur();
        System.out.println(g.generate(prog,tds));

    }

}
