package com.example.ozcanyureklioglu.bordefterim;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

public class musteriEkle extends AppCompatActivity implements View.OnClickListener {


    EditText isimEdit,soyisimEdit,borcEdit;
    Button ekleButton;
    veritabani veri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musteri_ekle);
        veri=new veritabani(getApplicationContext());

        isimEdit=(EditText)findViewById(R.id.isimEdit);
        soyisimEdit=(EditText)findViewById(R.id.soyisimEdit);
        borcEdit=(EditText)findViewById(R.id.borcEdit);
        ekleButton=(Button)findViewById(R.id.ekleButton);

        ekleButton.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.ekleButton){
            if(!isimEdit.getText().toString().matches("") && !soyisimEdit.getText().toString().matches("") && !borcEdit.getText().toString().matches("")){

                double borc=Double.parseDouble(borcEdit.getText().toString());
                veri.ekle(isimEdit.getText().toString(),soyisimEdit.getText().toString(),borc);

            }else{
                Toasty.error(musteriEkle.this, "Lütfen Geçerli Değerler Giriniz!", Toast.LENGTH_SHORT, true).show();
            }


        }

    }
}
