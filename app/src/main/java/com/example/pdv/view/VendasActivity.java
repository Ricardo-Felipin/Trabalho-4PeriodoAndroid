package com.example.pdv.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pdv.R;
import com.example.pdv.adapter.VendasListAdapter;
import com.example.pdv.controller.VendasController;
import com.example.pdv.model.Vendas;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class VendasActivity extends AppCompatActivity {

    private FloatingActionButton btCadastroAluno;
    private AlertDialog dialog;
    private VendasController controller;
    private EditText edRa;
    private EditText edNome;
    private View viewAlert;
    private RecyclerView rvAlunos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendas);

        controller = new VendasController(this);
        rvAlunos = findViewById(R.id.rvAlunos);
        btCadastroAluno = findViewById(R.id.btCadastroAluno);
        btCadastroAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirCadastro();
            }
        });

        atualizarListaAluno();
    }

    private void abrirCadastro() {
        //Carregando o arquivo xml do layout
        viewAlert = getLayoutInflater()
                .inflate(R.layout.dialog_cadastro_vendas, null);

        edRa = viewAlert.findViewById(R.id.edRa);
        edNome = viewAlert.findViewById(R.id.edNome);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Cadastrar Venda"); //Adicionando título ao popup
        builder.setView(viewAlert); //Setando o layout
        builder.setCancelable(false); //não deixa fechar o popup se clicar fora dele

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("Salvar", null);
        dialog = builder.create();
        //Adicionando ação ao botão salvar após criação da tela
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button bt = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                bt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        salvarDados();
                    }
                });
            }
        });
        dialog.show();

    }

    public void salvarDados(){
        String retorno = controller.salvarVendas(edRa.getText().toString(),
                edNome.getText().toString());
        if(retorno != null){
            if(retorno.contains("RA")){
                edRa.setError(retorno);
                edRa.requestFocus();
            }
            if(retorno.contains("NOME")){
                edNome.setError(retorno);
                edNome.requestFocus();
            }
        }else{
            Toast.makeText(this,
                    "Aluno salvo com sucesso!",
                    Toast.LENGTH_LONG).show();

            dialog.dismiss();
            atualizarListaAluno();
        }
    }

    /**
     * Método cria e atualiza a lista de alunos
     */
    private void atualizarListaAluno(){
        ArrayList<Vendas> listaVendas = controller.retornarVendas();
        VendasListAdapter adapter = new VendasListAdapter(listaVendas, this);
        rvAlunos.setLayoutManager(new LinearLayoutManager(this));
        rvAlunos.setAdapter(adapter);
    }
}