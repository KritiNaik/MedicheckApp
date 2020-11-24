package com.test.app32;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Routine extends AppCompatActivity {
    DatabaseHelper2 myDb;
    EditText edate, eweight, eheight, ebp, epr,ediabetes, eoxy;
    Button btnAddData;
    Button btnViewAll;
    Button btnUpdate;
    Button btnDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine);
        myDb= new DatabaseHelper2(this);

        edate=(EditText)findViewById(R.id.editName) ;
        eweight=(EditText)findViewById(R.id.editName2) ;
        eheight=(EditText)findViewById(R.id.editName3) ;
        ebp=(EditText)findViewById(R.id.editName4) ;
        epr=(EditText)findViewById(R.id.editName5) ;
        ediabetes=(EditText)findViewById(R.id.editName6) ;
        eoxy=(EditText)findViewById(R.id.editName7) ;
        btnAddData=(Button)findViewById(R.id.button);
        btnViewAll=(Button)findViewById(R.id.button2);
        btnUpdate=(Button)findViewById(R.id.button3);
        btnDelete=(Button)findViewById(R.id.button4);
        AddData();
        viewAll();
        UpdateData();
        DeleteData();
    }
    public void DeleteData(){
        btnDelete.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows= myDb.deleteData(edate.getText().toString());
                        if(deletedRows>0)
                            Toast.makeText(Routine.this, "Data deleted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Routine.this, "Data not deleted", Toast.LENGTH_LONG).show();

                    }

                }
        );
    }
    public void UpdateData(){
        btnUpdate.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                    boolean isUpdate= myDb.updateData(edate.getText().toString(), eweight.getText().toString(),eheight.getText().toString(),ebp.getText().toString(),epr.getText().toString(),ediabetes.getText().toString(),eoxy.getText().toString());
                    if(isUpdate==true)
                            Toast.makeText(Routine.this, "Data Updated", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Routine.this, "Not Updated", Toast.LENGTH_LONG).show();

                    }

                    }
        );
    }
    public void AddData(){
            btnAddData.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                   boolean isInserted = myDb.insertData(edate.getText().toString(),
                           eweight.getText().toString(),
                           eheight.getText().toString(),
                           ebp.getText().toString(),
                           epr.getText().toString(),
                           ediabetes.getText().toString(),
                           eoxy.getText().toString()
                           );
                   if(isInserted==true)
                       Toast.makeText(Routine.this, "Data inserted", Toast.LENGTH_LONG).show();
                   else
                       Toast.makeText(Routine.this, "Not inserted", Toast.LENGTH_LONG).show();
                    }
                }
            );
        }
        public void viewAll(){
        btnViewAll.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData();
                        if (res.getCount() == 0) {
                            showMessage("Error", "No data found");

                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while(res.moveToNext()){
                            buffer.append("date:"+ res.getString(0)+"\n");
                            buffer.append("weight:"+ res.getString(1)+"\n");
                            buffer.append("height:"+ res.getString(2)+"\n");
                            buffer.append("blood_pressure:"+ res.getString(3)+"\n");
                            buffer.append("pulse_rate"+ res.getString(4)+"\n");
                            buffer.append("diabetes:"+ res.getString(5)+"\n");
                            buffer.append("oxymeter:"+ res.getString(6)+"\n\n");
                        }
                        showMessage("Data", buffer.toString());
                    }
                    }

        );
        }
        public void showMessage(String title, String Message){
            AlertDialog.Builder builder= new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setTitle(title);
            builder.setMessage(Message);
            builder.show();
        }
    }
