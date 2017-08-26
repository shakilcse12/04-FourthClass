package com.hellohasan.forthclass;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private AlphaAnimation buttonClickAnimation = new AlphaAnimation(1F, 0.002F);
    private final int REQUEST_PHONE_CALL = 19987;
    private TextView responseTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        responseTextView = (TextView) findViewById(R.id.serverResponseTextView);

    }

    public void makePhoneCall(View view) {
        view.startAnimation(buttonClickAnimation);

        callNow();
    }

    private void callNow() {
        String telephone = "tel:+8801521101145";
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse(telephone));
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_PHONE_CALL);
            return;
        }
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == REQUEST_PHONE_CALL){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callNow();
            }
        }
    }

    public void sendDataToServer(View view) {

       NetworkCall networkCall =  new NetworkCall(new CallBackInterface() {
            @Override
            public void uiUpdate(ResponseModel responseModel) {
                responseTextView.setText(responseModel.getMessage());
            }
        });

        networkCall.sendData(new DataModel("Hasan", 26));
    }
}
