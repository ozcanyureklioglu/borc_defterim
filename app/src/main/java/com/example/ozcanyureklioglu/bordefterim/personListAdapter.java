package com.example.ozcanyureklioglu.bordefterim;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class personListAdapter extends ArrayAdapter<person> {
    private Context context;
    private int mResource;
    public personListAdapter(@NonNull Context context, int resource, @NonNull List<person> objects) {
        super(context, resource, objects);
        this.context=context;
        this.mResource=resource;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        int id=getItem(position).getId();
        String isim=getItem(position).getIsim();
        String soyisim=getItem(position).getSoyisim();
        double borcMiktari=getItem(position).getBorcMiktari();

        person prs=new person(id,isim,soyisim,borcMiktari);
        LayoutInflater inflater=LayoutInflater.from(context);
        convertView=inflater.inflate(mResource,parent,false);

        TextView txt1=(TextView)convertView.findViewById(R.id.textView1);
        TextView txt2=(TextView)convertView.findViewById(R.id.textView2);
        TextView txt3=(TextView)convertView.findViewById(R.id.textView3);

        txt1.setText(isim+" "+soyisim);
        txt2.setText("Müşteri No: "+id);
        txt3.setText("Borç: "+borcMiktari);
        return convertView;
    }


}
