package codes.slab.nonogram;

import java.util.Arrays;

public class Clues {

    Puzzle puzzle;
    int[][] X;
    int[][] Y;

    public Clues(Puzzle p) {
        this.puzzle = p;
        X = new int[Puzzle.SIZE][Puzzle.SIZE / 2];
        Y = new int[Puzzle.SIZE][Puzzle.SIZE / 2];

        boolean[][] grid = p.getGrid();
        boolean[][] invertedGrid = new boolean[Puzzle.SIZE][Puzzle.SIZE];
        //get inverted grid
        for(int i=0; i<grid.length; i++)
            for(int j=0; j<grid[i].length; j++)
                invertedGrid[j][i]=grid[i][j];

            X = getBlocks(grid);
            Y = getBlocks(invertedGrid);
    }

    private int[][] getBlocks(boolean[][] grid) {
        int[][] B = new int[Puzzle.SIZE][Puzzle.SIZE / 2];

        //X Axis
        for (int x = 0; x < Puzzle.SIZE; x++) {
            int[] blocks = new int[Puzzle.SIZE / 2];
            int currentBlock = 0;
            int blockStart = -1;
            for (int y = 0; y < Puzzle.SIZE + 1; y++) {
                //for cells on the end
                boolean value;
                if (y == Puzzle.SIZE)
                    value = false;
                else
                    value = grid[x][y];

                if (value) {
                    if (blockStart == -1) {
                        //begin block
                        blockStart = y;
                    }
                } else {
                    if (blockStart != -1) {
                        //save block
                        blocks[currentBlock] = y - blockStart;
                        currentBlock++;
                        blockStart = -1;
                    }
                }
            }
            B[x] = blocks;
        }

        return B;
    }

    public boolean matches(Clues input) {
        return Arrays.deepEquals(input.getCluesX(), this.getCluesX())
                && Arrays.deepEquals(input.getCluesY(), this.getCluesY());
    }

    public String toString() {
        String s = "\t\t";
        //x axis
        for (int x = 0; x < Puzzle.SIZE; x++) {
            s += x + ": ";
            for (int i = 0; i < Puzzle.SIZE / 2; i++) {
                s += X[x][i] + ", ";
            }
            s += "\t";
        }
        //y axis
        s += "\n";
        for (int x = 0; x < Puzzle.SIZE; x++) {
            s += x + ": ";
            for (int i = 0; i < Puzzle.SIZE / 2; i++) {
                s += Y[x][i] + ", ";
            }
            s += "\n";
        }

        //TODO y AXIS

        return s;
    }

    public int[][] getCluesX() {
        return X;
    }
    public int[][] getCluesY() {
        return Y;
    }

}
