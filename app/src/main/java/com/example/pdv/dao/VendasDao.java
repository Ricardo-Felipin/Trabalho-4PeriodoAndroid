package com.example.pdv.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.pdv.helper.SQLiteDataHelper;
import com.example.pdv.model.Vendas;

import java.util.ArrayList;

public class VendasDao implements IGenericDao<Vendas>{

    //Variavel responsavel por abrir conexão com a BD
    private SQLiteOpenHelper openHelper;

    //Base de Dados
    private SQLiteDatabase baseDados;

    //nome das colunas da tabela;
    private String[]colunas = {"RA", "NOME"};

    //nome da tabela
    private String tabela = "ALUNO";

    //Contexto (view)
    private Context context;

    private static VendasDao instancia;

    public static VendasDao getInstancia(Context context){
        if(instancia == null){
            return instancia = new VendasDao(context);
        }else{
            return instancia;
        }
    }

    private VendasDao(Context context){
        this.context = context;

        //Abrir a conexão com a base de dados
        openHelper = new SQLiteDataHelper(this.context,
                "UNIPAR_BD", null, 1);

        //instanciando a base de dados
        baseDados = openHelper.getWritableDatabase();


    }

    @Override
    public long insert(Vendas obj) {
        try{
            ContentValues valores = new ContentValues();
            valores.put(colunas[0], obj.getRa()); //RA

            valores.put(colunas[1], obj.getNome()); //NOME

            return baseDados.insert(tabela, null, valores);

//            return baseDados.execSQL("INSERT INTO ALUNO (RA, NOME) VALUES " +
//                    "("+obj.getRa()+", "+obj.getNome()+" )");

        }catch (SQLException ex){
            Log.e("UNIPAR", "ERRO: VendasDao.insert() "+ex.getMessage());
        }
        return 0;
    }

    @Override
    public long update(Vendas obj) {
        try{
            ContentValues valores = new ContentValues();
            valores.put(colunas[1], obj.getNome());

            String[]identificador = {String.valueOf(obj.getRa())};

            return baseDados.update(tabela,  valores,
                    colunas[0]+"= ?", identificador);

        }catch (SQLException ex){
            Log.e("UNIPAR", "ERRO: VendasDao.update() "+ex.getMessage());
        }
        return 0;
    }

    @Override
    public long delete(Vendas obj) {
        try{
            String[]identificador = {String.valueOf(obj.getRa())};

            return baseDados.delete(tabela,
                    colunas[0]+"= ?", identificador);
        }catch (SQLException ex){
            Log.e("UNIPAR", "ERRO: VendasDao.delete() "+ex.getMessage());
        }
        return 0;
    }

    @Override
    public ArrayList<Vendas> getAll() {
        ArrayList<Vendas> lista = new ArrayList<>();
        try{
            Cursor cursor = baseDados.query(tabela,
                    colunas, null,
                    null, null,
                    null, colunas[0]+" desc");

            if(cursor.moveToFirst()){
                do{
                    Vendas vendas = new Vendas();
                    vendas.setRa(cursor.getInt(0));
                    vendas.setNome(cursor.getString(1));

                    lista.add(vendas);

                }while (cursor.moveToNext());
            }

        }catch (SQLException ex){
            Log.e("UNIPAR", "ERRO: VendasDao.getAll() "+ex.getMessage());
        }

        return lista;
    }

    @Override
    public Vendas getById(int id) {
        try{
            String[]identificador = {String.valueOf(id)};
            Cursor cursor = baseDados.query(tabela, colunas,
                    colunas[0]+"= ?", identificador,
                    null, null, null);

            if(cursor.moveToFirst()){
                Vendas aluno = new Vendas();
                aluno.setRa(cursor.getInt(0));
                aluno.setNome(cursor.getString(1));

                return aluno;
            }

        }catch (SQLException ex){
            Log.e("UNIPAR", "ERRO: VendasDao.getById() "+ex.getMessage());
        }
        return null;
    }
}
