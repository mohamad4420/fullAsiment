package com.example.sous;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class dataBaceClass {
    public  SQLiteDatabase db;
public dataBaceClass(){


}

public boolean checkId(SQLiteDatabase db,String id){
    Cursor rs = db.rawQuery("select *  from emp1  where id = ? ;",
            new String[]{id});
    rs.moveToFirst();
    if (rs.getCount()< 1) {
       return true;
    }



    return false;
}
public String[][] print(SQLiteDatabase db){

    Cursor  rs= db.rawQuery ( "select *  from emp1",null);
    rs.moveToFirst();
    int i=0;
    while (!rs.isAfterLast()){
    if(rs.getString(rs.getColumnIndex("id"))!=null){
        i++;

    }
    rs.moveToNext();
    }
    String d[]=new String [i];
    String s[][]= new String[6][i];
i=0;
    rs.moveToFirst();
    while(!rs.isAfterLast()){
        if(rs.getString(rs.getColumnIndex("id"))!=null){
            s[0][i]=rs.getString(rs.getColumnIndex("id"));
            s[1][i]=rs.getString(rs.getColumnIndex("name"));
            s[2][i]=  rs.getString(rs.getColumnIndex("sex"));
            s[3][i]=""+rs.getFloat(rs.getColumnIndex("BaseSalaryDB"));
            s[4][i]=""+rs.getFloat(rs.getColumnIndex("TotalSalesDB"));
            s[5][i]=""+rs.getFloat(rs.getColumnIndex("CommissionRate"));
        }
        i++;
        rs.moveToNext();

    }
    return s;

}

    public void insert(SQLiteDatabase db,String id, String name, String sex, float BaseSalary, float TotalSales, float  CommissionRate) {


        db.execSQL ("insert into emp1 values (?,?,?,?,?,?);",
                new Object []{id ,
                        name,
                        sex,
                        BaseSalary,
                        TotalSales,
                        CommissionRate}   );

    }
    public String[] search(SQLiteDatabase db, String id){

        String s[]= new String[6];
        Cursor rs = db.rawQuery("select *  from emp1  where id = ? ;",
                new String[]{id});
        rs.moveToFirst();
        s[0] = rs.getString(rs.getColumnIndex("id"));
        s[1] = rs.getString(rs.getColumnIndex("name"));
        s[2] = rs.getString(rs.getColumnIndex("sex"));
        s[3] = "" + rs.getFloat(rs.getColumnIndex("BaseSalaryDB"));
        s[4] = "" + rs.getFloat(rs.getColumnIndex("TotalSalesDB"));
        s[5] = "" + rs.getFloat(rs.getColumnIndex("CommissionRate"));
        return s;

    }
    public boolean  modfay(SQLiteDatabase db,String id, String name, String sex, float BaseSalary, float TotalSales, float  CommissionRate , String ids){

        db.execSQL("update emp1 set id='" + id + "', name='" + name+ "',sex='" + sex + "',BaseSalaryDB='" + BaseSalary +
                "',TotalSalesDB='" + TotalSales + "',CommissionRate='" + CommissionRate + "' where  id = '" + ids + "' ");

return true;


    }
    public boolean delete(SQLiteDatabase db,String id){
        db.execSQL ( "delete from emp1 where  id =?", new String []{id} );

return true;
    }


}