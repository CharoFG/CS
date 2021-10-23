package interfaz;
import encriptado.*;
import javax.swing.*;
import java.awt.event.*;
//Seleccionar archivo
import java.io.File;
import javax.swing.JFileChooser;

public class Interfaz extends JFrame implements ActionListener {

JButton boton1;
JButton boton2;

  public Interfaz() {

    //Layout absoluto
    setLayout(null);

    //Tamaño de la ventana
    setBounds(0,0,400,400);

    //Título
    setTitle("Ejemplo 1: Botón");

    //No redimensionable
    setResizable(false);

    //Cerrar proceso al cerrar la ventana
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    //Botón 1
    boton1=new JButton("Encriptar");
    boton1.setBounds(130,100,140,50);
    add(boton1);
    boton1.addActionListener(this);

    //Botón 2
    boton2=new JButton("Desencriptar");
    boton2.setBounds(130,200,140,50);
    add(boton2);
    boton2.addActionListener(this);

    //Muestro JFrame (lo último para que lo pinte todo correctamanete)
    setVisible(true);


  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == boton1) {
      JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(fileChooser);
        try {
            String ruta = fileChooser.getSelectedFile().getAbsolutePath();                                        
            File f = new File(ruta);

            //desde aqui tengo el archivo






        } catch (NullPointerException x) {
            System.out.println("No se ha seleccionado ningún fichero");
        } catch (Exception x) {
            System.out.println(x.getMessage());
        }
    }else if(e.getSource() == boton2) {
      JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(fileChooser);
        try {
            String ruta = fileChooser.getSelectedFile().getAbsolutePath();                                        
            File f = new File(ruta);

            //desencriptar






        } catch (NullPointerException x) {
            System.out.println("No se ha seleccionado ningún fichero");
        } catch (Exception x) {
            System.out.println(x.getMessage());
        }
      }
    }

  public static void main(String[] args) {
    new Interfaz();
  }
}