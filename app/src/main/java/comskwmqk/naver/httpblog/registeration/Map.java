package comskwmqk.naver.httpblog.registeration;

import android.util.Log;
import android.widget.ImageView;

import java.net.URL;

/**
 * Created by Administrator on 2017-09-22.
 */

public class Map {
    int imagesrc;
    String imagetitle;
    String floor1;
    String floor2;
    String floor3;
    String floor4;
    String floor5;
    String floor6;
    String floor7;

    public Map(int imagesrc,String imagetitle, String floor1, String floor2, String floor3, String floor4, String floor5, String floor6, String floor7) {
        this.imagesrc = imagesrc;
        this.imagetitle=imagetitle;
        this.floor1 = floor1;
        this.floor2 = floor2;
        this.floor3 = floor3;
        this.floor4 = floor4;
        this.floor5 = floor5;
        this.floor6 = floor6;
        this.floor7 = floor7;
    }



    public int getImagesrc() {
        return imagesrc;
    }

    public void setImagesrc(int imagesrc) {
        this.imagesrc = imagesrc;
    }

    public String getName() {
        return imagetitle;
    }

    public void setName(String imagetitle) {
        this.imagetitle = imagetitle;
    }

    public String getFloor1() {
        return floor1;
    }

    public void setFloor1(String floor1) {
        this.floor1 = floor1;
    }

    public String getFloor2() {
        return floor2;
    }

    public void setFloor2(String floor2) {
        this.floor2 = floor2;
    }

    public String getFloor3() {
        return floor3;
    }

    public void setFloor3(String floor3) {
        this.floor3 = floor3;
    }

    public String getFloor4() {
        return floor4;
    }

    public void setFloor4(String floor4) {
        this.floor4 = floor4;
    }

    public String getFloor5() {
        return floor5;
    }

    public void setFloor5(String floor5) {
        this.floor5 = floor5;
    }

    public String getFloor6() {
        return floor6;
    }

    public void setFloor6(String floor6) {
        this.floor6 = floor6;
    }

    public String getFloor7() {
        return floor7;
    }

    public void setFloor7(String floor7) {
        this.floor7 = floor7;
    }

}
