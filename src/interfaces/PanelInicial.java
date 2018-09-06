/**********************************************************************/

Copyleft (C) 2017  Caetán Tojeiro Carpente

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU Affero General Public License as published by
the Free Software Foundation, either version 3 of the License, or
any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/agpl-3.0.html>

/**********************************************************************/

package interfaces;

import javax.swing.JPanel;


import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.awt.event.ActionEvent;

public class PanelInicial extends JPanel {
	private JTextField tfNombre;
	private final JLabel lblNewLabel = new JLabel("Descripcion");
	private JTextField tfDescripcion;
	private final JLabel lblContrasea = new JLabel("Contrasena");
	private JTextField tfContrasena;
	private JLabel lblNewLabel_1;
	private JButton btnVerContrasenas;

	/**
	 * Create the panel.
	 */
	
	
	File fichero = new File(javax.swing.filechooser.FileSystemView.getFileSystemView().getHomeDirectory() + "\\BANCO_DE_CONTRASENAS.doc");//Archivo usado para guardar y leer los datos, con ruta
	//relativa a nuestro .jar
	String Nombre, Descripcion, Contrasena;



	
	public PanelInicial() {
		setLayout(new GridLayout(9, 2, 5, 5));


		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 17));
		add(lblNombre);
		
		tfNombre = new JTextField();
		add(tfNombre);
		tfNombre.setColumns(10);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		add(lblNewLabel);
		
		tfDescripcion = new JTextField();
		add(tfDescripcion);
		tfDescripcion.setColumns(10);
		lblContrasea.setFont(new Font("Tahoma", Font.BOLD, 17));
		add(lblContrasea);
		
		tfContrasena = new JTextField();
		add(tfContrasena);
		tfContrasena.setColumns(10);
		
		JButton btnGuardar = new JButton("GUARDAR");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Guardar datos
				Nombre = tfNombre.getText();
				Descripcion = tfDescripcion.getText();
				Contrasena = tfContrasena.getText();
				mostrarDatos();
				guardarDatos(Nombre,Descripcion,Contrasena);
				
			}

		});
		add(btnGuardar);
		
		lblNewLabel_1 = new JLabel("");
		add(lblNewLabel_1);
		
		btnVerContrasenas = new JButton("VER CONTRASENAS");
		btnVerContrasenas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Ver contraseñas
				leerDatos();
			//	mostrarDatos();
				abrirFichero();
			}
            

		});
		add(btnVerContrasenas);

	}
	
	public String darNombre() {
		return tfNombre.getText();
	}
	
	public String darDescripcion() {
		return tfDescripcion.getText();
	}
	
	public String darContrasena() {
		return tfContrasena.getText();
	}
	
	public void limpiarCampos() {
		tfNombre.setText("");
		tfDescripcion.setText("");
		tfContrasena.setText("");
	}
	
    
	private void mostrarDatos() {
		System.out.println(Nombre + "\n");
		System.out.println(Descripcion + "\n");
		System.out.println(Contrasena + "\n");
		System.out.println("------------------------------------------------------------------------------------------------" + "\n");
	}

	private void leerDatos() {
        String linea = null;//Para guardar los datos de forma temporal 
        if (fichero.exists()) {//Si se ha creado el fichero 
            try { 
                Scanner lector = new Scanner(fichero); 
                while(lector.hasNextLine()){ 
                linea=lector.nextLine(); 
                } 
                 String[] datos = linea.split("-,-"); 
                 Nombre = datos[0]; 
                 Descripcion = datos[1]; 
                 Contrasena = datos[2]; 

            } catch (FileNotFoundException ex) { 
                System.out.print("No se pudo abrir el archivo!"+ex.getMessage()); 
            } 
        } 				
	}
	

	private void guardarDatos(String nombre, String descripcion, String contrasena) {
		if (fichero.exists()) {
	        try { 
	            FileWriter bw;//Instancia de BW 
	            bw = new FileWriter(fichero,true);//Inicialización de "BW" con "FW" como parámetro con "fichero" como parámetro
	            bw.write("-- 1. NOMBRE: " + nombre + "-,-" + "2. DESCRIPCIÓN: "+ descripcion + "-,-" + "3. CONTRASEÑA: " + contrasena + "\r\n");//Escribimos lo que haya en los TextFields
	            //y entre ello agregamos una coma ("-,-"). 
	            bw.close();//Cerramos para guardar los datos en el fichero 
	        } catch (IOException ex) { 
	            System.out.print("No se pudieron guardar los datos!n" + ex.getMessage()); 
	        } 
		} else {
			try { 
	            FileWriter bw;//Instancia de BW 
	            bw = new FileWriter(fichero,true);//Inicialización de "BW" con "FW" como parámetro con "fichero" como parámetro
	            bw.write("-- 1. NOMBRE: " + nombre + "-,-" + "2. DESCRIPCIÓN: "+ descripcion + "-,-" + "3. CONTRASEÑA: " + contrasena + "\r\n");//Escribimos lo que haya en los TextFields
	            //y entre ello agregamos una coma ("-,-"). 
	            bw.close();//Cerramos para guardar los datos en el fichero 
	        } catch (IOException ex) { 
	            System.out.print("No se pudieron guardar los datos!n" + ex.getMessage()); 
	        }
		}
	}
	
	
	public void abrirFichero(){

	     try {
	            Desktop.getDesktop().open(fichero);

	     }catch (IOException ex) {

	            System.out.println(ex);

	     }

	}        

}
