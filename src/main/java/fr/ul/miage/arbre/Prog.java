/*
 *  License and Copyright:
 *  This file is part of arbre project.
 *
 *   It is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   any later version.
 *
 *   It is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 * 
 *  Copyright 2019 by LORIA, Université de Lorraine
 *  All right reserved 
 */
package main.java.fr.ul.miage.arbre;

import java.util.ArrayList;

/**
 * Description :
 * Noeud PROG : n-aire / pas de valeur
 * @author Azim Roussanaly
 * Created at 28 févr. 2019
 */
public class Prog extends Noeud0 {
	//constructeurs
	public Prog() {
        super(null);
		setCat(Categories.PROG);
		setFils(new ArrayList<>());
	}
}
