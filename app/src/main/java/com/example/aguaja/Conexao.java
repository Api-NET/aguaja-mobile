package com.example.aguaja;

import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class Conexao {


    public static String getDados(String uri) {
        BufferedReader bufferedReader = null;
        try {
            URL url = new URL(uri);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            StringBuilder stringBuilder = new StringBuilder();
            bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));

            String linha;

            while ((linha = bufferedReader.readLine()) != null) {
                stringBuilder.append(linha + "\n");
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public  void  postData(EditText email, EditText senha)  throws UnsupportedEncodingException
    {
        // Get user defined values
        String Email = email.getText().toString();
        String Senha   = senha.getText().toString();


        // Create data variable for sent values to server

        String data = URLEncoder.encode("email", "UTF-8")
                + "=" + URLEncoder.encode(Email, "UTF-8");

        data += "&" + URLEncoder.encode("senha", "UTF-8") + "="
                + URLEncoder.encode(Senha, "UTF-8");


        String text = "";
        BufferedReader reader=null;

        // Send data
        try
        {

            // Defined URL  where to send data
            URL url = new URL("http://localhost:{porta}/cliente/login");

            // Send POST data request

            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write( data );
            wr.flush();

            // Get the server response

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;

            // Read Server Response
            while((line = reader.readLine()) != null)
            {
                // Append server response in string
                sb.append(line + "\n");
            }


            text = sb.toString();
        }
        catch(Exception ex)
        {

        }
        finally
        {
            try
            {
                reader.close();
            }

            catch(Exception ex) {}
        }

        // Show response on activity
        //content.setText( text  );

    }
}
