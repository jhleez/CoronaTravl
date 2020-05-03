package com.example.coronatravel.Adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coronatravel.LocationBasedList_Class;
import com.example.coronatravel.R;
import com.squareup.picasso.Picasso;


import java.util.List;

public class ItemAdapter extends BaseAdapter {


    private List<LocationBasedList_Class> list;

    public ItemAdapter(List<LocationBasedList_Class> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int i, View convertView, final ViewGroup viewGroup) {

        LocationBasedList_Class data = list.get(i);
        convertView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_list_item, viewGroup, false);

        TextView title = convertView.findViewById(R.id.textview_listitem_title);
        TextView addreess = convertView.findViewById(R.id.textview_listitem_address);
        //TextView distance = convertView.findViewById(R.id.textview_listitem_distance);
        ImageView imageView = convertView.findViewById(R.id.imageview_listitem);
        //final Button bookmark = convertView.findViewById(R.id.listitem_bookmar);
        String URI = data.getFirstimage();
       /* bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(viewGroup.getContext());
                builder.setTitle("즐겨찾기 설정");
                builder.setCancelable(true)
                        .setMessage("즐겨찾기로 추가하시겠습니까?")
                        .setNeutralButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setPositiveButton("추가", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(viewGroup.getContext(), i + "째 추가", Toast.LENGTH_SHORT).show();
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });*/
        if (URI == "") URI = "http://";

        Picasso.get().load(URI).placeholder(R.drawable.ic_launcher_background).into(imageView);

        title.setText(data.getTitle());
        addreess.setText(data.getAddr1());
        //distance.setText(data.getDist());


        return convertView;
    }

}
