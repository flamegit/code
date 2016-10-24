package com.example.studydemo;

import android.animation.ObjectAnimator;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ContentFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ContentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContentFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private int mParam1;
    GameService mService;

    public ContentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MeunFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment newInstance(int resume) {
        ContentFragment fragment = new ContentFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1,resume);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
            mService=GameService.getInstanc();
            mService.setState(mParam1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View views= inflater.inflate(R.layout.activity_main, container, false);
        TextSwitcher score=(TextSwitcher)views.findViewById(R.id.score);
        score.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView view=new TextView(views.getContext());
                view.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT));
                view.setTextSize(45);
                view.setTextColor(Color.WHITE);
                return view;
            }
        });
		GridImageView board=(GridImageView)views.findViewById(R.id.board);
        //ObjectAnimator animator=ObjectAnimator.ofFloat(board,"translationX",0,100).setDuration(1000);
       // animator.start();
		ProgressBar bar =(ProgressBar)views.findViewById(R.id.progressbar);
        mService.bindView(bar,board,score);
		board.setService(mService);
        return views;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("LLK","ondetach");
    }

    @Override
    public void onPause() {
        super.onPause();
        mService.pause();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("LLK","ondestroy");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("LLK","onDestroyView");
    }
}
