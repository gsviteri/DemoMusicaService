package br.com.fiap.demomusicaservice.service;

import android.app.Service;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.IBinder;

import br.com.fiap.demomusicaservice.binder.MusicaBinder;

public class MusicaService extends Service {

    private MediaPlayer mediaPlayer;

    // posicao atual da musica em millisegundos
    private int posicao = 0;

    // musica a ser tocada, localizada no diretorio "assets"
    /*
        assets: algum arquivo que tenha que ir na applicaçao.
     */
    private static final String MUSICA = "porco.mp3";

    private IBinder binder = new MusicaBinder(this);

    public MusicaService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public void tocar() {
        if (posicao > 0) {
            mediaPlayer.seekTo(posicao);
        } else {
            mediaPlayer = new MediaPlayer();
            try {
                AssetFileDescriptor afd = getApplicationContext().getAssets().openFd(MUSICA);

                mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());

                mediaPlayer.prepare();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        mediaPlayer.start();

    }

    public void parar(){
        if(mediaPlayer.isPlaying()){
            mediaPlayer.stop();
            posicao = 0;
        }
    }

    public void pausar(){
        if(mediaPlayer.isPlaying()){
            mediaPlayer.pause();
            posicao = mediaPlayer.getCurrentPosition();

        }
    }
}
