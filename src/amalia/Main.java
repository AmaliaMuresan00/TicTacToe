//Muresan Amalia - Subgroup2
package amalia;

import java.util.Scanner;

class TicTacToe
{
    static class Move
    {
        int row, col;
    }

    //player = PC             Opponent = User (me)
    static char player = 'O', opponent = 'X';


    //it returns if there are moves left(true) and if not (false)
    static Boolean isMovesLeft(char[][] board)
    {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == ' ')
                    return true;
        return false;
    }

    //this evaluateas if there are any victories on any row,column or diagonal
    static int evaluate(char[][] b)
    {
        //row
        for (int row = 0; row < 3; row++)
        {
            if (b[row][0] == b[row][1] &&
                    b[row][1] == b[row][2])
            {
                if (b[row][0] == player)
                    return +10;
                else if (b[row][0] == opponent)
                    return -10;
            }
        }

        //column
        for (int col = 0; col < 3; col++)
        {
            if (b[0][col] == b[1][col] &&
                    b[1][col] == b[2][col])
            {
                if (b[0][col] == player)
                    return +10;

                else if (b[0][col] == opponent)
                    return -10;
            }
        }

        // diagonal
        if (b[0][0] == b[1][1] && b[1][1] == b[2][2])
        {
            if (b[0][0] == player)
                return +10;
            else if (b[0][0] == opponent)
                return -10;
        }

        if (b[0][2] == b[1][1] && b[1][1] == b[2][0])
        {
            if (b[0][2] == player)
                return +10;
            else if (b[0][2] == opponent)
                return -10;
        }
        //if no one wins
        return 0;
    }


    //minimax function
    static int minimax(char[][] board, int depth, Boolean isMax, int alpha, int beta)
    {

        int score = evaluate(board);

        //if maximizer wins
        if (score == 10)
            return score;

        //if minimizer wins
        if (score == -10)
            return score;

        //this calls the isMovesLeft() to see if there are any moves left and if there are not, it is consider draw.
        if (!isMovesLeft(board))
            return 0;

        //maximizers move
        if (isMax)
        {
            int best = -1000;

            for (int i = 0; i < 3; i++)
            {
                for (int j = 0; j < 3; j++)
                {
                    //checks if the place is empty
                    if (board[i][j]==' ')
                    {
                        //then it makes its move
                        board[i][j] = player;

                        //calls the function and gets the max value
                        best = Math.max(best, minimax(board, depth + 1, false, alpha, beta));
                        alpha = Math.max(alpha,best);

                        //undoes the move
                        board[i][j] = ' ';

                        //Alpha Beta Pruning
                        if (beta <= alpha)
                            break;
                    }
                }

                // Alpha Beta Pruning
                if (beta <= alpha)
                    break;
            }
            return best;
        }

        //minimizer's move
        else
        {
            int best = 1000;

            for (int i = 0; i < 3; i++)
            {
                for (int j = 0; j < 3; j++)
                {
                    //checks if the place is empty
                    if (board[i][j] == ' ')
                    {
                        //then it makes the move
                        board[i][j] = opponent;

                        //calls the function and gets the min value
                        best = Math.min(best, minimax(board, depth + 1, true, alpha, beta));
                        beta = Math.min(beta, best);


                        //undoes the move
                        board[i][j] = ' ';

                        // Alpha Beta Pruning
                        if (beta <= alpha)
                            break;
                    }
                }

                // Alpha Beta Pruning
                if (beta <= alpha)
                    break;
            }
            return best;
        }
    }

    //fins the best move for the PC
    static Move findBestMove(char[][] board)
    {
        int bestVal = -1000;
        Move bestMove = new Move();
        bestMove.row = -1;
        bestMove.col = -1;

        // Traverse all cells, evaluate minimax function
        // for all empty cells. And return the cell
        // with optimal value.

        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                if (board[i][j] == ' ')
                {
                    board[i][j] = player;
                    int moveVal = minimax(board, 0, false, -1000, 1000);

                    board[i][j] = ' ';

                    //updates the best value if the move value is greater
                    if (moveVal > bestVal)
                    {
                        bestMove.row = i;
                        bestMove.col = j;
                        bestVal = moveVal;
                    }
                }
            }
        }


        return bestMove;
    }

    //introduces the values from 1-9, and checks if the positions are free or exist.
    public static void intValues(char[][] board){
        boolean repeat;
        boolean out;
        do {
            repeat = false;
            out = false;
            Scanner input = new Scanner(System.in);
            System.out.print("Your turn to place! [1-9]: ");

            int position = input.nextInt();
            System.out.print("\n");


            switch (position) {
                case 1:
                    if(board[0][0] == ' ')
                        board[0][0] = 'X';
                    else
                        repeat = true;
                    break;
                case 2:
                    if(board[0][1] == ' ')
                        board[0][1] = 'X';
                    else
                        repeat = true;
                    break;
                case 3:
                    if(board[0][2] == ' ')
                        board[0][2] = 'X';
                    else
                        repeat = true;
                    break;
                case 4:
                    if(board[1][0] == ' ')
                        board[1][0] = 'X';
                    else
                        repeat = true;
                    break;
                case 5:
                    if(board[1][1] == ' ')
                        board[1][1] = 'X';
                    else
                        repeat = true;
                    break;
                case 6:
                    if(board[1][2] == ' ')
                        board[1][2] = 'X';
                    else
                        repeat = true;
                    break;
                case 7:
                    if(board[2][0] == ' ')
                        board[2][0] = 'X';
                    else
                        repeat = true;
                    break;
                case 8:
                    if(board[2][1] == ' ')
                        board[2][1] = 'X';
                    else
                        repeat = true;
                    break;
                case 9:
                    if(board[2][2] == ' ')
                        board[2][2] = 'X';
                    else
                        repeat = true;
                    break;
                default:
                    System.out.println("Position doesn't exist");
                    repeat = true;
                    out = true;
                    break;
            }
            if (repeat && !out)
                System.out.println("Position isn't free");
        }while(repeat);



    }

    //prints the board
    public static void printBoard(char[][] board){
        for(int i = 0; i<3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.print("\n");
            if(i<2) {
                System.out.print("----+---+----");
                System.out.print("\n");
            }
        }


    }

    //main
    public static void main(String[] args)
    {
        char[][] board = {{ ' ', ' ', ' ' },
                          { ' ', ' ', ' ' },
                          { ' ', ' ', ' ' }};


        while(isMovesLeft(board)) {
            //player s turn
            intValues(board);
            printBoard(board);

            //PC s turn
            if (isMovesLeft(board)) {

                Move bestMove = findBestMove(board);

                System.out.println("Your opponent moved!");
                board[bestMove.row][bestMove.col] = 'O';
                printBoard(board);
            }
            else
                System.out.println("It's a draw! :| ");

            if(evaluate(board) == 10){
                System.out.println("You lost! :( ");
                break;
            }


            if(evaluate(board) == -10){
                System.out.println("Congrats! You won! :) ");
                break;
            }

        }


    }

}



