package com.example.newdeluxfastfood.screens.order_summary_screen.ui.ui.main;

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

import com.example.newdeluxfastfood.R;
import com.example.newdeluxfastfood.listAdapter.CurrentScreenAdapter;
import com.example.newdeluxfastfood.screens.order_summary_screen.ui.constants.Constants;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

public class CurrentOrderScreen extends Fragment {
    private static final String TAG = "CurrentOrderScreen";
    private boolean listenerSet = false;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private RecyclerView mRecyclerView;
    private Constants mConstants = Constants.getInstance();
    private final CurrentScreenAdapter adapter;

    public CurrentOrderScreen() {
        adapter = new CurrentScreenAdapter(mConstants.getContext());
    }

    public static CurrentOrderScreen newInstance() {
        CurrentOrderScreen fragment = new CurrentOrderScreen();
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        //Setting listener to not again add listener to current db if onStart is called again
        if(!listenerSet) {
            listenerSet = true;
            db.collection("users")
                    .document(auth.getUid())
                    .collection("currentOrders")
                    .orderBy("time", Query.Direction.DESCENDING)
                    .addSnapshotListener(new EventListener<QuerySnapshot>() {
                        @Override
                        public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                            if (e != null) {
                                Log.e(TAG, "onEvent: ", e);
                                return;
                            }

                            for (DocumentSnapshot snapshot : queryDocumentSnapshots) {
                                adapter.updatePriceList(snapshot);
                            }
                        }
                    });
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_order_summay_screen, container, false);
        mRecyclerView = root.findViewById(R.id.current_order_recycler_view);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mConstants.getContext()));
        return root;
    }
}