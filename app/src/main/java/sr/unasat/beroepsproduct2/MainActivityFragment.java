package sr.unasat.beroepsproduct2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class MainActivityFragment extends Fragment {
    List<Model> modelList;
    RecyclerView recyclerView;
    OrderAdapter mAdapter;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_main_activity, container, false);


        modelList = new ArrayList<>();
        modelList.add(new Model("Komkommer", getString(R.string.komkommer), R.drawable.komkommer));
        modelList.add(new Model("pompoen", getString(R.string.pompoen), R.drawable.pompoen));
        modelList.add(new Model("Kouseband", getString(R.string.kouseband), R.drawable.kouseband));
        modelList.add(new Model("boulanger", getString(R.string.boulanger), R.drawable.boulanger));
        modelList.add(new Model("klaroen", getString(R.string.klaroen), R.drawable.klaroen));
        modelList.add(new Model("kool", getString(R.string.kool), R.drawable.kool));
        modelList.add(new Model("bitawiri", getString(R.string.bitawiri), R.drawable.bitawiri));
        modelList.add(new Model("antroewa", getString(R.string.antroewa), R.drawable.antroewa));

        recyclerView = v.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(null));

        mAdapter = new OrderAdapter(v.getContext(), modelList);
        recyclerView.setAdapter(mAdapter);

        return v;
    }
}