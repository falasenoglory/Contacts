package com.sjimenez.contacts;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sjimenez.contacts.Object.Contacts;
import com.sjimenez.contacts.fragments.ListViewFragment;

public class MainActivity extends AppCompatActivity {


    private ListViewFragment mListViewFragment;
    private FirebaseDatabase database;
    private DatabaseReference databaseDatabaseReference;
    private long count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        database = FirebaseDatabase.getInstance();
        databaseDatabaseReference = database.getReference("contacts-53a65");

        setContentView(R.layout.activity_main);

        mListViewFragment = ListViewFragment.newInstance();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, mListViewFragment)
                .commit();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Add new contact");

                // Set up the input
                final EditText inputFirstName = new EditText(MainActivity.this);
                final EditText inputLastName = new EditText(MainActivity.this);
                final EditText inputContactNo = new EditText(MainActivity.this);

                // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                inputFirstName.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                inputLastName.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                inputContactNo.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                builder.setView(inputFirstName);
                builder.setView(inputLastName);
                builder.setView(inputContactNo);

                // Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    String FN= inputFirstName.getText().toString();
                    String LN= inputLastName.getText().toString();
                    String Con= inputContactNo.getText().toString();

                        Contacts cont= new Contacts(FN,LN,Con);

                        Firebase ref = new Firebase("https://contacts-53a65.firebaseio.com/");
                        ref.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot snapshot) {
                                count = snapshot.getChildrenCount();
                            }

                            @Override
                            public void onCancelled(FirebaseError firebaseError) {

                            }

                        });
                        Firebase newContact = ref.child(count+"");
                        newContact.setValue(cont);


                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();



            }
        });


    }
















}
