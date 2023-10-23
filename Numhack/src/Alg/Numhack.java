package Alg;

import Cell.Cell;

import java.util.ArrayList;

public class Numhack {

    public static int n, k=24;
    private static int max, min, range;
    private static Cell root;
    private static int index=0;

    public static void sort(ArrayList<Integer> ar){
        index = 0;
        root = null;

        int size = ar.size();
        max = ar.get(0);
        min = ar.get(0);
        // get max and min values
        for (int i = 1; i < size; i++) {
            if (ar.get(i) > max) max = ar.get(i);
            if (ar.get(i) < min) min = ar.get(i);
        }
        range = 1+max-min;

        double q = Math.pow(k*Math.log(range)/2, 0.5);
        n = (int) Math.round(Math.pow(Math.E, 2*LambertW.W(q)));

        System.out.println(n);

        for (Integer integer : ar) {
            insert(integer);
        }

        fill(root, ar);
    }

    public static void sort(Integer[] ar){
        index = 0;
        root = null;

        int size = ar.length;

        max = ar[0];
        min = ar[0];
        // get max and min values
        for (int i = 1; i < size; i++) {
            if (ar[i] > max) max = ar[i];
            if (ar[i] < min) min = ar[i];
        }
        range = 1+max-min;

        double q = Math.pow(k*Math.log(range)/2, 0.5);
        n = (int) Math.round(Math.pow(Math.E, 2*LambertW.W(q)));

        for (Integer integer : ar) {
            insert(integer);
        }

        fill(root, ar);
    }

    private static void fill(Cell c, Integer[] ar){
        if(c.CellChain!=null) {
            for(Cell cC: c.CellChain) if(cC!=null) fill(cC, ar);
        }
        else{
            for(Integer i: c.stNum){
                ar[index] = i;
                index++;
            }
        }
    }

    private static void fill(Cell c, ArrayList<Integer> ar){
        if(c.CellChain!=null){
            for(Cell cC: c.CellChain) if(cC!=null) fill(cC, ar);
        }
        else {
            for(Integer i: c.stNum){
                ar.set(index, i);
                index++;
            }
        }
    }

    private static void insert(Integer i){
        if(root==null) root = new Cell(i, min, range);
        else root.handle(i);
    }
}
