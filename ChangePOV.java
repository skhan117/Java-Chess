/*
    The Change POV class contains the change method.
    The change method takes a 2-dimensional array
    representing a chessboard as an argument, switches the 
    board from white's perspective to black's perspective, 
    or vice-versa, then returns a new board.
*/

public class ChangePOV {
    
    public static int[][] change (int[][] oldBoard) {
        
        int [][] newBoard = new int[8][8];
        
        // Go through each tile and multiply its value by -1
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                newBoard[i][j] = oldBoard[i][j] * -1;
            }
        }
        
        // Flip the chessboard's rows
        int w = 0;
        while (w != 3) {
            int [] tempRow = new int[8];
            tempRow = newBoard[7 - w];
            newBoard[7 - w] = newBoard[w];
            newBoard[w] = tempRow;
            w++;
        }   
        return newBoard;
    }
}