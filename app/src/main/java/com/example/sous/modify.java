package com.example.sous;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class modify extends AppCompatActivity implements View.OnClickListener, View.OnKeyListener {
    public SQLiteDatabase db;
    dataBaceClass data;
    public TextView idtext, nametext, sextext,  BaseSalarytetx, TotalSalestext, CommissionRatetext, id, name, sex,BaseSalary , TotalSales,CommissionRate;
    public Button what;
    public RadioGroup mod;
    String[] a;
    public String ids;
    public  RadioButton  radioSexButton ,mali,fimal;
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
    public void onPointerCaptureChanged(boolean hasCapture) {

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

            finish();

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);
        idtext = findViewById(R.id.textView11);
        nametext = findViewById(R.id.textView5);
        sextext = findViewById(R.id.textView12);
        BaseSalarytetx = findViewById(R.id.textView13);
        TotalSalestext    = findViewById(R.id.textView14);
        CommissionRatetext = findViewById(R.id.textView15);
        id = findViewById(R.id.editTextTextPersonName5);
        name = findViewById(R.id.editTextTextPersonName6);
        mod=findViewById(R.id.sexmodfi);
        mod.setOnClickListener(this);
        BaseSalary = findViewById(R.id.editTextTextPersonName4);
        TotalSales = findViewById(R.id.editTextTextPersonName7);
        CommissionRate = findViewById(R.id.editTextTextPersonName8);
        what = findViewById(R.id.button);
        what.setOnClickListener(this);
        mali=findViewById(R.id.male1);
        fimal=findViewById(R.id.female2);

data=new dataBaceClass();

        Intent kk = getIntent();


        if (kk.hasExtra("search")) {
            a = kk.getStringArrayExtra("search");

            what.setVisibility(View.INVISIBLE);

            id.setText(a[0]);
            name.setText(a[1]);
            if(a[2]=="Male"){

                fimal.setChecked(true);
                mali.setChecked(false);

            }
            else{

                fimal.setChecked(true);
                mali.setChecked(false);
            }

            BaseSalary.setText(a[3]);
            TotalSales.setText(a[4]);
            CommissionRate.setText(a[5]);
            id.setEnabled(false);
            name.setEnabled(false);
            mod.setEnabled(false);
            mali.setEnabled(false);
            fimal.setEnabled(false);
            BaseSalary.setEnabled(false);
            TotalSales.setEnabled(false);
            CommissionRate.setEnabled(false);
            idtext.setText(" id");
            nametext.setText(" name ");
            sextext.setText("sex");
            BaseSalarytetx.setText("BaseSalary");
            TotalSalestext.setText("TotalSales");
            CommissionRatetext.setText("CommissionRate");


        } else if (kk.hasExtra("modify")) {
            a = kk.getStringArrayExtra("modify");
            what.setVisibility(View.VISIBLE);

            id.setText(a[0]);
            ids = id.getText().toString();
            name.setText(a[1]);

            if(a[2].equals("Male")){

                fimal.setChecked(true);
                mali.setChecked(false);
            }
            else{

                fimal.setChecked(true);
                mali.setChecked(false);
            }
            BaseSalary.setText(a[3]);
            TotalSales.setText(a[4]);
            CommissionRate.setText(a[5]);

        }


    }

    @Override
    public void onClick(View v) {
        int selectedId = mod.getCheckedRadioButtonId();
        radioSexButton = (RadioButton) findViewById(selectedId);

        db = openOrCreateDatabase("firstDB", MODE_PRIVATE, null);
        db.execSQL("create table if not exists emp1 ( id varchar primary key,name varchar , sex varchar , BaseSalaryDB float , TotalSalesDB float,CommissionRate float);");


       if(   data.modfay(db,id.getText().toString(),name.getText().toString(),radioSexButton.getText().toString(), Float.parseFloat(BaseSalary.getText().toString()),  Float.parseFloat(TotalSales.getText().toString())
                  ,Float.parseFloat(CommissionRate.getText().toString()),ids)==true){

           Toast.makeText ( this, " This modify has been successful ",Toast.LENGTH_LONG ).show ();

       }



    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        return false;
    }
}
