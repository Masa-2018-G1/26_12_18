package com.sheygam.masa_2018_g1_26_12_18;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ListFragment extends Fragment implements MyAdapter.MyAdapterClickListner {
    private RecyclerView myRv;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list,container,false);
        myRv = view.findViewById(R.id.my_rv);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        MyAdapter adapter = new MyAdapter();
        adapter.setListener(this);
        DividerItemDecoration divider = new DividerItemDecoration(getActivity(),manager.getOrientation());
        myRv.setLayoutManager(manager);
        myRv.setAdapter(adapter);
        myRv.addItemDecoration(divider);
        return view;
    }

    @Override
    public void onRowClick(String title) {

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.root_container,InfoFragment.of(title))
                .addToBackStack(null)
                .commit();
    }
}
