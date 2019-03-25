package com.example.ozcanyureklioglu.bordefterim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class musteriDuzenle extends AppCompatActivity {

    veritabani veri;
    TextView idView;
    EditText isimEdit,soyisimEdit,borcEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musteri_duzenle);
        Intent i=getIntent();
       final String id=i.getStringExtra("musteriId");
        String isim=i.getStringExtra("musteriIsim");
        String soyisim=i.getStringExtra("musteriSoyisim");
        String borc=i.getStringExtra("musteriBorc");
        veri =new veritabani(getApplicationContext());
        idView=(TextView)findViewById(R.id.idView);
        isimEdit=(EditText)findViewById(R.id.isimEdit);
        soyisimEdit=(EditText)findViewById(R.id.soyisimEdit);
        borcEdit=(EditText)findViewById(R.id.borcEdit);

        idView.setText(id+" Numaralı Müşteri");
        isimEdit.setText(isim);
        soyisimEdit.setText(soyisim);
        borcEdit.setText(borc);

        findViewById(R.id.duzenleButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                veri.kayitDuzenle(Integer.parseInt(id),isimEdit.getText().toString(),soyisimEdit.getText().toString(),Double.parseDouble(borcEdit.getText().toString()));

            }
        });




    }
}
