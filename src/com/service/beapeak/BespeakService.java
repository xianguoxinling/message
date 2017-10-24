package com.service.beapeak;

import java.util.ArrayList;
import java.util.List;

import com.db.manager.BespeakDBManager;
import com.model.bespeak.Bespeak;
import com.model.bespeak.BespeakState;
import com.service.interfaces.BespeakServiceInterface;
import com.until.errorcode.MAGICCODE;
import com.until.queryterm.QueryTerm;

public class BespeakService implements BespeakServiceInterface
{

    private BespeakDBManager dbManager = null;
    public BespeakService()
    {
        dbManager = new BespeakDBManager();
    }
    @Override
    public int createBespeak(String token, Bespeak bespeak)
    {
        int result = MAGICCODE.OK;
        result = dbManager.addBespeak(bespeak);
        if(MAGICCODE.OK != result)
        {
            
        }
        
        return result;
    }

    @Override
    public int updateBespeakState(String token, String id, String state, String magicKey)
    {
        int result = MAGICCODE.OK;
        result = dbManager.updateBespeakState(id, state, magicKey);
        if(MAGICCODE.OK != result)
        {
            
        }
        return result;
    }

    @Override
    public List<Bespeak> queryBespeak(String token, String magicKey,QueryTerm queryTerm)
    {
        List<Bespeak> bespeakList = new ArrayList<Bespeak>();
        int result = MAGICCODE.OK;
        result = dbManager.queryBespeak(bespeakList, magicKey,queryTerm);
        if(MAGICCODE.OK != result)
        {
            
        }
        return bespeakList;
    }
    @Override
    public List<Bespeak> queryNewBespeak(String token, String magicKey,QueryTerm queryTerm)
    {
        List<Bespeak> bespeakList = new ArrayList<Bespeak>();
        int result = MAGICCODE.OK;
        result = dbManager.queryBespeakByState(bespeakList, BespeakState.UNDEAL, magicKey,queryTerm);
        if(MAGICCODE.OK != result)
        {
            
        }
        return bespeakList;
    }
    @Override
    public List<Bespeak> queryToStoreBespeak(String token, String magicKey,QueryTerm queryTerm)
    {
        List<Bespeak> bespeakList = new ArrayList<Bespeak>();
        int result = MAGICCODE.OK;
        result = dbManager.queryBespeakByState(bespeakList, BespeakState.TO_THE_STORE, magicKey,queryTerm);
        if(MAGICCODE.OK != result)
        {
            
        }
        return bespeakList;
    }
    @Override
    public List<Bespeak> queryNoShopBespeak(String token, String magicKey,QueryTerm queryTerm)
    {
        List<Bespeak> bespeakList = new ArrayList<Bespeak>();
        int result = MAGICCODE.OK;
        result = dbManager.queryBespeakByState(bespeakList, BespeakState.NO_SHOP, magicKey,queryTerm);
        if(MAGICCODE.OK != result)
        {
            
        }
        return bespeakList;
    }

}
