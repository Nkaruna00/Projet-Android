package com.example.mysudoku;

import android.util.Log;
@SuppressWarnings("WeakerAccess")

public class GameBoard {

    /**
     * This class represent one cell and it's informations.
     */
    public static class GameCell {
        public int getRealValue() {
            return realValue;
        }

        public void setRealValue(int realValue) {
            this.realValue = realValue;
        }

        private int realValue;

        public int getAssumedValue() {
            return assumedValue;
        }

        public void setAssumedValue(int assumedValue) {
            this.assumedValue = assumedValue;
        }

        private int assumedValue;

        public boolean getisInitial() {
            return isInitial;
        }

        public void setInitial(boolean initial) {
            isInitial = initial;
        }

        private boolean isInitial = false;



        public boolean [] marks = { false, false, false, false, false, false, false, false, false };

        public GameCell( int realValue ) {
            this.realValue = realValue;
        }

        public GameCell( int realValue, int isInitial ) {
            this.realValue = realValue;
            this.isInitial = isInitial == 1;
            if ( this.isInitial ) this.assumedValue = realValue;
        }
    }




    public GameLevel level;
    public boolean bigNumber = true;


    public int currentCellX = -1;
    public int currentCellY = -1;


    public GameCell [][] cells;


    //int[] mat[];
    //int N;
   // int SRN;
    //int K;






    private GameBoard( GameLevel level, GameCell [][] cells ) {
        this.level = level;
        this.cells = cells;
    }





    public int getSelectedValue() {
        // We need to know the current cell
        if ( this.currentCellX == -1 ) return 0;
        if ( this.currentCellY == -1 ) return 0;

        GameCell currentCell = this.cells[ this.currentCellY ][ this.currentCellX ];
        return currentCell.getAssumedValue();
    }


    public void pushValue( int value ) {
        // We need to know the current cell
        if ( this.currentCellX == -1 ) return;
        if ( this.currentCellY == -1 ) return;

        GameCell currentCell = this.cells[ this.currentCellY ][ this.currentCellX ];

        if ( currentCell.getisInitial()) return;

        if ( this.bigNumber ) {
            currentCell.setAssumedValue(value);



        } else {
            currentCell.marks[value-1] = ! currentCell.marks[value-1];
        }
    }


    public void clearCell() {

        if ( this.currentCellX == -1 ) return;
        if ( this.currentCellY == -1 ) return;

        GameCell currentCell = this.cells[ this.currentCellY ][ this.currentCellX ];

        if ( currentCell.getisInitial() ) return;

        currentCell.setAssumedValue(0);
        currentCell.marks = new boolean[] { false, false, false, false, false, false, false, false, false };
    }


