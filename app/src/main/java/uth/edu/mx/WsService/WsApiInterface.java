package uth.edu.mx.WsService;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import uth.edu.mx.WsService.DTO.AccesoUsuario;
import uth.edu.mx.WsService.DTO.EstatusResponse;
import uth.edu.mx.WsService.DTO.RegistroDTO;

public interface WsApiInterface {
    @POST("registro.php")
    Call<EstatusResponse> nuevoRegistro(@Body RegistroDTO registro);

    @POST("valida.php")
    Call<EstatusResponse> accesoUsuario(@Body AccesoUsuario registro2);
}
