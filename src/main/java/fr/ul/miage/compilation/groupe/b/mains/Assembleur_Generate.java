package main.java.fr.ul.miage.compilation.groupe.b.mains;

import main.java.fr.ul.miage.compilation.groupe.b.generateur.*;
import main.java.fr.ul.miage.tds.*;

import java.util.ArrayList;

import main.java.fr.ul.miage.arbre.*;

public class Assembleur_Generate {
    
    public static void main(String[] args) throws Exception {
        int code = 1; //Valeur à changer pour exécuter le code example voulu
        Tds tds = new Tds();
        Prog prog = null;
        switch(code) {
            case 1:
                tds.ajouter("main",Symbole.CAT_FONCTION, Symbole.SCOPE_GLOBAL,"void");
                prog = generateCode1();               
                break;
            case 2:
                tds.ajouter("main",Symbole.CAT_FONCTION,Symbole.SCOPE_GLOBAL,"void");
                tds.ajouter("i",Symbole.CAT_GLOBAL,Symbole.SCOPE_GLOBAL,"int").set_valeur(10);
                tds.ajouter("j",Symbole.CAT_GLOBAL,Symbole.SCOPE_GLOBAL,"int").set_valeur(20);
                tds.ajouter("k",Symbole.CAT_GLOBAL,Symbole.SCOPE_GLOBAL,"int");
                tds.ajouter("l",Symbole.CAT_GLOBAL,Symbole.SCOPE_GLOBAL,"int");
                prog = generateCode2();  
                break;
            case 3:
                tds.ajouter("main",Symbole.TYPE_VOID,Symbole.CAT_FONCTION, "void");
                Symbole s2 = tds.ajouter("i",Symbole.CAT_GLOBAL,Symbole.SCOPE_GLOBAL,"int").set_valeur(10);
                Symbole s3 = tds.ajouter("j",Symbole.CAT_GLOBAL,Symbole.SCOPE_GLOBAL,"int").set_valeur(20);
                Symbole s4 = tds.ajouter("k",Symbole.CAT_GLOBAL,Symbole.SCOPE_GLOBAL,"int");
                Symbole s5 = tds.ajouter("l",Symbole.CAT_GLOBAL,Symbole.SCOPE_GLOBAL,"int");
                prog = generateCode3();  
                break;
            case 4:
                tds.ajouter("main",Symbole.CAT_FONCTION,Symbole.SCOPE_GLOBAL,"void");
                tds.ajouter("i",Symbole.CAT_GLOBAL,Symbole.SCOPE_GLOBAL,"int");
                tds.ajouter("j",Symbole.CAT_GLOBAL,Symbole.SCOPE_GLOBAL,"int").set_valeur(20);
                prog = generateCode4();
                break;
            case 5:
                tds.ajouter("main",Symbole.CAT_FONCTION,Symbole.SCOPE_GLOBAL, "void");
                tds.ajouter("i",Symbole.CAT_GLOBAL,Symbole.SCOPE_GLOBAL,"int");
                prog = generateCode5();
                break;
            case 6:
                tds.ajouter("main",Symbole.CAT_FONCTION,Symbole.SCOPE_GLOBAL,"void");
                tds.ajouter("i",Symbole.CAT_GLOBAL,Symbole.SCOPE_GLOBAL,"int");
                tds.ajouter("n",Symbole.CAT_GLOBAL,Symbole.SCOPE_GLOBAL,"int").set_valeur(5);
                prog = generateCode6();
                break;
            case 7:
                tds.ajouter("main",Symbole.CAT_FONCTION,Symbole.CAT_GLOBAL,"void");
                tds.ajouter("a",Symbole.CAT_GLOBAL,Symbole.CAT_GLOBAL,"int").set_valeur(10);
                tds.ajouter("f",Symbole.CAT_FONCTION, Symbole.SCOPE_GLOBAL,"int").set_nbparam(1).set_nbloc(2);
                tds.ajouter("i",Symbole.CAT_PARAMETRE,"f","int").set_rang(0);
                tds.ajouter("x",Symbole.CAT_LOCAL,"f","int").set_rang(0);
                tds.ajouter("y",Symbole.CAT_LOCAL,"f","int").set_rang(1);
                prog = generateCode7();
                break;
            case 8:
                tds.ajouter("main",Symbole.CAT_FONCTION,Symbole.CAT_GLOBAL,"void");
                tds.ajouter("a",Symbole.CAT_GLOBAL,Symbole.CAT_GLOBAL,"int");
                tds.ajouter("f",Symbole.CAT_FONCTION, Symbole.SCOPE_GLOBAL,"int").set_nbparam(2).set_nbloc(1);
                tds.ajouter("x",Symbole.CAT_LOCAL,"f","int").set_rang(0);
                tds.ajouter("i",Symbole.CAT_PARAMETRE,"f","int").set_rang(0);
                tds.ajouter("j",Symbole.CAT_PARAMETRE,"f","int").set_rang(1);
                prog = generateCode8();
                break;
            
            default:
                break;
            }
        System.out.println(tds.toString());
        Generateur g = new Generateur();
        System.out.println(g.generate(prog,tds));
        Afficheur.afficher(prog);
    }

