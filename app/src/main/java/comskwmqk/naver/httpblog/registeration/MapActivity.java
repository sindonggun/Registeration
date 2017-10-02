package comskwmqk.naver.httpblog.registeration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MapActivity extends AppCompatActivity {
    private ListView listView;
    private MapListAdapter adapter;
    private List<Map> mapList;
    private List<Map> saveList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        Intent intent=getIntent();

        listView=(ListView)findViewById(R.id.listView);
        mapList=new ArrayList<Map>();
        saveList=new ArrayList<Map>();

      //  mapList.add(new Map(R.drawable.campus_img_03,"60주년","강의실1","강의실2","강의실3","강의실4","강의실5","강의실6","강의실7"));
        adapter=new MapListAdapter(getApplicationContext(),mapList,this,saveList);
        listView.setAdapter(adapter);

        try{
            JSONObject jsonObject=new JSONObject(intent.getStringExtra("mapList"));
            JSONArray jsonArray=jsonObject.getJSONArray("response");
            int count=0;
            int imagesrc=0;
            String imagetitle,floor1,floor2,floor3,floor4,floor5,floor6,floor7;
            while(count<jsonArray.length()){
                JSONObject object=jsonArray.getJSONObject(count);
                imagesrc=object.getInt("imagesrc");
                imagetitle=object.getString("imagetitle");
                floor1=object.getString("floor1");
                floor2=object.getString("floor2");
                floor3=object.getString("floor3");
                floor4=object.getString("floor4");
                floor5=object.getString("floor5");
                floor6=object.getString("floor6");
                floor7=object.getString("floor7");
                Map map=new Map(imagesrc,imagetitle,floor1,floor2,floor3,floor4,floor5,floor6,floor7);
                mapList.add(map);
                saveList.add(map);
                count++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        EditText search=(EditText)findViewById(R.id.search);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //변하기전
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchUser(s.toString());
                //찾기 실행
            }

            @Override
            public void afterTextChanged(Editable s) {
                //변한후
            }
        });


    }
    public void searchUser(String search){
        mapList.clear();
        //리스트초기화
        for (int i=0;i<saveList.size();i++){
            if(saveList.get(i).getName().contains(search)){
                mapList.add(saveList.get(i));
                //값생성
            }
        }
        //값변경
        adapter.notifyDataSetChanged();
    }
}
