package com.example.ozcanyureklioglu.bordefterim;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button ekleButton,duzenleButton;
    veritabani veri;
    ListView liste;
    EditText dznEdit;

    @Override
    protected void onResume() {
        super.onResume();
        veri.kontrolEt();
        doldur();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         final EditText duzenleEdit=(EditText)findViewById(R.id.duzenleEdit);
         dznEdit=duzenleEdit;
        veri=new veritabani(this);
        ekleButton=(Button)findViewById(R.id.ekleButton);
        duzenleButton=(Button)findViewById(R.id.duzenleButton);
        ekleButton.setOnClickListener(MainActivity.this);
        duzenleButton.setOnClickListener(this);

        veri.kontrolEt();
        doldur();
        registerForContextMenu(liste);
        liste.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
                    person pr=(person)liste.getItemAtPosition(info.position);

                menu.setHeaderTitle(pr.getIsim()+" "+pr.getSoyisim());
                menu.add(0, 0, 0, "Sil");
                menu.add(0, 1, 0, "Düzenle");
                menu.add(0, 2, 0, "Detaylı Bilgi");



            }
        });

        liste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                person p=(person)parent.getAdapter().getItem(position);
                duzenleEdit.setText(""+p.getId());


            }
        });



    }
    public boolean onContextItemSelected(MenuItem item)
    {
        if(item.getItemId()==0){
            Toasty.info(getApplicationContext(),"Birinci Öğeye Tıklandı..",Toast.LENGTH_SHORT, true).show();

        }

        return true;
    }

    public void doldur(){
        liste=(ListView)findViewById(R.id.liste);
        ArrayList<person> arrayList=veri.getData();
        personListAdapter adapter=new personListAdapter(getApplicationContext(),R.layout.android_layout_view,arrayList);
        liste.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==ekleButton.getId()){
            Intent i =new Intent(MainActivity.this,musteriEkle.class);
            startActivity(i);
        }
         if(v.getId()==duzenleButton.getId() && !dznEdit.getText().toString().matches("")){
            String[] dizi=veri.musteriKontrolEt(Integer.parseInt(dznEdit.getText().toString()));
            if(dizi!=null){
                    Intent i=new Intent(getApplicationContext(),musteriDuzenle.class);
                    i.putExtra("musteriId",dizi[0]);
                    i.putExtra("musteriIsim",dizi[1]);
                    i.putExtra("musteriSoyisim",dizi[2]);
                    i.putExtra("musteriBorc",dizi[3]);
                    startActivity(i);

             }



        }

    }
}
