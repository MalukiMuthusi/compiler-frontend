package lexer;

/**
 * A token be a variable, type
 * see the @file  README for all the token types in the grammar
 */
public class Token {

	public final int tag;
	public Token(int t) { tag = t; }
	public String toString() {return "" + (char)tag;}
}
