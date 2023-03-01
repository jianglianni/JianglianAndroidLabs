package com.example.chenapplication5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Chen lin
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {
    /** This holds the text at the centre of the screen*/
    private TextView tv = null;
    /** This holds ...*/
    private EditText et = null;
    /** This holds ...*/
    private Button btn = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.textView);
        et = findViewById(R.id.editText);
        btn = findViewById(R.id.button);

        btn.setOnClickListener( clk -> {
            String password = et.getText().toString();

            if(checkPasswordComplexity(password))
            {
                tv.setText("Your password meets the requirements.");
            }
            else {
                tv.setText("You shall not pass!");
            }
        });



    }

    /**
     * password validation
     * @param pw The String object that we are checking
     * @return Return true if ....
     */
    boolean checkPasswordComplexity(String pw)
    {
        boolean foundUpperCase = false;
        boolean foundLowerCase = false;
        boolean foundNumber = false;
        boolean foundSpecial = false;
        boolean validLength = (pw.length() > 3 && pw.length() < 21) ? true : false;

        for(int i = 0; i < pw.length(); i++)
        {
            char c = pw.charAt(i);
            if(Character.isUpperCase(c))
            {
                foundUpperCase = true;
            }
            else if (Character.isLowerCase(c)) {
                foundLowerCase = true;
            } else if (Character.isDigit(c)) {
                foundNumber = true;
            }
            else if (isSpecialCharacter(c))
            {
                foundSpecial = true;
            }
        }

        if(!validLength){
            Toast.makeText(getApplicationContext(),"your password length should be between 4 and 20 inclusive",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(!foundLowerCase)
        {
            Toast.makeText(getApplicationContext(),"Your password does not have an lower case letter",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(!foundUpperCase){
            Toast.makeText(getApplicationContext(),"Your password does not have an upper case letter",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(!foundNumber){
            Toast.makeText(getApplicationContext(),"Your password does not have a number",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(!foundSpecial)
        {
            Toast.makeText(getApplicationContext(),"Your password does not have any special character",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        else{
            return true;
        }
    }

    /**
     * To check if a password contains any character
     * @param c to check any character in a password
     * @return Return true if...
     */
    boolean isSpecialCharacter(char c)
    {
        switch(c)
        {
            case '#':
            case '?':
            case '*':
            case '@':
            case '$':
            case '%':
            case '^':
            case '!':
                return true;
            default:
                return false;
        }
    }
}