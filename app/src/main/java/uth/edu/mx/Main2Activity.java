package uth.edu.mx;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


       Bundle p = getIntent().getExtras();
       if(p != null){
           String mensaje = p.getString("textoCapturado");
           Toast t = Toast.makeText(this, "Nombre: " + mensaje , Toast.LENGTH_SHORT );

           String mensaje2 = p.getString("textoCapturado2");
           Toast t2 = Toast.makeText(this, "Apellido: " + mensaje2 , Toast.LENGTH_SHORT );

           String mensaje3 = p.getString("textoCapturado3");
           Toast t3 = Toast.makeText(this, "Genero: " + mensaje3 , Toast.LENGTH_SHORT );

           String mensaje4 = p.getString("textoCapturado4");
           Toast t4 = Toast.makeText(this, "Correo: " + mensaje4 , Toast.LENGTH_SHORT );

           String mensaje5 = p.getString("textoCapturado5");
           Toast t5 = Toast.makeText(this, "Telefono: " + mensaje5 , Toast.LENGTH_SHORT );

           t.show();
           t2.show();
           t3.show();
           t4.show();
           t5.show();
       }


    }
}
