package main.java.fr.ul.miage.compilation.groupe.b.generateur;

import java.util.List;
import java.util.Map;

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
		resultat += genererDataGlobal(tds);
		// appel de la fonction main
		resultat += "debut :\n" 
		        + "\tCALL(main)\n" 
		        + "\tHALT()\n";
		for (int i = 0; i < node.getFils().size(); i++) {
			// generation du code pour chaque noeud
			resultat += genererCode(node.getFils().get(i),tds);
		}

		resultat += "pile : \n";

		return resultat;
	}

	private String genererCode(Noeud noeud,Tds tds) {
		String resultat = "";
		switch (noeud.getCat()) {
		case FONCTION:
			resultat = genererFonction(noeud,tds); 
			break;
		case BLOC:
			resultat = genererBloc(noeud,tds); 
			break;
		case AFF:
			resultat = genererAffectation(noeud,tds); 
			break;
		case SI:
			resultat = genererSi(noeud,tds);
			break;
		case TQ:
			resultat = genererTantque(noeud,tds);
			break;
		case ECR:
			resultat = genererEcrire(noeud,tds);
			break;
		case RET:
			resultat = genererRetourne(noeud,tds);
			break;
		case PLUS:
			resultat = genererPlus(noeud,tds);
			break;
		case MOINS:
			resultat = genererMoins(noeud,tds);
			break;
		case DIV:
			resultat = genererDiv(noeud,tds);
			break;
		case MUL:
			resultat = genererMul(noeud,tds);
			break;
		case SUP:
			resultat = genererSup(noeud,tds);
			break;
		case INF:
			resultat = genererInf(noeud,tds);
			break;
		case SUPE:
			resultat = genererSupegal(noeud,tds);
			break;
		case INFE:
			resultat = genererInfegal(noeud,tds);
			break;
		case EG:
			resultat = genererEgal(noeud,tds);
			break;
		case DIF:
			resultat = genererDiff(noeud,tds);
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

	private String genererFonction(Noeud noeud,Tds tds) {
	    String resultat = "\n\t|Génération de fonction\n";
        Fonction f = new Fonction("");
        if (noeud instanceof Fonction) {
            f = (Fonction) noeud;
        }
        resultat += f.getValeur()+" :\n";
        resultat += genererDataLocal(f, tds);
        for (int i = 0; i < noeud.getFils().size(); i++) {
            resultat += genererCode(noeud.getFils().get(i),tds);
        }
        resultat +=  "\tHALT() |Fin Fonction\n";
        return resultat;
	}


	private String genererBloc(Noeud noeud,Tds tds) {
    	String resultat = "\n\t|Génération de bloc\n";
    	for (int i = 0; i < noeud.getFils().size(); i++) {
            resultat += genererCode(noeud.getFils().get(i),tds);
        }
        return resultat+="\t|Fin Bloc\n";
    }

	private String genererAffectation(Noeud noeud,Tds tds) {
	    String resultat = "\n\t|Génération du Affectation\n";
		Idf i = new Idf(0);
		if(noeud.getFils().get(0) instanceof Idf) {
			i = (Idf) noeud.getFils().get(0);
		}
		resultat += genererCode(noeud.getFils().get(1),tds)
				+ "\tPOP(R0)\n"
				+ "\tST(R0, "+i.getValeur()+") |Fin Affectation\n"; 
		return resultat;
	}

	private String genererSi(Noeud noeud,Tds tds) {
	    String resultat = "\n\t|Génération du Si\n";
	    Si s = new Si(0);
        if(noeud instanceof Si) {
            s = (Si) noeud;
        }
		resultat += genererCode(s.getFils().get(0),tds)
				+ "\tPOP(R0)\n"
				+ "\tBF(R0, sinon"+s.getValeur()+")\n"
				+ genererBloc(s.getFils().get(1),tds)
				+ "\tJPM(fsi"+s.getValeur()+")\n"
				+ "sinon"+s.getValeur()+" :\n"
				+ genererBloc(s.getFils().get(2),tds)
				+ "fsi"+s.getValeur()+" : |Fin Si\n";
		return resultat;
	}

	private String genererTantque(Noeud noeud,Tds tds) {
	    String resultat = "\n\t|Génération du Tant Que\n";
		TantQue tq = new TantQue(0);
        if (noeud instanceof TantQue) {
            tq = (TantQue) noeud;
        }
		resultat += "boucle"+tq.getValeur()+" : \n"
		        + genererCode(tq.getCondition(),tds)
		        + "\tPOP(r0)\n"
		        + "\tBF(r0, finboucle"+tq.getValeur()+")\n"
		        + genererBloc(tq.getBloc(),tds)
		        + "\tJMP(boucle"+tq.getValeur()+")\n"
		        + "finboucle"+tq.getValeur()+" : |Fin Tant Que\n";
		return resultat;
	}

   private String genererEcrire(Noeud noeud,Tds tds) {
       String resultat = "\n\t|Génération du Ecrire\n";
        resultat += genererCode(noeud.getFils().get(0),tds)
                + "\tPOP(r0)\n"
        		+ "\tWRINT() |Fin Ecrire\n";
        return resultat;
    }

    private String genererRetourne(Noeud noeud,Tds tds) {
        String resultat = "\n\t|Génération du Retourne\n";
        Retour r = new Retour("");
        if (noeud instanceof Retour) {
            r = (Retour) noeud;
        }
        resultat += genererCode(r.getLeFils(),tds)
                + "\tPUSH(r0) |Fin Retourne\n";
        return resultat;
    }

    private String genererPlus(Noeud noeud,Tds tds) {
        String resultat = "\n\t|Génération du Plus\n";
        resultat += genererCode(noeud.getFils().get(0),tds)
                + genererCode(noeud.getFils().get(1),tds)
                + "\tPOP(R1)\n"
                + "\tPOP(R0)\n"
                + "\tADD(R0, R1, R2)\n"
                + "\tPUSH(R2) |Fin Plus\n";
        return resultat;
    }

    private String genererMoins(Noeud noeud,Tds tds) {
        String resultat = "\n\t|Génération du Moins\n";
        resultat += genererCode(noeud.getFils().get(0),tds)
                + genererCode(noeud.getFils().get(1),tds)
                + "\tPOP(r1)\n"
                + "\tPOP(r0)\n"
                + "\tSUB(r0,r1,r2)\n"
                + "\tPUSH(r2) |Fin Moins\n";
        return resultat;
    }

    private String genererDiv(Noeud noeud,Tds tds) {
        String resultat = "\n\t|Génération du Div\n";
        resultat += genererCode(noeud.getFils().get(0),tds)
                + genererCode(noeud.getFils().get(1),tds)
                + "\tPOP(R1)\n"
                + "\tPOP(r0)\n"
                + "\tDIV(r0,r1,r2)\n"
                + "\tPUSH(r2) |Fin Div\n";
        return resultat;
    }

    private String genererMul(Noeud noeud,Tds tds) {
        String resultat = "\n\t|Génération du Mul\n";
        resultat += genererCode(noeud.getFils().get(0),tds)
                + genererCode(noeud.getFils().get(1),tds)
                + "\tPOP(R1)\n"
                + "\tPOP(r0)\n"
                + "\tMUL(r0,r1,r2)\n"
                + "\tPUSH(r2) |Fin Mul\n";
        return resultat;
    }

    private String genererSup(Noeud noeud,Tds tds) {
        String resultat = "\n\t|Génération du Sup\n";
        resultat += genererCode(noeud.getFils().get(0),tds)
                + genererCode(noeud.getFils().get(1),tds)
                + "\tPOP(R1)\n"
                + "\tPOP(r0)\n"
                + "\tCMPLT(r1,r0,r2)\n"
                + "\tPUSH(r2) |Fin Sup\n";
        return resultat;
    }

    private String genererInf(Noeud noeud,Tds tds) {
        String resultat = "\n\t|Génération du Inf\n";
        resultat += genererCode(noeud.getFils().get(0),tds)
                + genererCode(noeud.getFils().get(1),tds)
                + "\tPOP(R1)\n"
                + "\tPOP(r0)\n"
                + "\tCMPLT(r0,r1,r2)\n"
                + "\tPUSH(r2) |Fin Inf\n";
        return resultat;
    }

    private String genererSupegal(Noeud noeud,Tds tds) {
        String resultat = "\n\t|Génération du Supegal\n";
        resultat += genererCode(noeud.getFils().get(0),tds)
                + genererCode(noeud.getFils().get(1),tds)
                + "\tPOP(R1)\n"
                + "\tPOP(r0)\n"
                + "\tCMPLE(r1,r0,r2)\n"
                + "\tPUSH(r2) |Fin Supegal\n";
        return resultat;
    }

    private String genererInfegal(Noeud noeud,Tds tds) {
        String resultat = "\n\t|Génération du Infegal\n";
        resultat += genererCode(noeud.getFils().get(0),tds)
                + genererCode(noeud.getFils().get(1),tds)
                + "\tPOP(R1)\n"
                + "\tPOP(r0)\n"
                + "\tCMPLE(r0,r1,r2)\n"
                + "\tPUSH(r2) |Fin Infegal\n";
        return resultat;
    }

    private String genererEgal(Noeud noeud,Tds tds) {
        String resultat = "\n\t|Génération du Egal\n";
        resultat += genererCode(noeud.getFils().get(0),tds)
                + genererCode(noeud.getFils().get(1),tds)
                + "\tPOP(R1)\n"
                + "\tPOP(r0)\n"
                + "\tCMPEQ(r0,r1,r2)\n"
                + "\tPUSH(r2) |Fin Egal\n";
        return resultat;
    }

    private String genererDiff(Noeud noeud,Tds tds) {
        String resultat = "\n\t|Génération du Diff\n";
        resultat += genererCode(noeud.getFils().get(0),tds)
                + genererCode(noeud.getFils().get(1),tds)
                + "\tPOP(R1)\n"
                + "\tPOP(r0)\n"
                + "\tCMPEQ(r0,r1,r2)\n"
                + "\tCMPEQC(R2,0,R3)\n"
                + "\tPUSH(r) |Fin Diff\n";
        return resultat;
    }

    private String genererIdf(Noeud noeud) {
        String resultat = "\n\t|Génération du Idf\n";
        Idf i = new Idf(0);
        if (noeud instanceof Idf) {
            i = (Idf) noeud;
        }
        resultat += "\tLD("+i.getValeur()+", R0)\n"
                + "\tPUSH(R0) |Fin Idf\n";
        return resultat;
    }

    private String genererConst(Noeud noeud) {
        String resultat = "\n\t|Génération du Const\n";
        Const c = new Const(0);
        if (noeud instanceof Const) {
            c = (Const) noeud;
        }
        resultat += "\tCMOVE("+c.getValeur()+", r0) \n"
                + "\tPUSH(r0) |Fin Const\n";
        return resultat;
    }

    private String genererLire(Noeud noeud) {
        String resultat = "\n\t|Génération du Lire\n";
        resultat = "\tRDINT()\n"
                + "\tPUSH(R0) |Fin Lire\n";
        return resultat;
    }

    private String genererAppel(Noeud noeud) { //PARAMETRES ?
        String resultat = "\n\t|Génération du Appel\n";
        Appel a = new Appel("");
        if (noeud instanceof Appel) {
            a = (Appel) noeud;
        }
        Fonction f = new Fonction("");
        if (a.getValeur() instanceof Fonction) {
            f = (Fonction) a.getValeur();
        }
        resultat += "\tCALL(" + f.getValeur() + ") |Fin Appel\n";
        return resultat;
    }

    private String genererDataGlobal(Tds tds) {
        String resultat = "\n";
        
        for( String k : tds.getTable().keySet()) {
            List<Symbole> i = tds.getTable().get(k);
            for(Symbole s : i) {
                if(s.get_type() == "int" && s.getScope()==Symbole.SCOPE_GLOBAL && s.getCat()==Symbole.CAT_GLOBAL) {
                    if(s.get_valeur() != 0) {
                        resultat += s.getNom() +": LONG("+s.get_valeur()+")\n";
                    }else {
                        resultat += s.getNom() +":\n";
                    }
                }
              }
        }
        return resultat+"\n";
    }
    
    private String genererDataLocal(Fonction f, Tds tds) {
        String resultat = "";
        
        for( String k : tds.getTable().keySet()) {
            List<Symbole> i = tds.getTable().get(k);
            for(Symbole s : i) {
                if(s.get_type() == "int" && s.getScope()== f.getValeur() && s.getCat()==Symbole.CAT_LOCAL) {
                    if(s.get_valeur() != 0) {
                        resultat += s.getNom() +": LONG("+s.get_valeur()+")\n";
                    }else {
                        resultat += s.getNom() +":\n";
                    }
                }
              }
        }
        return resultat;
    }

}
