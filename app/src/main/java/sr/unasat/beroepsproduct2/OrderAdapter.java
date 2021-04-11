package sr.unasat.beroepsproduct2;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import sr.unasat.beroepsproduct2.items.AntroewaActivity;
import sr.unasat.beroepsproduct2.items.BitawiriActivity;
import sr.unasat.beroepsproduct2.items.BoulangerActivity;
import sr.unasat.beroepsproduct2.items.KlaroenActivity;
import sr.unasat.beroepsproduct2.items.KomkommerActivity;
import sr.unasat.beroepsproduct2.items.KoolActivity;
import sr.unasat.beroepsproduct2.items.KousebandActivity;
import sr.unasat.beroepsproduct2.items.PompoenActivity;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    List<Model> modelList;
    Context context;

    public OrderAdapter(Context context, List<Model> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.listitem, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        String nameofVegetable = modelList.get(position).getmVegetableName();
        String descriptionofVegetable = modelList.get(position).getmVegetableDetail();
        int images = modelList.get(position).getmVegetablePhoto();

        holder.mVegetableName.setText(nameofVegetable);
        holder.mVegetableDescription.setText(descriptionofVegetable);
        holder.imageView.setImageResource(images);

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        TextView mVegetableName, mVegetableDescription;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);

            mVegetableName = itemView.findViewById(R.id.vegetableName);
            mVegetableDescription = itemView.findViewById(R.id.description);
            imageView = itemView.findViewById(R.id.vegetableImage);
            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {

            int position = getAdapterPosition();

            if (position == 0) {
                Intent intent = new Intent(context, KomkommerActivity.class);
                context.startActivity(intent);
            }

            if (position == 1) {
                Intent intent2 = new Intent(context, PompoenActivity.class);
                context.startActivity(intent2);
            }
            if (position == 2) {
                Intent intent3 = new Intent(context, KousebandActivity.class);
                context.startActivity(intent3);
            }
            if (position == 3) {
                Intent intent4 = new Intent(context, BoulangerActivity.class);
                context.startActivity(intent4);

            }
            if (position == 4) {
                Intent intent5 = new Intent(context, KlaroenActivity.class);
                context.startActivity(intent5);

            }
            if (position == 5) {
                Intent intent6 = new Intent(context, KoolActivity.class);
                context.startActivity(intent6);

            }
            if (position == 6) {
                Intent intent7 = new Intent(context, BitawiriActivity.class);
                context.startActivity(intent7);

            }
            if (position == 7) {
                Intent intent8 = new Intent(context, AntroewaActivity.class);
                context.startActivity(intent8);
            }


        }

    }

}