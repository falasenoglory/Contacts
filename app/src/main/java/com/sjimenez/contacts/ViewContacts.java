package com.sjimenez.contacts;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewContacts extends AppCompatActivity {

    private TextView txtname;
    private TextView txtContactNo;
    private ImageView imgCall;

    private String firstName, lastName, contactNo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contacts);

        txtname = (TextView) findViewById(R.id.tvViewName);
        txtContactNo = (TextView) findViewById(R.id.tvViewContactNo);
        imgCall = (ImageView) findViewById(R.id.imgCall);

        Intent intent = getIntent();

        firstName = intent.getStringExtra("FirstName");
        lastName = intent.getStringExtra("LastName");
        contactNo = intent.getStringExtra("ContactNo");

        txtname.setText(firstName + " " + lastName);
        txtContactNo.setText(contactNo);

        imgCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uri = "tel:" + contactNo.trim();
                Intent intents = new Intent(Intent.ACTION_CALL);
                intents.setData(Uri.parse(uri));

                if (ActivityCompat.checkSelfPermission(ViewContacts.this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(intents);
            }
        });







    }
}
