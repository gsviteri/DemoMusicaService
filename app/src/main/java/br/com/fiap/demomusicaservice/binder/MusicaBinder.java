package br.com.fiap.demomusicaservice.binder;

import android.os.Binder;
import android.os.IBinder;

import br.com.fiap.demomusicaservice.service.MusicaService;

/**
 * Created by gsviteri on 8/24/15.
 */
public class MusicaBinder extends Binder {

    private MusicaService musicaService;

    public MusicaBinder (MusicaService musicaService){
        this.musicaService = musicaService;
    }

    // metodo responsavel por obter musica
    public MusicaService getMusicaService() {
        return musicaService;
    }
}