    /*

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    public static void fillValues(GameBoard gameboard)
    {
        // Fill the diagonal of SRN x SRN matrices
        fillDiagonal(gameboard);

        // Fill remaining blocks
        fillRemaining(0, 3,gameboard);

        // Remove Randomly K digits to make game
        removeKDigits(gameboard);
    }

    // Fill the diagonal SRN number of SRN x SRN matrices
    static void fillDiagonal(GameBoard gameboard)
    {

        for (int i = 0; i<9; i=i+3)

            // for diagonal box, start coordinates->i==j
            fillBox(i, i,gameboard);


    }

    static boolean unUsedInBox(int rowStart, int colStart, int num,GameBoard gameboard)
    {
        for (int i = 0; i<3; i++) {
            for (int j = 0; j < 3; j++) {
                Log.v("WHILE !","lE CELL: " + gameboard.cells[rowStart+i][colStart+j].realValue );
                Log.v("WHILE !","lE NUM: " + num);
                if (gameboard.cells[rowStart + i][colStart + j].realValue == num)


                    return false;
            }
        }
        return true;
    }

    static void fillBox(int row,int col,GameBoard gameboard)
    {
        int num;
        for (int i=0; i<3; i++)
        {
            for (int j=0; j<3; j++)
            {
                do
                {
                    num = randomGenerator(9);
                    Log.v("WHILE !","je suis dans le while");
                }
                while (!unUsedInBox(row, col, num,gameboard));

                gameboard.cells[row+i][col+j].realValue = num;
            }
        }
    }

    static int randomGenerator(int num)
    {
        return (int) Math.floor((Math.random()*num+1));
    }

    static boolean CheckIfSafe(int i,int j,int num,GameBoard gameboard)
    {
        return (unUsedInRow(i, num,gameboard) &&
                unUsedInCol(j, num,gameboard) &&
                unUsedInBox(i-i%3, j-j%3, num,gameboard));
    }


    static boolean unUsedInRow(int i,int num,GameBoard gameboard)
    {
        for (int j = 0; j<9; j++)
            if (gameboard.cells[i][j].realValue == num)
                return false;
        return true;
    }

    // check in the row for existence
    static boolean unUsedInCol(int j,int num,GameBoard gameboard)
    {
        for (int i = 0; i<9; i++)
            if (gameboard.cells[i][j].realValue == num)
                return false;
        return true;
    }


    static boolean fillRemaining(int i, int j,GameBoard gameboard)
    {
        // System.out.println(i+" "+j);
        if (j>=9 && i<9-1)
        {
            i = i + 1;
            j = 0;
        }
        if (i>=9 && j>=9)
            return true;

        if (i < 3)
        {
            if (j < 3)
                j = 3;
        }
        else if (i < 9-3)
        {
            if (j==(int)(i/3)*3)
                j = j + 3;
        }
        else
        {
            if (j == 9-3)
            {
                i = i + 1;
                j = 0;
                if (i>=9)
                    return true;
            }
        }

        for (int num = 1; num<=9; num++)
        {
            if (CheckIfSafe(i, j, num,gameboard))
            {
                gameboard.cells[i][j].realValue = num;
                if (fillRemaining(i, j+1,gameboard))
                    return true;

                gameboard.cells[i][j].realValue = 0;
            }
        }
        return false;
    }

    public static void removeKDigits(GameBoard gameboard)
    {
        int count = 20;
        while (count != 0)
        {
            int cellId = randomGenerator(9*9);

            // System.out.println(cellId);
            // extract coordinates i and j
            int i = (cellId/9);
            int j = cellId%9;
            if (j != 0)
                j = j - 1;

            // System.out.println(i+" "+j);
            if (gameboard.cells[i][j].realValue != 0)
            {
                count--;
                gameboard.cells[i][j].realValue = 0;
            }
        }
    }


    */



       static GameBoard gameBoardValue(GameBoard gameboard){
        Sudoku sudoku = new Sudoku(9,0);
        sudoku.fillValues();


        for(int i = 0 ; i < 9;++i) {
            for (int j = 0; j < 9; ++j) {
                gameboard.cells[i][j].setRealValue(sudoku.mat[i][j]);

            }
        }
        sudoku.removeKDigits();

           for(int i = 0 ; i < 9;++i) {
               for (int j = 0; j < 9; ++j) {
                   Log.d("valeur","Valeur de " + i + j + " :"  + sudoku.mat[i][j] + "");

                   if (sudoku.mat[i][j] != 0) {
                       gameboard.cells[i][j].setInitial(true);
                       gameboard.cells[i][j].setAssumedValue(gameboard.cells[i][j].getRealValue());
                   }
               }
           }


        return gameboard;

    }




/*

    static GameBoard gameBoardValue(GameBoard gameboard) {
        Sudoku sudoku = new Sudoku(9,0);
        sudoku.fillValues();


        for(int i = 0 ; i < 9;++i) {
            for (int j = 0; j < 9; ++j) {
                gameboard.cells[i][j].setRealValue(sudoku.mat[i][j]);
                gameboard.cells[i][j].setAssumedValue(sudoku.mat[i][j]);
            }
        }

        // assumedvalue == real value
        // put random 0 in assumed vallue
        // if assumed value = 0, initial = false


        return gameboard;
    }


 */


    boolean compareTabs(GameBoard gameboard){
           for(int i =0; i < 9; ++i){
               for(int j = 0; j < 9; ++j){
                   if(gameboard.cells[i][j].getRealValue() != gameboard.cells[i][j].getAssumedValue()){
                       return false;
                   }
               }
           }
           return true;
    }

    boolean checkWin(GameBoard gameboard){
           if(compareTabs(gameboard) == true){
               System.out.println("Win !");
               return true;
           }
           else{
               return false;
           }
    }



