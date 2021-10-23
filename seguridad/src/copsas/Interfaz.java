import javax.swing.*;
import java.awt.event.*;
import javax.swing.JFileChooser;

public class Interfaz extends JFrame implements ActionListener {

JButton boton1;
JButton boton2;
JButton boton3;
JTextField textField;
String ruta;
JLabel etiq1;
JLabel etiq2;
JLabel etiq3;
JLabel etiq4;
  public Interfaz() {

    //Layout absoluto
    setLayout(null);

    //Tamaño de la ventana
    setBounds(0,0,500,400);

    //Título
    setTitle("Practica 1 CS");

    //No redimensionable
    setResizable(false);

    //Cerrar proceso al cerrar la ventana
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    //Label 1
    etiq1= new JLabel("1.Encriptar");
    etiq1.setBounds(50,25,110,30);
    add(etiq1);

    //Botón 1
    boton1=new JButton("Encriptar");
    boton1.setBounds(50,50,110,30);
    add(boton1);
    boton1.addActionListener(this);

    //Label 2
    etiq2= new JLabel("2.Desencriptar");
    etiq2.setBounds(50,75,110,30);
    add(etiq2);

    //Botón 2
    boton2=new JButton("Seleccionar archivo");
    boton2.setBounds(50,100,110,30);
    add(boton2);
    boton2.addActionListener(this);

    //Label 4
    etiq4= new JLabel(ruta);
    etiq4.setBounds(165,100,300,30);
    add(etiq4);


    //Label 3
    etiq3= new JLabel("Introduzca la clave: ");
    etiq3.setBounds(50,125,200,30);
    add(etiq3);

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
    //Encriptar
    if (e.getSource() == boton1) {
      JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(fileChooser);
        fileChooser.setMultiSelectionEnabled(true);
        try {
            ruta = fileChooser.getSelectedFile().getAbsolutePath();                                        
            //desde aqui tengo el archivo
            encripta.main(ruta);
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
            etiq4= new JLabel(ruta);
            etiq4.setBounds(165,100,300,30);
            add(etiq4);
            //actualizar
            SwingUtilities.updateComponentTreeUI(this);
            this.validateTree();                                        
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