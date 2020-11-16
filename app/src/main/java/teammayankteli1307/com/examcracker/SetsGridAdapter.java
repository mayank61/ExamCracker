package teammayankteli1307.com.examcracker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SetsGridAdapter  extends BaseAdapter {
private     Context context;
   private LayoutInflater inflater;
   ArrayList<Integer> list=new ArrayList<>();



    public SetsGridAdapter(Context context,  List listSet) {

        this.context=context;
        inflater = (LayoutInflater.from(context));
        list.addAll(listSet);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return 0;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView textView ;
        view =inflater.inflate(R.layout.setsgrid,null);
        textView=view.findViewById(R.id.setNumber);
        textView.setText( String.valueOf(list.get(i)));

        return  view;

    }
}
