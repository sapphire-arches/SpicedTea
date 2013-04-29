package tk.sirtwinkles.spicedtea.world.util;

import static tk.sirtwinkles.spicedtea.MathUtils.abs;

import java.util.LinkedList;
import java.util.PriorityQueue;

import tk.sirtwinkles.spicedtea.entities.Entity;
import tk.sirtwinkles.spicedtea.world.Level;
import tk.sirtwinkles.spicedtea.world.Point;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;

public final class Pather {
	static class PathNode implements Poolable, Comparable<PathNode> {
		public PathNode[] neighbors;
		public int distance;
		public int heuristic;
		int x, y;
		public boolean visited;

		@Override
		public void reset() {
			distance = -1;
			heuristic = 0;
			visited = false;
		}

		@Override
		public int compareTo(PathNode o) {
			return this.distance - o.distance;
		}
	}

	private static Pool<PathNode> pnodes = new Pool<Pather.PathNode>() {
		@Override
		protected PathNode newObject() {
			return new PathNode();
		}
	};
	private static PathNode[][] nodes;
	private static PriorityQueue<PathNode> queue = new PriorityQueue<PathNode>();

	/**
	 * Path find.
	 * 
	 * @param l
	 *            The level to path in.
	 * @param tr
	 *            A linked list to append to when returning.
	 * @param start
	 *            The start position.
	 * @param end
	 *            The end position
	 * @param ent
	 *            The entity to path for.
	 * @return null if new path. tr if tr was not null, a new linked list
	 *         otherwise.
	 */
	public static LinkedList<Point> path(Level l, LinkedList<Point> tr,
			Point start, Point end, Entity ent) {
		// Some basic sanity checking.
		if (!l.getTile(start.x, start.y).isPathable(ent, l)) {
			throw new IllegalArgumentException(
					"Unable to path for ent: Start blocked");
		}
		if (!l.getTile(start.x, start.y).isPathable(ent, l)) {
			throw new IllegalArgumentException(
					"Unable to path for ent: Goal unreachable");
		}

		if (nodes == null || nodes.length != l.getWidth()
				|| nodes[0].length != l.getHeight()) {
			nodes = new PathNode[l.getWidth()][l.getHeight()];
			System.gc();
			for (int x = 0; x < nodes.length; ++x) {
				for (int y = 0; y < nodes[x].length; ++y) {
					nodes[x][y] = pnodes.obtain();
					nodes[x][y].x = x;
					nodes[x][y].y = y;
				}
			}
			for (int x = 0; x < nodes.length; ++x) {
				for (int y = 0; y < nodes[x].length; ++y) {
					nodes[x][y].neighbors = new PathNode[4];
					int index = 0;
					if (x > 0) {
						nodes[x][y].neighbors[index++] = nodes[x - 1][y];
					}
					if (x + 1 < nodes.length) {
						nodes[x][y].neighbors[index++] = nodes[x + 1][y];
					}
					if (y > 0) {
						nodes[x][y].neighbors[index++] = nodes[x][y - 1];
					}
					if (y + 1 < nodes[0].length) {
						nodes[x][y].neighbors[index++] = nodes[x][y + 1];
					}
				}
			}
		}
		if (tr == null) {
			tr = new LinkedList<Point>();
		}
		for (int x = 0; x < nodes.length; ++x) {
			for (int y = 0; y < nodes[x].length; ++y) {
				nodes[x][y].distance = Integer.MAX_VALUE;
				nodes[x][y].visited = false;
			}
		}

		nodes[start.x][start.y].distance = 0;
		nodes[start.x][start.y].heuristic = manhattanDistance(start, end);
		queue.clear();
		queue.add(nodes[start.x][start.y]);
		while (!queue.isEmpty() && !nodes[end.x][end.y].visited) {
			PathNode n = queue.remove();
			if (!n.visited) {
				n.heuristic = manhattanDistance(n.x, n.y, end.x, end.y);
				for (PathNode nbr : n.neighbors) {
					if (l.getTile(nbr.x, nbr.y).isPathable(ent, l)) {
						if (nbr != null && !nbr.visited) {
							if (nbr.distance == -1
									|| n.distance + 1 < nbr.distance) {
								nbr.distance = n.distance + 1;
							}
						}
						if (nbr != null && !nbr.visited) {
							queue.add(nbr);
						}
					}
				}
				n.visited = true;
			}
		}

		if (!nodes[end.x][end.y].visited) {
			return null;
		}
		PathNode node = nodes[end.x][end.y];
		tr.clear();
		while (node.x != start.x || node.y != start.y) {
			tr.addFirst(Point.getPoint(node.x, node.y));
			PathNode minNbr = node;
			for (PathNode nbr : node.neighbors) {
				if (nbr != null)
				if (nbr != null && nbr.visited
						&& nbr.distance < minNbr.distance) {
					minNbr = nbr;
				}
			}
			node = minNbr;
		}
		return tr;
	}

	private static int manhattanDistance(Point p1, Point p2) {
		return manhattanDistance(p1.x, p1.y, p2.x, p2.y);
	}

	private static int manhattanDistance(int x1, int y1, int x2, int y2) {
		return abs(x1 - x2) + abs(y1 - y2);
	}

	// Never construct
	private Pather() {
	}
}
