package com.example.unzipexample;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.widget.TextView;

/** 
 * 
 * @author Aritz Gamboa (aGamboaDev)
 * agamboadev.esy.es
 */ 

public class MainActivity extends Activity {
	
	TextView txtMsg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		txtMsg = (TextView) findViewById(R.id.txtMsg);
		
		// Se obtiene la direccion del almacenamiento externo + el nombre del archivo
		// El rar cuelga directamente de la raiz para este ejemplo. Si esta en alguna otra
		// carpeta hay que cambiar "/prueba.zip"
		String zipFile = Environment.getExternalStorageDirectory() + "/prueba.zip";
		// Se crea un String con la ruta completa donde se va volcar lo descomprimido
		String unzipLocation = Environment.getExternalStorageDirectory() + "/unzipped/"; 
		 
		// Se descomprime el zip
		Decompress d = new Decompress(zipFile, unzipLocation); 
		if (d.unzip()) {
			txtMsg.setText("Archivo descomprimido correctamente");
		} else {
			txtMsg.setText("Archivo NO descomprimido correctamente");
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
