package uth.edu.mx.WsService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WsApiAdapter {

    private  static WsApiInterface wsService;
    public static WsApiInterface getWsService(){
        HttpLoggingInterceptor loggin = new HttpLoggingInterceptor();
        loggin.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(loggin);
            String urlBase="http://mpchstormland.000webhostapp.com/API/";
        if(wsService == null){
            Retrofit retro = new Retrofit.Builder()
                            .baseUrl(urlBase)
                            .addConverterFactory(GsonConverterFactory.create    ())
                            .client(httpClient.build())
                            .build();
            wsService = retro.create(WsApiInterface.class);
        }
        return wsService;
    }

}
