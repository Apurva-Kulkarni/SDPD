package com.example.rohit.calc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    com.example.rohit.calc.CircularQueue results = new CircularQueue(5);
    Deque<String> res = new LinkedList();
    double num1 = 0;
    double num2 = 0;
    char op = '\0';
    String exp = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);
        Button button5 = (Button) findViewById(R.id.button5);
        Button button6 = (Button) findViewById(R.id.button6);
        Button button7 = (Button) findViewById(R.id.button7);
        Button button8 = (Button) findViewById(R.id.button8);
        Button button9 = (Button) findViewById(R.id.button9);
        Button button0 = (Button) findViewById(R.id.button0);
        Button button_add = (Button) findViewById(R.id.button_add);
        Button button_sub = (Button) findViewById(R.id.button_sub);
        Button button_mul = (Button) findViewById(R.id.buttonmul);
        Button button_div = (Button) findViewById(R.id.buttondiv);
        Button button_dot = (Button) findViewById(R.id.button_dot);
        Button evaluate = (Button) findViewById(R.id.button_equals);
        Button erase = findViewById(R.id.button_del);

        final TextView expression = findViewById(R.id.textView);
        final TextView outputs = findViewById(R.id.output_view);

        button1.setOnClickListener(new OnClickListener(){
            public void onClick(View view){
                expression.setText(expression.getText() + "1");
            }
        });

        button2.setOnClickListener(new OnClickListener(){
            public void onClick(View view){
                expression.setText(expression.getText() + "2");
            }
        });

        button3.setOnClickListener(new OnClickListener(){
            public void onClick(View view){
                expression.setText(expression.getText() + "3");
            }
        });
        button4.setOnClickListener(new OnClickListener(){
            public void onClick(View view){
                expression.setText(expression.getText() + "4");
            }
        });

        button5.setOnClickListener(new OnClickListener(){
            public void onClick(View view){
                expression.setText(expression.getText() + "5");
            }
        });
        button6.setOnClickListener(new OnClickListener(){
            public void onClick(View view){
                expression.setText(expression.getText() + "6");
            }
        });

        button7.setOnClickListener(new OnClickListener(){
            public void onClick(View view){
                expression.setText(expression.getText() + "7");
            }
        });
        button8.setOnClickListener(new OnClickListener(){
            public void onClick(View view){
                expression.setText(expression.getText() + "8");
            }
        });

        button9.setOnClickListener(new OnClickListener(){
            public void onClick(View view){
                expression.setText(expression.getText() + "9");
            }
        });

        button0.setOnClickListener(new OnClickListener(){
            public void onClick(View view){
                expression.setText(expression.getText() + "0");
            }
        });
        button_dot.setOnClickListener(new OnClickListener(){
            public void onClick(View view){
                expression.setText(expression.getText() + ".");
            }
        });
        button_add.setOnClickListener(new OnClickListener(){
            public void onClick(View view){
                if(op == '\0')
                {
                    if(expression.getText().toString().equals("-"))
                        expression.setText(expression.getText() + "-");
                    else{
                        num1 = Double.parseDouble(expression.getText().toString());
                        exp = exp + num1 + "+";
                        expression.setText("");
                    }

                }
                else {
                    num2 = Double.parseDouble(expression.getText().toString());
                    try {
                        num1 = applyop(op, num1, num2);
                    } catch (UnsupportedOperationException e) {
                        num1 = 0;
                        num2 = 0;
                        op = '\0';
                        System.out.println(e.getMessage());
                    }
                    expression.setText("");
                    op = '+';
                }
            }
        });

        button_sub.setOnClickListener(new OnClickListener(){
            public void onClick(View view){
                if(op == '\0')
                {
                    expression.setText("-");
                }
                else {
                    System.out.println(expression.getText().toString());
                    if(expression.getText().toString().equals("")) {
                        expression.setText(expression.getText() + "-");
                    }
                    else
                    {
                        num2 = Double.parseDouble(expression.getText().toString());

                        try {
                            num1 = applyop(op, num1, num2);
                        } catch (UnsupportedOperationException e) {
                            num1 = 0;
                            num2 = 0;
                            op = '\0';
                            System.out.println(e.getMessage());
                        }
                        expression.setText("");
                    }
                    op = '-';
                }

            }
        });
        button_mul.setOnClickListener(new OnClickListener(){
            public void onClick(View view){
                if(op == '\0')
                {
                    if(expression.getText().toString().equals("-"))
                        expression.setText(expression.getText() + "-");
                    else{
                        num1 = Double.parseDouble(expression.getText().toString());
                        exp = exp + num1 + "+";
                        expression.setText("");
                        op='*';
                    }

                }
                else {
                    num2 = Double.parseDouble(expression.getText().toString());
                    try {
                        num1 = applyop(op, num1, num2);
                    }
                    catch(UnsupportedOperationException e){
                        num1=0;
                        num2=0;
                        op='\0';
                        System.out.println(e.getMessage());
                    }
                    expression.setText("");
                    op = '*';
                }

            }
        });
        button_div.setOnClickListener(new OnClickListener(){
            public void onClick(View view){
                if(op == '\0')
                {
                    if(expression.getText().toString().equals("-"))
                        expression.setText(expression.getText() + "-");
                    else{
                        num1 = Double.parseDouble(expression.getText().toString());
                        exp = exp + num1 + "+";
                        expression.setText("");
                        op='/';
                    }

                }
                else {
                    num2 = Double.parseDouble(expression.getText().toString());
                    try {
                        num1 = applyop(op, num1, num2);
                    }
                    catch(UnsupportedOperationException e){
                        num1=0;
                        num2=0;
                        op='\0';
                        System.out.println(e.getMessage());
                    }
                    expression.setText("");
                    op = '/';
                }

            }
        });

        erase.setOnClickListener(new OnClickListener(){
            public void onClick(View view){
                expression.setText("");
            }
        });

        evaluate.setOnClickListener(new OnClickListener(){
            public void onClick(View view){
                if(op == '\0'){
                    System.out.println(num1);
                    if(res.size()<5)
                        res.push(num1 + "");
                    else{
                        res.removeFirst();
                        res.addFirst(num1 + "");
                    }
                }
                else{
                    num2 = Double.parseDouble(expression.getText().toString());
                    try {
                        num1 = applyop(op, num1, num2);
                        if(res.size()<5)
                            res.push(num1 + "");
                        else{
                            res.removeLast();
                            res.addFirst(num1 + "");
                        }
                    }
                    catch(UnsupportedOperationException e) {
                        System.out.println(e.getMessage());
                        if(res.size()<5)
                            res.push(e.getMessage());
                        else{
                            res.removeLast();
                            res.addFirst(e.getMessage() );
                        }
                    }
                    op='\0';
                    num1=0;
                    num2=0;
                }
                expression.setText("");
                update(outputs, res);
            }
        });
    }
    public double applyop(char op, double a, double b) throws UnsupportedOperationException{
        switch (op){
            case '+':
                return a+b;
            case '-':
                return a-b;
            case '*':
                return a*b;
            case '/':
                if(b==0){
                    throw new UnsupportedOperationException("Cannot Divide by Zero");
                }

                return a/b;
        }
        return a+b;
    }
    public void update(TextView output, Deque ret){
        output.setText("");
        Iterator iterator = ret.iterator();
        while(iterator.hasNext()){
            String element = (String) iterator.next();
            output.append(element + "\n");
        }
    }
}