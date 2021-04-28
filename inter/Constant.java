package inter;

import lexer.*;
import symbols.*;

/**
 * 
 */
public class Constant extends Expr {

   /**
    * construct a leaf in the syntax tree with label @param tok and type @param p
    * 
    * @param tok
    * @param p
    */
   public Constant(Token tok, Type p) {
      super(tok, p);
   }

   /**
    * create a constant object from an integer
    * 
    * @param i
    */
   public Constant(int i) {
      super(new Num(i), Type.Int);
   }

   public static final Constant True = new Constant(Word.True, Type.Bool);
   public static final Constant False = new Constant(Word.False, Type.Bool);

   /**
    * t=0 is special label
    */
   public void jumping(int t, int f) {
      if (this == True && t != 0)
         emit("goto L" + t);
      else if (this == False && f != 0)
         emit("goto L" + f);
   }
}
