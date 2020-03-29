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
			resultat += genererCode(node.getFils().get(i));
		}

		resultat += "pile : \n";

		return resultat;
	}

	private String genererCode(Noeud noeud) {
		String resultat = "";
		switch (noeud.getCat()) {
		case FONCTION:
			resultat = genererFonction(noeud); 
			break;
		case BLOC:
			resultat = genererBloc(noeud); 
			break;
		case AFF:
			resultat = genererAffectation(noeud); 
			break;
		case SI:
			resultat = genererSi(noeud);
			break;
		case TQ:
			resultat = genererTantque(noeud);
			break;
		case ECR:
			resultat = genererEcrire(noeud);
			break;
		case RET:
			resultat = genererRetourne(noeud);
			break;
		case PLUS:
			resultat = genererPlus(noeud);
			break;
		case MOINS:
			resultat = genererMoins(noeud);
			break;
		case DIV:
			resultat = genererDiv(noeud);
			break;
		case MUL:
			resultat = genererMul(noeud);
			break;
		case SUP:
			resultat = genererSup(noeud);
			break;
		case INF:
			resultat = genererInf(noeud);
			break;
		case SUPE:
			resultat = genererSupegal(noeud);
			break;
		case INFE:
			resultat = genererInfegal(noeud);
			break;
		case EG:
			resultat = genererEgal(noeud);
			break;
		case DIF:
			resultat = genererDiff(noeud);
			break;
		case IDF:
			resultat = genererIdf(noeud);
			break;
		case CONST:
			resultat = genererConst(noeud);
			break;
		case LIRE:
			resultat = genererLire(noeud);
			break;
		case APPEL:
			resultat = genererAppel(noeud);
			break;
		default:
			break;
		}
		return resultat;
	}

	private String genererFonction(Noeud noeud) {
	    String resultat = "";
        Fonction f = new Fonction("");
        if (noeud instanceof Fonction) {
            f = (Fonction) noeud;
        }
        resultat += f.getValeur()+" :\n";
        for (int i = 0; i < noeud.getFils().size(); i++) {
            resultat += genererCode(noeud.getFils().get(i));
        }
        resultat +=  "\tHALT()\n";
        return resultat;
	}


	private String genererBloc(Noeud noeud) {
    	String resultat = "";
    	for (int i = 0; i < noeud.getFils().size(); i++) {
            resultat += genererCode(noeud.getFils().get(i));
        }
        return resultat;
    }

	private String genererAffectation(Noeud noeud) {
		String resultat = "";
		Idf i = new Idf(0);
		if(noeud.getFils().get(0) instanceof Idf) {
			i = (Idf) noeud.getFils().get(0);
		}
		resultat = genererCode(noeud.getFils().get(1))
				+ "\tPOP(R0)\n"
				+ "\tST(R0, "+i.getValeur()+")\n"; 
		return resultat;
	}

	private String genererSi(Noeud noeud) {
		String resultat = "";
		resultat = genererCode(noeud.getFils().get(0))
				+ "\tPOP(R0)\n"
				+ "\tBF(R0, sinon)\n"
				+ genererBloc(noeud.getFils().get(1))
				+ "\tJPM(fsi)\n"
				+ "sinon :\n"
				+ genererBloc(noeud.getFils().get(2))
				+ "fsi :\n";
		return resultat;
	}

	private String genererTantque(Noeud noeud) {
		String resultat = "";
		TantQue tq = new TantQue(0);
        if (noeud instanceof TantQue) {
            tq = (TantQue) noeud;
        }
		resultat += "boucle : \n"
		        + genererCode(tq.getCondition())
		        + "\tPOP(r0)\n"
		        + "\tBF(r0, finboucle)\n"
		        + genererBloc(tq.getBloc())
		        + "\tJMP(boucle)\n"
		        + "finboucle :\n";
		return resultat;
	}

   private String genererEcrire(Noeud noeud) {
    	String resultat = "";
        resultat += genererCode(noeud.getFils().get(1))
                + "\tPOP(r0)\n"
        		+ "\tWRINT()\n";
        return resultat;
    }

    private String genererRetourne(Noeud noeud) {
        String resultat = "";
        Retour r = new Retour("");
        if (noeud instanceof Retour) {
            r = (Retour) noeud;
        }
        resultat += genererCode(r.getLeFils())
                + "\tPUSH(r0)\n";
        return resultat;
    }

    private String genererPlus(Noeud noeud) {
        String resultat = "";
        resultat += genererCode(noeud.getFils().get(0))
                + genererCode(noeud.getFils().get(1))
                + "\tPOP(R1)\n"
                + "\tPOP(R0)\n"
                + "\tADD(R0, R1, R2)\n"
                + "\tPUSH(R2)\n";
        return resultat;
    }

    private String genererMoins(Noeud noeud) {
        String resultat = "";
        resultat += genererCode(noeud.getFils().get(0))
                + genererCode(noeud.getFils().get(1))
                + "\tPOP(r1)\n"
                + "\tPOP(r0)\n"
                + "\tSUB(r0,r1,r2)\n"
                + "\tPUSH(r2)\n";
        return resultat;
    }

    private String genererDiv(Noeud noeud) {
        String resultat = "";
        resultat += genererCode(noeud.getFils().get(0))
                + genererCode(noeud.getFils().get(1))
                + "\tPOP(R1)\n"
                + "\tPOP(r0)\n"
                + "\tDIV(r0,r1,r2)\n"
                + "\tPUSH(r2)\n";
        return resultat;
    }

    private String genererMul(Noeud noeud) {
        String resultat = "";
        resultat += genererCode(noeud.getFils().get(0))
                + genererCode(noeud.getFils().get(1))
                + "\tPOP(R1)\n"
                + "\tPOP(r0)\n"
                + "\tMUL(r0,r1,r2)\n"
                + "\tPUSH(r2)\n";
        return resultat;
    }

    private String genererSup(Noeud noeud) {
        String resultat = "";
        resultat += genererCode(noeud.getFils().get(0))
                + genererCode(noeud.getFils().get(1))
                + "\tPOP(R1)\n"
                + "\tPOP(r0)\n"
                + "\tCMPLT(r1,r0,r2)\n"
                + "\tPUSH(r2)\n";
        return resultat;
    }

    private String genererInf(Noeud noeud) {
        String resultat = "";
        resultat += genererCode(noeud.getFils().get(0))
                + genererCode(noeud.getFils().get(1))
                + "\tPOP(R1)\n"
                + "\tPOP(r0)\n"
                + "\tCMPLT(r0,r1,r2)\n"
                + "\tPUSH(r2)\n";
        return resultat;
    }

    private String genererSupegal(Noeud noeud) {
        String resultat = "";
        resultat += genererCode(noeud.getFils().get(0))
                + genererCode(noeud.getFils().get(1))
                + "\tPOP(R1)\n"
                + "\tPOP(r0)\n"
                + "\tCMPLE(r1,r0,r2)\n"
                + "\tPUSH(r2)\n";
        return resultat;
    }

    private String genererInfegal(Noeud noeud) {
        String resultat = "";
        resultat += genererCode(noeud.getFils().get(0))
                + genererCode(noeud.getFils().get(1))
                + "\tPOP(R1)\n"
                + "\tPOP(r0)\n"
                + "\tCMPLE(r0,r1,r2)\n"
                + "\tPUSH(r2)\n";
        return resultat;
    }

    private String genererEgal(Noeud noeud) {
        String resultat = "";
        resultat += genererCode(noeud.getFils().get(0))
                + genererCode(noeud.getFils().get(1))
                + "\tPOP(R1)\n"
                + "\tPOP(r0)\n"
                + "\tCMPEQ(r0,r1,r2)\n"
                + "\tPUSH(r2)\n";
        return resultat;
    }

    private String genererDiff(Noeud noeud) {
        String resultat = "";
        resultat += genererCode(noeud.getFils().get(0))
                + genererCode(noeud.getFils().get(1))
                + "\tPOP(R1)\n"
                + "\tPOP(r0)\n"
                + "\tCMPEQ(r0,r1,r2)\n"
                + "\tCMPEQC(R2,0,R3)\n"
                + "\tPUSH(r)\n";
        return resultat;
    }

    private String genererIdf(Noeud noeud) {
        String resultat = "";
        Idf i = new Idf(0);
        if (noeud instanceof Idf) {
            i = (Idf) noeud;
        }
        resultat = "\tLD("+i.getValeur()+", R0)\n"
                + "\tPUSH(R0)\n";
        return resultat;
    }

    private String genererConst(Noeud noeud) {
        String resultat ="";
        Const c = new Const(0);
        if (noeud instanceof Const) {
            c = (Const) noeud;
        }
        resultat += "\tCMOVE("+c.getValeur()+", r0) \n"
                + "\tPUSH(r0)\n";
        return resultat;
    }

    private String genererLire(Noeud noeud) {
        String resultat = "";
        resultat = "\tRDINT()\n"
                + "\tPUSH(R0)\n";
        return resultat;
    }

    private String genererAppel(Noeud noeud) {
        String resultat = "";
        Appel a = new Appel("");
        if (noeud instanceof Appel) {
            a = (Appel) noeud;
        }
        Fonction f = new Fonction("");
        if (a.getValeur() instanceof Fonction) {
            f = (Fonction) a.getValeur();
        }
        resultat += "\tCALL(" + f.getValeur() + "kk)\n";
        return resultat;
    }

    private String genererData(Tds tds) {

        return "";
    }

}
