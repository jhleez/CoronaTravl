package com.example.coronatravel.detail.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        second = view.findViewById(R.id.second);
        second.setText("");
        if (contenttypeid.equals("12")) {
            second.setText("유모차 대여 정보 : " + detail_I_12.getChkbabycarriage() +
                    "\n주차 시설 : " + detail_I_12.getParking() +
                    "\n애완동물 동반 가능 정보 : " + detail_I_12.getChkpet() +
                    "\n\n체험 안내 : " + detail_I_12.getExpguide() +
                    "\n수용 인원 : " + detail_I_12.getAccomcount() +
                    "\n체험가능 연령: " + detail_I_12.getExpguide() +
                    "\n\n개장일: " + detail_I_12.getOpendate() +
                    "\n쉬는 날: " + detail_I_12.getRestdate() +
                    "\n이용 시기: " + detail_I_12.getUseseason() +
                    "\n이용 시간: " + detail_I_12.getUsetime() +
                    "\n문의 및 안내: " + detail_I_12.getInfocenter());
        }

        else if (contenttypeid.equals("14")) {
            second.setText("유모차 대여 정보 : " + detail_I_14.getChkbabycarriageculture() +
                    "\n주차 시설 : " + detail_I_14.getParkingculture() +
                    "\n애완동물 동반 가능 정보 : " + detail_I_14.getChkpetculture() +
                    "\n\n수용 인원: " + detail_I_14.getAccomcountculture() +
                    "\n관람 소요 시간 : " + detail_I_14.getSpendtime() +
                    "\n할인 정보 :\n" + detail_I_14.getDiscountinfo() +
                    "\n\n쉬는 날 : " + detail_I_14.getRestdateculture() +
                    "\n문의 및 안내 : " + detail_I_14.getInfocenterculture());
        }

        else if (contenttypeid.equals("15")) {
            second.setText("관람 가능 연령 : " + detail_I_15.getAgelimit() +
                    "\n\n행사 장소 : " + detail_I_15.getEventplace() +
                    "\n행사 시작일: " + detail_I_15.getEventstartdate() +
                    "\n행사 종료일: " + detail_I_15.getEventenddate() +
                    "\n행사 프로그램: " + detail_I_15.getProgram() +
                    "\n행사장 위치 안내 :\n" + detail_I_15.getEventplace() +
                    "\n공연 시간 : " + detail_I_15.getPlaytime() +
                    "\n\n예매처 : " + detail_I_15.getBookingplace() +
                    "\n할인 정보 :\n" + detail_I_15.getDiscountinfofestival() +
                    "\n이용 요금 :" + detail_I_15.getUsetimefestival());
        }

        else if (contenttypeid.equals("25")) {
            second.setText("\n코스 총 거리 : " + detail_I_25.getDistance() +
                    "\n코스 총 소요 시간 : " + detail_I_25.getTaketime());
        }


        else if (contenttypeid.equals("28")) {
            second.setText("유모차 대여 정보: " + detail_I_28.getChkbabycarriageleports() +
                    "\n체험가능 연령 : " + detail_I_28.getExpagerangeleports() +
                    "\n\n수용 인원 : " + detail_I_28.getAccomcountleports() +
                    "\n개장 기간 : " + detail_I_28.getOpenperiod() +
                    "\n이용 시간 : " + detail_I_28.getUsetimeleports() +
                    "\n쉬는 날: " + detail_I_28.getRestdateleports() +
                    "\n\n입장료: " + detail_I_28.getUsefeeleports() +
                    "\n예약 안내 : " + detail_I_28.getReservation() +
                    "\n\n문의 및 안내 : " + detail_I_28.getInfocenterleports());
        }

        else if (contenttypeid.equals("32")) {
            second.setText("입실 시간: " + detail_I_32.getCheckintime() +
                    "\n퇴실 시간 : " + detail_I_32.getCheckouttime() +
                    "\n픽업 여부 : " + detail_I_32.getPickup() +
                    "\n\n예약 홈페이지: " + detail_I_32.getReservationurl() +
                    "\n환불 규정 : " + detail_I_32.getRefundregulation() +
                    "\n문의 및 안내 : " + detail_I_32.getInfocenterlodging());
        }

        else if (contenttypeid.equals("38")) {
            second.setText("유모차 대여 정보: " + detail_I_38.getChkbabycarriageshopping() +
                    "\n애완동물 동반 가능 여부: " + detail_I_38.getChkpetshopping() +
                    "\n신용카드 가능 정보 : " + detail_I_38.getChkcreditcardshopping() +
                    "\n\n장 서는 날: " + detail_I_38.getFairday() +
                    "\n개장일 : " + detail_I_38.getOpendateshopping() +
                    "\n쉬는 날 : " + detail_I_38.getRestdateshopping() +
                    "\n매장 안내 : " + detail_I_38.getShopguide() +
                    "\n판매 품목 : " + detail_I_38.getSaleitem() +
                    "\n문의 및 안내 : " + detail_I_38.getInfocentershopping());
        }

        else if (contenttypeid.equals("38")) {
            second.setText("유모차 대여 정보: " + detail_I_38.getChkbabycarriageshopping() +
                    "\n애완동물 동반 가능 여부: " + detail_I_38.getChkpetshopping() +
                    "\n신용카드 가능 정보 : " + detail_I_38.getChkcreditcardshopping() +
                    "\n\n장 서는 날: " + detail_I_38.getFairday() +
                    "\n쉬는 날 : " + detail_I_38.getRestdateshopping() +
                    "\n매장 안내 : " + detail_I_38.getShopguide() +
                    "\n\n판매 품목 : " + detail_I_38.getSaleitem() +
                    "\n문의 및 안내 : " + detail_I_38.getInfocentershopping());
        }

        else if (contenttypeid.equals("39")) {
            String kids = detail_I_39.getKidsfacility();
            if(kids.equals("0")){
                kids = "없음";
            }
            else{
                kids="있음";
            }
            second.setText("대표 메뉴: " + detail_I_39.getFirstmenu() +
                    "\n취급 메뉴: " + detail_I_39.getTreatmenu() +
                    "\n어린이 놀이방 여부: " + kids +
                    "\n포장가능 여부 : " + detail_I_39.getPacking() +
                    "\n신용카드 가능 여부: " + detail_I_39.getChkcreditcardfood() +
                    "\n영업시간 : " + detail_I_39.getOpentimefood() +
                    "\n영업일 : " + detail_I_39.getOpendatefood() +
                    "\n문의 및 안내 : " + detail_I_39.getInfocenterfood());
        }
        return view;
    }
}
