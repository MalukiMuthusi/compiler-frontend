package lexer;

import java.io.*;
import java.util.*;
import symbols.*;

/**
 * maps strings to words
 */
public class Lexer {
   public static int line = 1;
   char peek = ' ';

   // symbol table
   Hashtable words = new Hashtable();

   void reserve(Word w) {
      words.put(w.lexeme, w);
   }

   /**
    * reseve key words
    */
   public Lexer() {

      reserve(new Word("if", Tag.IF));
      reserve(new Word("else", Tag.ELSE));
      reserve(new Word("while", Tag.WHILE));
      reserve(new Word("do", Tag.DO));
      reserve(new Word("break", Tag.BREAK));

      reserve(Word.True);
      reserve(Word.False);

      reserve(Type.Int);
      reserve(Type.Char);
      reserve(Type.Bool);
      reserve(Type.Float);
   }

   /**
    * read the next input character into @peek
    * 
    * @throws IOException
    */
   void readch() throws IOException {
      peek = (char) System.in.read();
   }

   /**
    * identify composite tokens
    * 
    * @param c
    * @return
    * @throws IOException
    */
   boolean readch(char c) throws IOException {
      readch();
      if (peek != c)
         return false;
      peek = ' ';
      return true;
   }

   /**
    * recognize numbers, identifiers, and reserved words
    */
   public Token scan() throws IOException {
      for (;; readch()) {

         // skip white spaces
         if (peek == ' ' || peek == '\t')
            continue;
         else if (peek == '\n')
            line = line + 1;
         else
            break;
      }

      // check the character you have read if it is part of a composite token e.g &&
      switch (peek) {
      case '&':
         if (readch('&'))
            return Word.and;
         else
            return new Token('&');
      case '|':
         if (readch('|'))
            return Word.or;
         else
            return new Token('|');
      case '=':
         if (readch('='))
            return Word.eq;
         else
            return new Token('=');
      case '!':
         if (readch('='))
            return Word.ne;
         else
            return new Token('!');
      case '<':
         if (readch('='))
            return Word.le;
         else
            return new Token('<');
      case '>':
         if (readch('='))
            return Word.ge;
         else
            return new Token('>');
      }

      // handle digits in the source code
      if (Character.isDigit(peek)) {
         int v = 0;
         do {
            v = 10 * v + Character.digit(peek, 10);
            readch();
         } while (Character.isDigit(peek));
         if (peek != '.')
            return new Num(v);
         float x = v;
         float d = 10;
         for (;;) {
            readch();
            if (!Character.isDigit(peek))
               break;
            x = x + Character.digit(peek, 10) / d;
            d = d * 10;
         }
         return new Real(x);
      }

      // handle letters, letters for form variables and keywords.
      if (Character.isLetter(peek)) {
         StringBuffer b = new StringBuffer();
         do {
            b.append(peek);
            readch();
         } while (Character.isLetterOrDigit(peek));
         String s = b.toString();
         Word w = (Word) words.get(s);
         if (w != null)
            return w;
         w = new Word(s, Tag.ID);
         words.put(s, w);
         return w;
      }

      // return any remaining characters as token
      Token tok = new Token(peek);
      peek = ' ';
      return tok;
   }
}
