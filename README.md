# jlox-from-crafting-interpreters

This is the implementation of the Lox programming language from the book "Crafting Interpreters" by Robert Nystrom. The source includes solutions to some of the challenges at the end of each chapter.


## Example test programs to try in the REPL
Note: The REPL does not support multiline entry yet, this is a TODO for the future.
```
> fun fib (n) { if (n <= 1) return n; return fib(n-2) + fib (n-1);}  for (var i = 0; i < 20 ; i = i + 1) { print fib(i); }
0
1
1
2
3
5
8
13
21
34
55
89
144
233
377
610
987
1597
2584
4181
> 
```