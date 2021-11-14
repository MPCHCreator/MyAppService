package uth.edu.mx;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uth.edu.mx.WsService.DTO.EstatusResponse;
import uth.edu.mx.WsService.DTO.RegistroDTO;
import uth.edu.mx.WsService.WsApiAdapter;

public class MainActivity extends AppCompatActivity implements Callback<EstatusResponse> {

    EditText campoTexto;
    EditText campoApellido;
    EditText campoCorreo;
    EditText campoTelefono;

    Spinner campoGenero;
    EditText usuario;
    EditText contraseña;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle(R.string.res);
        campoTexto = findViewById(R.id.nombre);
        campoApellido = findViewById(R.id.apellido);
        campoCorreo = findViewById(R.id.correo);
        campoTelefono = findViewById(R.id.telefono);
        campoGenero = findViewById(R.id.spinner);

        progressDialog = new ProgressDialog(MainActivity.this);

        String [] genero = {"","Femenino","Masculino"};
        ArrayAdapter <String> adapter= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,genero);
        campoGenero.setAdapter(adapter);

        usuario = findViewById(R.id.user);
        contraseña = findViewById(R.id.pwd);


    }




    @Override
    public void onSaveInstanceState(Bundle params){
        super.onSaveInstanceState(params);
        params.putString(campoTexto.getText().toString(),"nomParam");
        params.putString(campoApellido.getText().toString(),"apParam");
        params.putString(campoCorreo.getText().toString(), "amParam");

    }

    @Override
    public void onRestoreInstanceState(Bundle params){
        super.onRestoreInstanceState(params);
        if(params!= null){
            campoTexto.setText(params.getString("nomParam"));
            campoApellido.setText(params.getString("apParam"));
            campoCorreo.setText(params.getString("amParam"));

        }
    }


    public void Notmatter(View v){

       // String valorGenero = campoGenero.getSelectedItem().toString();

       // Toast t = Toast.makeText(this,valorGenero,Toast.LENGTH_SHORT);

        RegistroDTO nuevoUsuario = new RegistroDTO();
        nuevoUsuario.setNombre_usuario(campoTexto.getText().toString());
        nuevoUsuario.setApellido(campoApellido.getText().toString());
        nuevoUsuario.setTelefono(campoTelefono.getText().toString());
        nuevoUsuario.setCorreo(campoCorreo.getText().toString());
        nuevoUsuario.setGenero(campoGenero.getSelectedItem().toString());
        nuevoUsuario.setUsuario(usuario.getText().toString());
        nuevoUsuario.setContrasena(contraseña.getText().toString());

        Call<EstatusResponse> call = WsApiAdapter.getWsService().nuevoRegistro(nuevoUsuario);


        progressDialog.setMax(100);
        progressDialog.setMessage("Esperando");
        progressDialog.setTitle("cargando");
        progressDialog.setProgress(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        call.enqueue(this);


        /*
        Call<EstatusResponse> call = WsApiAdapter.getWsService().nuevoRegistro();
        call.enqueue(this);
        
        Intent pantallaDos = new Intent(this, Main2Activity.class);

        Bundle params = new Bundle();

        params.putString("textoCapturado", campoTexto.getText().toString());
        params.putString("textoCapturado2", campoApellido.getText().toString());
        params.putString("textoCapturado3", campoGenero.getItemAtPosition(1).toString());
        params.putString("textoCapturado4", campoCorreo.getText().toString());
        params.putString("textoCapturado5", campoTelefono.getText().toString());

        pantallaDos.putExtras(params);
        startActivity(pantallaDos);*/

    }

    @Override
    public void onResponse(Call<EstatusResponse> call, Response<EstatusResponse> response) {

        EstatusResponse dto = response.body();
        progressDialog.dismiss();

        Intent Menu = new Intent(this, Login.class);
        Bundle params2 = new Bundle();
        params2.putString("estatus2", dto.getEstatus());
        Menu.putExtras(params2);

        startActivity(Menu);

    }

    @Override
    public void onFailure(Call<EstatusResponse> call, Throwable t) {

    }
}
