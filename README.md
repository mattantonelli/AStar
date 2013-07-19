<b><u>A* Search Algorithm</u>
Implemented by Matt Antonelli</b>

<i>"Peter Hart, Nils Nilsson and Bertram Raphael of Stanford Research Institute (now SRI International) first described the 
algorithm in 1968. It is an extension of Edsger Dijkstra's 1959 algorithm. A* achieves better time performance by using heuristics."</i>
========

This Java application implements the A* search algorithm on a two-dimensional 5x5 grid. No movement cost
is associated with any of the tiles of the grind, and no obstacle handling exists minus the size of the grid.

Each tile is tied with three variables.
<ol><li>F: The total cost to reach the end tile. (G + H)</li>
<li>G: The actual cost to reach this tile.</li>
<li>H: The estimated cost to reach the end tile from this tile, calculated using the Manhattan Distance.</li></ol>

The GUI highlights the start tile in <i>blue</i> and the end tile in <i>green</i>. While the path is being calculated, the
selected tile being checked is highlighted in <i>yellow</i>. After the shortest path has been found, the tiles between the
start tile and the end tile are highlighted in <i>yellow</i>.

Each tile's F, G, and H values are modified as they are reached in the path calculation.

The start and end tiles can be modified from the <i>run</i> method in <i>AStar.java</i>.

In a possible future update, start and end tiles will be selected through mouse input, which will calculate a new shortest path.