package com.example.aguaja;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
    private String urlPost = DomainName.NAME + "/api/order/";
    Button btnAumentaQtd;
    EditText quantidade;
    TextView valorPreco;
    TextView nomeProduto;
    TextView descricao;
    TextView litrosProduto;
    Estoque est;
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
        Integer idProd = Integer.parseInt( extras.getString("idProd"));

        List<Produto> produtos = Produto.criaVarios();
        if (extras!= null){
            url += idEst;
        }else{
            System.out.println("Id nulo-------------------------------------------------------");
        }

        System.out.println("---------------------------------------------------"+url);

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            String texto;
                            Integer id = Integer.parseInt(response.getString("id"));
                            Double custo = Double.parseDouble(response.getString("cost_sell"));
                            Integer quantidade = Integer.parseInt(response.getString("quantity"));
                            est = new Estoque(id, custo, quantidade);
                            System.out.println(est.getPrecoVenda());
                            texto = "\nPreço: R$"+ custo + "\n"
                                    + "Nome: "+ produtos.get(idProd).getNome() + "\n"
                                    + "Litros: "+ produtos.get(idProd).getLitros() + "L\n"
                                    + "Descrição: \n"+ produtos.get(idProd).getDescricao();
                            System.out.println(texto);
                            nomeProduto.setText(produtos.get(idProd).getNome());
                            valorPreco.setText("Preço: R$"+custo);
                            litrosProduto.setText(""+produtos.get(idProd).getLitros()+"L");
                            descricao.setText(""+produtos.get(idProd).getDescricao());
                        }catch(JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });
        meuQue.add(jsonObjectRequest);

    }
    public void aumentaVolume(View view) {

        Integer qutd = Integer.parseInt(quantidade.getText().toString());
        if(qutd < est.getQuatidade()){
            quantidade.setText((qutd+1)+"");
            valorPreco.setText("Preço: R$"+(est.getPrecoVenda()*(qutd+1))+"");
        }else {
            Toast toast = Toast.makeText(getApplicationContext(), "Quantidade máxima", Toast.LENGTH_LONG);
            toast.show();
        }

    }
    public void diminuiVolume(View view) {
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
                params.put("discount", "50.2");
                params.put("order_status", "1");
                params.put("client_id", "1");
                params.put("seller_id", "1");
                params.put("Content-Type", "application/json; charset=utf-8");
                params.put("Accept", "application/json; charset=utf-8");

                return params;
            }
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json; charset=utf-8");
                return params;
            }



        };
        meuQue.add(stringRequestPost);
    }
}