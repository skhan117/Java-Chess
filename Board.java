/*
    Board class models a chessboard, the positions of its pieces, and the
    possible moves its pieces may make. 
*/
import java.util.*;

public class Board {
    /*
    A two-dimensional integer array boardState
    depicts a particular state of the chess board where:
    -white pawns are represented by 1.
    -white bishops are represented by 2.
    -white knights are represented by 3.
    -white rooks are represented by 4.
    -the white queen is represented by 5.
    -the white king is represented by 6.
    -black pieces are represented by -n. e.g a black pawn
     is -1, a black bishop is -2, etc.
    */
    private int [][] boardState;   
    private ArrayList <Integer> deadPieces; 
    
    // Default constructor starts a new board.
    public Board() {

        boardState = new int[8][8];
        
        setupWhitePieces();
        setupBlackPieces();
        
        deadPieces = new ArrayList<Integer>();
    }
    
    // Getter for boardState
    public int[][] getBoard() {
        return boardState;
    }
    
    // Setter for boardState
    public void setBoard(int[][]a) {
        boardState = a;
    }
    
    // Getter for deadPieces
    public ArrayList<Integer> getDeadPieces() {
        return deadPieces;
    }
    
    // Place white pieces in starting positions.
    public void setupWhitePieces() {
        //Setup pawns
        for (int i = 0; i < 8; i++) {
            boardState[6][i] = 1;
        }
        // Setup other white pieces
        boardState[7][0] = 4;
        boardState[7][7] = 4;
        boardState[7][1] = 3;
        boardState[7][6] = 3;
        boardState[7][2] = 2;
        boardState[7][5] = 2;
        boardState[7][3] = 5;
        boardState[7][4] = 6;
    }
        
    // Place black pieces in starting positions.
    public void setupBlackPieces() {
        // Setup pawns
        for (int i = 0; i < 8; i++) {
            boardState[1][i] = -1;
        }
        boardState[0][0] = -4;
        boardState[0][7] = -4;
        boardState[0][1] = -3;
        boardState[0][6] = -3;
        boardState[0][2] = -2;
        boardState[0][5] = -2;
        boardState[0][3] = -5;
        boardState[0][4] = -6;        
    }

    // makeAWhitePawnMove takes four arguments:
    //  1) original row
    //  2) original column
    //  3) proposed row
    //  4) proposed column
    // and returns Boolean true or false if that move is valid.
    // If the move is valid, the pawn is moved to a new position,
    // and any killed pieces are removed from the board and added
    // to ArrayList deadPieces.
    public Boolean makeAWhitePawnMove (int originalRow, int originalColumn, 
            int proposedRow, int proposedColumn) {
        
        // If pawn isn't where command says it should be, return false
        if (boardState[originalRow][originalColumn] != 1) {
            System.out.println("\nPawn isn't there\n");
            return false;
        }
        
        int checkRow = originalRow;
        int checkColumn = originalColumn;
        
        // Make an 8x8 2D array representing pawn's valid moves
        boolean [][] validPawnMoves = new boolean[8][8];

        // Pawn can move one space forward as long as those spaces
        // are currently unoccupied
        try {
            if ((boardState[checkRow - 1][checkColumn]) == 0)
                validPawnMoves[checkRow - 1][checkColumn] = true;}
        catch(Exception e) {}
            
        // If pawn is in initial row it can move up two spaces
        if (originalRow == 6) {
            try {
                if (boardState[checkRow - 2][checkColumn] == 0) 
                    validPawnMoves[checkRow - 2][checkColumn] = true;}
            catch(Exception e) {}
        }
        // Pawn can attack an opponent up-left
        try {
            if ((boardState[checkRow - 1][checkColumn - 1]) < 0) 
                validPawnMoves[checkRow - 1][checkColumn - 1] = true;}
        catch (Exception e) {}
            
        // Pawn can attack an opponent up-right
        try {
            if ((boardState[checkRow - 1][checkColumn + 1]) < 0) 
                validPawnMoves[checkRow - 1][checkColumn + 1] = true;}
        catch (Exception e) {}
            
        /* For testing: Print potential pawn moves
        System.out.println();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (validPawnMoves[i][j] == true) {
                    System.out.print(validPawnMoves[i][j] + "  ");
                }
                else {
                    System.out.print(validPawnMoves[i][j] + " ");
                }
            }
            System.out.println();
        }
        */
        
        // If proposed pawn move isn't valid, return false
        if (validPawnMoves[proposedRow][proposedColumn] == false) {
            System.out.println("\nNot a valid pawn move\n");
            return false;
        }

        // If pawn's proposed position is empty, move it there
        if (boardState[proposedRow][proposedColumn] == 0) {
            boardState[originalRow][originalColumn] = 0;
            boardState[proposedRow][proposedColumn] = 1;
        }
        // If pawn's proposed position is occupied by opponent, take its place
        // and add defeated foe to deadPieces Array List
        else if (boardState[proposedRow][proposedColumn] < 0) {
            deadPieces.add(boardState[proposedRow][proposedColumn]);
            boardState[originalRow][originalColumn] = 0;
            boardState[proposedRow][proposedColumn] = 1;
        }
        
        // If pawn has reached row 0, then make it into a queen
        if (proposedRow == 0) {
            boardState[proposedRow][proposedColumn] = 5;
            System.out.println("\nPawn --> Queen\n");
        }
        
        System.out.println("\nPawn move successful\n");
        return true;
        }
    
