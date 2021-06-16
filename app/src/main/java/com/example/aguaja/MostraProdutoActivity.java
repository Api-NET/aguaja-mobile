package com.example.aguaja;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.aguaja.entity.Estoque;
import com.example.aguaja.entity.Produto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MostraProdutoActivity extends AppCompatActivity {
    private String url = DomainName.NAME + "/api/stock/";
    private String urlPost = DomainName.NAME + "/api/order";
    private Button btnAumentaQtd;
    private EditText quantidade;
    private TextView valorPreco;
    private TextView nomeProduto;
    private TextView descricao;
    private TextView litrosProduto;
    private Estoque est;
    private RequestQueue meuQue;
    private JsonObjectRequest jsonObjectRequest;
    private StringRequest stringRequestPost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostra_produto);
        btnAumentaQtd= (Button) findViewById(R.id.aumenta);
        quantidade = (EditText) findViewById(R.id.qtd);
        descricao = (TextView) findViewById(R.id.descriçao);
        Bundle extras = getIntent().getExtras();
        valorPreco = findViewById(R.id.valorPreco);
        nomeProduto = findViewById(R.id.nome);
        litrosProduto = findViewById(R.id.litros);
        meuQue = Volley.newRequestQueue(this);
        Integer idEst = Integer.parseInt( extras.getString("idEst"));
        Double custo = Double.parseDouble(extras.getString("preco"));
        Integer quantidade = Integer.parseInt(extras.getString("quantidade"));


        Integer idProdu = Integer.parseInt(extras.getString("idProd"));
        String nome = extras.getString("nome");
        String descProd = extras.getString("descricao");
        Double litros = Double.parseDouble(extras.getString("litros"));
        Produto produto = new Produto(idProdu,descProd , litros, nome);
        est = new Estoque(idEst, custo, quantidade, produto);

        String[] arDesc = descProd.split(";");


        nomeProduto.setText(produto.getNome());
        valorPreco.setText("Preço: R$"+est.getPrecoVenda());
        litrosProduto.setText(""+produto.getLitros()+"L");
        descricao.setText(arDesc[0]+"\n"
                            +arDesc[1]+"\n"
                            +arDesc[2]+"\n"
                            +arDesc[3]);


    }
    public void aumentaQuantidade(View view) {

        Integer qutd = Integer.parseInt(quantidade.getText().toString());
        if(qutd < est.getQuatidade()){
            quantidade.setText((qutd+1)+"");
            valorPreco.setText("Preço: R$"+(est.getPrecoVenda()*(qutd+1))+"");
        }else {
            Toast toast = Toast.makeText(getApplicationContext(), "Quantidade máxima", Toast.LENGTH_LONG);
            toast.show();
        }

    }
    public void diminuiQuantidade(View view) {
        Integer qutd = Integer.parseInt(quantidade.getText().toString());
        if(qutd > 1){
            quantidade.setText((qutd-1)+"");
            valorPreco.setText("Preço: R$"+(est.getPrecoVenda()*(qutd-1))+"");
        }

    }

    public void compra(View view) {
        stringRequestPost = new StringRequest(Request.Method.POST, urlPost,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        ) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("date", "2020-12-12");
                params.put("discount", String.valueOf(50));
                params.put("order_status", String.valueOf(1));
                params.put("client_id", String.valueOf(2));
                params.put("seller_id", String.valueOf(1));
                params.put("Content-Type", "application/json; charset=utf-8; application/x-www-form-urlencoded");
                params.put("Accept", "application/json; charset=utf-8; application/x-www-form-urlencoded");

                return params;
            }

            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8; application/x-www-form-urlencoded";
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type","application/json");
                return params;
            }



        };
        meuQue.add(stringRequestPost);
        Toast.makeText(getApplicationContext(), "Compra realizada com sucesso!", Toast.LENGTH_LONG).show();
    }
}