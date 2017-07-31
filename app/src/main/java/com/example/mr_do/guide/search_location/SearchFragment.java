package com.example.mr_do.guide.search_location;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mr_do.guide.Locate.LocateActivity;
import com.example.mr_do.guide.R;
import com.example.mr_do.guide.utils.KeyPressUtil;

import java.io.IOException;

/**
 * Created by Mr_Do on 2017/7/26.
 */

public class SearchFragment extends Fragment implements ISearchView{

    private View mView;
    private Button mBackButton;
    private Button mSearchButton;
    private SearchPresenter mPresenter;


    public static SearchFragment newInstance() {
        Bundle args = new Bundle();

        SearchFragment fragment = new SearchFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_search , container , false);
        mBackButton = mView.findViewById(R.id.back_button);
        mSearchButton =  mView.findViewById(R.id.search_bottom);
        mPresenter = new SearchPresenter(this);
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.back();
            }
        });
        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.search();
            }
        });
        return mView;
    }

    @Override
    public void search() {
        if(isAdded()) {
            Intent intent = new Intent(getActivity(), LocateActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void back() {
        if(isAdded()) {
            try {
                mBackButton.setEnabled(false);
                KeyPressUtil.pressKeyBack();
            } catch (IOException e) {
                e.printStackTrace();
                getActivity().finish();
            }
        }
    }
}
