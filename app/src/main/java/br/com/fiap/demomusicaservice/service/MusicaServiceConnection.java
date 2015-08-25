package br.com.fiap.demomusicaservice.service;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

import br.com.fiap.demomusicaservice.binder.MusicaBinder;

/**
 * Created by gsviteri on 8/24/15.
 */
public class MusicaServiceConnection implements ServiceConnection {

    private MusicaService musicaService;

    @Override
    public void onServiceConnected(ComponentName name, IBinder binder) {
        MusicaBinder musicaBinder = (MusicaBinder) binder;
        this.musicaService = musicaBinder.getMusicaService();
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }

    public MusicaService getMusicaService(){
        return musicaService;
    }
}
