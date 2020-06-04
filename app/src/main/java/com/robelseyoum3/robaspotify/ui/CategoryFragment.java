package com.codingwithmitch.audiostreamer.ui;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.robelseyoum3.robaspotify.R;
import com.robelseyoum3.robaspotify.adapters.CategoryRecyclerAdapter;
import com.robelseyoum3.robaspotify.models.Artist;
import com.robelseyoum3.robaspotify.ui.IMainActivity;

import java.util.ArrayList;


public class CategoryFragment extends Fragment implements
        CategoryRecyclerAdapter.ICategorySelector
{

    private static final String TAG = "CategoryFragment";


    // UI Components
    private RecyclerView mRecyclerView;


    // Vars
    private CategoryRecyclerAdapter mAdapter;
    private ArrayList<Artist> mArtists = new ArrayList<>();
    private IMainActivity mIMainActivity;
    public String mSelectedCategory;

    public static CategoryFragment newInstance(String category) {
        CategoryFragment categoryFragment = new CategoryFragment();
        Bundle args = new Bundle();
        args.putString("category", category);
        categoryFragment.setArguments(args);
        return categoryFragment;
    }

    //getting selected category
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            mSelectedCategory = getArguments().getString("category");
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerView(view);
    }

    private void initRecyclerView(View view){
        if(mRecyclerView == null){
            mRecyclerView = view.findViewById(R.id.recycler_view);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            mAdapter = new CategoryRecyclerAdapter(getActivity(), mArtists, this);
            mRecyclerView.setAdapter(mAdapter);
            retrieveArtists();
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mIMainActivity = (IMainActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    public void retrieveArtists(){
        mIMainActivity.showProgressBar();

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();

        Query query  = firestore
                .collection(getString(R.string.collection_audio))
                .document(getString(R.string.document_categories))
                .collection(mSelectedCategory);

        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        mArtists.add(document.toObject(Artist.class));
                    }
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
                updateDataSet();
            }
        });
    }

    private void updateDataSet(){
        mAdapter.notifyDataSetChanged();
        mIMainActivity.hideProgressBar();
    }


    @Override
    public void onArtistSelected(int position) {

    }

}
























