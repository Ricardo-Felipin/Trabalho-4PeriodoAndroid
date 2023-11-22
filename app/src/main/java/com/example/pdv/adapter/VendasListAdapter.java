package com.example.pdv.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pdv.R;
import com.example.pdv.model.Vendas;

import java.util.ArrayList;

public class VendasListAdapter extends
        RecyclerView.Adapter<VendasListAdapter.ViewHolder>{

        private ArrayList<Vendas> listaVendas;
        private Context context;

    public VendasListAdapter(ArrayList<Vendas> listaVendas, Context context) {
            this.listaVendas = listaVendas;
            this.context = context;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View listItem = inflater.inflate(R.layout.item_list_vendas,parent, false);

            return new ViewHolder(listItem);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Vendas vendaSelecionada = listaVendas.get(position);
            holder.tvRa.setText(String.valueOf(vendaSelecionada.getRa()));
            holder.tvNome.setText(vendaSelecionada.getNome());
        }

        @Override
        public int getItemCount() {
            return this.listaVendas.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{

            public TextView tvRa;
            public TextView tvNome;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                this.tvRa = itemView.findViewById(R.id.tvRa);
                this.tvNome = itemView.findViewById(R.id.tvNome);
            }
        }


    }
