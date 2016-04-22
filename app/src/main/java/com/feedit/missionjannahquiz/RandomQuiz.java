package com.feedit.missionjannahquiz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Fikriansyah on 27/12/2015.
 */
public class RandomQuiz {
    private List<Integer> shuffeld;

    public RandomQuiz(int from, int to) {
        List<Integer>listOfNumbers = new ArrayList<>(to-from);
        for (int i=from;i<to;i++){
            listOfNumbers.add(i);
        }

        Collections.shuffle(listOfNumbers);
        this.shuffeld = listOfNumbers;
    }

    public synchronized Integer next(){
        if(shuffeld.isEmpty()){
            throw new IllegalStateException("");
        }
        return shuffeld.remove(0);
    }
}
