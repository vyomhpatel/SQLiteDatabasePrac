package b12app.vyom.com.sqlitedatabaseprac;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText username, password, email;
    private Button btnSave, btnShow;
    static MyDbHelper myDbHelper;
    SQLiteDatabase sqLiteDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDbHelper = new MyDbHelper(MainActivity.this);
        sqLiteDb = myDbHelper.getWritableDatabase();


        username = findViewById(R.id.etName);
        password = findViewById(R.id.etPass);
        email = findViewById(R.id.etEmail);

        btnShow = findViewById(R.id.btnShow);
        btnSave = findViewById(R.id.btnSave);

        btnShow.setOnClickListener(this);
        btnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {


        switch (v.getId()){

            case R.id.btnSave:
                ContentValues values = new ContentValues();
                values.put(MyDbHelper.USERNAME,username.getText().toString());
                values.put(MyDbHelper.PASSWORD,password.getText().toString());
                values.put(MyDbHelper.EMAIL,email.getText().toString());



                sqLiteDb.insert(MyDbHelper.TABLE_NAME,null,values);

                Intent intent = new Intent(MainActivity.this,List.class);
                startActivity(intent);

                username.setText("");
                password.setText("");
                email.setText("");

                break;

            case R.id.btnShow:



                Cursor cursor = sqLiteDb.rawQuery("SELECT * FROM "+MyDbHelper.TABLE_NAME,null);

                cursor.moveToFirst();
                do {
                    String cursor_username = cursor.getString(cursor.getColumnIndex(MyDbHelper.USERNAME));
                    String cursor_pass = cursor.getString(cursor.getColumnIndex(MyDbHelper.PASSWORD));
                    String cursor_email = cursor.getString(cursor.getColumnIndex(MyDbHelper.EMAIL));

                    Toast.makeText(this, "username: " + cursor_username + "\n Password: " + cursor_pass + "\n Email: " + cursor_email, Toast.LENGTH_LONG).show();

                }
                while (cursor.moveToNext());
                break;
        }
    }
}
