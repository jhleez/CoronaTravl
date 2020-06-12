package com.example.coronatravel.detail.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.coronatravel.R;
import com.example.coronatravel.detail.detailCommon;
import com.example.coronatravel.detail.detailInfo_12;
import com.example.coronatravel.detail.detailInfo_14;
import com.example.coronatravel.detail.detailInfo_15;
import com.example.coronatravel.detail.detailInfo_25;
import com.example.coronatravel.detail.detailInfo_28;
import com.example.coronatravel.detail.detailInfo_32;
import com.example.coronatravel.detail.detailInfo_38;
import com.example.coronatravel.detail.detailInfo_39;

import org.w3c.dom.Text;

public class Detail_Second_Fragment extends Fragment {
    detailInfo_12 detail_I_12;
    detailInfo_14 detail_I_14;
    detailInfo_15 detail_I_15;
    detailInfo_25 detail_I_25;
    detailInfo_28 detail_I_28;
    detailInfo_32 detail_I_32;
    detailInfo_38 detail_I_38;
    detailInfo_39 detail_I_39;
    String contenttypeid;
    String contentid;
    TextView second;

    LinearLayout detail12, detail14, detail15, detail25, detail28, detail32, detail38, detail39;
    TextView Babyseat12, Car12, Pet12, Experience12, People12, Age12, Openday12, Useingdays12, Break12, Useingtime12, Introduce12;
    TextView Babyseat14, Car14, Pet14, People14, Taketime14, Discount14, Break14, Introduce14;
    TextView Age15, Place15, Start15, End15, Program15, Positionintroduce15, Festival15, Reservation15, Discount15, Usemoney15;
    TextView Distance25, Taketime25;
    TextView Babyseat28, Age28, People28, Openday28, Usingtime28, Break28, Inputmoney28, Reservation28, Introduce28;
    TextView Intime32, Outtime32, Pickup32, Reservation32, payback32, Introduce32;
    TextView Babyseat38, Pet38, Card38, Jangday38, Openday38, Break38, Store38, Sellproduct38, Introduce38;
    TextView Representation39, Addtional39, Play39, Package39, Card39, Opentime39, Openday39, Introduce39;


