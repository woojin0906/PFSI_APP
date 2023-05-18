package kr.co.company.pfsi_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.skt.Tmap.TMapGpsManager;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapView;

import java.util.ArrayList;

public class WelfareMapActivity extends AppCompatActivity implements TMapGpsManager.onLocationChangedCallback {
    private String api_key = "WDUOyZdacml2BjQXwZJL2PDCzymCNXM4U6HOF5td";
    private TMapView tMapView = null;
    private TMapGpsManager tmapgps = null;

    private TMapPoint tpoint = null;

    private Button btnSetTrackingMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welfare_map);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // T Map View
//        LinearLayout linearLayoutTmap = (LinearLayout) findViewById(R.id.linearLayoutTmap);
        FrameLayout frameLayoutTmap = (FrameLayout) findViewById(R.id.linearLayoutTmap);
        tMapView = new TMapView(this);

        // API Key
        tMapView.setSKTMapApiKey(api_key);
//        linearLayoutTmap.addView(tMapView);
        frameLayoutTmap.addView(tMapView);
        setUpMap();

        tMapView.setZoomLevel(17);
        tMapView.setIconVisibility(true);
        tMapView.setMapType(TMapView.MAPTYPE_STANDARD);
        tMapView.setLanguage(TMapView.LANGUAGE_KOREAN);

        tmapgps = new TMapGpsManager(WelfareMapActivity.this);
        tmapgps.setMinTime(1000);              // 일정 시간마다 리셋
        tmapgps.setMinDistance(5);             // 일정 거리마다 리셋
        tmapgps.setProvider(tmapgps.NETWORK_PROVIDER); // 네트워크에 맞춰 현재 위치 표시  -> 디바이스로 실행할 때 사용

        tMapView.setTrackingMode(true);
        tMapView.setSightVisible(true);

        tmapgps.OpenGps();

        // 현재위치로 돌아가는 버튼(TextView)
        btnSetTrackingMode = findViewById(R.id.BtnSetTrackingMode);
        btnSetTrackingMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Toast.makeText(WelfareMapActivity.this, "현재 내위치", Toast.LENGTH_SHORT).show();
                    tMapView.setTrackingMode(true);
                    tMapView.setSightVisible(true);
                    tMapView.setZoomLevel(17);
            }
        });
    }

    private void setUpMap() {
        WelfareMapApi parser = new WelfareMapApi();
        ArrayList<MapPoint> mapPoint = new ArrayList<MapPoint>();
        try {
            mapPoint = parser.apiParserSearch();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < mapPoint.size(); i++) {
            for (MapPoint entity : mapPoint) {
                TMapPoint point = new TMapPoint(mapPoint.get(i).getLatitude(), mapPoint.get(i).getLongitude());
                TMapMarkerItem item = new TMapMarkerItem();
//                Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.marker);

                item.setPosition(0.5f, 1.0f);
//                item.setIcon(bitmap);


                item.setCalloutTitle(mapPoint.get(i).getName());
                item.setCalloutSubTitle(mapPoint.get(i).getPhone());
                item.setCanShowCallout(true);
                item.setAutoCalloutVisible(true);

                item.setTMapPoint(point);
                item.setName(entity.getName());
                tMapView.setCenterPoint(mapPoint.get(i).getLongitude(), mapPoint.get(i).getLatitude());
                tMapView.addMarkerItem("item" + i, item);

            }
        }

    }

    @Override
    public void onLocationChange(Location location) {
        tMapView.setLocationPoint(location.getLongitude(), location.getLatitude());
//        tMapView.setCenterPoint(location.getLongitude(), location.getLatitude());

    }
}