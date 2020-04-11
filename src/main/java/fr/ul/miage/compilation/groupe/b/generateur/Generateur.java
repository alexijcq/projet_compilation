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
		resultat += genererData(tds);
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
    		case FONCTION:
                resultat = genererFonction(noeud,tds); 
                break;
    		case APPEL:
                resultat = genererAppel(noeud,tds);
                break;
    		case RET:
    			resultat = genererRetourne(noeud,tds);
    			break;
    		case PLUS:
    		case MOINS:
    		case DIV:
    		case MUL:
    			resultat = genererCalcul(noeud,tds);
    			break;
    		case IDF:
    			resultat = genererIdf(noeud,tds);
    			break;
    		case CONST:
    			resultat = genererConst(noeud);
    			break;
    		case ECR:
                resultat = genererEcrire(noeud,tds);
                break;
    		case LIRE:
    			resultat = genererLire(noeud);
    			break;
    		default:
    			break;
    	}
		return resultat;
	}

	private String genererExprBooleen(Noeud noeud, Tds tds) {
	    String resultat = genererCode(noeud.getFils().get(0),tds)
                + genererCode(noeud.getFils().get(1),tds)
                + "\tPOP(R1)\n"
                + "\tPOP(r0)\n";
	    switch(noeud.getCat()) {
    	    case SUP:
    	        resultat += "\tCMPLT(r1,r0,r2)\n";
    	        break;
            case INF:
                resultat += "\tCMPLT(r0,r1,r2)\n";
                break;
            case SUPE:
                resultat += "\tCMPLE(r1,r0,r2)\n";
                break;
            case INFE:
                resultat += "\tCMPLE(r0,r1,r2)\n";
                break;
            case EG:
                resultat += "\tCMPEQ(r0,r1,r2)\n";
                break;
            case DIF:
                resultat += "\tCMPEQ(r0,r1,r2)\n"
                        + "\tCMPEQC(R2,0,R3)\n";
                break;
            default : 
                break;
	    }
        return resultat+"\tPUSH(r2)\n";
    }

    private String genererCalcul(Noeud noeud, Tds tds) {
        String resultat = genererCode(noeud.getFils().get(0),tds)
                + genererCode(noeud.getFils().get(1),tds)
                + "\tPOP(R1)\n"
                + "\tPOP(R0)\n";
        switch(noeud.getCat()) {
            case PLUS:
                resultat += "\tADD(R0, R1, R2)\n";
                break;
            case MOINS:
                resultat += "\tSUB(r0,r1,r2)\n";
                break;
            case DIV:
                resultat += "\tDIV(r0,r1,r2)\n";
                break;
            case MUL:
                resultat += "\tMUL(r0,r1,r2)\n";
                break;
            default :
                break;
        }
        return resultat+"\tPUSH(r2)\n";
    }

    private String genererFonction(Noeud noeud,Tds tds) {
	    String resultat = "\n";
        Fonction f = null;
        if (noeud instanceof Fonction)
            f = (Fonction) noeud;
        resultat += f.getValeur()+" :\n"
                + "\tPUSH(LP)\n"
                + "\tPUSH(BP)\n"
                + "\tMOVE(SP,BP)\n";
        int vlocal = 0;
        for( String k : tds.getTable().keySet()) {
            List<Symbole> i = tds.getTable().get(k);
            for(Symbole s : i) {
                if(s.getScope() == f.getValeur() && s.get_type() == "int" && s.getCat()==Symbole.CAT_LOCAL)
                        vlocal++;
            }
        }
        resultat += "\tALLOCATE("+vlocal+")\n";
        
        for (int i = 0; i < noeud.getFils().size(); i++) {
            resultat += genererCode(noeud.getFils().get(i),tds);
        }
        
        resultat += "fin_fonc_"+f.getValeur()+":\n"
                + "\tMOVE(BP,SP)\n"
                + "\tPOP(BP)\n"
                + "\tPOP(LP)\n"
                + "\tRTN()\n";
        return resultat;
	}

	private String genererBloc(Noeud noeud,Tds tds) {
    	String resultat = "";
    	for (int i = 0; i < noeud.getFils().size(); i++) {
            resultat += genererCode(noeud.getFils().get(i),tds);
        }
        return resultat;
    }

	private String genererAffectation(Noeud noeud,Tds tds) {
	    String resultat = "";
		Idf id = null;
		if(noeud.getFils().get(0) instanceof Idf)
			id = (Idf) noeud.getFils().get(0);
		resultat += genererCode(noeud.getFils().get(1),tds)
				+ "\tPOP(R0)\n";
		String fonctionName = rechercheFonction(id, tds);
		Symbole s = tds.rechercher(id.getValeur()+"",fonctionName);
        if(s.getCat()==Symbole.CAT_LOCAL)
            resultat += "\tPUTFRAME(r0,"+s.get_rang()*4+")\n";
        else if(s.getCat()==Symbole.CAT_PARAMETRE)
            resultat += "\tPUTFRAME(r0,"+(s.get_rang()+3)*(-4)+")\n";
        else
            resultat += "\tST(r0,"+s.getNom()+")\n"; 
		return resultat;
	}

	private String genererSi(Noeud noeud,Tds tds) {
	    String resultat = "";
	    Si s = null;
        if(noeud instanceof Si)
            s = (Si) noeud;
		resultat += genererExprBooleen(s.getFils().get(0),tds)
				+ "\tPOP(R0)\n"
				+ "\tBF(R0, sinon"+s.getValeur()+")\n"
				+ genererBloc(s.getFils().get(1),tds)
				+ "\tBR(fsi"+s.getValeur()+")\n"
				+ "sinon"+s.getValeur()+" :\n"
				+ genererBloc(s.getFils().get(2),tds)
				+ "fsi"+s.getValeur()+" : \n";
		return resultat;
	}

	private String genererTantque(Noeud noeud,Tds tds) {
	    String resultat = "";
		TantQue tq = null;
        if (noeud instanceof TantQue)
            tq = (TantQue) noeud;
		resultat += "boucle"+tq.getValeur()+" : \n"
		        + genererExprBooleen(tq.getCondition(),tds)
		        + "\tPOP(r0)\n"
		        + "\tBF(r0, finboucle"+tq.getValeur()+")\n"
		        + genererBloc(tq.getBloc(),tds)
		        + "\tBR(boucle"+tq.getValeur()+")\n"
		        + "finboucle"+tq.getValeur()+" :\n";
		return resultat;
	}

    private String genererEcrire(Noeud noeud,Tds tds) {
       String resultat = "";
        resultat += genererCode(noeud.getFils().get(0),tds)
                + "\tPOP(r0)\n"
        		+ "\tWRINT()\n";
        return resultat;
    }

    private String genererRetourne(Noeud noeud,Tds tds) {
        String resultat = "";
        Retour r = null;
        if (noeud instanceof Retour)
            r = (Retour) noeud;
        Symbole s = tds.rechercher(r.getValeur()+"",Symbole.SCOPE_GLOBAL);
        resultat += genererCode(r.getLeFils(),tds)
                + "\tPOP(r0)\n"
                + "\tPUTFRAME(r0,"+(s.get_nbparam()+3)*(-4)+")\n"
                + "\tBR(fin_fonc_"+r.getValeur()+")\n";
        return resultat;
    }

    private String genererIdf(Noeud noeud, Tds tds) {
        String resultat = "";
        Idf id = null;
        if (noeud instanceof Idf)
            id = (Idf) noeud;
        String fonctionName = rechercheFonction(id, tds);
        Symbole s = tds.rechercher(id.getValeur()+"",fonctionName);
        if(s.getCat()==Symbole.CAT_LOCAL)
            resultat += "\tGETFRAME("+s.get_rang()*4+",r0)\n";
        else if(s.getCat()==Symbole.CAT_PARAMETRE)
            resultat += "\tGETFRAME("+(s.get_rang()+3)*(-4)+",r0)\n";
        else
            resultat += "\tLD("+s.getNom()+",r0)\n";
        resultat += "\tPUSH(R0)\n";
        return resultat;
    }

    private String genererConst(Noeud noeud) {
        String resultat = "";
        Const c = null;
        if (noeud instanceof Const)
            c = (Const) noeud;
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

    private String genererAppel(Noeud noeud, Tds tds) {
        String resultat = "";
        Appel a = null;
        if (noeud instanceof Appel)
            a = (Appel) noeud;
        Fonction f = null;
        if (a.getValeur() instanceof Fonction)
            f = (Fonction) a.getValeur();
        Symbole s = tds.rechercher(f.getValeur()+"", Symbole.SCOPE_GLOBAL);
        if (!s.get_type().equals(Symbole.TYPE_VOID)){
            resultat += "\tALLOCATE(1)\n";
        }
        if(noeud.getFils() != null && !noeud.getFils().isEmpty()) {
            for(Noeud n : noeud.getFils()) {
                resultat+= genererCode(n,tds);
            }
        }
        for(String k : tds.getTable().keySet()) {
            List<Symbole> i = tds.getTable().get(k);
            for(Symbole si : i) {
                if(si.getCat() == Symbole.CAT_FONCTION && si.getNom() == f.getValeur())
                    resultat += "\tCALL("+ f.getValeur()+")\n"
                           + "\tDEALLOCATE("+si.get_nbparam()+")\n";
            }
        }
        return resultat;
    }

    private String genererData(Tds tds) {
        String resultat = "\n";
        for( String k : tds.getTable().keySet()) {
            List<Symbole> i = tds.getTable().get(k);
            for(Symbole s : i) {
                if(s.get_type() == "int" && s.getScope()==Symbole.SCOPE_GLOBAL && s.getCat()==Symbole.CAT_GLOBAL)
                        resultat += s.getNom() +": LONG("+s.get_valeur()+")\n";
            }
        }
        return resultat;
    }
    
    private String rechercheFonction(Idf n, Tds tds) {
        Noeud test = n.getNoeudPere();
        String res = "";
        for( String k : tds.getTable().keySet()) {
            List<Symbole> i = tds.getTable().get(k);
            for(Symbole s : i) {
                if(s.getNom() == n.getValeur() && s.get_type() == "int" && s.getCat()==Symbole.CAT_GLOBAL)
                        return "";
            }
        }
        while (test != null && !(test instanceof Fonction)) {
            test = test.getNoeudPere();
        }
        if(test != null) {
            Fonction f = (Fonction) test;
            res = (String) f.getValeur();
        }
        return res;
    }
}
