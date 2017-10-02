package comskwmqk.naver.httpblog.registeration;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Administrator on 2017-08-15.
 */

public class Pop extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop);

        DisplayMetrics ds=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(ds);

        int width=ds.widthPixels;
        int height=ds.heightPixels;

        getWindow().setLayout((int)(width*0.9),(int)(height*0.85));

        TextView addressTextview = (TextView)findViewById(R.id.addresstextview);
        addressTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://home.kangwon.ac.kr/main/html/main/main.jsp"));
                startActivity(myintent);
            }
        });
    }
}
