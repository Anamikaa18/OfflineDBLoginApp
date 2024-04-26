package com.example.offlinedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Sec extends AppCompatActivity {
    EditText e3,e4,e5;
    Button b3,b4;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec);
        e3=(EditText) findViewById(R.id.editTextText3);
        e4=(EditText) findViewById(R.id.editTextText4);
        e5=(EditText) findViewById(R.id.editTextText5);
        b3=(Button) findViewById(R.id.button3);
        b4=(Button) findViewById(R.id.button4 );
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s3=e3.getText().toString();
                String s4=e4.getText().toString();
                String s5=e5.getText().toString();
                if(s3.equals("")||s4.equals("")||s5.equals("")){
                    Toast.makeText(Sec.this, "Please fill all fields!", Toast.LENGTH_SHORT).show();
                }
                else {
                    SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase("meraDB", MODE_PRIVATE, null);
                    database.execSQL("Create table if not exists student(name varchar,email varchar,phone varchar)");
                    String s6 = "Select * from student where name='" + s3 + "' and email='" + s4 + "';
                    Cursor cursor=database.rawQuery(s6,null);
                    if(cursor.getCount()>0){
                        Toast.makeText(Sec.this, "You are already registered!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        database.execSQL("insert into student value ('"+s3+"','"+s4+"','"+s5+"',)");
                        Toast.makeText(Sec.this, "Congrats! You are registered.", Toast.LENGTH_SHORT).show();
                    }
                }
                Intent i=new Intent(Sec.this,MainActivity.class);
                startActivity(i);
                finish();

            }
        });
    }
}