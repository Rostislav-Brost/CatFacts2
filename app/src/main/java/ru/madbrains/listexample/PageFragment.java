package ru.madbrains.listexample;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;


public class PageFragment extends Fragment {

    TextView tv;
RecyclerView recyclerView;

    public static PageFragment newInstance(String text) {
        PageFragment pageFragment = new PageFragment();
        Bundle bundle = new Bundle();
        bundle.putString("test", text);
        pageFragment.setArguments(bundle);

        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)   {
        View view = inflater.inflate(R.layout.fragment_page, container, false);
      //  tv = (TextView) view.findViewById(R.id.tv);
       // tv.setText(getArguments().getString("test"));
//recyclerView= (RecyclerView) view.findViewById(R.id.recycler_view_id);

        return view;
    }}