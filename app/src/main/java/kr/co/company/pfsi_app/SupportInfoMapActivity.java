package kr.co.company.pfsi_app;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import com.skt.Tmap.TMapGpsManager;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapPolyLine;
import com.skt.Tmap.TMapView;

import java.util.ArrayList;

public class SupportInfoMapActivity extends AppCompatActivity implements TMapGpsManager.onLocationChangedCallback {

    private TMapView tMapView = null;
    private TMapGpsManager tmapgps = null;
    private String api_key = "WDUOyZdacml2BjQXwZJL2PDCzymCNXM4U6HOF5td";
    private Button button;
    private ArrayList<TMapPoint> alTMapPoint = new ArrayList<TMapPoint>();
    private double Latitude;
    private double Longitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support_info_map);

        Intent receiveIntent = getIntent();
        final String latitude = receiveIntent.getStringExtra("latitude");
        final String longitude = receiveIntent.getStringExtra("longitude");
        final String group = receiveIntent.getStringExtra("group");

        Latitude = Double.parseDouble(latitude);
        Longitude = Double.parseDouble(longitude);
        Log.d(">>>>>>", latitude);
        // GPS 권한 요청
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }

        LinearLayout linearLayoutTmap = (LinearLayout)findViewById(R.id.linearLayoutTmap);
        tMapView = new TMapView(this);

        tMapView.setSKTMapApiKey( api_key );
        linearLayoutTmap.addView( tMapView );

        tMapView.setZoomLevel(15);
        tMapView.setIconVisibility(true);
        tMapView.setMapType(TMapView.MAPTYPE_STANDARD);
        tMapView.setLanguage(TMapView.LANGUAGE_KOREAN);

        tmapgps = new TMapGpsManager(this);
        tmapgps.setMinTime(1000);              // 일정 시간마다 리셋
        tmapgps.setMinDistance(1);             // 일정 거리마다 리셋
        tmapgps.setProvider(tmapgps.NETWORK_PROVIDER); // 네트워크에 맞춰 현재 위치 표시  -> 디바이스로 실행할 때 사용
//        tmapgps.setProvider(tmapgps.GPS_PROVIDER);       //GPS  ->  애뮬레이터로 실행할 때 사용

        // 화면중심을 단말의 현재위치로 이동
        tMapView.setTrackingMode(true);
        tMapView.setSightVisible(true);

        // 주소 마커
        TMapPoint point = new TMapPoint(Latitude, Longitude);
        TMapMarkerItem markerItem1 = new TMapMarkerItem();
        markerItem1.setPosition(0.5f, 1.0f);

        markerItem1.setCanShowCallout(true);
        markerItem1.setAutoCalloutVisible(true);

        markerItem1.setTMapPoint(point);
        markerItem1.setName(group);
        tMapView.addMarkerItem("markerItem1", markerItem1);


        alTMapPoint.add(point); //가져온 경도,위도를 Point에 추가

        TMapPolyLine tMapPolyLine = new TMapPolyLine();
        tMapPolyLine.setLineColor(Color.RED);
        tMapPolyLine.setLineWidth(10);

        for( int i=0; i<alTMapPoint.size(); i++ ) {
            tMapPolyLine.addLinePoint( alTMapPoint.get(i) );
        }
        tMapView.addTMapPolyLine("Line1", tMapPolyLine); // point값을 polyLine로 그림

        // 현재위치로 돌아가는 버튼(TextView)
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  // 화면중심을 단말의 현재위치로 이동
                  tMapView.setTrackingMode(true);
                  tMapView.setSightVisible(true);
                }
        });

    }

    @Override
    public void onLocationChange(Location location) {
        tMapView.setLocationPoint(location.getLongitude(), location.getLatitude());
        tMapView.setCenterPoint(location.getLongitude(), location.getLatitude());
    }
}