    private static Prog generateCode1() {
        Prog prog = new Prog();
        Fonction main = new Fonction("main",prog);
        prog.ajouterUnFils(main);
        return prog;
    }
    
    private static Prog generateCode2() {
        Prog prog = new Prog();
        Fonction main = new Fonction("main", prog);
        prog.ajouterUnFils(main);
        return prog;
    }
    
    private static Prog generateCode3() {
        Prog prog = new Prog();
        Idf i3 = new Idf("i",prog);
        Idf j3 = new Idf("j",prog);
        Idf k3 = new Idf("k",prog);
        Idf l3 = new Idf("l",prog);
        Fonction main = new Fonction("main",prog);
        Affectation aff3_2 = new Affectation(main);
        Const c3_2 = new Const(2,aff3_2);
        aff3_2.setFilsGauche(k3);
        aff3_2.setFilsDroit(c3_2);
        main.ajouterUnFils(aff3_2);
        Affectation aff3_3 = new Affectation(main);
        aff3_3.setFilsGauche(l3);
        Plus add3 = new Plus(aff3_3);
        add3.setFilsGauche(i3);
        Multiplication mul3 = new Multiplication(add3);
        Const c3_3 = new Const(3,mul3);
        mul3.setFilsGauche(c3_3);
        mul3.setFilsDroit(j3);
        add3.setFilsDroit(mul3);
        aff3_3.setFilsDroit(add3);
        main.ajouterUnFils(aff3_3);
        prog.ajouterUnFils(main);
        return prog;
    }

    private static Prog generateCode4() {
        Prog prog = new Prog();
        Idf i4= new Idf("i",prog);
        Idf j4= new Idf("j",prog);
        Fonction main = new Fonction("main",prog);
        Affectation aff4_1 = new Affectation(main);
        Lire lire4 = new Lire(aff4_1);
        aff4_1.setFilsGauche(i4);
        aff4_1.setFilsDroit(lire4);
        main.ajouterUnFils(aff4_1);
        Ecrire ec4 = new Ecrire(main);
        Plus add4 = new Plus(ec4);
        add4.setFilsGauche(i4);
        add4.setFilsDroit(j4);
        ec4.setLeFils(add4);
        main.ajouterUnFils(ec4);
        prog.ajouterUnFils(main);
        return prog;
    }

    private static Prog generateCode5() {
        Prog prog = new Prog();
        Idf i5 = new Idf("i",prog);
        Fonction main = new Fonction("main",prog);
        Affectation aff5 = new Affectation(main);
        Lire lire5 = new Lire(aff5);
        aff5.setFilsGauche(i5);
        aff5.setFilsDroit(lire5);
        Si si5 = new Si(1,main);
        Superieur inf5 = new Superieur(si5);
        Noeud c5 = new Const(10,inf5);
        inf5.setFilsGauche(i5);
        inf5.setFilsDroit(c5);
        si5.setCondition(inf5);
        Bloc bl5 = new Bloc(si5);
        Ecrire ec5 = new Ecrire(bl5);
        Const c5_1 = new Const(1,bl5);
        ec5.setLeFils(c5_1);
        bl5.ajouterUnFils(ec5);
        si5.setBlocAlors(bl5);
        Bloc bl5_1 = new Bloc(si5);
        Ecrire ec5_1 = new Ecrire(bl5_1);
        Const c5_2 = new Const(2,bl5_1);
        ec5_1.setLeFils(c5_2);
        bl5_1.ajouterUnFils(ec5_1);
        si5.setBlocSinon(bl5_1);
        main.ajouterUnFils(aff5);
        main.ajouterUnFils(si5);
        prog.ajouterUnFils(main);
        return prog;
    }

