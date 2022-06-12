package com.example.housecleaners;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
        TextView user, hId, address, rooms, bathrooms, floorType, price, date;
        ImageView imageView = convertView.findViewById(R.id.imageView8);
        user = convertView.findViewById(R.id.textView55);
        hId = convertView.findViewById(R.id.textView44);
        address = convertView.findViewById(R.id.textView47);
        rooms = convertView.findViewById(R.id.textView46);
        bathrooms = convertView.findViewById(R.id.textView49);
        floorType = convertView.findViewById(R.id.textView43);
        price = convertView.findViewById(R.id.textView52);
        date = convertView.findViewById(R.id.textView54);

        Post post = arrayList.get(position);
        String uN = post.getUserName();
        String houseId = post.getHouseId();
        String adrs = post.getAddress();
        String room = post.getNoOfRooms();
        String bathRoom = post.getNoOfBathRooms();
        String floor = post.getFloorType();
        byte[] img = post.getImage();
        String p = post.getPrice();
        String d = post.getDate();

        Bitmap bitmap = BitmapFactory.decodeByteArray(img, 0, img.length);
        user.setText("Owner: "+uN);
        hId.setText(houseId);
        address.setText("Address: " +adrs);
        rooms.setText("Room(s): "+room);
        bathrooms.setText("Bathroom(s): "+bathRoom);
        floorType.setText("Floor Type: "+floor);
        price.setText("Price: "+p);
        date.setText("Date: "+d);
        imageView.setImageBitmap(bitmap);

        return convertView;
    }
}
