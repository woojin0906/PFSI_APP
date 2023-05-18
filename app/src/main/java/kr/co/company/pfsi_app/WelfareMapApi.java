package kr.co.company.pfsi_app;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedInputStream;
import java.net.URL;
import java.util.ArrayList;

public class WelfareMapApi {
    private static String ServiceKey ="CasVQl8mXH1V1FmvDfVV3asi1kVz%2FPsFJVtHoq62DtlAfYfq5uprSYyTogMbhwFj4flLo%2B1hYXr08JKxu3ZaPg%3D%3D";
    public WelfareMapApi() {
        try {
            apiParserSearch();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ArrayList<MapPoint> apiParserSearch() throws Exception {
        URL url = new URL(getURLParam(null));

        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(true);
        XmlPullParser xpp= factory.newPullParser();
        BufferedInputStream bis = new BufferedInputStream(url.openStream());
        xpp.setInput(bis, "utf-8");

        String tag = null;
        int event_type = xpp.getEventType();

        ArrayList<MapPoint> mapPoint = new ArrayList<MapPoint>();

        String facility_name = null, phone=null, addr=null, longitude=null, latitude=null;
        boolean bName = false, bPhone = false, bAddr = false, bLongitude = false, bLatitude = false;

        while (event_type != XmlPullParser.END_DOCUMENT) {
            if(event_type == XmlPullParser.START_TAG) {
                tag = xpp.getName();
                if(tag.equals("col")){
                    String name = xpp.getAttributeValue(null, "name");
                    if(name.equals("시설명")){
                        bName = true;
                    } else if(name.equals("전화번호")){
                        bPhone = true;
                    } else if(name.equals("시설 주소")){
                        bAddr = true;
                    } else if(name.equals("엑스(X)좌표")){
                        bLongitude = true;
                    } else if(name.equals("와이(Y)좌표")){
                        bLatitude = true;
                    }
                }
            } else if(event_type == XmlPullParser.TEXT){
                if(bName){
                    facility_name = xpp.getText();
                    bName = false;
                }else if(bPhone){
                    phone = xpp.getText();
                    bPhone = false;
                }else if(bAddr) {
                    addr = xpp.getText();
                    bAddr = false;
                }else if(bLongitude) {
                    longitude = xpp.getText();
                    bLongitude = false;
                }else if(bLatitude) {
                    latitude = xpp.getText();
                    bLatitude = false;
                }
            }else if (event_type == XmlPullParser.END_TAG) {
                tag = xpp.getName();
                if(tag.equals("item")) {
                    MapPoint entity = new MapPoint();
                    entity.setName(facility_name);
                    entity.setLatitude(Double.valueOf(latitude));
                    entity.setLongitude(Double.valueOf(longitude));
                    entity.setPhone(phone);
                    entity.setAddr(addr);
                    mapPoint.add(entity);
                }
            } event_type = xpp.next();
        } System.out.println(mapPoint.size());
        return  mapPoint;
    }

    private  String getURLParam(String search) {
        String url = "https://api.odcloud.kr/api/15075529/v1/uddi:f153fd90-c36c-44d5-a9f1-8a0041cfa9b7?perPage=254&returnType=XML&serviceKey=" + ServiceKey;
        return  url;
    }

    public static void main(String[] args) {
        new WelfareMapApi();
    }
}
