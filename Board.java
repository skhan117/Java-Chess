/*
    A Board object has a two-dimensional integer array
    depicting a particular state of the chess board where:
    -white pawns are represented by 1.
    -white bishops are represented by 2.
    -white knights are represented by 3.
    -white rooks are represented by 4.
    -the white queen is represented by 5.
    -the white king is represented by 6.
    -black pieces are represented by -n. e.g a black pawn
     is -1, a black bishop is -2, etc.

*/

import java.util.*;

public class Board {
    
    private int [][] boardState;   
    private ArrayList <Integer> deadPieces;
    
    // Default constructor starts a new board.
    public Board() {

        boardState = new int[8][8];
        
        setupWhitePieces();
        setupBlackPieces();
        
        deadPieces = new ArrayList<Integer>();
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
                 
        
        // Return false if pawn isn't trying to move one row ahead.
        if (!(proposedRow == originalRow - 1)) {
            System.out.println("Out 1"); // FIXME
            return false;
        }
        
        // If there's already another white piece in proposed place, move
        // cannot be made. Return false.
        if (boardState[proposedRow][proposedColumn] > 0) {
            System.out.println("Out 2"); // FIXME
            return false;

        }
        
        // If there's a black piece in proposed place, move must be diagonal.
        if (boardState[proposedRow][proposedColumn] < 0) {
            System.out.println("Black piece");
            // Return false if proposed column isn't adjacent to original
            if (proposedColumn == originalColumn - 1) {
                
            }
            else if (proposedColumn == originalColumn + 1) {
                
            }
            else {
                return false;
            }
        }


        // If the proposed position is empty and 1 row ahead of pawn, return
        // true and make the move
        if ((boardState[proposedRow][proposedColumn] == 0) && (proposedColumn == originalColumn) 
                && (proposedRow == originalRow -1)) {
            // FIXME
        }
        
        
        // Otherwise make the move and return true
        
        // If necessary, add black piece attacked to ArrayList deadpieces
        if (boardState[proposedRow][proposedColumn] < 0) {
            deadPieces.add(boardState[proposedRow][proposedColumn]);
        }
        
        // Replace original place with 0
        boardState[originalRow][originalColumn] = 0;
        
        // If proposedRow pawn is moving to is 0, replace pawn with queen.
        // If it's any other number, just place the pawn there.
        if (proposedRow == 0) {
            boardState[proposedRow][proposedColumn] = 5;
        } 
        else {
            boardState[proposedRow][proposedColumn] = 1;
        }
        
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
    // to ArrayList deadPieces. A shortcut can be used here. Just
    // make this function a composite of the white queen and white
    // rook.
    public Boolean makeAWhiteQueenMove (int originalRow, int originalColumn, 
            int proposedRow, int proposedColumn) {
                 
        
        
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
    // to ArrayList deadPieces. This function must first check to 
    // see if the king is moving into check; if so, then the move
    // cannot be made.
    public Boolean makeAWhiteKingMove (int originalRow, int originalColumn, 
            int proposedRow, int proposedColumn) {
                 
        
        
        return true;
        }
    
    // Print board method
    public void printBoard() {
        for (int i = 0; i < 8; i++) {

            for (int j = 0; j < 8; j++) {

                if (boardState[i][j] > -1) {
                    System.out.print(boardState[i][j] + "  ");
                }
                else {
                    System.out.print(boardState[i][j] + " ");
                }
                
            }
            System.out.println();
        }
    }
}

