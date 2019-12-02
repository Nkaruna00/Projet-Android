package com.example.mysudoku;

import android.content.Intent;
import android.util.Log;
@SuppressWarnings("WeakerAccess")

public class GameBoard {

    /**
     * UNE CELLULE
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


    public boolean bigNumber = true;


    public int currentCellX = -1;
    public int currentCellY = -1;


    public GameCell [][] cells;




    private GameBoard(GameCell [][] cells ) {

        this.cells = cells;
    }





    public int getSelectedValue() {

        if ( this.currentCellX == -1 ) return 0;
        if ( this.currentCellY == -1 ) return 0;

        GameCell currentCell = this.cells[ this.currentCellY ][ this.currentCellX ];
        return currentCell.getAssumedValue();
    }


    public void pushValue( int value ) {

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


                   if (sudoku.mat[i][j] != 0) {
                       gameboard.cells[i][j].setInitial(true);
                       gameboard.cells[i][j].setAssumedValue(gameboard.cells[i][j].getRealValue());
                   }
               }
           }


        return gameboard;

    }




    public static GameBoard getGameBoard() {


        GameBoard gameboard = new GameBoard(new GameCell[][]{

                { new GameCell(0,0), new GameCell(0,0), new GameCell(0,0),
                        new GameCell(0,0), new GameCell(0,0), new GameCell(0,0),
                        new GameCell(0,0), new GameCell(0,0), new GameCell(0,0) },
                { new GameCell(0,0), new GameCell(0,0), new GameCell(0,0),
                        new GameCell(0,0), new GameCell(0,0), new GameCell(0,0),
                        new GameCell(0,0), new GameCell(4,0), new GameCell(9,0) },
                { new GameCell(0,0), new GameCell(0,0), new GameCell(0,0),
                        new GameCell(0,0), new GameCell(0,0), new GameCell(0,0),
                        new GameCell(0,0), new GameCell(0,0), new GameCell(0,0) },

                { new GameCell(0,0), new GameCell(0,0), new GameCell(0,0),
                        new GameCell(0,0), new GameCell(0,0), new GameCell(0,0),
                        new GameCell(0,0), new GameCell(0,0), new GameCell(0,0) },
                { new GameCell(0,0), new GameCell(0,0), new GameCell(0,0),
                        new GameCell(0,0), new GameCell(0,0), new GameCell(0,0),
                        new GameCell(0,0), new GameCell(0,0), new GameCell(0,0) },
                { new GameCell(0,0), new GameCell(0,0), new GameCell(0,0),
                        new GameCell(0,0), new GameCell(0,0), new GameCell(0,0),
                        new GameCell(0,0), new GameCell(0,0), new GameCell(0,0) },

                { new GameCell(0,0), new GameCell(0,0), new GameCell(0,0),
                        new GameCell(0,0), new GameCell(0,0), new GameCell(0,0),
                        new GameCell(0,0), new GameCell(0,0), new GameCell(0,0) },
                { new GameCell(0,0), new GameCell(0,0), new GameCell(0,0),
                        new GameCell(0,0), new GameCell(0,0), new GameCell(0,0),
                        new GameCell(0,0), new GameCell(0,0), new GameCell(0,0) },
                { new GameCell(0,0), new GameCell(0,0), new GameCell(0,0),
                        new GameCell(0,0), new GameCell(0,0), new GameCell(0,0),
                        new GameCell(0,0), new GameCell(0,0), new GameCell(0,0) }
        });



        gameboard = gameBoardValue(gameboard);

        return gameboard;












    }

}