    public static GameBoard getGameBoard( GameLevel level ) {

        if ( level != GameLevel.MEDIUM ) throw new RuntimeException( "Not actually implemented" );



        //GameBoard gameboard2 = new GameBoard(level,new GameCell[][]{{new GameCell(9,1), new GameCell(2,0)}});
        GameBoard gameboard = new GameBoard(level,new GameCell[][]{

                { new GameCell(9,0), new GameCell(2,0), new GameCell(8,0),
                        new GameCell(7,0), new GameCell(5,0), new GameCell(4,0),
                        new GameCell(1,0), new GameCell(3,0), new GameCell(6,0) },
                { new GameCell(6,0), new GameCell(7,0), new GameCell(1,0),
                        new GameCell(8,0), new GameCell(2,0), new GameCell(3,0),
                        new GameCell(5,0), new GameCell(4,0), new GameCell(9,0) },
                { new GameCell(3,0), new GameCell(5,0), new GameCell(4,0),
                        new GameCell(9,0), new GameCell(1,0), new GameCell(6,0),
                        new GameCell(2,0), new GameCell(7,0), new GameCell(8,0) },

                { new GameCell(4,0), new GameCell(9,0), new GameCell(6,0),
                        new GameCell(2,0), new GameCell(3,0), new GameCell(7,0),
                        new GameCell(8,0), new GameCell(5,0), new GameCell(1,0) },
                { new GameCell(8,0), new GameCell(1,0), new GameCell(5,0),
                        new GameCell(4,0), new GameCell(6,0), new GameCell(9,0),
                        new GameCell(7,0), new GameCell(2,0), new GameCell(3,0) },
                { new GameCell(7,0), new GameCell(3,0), new GameCell(2,0),
                        new GameCell(5,0), new GameCell(8,0), new GameCell(1,0),
                        new GameCell(9,0), new GameCell(6,0), new GameCell(4,0) },

                { new GameCell(5,0), new GameCell(4,0), new GameCell(3,0),
                        new GameCell(1,0), new GameCell(9,0), new GameCell(2,0),
                        new GameCell(6,0), new GameCell(8,0), new GameCell(7,0) },
                { new GameCell(2,0), new GameCell(6,0), new GameCell(9,0),
                        new GameCell(3,0), new GameCell(7,0), new GameCell(8,0),
                        new GameCell(4,0), new GameCell(1,0), new GameCell(5,0) },
                { new GameCell(1,0), new GameCell(8,0), new GameCell(7,0),
                        new GameCell(6,0), new GameCell(4,0), new GameCell(5,0),
                        new GameCell(3,0), new GameCell(9,0), new GameCell(2,0) }
        });



        gameboard = gameBoardValue(gameboard);
        //gameboard.cells[0][0].assumedValue = 6;
        return gameboard;





        /*
        return new GameBoard( level, new GameCell[][] {
                { new GameCell(9,1), new GameCell(2,0), new GameCell(8,0),
                        new GameCell(7,1), new GameCell(5,0), new GameCell(4,0),
                        new GameCell(1,1), new GameCell(3,0), new GameCell(6,0) },
                { new GameCell(6,0), new GameCell(7,0), new GameCell(1,0),
                        new GameCell(8,1), new GameCell(2,0), new GameCell(3,1),
                        new GameCell(5,0), new GameCell(4,0), new GameCell(9,0) },
                { new GameCell(3,0), new GameCell(5,1), new GameCell(4,1),
                        new GameCell(9,0), new GameCell(1,1), new GameCell(6,0),
                        new GameCell(2,0), new GameCell(7,1), new GameCell(8,0) },

                { new GameCell(4,1), new GameCell(9,1), new GameCell(6,0),
                        new GameCell(2,0), new GameCell(3,0), new GameCell(7,0),
                        new GameCell(8,1), new GameCell(5,1), new GameCell(1,0) },
                { new GameCell(8,0), new GameCell(1,1), new GameCell(5,0),
                        new GameCell(4,1), new GameCell(6,0), new GameCell(9,1),
                        new GameCell(7,0), new GameCell(2,1), new GameCell(3,0) },
                { new GameCell(7,0), new GameCell(3,1), new GameCell(2,1),
                        new GameCell(5,0), new GameCell(8,0), new GameCell(1,0),
                        new GameCell(9,0), new GameCell(6,1), new GameCell(4,1) },

                { new GameCell(5,0), new GameCell(4,1), new GameCell(3,0),
                        new GameCell(1,0), new GameCell(9,1), new GameCell(2,0),
                        new GameCell(6,1), new GameCell(8,1), new GameCell(7,0) },
                { new GameCell(2,0), new GameCell(6,0), new GameCell(9,0),
                        new GameCell(3,1), new GameCell(7,0), new GameCell(8,1),
                        new GameCell(4,0), new GameCell(1,0), new GameCell(5,0) },
                { new GameCell(1,0), new GameCell(8,0), new GameCell(7,1),
                        new GameCell(6,0), new GameCell(4,0), new GameCell(5,1),
                        new GameCell(3,0), new GameCell(9,0), new GameCell(2,1) }
        });
    */






    }

}