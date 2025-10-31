import java.util.*;

public class TextMinroj {

    private static int size;
    private static int bombsCount;
    private static char[][] board;
    private static boolean[][] revealed;
    private static boolean[][] bombs;

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Välkommen till Textbaserat Minröj!");
        System.out.print("Ange storlek på spelplanen (t.ex. 6): ");
        size = readInt(3, 15);

        System.out.print("Ange antal bomber: ");
        bombsCount = readInt(1, size * size - 1);

        initBoard();
        placeBombs();

        boolean gameOver = false;
        while (!gameOver) {
            printBoard(false);
            System.out.print("\nVilken ruta vill du undersöka? (t.ex. b2): ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (!isValidInput(input)) {
                System.out.println("Felaktig inmatning. Försök igen!");
                continue;
            }

            int row = input.charAt(0) - 'a';
            int col = Character.getNumericValue(input.charAt(1)) - 1;

            if (bombs[row][col]) {
                printBoard(true);
                System.out.printf("💥 Pang! Game Over. Ruta %s har en bomb.\n", input);
                gameOver = true;
            } else {
                reveal(row, col);
                if (allSafeRevealed()) {
                    printBoard(true);
                    System.out.println("🎉 Grattis! Du vann! Alla säkra rutor är öppnade!");
                    gameOver = true;
                }
            }
        }
    }

    private static void initBoard() {
        board = new char[size][size];
        revealed = new boolean[size][size];
        bombs = new boolean[size][size];
        for (int r = 0; r < size; r++)
            Arrays.fill(board[r], ' ');
    }

    private static void placeBombs() {
        Random rand = new Random();
        int placed = 0;
        while (placed < bombsCount) {
            int r = rand.nextInt(size);
            int c = rand.nextInt(size);
            if (!bombs[r][c]) {
                bombs[r][c] = true;
                placed++;
            }
        }
    }

    private static void printBoard(boolean showBombs) {
        System.out.print("\n     ");
        for (int i = 1; i <= size; i++) System.out.printf("%2d ", i);
        System.out.println("\n   +" + "---".repeat(size) + "+");

        for (int r = 0; r < size; r++) {
            System.out.printf(" %c |", (char) ('a' + r));
            for (int c = 0; c < size; c++) {
                if (revealed[r][c]) {
                    int n = countAdjacent(r, c);
                    System.out.printf(" %d ", n);
                } else if (showBombs && bombs[r][c]) {
                    System.out.print(" O ");
                } else {
                    System.out.print("   ");
                }
            }
            System.out.println("|");
        }
        System.out.println("   +" + "---".repeat(size) + "+");
    }

    private static boolean isValidInput(String input) {
        if (input.length() < 2) return false;
        char row = input.charAt(0);
        char colChar = input.charAt(1);
        if (!Character.isLetter(row) || !Character.isDigit(colChar)) return false;
        int col = Character.getNumericValue(colChar) - 1;
        return row >= 'a' && row < 'a' + size && col >= 0 && col < size;
    }

    private static void reveal(int r, int c) {
        if (r < 0 || r >= size || c < 0 || c >= size) return;
        if (revealed[r][c]) return;
        revealed[r][c] = true;

        int adj = countAdjacent(r, c);
        if (adj == 0) {
            for (int dr = -1; dr <= 1; dr++)
                for (int dc = -1; dc <= 1; dc++)
                    if (dr != 0 || dc != 0)
                        reveal(r + dr, c + dc);
        }
    }

    private static int countAdjacent(int r, int c) {
        int count = 0;
        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                int nr = r + dr;
                int nc = c + dc;
                if (nr >= 0 && nr < size && nc >= 0 && nc < size && bombs[nr][nc]) {
                    count++;
                }
            }
        }
        return count;
    }

    private static boolean allSafeRevealed() {
        for (int r = 0; r < size; r++)
            for (int c = 0; c < size; c++)
                if (!bombs[r][c] && !revealed[r][c])
                    return false;
        return true;
    }

    private static int readInt(int min, int max) {
        while (true) {
            try {
                int val = Integer.parseInt(scanner.nextLine());
                if (val >= min && val <= max) return val;
            } catch (NumberFormatException ignored) {}
            System.out.print("Felaktig siffra, försök igen: ");
        }
    }
}

