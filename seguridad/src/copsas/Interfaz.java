import javax.swing.*;
import java.awt.event.*;
//Seleccionar archivo
import java.io.File;
import javax.swing.JFileChooser;

public class Interfaz extends JFrame implements ActionListener {

JButton boton1;
JButton boton2;
JButton boton3;
JTextField textField;
String ruta;

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
    boton1.setBounds(50,50,110,30);
    add(boton1);
    boton1.addActionListener(this);

    //Botón 2
    boton2=new JButton("Seleccionar archivo");
    boton2.setBounds(50,100,110,30);
    add(boton2);
    boton2.addActionListener(this);

    //Botón 3
    boton3=new JButton("Desencriptar");
    boton3.setBounds(50,200,110,30);
    add(boton3);
    boton3.addActionListener(this);

    //TextField
    textField= new JTextField(40);
    textField.setBounds(50,150,140,30);
    add(textField);

    //Muestro JFrame (lo último para que lo pinte todo correctamanete)
    setVisible(true);


  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == boton1) {
      JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(fileChooser);
        fileChooser.setMultiSelectionEnabled(true);
        try {
            ruta = fileChooser.getSelectedFile().getAbsolutePath();                                        
            //File f = new File(ruta);

            //desde aqui tengo el archivo
            encripta.main(ruta);

          //desencriptar
            //desencripta.main(ruta);


        } catch (NullPointerException x) {
            System.out.println("No se ha seleccionado ningún fichero");
        } catch (Exception x) {
            System.out.println(x.getMessage());
        }
    }else if(e.getSource() == boton2) {
      JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(fileChooser);
        try {
            ruta = fileChooser.getSelectedFile().getAbsolutePath();                                        
        } catch (NullPointerException x) {
            System.out.println("No se ha seleccionado ningún fichero");
        } catch (Exception x) {
            System.out.println(x.getMessage());
        }
      }else if(e.getSource() == boton3) {
        try {
          String texto = textField.getText();
          if ("".equals(texto)){
             System.out.println("El JTextField está vacío");
          }else{
            desencripta.main(ruta, texto);
          }

      } catch (NullPointerException x) {
          System.out.println("No se ha seleccionado ningún fichero");
      } catch (Exception x) {
          System.out.println(x.getMessage());
      }}
    }

  public static void main(String[] args) {
    new Interfaz();
  }
}