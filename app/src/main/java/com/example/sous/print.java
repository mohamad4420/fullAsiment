package com.example.sous;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
public class print extends AppCompatActivity {
    RecyclerView recyclerViewprint;
    public SQLiteDatabase db;
    public dataBaceClass data;
    String s[][];
    public void onBackPressed() {

        db.close();

        super.onBackPressed ();
    }
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
        setContentView(R.layout.activity_print);
        recyclerViewprint=findViewById(R.id.recyclerView);
        db=openOrCreateDatabase ( "firstDB",MODE_PRIVATE,null );
        db.execSQL ( "create table if not exists emp1 ( id varchar primary key,name varchar , sex varchar , BaseSalaryDB float , TotalSalesDB float,CommissionRate float);" );
        data=new dataBaceClass();
        s= data.print(db);
        recylerViewViewHolder RVH=new recylerViewViewHolder(this,s,s[0].length);
        recyclerViewprint.setAdapter(RVH);
        recyclerViewprint.setLayoutManager(new LinearLayoutManager(this));

    }
}