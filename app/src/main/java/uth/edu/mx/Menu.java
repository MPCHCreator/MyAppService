package uth.edu.mx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class Menu extends YouTubeBaseActivity {

    Button btn;
    YouTubePlayerView youTubePlayerView;
    YouTubePlayer.OnInitializedListener onInitializedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        this.setTitle(R.string.menu);

        btn = findViewById(R.id.play);
        youTubePlayerView = findViewById(R.id.YoutubePlayerView);

        Bundle p = getIntent().getExtras();

        if(p != null){
            if(p.getString("estatus") !=null){
                String mensaje = p.getString("estatus");
                Toast t = Toast.makeText(this, "Wellcome: " + mensaje , Toast.LENGTH_SHORT );
                t.show();
            }

        }

        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo("BFICFCx4r7Y");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                youTubePlayerView.initialize("AIzaSyCE6yhYQrExVlrZufG2M4oHgY2iFUAQQAw", onInitializedListener);
            }
        });

    }

    public void Salir(View v){

        Intent Login = new Intent(this, Login.class);
        startActivity(Login);
    }
}
