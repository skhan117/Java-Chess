import java.awt.*;
import javax.swing.*;

class Tile extends JPanel {

// tileNumber will indicate location of tile in 8x8 grid
private static int tileNumber = -1;
private int number;

// occupiedByPiece will indicate if a chess piece is occupying
// the tile.
// 0: tile is unoccupied
// 1 or -1: tile is occupied by white or black pawn
// 2 or -2: tile is occupied by white or black bishop
// 3 or -3: tile is occupied by white or black knight
// 4 or -4: tile is occupied by white or black rook
// 5 or -5: tile is occupied by white or black queen
// 6 or -6: tile is occupied by white or black king
private int occupiedByPiece;

    public Tile() {
        tileNumber++;
        number = tileNumber;       
        
        // By default, tile is unoccupied
        occupiedByPiece = 0;
    }
    
    public void paintComponent(Graphics g) {
        
        // Setup graphics for piece on tile here
        
    }   
    
    public int getNumber() {
        return number;
    }
    
    public void setNumber(int n) {
        number = n;
    }
    
    // Move a piece onto here
    public void occupyByPiece(int n) {
        occupiedByPiece = n;
    }
    

}