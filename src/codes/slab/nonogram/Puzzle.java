package codes.slab.nonogram;

import java.io.*;

public class Puzzle {

    public static final int SIZE = 6;

    private Clues clues;
    private boolean[][] cells = new boolean[SIZE][SIZE];

    public Puzzle(boolean[][] cells) {
        this.cells = cells;
    }

    public Puzzle(String fileName) {

        try {
            File file = new File("src/codes/slab/nonogram/puzzles/" + fileName);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);

            for (int y = 0; y < Puzzle.SIZE; y++) {
                String line = reader.readLine();
                for (int x = 0; x < Puzzle.SIZE; x++) {
                    if (x < line.length() && line.charAt(x) == 'X') {
                        cells[x][y] = true;
                    } else
                        cells[x][y] = false;
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        clues = new Clues(this);

        System.out.println(this);
    }

    public boolean[][] getGrid() {
        clues = new Clues(this);
        return cells;
    }

    public String toString() {
        String s = "Puzzle:\n";
        for (int y = 0; y < Puzzle.SIZE; y++) {
            for (int x = 0; x < Puzzle.SIZE; x++) {
                if (cells[x][y])
                    s += "X";
                else
                    s += " ";
            }
            s += "\n";
        }

        s += "\n\nClues:\n" + this.clues;

        return s;
    }

    public boolean isSolved(boolean[][] input) {
        Clues inputClues = new Clues(new Puzzle(input));

        return clues.matches(inputClues);
    }

    public boolean isUnique() {
        int totalCombinations = (int) Math.pow(2, Puzzle.SIZE * Puzzle.SIZE);
        for (int i = 0; i < totalCombinations; i++) {

        }
    }

}
