package fr.ul.miage.compilation.groupe.b.mains;
import fr.ul.miage.arbre.Afficheur;
import fr.ul.miage.arbre.*;
import fr.ul.miage.tds.*;

<<<<<<< HEAD
public class Main_Code5 {
    public static void main(String[] args) throws Exception{
    	Tds tdsEx = new Tds();
    	Symbole s = tdsEx.ajouter("main",Symbole.CAT_FONCTION,Symbole.SCOPE_GLOBAL);
		Symbole s2 = tdsEx.ajouter("i",Symbole.CAT_GLOBAL,Symbole.SCOPE_GLOBAL);
		System.out.println(tdsEx);
		
		
		Noeud prog5 = new Prog();
	    Idf i5 = new Idf("i");
	    
	    Noeud main5 = new Fonction("main");
	    
	    Affectation aff5 = new Affectation();
	    Lire lire5 = new Lire();
	    aff5.setFilsGauche(i5);
	    aff5.setFilsDroit(lire5);
	    
	    Si si5 = new Si(1);
	    Noeud c5 = new Const(10);
	    Inferieur inf5 = new Inferieur();
=======
import fr.ul.miage.compilation.groupe.b.generateur.*;
import fr.ul.miage.arbre.Afficheur;
import fr.ul.miage.arbre.*;
import fr.ul.miage.tds.*;

public class Main_Code5 {

    public static void main(String[] args) throws Exception {
        Tds tds = new Tds();
        Symbole s = tds.ajouter("main",Symbole.CAT_FONCTION,Symbole.SCOPE_GLOBAL);
        Symbole s2 = tds.ajouter("i",Symbole.CAT_GLOBAL,Symbole.SCOPE_GLOBAL);
        System.out.println(tds);
        
        
        Noeud prog = new Prog();
        Idf i5 = new Idf("i");
        
        Noeud main = new Fonction("main");
        
        Affectation aff5 = new Affectation();
        Lire lire5 = new Lire();
        aff5.setFilsGauche(i5);
        aff5.setFilsDroit(lire5);
        
        Si si5 = new Si(1);
        Noeud c5 = new Const(10);
        Inferieur inf5 = new Inferieur();
>>>>>>> leurs-conneries
        inf5.setFilsGauche(i5);
        inf5.setFilsDroit(c5);
        si5.setCondition(inf5);
        
        Bloc bl5 = new Bloc();
        Ecrire ec5 = new Ecrire();
        Const c5_1 = new Const(1);
        ec5.setLeFils(c5_1);
        bl5.ajouterUnFils(ec5);
        si5.setBlocAlors(bl5);
        
        Bloc bl5_1 = new Bloc();
        Ecrire ec5_1 = new Ecrire();
        Const c5_2 = new Const(2);
        ec5_1.setLeFils(c5_2);
        bl5_1.ajouterUnFils(ec5_1);
        si5.setBlocSinon(bl5_1);
        
<<<<<<< HEAD
	    main5.ajouterUnFils(aff5);
	    main5.ajouterUnFils(si5);
	    prog5.ajouterUnFils(main5);
	    Afficheur.afficher(prog5);
=======
        main.ajouterUnFils(aff5);
        main.ajouterUnFils(si5);
        prog.ajouterUnFils(main);
        Afficheur.afficher(prog);
        
        Generateur g = new Generateur();
        g.generate(prog,tds);
>>>>>>> leurs-conneries
    }

}
