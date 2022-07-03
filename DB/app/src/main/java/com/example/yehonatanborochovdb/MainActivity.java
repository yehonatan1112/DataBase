package com.example.yehonatanborochovdb;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText firstname,lastname,email,id,dob,password;
    Button insert,update,delete,view;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstname=findViewById(R.id.firstname);
        lastname=findViewById(R.id.lastname);
        email=findViewById(R.id.email);
        id=findViewById(R.id.id);
        dob=findViewById(R.id.dob);
        password=findViewById(R.id.password);

        insert=findViewById(R.id.btnInsert);
        update=findViewById(R.id.btnUpdate);
        delete=findViewById(R.id.btnDelete);
        view=findViewById(R.id.btnView);
        DB=new DBHelper(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstnameTXT=firstname.getText().toString();
                String lastnameTXT=lastname.getText().toString();
                String emailTXT=email.getText().toString();
                String idTXT=id.getText().toString();
                String dobTXT=dob.getText().toString();
                String passwordTXT=password.getText().toString();

                Boolean checkinsertdata=DB.insertuserdata(firstnameTXT,lastnameTXT,emailTXT,idTXT,dobTXT,passwordTXT);
                if(checkinsertdata==true)
                    Toast.makeText(MainActivity.this,"New Entry Insert",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this,"New Entry Not Insert",Toast.LENGTH_SHORT).show();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstnameTXT=firstname.getText().toString();
                String lastnameTXT=lastname.getText().toString();
                String emailTXT=email.getText().toString();
                String idTXT=id.getText().toString();
                String dobTXT=dob.getText().toString();
                String passwordTXT=password.getText().toString();

                Boolean checkupdatedata=DB.updateuserdata(firstnameTXT,lastnameTXT,emailTXT,idTXT,dobTXT,passwordTXT);
                if(checkupdatedata==true)
                    Toast.makeText(MainActivity.this,"Entry Update",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this,"Entry Not Updated",Toast.LENGTH_SHORT).show();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstnameTXT=firstname.getText().toString();

                Boolean checkdeletedata=DB.deletedata(firstnameTXT);
                if(checkdeletedata==true)
                    Toast.makeText(MainActivity.this,"Entry Deleted",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this,"Entry Not Deleted",Toast.LENGTH_SHORT).show();
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res=DB.getdata();
                if(res.getCount()==0){
                    Toast.makeText(MainActivity.this,"No Entry Exist",Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while(res.moveToNext())
                {
                    buffer.append("User:"+"\n");
                    buffer.append("First Name :"+res.getString(0)+"\n");
                    buffer.append("Last Name :"+res.getString(1)+"\n");
                    buffer.append("Email :"+res.getString(2)+"\n");
                    buffer.append("ID :"+res.getString(3)+"\n");
                    buffer.append("Date of Birth :"+res.getString(4)+"\n");
                    buffer.append("Password :"+res.getString(5)+"\n");
                    buffer.append("\n");
                }
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }
}