    // makeAWhiteRookMove takes four arguments:
    //  1) original row
    //  2) original column
    //  3) proposed row
    //  4) proposed column
    // and returns Boolean true or false if that move is valid.
    // If the move is valid, the rook is moved to a new position,
    // and any killed pieces are removed from the board and added
    // to ArrayList deadPieces.
    public Boolean makeAWhiteRookMove (int originalRow, int originalColumn, 
            int proposedRow, int proposedColumn) {
                
        // If rook isn't where command says it should be, return false
        if (boardState[originalRow][originalColumn] != 4) {
            System.out.println("\nRook isn't there\n");
            return false;
        }
        
        int checkRow = originalRow;
        int checkColumn = originalColumn;
        
        // Make an 8x8 2D array
        boolean [][] validRookMoves = new boolean[8][8];
        
        // Check up from rook
        while (checkRow != 0) {
            checkRow--;
            if (boardState[checkRow][checkColumn] == 0) {
                validRookMoves[checkRow][checkColumn] = true;
            }
            else if (boardState[checkRow][checkColumn] > 0) {
                break;
            }
            else if (boardState[checkRow][checkColumn] < 0) {
                validRookMoves[checkRow][checkColumn] = true;
                break;
            }
        }
        
        // Check down from rook
        checkRow = originalRow;
        checkColumn = originalColumn;
        while (checkRow != 7) {
            checkRow++;
            if (boardState[checkRow][checkColumn] == 0) {
                validRookMoves[checkRow][checkColumn] = true;
            }
            else if (boardState[checkRow][checkColumn] > 0) {
                break;
            }
            else if (boardState[checkRow][checkColumn] < 0) {
                validRookMoves[checkRow][checkColumn] = true;
                break;
            }
        }
        
        // Check left from rook
        checkRow = originalRow;
        checkColumn = originalColumn;
        while (checkColumn != 0) {
            checkColumn--;
            if (boardState[checkRow][checkColumn] == 0) {
                validRookMoves[checkRow][checkColumn] = true;
            }
            else if (boardState[checkRow][checkColumn] > 0) {
                break;
            }
            else if (boardState[checkRow][checkColumn] < 0) {
                validRookMoves[checkRow][checkColumn] = true;
                break;
            }
        }
        
        // Check right from rook
        checkRow = originalRow;
        checkColumn = originalColumn;
        while (checkColumn != 7) {
            checkColumn++;
            if (boardState[checkRow][checkColumn] == 0) {
                validRookMoves[checkRow][checkColumn] = true;
            }
            else if (boardState[checkRow][checkColumn] > 0) {
                break;
            }
            else if (boardState[checkRow][checkColumn] < 0) {
                validRookMoves[checkRow][checkColumn] = true;
                break;
            }
        }
        
        /* For testing: Print potential rook moves
        System.out.println();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (validRookMoves[i][j] == true) {
                    System.out.print(validRookMoves[i][j] + "  ");
                }
                else {
                    System.out.print(validRookMoves[i][j] + " ");
                }
            }
            System.out.println();
        }
        */
        
        // if proposed move is not a valid rook move, return false
        if (validRookMoves[proposedRow][proposedColumn] == false) {
            System.out.println();
            System.out.println("Not a valid rook move\n");
            return false;
        }
        
        // If rook's proposed position is empty, move it there
        if (boardState[proposedRow][proposedColumn] == 0) {
            boardState[originalRow][originalColumn] = 0;
            boardState[proposedRow][proposedColumn] = 4;
        }
        // If rook's proposed position is occupied by opponent, take its place
        // and add defeated foe to deadPieces Array List
        else if (boardState[proposedRow][proposedColumn] < 0) {
            deadPieces.add(boardState[proposedRow][proposedColumn]);
            boardState[originalRow][originalColumn] = 0;
            boardState[proposedRow][proposedColumn] = 4;
        }
        
        System.out.println("\nRook's move was successful\n");
        return true;
        }

