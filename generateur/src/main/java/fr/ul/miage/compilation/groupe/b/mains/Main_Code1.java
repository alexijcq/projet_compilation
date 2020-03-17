package fr.ul.miage.compilation.groupe.b.mains;

import fr.ul.miage.compilation.groupe.b.generateur.*;
import fr.ul.miage.arbre.Afficheur;
import fr.ul.miage.arbre.*;
import fr.ul.miage.tds.*;

public class Main_Code1 {

    public static void main(String[] args) throws Exception{
        Tds tds = new Tds();
        Symbole s = tds.ajouter("main",Symbole.CAT_FONCTION, Symbole.SCOPE_GLOBAL);
        System.out.println(tds.toString());
        
        Noeud prog = new Prog();
        Noeud main = new Fonction("main");
        prog.ajouterUnFils(main);
        Afficheur.afficher(prog);
        
        Generateur g = new Generateur();
        g.generate(prog,tds);
        System.out.println(g.toString());

    }

}
