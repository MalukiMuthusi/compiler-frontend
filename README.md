# Compiler

## The Language Grammar

```bnf
P  ->  { DD SS }
DD ->  e | DD D | D
D  ->  T id ;
T  ->  T [ num ] | int | float | char | bool
SS ->  e | SS S | S
S  ->  L = E ; | if ( B ) S | if ( B ) S else S | while ( B ) S
   |   do S while ( B ) ; | break ; | { DD SS }
B  ->  B or B | B and B | ! B | ( B ) | E rel E | true | false
E  ->  E + E | E - E | E * E | E / E | L | ( B ) | num
L  ->  L [ B ] | id
```

### Package lexer

class Tag. Tags distinguish tokens.
class Token with subclasses Num, Real, and Word
class Lexer, with procedure scan

### Package symbols

class Type.  Put types here.
class Id.  Could have put Id's with expressions; in fact Id extends Expr
class Env.  Linked symbol tables.

### Package inter for intermediate code

For simplicity, the front end builds syntax trees.  Three-address code is
emitted during a subsequent pass.  We generate short-circuit code for
boolean expressions.

An optimizing compiler would presumably create intermediate-code objects
rather than emitting strings.  Further, Chapter 9 has examples with code
that might be produced by backpatching -- that's a variant to be explored
separately.
