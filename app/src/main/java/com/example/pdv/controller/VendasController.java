package com.example.pdv.controller;

import android.content.Context;

import com.example.pdv.dao.VendasDao;
import com.example.pdv.model.Vendas;

import java.util.ArrayList;

public class VendasController {

    private Context context;

    public VendasController(Context context) {
        this.context = context;
    }

    public String salvarVendas(String ra, String nome){
        try{
            if(ra.equals("") || ra.isEmpty()){
                return "Informe o RA do Aluno!";
            }
            if(nome.equals("") || nome.isEmpty()){
                return "Informe o NOME do Aluno!";
            }

            Vendas vendas = VendasDao.getInstancia(context)
                    .getById(Integer.parseInt(ra));
            if(vendas != null){
                return "O RA ("+ra+") já está cadastrado!";
            }else{
                vendas = new Vendas();
                vendas.setRa(Integer.parseInt(ra));
                vendas.setNome(nome);

                VendasDao.getInstancia(context).insert(vendas);
            }

        }catch (Exception ex){
            return "Erro ao Gravar Aluno.";
        }
        return null;
    }

    /**
     * Retorna todos os alunos cadastrados no banco
     * @return
     */
    public ArrayList<Vendas> retornarVendas(){
        return VendasDao.getInstancia(context).getAll();
    }

}
