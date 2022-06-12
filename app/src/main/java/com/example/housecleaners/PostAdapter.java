package com.example.housecleaners;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PostAdapter extends BaseAdapter {

    Context context;
    ArrayList<Post> arrayList;


    public PostAdapter(Context context, ArrayList<Post> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        convertView  = inflater.inflate(R.layout.show_layout, null);
        TextView address, price, date;
        ImageView imageView = convertView.findViewById(R.id.imageView8);

        address = convertView.findViewById(R.id.textView47);
        price = convertView.findViewById(R.id.textView52);
        date = convertView.findViewById(R.id.textView54);

        Post post = arrayList.get(position);
        String adrs = post.getAddress();
        byte[] img = post.getImage();
        String p = post.getPrice();
        String d = post.getDate();

        Bitmap bitmap = BitmapFactory.decodeByteArray(img, 0, img.length);
        address.setText("Address: " +adrs);
        price.setText("Price: "+p);
        date.setText("Date: "+d);
        imageView.setImageBitmap(bitmap);

        return convertView;
    }
}
