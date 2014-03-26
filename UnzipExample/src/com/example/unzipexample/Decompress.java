package com.example.unzipexample;

import android.util.Log; 
import java.io.File; 
import java.io.FileInputStream; 
import java.io.FileOutputStream; 
import java.util.zip.ZipEntry; 
import java.util.zip.ZipInputStream; 
 
/** 
 * 
 * @author Aritz Gamboa (aGamboaDev)
 * agamboadev.esy.es
 */ 
public class Decompress { 
  private String strZipFile; 
  private String strLocation; 
 
  // Se le pasa al constructor el nombre del archivo y su ubicacion
  public Decompress(String strZipFile, String strLocation) {	  
	  this.strZipFile = strZipFile; 
	  this.strLocation = strLocation; 
 
	  // Se utiliza para crear una carpeta en la ubicacion del archivo 
	  // comprimido donde se guardana los archivos que contiene
	  dirChecker(""); 
  } 
 
  public boolean unzip() {
	  Log.v("Decompress", "unzip()"); 
	  try  {
		  // Se crea un elemento ZipInputStream con el fichero de entrada 
		  // (cargandolo como FileInputStream)
		  FileInputStream fin = new FileInputStream(strZipFile); 
		  ZipInputStream zin = new ZipInputStream(fin); 
		  ZipEntry ze = null; 
		  Log.v("Decompress", "Inicio lectura del zip"); 
		  while ((ze = zin.getNextEntry()) != null) {
			  // Mientras existan datos se realizan las siguientes operaciones
			  Log.v("Decompress", "Unzipping the file " + ze.getName()); 
 
			  // Se comprueba si el elemento es un directorio
			  if(ze.isDirectory()) { 
				  // Si lo es se crea la carpeta
				  dirChecker(ze.getName()); 
			  } else { 
				  // En caso contrario se escribe el fichero
				  FileOutputStream fout = new FileOutputStream(strLocation + ze.getName()); 
				  for (int c = zin.read(); c != -1; c = zin.read()) { 
					  fout.write(c); 
				  } 
 
				  zin.closeEntry(); 
				  fout.close(); 
			  } 
         
		  } 
		  return true;
	  } catch(Exception e) { 
		  Log.e("Decompress", "unzip", e);
		  return false;
	  } 
 
  } 
 
  // Funcion que crea una carpeta (si no existe) en la ubicacion del archivo 
  // comprimido donde se guardana los archivos que contiene
  // En caso de no querer copiarlos directamente en la ubicacion, sin crear ninguna carpeta,
  // se le pasa como dato un String vacio
  private void dirChecker(String dir) { 
	  File f = new File(strLocation + dir); 
 
	  if(!f.isDirectory()) {
		  // Si no es un directorio, se crea
		  f.mkdirs(); 
	  } 
  	} 
} 
