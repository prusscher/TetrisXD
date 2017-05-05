package com.bepis.tetris;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Parker on 4/21/2017.
 */

public class PieceGenerator {
    private ArrayList<Integer> queue;

    public PieceGenerator() {
        queue = new ArrayList<Integer>();
        nextSet();
    }

    public void printQueue() {
        System.out.println(queue.toString());
        nextSet();
    }

    private void nextSet() {
        Random r = new Random();

        // actually clearing the queue doesnt really do anything and could fuck things up
        // If we clear when we dont want, we could lose some pieces
//        queue.clear();

        // Array of indexes representing the tiles
        ArrayList<Integer> temp = new ArrayList<Integer>();
        for(int x = 0; x < 7; x++)
            temp.add(x);

        // Remove an index 1by1 at random from temp and add to the queue
        for(int x = 0; x < 7; x++) {
            int remove = r.nextInt(temp.size());
            queue.add(temp.remove(remove));
        }
    }

    public int nextPiece() {
        // If the queue/item pag is empty, get a new set of pieces
        if(queue.size() <= 1)
            nextSet();

        return queue.remove(0);
    }
}
