package br.com.fiap.demomusicaservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import br.com.fiap.demomusicaservice.service.MusicaService;
import br.com.fiap.demomusicaservice.service.MusicaServiceConnection;

public class MainActivity extends AppCompatActivity {

    private Button btTocar;
    private Button btPausar;
    private Button btParar;

    private Intent intent;

    private MusicaServiceConnection connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniciaServico();
        conectaServico();

        btParar = (Button) findViewById(R.id.btParar);
        btPausar = (Button) findViewById(R.id.btPausar);
        btTocar = (Button) findViewById(R.id.btTocar);
    }

    public void tocar(View v){
        connection.getMusicaService().tocar();
        ativarDesativarBotoes(true);
    }

    public void pausar(View v){
        connection.getMusicaService().pausar();
        ativarDesativarBotoes(false);
    }

    public void parar(View v){
        connection.getMusicaService().parar();
        ativarDesativarBotoes(false);
    }

    private void iniciaServico() {
        intent = new Intent(this, MusicaService.class);
        startService(intent);
    }

    private void conectaServico() {
        connection = new MusicaServiceConnection();
        bindService(intent, connection, 0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void ativarDesativarBotoes(boolean isPlaying){

        btTocar.setEnabled(!isPlaying);
        btPausar.setEnabled(isPlaying);
        btParar.setEnabled(isPlaying);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }
}

