
public class Knights {
    public static int[][] board; //объявляем массив доски
    public static int[][] moves; // Массив возможныхх ходов
    public static int maxMoves; // Максимальное количество шагов
    public static void main(String[] args) {
        System.out.println("Cтарт"); 
        moveThem(5);
    }
    public static void moveThem(int boardSize) {
        if (boardSize >= 0 && boardSize <=2){
            System.out.println("Доска не подходит для обхода конём");
            return ;
        }
        board = new int[boardSize][boardSize]; // объявляем массив доски
        maxMoves = board.length * board[0].length; // подсчет количества максимальных шагов
    //Как может ходить конь по доске
        moves = new int[][] { { 1, 2 }, { 2, 1 }, { -2, -1 }, { -1, -2 }, { -1, 2 }, { -2, 1 }, { -1, 2 }, { 1, -2 } };
        int count = 0; // Счетчик для счета количества вариантов обхода
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (findPath(i, j, 1)) {
                    System.out.println("Вариант обхода № " + (count+1));
                    printSol();
                    System.out.println("--".repeat(boardSize*2));
                    //System.out.printf("%4d","-");
                    board = new int[boardSize][boardSize]; //Если нашли варинат обхода доски, то создаем заново доску и пробуем еще раз обойти
                    count++;
                }
            }
        }
        if (count > 2) { // Проверка
            System.out.println("Обход закончен");
        } else {
            System.out.println("Эту доску невозможно обойти");
        }
    }
    public static boolean findPath(int row, int col, int moveNum) {
        board[row][col] = moveNum; // запоминаем ход
        if (moveNum >= maxMoves) { //Если ход больше максимального возможных ходов на доске, выходим
            return true;
        }
        //Проверяем возможность сходить перебирая доступные коню ходы.
        for (int i = 0; i < 8; i++) { 
            int newRow = row + moves[i][0];
            int newCol = col + moves[i][1];
            if (isPossibleMove(newRow, newCol) && findPath(newRow, newCol, moveNum + 1)) {
                return true;
            }
        }
        board[row][col] = 0;
        return false;
    }
    public static boolean isPossibleMove(int x, int y) { //Проверяем попадает ли ход коня в границы доски и был ли конь уже в этой клетке.
        return x >= 0 && x < board.length && y >= 0 && y < board.length && board[x][y] == 0;
    }

    public static void printSol() { //Выводим получившуюся доску
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                //System.out.print(board[i][j] + "\t");
                System.out.printf( "%4d", board[i][j]);
            }
        System.out.println();
        }
    }
}