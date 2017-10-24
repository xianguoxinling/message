package com.service.interfaces;

import java.util.List;

import com.model.bespeak.Bespeak;
import com.until.queryterm.QueryTerm;

public interface BespeakServiceInterface
{
    public int createBespeak(String token,Bespeak bespeak);
    public int updateBespeakState(String token,String id,String state,String magicKey);
    public List<Bespeak> queryBespeak(String token,String magicKey,QueryTerm queryTerm);
    public List<Bespeak> queryNewBespeak(String token,String magicKey,QueryTerm queryTerm);
    public List<Bespeak> queryToStoreBespeak(String token,String magicKey,QueryTerm queryTerm);
    public List<Bespeak> queryNoShopBespeak(String token,String magicKey,QueryTerm queryTerm);
}
