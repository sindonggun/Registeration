package comskwmqk.naver.httpblog.registeration;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017-08-08.
 */

public class DeleteRequest extends StringRequest{
    final static private String URL="http://zlskqk.cafe24.com/ScheduleDelete.php";
    private Map<String,String> parameters;

    public DeleteRequest(String userID, String courseID, Response.Listener<String> listener) {
        //해당요청숨겨서 보내준다
        super(Method.POST,URL,listener,null);
        parameters=new HashMap<>();
        parameters.put("userID",userID);
        parameters.put("courseID",courseID);
    }

    @Override
    public Map<String,String>getParams(){
        return parameters;
    }
}
