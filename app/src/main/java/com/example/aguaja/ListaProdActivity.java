package com.example.aguaja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.Transliterator;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.aguaja.entity.Estoque;
import com.example.aguaja.entity.Produto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.chrono.JapaneseChronology;
import java.util.ArrayList;
import java.util.List;

public class ListaProdActivity extends AppCompatActivity {
    List<String> estStrings = new ArrayList<>();
    List<Produto> produtos = Produto.criaVarios();
    List<Integer>  idEstGl = new ArrayList<>();
    ListView estoqueListaView;
    ListaEstoqueAdapter adapter;
    List<Estoque> listEstoque = new ArrayList<>();

    private String urlEstoque = DomainName.NAME + "/api/stock";

    private RequestQueue meuQue;
    private JsonArrayRequest jsonArrayRequestEstoque;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_prod);
        meuQue = Volley.newRequestQueue(this);
        estoqueListaView= (ListView) findViewById(R.id.lista);
        jsonArrayRequestEstoque = new JsonArrayRequest(
                Request.Method.GET,
                urlEstoque,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {

                            for(int i = 0; i<response.length(); i++){
                                JSONObject obj = response.getJSONObject(i);
                                Integer idEst = Integer.parseInt(obj.getString("id"));
                                Double custo = Double.parseDouble(obj.getString("cost_sell"));
                                Integer quantidade = Integer.parseInt(obj.getString("quantity"));
                                Estoque est = new Estoque(idEst, custo, quantidade, produtos.get(i));
                                idEstGl.add(idEst);
                                System.out.println(est.getPrecoVenda());
                                listEstoque.add(est);
                                estStrings.add("\nPreço: R$"+ custo + "\n"
                                                + "Nome: "+ produtos.get(i).getNome() + "\n"
                                                + "Litros: "+ produtos.get(i).getLitros() + "L\n"
                                                + "Descrição: \n"+ produtos.get(i).getDescricao());
                            }

                            estoqueListaView.setAdapter(adapter);
                        }catch(JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        meuQue.add(jsonArrayRequestEstoque);


        adapter = new ListaEstoqueAdapter(getApplicationContext(), listEstoque);
        estoqueListaView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Estoque estoque = listEstoque.get(position);
                Bundle parametros = new Bundle();
                Intent intent = new Intent(getApplicationContext(), MostraProdutoActivity.class);
                parametros.putString("idEst", ""+estoque.getId());
                parametros.putString("idProd", ""+(estoque.getProduto().getId()-1));
                intent.putExtras(parametros);
            System.out.println("Estoque na posição: "+position+" : "+listEstoque.get(position));
                Toast toast = Toast.makeText(getApplicationContext(), ""+position +" ----- " + estoque.getId(), Toast.LENGTH_LONG);
                toast.show();
                startActivity(intent);
            }
        });

    }


}