package com.example.myfirstapplication.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myfirstapplication.R;
import com.example.myfirstapplication.adapters.AdapterRecordItems;
import com.example.myfirstapplication.db.AppDatabase;
import com.example.myfirstapplication.db.Record;
import com.example.myfirstapplication.db.RecordItem;
import com.example.myfirstapplication.db.daos.DaoRecord;
import com.example.myfirstapplication.db.daos.DaoRecordItem;

import java.util.Calendar;
import java.util.List;

public class RecordItemsFragment extends Fragment {
    RecyclerView recview;
    TextView textViewEmpty;
    public RecordItemsFragment() {
        // Required empty public constructor
    }

    public static RecordItemsFragment newInstance(String param1, String param2) {
        RecordItemsFragment fragment = new RecordItemsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_record_items, container, false);
        AppDatabase db = AppDatabase.getInstance(this.getContext());
        DaoRecordItem daoRecordItem = db.daoRecordItem();
        DaoRecord daoRecord = db.daoRecord();

        recview = root.findViewById(R.id.record_items_recycle_view);
        textViewEmpty = root.findViewById(R.id.record_items_textEmpty);
        LinearLayoutManager layoutManager = new LinearLayoutManager(root.getContext(), LinearLayoutManager.VERTICAL, false);
        recview.setLayoutManager(layoutManager);

        Calendar c = Calendar.getInstance();
        Record record = daoRecord.getRecordByDate(c.get(Calendar.DAY_OF_MONTH), c.get(Calendar.MONTH), c.get(Calendar.YEAR));

        if(record!= null){
            List<RecordItem> recordItems = daoRecordItem.getRecordsItemByRecord(record.getId());
            AdapterRecordItems adapterRecordItems = new AdapterRecordItems(this.getContext(), recordItems);
            recview.setAdapter(adapterRecordItems);
        }
        else{
            textViewEmpty.setText("Aun no se ha registrado ningún elemento el día de hoy");
        }
        return root;
    }
}