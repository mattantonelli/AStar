A* Search Algorithm
========
<i>Implemented by: Matt Antonelli</i>

This Java application implements the A* search algorithm on a two-dimensional 5x5 grid. No movement cost
is associated with any of the tiles of the grid.

Each tile is tied with three variables:
<ol><li>F: The total cost to reach the end tile. (G + H)</li>
<li>G: The actual cost to reach this tile.</li>
<li>H: The estimated cost to reach the end tile from this tile, calculated using the Manhattan Distance.</li></ol>

The GUI highlights the start tile in <i>blue</i> and the end tile in <i>green</i>. While the path is being calculated, the
selected tile being checked is highlighted in <i>yellow</i>. After the shortest path has been found, the tiles between the
start tile and the end tile are highlighted in <i>yellow</i>.

Each tile's F, G, and H values are modified as they are reached in the path calculation.

The render speed of the path can be increased by lowering the value of DELAY.

	private final int DELAY = 200;

Instructions:
<ul><li>Left click: Select start tile</li>
<li>Ctrl + Click: Set obstacle</li>
<li>Right click: Select end tile</li>



Sample Runs:

![Sample Run 1](http://tunabytes.com/imgdump/astar1.png)

![Sample Run 2](http://tunabytes.com/imgdump/astar2.png)

![Sample Run 3](http://tunabytes.com/imgdump/astar3.png)

![Sample Run 4](http://tunabytes.com/imgdump/astar4.png)