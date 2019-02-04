package com.example.pablo_mp4_part2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Boolean op1 = true;
    Boolean op2 = false;
    Boolean ex = false;
    Boolean dot = false;
    Boolean eq = false;

    double num1 = 0;
    double num2 = 0;

    String e = "";
    String s1 = "";
    String s2 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void calculate(View view) {
        //Instantiate the different views and buttons
        TextView nout = (TextView)findViewById(R.id.number_out);
        TextView eout = (TextView)findViewById(R.id.express_out);
        Button curr = (Button)view;
        String s = curr.getText().toString();

        if(curr.getId() == R.id.mul) {
            s = "X";
        } else if(curr.getId() == R.id.minus) {
            s = "-";
        } else if(curr.getId() == R.id.plus) {
            s = "+";
        } else if(curr.getId() == R.id.equals) {
            s = "=";
        } else if(curr.getId() == R.id.clear) {
            s = "C";
        } else if(curr.getId() == R.id.dot) {
            s = ".";
        }

        if(s == "X" || s == "-" || s == "+") {
            //if one of the operands is the input
            if(ex == false) {
                e = s;
                op2 = true;
                op1 = false;
                dot = false;
                ex = true;
                eout.setText(e);
            }
        } else if(s == "=") {
            //if displaying a final result
            if(e == "X") {
                eq = true;
                eout.setText("=");
                nout.setText(Double.toString(num1 * num2));
            } else if(e == "+") {
                eq = true;
                eout.setText("=");
                nout.setText(Double.toString(num1 + num2));
            } else if (e == "-"){
                eq = true;
                eout.setText("=");
                nout.setText(Double.toString(num1 - num2));
            } else {
                //do nothing
            }
            if(eq == true) {
                num1 = 0;
                num2 = 0;
                eq = false;
                op1 = true;
                op2 = false;
                ex = false;
                dot = false;
                e = "";
                s1 = "";
                s2 = "";
            }
        } else if(s == "C") {
            //when clearing with C
            if(eout.getText() == "=") {
                eout.setText("");
                nout.setText("0");
            } else if(op1 == true && ex == false) {
                //when going from input in s1 with no expression
                s1 = "";
                nout.setText("0");
            } else if(op1 == false && op2 == true && ex == true && s2 == "") {
                //when going from having input in s1 and an expression but no input in s2 yet
                ex = false;
                eout.setText("");
                op1 = true;
                op2 = false;
                nout.setText(s1);
            } else if(op1 == false && op2 == true && ex == true && s2 != "") {
                //when going from having input in s1 and an input in s2
                s2 = "";
                nout.setText(s2);
            }
        } else {
            if(op1 == true) {
                //when working with the first operand
                if(s == ".") {
                    dot = true;
                    if(s1 == "") {
                        s1 = "0";
                    }
                    s1 = s1 + s;
                    nout.setText(s1);
                    num1 = Double.parseDouble(s1);
                } else {
                    s1 = s1 + s;
                    nout.setText(s1);
                    num1 = Double.parseDouble(s1);
                }
            } else if(op2 == true) {
                //when working with the second operand
                if(s == "." && dot == false) {
                    dot = true;
                    if(s2 == "") {
                        s2 = "0";
                    }
                    s2 = s2 + s;
                    nout.setText(s2);
                    num2 = Double.parseDouble(s2);
                } else {
                    s2 = s2 + s;
                    nout.setText(s2);
                    num2 = Double.parseDouble(s2);
                }
            } else {
                //do nothing
            }
        }
    }
}