    private static Prog generateCode6() {
        Prog prog = new Prog();
        Idf i6 = new Idf("i",prog);
        Idf n6 = new Idf("n",prog);
        Fonction main = new Fonction("main",prog);
        Affectation aff6 = new Affectation(main);
        Const c6 = new Const(0,aff6);
        aff6.setFilsGauche(i6);
        aff6.setFilsDroit(c6);
        TantQue tq6 = new TantQue(1,main);
        Inferieur inf6 = new Inferieur(tq6);
        inf6.setFilsGauche(i6);
        inf6.setFilsDroit(n6);
        Bloc b6 = new Bloc(tq6);
        tq6.ajouterUnFils(inf6);
        tq6.ajouterUnFils(b6);
        Ecrire e6 = new Ecrire(b6);
        e6.setLeFils(i6);
        Affectation aff6_1 = new Affectation(b6);
        Plus plus6 = new Plus(aff6_1);
        Const c6_1 = new Const(1,plus6);
        plus6.setFilsGauche(i6);
        plus6.setFilsDroit(c6_1);
        aff6_1.setFilsGauche(i6);
        aff6_1.setFilsDroit(plus6);
        ArrayList<Noeud> listeNoeud6 = new ArrayList<Noeud>();
        listeNoeud6.add(inf6);
        listeNoeud6.add(b6);
        tq6.setFils(listeNoeud6);
        b6.ajouterUnFils(e6);
        b6.ajouterUnFils(aff6_1);
        main.ajouterUnFils(aff6);
        main.ajouterUnFils(tq6);
        prog.ajouterUnFils(main);
        return prog;
    }

    private static Prog generateCode7() {
        Prog prog = new Prog();
        Idf a7 = new Idf("a",prog);
        Fonction f7 = new Fonction("f",prog);
        Idf i7 = new Idf("i",f7);
        Idf x7 = new Idf('x',f7);
        Idf y7 = new Idf('y',f7);
        Affectation aff7_1 = new Affectation(f7);
        Const c7_1 = new Const(1,aff7_1);
        aff7_1.setFilsGauche(x7);
        aff7_1.setFilsDroit(c7_1);
        Affectation aff7_2 = new Affectation(f7);
        Const c7_2 = new Const(1,aff7_2);
        aff7_2.setFilsGauche(y7);
        aff7_2.setFilsDroit(c7_2);
        Affectation aff7_3 = new Affectation(f7);
        aff7_3.setFilsGauche(a7);
        Plus add7 = new Plus(aff7_3);
        add7.setFilsGauche(i7);
        Plus add7_1 = new Plus(add7);
        add7_1.setFilsGauche(x7);
        add7_1.setFilsDroit(y7);
        add7.setFilsDroit(add7_1);
        aff7_3.setFilsDroit(add7);
        f7.ajouterUnFils(aff7_1);
        f7.ajouterUnFils(aff7_2);
        f7.ajouterUnFils(aff7_3);
        Fonction main = new Fonction("main",prog);
        Appel ap7 = new Appel(f7,main);
        Const c7_3 = new Const(3,ap7);
        ap7.ajouterUnFils(c7_3);
        Ecrire ec7 = new Ecrire(main);
        ec7.setLeFils(a7);
        main.ajouterUnFils(ap7);
        main.ajouterUnFils(ec7);
        prog.ajouterUnFils(f7);
        prog.ajouterUnFils(main);
        return prog;
    }
    
    private static Prog generateCode8() {
        Prog prog = new Prog();
        Idf a8 = new Idf("a",prog);
        Noeud f8 = new Fonction("f",prog);
        Idf i8 = new Idf("i",f8);
        Idf j8 = new Idf("j",f8);
        Affectation aff8 = new Affectation(f8);
        Idf x8 = new Idf("x",f8);
        Plus plus8 = new Plus(aff8);
        plus8.setFilsGauche(i8);
        plus8.setFilsDroit(j8);
        aff8.setFilsGauche(x8);
        aff8.setFilsDroit(plus8);
        Retour ret8 = new Retour("f",f8);
        Plus plus8_1 = new Plus(ret8);
        Const c8 = new Const(10,plus8_1);
        plus8_1.setFilsGauche(x8);
        plus8_1.setFilsDroit(c8);
        ret8.ajouterUnFils(plus8_1);
        f8.ajouterUnFils(aff8);
        f8.ajouterUnFils(ret8);
        Noeud main = new Fonction("main",prog);
        Affectation aff8_1 = new Affectation(main);
        Appel appel8 = new Appel(f8,aff8_1);
        Const c8_1 = new Const(1,appel8);
        Const c8_2 = new Const(2,appel8);
        appel8.ajouterUnFils(c8_1);
        appel8.ajouterUnFils(c8_2);
        aff8_1.setFilsGauche(a8);
        aff8_1.setFilsDroit(appel8);
        Ecrire e8 = new Ecrire(main);
        e8.ajouterUnFils(a8);
        main.ajouterUnFils(aff8_1);
        main.ajouterUnFils(e8);
        prog.ajouterUnFils(f8);
        prog.ajouterUnFils(main);
        return prog;
    }
}
