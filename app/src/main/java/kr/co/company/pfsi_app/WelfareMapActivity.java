package kr.co.company.pfsi_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.skt.Tmap.TMapView;

public class WelfareMapActivity extends AppCompatActivity {

    private String api_key = "WDUOyZdacml2BjQXwZJL2PDCzymCNXM4U6HOF5td";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welfare_map);

        LinearLayout linearLayoutTmap = (LinearLayout)findViewById(R.id.linearLayoutTmap);
        TMapView tMapView = new TMapView(this);

        tMapView.setSKTMapApiKey( api_key );
        linearLayoutTmap.addView( tMapView );

    }
}