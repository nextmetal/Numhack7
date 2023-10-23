import Alg.Numhack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Main {
    Main(){
        Random r = new Random();
        System.out.println("Checkpoint 1");

        ArrayList<Integer> nonSortedList = new ArrayList<>();
        for (int i=0; i<10000000; i++) {
            nonSortedList.add(r.nextInt()%1000000000);
        }
        Collections.shuffle(nonSortedList);

        System.out.println("Checkpoint 2");

        Numhack.sort(nonSortedList);

        System.out.println("Checkpoint 3");

        for (int i=1; i<10000000; i++) {
            if(nonSortedList.get(i)<nonSortedList.get(i-1)) System.out.println(false);
        }
    }

    public static void main(String[] args) {
        new Main();

    }
}