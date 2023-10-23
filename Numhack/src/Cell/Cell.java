package Cell;

import Alg.Numhack;

import java.util.ArrayList;

public class Cell {

    // Do not initialize if not necessary
    public ArrayList<Integer> stNum;

    public double rangel;
    public double range;
    public Cell[] CellChain;


    public Cell(Integer storedNum, int rangel, int range){
        // used when inserting the first element, root must always be cellchain

        this.rangel = rangel;
        this.range = range;
        CellChain = new Cell[Numhack.n];
        this.handle(storedNum);
    }

    Cell(Integer storedNum, Cell vexCell, int index){
        // hypoInsert
        this.stNum = new ArrayList<>();
        this.stNum.add(storedNum);

        this.range = vexCell.range/Numhack.n;
        this.rangel = vexCell.rangel + range*index;
    }

    Cell(ArrayList<Integer> storedNum, Cell vexCell, int index){
        // hypoInsert
        this.stNum = storedNum;

        this.range = vexCell.range/Numhack.n;
        this.rangel = vexCell.rangel + range*index;
    }

    Cell(Cell vexCell, int index){
        // interInsert
        this.range = vexCell.range/Numhack.n;
        this.rangel = vexCell.rangel + range*index;
        this.CellChain = new Cell[Numhack.n];
    }

    public void handle(Integer i){

        if(stNum!=null){
            if(i.equals(stNum.get(0))){
                stNum.add(i);
            }
            else{
                CellChain = new Cell[Numhack.n];
                finalizeCellChain(i);
                stNum=null;
            }
        }
        else{
            insertToCellChain(i);
        }
    }

    private void finalizeCellChain(Integer i){
        int nexti1 = numhackF(i);
        int nexti2 = numhackF(stNum.get(0));

        while(nexti1==nexti2){
            range = range/Numhack.n;
            rangel = rangel + (range*nexti1);

            nexti1 = numhackF(i);
            nexti2 = numhackF(stNum.get(0));
        }

        // generating nexCells
        CellChain[nexti1] = new Cell(i, this, nexti1);
        CellChain[nexti2] = new Cell(stNum, this, nexti2);

        stNum = null;
    }

    private void finalizeCellChain(Integer i, Cell nexCell){
        int nexti1 = numhackF(i);
        int nexti2 = numhackF(nexCell.rangel+nexCell.range/2);

        while(nexti1==nexti2){
            range = range/Numhack.n;
            rangel = rangel + (range*nexti1);

            nexti1 = numhackF(i);
            nexti2 = numhackF(nexCell.rangel+nexCell.range/2);
        }
        // generating nexCells
        CellChain[nexti1] = new Cell(i, this, nexti1);
        CellChain[nexti2] = nexCell;
    }

    private void insertToCellChain(Integer i){
        int nexti = numhackF(i);
        Cell nexCell = CellChain[nexti];

        if(nexCell==null) {
            CellChain[nexti] = new Cell(i, this, nexti);
        }
        else if(i<nexCell.rangel || nexCell.rangel+nexCell.range<=i){
            CellChain[nexti] = new Cell(this, nexti);
            CellChain[nexti].finalizeCellChain(i, nexCell);
        }
        else{
            nexCell.handle(i);
        }
    }

    private int numhackF(Integer i){
        return (int) Math.floor((Numhack.n*(i-rangel))/range);
    }

    private int numhackF(Double i){
        return (int) Math.floor((Numhack.n*(i-rangel))/range);
    }

}
