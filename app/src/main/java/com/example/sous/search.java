package com.example.sous;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class search extends AppCompatActivity implements View.OnClickListener {
    public SQLiteDatabase db;
    public TextView Serchfeld;
    public Button but;
    public String[] sa;
    public dataBaceClass data;

    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inf=getMenuInflater ();
        inf.inflate ( R.menu.menu1 ,menu);

        if (menu!=null && menu instanceof MenuBuilder)
            ((MenuBuilder)menu).setOptionalIconsVisible ( true );

        return super.onCreateOptionsMenu ( menu );
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {



        return super.onPrepareOptionsMenu ( menu );
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {


        return super.onMenuOpened ( featureId, menu );
    }

    @Override
    public void onOptionsMenuClosed(Menu menu) {
        super.onOptionsMenuClosed ( menu );
    }
    public void onBackPressed() {

        db.close();
        super.onBackPressed ();
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        if (item.getItemId ()==R.id.add)
        {
            Intent intent=new Intent(this,MainActivity.class);
            startActivityForResult ( intent ,0);
        }
        if (item.getItemId ()==R.id.search)
        {
            Intent intent=new Intent(this,search.class);
            intent.putExtra("search","s");
            startActivityForResult ( intent ,0);

        }
        if (item.getItemId ()==R.id.modify)
        {
            Intent intent=new Intent(this,search.class);
            intent.putExtra("modfiy","s");
            startActivityForResult ( intent ,0);
        }
        if (item.getItemId ()==R.id.print)
        {
            Intent intent=new Intent(this,print.class);

            startActivityForResult ( intent ,0);


        }
        if (item.getItemId() == R.id.delete) {
            Intent intent = new Intent(this, search.class);
            intent.putExtra("delete", "s");

            startActivityForResult(intent, 0);


        }
        if (item.getItemId() == R.id.Close) {

            onBackPressed();

        }


        return super.onOptionsItemSelected ( item );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Serchfeld=findViewById(R.id.searchfild);
        but=findViewById(R.id.button2);
        but.setOnClickListener(this);
        data=new dataBaceClass();
        Intent kk=getIntent ();
        db=openOrCreateDatabase ( "firstDB", MODE_PRIVATE,null );
        db.execSQL ( "create table if not exists emp1 ( id varchar primary key,name varchar , sex varchar , BaseSalaryDB float , TotalSalesDB float,CommissionRate float);" );

        if( kk.hasExtra("search")){
            but.setText("search");
        }
        else if(kk.hasExtra("modfiy")){

            but.setText("modify");
        }
        else
            but.setText("delete");



    }

    @Override
    public void onClick(View v) {
        sa = new String[6];


     if (v == but) {



            if(data.checkId(db,Serchfeld.getText().toString())==true){
                Serchfeld.setError("this id not found");

            }
            else if (but.getText() == "search") {
                sa=data.search(db,Serchfeld.getText().toString());
                Intent intent = new Intent(this, modify.class);
                intent.putExtra("search",sa);
                startActivityForResult(intent, 0);

            } else if(but.getText() == "modify"){
                sa=data.search(db,Serchfeld.getText().toString());
                Intent intent = new Intent(this, modify.class);
                intent.putExtra("modify", sa);
                startActivityForResult(intent, 0);
            }
            else{ if(data.delete(db,Serchfeld.getText().toString())==true){
                Toast.makeText ( this, " This deletion has been successful ",Toast.LENGTH_LONG ).show ();


            }


            }


        }
    }
}
