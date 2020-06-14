package com.example.coronatravel.detail.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coronatravel.R;
import com.example.coronatravel.detail.detailRepeat;
import com.example.coronatravel.detail.detailRepeat_25;
import com.example.coronatravel.detail.detailRepeat_32;
import com.squareup.picasso.Picasso;


public class Detail_Third_Fragment extends Fragment {

    String contentid,contenttypeid;
    TextView third,title;
    LinearLayout linearLayout;
    ImageView presentimageview;
    ScrollView scrollView;
    public Detail_Third_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detail_third_, container, false);
        // Inflate the layout for this fragment
        linearLayout=view.findViewById(R.id.fragment_linearlayout);

        title=view.findViewById(R.id.third_title);
        contentid = Data.contentid;
        contenttypeid = Data.contenttypeid;
    /*    String subdetailalt; // 이름
        String subdetailimg; // 사진
        String subdetailoverview; // 설명*/
        third = view.findViewById(R.id.third);
        third.setText("");
        presentimageview=view.findViewById(R.id.present_image);

        /*for(int i=0;i<detailRepeat.repeat_array.size();i++){
            ImageView imageView= new ImageView(getContext());
            detailRepeat_25 item =detailRepeat_25.repeat_array.get(i);
            Picasso.get().load(item.getSubdetailimg()).placeholder(R.drawable.luggageicon).into(imageView);
            recyclerView.addView(imageView,200,recyclerView.getHeight());
        }*/

        if(contenttypeid.equals("25")){

            title.setVisibility(View.VISIBLE);
            presentimageview.setVisibility(View.VISIBLE);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(200,200);
            Typeface typeface = ResourcesCompat.getFont(getContext(),R.font.binggraemelona);

            //third.setText(detailRepeat_25.repeat_array.get(0).getSubdetailalt() +"\n"+ detailRepeat_25.repeat_array.get(0).getSubdetailoverview());
            for(int i=0;i<detailRepeat_25.repeat_array.size();i++){
                final detailRepeat_25 item =detailRepeat_25.repeat_array.get(i);
                ImageView imageView= new ImageView(getContext());
                TextView textView = new TextView(getContext());
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                textView.setText((i+1)+"번 코스");
                textView.setTypeface(typeface);

                textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try{
                            Picasso.get().load(item.getSubdetailimg()).placeholder(R.drawable.luggageicon).into(presentimageview);
                        }catch (Exception e){
                            presentimageview.setImageResource(R.drawable.luggageicon);
                        }
                        third.setText(item.getSubdetailoverview());
                        title.setText(item.getSubdetailalt());

                    }
                });
                try{
                    Picasso.get().load(item.getSubdetailimg()).placeholder(R.drawable.luggageicon).into(imageView);
                }catch (Exception e){
                    imageView.setImageResource(R.drawable.luggageicon);
                }
                addView(textView,imageView,200,200);

            }
            Log.i("course","course");
            try{
                Picasso.get().load(detailRepeat_25.repeat_array.get(0).getSubdetailimg()).placeholder(R.drawable.luggageicon).into(presentimageview);
                third.setText(detailRepeat_25.repeat_array.get(0).getSubdetailoverview());
                title.setText(detailRepeat_25.repeat_array.get(0).getSubdetailalt());
            }catch (Exception e){
                presentimageview.setImageResource(R.drawable.luggageicon);
            }

        }
        else if(contenttypeid.equals("32")){
            third.setText(detailRepeat_32.repeat_array.get(0).getRoomtitle() + "\n" + detailRepeat_32.repeat_array.get(0).getRoombasecount());
        }
        else {
            String repeat = "";
            for (int i = 0; i < detailRepeat.repeat_array.size(); i++) {
                repeat = repeat + detailRepeat.repeat_array.get(i).getInfoname() + ": " + detailRepeat.repeat_array.get(i).getInfotext() + "\n\n";
            }
            third.setText(repeat);
        }

        //Toast.makeText(getContext(), detailRepeat.repeat_array.size()+"", Toast.LENGTH_SHORT).show();
        return view;
    }
    public void addView(TextView textView,ImageView imageView,int width, int height){
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width,height);
        layoutParams.setMargins(10,10,10,10);

        LinearLayout InlinearLayout = new LinearLayout(getContext());
        InlinearLayout.setOrientation(LinearLayout.VERTICAL);
        imageView.setLayoutParams(layoutParams);
        InlinearLayout.addView(imageView);
        InlinearLayout.addView(textView);
        imageView.setLayoutParams(layoutParams);
        linearLayout.addView(InlinearLayout);
    }
}
