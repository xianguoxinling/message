package com.control.beapeak;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.model.bespeak.Bespeak;
import com.platform.base.UserCookieManager;
import com.service.beapeak.BespeakService;
import com.service.interfaces.BespeakServiceInterface;
import com.until.errorcode.MAGICCODE;
import com.until.queryterm.QueryTerm;

public class QueryToTheStoreController implements Controller
{

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        HttpSession session = request.getSession(); 
        String token = (String)session.getAttribute("token");
        if (null == token)
        {
            token =  UserCookieManager.getCookieValueByName(request, "token");
            if (null == token)
            {
                return new ModelAndView("/store/storelogin.jsp");
            }
        }
        
        String keyID = (String)session.getAttribute("keyID");
        if (null == keyID)
        {
            keyID = UserCookieManager.getCookieValueByName(request, "keyID");
            if(null == keyID)
            {
                return new ModelAndView("/store/storelogin.jsp");
            }
        }
        
        String begin = request.getParameter("begin");
        String num = request.getParameter("num");

        QueryTerm queryTerm = new QueryTerm();
        if (null != begin)
        {
            queryTerm.queryNum = Integer.parseInt(num);
            queryTerm.queryBegin = (Integer.parseInt(begin) - 1) * queryTerm.queryNum;
        }
        
        BespeakServiceInterface service = new BespeakService();
        List<Bespeak> bespeakList = service.queryToStoreBespeak(token, keyID,queryTerm);
        if(null == bespeakList)
        {
            return new ModelAndView("/store/error.html");
        }
        
        request.setAttribute("magiczz", "tostore");
        
        return new ModelAndView("/bespeak/bespeaklist.jsp","bespeakList",bespeakList);
        
    }
}
