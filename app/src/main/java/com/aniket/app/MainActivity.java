package com.aniket.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    ImageButton Signup;
    EditText UserName, Password, Email, PhoneNo , Address;
    String UserName_Holder, Password_Holder, Email_Holder, PhoneNo_Holder , Address_holder;
    Boolean CheckEditText ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Assign Id'S
        UserName = (EditText)findViewById(R.id.username);
        Password = (EditText)findViewById(R.id.password);
        Email = (EditText)findViewById(R.id.email);
        PhoneNo = (EditText)findViewById(R.id.phone);
        Address = (EditText)findViewById(R.id.address);
        Signup = (ImageButton)findViewById(R.id.signup);




        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Checking whether EditText is Empty or Not
                CheckEditTextIsEmptyOrNot();

                if(CheckEditText){


                    // If EditText is not empty and CheckEditText = True then this block will execute.
                    // If EditText is empty then this block will execute .
                    Toast.makeText(MainActivity.this, "User Signed Up", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(MainActivity.this,Home.class);
                    startActivity(intent);




                }
                else {

                    // If EditText is empty then this block will execute .
                    Toast.makeText(MainActivity.this, "Please fill all the fields.", Toast.LENGTH_LONG).show();

                }



            }
        });


    }

    public void CheckEditTextIsEmptyOrNot(){

        UserName_Holder = UserName.getText().toString();
        Password_Holder = Password.getText().toString();
        Email_Holder = Email.getText().toString();
        PhoneNo_Holder = PhoneNo.getText().toString();
        Address_holder = Address.getText().toString();


        if(TextUtils.isEmpty(UserName_Holder) || TextUtils.isEmpty(Password_Holder) || TextUtils.isEmpty(Email_Holder) || TextUtils.isEmpty(PhoneNo_Holder) || TextUtils.isEmpty(Address_holder))
        {

            CheckEditText = false;

        }
        else {

            if(UserName_Holder.length()<4){
                Toast.makeText(MainActivity.this, "Username should be more than 4 Characters", Toast.LENGTH_LONG).show();
            }
            else if(Address_holder.length()<10){
                Toast.makeText(MainActivity.this, "Address should be more than 10 Characters.", Toast.LENGTH_LONG).show();
            }
            else if (isValid(Email_Holder)){
                Toast.makeText(MainActivity.this, "Invalid Email Address.", Toast.LENGTH_LONG).show();
            }
            else if(PhoneNo_Holder.length()>10 || PhoneNo_Holder.length()<10){
                Toast.makeText(MainActivity.this, "Invalid Phone Number", Toast.LENGTH_LONG).show();
            }
            else {
                CheckEditText = true ;
            }

        }

    }


    public static boolean isValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }


}