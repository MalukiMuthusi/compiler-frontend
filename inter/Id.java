package inter;

import lexer.*;
import symbols.*;

/**
 * Identifier is an address the node for an Id is leaf in a syntax tree
 */
public class Id extends Expr {

	/**
	 * holds the relative address of this identifier
	 */
	public int offset;

	public Id(Word id, Type p, int b) {
		super(id, p);
		offset = b;
	}

	// public String toString() {return "" + op.toString() + offset;}
}