    public Detail_Second_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_detail_second_, container, false);
        detail_I_12 = Data.detail_I_12;
        detail_I_14 = Data.detail_I_14;
        detail_I_15 = Data.detail_I_15;
        detail_I_25 = Data.detail_I_25;
        detail_I_28 = Data.detail_I_28;
        detail_I_32 = Data.detail_I_32;
        detail_I_38 = Data.detail_I_38;
        detail_I_39 = Data.detail_I_39;
        contentid = Data.contentid;
        contenttypeid = Data.contenttypeid;

        detail12 = view.findViewById(R.id.detail_12);
        detail14 = view.findViewById(R.id.detail_14);
        detail15 = view.findViewById(R.id.detail_15);
        detail25 = view.findViewById(R.id.detail_25);
        detail28 = view.findViewById(R.id.detail_28);
        detail32 = view.findViewById(R.id.detail_32);
        detail38 = view.findViewById(R.id.detail_38);
        detail39 = view.findViewById(R.id.detail_39);

        Babyseat12 = view.findViewById(R.id.detail_12_babyseat);
        Car12 = view.findViewById(R.id.detail_12_car);
        Pet12 = view.findViewById(R.id.detail_12_pet);
        Experience12 = view.findViewById(R.id.detail_12_experience);
        People12 = view.findViewById(R.id.detail_12_people);
        Age12 = view.findViewById(R.id.detail_12_age);
        Openday12 = view.findViewById(R.id.detail_12_openday);
        Useingdays12 = view.findViewById(R.id.detail_12_useingdays);
        Break12 = view.findViewById(R.id.detail_12_breakday);
        Useingtime12 = view.findViewById(R.id.detail_12_usingtime);
        Introduce12 = view.findViewById(R.id.detail_12_introduce);

        Babyseat14 = view.findViewById(R.id.detail_14_babyseat);
        Car14 = view.findViewById(R.id.detail_14_car);
        Pet14 = view.findViewById(R.id.detail_14_pet);
        People14 = view.findViewById(R.id.detail_14_people);
        Taketime14 = view.findViewById(R.id.detail_14_taketime);
        Discount14 = view.findViewById(R.id.detail_14_discount);
        Break14 = view.findViewById(R.id.detail_14_breakday);
        Introduce14 = view.findViewById(R.id.detail_14_introduce);

        Age15 = view.findViewById(R.id.detail_15_age);
        Place15 = view.findViewById(R.id.detail_15_place);
        Start15 = view.findViewById(R.id.detail_15_start);
        End15 = view.findViewById(R.id.detail_15_end);
        Program15 = view.findViewById(R.id.detail_15_program);
        Positionintroduce15 = view.findViewById(R.id.detail_15_positionintroduce);
        Festival15 = view.findViewById(R.id.detail_15_fetivaltime);
        Reservation15 = view.findViewById(R.id.detail_15_reservation);
        Discount15 = view.findViewById(R.id.detail_15_discount);
        Usemoney15 = view.findViewById(R.id.detail_15_usemoney);

        Distance25 = view.findViewById(R.id.detail_25_distance);
        Taketime25 = view.findViewById(R.id.detail_25_taketime);


        Babyseat28 = view.findViewById(R.id.detail_28_babyseat);
        Age28 = view.findViewById(R.id.detail_28_age);
        People28 = view.findViewById(R.id.detail_28_people);
        Openday28 = view.findViewById(R.id.detail_28_openday);
        Usingtime28 = view.findViewById(R.id.detail_28_usingtime);
        Break28 = view.findViewById(R.id.detail_28_breakday);
        Inputmoney28 = view.findViewById(R.id.detail_28_inputmoney);
        Reservation28 = view.findViewById(R.id.detail_28_reservation);
        Introduce28 = view.findViewById(R.id.detail_28_introduce);


        Intime32 = view.findViewById(R.id.detail_32_intime);
        Outtime32 = view.findViewById(R.id.detail_32_outtime);
        Pickup32 = view.findViewById(R.id.detail_32_pickup);
        Reservation32 = view.findViewById(R.id.detail_32_reservation);
        payback32 = view.findViewById(R.id.detail_32_payback);
        Introduce32 = view.findViewById(R.id.detail_32_introuduce);

        Babyseat38 = view.findViewById(R.id.detail_38_babyseat);
        Pet38 = view.findViewById(R.id.detail_38_pet);
        Card38 = view.findViewById(R.id.detail_38_card);
        Jangday38 = view.findViewById(R.id.detail_38_jangday);
        Openday38 = view.findViewById(R.id.detail_38_opendays);
        Break38 = view.findViewById(R.id.detail_38_breakday);
        Store38 = view.findViewById(R.id.detail_38_storeintroduce);
        Sellproduct38 = view.findViewById(R.id.detail_38_sellproduct);
        Introduce38 = view.findViewById(R.id.detail_38_introduce);

        Representation39 = view.findViewById(R.id.detail_39_represent);
        Addtional39 = view.findViewById(R.id.detail_39_addition);
        Play39 = view.findViewById(R.id.detail_39_play);
        Package39 = view.findViewById(R.id.detail_39_package);
        Card39 = view.findViewById(R.id.detail_39_card);
        Opentime39 = view.findViewById(R.id.detail_39_opentime);
        Openday39 = view.findViewById(R.id.detail_39_opendays);
        Introduce39 = view.findViewById(R.id.detail_39_introduce);


        second = view.findViewById(R.id.second);
        second.setText("");
        if (contenttypeid.equals("12")) {
            detail12.setVisibility(View.VISIBLE);
            detail14.setVisibility(View.GONE);
            detail15.setVisibility(View.GONE);
            detail25.setVisibility(View.GONE);
            detail28.setVisibility(View.GONE);
            Babyseat12.setText(judge(detail_I_12.getChkbabycarriage()));
            Car12.setText(judge(detail_I_12.getParking()));
            Pet12.setText(judge(detail_I_12.getChkpet()));
            Experience12.setText(judge(detail_I_12.getExpguide()));
            People12.setText(judge(detail_I_12.getAccomcount()));
            Age12.setText(judge(detail_I_12.getExpguide()));
            Openday12.setText(judge(detail_I_12.getOpendate()));
            Break12.setText(judge(detail_I_12.getRestdate()));
            Useingdays12.setText(judge(detail_I_12.getUseseason()));
            Useingtime12.setText(judge(detail_I_12.getUsetime()));
            Introduce12.setText(judge(detail_I_12.getExpagerange()));

        } else if (contenttypeid.equals("14")) {
            detail12.setVisibility(View.GONE);
            detail14.setVisibility(View.VISIBLE);
            detail15.setVisibility(View.GONE);
            detail25.setVisibility(View.GONE);
            detail28.setVisibility(View.GONE);
            detail32.setVisibility(View.GONE);
            detail38.setVisibility(View.GONE);
            detail39.setVisibility(View.GONE);
            Babyseat14.setText(judge(detail_I_14.getChkbabycarriageculture()));
            Car14.setText(judge(detail_I_14.getParkingculture()));
            Pet14.setText(judge(detail_I_14.getChkpetculture()));
            People14.setText(judge(detail_I_14.getAccomcountculture()));
            Taketime14.setText(judge(detail_I_14.getSpendtime()));
            Discount14.setText(judge(detail_I_14.getDiscountinfo()));
            Break14.setText(judge(detail_I_14.getRestdateculture()));
            Introduce14.setText(judge(detail_I_14.getInfocenterculture()));
        } else if (contenttypeid.equals("15")) {
            detail12.setVisibility(View.GONE);
            detail14.setVisibility(View.GONE);
            detail15.setVisibility(View.VISIBLE);
            detail25.setVisibility(View.GONE);
            detail28.setVisibility(View.GONE);
            detail32.setVisibility(View.GONE);
            detail38.setVisibility(View.GONE);
            detail39.setVisibility(View.GONE);
            Age15.setText(judge(detail_I_15.getAgelimit()));
            Place15.setText(judge(detail_I_15.getEventplace()));
            Start15.setText(judge(detail_I_15.getEventstartdate()));
            End15.setText(judge(detail_I_15.getEventenddate()));
            Program15.setText(judge(detail_I_15.getProgram()));
            Positionintroduce15.setText(judge(detail_I_15.getEventplace()));
            Festival15.setText(judge(detail_I_15.getPlaytime()));
            Reservation15.setText(judge(detail_I_15.getBookingplace()));
            Discount15.setText(judge(detail_I_15.getDiscountinfofestival()));
            Usemoney15.setText(judge(detail_I_15.getUsetimefestival()));
        } else if (contenttypeid.equals("25")) {
            detail12.setVisibility(View.GONE);
            detail14.setVisibility(View.GONE);
            detail15.setVisibility(View.GONE);
            detail25.setVisibility(View.VISIBLE);
            detail28.setVisibility(View.GONE);
            detail32.setVisibility(View.GONE);
            detail38.setVisibility(View.GONE);
            detail39.setVisibility(View.GONE);
            Distance25.setText(judge(detail_I_25.getDistance()));
            Taketime25.setText(judge(detail_I_25.getTaketime()));
        } else if (contenttypeid.equals("28")) {
            detail12.setVisibility(View.GONE);
            detail14.setVisibility(View.GONE);
            detail15.setVisibility(View.GONE);
            detail25.setVisibility(View.GONE);
            detail28.setVisibility(View.VISIBLE);
            detail32.setVisibility(View.GONE);
            detail38.setVisibility(View.GONE);
            detail39.setVisibility(View.GONE);
            Babyseat28.setText(judge(detail_I_28.getChkbabycarriageleports()));
            Age28.setText(judge(detail_I_28.getExpagerangeleports()));
            People28.setText(judge(detail_I_28.getAccomcountleports()));
            Openday28.setText(judge(detail_I_28.getOpenperiod()));
            Usingtime28.setText(judge(detail_I_28.getUsetimeleports()));
            Break28.setText(judge(detail_I_28.getRestdateleports()));
            Inputmoney28.setText(judge(detail_I_28.getUsefeeleports()));
            Reservation28.setText(judge(detail_I_28.getReservation()));
            Introduce28.setText(judge(detail_I_28.getInfocenterleports()));

        } else if (contenttypeid.equals("32")) {
            detail12.setVisibility(View.GONE);
            detail14.setVisibility(View.GONE);
            detail15.setVisibility(View.GONE);
            detail25.setVisibility(View.GONE);
            detail28.setVisibility(View.GONE);
            detail32.setVisibility(View.VISIBLE);
            detail38.setVisibility(View.GONE);
            detail39.setVisibility(View.GONE);
            Intime32.setText(judge(detail_I_32.getCheckintime()));
            Outtime32.setText(judge(detail_I_32.getCheckouttime()));
            Pickup32.setText(judge(detail_I_32.getPickup()));
            Reservation32.setText(judge(detail_I_32.getReservationurl()));
            payback32.setText(judge(detail_I_32.getRefundregulation()));
            Introduce32.setText(judge(detail_I_32.getInfocenterlodging()));

        } else if (contenttypeid.equals("38")) {
            detail12.setVisibility(View.GONE);
            detail14.setVisibility(View.GONE);
            detail15.setVisibility(View.GONE);
            detail25.setVisibility(View.GONE);
            detail28.setVisibility(View.GONE);
            detail32.setVisibility(View.GONE);
            detail38.setVisibility(View.VISIBLE);
            detail39.setVisibility(View.GONE);
            Babyseat38.setText(judge(detail_I_38.getChkbabycarriageshopping()));
            Pet38.setText(judge(detail_I_38.getChkpetshopping()));
            Card38.setText(judge(detail_I_38.getChkcreditcardshopping()));
            Jangday38.setText(judge(detail_I_38.getFairday()));
            Openday38.setText(judge(detail_I_38.getOpendateshopping()));
            Break38.setText(judge(detail_I_38.getRestdateshopping()));
            Store38.setText(judge(detail_I_38.getShopguide()));
            Sellproduct38.setText(judge(detail_I_38.getSaleitem()));
            Introduce38.setText(judge(detail_I_38.getInfocentershopping()));

        } else if (contenttypeid.equals("39")) {
            String kids = detail_I_39.getKidsfacility();
            if (kids.equals("0")) {
                kids = "없음";
            } else {
                kids = "있음";
            }
            detail12.setVisibility(View.GONE);
            detail14.setVisibility(View.GONE);
            detail15.setVisibility(View.GONE);
            detail25.setVisibility(View.GONE);
            detail28.setVisibility(View.GONE);
            detail32.setVisibility(View.GONE);
            detail38.setVisibility(View.GONE);
            detail39.setVisibility(View.VISIBLE);
            Representation39.setText(judge(detail_I_39.getFirstmenu()));
            Addtional39.setText(judge(detail_I_39.getTreatmenu()));
            Play39.setText(kids);
            Package39.setText(judge(detail_I_39.getPacking()));
            Card39.setText(judge(detail_I_39.getChkcreditcardfood()));
            Opentime39.setText(judge(detail_I_39.getOpentimefood()));
            Openday39.setText(judge(detail_I_39.getOpendatefood()));
            Introduce39.setText(judge(detail_I_39.getInfocenterfood()));

        }
        return view;
    }


    private String judge(String data) {
        if (data.length() == 0) return " - ";
        return data;
    }
}
