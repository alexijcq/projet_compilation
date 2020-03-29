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
        String resultat = "";
        switch (noeud.getCat()) {
            case FONCTION:
                resultat = genererFonction(noeud, tds); 
                break;
            case BLOC:
                resultat = genererBloc(noeud, tds); 
                break;
            case AFF:
                resultat = genererAffectation(noeud, tds); 
                break;
            case SI:
                resultat = genererSi(noeud, tds);
                break;
            case TQ:
                resultat = genererTantque(noeud, tds);
                break;
            case ECR:
                resultat = genererEcrire(noeud, tds);
                break;
            case RET:
                resultat = genererRetourne(noeud, tds);
                break;
            case PLUS:
                resultat = genererPlus(noeud, tds);
                break;
            case MOINS:
                resultat = genererMoins(noeud, tds);
                break;
            case DIV:
                resultat = genererDiv(noeud, tds);
                break;
            case MUL:
                resultat = genererMul(noeud, tds);
                break;
            case SUP:
                resultat = genererSup(noeud, tds);
                break;
            case INF:
                resultat = genererInf(noeud, tds);
                break;
            case SUPE:
                resultat = genererSupegal(noeud, tds);
                break;
            case INFE:
                resultat = genererInfegal(noeud, tds);
                break;
            case EG:
                resultat = genererEgal(noeud, tds);
                break;
            case DIF:
                resultat = genererDiff(noeud, tds);
                break;
            case IDF:
                resultat = genererIdf(noeud, tds);
                break;
            case CONST:
                resultat = genererConst(noeud, tds);
                break;
            case LIRE:
                resultat = genererLire(noeud, tds);
                break;
            case APPEL:
                resultat = genererAppel(noeud, tds);
                break;
            default:
                break;
        }
        return resultat;
    }

    private String genererFonction(Noeud noeud, Tds tds) {
        String resultat = "";
        resultat += " \n"
                + "";
        return resultat;
    }

    private String genererBloc(Noeud noeud, Tds tds) {
        // TODO Auto-generated method stub
        return null;
    }

    private String genererAffectation(Noeud noeud, Tds tds) {
        // TODO Auto-generated method stub
        return null;
    }

    private String genererSi(Noeud noeud, Tds tds) {
        // TODO Auto-generated method stub
        return null;
    }

    private String genererTantque(Noeud noeud, Tds tds) {
        // TODO Auto-generated method stub
        return null;
    }

    private String genererEcrire(Noeud noeud, Tds tds) {
        // TODO Auto-generated method stub
        return null;
    }

    private String genererRetourne(Noeud noeud, Tds tds) {
        // TODO Auto-generated method stub
        return null;
    }

    private String genererPlus(Noeud noeud, Tds tds) {
        // TODO Auto-generated method stub
        return null;
    }

    private String genererMoins(Noeud noeud, Tds tds) {
        // TODO Auto-generated method stub
        return null;
    }

    private String genererDiv(Noeud noeud, Tds tds) {
        // TODO Auto-generated method stub
        return null;
    }

    private String genererMul(Noeud noeud, Tds tds) {
        // TODO Auto-generated method stub
        return null;
    }

    private String genererSup(Noeud noeud, Tds tds) {
        // TODO Auto-generated method stub
        return null;
    }

    private String genererInf(Noeud noeud, Tds tds) {
        // TODO Auto-generated method stub
        return null;
    }

    private String genererSupegal(Noeud noeud, Tds tds) {
        // TODO Auto-generated method stub
        return null;
    }

    private String genererInfegal(Noeud noeud, Tds tds) {
        // TODO Auto-generated method stub
        return null;
    }

    private String genererEgal(Noeud noeud, Tds tds) {
        // TODO Auto-generated method stub
        return null;
    }

    private String genererDiff(Noeud noeud, Tds tds) {
        // TODO Auto-generated method stub
        return null;
    }

    private String genererIdf(Noeud noeud, Tds tds) {
        // TODO Auto-generated method stub
        return null;
    }

    private String genererConst(Noeud noeud, Tds tds) {
        String resultat ="";
        resultat += "CMOVE(a.val, r0) \n"
                + "PUSH(r0) \n";
        return resultat;
    }

    private String genererLire(Noeud noeud, Tds tds) {
        return null;
    }

    private String genererAppel(Noeud noeud, Tds tds) {
        // TODO Auto-generated method stub
        return null;
    }

    private String genererData(Tds tds) {

        return "";
    }

}
