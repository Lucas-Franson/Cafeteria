package com.uniso.lpdm.cafeteria_cap7;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "cafeteria_completo";
    private static final int DB_VERSION = 2;

    DatabaseHelper(Context context){

        super(context, DB_NAME, null, DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        atualizarBanco(db, 0, DB_VERSION);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        atualizarBanco(db, oldVersion, newVersion);

    }

    public static void insertBebida(SQLiteDatabase db, String nome, String descricao,
                                    int imagem_resource_id){
        ContentValues bebida = new ContentValues();
        bebida.put("nome", nome);
        bebida.put("descricao", descricao);
        bebida.put("imagem_resource_id", imagem_resource_id);
        db.insert("BEBIDA", null, bebida);
    }

    private void atualizarBanco(SQLiteDatabase db, int oldVersion, int newVersion){
        String sql;
        Log.d("entrrou", "######################-------ENTROU ####################---------------");

        if(oldVersion < 1){

            sql = "CREATE TABLE BEBIDA (" +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nome TEXT, " +
                    "descricao TEXT, " +
                    "imagem_resource_id INTEGER" +
                    ");";

            db.execSQL(sql);

            insertBebida(db, "Latte", "Um cafe com leite", R.drawable.latte);
            insertBebida(db, "Cappuccino", "Um Cappuccino", R.drawable.cappuccino);
            insertBebida(db, "Filtrado", "Um cafe filtrado", R.drawable.filtrado);
        }

        if (oldVersion <= 2){
            sql = "ALTER TABLE BEBIDA ADD COLUMN favorita NUMERIC;";
            db.execSQL(sql);
        }

    }
}
