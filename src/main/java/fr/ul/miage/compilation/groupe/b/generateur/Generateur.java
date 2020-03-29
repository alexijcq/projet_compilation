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
        Fonction f = new Fonction("");
        if (noeud instanceof Fonction) {
            f = (Fonction) noeud;
        }
        resultat += f.getValeur()+" : ";
        for (int i = 0; i < noeud.getFils().size(); i++) {
            resultat += genererCode(noeud.getFils().get(i), tds)+"\n";
        }
        resultat +=  "\tHALT() \n";
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
        String resultat = "";
        Retour r = new Retour("");
        if (noeud instanceof Retour) {
            r = (Retour) noeud;
        }
        resultat += genererCode(r.getLeFils(),tds);
        resultat = "\tPUSH(r0)\n";
        return resultat;
    }

    private String genererPlus(Noeud noeud, Tds tds) {
        // TODO Auto-generated method stub
        return null;
    }

    private String genererMoins(Noeud noeud, Tds tds) {
        String resultat = "";
        resultat += genererCode(noeud.getFils().get(0), tds);
        resultat += genererCode(noeud.getFils().get(1), tds);
        resultat += "\tPOP(r1)\n"
                + "\tPOP(r0)\n"
                + "\tSUB(r0,r1,r2)\n"
                + "\tPUSH(r2)";
        return resultat;
    }

    private String genererDiv(Noeud noeud, Tds tds) {
        String resultat = "";
        resultat += genererCode(noeud.getFils().get(0), tds);
        resultat += genererCode(noeud.getFils().get(1), tds);
        resultat += "\tPOP(r1)\n"
                + "\tPOP(r0)\n"
                + "\tDIV(r0,r1,r2)\n"
                + "\tPUSH(r2)";
        return resultat;
    }

    private String genererMul(Noeud noeud, Tds tds) {
        String resultat = "";
        resultat += genererCode(noeud.getFils().get(0), tds);
        resultat += genererCode(noeud.getFils().get(1), tds);
        resultat += "\tPOP(r1)\n"
                + "\tPOP(r0)\n"
                + "\tMUL(r0,r1,r2)\n"
                + "\tPUSH(r2)";
        return resultat;
    }

    private String genererSup(Noeud noeud, Tds tds) {
        String resultat = "";
        resultat += genererCode(noeud.getFils().get(0), tds);
        resultat += genererCode(noeud.getFils().get(1), tds);
        resultat += "\tPOP(r1)\n"
                + "\tPOP(r0)\n"
                + "\tCMPLT(r1,r0,r2)\n"
                + "\tPUSH(r2)";
        return resultat;
    }

    private String genererInf(Noeud noeud, Tds tds) {
        String resultat = "";
        resultat += genererCode(noeud.getFils().get(0), tds);
        resultat += genererCode(noeud.getFils().get(1), tds);
        resultat += "\tPOP(r1)\n"
                + "\tPOP(r0)\n"
                + "\tCMPLT(r0,r1,r2)\n"
                + "\tPUSH(r2)";
        return resultat;
    }

    private String genererSupegal(Noeud noeud, Tds tds) {
        String resultat = "";
        resultat += genererCode(noeud.getFils().get(0), tds);
        resultat += genererCode(noeud.getFils().get(1), tds);
        resultat += "\tPOP(r1)\n"
                + "\tPOP(r0)\n"
                + "\tCMPLE(r1,r0,r2)\n"
                + "\tPUSH(r2)";
        return resultat;
    }

    private String genererInfegal(Noeud noeud, Tds tds) {
        String resultat = "";
        resultat += genererCode(noeud.getFils().get(0), tds);
        resultat += genererCode(noeud.getFils().get(1), tds);
        resultat += "\tPOP(r1)\n"
                + "\tPOP(r0)\n"
                + "\tCMPLE(r0,r1,r2)\n"
                + "\tPUSH(r2)";
        return resultat;
    }

    private String genererEgal(Noeud noeud, Tds tds) {
        String resultat = "";
        resultat += genererCode(noeud.getFils().get(0), tds);
        resultat += genererCode(noeud.getFils().get(1), tds);
        resultat += "\tPOP(r1)\n"
                + "\tPOP(r0)\n"
                + "\tCMPEQ(r0,r1,r2)\n"
                + "\tPUSH(r2)";
        return resultat;
    }

    private String genererDiff(Noeud noeud, Tds tds) {
        String resultat = "";
        resultat += genererCode(noeud.getFils().get(0), tds);
        resultat += genererCode(noeud.getFils().get(1), tds);
        resultat += "\tPOP(r1)\n"
                + "\tPOP(r0)\n"
                + "\tCMPEQ(r0,r1,r2)\n"
                + "\tCMPEQC(R2,0,R3)\n"
                + "\tPUSH(r3)";
        return resultat;
    }

    private String genererIdf(Noeud noeud, Tds tds) {
        // TODO Auto-generated method stub
        return null;
    }

    private String genererConst(Noeud noeud, Tds tds) {
        String resultat ="";
        Const c = new Const(0);
        if (noeud instanceof Const) {
            c = (Const) noeud;
        }
        resultat += "CMOVE("+c.getValeur()+", r0) \n"
                + "PUSH(r0) \n";
        return resultat;
    }

    private String genererLire(Noeud noeud, Tds tds) {
     // TODO Auto-generated method stub
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
