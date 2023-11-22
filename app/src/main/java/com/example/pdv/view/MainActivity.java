package com.example.pdv.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.pdv.R;
import com.example.pdv.dao.VendasDao;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VendasDao.getInstancia(this);
    }

    public void abrirCadastroVendas(View view) {
        Intent intent = new Intent(MainActivity.this,
                VendasActivity.class);

        startActivity(intent);

    }
}