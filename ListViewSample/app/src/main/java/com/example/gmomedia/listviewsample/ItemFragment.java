package com.example.gmomedia.listviewsample;

import android.app.Activity;
import android.os.Bundle;
import android.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.lang.reflect.Method;

public class ItemFragment extends ListFragment {

    private RequestQueue mQueue;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // TODO: Rename and change types of parameters
    public static ItemFragment newInstance(String param1, String param2) {
        ItemFragment fragment = new ItemFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */


    public ItemFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

      //  TODO: Change Adapter to display your content
//        setListAdapter(new ArrayAdapter<Weather.Detail>(getActivity(),
//                android.R.layout.simple_list_item_1, android.R.id.text1, Weather.Detail.ITEMS));
//
//        setListAdapter(new CustomListItemAdapter(getActivity(), Weather.Detail.ITEMS));



        // 東京都の天気情報
        String url = "http://api.openweathermap.org/data/2.5/forecast/daily?lat=35.656470&lon=139.699470&mode=json&cnt=14";
        String url1 = "http://openweathermap.org/img/w/04d.png";
        mQueue = Volley.newRequestQueue(getActivity());
        mQueue.add(new JsonObjectRequest(ImageRequest.Method.GET, url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                       // Log.i(" response: ",response.toString());
                        Gson gson = new Gson();
                        Weather weather = gson.fromJson(response.toString(), Weather.class);
//                        Log.i("ItemFragment",weather.getList().get(0).getDt());
//                        Log.i("ItemFragment",weather.getList().get(0).getTemp().max);
//                        Log.i("ItemFragment",weather.getList().get(0).getWeather().get(0).toString());
                        Log.i("test", "load finished.");

                        setListAdapter(new CustomListItemAdapter(getActivity(),weather.getList(), mQueue));
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Log.i("error", error.getMessage());
                    }
                }


        ));
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
    }

}
