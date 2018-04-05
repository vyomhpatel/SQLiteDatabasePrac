package b12app.vyom.com.sqlitedatabaseprac;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class List extends AppCompatActivity {

    private static final String TAG ="tag" ;
    private ListView listView;
    MyDbHelper myDbHelper;
    ArrayList<String> user_list;
    ArrayList<Integer> user_id;
    MyAdapter myAdapter;
    int cursor_id;
    String cursor_username;
    Cursor cursor = null;
    int cursor_userId;

    public void loadCursor(Cursor cursor){
        cursor.moveToFirst();
        do {
            cursor_userId = cursor.getInt(cursor.getColumnIndex(MyDbHelper.ID));
            String cursor_username = cursor.getString(cursor.getColumnIndex(MyDbHelper.USERNAME));
            String cursor_pass = cursor.getString(cursor.getColumnIndex(MyDbHelper.PASSWORD));
            String cursor_email = cursor.getString(cursor.getColumnIndex(MyDbHelper.EMAIL));


            user_list.add("Username: "+cursor_username +" \n Password: "+cursor_pass +"\n Email: "+cursor_email);
            user_id.add(cursor_userId);
            Log.i(TAG, "Username: "+cursor_username +" \n Password: "+cursor_pass +"\n Email: "+cursor_email);
            Log.i(TAG, "list: "+user_list);
//            Toast.makeText(this, "username: " + cursor_username + "\n Password: " + cursor_pass + "\n Email: " + cursor_email, Toast.LENGTH_LONG).show();

        }
        while (cursor.moveToNext());

//        cursor.close();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listView = findViewById(R.id.user_list);

        myDbHelper = new MyDbHelper(this);
        final SQLiteDatabase sqLiteDatabase = myDbHelper.getWritableDatabase();

        cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+MyDbHelper.TABLE_NAME,null);
        user_list = new ArrayList<>();
        user_id = new ArrayList<Integer>();

        loadCursor(cursor);

        myAdapter = new MyAdapter(user_list,this);
        listView.setAdapter(myAdapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

//                cursor_id = cursor.getInt(cursor.getColumnIndex(MyDbHelper.ID));


               cursor.moveToPosition(position);
                cursor_username = cursor.getString(cursor.getColumnIndex(MyDbHelper.USERNAME));
//               sqLiteDatabase.rawQuery("DELETE FROM " +MyDbHelper.TABLE_NAME +" WHERE  username = "+ cursor_username ,null);

                sqLiteDatabase.delete(
                        MyDbHelper.TABLE_NAME,
                        MyDbHelper.ID + "="
                                + user_id.get(position), null);
//                String sql = "DELETE FROM "+MyDbHelper.TABLE_NAME+" WHERE id = ?";
//                sqLiteDatabase.execSQL(sql, new Integer[]{cursor_id});

                loadCursor(cursor);
                myAdapter.myArrayList.remove(position);
                myAdapter.notifyDataSetChanged();


                return false;
            }
        });



    }
}
