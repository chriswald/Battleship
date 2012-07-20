/**
A simple artificial intelligence for playing battleship
@author Christopher J. Wald
@date May 12, 2012
*/

class compai {
    private int orig_hit = -1;
    private boolean guessing_ship = false;
    private boolean have_direction = false;
    private int last_guess = -1;
    private int llast_guess = -1;
    public  Grid mygrid = new Grid();

    public compai() {
	int loc = 0;
	int dir = 0;

	//TODO Places buildings off of the grid
	int[] sizes = {5, 4, 3, 3, 2};
	for (int BUILDING_SIZE : sizes) {
	    int[] locs = new int[BUILDING_SIZE];
	    do {
		loc = (int) (Math.random() * 100);
		dir = ((int) (Math.random() * 2) == 0 ? 1 : 10);
		for (int i = 0; i < BUILDING_SIZE; i ++)
		    locs[i] = loc + dir*i;
	    } while (!this.mygrid.add(locs));
	}
    }

    public Grid AI_Guess(Grid grid) {
	Grid tmp;
	if (guessing_ship) {
	    if (have_direction) {
		int next_spot = -1;
		if (grid.getGrid()[last_guess] == Grid.MISS) {
		    next_spot = orig_hit - (last_guess - llast_guess);
		    if (!isvalid(grid, next_spot)) {
			guessing_ship = false;
			have_direction = false;
			orig_hit = -1;
			tmp = guess_rand(grid);
		    } else {
			int val = grid.getGrid()[next_spot] + 2;
			if (val == Grid.MISS) {
			    guessing_ship = false;
			    have_direction = false;
			    orig_hit = -1;
			    tmp = update_grid(grid, next_spot, val);
			} else {
			    tmp = update_grid(grid, next_spot, val);
			    llast_guess = orig_hit;
			}
		    }
		} else {
		    next_spot = last_guess + (last_guess - llast_guess);
		    if (isvalid(grid, next_spot) && (next_spot/10 == last_guess/10 || next_spot%10 == last_guess%10)) {
			tmp = update_grid(grid, next_spot, grid.getGrid()[next_spot] + 2);
		    } else {
			next_spot = orig_hit - (last_guess - llast_guess);
			if (isvalid(grid, next_spot) && (next_spot/10 == orig_hit/10 || next_spot%10 == orig_hit%10)) {
			    tmp = update_grid(grid, next_spot, grid.getGrid()[next_spot] + 2);
			    llast_guess = orig_hit;
			} else {
			    guessing_ship = false;
			    have_direction = false;
			    orig_hit = -1;
			    tmp = guess_rand(grid);
			}
		    }
		}
	    } else {
		int next_spot = -1;
		next_spot = orig_hit + 10;
		if (isvalid(grid, next_spot) && (next_spot%10 == orig_hit%10)) {
		    int val = grid.getGrid()[next_spot] + 2;
		    if (val == Grid.HIT) {
			have_direction = true;
		    }
		    tmp = update_grid(grid, next_spot, val);
		    llast_guess = orig_hit;
		} else if (isvalid(grid, (next_spot = orig_hit + 1)) && (next_spot/10 == orig_hit/10)) {
		    int val = grid.getGrid()[next_spot] + 2;
		    if (val == Grid.HIT) {
			have_direction = true;
		    }
		    tmp = update_grid(grid, next_spot, val);
		    llast_guess = orig_hit;
		} else if (isvalid(grid, (next_spot = orig_hit - 10)) && (next_spot%10 == orig_hit%10)) {
		    int val = grid.getGrid()[next_spot] + 2;
		    if (val == Grid.HIT) {
			have_direction = true;
		    }
		    tmp = update_grid(grid, next_spot, val);
		    llast_guess = orig_hit;
		} else if (isvalid(grid, (next_spot = orig_hit - 1)) && (next_spot/10 == orig_hit/10)) {
		    int val = grid.getGrid()[next_spot] + 2;
		    if (val == Grid.HIT) {
			have_direction = true;
		    }
		    tmp = update_grid(grid, next_spot, val);
		    llast_guess = orig_hit;
		} else {
		    tmp = guess_rand(grid);
		    guessing_ship = false;
		    have_direction = false;
		    orig_hit = -1;
		}
	    }
	} else {
	    tmp = guess_rand(grid);
	}

	return tmp;
    }

    private boolean isvalid(Grid g, int spot) {
	return (spot < 100 && spot >= 0 && g.getGrid()[spot] < Grid.MISS);
    }	   

    private Grid guess_rand(Grid g) {
	int next_spot = -1;
	do {
	    next_spot = (int) (Math.random() * 100);
	} while (g.getGrid()[next_spot] >= Grid.MISS);
	return update_grid(g, next_spot, g.getGrid()[next_spot] + 2);
    }

    private Grid update_grid(Grid g, int spot, int val) {
	int[] locs = g.getGrid();
	locs[spot] = val;
	g.putLocations(locs);
	llast_guess = last_guess;
	last_guess = spot;
	if (val == Grid.HIT) {
	    guessing_ship = true;
	    if (orig_hit == -1)
		orig_hit = spot;
	}
	return g;
    }
}
