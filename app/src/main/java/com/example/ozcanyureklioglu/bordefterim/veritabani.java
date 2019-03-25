package com.example.ozcanyureklioglu.bordefterim;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import es.dmoral.toasty.Toasty;


public class veritabani extends SQLiteOpenHelper {
    SQLiteDatabase database;
    public static String dbName="database";
    Context cx;


    public veritabani(Context context) {
        super(context,dbName,null,1);
        cx=context;
    }

    public void kontrolEt(){//onCreate methodunda borcu olmayan müşterileri siler.
        database=this.getWritableDatabase();
       if(database.delete("veriler","borcMiktar=?",new String[]{"0.0"})>0){
           Toasty.success(cx, "Borcu olmayan müşteri silindi", Toast.LENGTH_SHORT, true).show();
       }
        database.close();
    }

    public String[] musteriKontrolEt(int id){
        database=this.getWritableDatabase();
        String[] musteri;
        Cursor cursor=database.rawQuery("select* from veriler where ID="+id,null);
        if(cursor.moveToNext()) {
            musteri = new String[]{cursor.getInt(0) + "", cursor.getString(1), cursor.getString(2), cursor.getDouble(3) + ""};
        }else{
            musteri=null;
        }
        return musteri;

    }
    public void ekle(String isim,String soyisim,double borcMiktari){// yeni bir müşteri ekler
        database=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        //values.put("id",id);
        values.put("isim",isim);
        values.put("soyisim",soyisim);
        values.put("borcMiktar",borcMiktari);

        if(database.insert("veriler",null,values)>-1){
            Toasty.success(cx, "Kayıt Başarılı", Toast.LENGTH_SHORT, true).show();

        }
        else{
            Toast.makeText(cx, "Hata Var.", Toast.LENGTH_SHORT).show();

        }
        database.close();

    }

    public void kayitDuzenle(int id,String isim,String soyisim,double borc){
        database=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("isim",isim);
        values.put("soyisim",soyisim);
        values.put("borcMiktar",borc);

        if(database.update("veriler",values,"ID=?",new String[]{String.valueOf(id)})>-1){
            Toasty.success(cx, "Düzenleme Başarılı", Toast.LENGTH_SHORT, true).show();

        }


    }

    public void kayitsil(int id){//Müşteri siler.
        database=this.getWritableDatabase();
        if(database.delete("veriler","id=?",new String[]{id+""})>0){
            Toasty.success(cx, "Silme Başarılı", Toast.LENGTH_SHORT, true).show();
        }
        else{
            Toasty.error(cx, "Böyle Kayıt Yok", Toast.LENGTH_SHORT, true).show();
        }
        database.close();
    }
    public ArrayList<person> getData(){
        ArrayList<person> liste=new ArrayList<person>();
        database=this.getWritableDatabase();
        Cursor cursor=database.rawQuery("select * from veriler",null);
        while(cursor.moveToNext()){
            liste.add(new person(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getDouble(3)));
            //liste.add(cursor.getInt(0)+" "+cursor.getString(1)+" "+cursor.getString(2)+" "+cursor.getInt(3));
        }

        return liste;
    }
    /*Veritabanında tablo oluşturma işlemleri*/
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sorgu="create table veriler(ID INTEGER PRIMARY KEY AUTOINCREMENT,isim text,soyisim text,borcMiktar double)";
        db.execSQL(sorgu);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists veriler");
        onCreate(db);
    }




}
