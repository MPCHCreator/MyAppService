package uth.edu.mx;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uth.edu.mx.WsService.DTO.AccesoUsuario;
import uth.edu.mx.WsService.DTO.EstatusResponse;
import uth.edu.mx.WsService.WsApiAdapter;

public class Login extends AppCompatActivity implements Callback<EstatusResponse> {

    EditText usuario;
    EditText contraseña;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.setTitle(R.string.log);
        usuario = findViewById(R.id.user_name);
        contraseña= findViewById(R.id.password);
        progressDialog = new ProgressDialog(Login.this);


    }

    public void Ingresa(View v){
        if(!usuario.getText().toString().equals("")&& !contraseña.getText().toString().equals("")){
            AccesoUsuario acceso = new AccesoUsuario();
            acceso.setUsuario(usuario.getText().toString());
            acceso.setContrasena(contraseña.getText().toString());
            Call<EstatusResponse> call = WsApiAdapter.getWsService().accesoUsuario(acceso);
            progressDialog.setMax(100);
            progressDialog.setMessage("Esperando respuesta");
            progressDialog.setTitle("Cargando");
            progressDialog.setProgress(ProgressDialog.STYLE_SPINNER);
            progressDialog.show();

            call.enqueue(this);
        }else {
            Toast x = Toast.makeText(this,"Ingresa tu usuario y contraseña" , Toast.LENGTH_SHORT );
            x.show();
        }

    }

    @Override
    public void onResponse(Call<EstatusResponse> call, Response<EstatusResponse> response) {


        EstatusResponse dto = response.body();
        progressDialog.dismiss();
        if(dto != null && dto.getEstatus().toString().equals("ok")){
            Toast t = Toast.makeText(this,"Estatus: "+dto.getEstatus() , Toast.LENGTH_SHORT );
            t.show();

            Intent Menu = new Intent(this, Menu.class);
            Bundle params = new Bundle();
            params.putString("estatus", usuario.getText().toString());
            Menu.putExtras(params);
            startActivity(Menu);
        }else{
            Toast f = Toast.makeText(this,"Usuario invalido" , Toast.LENGTH_SHORT );
            f.show();
        }

    }

    @Override
    public void onFailure(Call<EstatusResponse> call, Throwable t) {
        progressDialog.dismiss();
        Toast t1 = Toast.makeText(this,"Denegado" , Toast.LENGTH_SHORT );
        t1.show();
    }

    public void Register(View v){
        Intent Menu = new Intent(this, MainActivity.class);
        startActivity(Menu);
    }
}
