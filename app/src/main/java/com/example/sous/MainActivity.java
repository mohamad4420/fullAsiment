package com.example.sous;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.method.DigitsKeyListener;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public Button add,clean,close;
    public TextView id, name, BaseSalary, TotalSales, CommissionRate;
    public RadioGroup radiosex;
    public SQLiteDatabase db;

    public dataBaceClass data;

    private RadioButton radioSexButton;
    public boolean checkText(TextView one,TextView two,TextView three ,TextView four,TextView five ){
        if(one.getText().toString().isEmpty()){

            one.setError("Please enter the ID");
return false;
        }
        if(two.getText().toString().isEmpty()){

            two.setError("Please enter a Name");
            return false;
        }

        if(three.getText().toString().isEmpty()){

            three.setError("Please enter Basic salary");
            return false;
        }

        if(four.getText().toString().isEmpty()){

            four.setError("Please enter the total sales");
            return false;
        }


        if(five.getText().toString().isEmpty()){

            one.setError("Please enter the commission rate");
            return false;
        }


        return true;

    }
    @Override
    public void onBackPressed() {

      db.close();
        super.onBackPressed ();
    }
    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inf = getMenuInflater();
        inf.inflate(R.menu.menu1, menu);

        if (menu != null && menu instanceof MenuBuilder)
            ((MenuBuilder) menu).setOptionalIconsVisible(true);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {


        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {


        return super.onMenuOpened(featureId, menu);
    }

    @Override
    public void onOptionsMenuClosed(Menu menu) {
        super.onOptionsMenuClosed(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        if (item.getItemId() == R.id.add) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivityForResult(intent, 0);
        }
        if (item.getItemId() == R.id.search) {
            Intent intent = new Intent(this, search.class);
            intent.putExtra("search", "s");
            startActivityForResult(intent, 0);

        }
        if (item.getItemId() == R.id.modify) {
            Intent intent = new Intent(this, search.class);
            intent.putExtra("modfiy", "s");
            startActivityForResult(intent, 0);
        }
        if (item.getItemId() == R.id.print) {
            Intent intent = new Intent(this, print.class);

            startActivityForResult(intent, 0);


        }
        if (item.getItemId() == R.id.delete) {
            Intent intent = new Intent(this, search.class);
            intent.putExtra("delete", "s");

            startActivityForResult(intent, 0);


        }
        if (item.getItemId() == R.id.Close) {

            onBackPressed();

        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        add = findViewById(R.id.addButton);
        clean = findViewById(R.id.cleanButton);
        close = findViewById(R.id.closeButton);
        radiosex = findViewById(R.id.sex);
        data = new dataBaceClass();
        id = findViewById(R.id.IDString);
        name = findViewById(R.id.nameString);
        BaseSalary = findViewById(R.id.basicSalaryString);
        TotalSales = findViewById(R.id.totalSalaryString);
        CommissionRate = findViewById(R.id.commissionRateString);
        add.setOnClickListener(this);
        clean.setOnClickListener(this);
        close.setOnClickListener(this);
        BaseSalary.setKeyListener ( DigitsKeyListener.getInstance ( "0123456789." ) );
        TotalSales.setKeyListener ( DigitsKeyListener.getInstance ( "0123456789." ) );
        CommissionRate.setKeyListener ( DigitsKeyListener.getInstance ( "0123456789." ) );
        db = openOrCreateDatabase("firstDB", MODE_PRIVATE, null);
        db.execSQL("create table if not exists emp1 ( id varchar primary key,name varchar , sex varchar , BaseSalaryDB float , TotalSalesDB float,CommissionRate float);");


    }


    @Override
    public void onClick(View v) {
        int selectedId = radiosex.getCheckedRadioButtonId();
        radioSexButton = (RadioButton) findViewById(selectedId);
        if (v instanceof  Button)
        {  if (v==close) {
            onBackPressed();
        }

            if (clean==v) {
                id.setText("");
                name.setText("");
                BaseSalary.setText("");
                TotalSales.setText("");
                CommissionRate.setText("");


            }
            if (v == add) {


                if(data.checkId(db,id.getText().toString())){

                    if (checkText(id , name,  BaseSalary, TotalSales, CommissionRate)) {
                        data.insert(db, id.getText().toString(), name.getText().toString(), radioSexButton.getText().toString(), Float.parseFloat(BaseSalary.getText().toString()), Float.parseFloat(TotalSales.getText().toString()), Float.parseFloat(CommissionRate.getText().toString()));
                        id.setText("");
                        name.setText("");

                        BaseSalary.setText("");
                        TotalSales.setText("");
                        CommissionRate.setText("");}



                }
                else {

                    id.setError("this id is exsest");

                }
            }
        }





    }


}
