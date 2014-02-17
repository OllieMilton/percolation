
public class Percolation {

	class Site {
		boolean open = false;
		// x/y coordidate of root
		int[] root = new int[2];
		void root(int i, int j) {
			root[0] = i;
			root[1] = j;
		}
	}
	
	private Site[][] grid;
	private int[][] sz;
	private Site vtop = new Site();
	private Site vbottom = new Site();
	private int size;
	
	public Percolation(int N) {
		size = N;
		grid = new Site[N][N];
		sz = new int[N][N];
		// subtract one so the the beggining of the array point to 1,1
		for (int i=0; i<N-1; i++) {
			for (int j=0; j<N-1; j++) {
				grid[i][j] = new Site();
				grid[i][j].root(i,j);
				sz[i][j] = 1;
				if (i == 0) {
					// connect to vtop
				} else if (i == N-1) {
					// connect to vbottom 
				}
			}
		}
	}
	
	public void open(int i, int j) {
		// open site (row i, column j) if it is not already
		validate(i, j);
		Site s = grid[i][j]; 
		s.open = true;
		if (i+1 <= size && grid[i+1][j].open) {
			if (sz[i+1][j] < sz[s.root[0]][s.root[1]]) {
				sz[i+1][j] += sz[s.root[0]][s.root[1]];
				s.root = grid[i+1][j].root;
			}
		} else if (i+1 >= 1 && grid[i-1][j].open) {
			if (sz[i-1][j] < sz[s.root[0]][s.root[1]]) {
				sz[i-1][j] += sz[s.root[0]][s.root[1]];
				s.root = grid[i-1][j].root;
			}
		} else if (j-1 >= 1 && grid[i][j-1].open) {
			if (sz[i][j-1] < sz[s.root[0]][s.root[1]]) {
				sz[i][j-1] += sz[s.root[0]][s.root[1]];
				s.root = grid[i+1][j].root;
			}
		} else if (j+1 <= size && grid[i][j+1].open) {
			if (sz[i][j+1] < sz[s.root[0]][s.root[1]]) {
				sz[i][j+1] += sz[s.root[0]][s.root[1]];
				s.root = grid[i+1][j].root;
			}
		}
	}
	
	public boolean isOpen(int i, int j) {
		// is site (row i, column j) open?
		validate(i, j);
		return grid[i][j].open;
	}
	
	public boolean isFull(int i, int j) {
		// is site (row i, column j) full? Is connected to top?
		return false;
	}
	
	private void validate(int i, int j) {
		if (i < 1 || i > size || j < 1 || j > size) {
			throw new IndexOutOfBoundsException();
		}
	}
	
	public boolean percolates() {
		return false;
	}
	
	
	public static void main(String[] args) {
		Percolation p = new Percolation(10);
		p.open(1, 1);
		p.open(1, 3);
		p.open(1, 2);
	}
}
