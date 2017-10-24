package com.control.beapeak;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.model.bespeak.Bespeak;
import com.service.beapeak.BespeakService;
import com.service.interfaces.BespeakServiceInterface;
import com.until.errorcode.MAGICCODE;

public class CreateBespeakController implements Controller
{

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
    {

        Map<String, String> map = new HashMap<String, String>();
        JSONObject json = null;
        OutputStream stream = response.getOutputStream();
        String keyID = request.getParameter("keyID");
        if (null == keyID)
        {
            map.put("code", MAGICCODE.MAGIC_KEY_NULL);
            json = JSONObject.fromObject(map);
            stream.write(json.toString().getBytes("UTF-8"));
            return null;
        }

        String token = request.getParameter("token");
        if (null == token)
        {
            map.put("code", MAGICCODE.MAGIC_NOT_LOGIN);
            json = JSONObject.fromObject(map);
            stream.write(json.toString().getBytes("UTF-8"));
            return null;
        }

        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String number = request.getParameter("bespeak_number");
        String message = request.getParameter("message");
        String bespeakTime = request.getParameter("bespeak_time");
        String type = request.getParameter("bespeak_type");
        String content = request.getParameter("bespeak_content"); 
        Bespeak bespeak = new Bespeak();
        bespeak.setBespeak_time(bespeakTime);
        bespeak.setContent(content);
        bespeak.setMagicKey(keyID);
        bespeak.setMessage(message);
        bespeak.setNumber(number);
        bespeak.setName(name);
        bespeak.setType(type);
        bespeak.setPhone(phone);
        
        BespeakServiceInterface service = new BespeakService();
        int result = service.createBespeak(token, bespeak);
        if(MAGICCODE.OK != result)
        {
            map.put("code", MAGICCODE.MAGIC_ERROR);
        }else
        {
            map.put("code", MAGICCODE.MAGIC_OK);
        }
        
        json = JSONObject.fromObject(map);
        stream.write(json.toString().getBytes("UTF-8"));
        return null;
    }

}