    // makeAWhiteKnightMove takes four arguments:
    //  1) original row
    //  2) original column
    //  3) proposed row
    //  4) proposed column
    // and returns Boolean true or false if that move is valid.
    // If the move is valid, the knight is moved to a new position,
    // and any killed pieces are removed from the board and added
    // to ArrayList deadPieces.
    public Boolean makeAWhiteKnightMove (int originalRow, int originalColumn, 
            int proposedRow, int proposedColumn) {
                 
        if (boardState[originalRow][originalColumn] != 3) {
            System.out.println("Knight isn't there");
            return false;
        }
        
        // The ideal way to model a knight's moves is to model each of its
        // potential jumps in try/catch blocks. Valid jumps are added to
        // validKnightMoves 2D boolean array; invalid jumps don't cause errors.
        boolean[][] validKnightMoves = new boolean[8][8];

        int checkRow;
        int checkColumn;

        // up-right
        checkRow = originalRow - 2;
        checkColumn = originalColumn + 1;
        try {
            if(!(boardState[checkRow][checkColumn] > 0))
                validKnightMoves[checkRow][checkColumn] = true;
        }
        catch(Exception e) {}
        
        // up-left
        checkRow = originalRow - 2;
        checkColumn = originalColumn - 1;
        try {
            if(!(boardState[checkRow][checkColumn] > 0))
                validKnightMoves[checkRow][checkColumn] = true;
        }
        catch(Exception e) {}
        
        // right-up
        checkRow = originalRow - 1;
        checkColumn = originalColumn + 2;
        try {
            if(!(boardState[checkRow][checkColumn] > 0))
                validKnightMoves[checkRow][checkColumn] = true;
        }
        catch(Exception e) {}

        // right-down
        checkRow = originalRow + 1;
        checkColumn = originalColumn + 2;
        try {
            if(!(boardState[checkRow][checkColumn] > 0))
                validKnightMoves[checkRow][checkColumn] = true;
        }
        catch(Exception e) {}


        // down-right
        checkRow = originalRow + 2;
        checkColumn = originalColumn + 1;
        try {
            if(!(boardState[checkRow][checkColumn] > 0))
                validKnightMoves[checkRow][checkColumn] = true;
        }
        catch(Exception e) {}

        // down-left
        checkRow = originalRow + 2;
        checkColumn = originalColumn - 1;
        try {
            if(!(boardState[checkRow][checkColumn] > 0))
                validKnightMoves[checkRow][checkColumn] = true;
        }
        catch(Exception e) {}
        
        // left-up
        checkRow = originalRow - 1;
        checkColumn = originalColumn - 2;
        try {
            if(!(boardState[checkRow][checkColumn] > 0))
                validKnightMoves[checkRow][checkColumn] = true;
        }
        catch(Exception e) {}

        // left-down
        checkRow = originalRow - 1;
        checkColumn = originalColumn - 2;
        try {
            if(!(boardState[checkRow][checkColumn] > 0))
                validKnightMoves[checkRow][checkColumn] = true;
        }
        catch(Exception e) {}
        
        // For testing: Print potential knight moves
        System.out.println();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (validKnightMoves[i][j] == true) {
                    System.out.print(validKnightMoves[i][j] + "  ");
                }
                else {
                    System.out.print(validKnightMoves[i][j] + " ");
                }
            }
            System.out.println();
        }
        
        // if proposed move is not a valid knight move, return false
        if (validKnightMoves[proposedRow][proposedColumn] == false) {
            System.out.println();
            System.out.println("Not a valid knight move\n");
            return false;
        }
        
        // If knight's proposed position is empty, move it there
        if (boardState[proposedRow][proposedColumn] == 0) {
            boardState[originalRow][originalColumn] = 0;
            boardState[proposedRow][proposedColumn] = 3;
        }
        // If rook's proposed position is occupied by opponent, take its place
        // and add defeated foe to deadPieces Array List
        else if (boardState[proposedRow][proposedColumn] < 0) {
            deadPieces.add(boardState[proposedRow][proposedColumn]);
            boardState[originalRow][originalColumn] = 0;
            boardState[proposedRow][proposedColumn] = 3;
        }
        
        
        
        System.out.println("Knight move was a success");
        return true;
        }

    // makeAWhiteBishopMove takes four arguments:
    //  1) original row
    //  2) original column
    //  3) proposed row
    //  4) proposed column
    // and returns Boolean true or false if that move is valid.
    // If the move is valid, the knight is moved to a new position,
    // and any killed pieces are removed from the board and added
    // to ArrayList deadPieces. 
    public Boolean makeAWhiteBishopMove (int originalRow, int originalColumn, 
            int proposedRow, int proposedColumn) {
                 
        // if bishop isn't where command says it should be, return false
        if (boardState[originalRow][originalColumn] != 2) {
            System.out.println("\nBishop isn't there\n");
            return false;
        }
        
        int checkRow = originalRow;
        int checkColumn = originalColumn;
        
        // Make an 8x8 2D array
        boolean [][] validBishopMoves = new boolean[8][8];
        
        // Check up-right from bishop
        while ((checkRow != 0) && (checkColumn != 7)) {
            checkRow--;
            checkColumn++;
            if (boardState[checkRow][checkColumn] == 0) {
                validBishopMoves[checkRow][checkColumn] = true;
            }
            else if (boardState[checkRow][checkColumn] > 0) {
                break;
            }
            else if (boardState[checkRow][checkColumn] < 0) {
                validBishopMoves[checkRow][checkColumn] = true;
                break;
            }
        }
                
        // Check down-right from bishop
        checkRow = originalRow;
        checkColumn = originalColumn;
        while ((checkRow != 7) && (checkColumn != 7)) {
            checkRow++;
            checkColumn++;
            if (boardState[checkRow][checkColumn] == 0) {
                validBishopMoves[checkRow][checkColumn] = true;
            }
            else if (boardState[checkRow][checkColumn] > 0) {
                break;
            }
            else if (boardState[checkRow][checkColumn] < 0) {
                validBishopMoves[checkRow][checkColumn] = true;
                break;
            }
        }
        
        // Check down-left from bishop
        checkRow = originalRow;
        checkColumn = originalColumn;
        while ((checkRow != 7) && (checkColumn != 0)) {
            checkRow++;
            checkColumn--;
            if (boardState[checkRow][checkColumn] == 0) {
                validBishopMoves[checkRow][checkColumn] = true;
            }
            else if (boardState[checkRow][checkColumn] > 0) {
                break;
            }
            else if (boardState[checkRow][checkColumn] < 0) {
                validBishopMoves[checkRow][checkColumn] = true;
                break;
            }
        }
        
        // Check up-left from bishop
        checkRow = originalRow;
        checkColumn = originalColumn;
        while ((checkRow != 0) && (checkColumn != 0)) {
            checkRow--;
            checkColumn--;
            if (boardState[checkRow][checkColumn] == 0) {
                validBishopMoves[checkRow][checkColumn] = true;
            }
            else if (boardState[checkRow][checkColumn] > 0) {
                break;
            }
            else if (boardState[checkRow][checkColumn] < 0) {
                validBishopMoves[checkRow][checkColumn] = true;
                break;
            }
        }
        
        /* For testing: Print potential bishop moves
        System.out.println("Bishop moves");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (validBishopMoves[i][j] == true) {
                    System.out.print(validBishopMoves[i][j] + "  ");
                }
                else {
                    System.out.print(validBishopMoves[i][j] + " ");
                }
            }
            System.out.println();
        }
        */
        
        // if proposed move is not a valid bishop move, return false
        if (validBishopMoves[proposedRow][proposedColumn] == false) {
            System.out.println();
            System.out.println("Not a valid bishop move\n");
            return false;
        }
        
        // If bishop's proposed position is empty, move it there
        if (boardState[proposedRow][proposedColumn] == 0) {
            boardState[originalRow][originalColumn] = 0;
            boardState[proposedRow][proposedColumn] = 2;
        }
        // If bishop's proposed position is occupied by opponent, take its place
        // and add defeated foe to deadPieces Array List
        else if (boardState[proposedRow][proposedColumn] < 0) {
            deadPieces.add(boardState[proposedRow][proposedColumn]);
            boardState[originalRow][originalColumn] = 0;
            boardState[proposedRow][proposedColumn] = 2;
        }

        System.out.println("Bishop move was successful");
        return true;
        }

    // makeAWhiteQueenMove takes four arguments:
    //  1) original row
    //  2) original column
    //  3) proposed row
    //  4) proposed column
    // and returns Boolean true or false if that move is valid.
    // If the move is valid, the queen is moved to a new position,
    // and any killed pieces are removed from the board and added
    // to ArrayList deadPieces. 
    public Boolean makeAWhiteQueenMove (int originalRow, int originalColumn, 
            int proposedRow, int proposedColumn) {
                 
        if (boardState[originalRow][originalColumn] != 5) {
            System.out.println("\nQueen isn't there\n");
            return false;
        }
        
        // Make an 8x8 2D array
        boolean [][] validQueenMoves = new boolean[8][8];
        
        int checkRow = originalRow;
        int checkColumn = originalColumn;
        
        // Check up from queen
        while (checkRow != 0) {
            checkRow--;
            if (boardState[checkRow][checkColumn] == 0) {
                validQueenMoves[checkRow][checkColumn] = true;
            }
            else if (boardState[checkRow][checkColumn] > 0) {
                break;
            }
            else if (boardState[checkRow][checkColumn] < 0) {
                validQueenMoves[checkRow][checkColumn] = true;
                break;
            }
        }
        
        // Check down from queen
        checkRow = originalRow;
        checkColumn = originalColumn;
        while (checkRow != 7) {
            checkRow++;
            if (boardState[checkRow][checkColumn] == 0) {
                validQueenMoves[checkRow][checkColumn] = true;
            }
            else if (boardState[checkRow][checkColumn] > 0) {
                break;
            }
            else if (boardState[checkRow][checkColumn] < 0) {
                validQueenMoves[checkRow][checkColumn] = true;
                break;
            }
        }
        
        // Check left from queen
        checkRow = originalRow;
        checkColumn = originalColumn;
        while (checkColumn != 0) {
            checkColumn--;
            if (boardState[checkRow][checkColumn] == 0) {
                validQueenMoves[checkRow][checkColumn] = true;
            }
            else if (boardState[checkRow][checkColumn] > 0) {
                break;
            }
            else if (boardState[checkRow][checkColumn] < 0) {
                validQueenMoves[checkRow][checkColumn] = true;
                break;
            }
        }
        
        // Check right from queen
        checkRow = originalRow;
        checkColumn = originalColumn;
        while (checkColumn != 7) {
            checkColumn++;
            if (boardState[checkRow][checkColumn] == 0) {
                validQueenMoves[checkRow][checkColumn] = true;
            }
            else if (boardState[checkRow][checkColumn] > 0) {
                break;
            }
            else if (boardState[checkRow][checkColumn] < 0) {
                validQueenMoves[checkRow][checkColumn] = true;
                break;
            }
        }
        
        checkRow = originalRow;
        checkColumn = originalColumn;
        // Check up-right from queen
        while ((checkRow != 0) && (checkColumn != 7)) {
            checkRow--;
            checkColumn++;
            if (boardState[checkRow][checkColumn] == 0) {
                validQueenMoves[checkRow][checkColumn] = true;
            }
            else if (boardState[checkRow][checkColumn] > 0) {
                break;
            }
            else if (boardState[checkRow][checkColumn] < 0) {
                validQueenMoves[checkRow][checkColumn] = true;
                break;
            }
        }
                
        // Check down-right from queen
        checkRow = originalRow;
        checkColumn = originalColumn;
        while ((checkRow != 7) && (checkColumn != 7)) {
            checkRow++;
            checkColumn++;
            if (boardState[checkRow][checkColumn] == 0) {
                validQueenMoves[checkRow][checkColumn] = true;
            }
            else if (boardState[checkRow][checkColumn] > 0) {
                break;
            }
            else if (boardState[checkRow][checkColumn] < 0) {
                validQueenMoves[checkRow][checkColumn] = true;
                break;
            }
        }
        
        // Check down-left from queen
        checkRow = originalRow;
        checkColumn = originalColumn;
        while ((checkRow != 7) && (checkColumn != 0)) {
            checkRow++;
            checkColumn--;
            if (boardState[checkRow][checkColumn] == 0) {
                validQueenMoves[checkRow][checkColumn] = true;
            }
            else if (boardState[checkRow][checkColumn] > 0) {
                break;
            }
            else if (boardState[checkRow][checkColumn] < 0) {
                validQueenMoves[checkRow][checkColumn] = true;
                break;
            }
        }
        
        // Check up-left from queen
        checkRow = originalRow;
        checkColumn = originalColumn;
        while ((checkRow != 0) && (checkColumn != 0)) {
            checkRow--;
            checkColumn--;
            if (boardState[checkRow][checkColumn] == 0) {
                validQueenMoves[checkRow][checkColumn] = true;
            }
            else if (boardState[checkRow][checkColumn] > 0) {
                break;
            }
            else if (boardState[checkRow][checkColumn] < 0) {
                validQueenMoves[checkRow][checkColumn] = true;
                break;
            }
        }
        
        // For testing: Print potential queen moves
        System.out.println("Queen moves");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (validQueenMoves[i][j] == true) {
                    System.out.print(validQueenMoves[i][j] + "  ");
                }
                else {
                    System.out.print(validQueenMoves[i][j] + " ");
                }
            }
            System.out.println();
        }
        
        // if proposed move is not a valid queen move, return false
        if (validQueenMoves[proposedRow][proposedColumn] == false) {
            System.out.println();
            System.out.println("Not a valid queen move\n");
            return false;
        }
        
        // If queen's proposed position is empty, move it there
        if (boardState[proposedRow][proposedColumn] == 0) {
            boardState[originalRow][originalColumn] = 0;
            boardState[proposedRow][proposedColumn] = 5;
        }
        // If queen's proposed position is occupied by opponent, take its place
        // and add defeated foe to deadPieces Array List
        else if (boardState[proposedRow][proposedColumn] < 0) {
            deadPieces.add(boardState[proposedRow][proposedColumn]);
            boardState[originalRow][originalColumn] = 0;
            boardState[proposedRow][proposedColumn] = 5;
        }
        
        System.out.println("Queen move was successful");
        return true;
        }
    
    // makeAWhiteKingMove takes four arguments:
    //  1) original row
    //  2) original column
    //  3) proposed row
    //  4) proposed column
    // and returns Boolean true or false if that move is valid.
    // If the move is valid, the king is moved to a new position,
    // and any killed pieces are removed from the board and added
    // to ArrayList deadPieces. 
    public Boolean makeAWhiteKingMove (int originalRow, int originalColumn, 
            int proposedRow, int proposedColumn) {
        
        // if king isn't where command says it should be, return false
        if (boardState[originalRow][originalColumn] != 6) {
            System.out.println("\nKing isn't there\n");
            return false;
        }
        
        int checkRow;
        int checkColumn;
        
        // Make an 8x8 2D array for possible king moves
        // Unlike the other pieces, this is an integer array
        // 0 indicates that the king cannot move there
        // 1 indicates that the king can move there
        // -1 indicates that if the king moved there, it would be put in check
        int [][] validKingMoves = new int[8][8];
        
        // check up from king
        checkRow = originalRow;
        checkColumn = originalColumn;    
        try {
            if ((boardState[checkRow - 1][checkColumn]) == 0)
                validKingMoves[checkRow - 1][checkColumn] = 1;}
        catch(Exception e) {}
        
        // check up-right from king
        checkRow = originalRow;
        checkColumn = originalColumn;    
        try {
            if ((boardState[checkRow - 1][checkColumn + 1]) == 0)
                validKingMoves[checkRow - 1][checkColumn + 1] = 1;}
        catch(Exception e) {}
        
        // check up-left from king
        checkRow = originalRow;
        checkColumn = originalColumn;    
        try {
            if ((boardState[checkRow - 1][checkColumn - 1]) == 0)
                validKingMoves[checkRow - 1][checkColumn - 1] = 1;}
        catch(Exception e) {}
        
        // check left from king
        checkRow = originalRow;
        checkColumn = originalColumn;    
        try {
            if ((boardState[checkRow][checkColumn - 1]) == 0)
                validKingMoves[checkRow][checkColumn - 1] = 1;}
        catch(Exception e) {}
        
        // check left-down from king
        checkRow = originalRow;
        checkColumn = originalColumn;    
        try {
            if ((boardState[checkRow + 1][checkColumn - 1]) == 0)
                validKingMoves[checkRow + 1][checkColumn - 1] = 1;}
        catch(Exception e) {}
        
        // check down from king
        checkRow = originalRow;
        checkColumn = originalColumn;    
        try {
            if ((boardState[checkRow + 1][checkColumn]) == 0)
                validKingMoves[checkRow + 1][checkColumn] = 1;}
        catch(Exception e) {}
        
        // check down-right from king
        checkRow = originalRow;
        checkColumn = originalColumn;    
        try {
            if ((boardState[checkRow + 1][checkColumn + 1]) == 0)
                validKingMoves[checkRow + 1][checkColumn + 1] = 1;}
        catch(Exception e) {}
        
        // check right from king
        checkRow = originalRow;
        checkColumn = originalColumn;    
        try {
            if ((boardState[checkRow][checkColumn + 1]) == 0)
                validKingMoves[checkRow][checkColumn + 1] = 1;}
        catch(Exception e) {}
                
        /*
        For testing: Print potential king moves
        System.out.println("Valid king moves");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (validKingMoves[i][j] == 1) {
                    System.out.print(validKingMoves[i][j] + " ");
                }
                else {
                    System.out.print(validKingMoves[i][j] + " ");
                }
            }
            System.out.println();
        }
        */
        
        
        // FIXME: Need to model king's potential moves into check,
        // and println "Cannot move King into check" it tries to do that 
        
        // Step 1: Copy all black pieces to 2d array blackPieces
        int[][] blackPieces = new int[8][8];
        int[][] blackChecking = new int[8][8];
        
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (boardState[i][j] < 0) {
                    blackPieces[i][j] = boardState[i][j];
                }
            }
        }
                
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                // Step 2: Model all positions black pawns check
                if (blackPieces[i][j] == -1) {
                    try {
                        blackChecking[i + 1][j - 1] = -1;}
                    catch(Exception e) {};
                    try {
                        blackChecking[i + 1][j + 1] = -1;}
                    catch(Exception e){};
                }
                
                // Step 3: Model all positions that black bishops check
                if (blackPieces[i][j] == -2) {
                    int bishopRow = i;
                    int bishopColumn = j;
                    int checkThisRow;
                    int checkThisColumn;
                    
                    // check up-right from black bishop
                    checkThisRow = bishopRow;
                    checkThisColumn = bishopColumn;
                    while ((checkThisRow >= 0) && (checkThisColumn) <= 7) {
                        checkThisRow--;
                        checkThisColumn++;
                        try {
                            if (boardState[checkThisRow][checkThisColumn] == 0) {
                                blackChecking[checkThisRow][checkThisColumn] = -1;
                            }
                            else if (boardState[checkThisRow][checkThisColumn] > 0) {
                                blackChecking[checkThisRow][checkThisColumn] = -1;
                                break;
                            }
                            else {
                                break;
                            }
                        }
                        catch (Exception e) {}
                    }
                    
                    // check down-right from black bishop
                    checkThisRow = bishopRow;
                    checkThisColumn = bishopColumn;
                    while ((checkThisRow <= 7) && (checkThisColumn) <= 7) {
                        checkThisRow++;
                        checkThisColumn++;
                        try {
                            if (boardState[checkThisRow][checkThisColumn] == 0) {
                                blackChecking[checkThisRow][checkThisColumn] = -1;
                            }
                            else if (boardState[checkThisRow][checkThisColumn] > 0) {
                                blackChecking[checkThisRow][checkThisColumn] = -1;
                                break;
                            }
                            else {
                                break;
                            }
                        }
                        catch (Exception e) {}
                    }
                    
                    // check down-left from black bishop
                    checkThisRow = bishopRow;
                    checkThisColumn = bishopColumn;
                    while ((checkThisRow <= 7) && (checkThisColumn) >= 0) {
                        checkThisRow++;
                        checkThisColumn--;
                        try {
                            if (boardState[checkThisRow][checkThisColumn] == 0) {
                                blackChecking[checkThisRow][checkThisColumn] = -1;
                            }
                            else if (boardState[checkThisRow][checkThisColumn] > 0) {
                                blackChecking[checkThisRow][checkThisColumn] = -1;
                                break;
                            }
                            else {
                                break;
                            }
                        }
                        catch (Exception e) {}
                    }

                    // check up-left from black bishop
                    checkThisRow = bishopRow;
                    checkThisColumn = bishopColumn;
                    while ((checkThisRow >= 0) && (checkThisColumn) >= 0) {
                        checkThisRow--;
                        checkThisColumn--;
                        try {
                            if (boardState[checkThisRow][checkThisColumn] == 0) {
                                blackChecking[checkThisRow][checkThisColumn] = -1;
                            }
                            else if (boardState[checkThisRow][checkThisColumn] > 0) {
                                blackChecking[checkThisRow][checkThisColumn] = -1;
                                break;
                            }
                            else {
                                break;
                            }
                        }
                        catch (Exception e) {}
                    }                    
                } 
                
                // Step 4: Model all positions that black rook checks
                if (blackPieces[i][j] == -4) {
                    int rookRow = i;
                    int rookColumn = j;
                    int checkThisRow;
                    int checkThisColumn;
                    
                    // check up from black rook
                    checkThisRow = rookRow;
                    checkThisColumn = rookColumn;
                    while (checkThisRow >= 0) {
                        checkThisRow--;
                        try {
                            if (boardState[checkThisRow][checkThisColumn] == 0) {
                                blackChecking[checkThisRow][checkThisColumn] = -1;
                            }
                            else if (boardState[checkThisRow][checkThisColumn] > 0) {
                                blackChecking[checkThisRow][checkThisColumn] = -1;
                                break;
                            }
                            else {
                                break;
                            }
                        }
                        catch (Exception e) {}
                    }
                    
                    // check right from black rook
                    checkThisRow = rookRow;
                    checkThisColumn = rookColumn;
                    while (checkThisColumn <= 7) {
                        checkThisColumn++;
                        try {
                            if (boardState[checkThisRow][checkThisColumn] == 0) {
                                blackChecking[checkThisRow][checkThisColumn] = -1;
                            }
                            else if (boardState[checkThisRow][checkThisColumn] > 0) {
                                blackChecking[checkThisRow][checkThisColumn] = -1;
                                break;
                            }
                            else {
                                break;
                            }
                        }
                        catch (Exception e) {}
                    }
                    
                    // check down from black rook
                    checkThisRow = rookRow;
                    checkThisColumn = rookColumn;
                    while (checkThisRow <= 7) {
                        checkThisRow++;
                        try {
                            if (boardState[checkThisRow][checkThisColumn] == 0) {
                                blackChecking[checkThisRow][checkThisColumn] = -1;
                            }
                            else if (boardState[checkThisRow][checkThisColumn] > 0) {
                                blackChecking[checkThisRow][checkThisColumn] = -1;
                                break;
                            }
                            else {
                                break;
                            }
                        }
                        catch (Exception e) {}
                    }

                    // check left from black rook
                    checkThisRow = rookRow;
                    checkThisColumn = rookColumn;
                    while (checkThisColumn >= 0) {
                        checkThisColumn--;
                        try {
                            if (boardState[checkThisRow][checkThisColumn] == 0) {
                                blackChecking[checkThisRow][checkThisColumn] = -1;
                            }
                            else if (boardState[checkThisRow][checkThisColumn] > 0) {
                                blackChecking[checkThisRow][checkThisColumn] = -1;
                                break;
                            }
                            else {
                                break;
                            }
                        }
                        catch (Exception e) {}
                    }                    
                }

                // Step 5: Model all positions that black knights check
                if (blackPieces[i][j] == -3) {                   
                    int checkThisRow = i;
                    int checkThisColumn = j;
                    
                    // check up-right
                    try {
                        if (boardState[checkThisRow - 2][checkThisColumn + 1] >= 0) {
                            blackChecking[checkThisRow - 2][checkThisColumn + 1] = -1;
                        }
                    }
                    catch (Exception e) {};
                    // check up-left
                    try {
                        if (boardState[checkThisRow - 2][checkThisColumn - 1] >= 0) {
                            blackChecking[checkThisRow - 2][checkThisColumn -1] = -1;
                        }
                    }
                    catch (Exception e) {};
                    // check right-up
                    try {
                        if (boardState[checkThisRow - 1][checkThisColumn + 2] >= 0) {
                            blackChecking[checkThisRow - 1][checkThisColumn + 2] = -1;
                        }
                    }
                    catch (Exception e) {};
                    // check right-down
                    try {
                        if (boardState[checkThisRow + 1][checkThisColumn + 2] >= 0) {
                            blackChecking[checkThisRow + 1][checkThisColumn + 2] = -1;
                        }
                    }
                    catch (Exception e) {};
                    // check down-right
                    try {
                        if (boardState[checkThisRow + 2][checkThisColumn + 1] >= 0) {
                            blackChecking[checkThisRow + 2][checkThisColumn + 1] = -1;
                        }
                    }
                    catch (Exception e) {};
                    // check down-left
                    try {
                        if (boardState[checkThisRow + 2][checkThisColumn - 1] >= 0) {
                            blackChecking[checkThisRow + 2][checkThisColumn - 1] = -1;
                        }
                    }
                    catch (Exception e) {};
                    // check left-down
                    try {
                        if (boardState[checkThisRow + 1][checkThisColumn - 2] >= 0) {
                            blackChecking[checkThisRow + 1][checkThisColumn -2] = -1;
                        }
                    }
                    catch (Exception e) {};
                    // check left-up
                    try {
                        if (boardState[checkThisRow - 1][checkThisColumn - 2] >= 0) {
                            blackChecking[checkThisRow - 1][checkThisColumn - 2] = -1;
                        }
                    }
                    catch (Exception e) {};                    
                } // close black knight checking branch
                
                // Step 6: Model all positions that black king checks
                if (blackPieces[i][j] == -6) {
                    int checkThisRow = i;
                    int checkThisColumn = j;
                    
                    // check up from king
                    try {
                        if (boardState[checkThisRow - 1][checkThisColumn] >= 0) {
                            blackChecking[checkThisRow - 1][checkThisColumn] = -1;
                        }
                    }
                    catch(Exception e) {};
                    // check up-right from king
                    try {
                        if (boardState[checkThisRow - 1][checkThisColumn + 1] >= 0) {
                            blackChecking[checkThisRow - 1][checkThisColumn + 1] = -1;
                        }
                    }
                    catch(Exception e) {};
                    // check right from king
                    try {
                        if (boardState[checkThisRow][checkThisColumn + 1] >= 0) {
                            blackChecking[checkThisRow][checkThisColumn + 1] = -1;
                        }
                    }
                    catch(Exception e) {};
                    // check down-right from king
                    try {
                        if (boardState[checkThisRow + 1][checkThisColumn + 1] >= 0) {
                            blackChecking[checkThisRow + 1][checkThisColumn + 1] = -1;
                        }
                    }
                    catch(Exception e) {};
                    // check down from king
                    try {
                        if (boardState[checkThisRow + 1][checkThisColumn] >= 0) {
                            blackChecking[checkThisRow + 1][checkThisColumn] = -1;
                        }
                    }
                    catch(Exception e) {};
                    // check down-left from king
                    try {
                        if (boardState[checkThisRow + 1][checkThisColumn - 1] >= 0) {
                            blackChecking[checkThisRow + 1][checkThisColumn - 1] = -1;
                        }
                    }
                    catch(Exception e) {};
                    // check left from king
                    try {
                        if (boardState[checkThisRow][checkThisColumn - 1] >= 0) {
                            blackChecking[checkThisRow][checkThisColumn - 1] = -1;
                        }
                    }
                    catch(Exception e) {};
                    // check left-up from king
                    try {
                        if (boardState[checkThisRow - 1][checkThisColumn - 1] >= 0) {
                            blackChecking[checkThisRow - 1][checkThisColumn - 1] = -1;
                        }
                    }
                    catch(Exception e) {};    
                }
                
                // Step 7: Model all positions that black queen checks
                if (blackPieces[i][j] == -5) {
                    int queenRow = i;
                    int queenColumn = j;
                    
                    // check up-right from black queen
                    int checkThisRow = queenRow;
                    int checkThisColumn = queenColumn;
                    while ((checkThisRow >= 0) && (checkThisColumn) <= 7) {
                        checkThisRow--;
                        checkThisColumn++;
                        try {
                            if (boardState[checkThisRow][checkThisColumn] == 0) {
                                blackChecking[checkThisRow][checkThisColumn] = -1;
                            }
                            else if (boardState[checkThisRow][checkThisColumn] > 0) {
                                blackChecking[checkThisRow][checkThisColumn] = -1;
                                break;
                            }
                            else {
                                break;
                            }
                        }
                        catch (Exception e) {}
                    }
                    
                    // check down-right from black queen
                    checkThisRow = queenRow;
                    checkThisColumn = queenColumn;
                    while ((checkThisRow <= 7) && (checkThisColumn) <= 7) {
                        checkThisRow++;
                        checkThisColumn++;
                        try {
                            if (boardState[checkThisRow][checkThisColumn] == 0) {
                                blackChecking[checkThisRow][checkThisColumn] = -1;
                            }
                            else if (boardState[checkThisRow][checkThisColumn] > 0) {
                                blackChecking[checkThisRow][checkThisColumn] = -1;
                                break;
                            }
                            else {
                                break;
                            }
                        }
                        catch (Exception e) {}
                    }
                    
                    // check down-left from black queen
                    checkThisRow = queenRow;
                    checkThisColumn = queenColumn;
                    while ((checkThisRow <= 7) && (checkThisColumn) >= 0) {
                        checkThisRow++;
                        checkThisColumn--;
                        try {
                            if (boardState[checkThisRow][checkThisColumn] == 0) {
                                blackChecking[checkThisRow][checkThisColumn] = -1;
                            }
                            else if (boardState[checkThisRow][checkThisColumn] > 0) {
                                blackChecking[checkThisRow][checkThisColumn] = -1;
                                break;
                            }
                            else {
                                break;
                            }
                        }
                        catch (Exception e) {}
                    }

                    // check up-left from black queen
                    checkThisRow = queenRow;
                    checkThisColumn = queenColumn;
                    while ((checkThisRow >= 0) && (checkThisColumn) >= 0) {
                        checkThisRow--;
                        checkThisColumn--;
                        try {
                            if (boardState[checkThisRow][checkThisColumn] == 0) {
                                blackChecking[checkThisRow][checkThisColumn] = -1;
                            }
                            else if (boardState[checkThisRow][checkThisColumn] > 0) {
                                blackChecking[checkThisRow][checkThisColumn] = -1;
                                break;
                            }
                            else {
                                break;
                            }
                        }
                        catch (Exception e) {}
                    }    
                    
                    // check up from black queen
                    checkThisRow = queenRow;
                    checkThisColumn = queenColumn;
                    while (checkThisRow >= 0) {
                        checkThisRow--;
                        try {
                            if (boardState[checkThisRow][checkThisColumn] == 0) {
                                blackChecking[checkThisRow][checkThisColumn] = -1;
                            }
                            else if (boardState[checkThisRow][checkThisColumn] > 0) {
                                blackChecking[checkThisRow][checkThisColumn] = -1;
                                break;
                            }
                            else {
                                break;
                            }
                        }
                        catch (Exception e) {}
                    }
                    
                    // check right from black queen
                    checkThisRow = queenRow;
                    checkThisColumn = queenColumn;
                    while (checkThisColumn <= 7) {
                        checkThisColumn++;
                        try {
                            if (boardState[checkThisRow][checkThisColumn] == 0) {
                                blackChecking[checkThisRow][checkThisColumn] = -1;
                            }
                            else if (boardState[checkThisRow][checkThisColumn] > 0) {
                                blackChecking[checkThisRow][checkThisColumn] = -1;
                                break;
                            }
                            else {
                                break;
                            }
                        }
                        catch (Exception e) {}
                    }
                    
                    // check down from black queen
                    checkThisRow = queenRow;
                    checkThisColumn = queenColumn;
                    while (checkThisRow <= 7) {
                        checkThisRow++;
                        try {
                            if (boardState[checkThisRow][checkThisColumn] == 0) {
                                blackChecking[checkThisRow][checkThisColumn] = -1;
                            }
                            else if (boardState[checkThisRow][checkThisColumn] > 0) {
                                blackChecking[checkThisRow][checkThisColumn] = -1;
                                break;
                            }
                            else {
                                break;
                            }
                        }
                        catch (Exception e) {}
                    }

                    // check left from black queen
                    checkThisRow = queenRow;
                    checkThisColumn = queenColumn;
                    while (checkThisColumn >= 0) {
                        checkThisColumn--;
                        try {
                            if (boardState[checkThisRow][checkThisColumn] == 0) {
                                blackChecking[checkThisRow][checkThisColumn] = -1;
                            }
                            else if (boardState[checkThisRow][checkThisColumn] > 0) {
                                blackChecking[checkThisRow][checkThisColumn] = -1;
                                break;
                            }
                            else {
                                break;
                            }
                        }
                        catch (Exception e) {}
                    }                    
                }                
            }
        }
        
        /* 
        For testing, print out all black checking positions
        System.out.println("\nPrint out black checking positions\n");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (blackChecking[i][j] < 0)
                    System.out.print(blackChecking[i][j] + " ");
                else
                    System.out.print(blackChecking[i][j] + "  ");
            }
            System.out.println("");
        }
        */
                
        // if proposed move is not a valid king move, return false
        if (validKingMoves[proposedRow][proposedColumn] != 1) {
            System.out.println();
            System.out.println("Not a valid king move\n");
            return false;
        }
        
        // if king is trying to a valid move, but is moving into a black
        // piece's checked position, return false
        if ((validKingMoves[proposedRow][proposedColumn] == 1) &&
                blackChecking[proposedRow][proposedColumn] == -1) {
            System.out.println();
            System.out.println("Cannot move king into check");
            return false;
        }
        
        // If king's proposed position is empty, move it there
        if (boardState[proposedRow][proposedColumn] == 0) {
            boardState[originalRow][originalColumn] = 0;
            boardState[proposedRow][proposedColumn] = 6;
        }
        // If rook's proposed position is occupied by opponent, take its place
        // and add defeated foe to deadPieces Array List
        else if (boardState[proposedRow][proposedColumn] < 0) {
            deadPieces.add(boardState[proposedRow][proposedColumn]);
            boardState[originalRow][originalColumn] = 0;
            boardState[proposedRow][proposedColumn] = 6;
        }
        
        System.out.println("Successful king move");
        return true;
        }
    
    // Print board method
    public void printBoard() {
        System.out.println("");
        for (int i = 0; i < 8; i++) {

            for (int j = 0; j < 8; j++) {

                if (boardState[i][j] > -1) {
                    System.out.print(boardState[i][j] + "  ");
                }
                else {
                    System.out.print(boardState[i][j] + " ");
                }
            }
            System.out.println("");
        }
    }
}