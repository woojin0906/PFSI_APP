package kr.co.company.pfsi_app;
// 장애정보지원 리스트 액티비티 (2023-05-12 우진)
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class SupportProgramInfoActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SupportInfoAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<SupportInfoData> arrayList;

    private EditText etSearch;
    private Button btnSearch;
    private String search = "notSearch";
    private Spinner spinner;

    private String key="e9c8196a7d2b452ba115f668e045546a";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support_program_info);

        spinner = (Spinner)findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                search = (String) parent.getItemAtPosition(position);

                // 리스트 item초기화
                arrayList = new ArrayList<>();
                adapter = new SupportInfoAdapter(arrayList);
                recyclerView.setAdapter(adapter);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        getXmlData(search);

                        (SupportProgramInfoActivity.this).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter.notifyDataSetChanged();
                            }
                        });
                    }
                }).start();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // List 설정
        recyclerView = findViewById(R.id.rvSupportInfoList);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        arrayList = new ArrayList<>();

        adapter = new SupportInfoAdapter(arrayList);
        recyclerView.setAdapter(adapter);

        etSearch = findViewById(R.id.etSearch);
        btnSearch = findViewById(R.id.btnSearch);

        new Thread(new Runnable() {
            @Override
            public void run() {
                getXmlData(search);

                (SupportProgramInfoActivity.this).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search = etSearch.getText().toString();

                // 리스트 item초기화
                arrayList = new ArrayList<>();
                adapter = new SupportInfoAdapter(arrayList);
                recyclerView.setAdapter(adapter);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        getXmlData(search);

                        (SupportProgramInfoActivity.this).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter.notifyDataSetChanged();
                            }
                        });
                    }
                }).start();
            }
        });
    }

    public void getXmlData(String search) {
        String queryUrl="https://openapi.gg.go.kr/DspsnCmwelfctOpertProg?KEY="+key+"";
        try{
            URL url= new URL(queryUrl); //문자열로 된 요청 url을 URL 객체로 생성.
            InputStream is= url.openStream(); //url위치로 입력스트림 연결

            XmlPullParserFactory factory= XmlPullParserFactory.newInstance();//xml파싱을 위한
            XmlPullParser xpp= factory.newPullParser();
            xpp.setInput( new InputStreamReader(is, "UTF-8") ); //inputstream 으로부터 xml 입력받기

            String tag, city = "", group = "", peopleState = "", peopleStateInfo = "", category = "", categoryInfo = "", programTitle = "", time = "", money = "", address = "", phone = "", programContent = "", latitude = "", longitude = "";

            xpp.next();
            int eventType= xpp.getEventType();
            while( eventType != XmlPullParser.END_DOCUMENT ){
                switch( eventType ){
                    case XmlPullParser.START_DOCUMENT: // 시작
                        break;
                    case XmlPullParser.START_TAG:
                        tag= xpp.getName(); //태그 이름 얻어오기

                        if(tag.equals("row")); // 첫번째 검색결과
                        else if(tag.equals("SIGUN_NM")){ // 시군명
                            xpp.next();
                            city = xpp.getText();
                        }
                        else if(tag.equals("CMWELFCT_NM_INFO")){ // 복지관명
                            xpp.next();
                            group = xpp.getText();
                        }
                        else if(tag.equals("USE_TARGET")){ // 이용대상
                            xpp.next();
                            peopleState = xpp.getText();
                        }
                        else if(tag.equals("USE_TARGET_OBSTCL_TYPE_COND")){ // 이용대상상세조건
                            xpp.next();
                            peopleStateInfo = xpp.getText();
                        }
                        else if(tag.equals("PROG_DIV_NM")){ // 구분
                            xpp.next();
                            category = xpp.getText();
                        }
                        else if(tag.equals("DETAIL_DIV_NM")){ // 상세구분
                            xpp.next();
                            categoryInfo = xpp.getText();
                        }
                        else if(tag.equals("PROG_TITLE")){ // 프로그램명
                            xpp.next();
                            programTitle = xpp.getText();
                        }
                        else if(tag.equals("USE_TM_INFO")){ // 이용시간
                            xpp.next();
                            time = xpp.getText();
                        }
                        else if(tag.equals("USE_AMT")){ // 이용금액
                            xpp.next();
                            money = xpp.getText();
                        }
                        else if(tag.equals("REFINE_ROADNM_ADDR")){ // 주소
                            xpp.next();
                            address = xpp.getText();
                        }
                        else if(tag.equals("TELNO")){ // 전화번호
                            xpp.next();
                            phone = xpp.getText();
                        }
                        else if(tag.equals("PROG_CONT")){ // 프로그램내용
                            xpp.next();
                            programContent = xpp.getText();
                        }
                        else if(tag.equals("REFINE_WGS84_LAT")){ // 위도
                            xpp.next();
                            latitude = xpp.getText();
                        }
                        else if(tag.equals("REFINE_WGS84_LOGT")){ // 경도
                            xpp.next();
                            longitude = xpp.getText();
                        }
                        break;

                    case XmlPullParser.TEXT:
                        break;

                    case XmlPullParser.END_TAG:
                        tag= xpp.getName(); //테그 이름 얻어오기

                        if(tag.equals("row")) {
                            SupportInfoData mainData = new SupportInfoData(city, group, peopleState, peopleStateInfo, category, categoryInfo, programTitle, time, money, address, phone, programContent, latitude, longitude);

                            // 검색 데이터가 없다면 모두 출력
                            if(search.equals("notSearch")) {
                                arrayList.add(mainData);
                            } else if(search.equals("전체")) {
                                arrayList.add(mainData);
                            }
                            else{
                                // 제목에 검색어를 포함하는 정보만 추가
                                if(programTitle.contains(search)){
                                    arrayList.add(mainData);
                                }
                                if(city.contains(search)) {
                                    arrayList.add(mainData);
                                }
                            }

                        }// 첫번째 검색결과종료
                        break;
                }

                eventType= xpp.next();
            }

        } catch (Exception e){
            e.printStackTrace();
        }
        return;
    }
}