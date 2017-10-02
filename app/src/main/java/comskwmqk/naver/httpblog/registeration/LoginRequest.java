package comskwmqk.naver.httpblog.registeration;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {
    final static private String URL="http://zlskqk.cafe24.com/UserLogin.php";
    private Map<String,String> parameters;

    public  LoginRequest(String userID,String userPassword,Response.Listener<String> listener) {
        //해당요청숨겨서 보내준다
        super(Request.Method.POST,URL,listener,null);
        parameters=new HashMap<>();
        parameters.put("userID",userID);
        parameters.put("userPassword",userPassword);
    }

    @Override
    public Map<String,String>getParams(){
        return parameters;
    }
}
