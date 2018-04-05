package b12app.vyom.com.sqlitedatabaseprac;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    ArrayList<String> myArrayList ;
    Context context;
    LayoutInflater layoutInflater;
    MyViewHolder myViewHolder;

    public MyAdapter(ArrayList<String> myArrayList, Context context) {
        this.myArrayList = myArrayList;
        this.context = context;
       layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return myArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (convertView == null) {


            convertView = layoutInflater.inflate(R.layout.list_item_format, null);
            myViewHolder = new MyViewHolder();

            myViewHolder.textView = convertView.findViewById(R.id.tvUser);


            convertView.setTag(myViewHolder);

        } else {

            myViewHolder = (MyViewHolder) convertView.getTag();

        }

        myViewHolder.textView.setText(myArrayList.get(position));
        return convertView;
    }

    public class MyViewHolder{

        TextView textView;
    }
}
