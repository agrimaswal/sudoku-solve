//sudoku solve

class Solution {
    final int MAX_SIZE = 9;
    private boolean isPresentInRow(int row, char value){
        // IN a Row Traverse in Each Col
        for(int col = 0; col<MAX_SIZE; col++){
            if(board[row][col] == value){
                return true;
            }
        }
        return false; 
    }
    private boolean isPresentInCol(int col, char value){
        // IN a Row Traverse in Each Col
        for(int row = 0; row<MAX_SIZE; row++){
            if(board[row][col] == value){
                return true;
            }
        }
        return false; 
    }

    private boolean isPresentInSubGrid(int row, int col, char value){
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for(int i = startRow; i<startRow+3; i++){
            for(int j= startCol; j<startCol+3; j++){
                if(board[i][j]==value){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isCorrectToPlaceANumber(int row , int col, char value){
        return !isPresentInRow(row, value) && !isPresentInCol(col, value) && !isPresentInSubGrid(row, col, value);
            // The Value is Present in Row
            // The Value is Present in Col
            // The Value is Present in SubGrid
    }
    char[][] board ; // Instance Variable
    private boolean solveSudoku(int row, int col){
        
         // if we reach to last column , then move to the next row
         if(col == MAX_SIZE){
             // Jump to the Next Row
             row = row + 1;
             col = 0;
         }
          // if we cross  the last row +1 (9) , we solve the sudoku (Termination Case)
           if(row == MAX_SIZE){ // Termination Case
               return true;
           } 
            // if cell is not empty so move to the next Column
            if(board[row][col]!='.'){
                return solveSudoku(row, col+1);
            }
             // If Cell is Empty , then Place the Digit (1 to 9 (Loop) ) But with Pre Check e.g isCorrectToPlaceANumber
             // Loop for 1 to 9 Options
             for(int i = 1; i<=MAX_SIZE; i++){
                 //isCorrectToPlaceANumber
                 if(isCorrectToPlaceANumber(row, col, (char) (i+'0'))){
                     // We can place a Digit in a current Cell
                     board[row][col] = (char) (i+'0'); // Value Placed in a Board
                     // Move to the Next Column
                    boolean result = solveSudoku(row, col+1);
                    if(result){
                        // Solved the Sudoku
                        return true; // Stack Fall
                    }
                    // Now do BackTracking
                    board[row][col] = '.'; // Undo the Option (Empty the Cell)
                 }
             } // Loop Exhaust We need Stack Fall
             return false;
    }
    public void solveSudoku(char[][] board) {
        this.board = board;
        solveSudoku(0,0);
       
       
        
       
    }
}