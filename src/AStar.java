import java.awt.*;
import java.util.*;

import javax.swing.*;


public class AStar extends JFrame {

	private static final long serialVersionUID = 1L;
	private final static JFrame frame = new JFrame("A* Search Algorithm");
	
	public static class AStarPanel extends JPanel {
		
		private static final long serialVersionUID = 1L;
		private static LinkedList<Tile> path = new LinkedList<Tile>();
		private Tile[][] tiles;
		private Tile selectedTile;
		
		/**
		 * Creates the grid used for path calculations
		 */
		public AStarPanel() {
			tiles = new Tile[5][5];
			for(int y = 0; y < 5; y++) {
				for(int x = 0; x < 5; x++) {
					tiles[x][y] = new Tile(x * 60, y * 60, 60, 60);
				}
			}
		}
		
		/**
		 * Selects start and end tiles, and calculates the shortest path between them
		 */
		public void run() {
			// Tiles are referenced [x][y]
			// X origin is on the left and Y origin is at the top of the grid
			
			Tile startTile = tiles[1][3];	// CHANGE START TILE HERE
			startTile.setOutline(new Color(7, 157, 208));
			startTile.setFill(new Color(36, 173, 222));
			
			Tile endTile = tiles[3][1];		// CHANGE END TILE HERE
			endTile.setOutline(new Color(102, 153, 0));
			endTile.setFill(new Color(138, 189, 0));
			
			// Manually set initial values for start and end tiles
			// These values are explained more in the Tile class
			startTile.setG(0);
			startTile.setF(endTile);
			endTile.setH(0);
			
			findShortestPath(startTile, endTile);	// Calculate shortest path
			
			// Creates the path by tracing back from the end tile
			path.add(endTile);
			while(path.peek().getPrev() != null) {
				path.addFirst(path.peek().getPrev());
			}
			
			// Highlights the path
			for(Tile tile : path) {
				if(tile != startTile && tile != endTile) {
					tile.setOutline(new Color(255, 138, 0));
					tile.setFill(new Color(255, 174, 24));
				}
			}
			
			repaint();
			
			// Prints the shortest path to the console
//			System.out.println("Shortest path:\n");
//			for(Tile tile : path) {
//				tile.print();
//			}
		}
		
		private void findShortestPath(Tile startTile, Tile endTile) {
			// A priority queue keeps tile with lowest F value on top
			PriorityQueue<Tile> queue = new PriorityQueue<Tile>();
			queue.add(startTile);	// Begin by adding the start tile
			
			// Continue until end is found. 
			// Can result in infinite loop since the problem is NP-hard.
			while(!queue.contains(endTile)) {
				Tile best = queue.poll();	// Grab the best tile and remove from queue
				selectedTile = best;		// Highlights selected tile in GUI
				LinkedList<Tile> neighbors = getNeighbors(best);	// Find tile neighbors
				int stepCost = best.getG() + 1;	// Set the G cost of travelling to neighbor
				
				for(Tile neighbor : neighbors) {	// Iterate through all neighbors
					if(stepCost < neighbor.getG()) {	// If new G is lower...
						neighbor.setG(stepCost);	// Update new G cost
						neighbor.setPrev(best);		// Update previous tile to reflect best path
						neighbor.setF(endTile);		// Re-calculate F cost
						queue.add(neighbor);		// Add neighbor to queue
						try {
							repaint();				// Draws new values in GUI
							Thread.sleep(720);
						} catch(InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
				// Prints the state of the queue to the console
//				for(Tile tile : queue) {
//					tile.print();
//				}
//				System.out.println();
			}
			selectedTile = null;	// De-select last tile used
			return;
		}
		
		private LinkedList<Tile> getNeighbors(Tile tile) {	// Adds all neighbors to list
			LinkedList<Tile> neighbors = new LinkedList<Tile>();
			int x = tile.getX() / tile.getWidth();
			int y = tile.getY() / tile.getHeight();
			
			if(x - 1 >= 0) {	// These if-statements check for bounds of array
				neighbors.add(tiles[x - 1][y]);
			}
			if(y - 1 >= 0) {
				neighbors.add(tiles[x][y - 1]);
			}
			if(x + 1 < tiles[0].length) {
				neighbors.add(tiles[x + 1][y]);
			}
			if(y + 1 < tiles.length) {
				neighbors.add(tiles[x][y + 1]);
			}
			
			return neighbors;
		}

		@Override
		protected void paintComponent(final Graphics gr) {
			Graphics2D g = (Graphics2D)gr;
			g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
	                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);	// Fancy text is the best text
			
			for(Tile[] row : tiles) {	// Draws tiles in the GUI
					for(Tile tile : row) {
						if(tile == selectedTile) {
							tile.draw(g, true);
						} else {
							tile.draw(g, false);
						}
				}
			}
		}
	}
	
	public static void main(String[] args) {
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		AStarPanel panel = new AStarPanel();
		frame.add(panel);
		frame.setSize(317, 340);
		frame.setVisible(true);
		panel.run();
	}
	
	public static void close() {
		frame.dispose();
	}
}

