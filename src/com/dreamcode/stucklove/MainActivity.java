package com.dreamcode.stucklove;

import com.dreamcode.stucklove.R;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ToggleButton;
 
public class MainActivity extends Activity {
 
    //Mendefinisikan MediaPlayer sebagai audioBackground
    MediaPlayer audioBackground;
 
    /*Variabel untuk ToggleButton kita beri nama dengan myToggle*/
    ToggleButton myToggle;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
 
        //Memberi definisi di onCreate untuk toggle
        myToggle = (ToggleButton) findViewById(R.id.toggleSound);
        //Memanggil file startup_apppro pada folder raw
        audioBackground = MediaPlayer.create(this, R.raw.mainmenu);
        //Set looping ke false untuk memainkan audio jika telah selesai
        audioBackground.setLooping(false);
        //Set volume audio agar berbunyi
        audioBackground.setVolume(1,1);
        //Memulai audio
        audioBackground.start();
    }
 
    /*Mendefinisikan fungsi onToggleClicked dengan
    pengkondisian standar if/else*/
 
    public void onToggleClicked(View view) {
        boolean on = ((ToggleButton) view).isChecked();
 
        if (on) {
            /*Mematikan suara audio*/
            audioBackground.setVolume(0, 0);
        } else {
            /*Menghidupkan kembali audio background*/
            audioBackground.setVolume(1, 1);
        }
    }
 
    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        audioBackground.stop();
        MainActivity.this.exit();
        exit();}
    
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, 0, 0, R.string.iNeedYou);
		menu.add(0, 1, 1, R.string.tentang_title);
		menu.add(0, 2, 2, R.string.history);
		return super.onCreateOptionsMenu(menu);
		}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
			switch(item.getItemId()){
			case 0 :
                startActivity(new Intent(this, StuckLoveOld.class));
                break;

			case 1:
				tentangAplikasi();
				break;
				
			case 2:
				dibalikCerita();
				break;

            default:
                return super.onOptionsItemSelected(item);
        }
			return false;
    }
	
	private void tentangAplikasi(){
		new AlertDialog.Builder(this)
			.setTitle(R.string.tentang_title)
			.setMessage(R.string.tentang_isi)
			.setPositiveButton("OK",new DialogInterface.OnClickListener(){	
				public void onClick(DialogInterface dialoginterface, int i){}
				}) .show();
			}
	
	private void dibalikCerita(){
		new AlertDialog.Builder(this)
			.setTitle(R.string.history)
			.setMessage(R.string.history_isi)
			.setPositiveButton("OK",new DialogInterface.OnClickListener(){	
				public void onClick(DialogInterface dialoginterface, int i){}
				}) .show();
			}
        
        private void exit() {
    		
    		AlertDialog.Builder builder = new AlertDialog.Builder(this);
    		builder.setMessage("Alya yakin kepengen keluar?").setCancelable(false)
    				// tidak bisa tekan tombol back
    				// jika pilih yess
    		.setPositiveButton("Iya,coe!",
    				new DialogInterface.OnClickListener() {
    					public void onClick(DialogInterface dialog, int id) {
    						finish();
    					}
    				})
    		// jika pilih no
    		.setNegativeButton("Ngga, poncoe!",
    				new DialogInterface.OnClickListener() {
    					public void onClick(DialogInterface dialog, int id) {
    						dialog.cancel();
    					}
    				}).show();
    }
 
}