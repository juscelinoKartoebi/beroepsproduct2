package sr.unasat.beroepsproduct2.Adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import sr.unasat.beroepsproduct2.Database.OrderContract;
import sr.unasat.beroepsproduct2.R;


public class CartAdapter extends CursorAdapter {


    public CartAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.cartlist, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView vegetableName, price, quantity;
        String total;


        vegetableName = view.findViewById(R.id.vegetableNameinOrderSummary);
        price = view.findViewById(R.id.priceinOrderSummary);
        quantity = view.findViewById(R.id.quantityinOrderSummary);


        int name = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_NAME);
        int priceofvegetable = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_PRICE);
        int quantityofvegetable = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_QUANTITY);



        String nameofvegetable = cursor.getString(name);
        String pricesofvegetable= cursor.getString(priceofvegetable);
        String quantitysofvegetable = cursor.getString(quantityofvegetable);



        vegetableName.setText(nameofvegetable);
        price.setText(pricesofvegetable);
        quantity.setText(quantitysofvegetable);





    }
}