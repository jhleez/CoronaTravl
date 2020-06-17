package com.rammus.covidtravel.detail.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import com.rammus.covidtravel.R;
import com.rammus.covidtravel.detail.detailRepeat;
import com.rammus.covidtravel.detail.detailRepeat_25;
import com.rammus.covidtravel.detail.detailRepeat_32;
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
            }catch (Exception e){
                presentimageview.setImageResource(R.drawable.luggageicon);
            }finally {
                third.setText(detailRepeat_25.repeat_array.get(0).getSubdetailoverview());
                title.setText(detailRepeat_25.repeat_array.get(0).getSubdetailalt());
            }

        }
        else if(contenttypeid.equals("32")){
            title.setVisibility(View.VISIBLE);
            presentimageview.setVisibility(View.VISIBLE);
            Typeface typeface = ResourcesCompat.getFont(getContext(),R.font.binggraemelona);


            for(int i=0;i<detailRepeat_32.repeat_array.size();i++){
                final detailRepeat_32 item =detailRepeat_32.repeat_array.get(i);
                ImageView imageView= new ImageView(getContext());
                TextView textView = new TextView(getContext());
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                textView.setText(item.getRoomtitle());
                textView.setTypeface(typeface);

                textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try{
                            Picasso.get().load(item.getRoomimg1()).placeholder(R.drawable.luggageicon).into(presentimageview);
                        }catch (Exception e){
                            presentimageview.setImageResource(R.drawable.luggageicon);
                        }
                        third.setText("방 사이즈(평수) : "+item.getRoomsize1()+
                                " 평\n기준 인원 : "+item.getRoombasecount()+
                                " 명\n최대 인원 : "+ item.getRoommaxcount()+
                                " 명\n비수기 주중 최소 가격 : "+item.getRoomoffseasonminfee1()+
                                " 원\n비수기 주말 최소 가격 : "+ item.getRoomoffseasonminfee2()+
                                " 원\n비수기 주중 최소 가격 : "+ item.getRoompeakseasonminfee1()+
                                " 원\n비수기 주말 최소 가격 : "+item.getRoompeakseasonminfee2()+" 원");
                        title.setText(item.getRoomtitle());
                    }
                });
                try{
                    Picasso.get().load(item.getRoomimg1()).placeholder(R.drawable.luggageicon).into(imageView);
                }catch (Exception e){
                    imageView.setImageResource(R.drawable.luggageicon);
                }
                addView(textView,imageView,200,200);
            }
            Log.i("course","course");
            try{
                Picasso.get().load(detailRepeat_32.repeat_array.get(0).getRoomimg1()).placeholder(R.drawable.luggageicon).into(presentimageview);

            }catch (Exception e){
                presentimageview.setImageResource(R.drawable.luggageicon);
            }finally {
                third.setText("방 사이즈(평수) : "+detailRepeat_32.repeat_array.get(0).getRoomsize1()+
                        " 평\n기준 인원 : "+detailRepeat_32.repeat_array.get(0).getRoombasecount()+
                        " 명\n최대 인원 : "+ detailRepeat_32.repeat_array.get(0).getRoommaxcount()+
                        " 명\n비수기 주중 최소 가격 : "+detailRepeat_32.repeat_array.get(0).getRoomoffseasonminfee1()+
                        " 원\n비수기 주말 최소 가격 : "+ detailRepeat_32.repeat_array.get(0).getRoomoffseasonminfee2()+
                        " 원\n성수기 주중 최소 가격 : "+ detailRepeat_32.repeat_array.get(0).getRoompeakseasonminfee1()+
                        " 원\n성수기 주말 최소 가격 : "+detailRepeat_32.repeat_array.get(0).getRoompeakseasonminfee2()+" 원");
                title.setText(detailRepeat_32.repeat_array.get(0).getRoomtitle());
            }
        }
        else {
            String repeat = "";
            for (int i = 0; i < detailRepeat.repeat_array.size(); i++) {
                repeat = repeat + detailRepeat.repeat_array.get(i).getInfoname() + ": " + detailRepeat.repeat_array.get(i).getInfotext() + "\n\n";
            }
            third.setText(repeat);
        }
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
