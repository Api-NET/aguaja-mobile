package com.example.aguaja;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aguaja.entity.Estoque;

import java.util.List;

public class ListaEstoqueAdapter extends BaseAdapter {

    private Context context;
    private List<Estoque> listEstoque;

    public ListaEstoqueAdapter(Context context, List<Estoque> listEstoque) {
        this.context = context;
        this.listEstoque = listEstoque;
    }

    @Override
    public int getCount() {
        return listEstoque.size();
    }

    @Override
    public Object getItem(int position) {
        return listEstoque.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(context, R.layout.item_estoque, null);

        TextView tvNome = (TextView) v.findViewById(R.id.nome);
        TextView tvLitros = (TextView) v.findViewById(R.id.litros);
        TextView tvPreco = (TextView) v.findViewById(R.id.preco);
        ImageView imagem = (ImageView) v.findViewById(R.id.imagem);
        TextView tvQuantidade = (TextView) v.findViewById(R.id.quantidade);


        tvNome.setText(listEstoque.get(position).getProduto().getNome());
        tvLitros.setText(String.valueOf(listEstoque.get(position).getProduto().getLitros())+"L");
        tvPreco.setText("R$" + String.valueOf(listEstoque.get(position).getPrecoVenda()));
        tvQuantidade.setText(String.valueOf(listEstoque.get(position).getQuatidade()) + " Unidades");
        imagem.setImageResource(R.drawable.garrafao);

        v.setTag(listEstoque.get(position).getId());

        return v;
    }
}
