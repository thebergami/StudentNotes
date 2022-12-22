
/* -- plans for movimentation  -- */


/* -- useful rules */ 

// find a free random location
random_pos(X,Y) :- 
   pos(AgX,AgY,_) &
   jia.random(RX,40)   & RX > 5 & X = (RX-20)+AgX & X > 0 &
   jia.random(RY,40,5) & RY > 5 & Y = (RY-20)+AgY &
   not jia.obstacle(X,Y).



/* -- plans to move to a destination represented in the belief target(X,Y) 
   -- (it is a kind of persistent goal)
*/

// if the target is changed, "restart" move
+target(NX,NY)
  <- .drop_desire(move);
     jia.set_target(NX,NY);
     -at_target;
     !!move.

+!move
   : not target(_,_)
  <- .print("waiting my target....");
     .wait({+target(_,_)});
     !move.

+!move 
   : pos(X,Y,_) & target(X,Y)  // I am at target
  <- -+at_target;
     do(skip);
     !!move.
  
// does one step towards target  
+!move 
    : pos(X,Y,_) & 
      target(BX,BY) & 
      jia.direction(X, Y, BX, BY, D) // jia.direction finds one action D (using A*) towards the target
   <- do(D);  // this action will "block" the intention until it is sent to the simulator (in the end of the cycle)
      !!move. // continue moving
  
// in case of failure, move
-!move
   <- .current_intention(I); .println("failure in moving; intention was: ",I);
      !move.

