package com.example.sous;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class recylerViewViewHolder extends RecyclerView.Adapter<recylerViewViewHolder.MyViewHolder> {

   String data[][];
   String data1[];
   Context context;
   int i;
int what;
    public recylerViewViewHolder(Context ct, String  s1[][], int count){
context=ct;
data=s1;

i=count;


    }
    public recylerViewViewHolder(Context ct, String  s2[], int count,int m){
        context=ct;
        data1=s2;
what=m;
        i=count;


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from((context));
     View view=  inflater.inflate(R.layout.recyclelist,parent,false);
        return new MyViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.text1.setText(" ID="+data[0][position]);
        holder.text2.setText("NAME="+data[1][position]);
        holder.text3.setText("SEX="+data[2][position]);
        holder.text4.setText( "BaseSalary="+data[3][position]);
        holder.text5.setText("TotalSales="+data[4][position]);
        holder.text6.setText("CommissionRate="+data[5][position]);

    }
    @Override
    public int getItemCount() {
        return i;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
TextView text1,text2,text3,text4,text5,text6;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            text1=itemView.findViewById(R.id.idprint);
            text2=itemView.findViewById(R.id.nameprint);
            text3=itemView.findViewById(R.id.sexprint);
            text4=itemView.findViewById(R.id.BaseSalaryprint);
            text5=itemView.findViewById(R.id.TotalSalesprint);
            text6=itemView.findViewById(R.id.CommissionRateprint);
        }
    }
}
