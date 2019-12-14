This is my version of Conway's Game of Life.

Currently, the game uses a variable n x n board and updates at a rate of 2 times per second The boundaries of the board are infinite, so a paceship moving to the left will loop back onto the right side of the board. The controls are :
  - LMB => Change the life status of an individual cell
  - Space => Start the game's live/die simulation
  - R => Resets the world
  
 Future implementations will (hopefully) include:
  - Variable tick rate and sizing through a gui
  - Perhaps a toggle for the infinite boundaries.
