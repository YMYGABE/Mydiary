package com.example.mydiary;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import java.util.List;

public class DiraryAdapter extends ArrayAdapter<Dirary> {
    private final int resourceId;
    public DiraryAdapter(@NonNull Context context, int textViewResourceId, @NonNull List<Dirary> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Dirary dirary = (Dirary) getItem(position); // 获取当前项的Fruit实例
        View view;
        ViewHolder viewHolder;
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent,false);
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) view.findViewById(R.id.title);
            viewHolder.context=(TextView) view.findViewById(R.id.mainContext);
            viewHolder.selectImage = (ImageView) view.findViewById(R.id.image);
            viewHolder.time=(TextView) view.findViewById(R.id.time);
            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.title.setText(dirary.getTitle());//为文本视图设置文本内容
        viewHolder.context.setText(dirary.getMainContext());
        Bitmap bit = BitmapFactory.decodeByteArray(dirary.getImage(), 0, dirary.getImage().length);
        viewHolder.selectImage.setImageBitmap(bit);
        viewHolder.time.setText(dirary.getTime());
        return view;
    }
    class ViewHolder{
        TextView title;
        TextView context;
        TextView time;
        ImageView selectImage;
    }
}
