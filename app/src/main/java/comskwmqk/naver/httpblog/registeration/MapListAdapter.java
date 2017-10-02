package comskwmqk.naver.httpblog.registeration;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2017-09-22.
 */

public class MapListAdapter extends BaseAdapter {
    private static final String TAG = "Building";
    private Context context;
    //Map클래스 메소드 리스트
    private List<Map> mapList;
    private Activity parentActivity;
    private List<Map> saveList;
    public MapListAdapter(Context context,List<Map>mapList,Activity parentActivity,List<Map>saveList){
        this.context=context;
        this.mapList=mapList;
        this.parentActivity=parentActivity;
        this.saveList=saveList;
    }
    @Override
    public int getCount() {
        return mapList.size();
    }

    @Override
    public Object getItem(int position) {
        return mapList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v=View.inflate(context,R.layout.map,null);
        ImageView imagesrc=(ImageView)v.findViewById(R.id.imageView3);
        TextView imagetitle=(TextView)v.findViewById(R.id.imagetitle);
        TextView floor1=(TextView)v.findViewById(R.id.floor1);
        TextView floor2=(TextView)v.findViewById(R.id.floor2);
        TextView floor3=(TextView)v.findViewById(R.id.floor3);
        TextView floor4=(TextView)v.findViewById(R.id.floor4);
        TextView floor5=(TextView)v.findViewById(R.id.floor5);
        TextView floor6=(TextView)v.findViewById(R.id.floor6);
        TextView floor7=(TextView)v.findViewById(R.id.floor7);

        imagesrc.setImageResource(mapList.get(position).getImagesrc());
        Log.v(TAG, "index=" + mapList.get(position).getImagesrc());
        imagetitle.setText(mapList.get(position).getName());
        floor1.setText(mapList.get(position).getFloor1());
        floor2.setText(mapList.get(position).getFloor2());
        floor3.setText(mapList.get(position).getFloor3());
        floor4.setText(mapList.get(position).getFloor4());
        floor5.setText(mapList.get(position).getFloor5());
        floor6.setText(mapList.get(position).getFloor6());
        floor7.setText(mapList.get(position).getFloor7());

        v.setTag(mapList.get(position).getFloor1());
        return v;
    }


}
