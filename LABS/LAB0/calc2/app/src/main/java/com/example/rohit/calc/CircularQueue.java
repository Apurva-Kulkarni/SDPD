package com.example.rohit.calc;

import android.widget.TextView;

/**
 * Created by rohit on 23/1/18.
 */

public class CircularQueue {
    int size;
    String [] data;
    int top;
    boolean flag = false;

    public CircularQueue(int size) {
        this.size = size;
        top = size - 1;
        data = new String[size];
    }

    public void add(String s){
        data[top] = s;
        top = top - 1;

        if (top < 0){
            top = size - 1;
            if(!flag){
                flag = true;
            }
        }


    }

    public void update(TextView view){
        view.setText("");

        if (flag){
            for(int i = top+1; ;){
                i = i%4;
                view.append(data[i]);
                i++;
                if(i==top-1)
                    break;
            }
        }
        else{
            for (int i = size-1; i > top; i--) {
                System.out.println(data[i]);
                view.append(data[i]);
            }
        }


    }
}
