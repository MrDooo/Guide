package com.example.mr_do.guide.search_location;

/**
 * Created by Mr_Do on 2017/7/26.
 */

public class SearchPresenter {
    private  ISearchView mSearchView;
    public SearchPresenter(ISearchView searchView){
        mSearchView = searchView;
    }

    public void back(){
        mSearchView.back();
    }

    public void search(){
        mSearchView.search();
    }
}
