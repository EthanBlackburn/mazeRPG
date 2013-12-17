mazeRPG
=======
mazeRPG is a simple maze game with randomly generated mazes and levels.

Levels:
-------
  Each level contains a maze with an objective (i.e. end location) and enemies with simple AI. Monsters move from one start location to another until they are in close range with the player. Once they are in range, they begin to follow and attack you. The monsters difficulty increases as the player reaches higher levels.

Maze:
-----
A "maze" (as of now called a Grid) consists of Walls and a Path. Both of these are represented by a set of Virtices which symbollically make up a graph. Mazes are constructed randomly via a randomized DFS method.

Vertex:
-------
A vertex has a location (x and y coordinates) and an ArrayList of other vertices, called connections, which symbolically records edges of a graph. This class has various functions in which one Vertex may be added or removed from another Vertex's connections and vice-versa. Lets say we have two Vertices, Vertex A and Vertex B, if A is in B's connections, then the function addConnection() guarantees that B should also be in A's connections. removeConnection() also has this guarantee.

Location:
---------
Locations are used in a variety of differnt classes. this is essentially an x and y coordinate.

