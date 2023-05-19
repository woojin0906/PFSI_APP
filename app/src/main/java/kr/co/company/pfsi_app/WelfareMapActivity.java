package kr.co.company.pfsi_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.skt.Tmap.TMapGpsManager;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapView;

import java.util.ArrayList;

public class WelfareMapActivity extends AppCompatActivity implements TMapGpsManager.onLocationChangedCallback {
    private static final int PERMISSION = 1;
    private String api_key = "WDUOyZdacml2BjQXwZJL2PDCzymCNXM4U6HOF5td";
    private TMapView tMapView = null;
    private TMapGpsManager tmapgps = null;

    private TMapPoint tpoint = null;
    private Button btnSetTrackingMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welfare_map);

        // 권한 설정
        if (Build.VERSION.SDK_INT >= 23) {
            ActivityCompat.requestPermissions(this, new String[]{
                    android.Manifest.permission.INTERNET,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, PERMISSION);
        }

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // T Map View
        FrameLayout frameLayoutTmap = (FrameLayout) findViewById(R.id.frameLayoutTmap);
        tMapView = new TMapView(this);

        // API Key
        tMapView.setSKTMapApiKey(api_key);
        frameLayoutTmap.addView(tMapView);
        multipleMarkers();

        tMapView.setZoomLevel(17);
        tMapView.setIconVisibility(true);
        tMapView.setMapType(TMapView.MAPTYPE_STANDARD);
        tMapView.setLanguage(TMapView.LANGUAGE_KOREAN);

        tmapgps = new TMapGpsManager(WelfareMapActivity.this);
        tmapgps.setMinTime(1000);
        tmapgps.setMinDistance(5);
        tmapgps.setProvider(tmapgps.NETWORK_PROVIDER);

        tMapView.setTrackingMode(true);
        tMapView.setSightVisible(true);

        tmapgps.OpenGps();

        // 내위치 버튼
        btnSetTrackingMode = findViewById(R.id.BtnSetTrackingMode);
        btnSetTrackingMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Toast.makeText(WelfareMapActivity.this, "내위치", Toast.LENGTH_SHORT).show();
                    tMapView.setTrackingMode(true);
                    tMapView.setSightVisible(true);
                    tMapView.setZoomLevel(17);
            }
        });
    }

    private void multipleMarkers() {
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
//                Bitmap bitmap = null;
//                bitmap = BitmapFactory.decodeResource(this.getResources(), R.mipmap.marker);

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
    }
}