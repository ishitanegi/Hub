package com.example.hub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.hub.classification.ClassifierActivity;
import com.example.hub.detection.DetectorActivity;
import com.example.hub.posenet.CameraActivity;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    Button btnLogout;
    ImageView ivFlower;
    ImageView ivObject;
    ImageView ivPose;
    ImageView ivMessage;

    FirebaseAuth mFirebaseAuth;
    ImageView ivweather,ivTextRecognition;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ivMessage=findViewById(R.id.ivMessage);
        ivMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, com.example.hub.friendlychat.MainActivity.class));

            }
        });

        ivPose=findViewById(R.id.ivPose);
        ivPose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, CameraActivity.class));

            }
        });

        ivObject=findViewById(R.id.ivObject);
        ivObject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, DetectorActivity.class));

            }
        });
        ivFlower=findViewById(R.id.ivFlower);
        ivFlower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, ClassifierActivity.class));
            }
        });
        btnLogout=findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intToMain= new Intent(HomeActivity.this,MainActivity.class);
                startActivity(intToMain);
            }
        });

        ivweather= findViewById(R.id.weather);
        ivTextRecognition= findViewById(R.id.imageTextRecognition);
        ivweather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,WeatherActivity.class));
            }
        });
        ivTextRecognition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,TextRecognitionActivity.class));
            }
        });

    }
}
