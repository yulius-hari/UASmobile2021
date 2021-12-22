package com.example.pendaftaraneventmahasiswa;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    Spinner txtEvent;
    EditText txtNama, txtNrp, txtHp, txtEmail;
    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtEvent = findViewById(R.id.txtEvent);
        txtNama = findViewById(R.id.txtNama);
        txtNrp = findViewById(R.id.txtNrp);
        txtEmail = findViewById(R.id.txtEmail);
        txtHp = findViewById(R.id.txtHp);
        
    }

    public void SimpanData(View v)
    {

    }

    private class GetClass extends AsyncTask<String, Void, Void> {

        private final Context context;

        public GetClass(Context c){
            this.context = c;
        }

        protected void onPreExecute(){
            progress = new ProgressDialog(this.context);
            progress.setMessage("Loading");
            progress.show();
        }

        @Override
        protected Void doInBackground(String... params) {
            try {
                //final TextView outputView = (TextView) findViewById(R.id.showOutput);
                //10.0.2.2/potr
                URL url = new URL("http://cekberita.web.id/kelasmobile/insert.php?event="+txtEvent.getSelectedItem().toString()+"&nrp="+txtNrp.getText().toString()+"&nama="+txtNama.getText().toString()+"&hp="+txtHp.getText().toString()+"&email="+txtEmail.getText().toString());
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                //String urlParameters = "fizz=buzz";
                connection.setRequestMethod("GET");
                connection.setRequestProperty("USER-AGENT", "Mozilla/5.0");
                connection.setRequestProperty("ACCEPT-LANGUAGE", "en-US,en;0.5");

                int responseCode = connection.getResponseCode();

                final StringBuilder output = new StringBuilder();
                //output.append(System.getProperty("line.separator") + "Response Code " + responseCode);
                //output.append(System.getProperty("line.separator") + "Type " + "GET");
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = "";
                StringBuilder responseOutput = new StringBuilder();
                System.out.println("" + br);
                while((line = br.readLine()) != null ) {
                    responseOutput.append(line);
                }
                br.close();

                output.append(responseOutput.toString());

                MainActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        progress.dismiss();
                    }
                });

            } catch (MalformedURLException e) {
// TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
// TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }
    }


    public void tambilkanPeserta(View v)
    {
        Intent i = new Intent(this, ViewPeserta.class);
        startActivity(i);
    }
}