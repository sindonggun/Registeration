package comskwmqk.naver.httpblog.registeration;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017-08-08.
 */
public class ValidateRequest extends StringRequest {
    final static private String URL="http://zlskqk.cafe24.com/UserValidate.php";
    private Map<String,String> parameters;
//중복체크 클레스
    public ValidateRequest(String userID,Response.Listener<String>listener) {
        //해당요청숨겨서 보내준다
        super(Method.POST,URL,listener,null);
        parameters=new HashMap<>();
        parameters.put("userID",userID);
    }

    @Override
    public Map<String,String> getParams(){
        return parameters;
    }

}