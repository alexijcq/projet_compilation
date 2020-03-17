package main.java.fr.ul.miage.compilation.groupe.b.generateur;

import main.java.fr.ul.miage.arbre.*;
import main.java.fr.ul.miage.tds.*;

public class Generateur {
    public Generateur() {

    }

    public String generate(Noeud node, Tds tds) {
        String resultat = "";
        // generation des instructions de démarrages
        resultat += ".include beta.uasm \n" + ".include intio.uasm \n" + ".options tty \n\n" + "CMOVE(pile,sp) \n"
                + "BR(debut) \n";

        // generation du code pour les symboles du tds
        resultat += genererData(tds);
        // appel de la fonction main
        resultat += "debut : \n" + "\tCALL(FUNC_main) \n" + "\tHALT() \n";
        for (int i = 0; i < node.getFils().size(); i++) {
            // generation du code pour chaque noeud
            resultat += genererCode(node.getFils().get(i), tds);
        }

        resultat += "pile : \n";

        return resultat;
    }

    private String genererCode(Noeud noeud, Tds tds) {
        switch (noeud.getCat()) {
            case FONCTION:
                System.out.println("yolo"); // appel méthode genere-fonction
                break;

            default:

                break;
        }
        return "";
    }

    private String genererData(Tds tds) {

        return "";
    }

}
