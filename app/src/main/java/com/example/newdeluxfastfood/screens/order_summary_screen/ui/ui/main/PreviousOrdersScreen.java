package com.example.newdeluxfastfood.screens.order_summary_screen.ui.ui.main;

import android.app.Activity;
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
import com.example.newdeluxfastfood.listAdapter.PreviousOrderListAdapter;
import com.example.newdeluxfastfood.screens.order_summary_screen.ui.constants.Constants;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PreviousOrdersScreen#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PreviousOrdersScreen extends Fragment {
    private static final String TAG = "PreviousOrdersScreen";
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private String firebaseID;
    private boolean listenerSet = false;
    PreviousOrderListAdapter listAdapter;
    private Constants mConstants = Constants.getInstance();
    private RecyclerView mRecyclerView;

    public PreviousOrdersScreen() {
        listAdapter = new PreviousOrderListAdapter(mConstants.getContext());
        Log.d(TAG, "PreviousOrdersScreen: called");
    }

    // TODO: Rename and change types and number of parameters
    public static PreviousOrdersScreen newInstance() {
        PreviousOrdersScreen fragment = new PreviousOrdersScreen();
        /**
         * code to pass arguments to new instance of fragment
         * Bundle bundle = new Bundle();
         * bundle.putString("string", "Ajmal");
         * fragment.setArguments(bundle);
         */
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        if(!listenerSet) {
            listenerSet = true;
            db.collection("users")
                    .document(firebaseID)
                    .collection("orders")
                    .orderBy("time", Query.Direction.DESCENDING)
                    .addSnapshotListener((Activity) mConstants.getContext(), new EventListener<QuerySnapshot>() {
                        @Override
                        public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                            if (e != null) {
                                Log.d(TAG, "onEvent Error: " + e.getMessage());
                                return;
                            }

                            for (DocumentSnapshot snapshot : queryDocumentSnapshots) {
                            /*Toast.makeText(mContext, snapshot.get("txnID").toString(), Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "snapshot value"+snapshot.get("txnID"));*/
                                listAdapter.updatePriceList(snapshot);
                            }
                        }
                    });
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseID = auth.getUid();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_previous_orders_screen, container, false);
        mRecyclerView = root.findViewById(R.id.previous_order_recycler_view);
        mRecyclerView.setAdapter(listAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mConstants.getContext()));
        return root;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}