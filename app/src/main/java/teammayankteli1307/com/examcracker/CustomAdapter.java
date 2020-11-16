package teammayankteli1307.com.examcracker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends BaseAdapter {
   private Context context;
    private List<Example> example;
 private    LayoutInflater inflater;

    public CustomAdapter(Context context, List<Example> example) {
        this.context = context;
        this.example = example;
        inflater =(LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return example.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView;
        TextView textView;
        view = inflater.inflate(R.layout.grid_layout,null);
        imageView =view.findViewById(R.id.imageview);
        textView=view.findViewById(R.id.textview);
        imageView.setImageResource(example.get(i).getImage());
        textView.setText(example.get(i).getName());
        return  view;
    }
}
