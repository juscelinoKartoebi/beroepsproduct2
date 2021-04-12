package sr.unasat.beroepsproduct2.Activities;


import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import sr.unasat.beroepsproduct2.Database.OrderContract;
import sr.unasat.beroepsproduct2.R;

public class BoulangerActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {


    ImageView imageView;
    ImageButton plusquantity, minusquantity;
    TextView quantitynumber, vegetableName, vegetablePrice;
    Button addtoCart;
    int quantity;
    public Uri mCurrentCartUri;
    boolean hasAllRequiredValues = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boulanger);

        imageView = findViewById(R.id.imageViewInfo);
        plusquantity = findViewById(R.id.addquantity);
        minusquantity = findViewById(R.id.subquantity);
        quantitynumber = findViewById(R.id.quantity);
        vegetableName = findViewById(R.id.vegetableNameinInfo);
        vegetablePrice = findViewById(R.id.vegetablePrice);
        addtoCart = findViewById(R.id.addtocart);




        vegetableName.setText("boulanger");

        addtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BoulangerActivity.this, SummaryActivity.class);


                AlertDialog.Builder alertDialog = new AlertDialog.Builder(BoulangerActivity.this);

                alertDialog.setIcon(android.R.drawable.ic_dialog_alert)

                        .setTitle("Are you sure?")

                        .setMessage("clicking to yes wil add this item ")

                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                startActivity(intent);
                                SaveCart();

                            }
                        })

                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {


                            }
                        })
                ;
                AlertDialog Dialog  = alertDialog.create();
                Dialog.show();
            }
        });


        plusquantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // coffee price
                int basePrice = 7;
                quantity++;
                displayQuantity();
                int coffePrice = basePrice * quantity;
                String setnewPrice = String.valueOf(coffePrice);
                vegetablePrice.setText(setnewPrice);

            }
        });

        minusquantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int basePrice = 7;

                if (quantity == 0) {
                    Toast.makeText(BoulangerActivity.this, "Cant decrease quantity < 0", Toast.LENGTH_SHORT).show();
                } else {
                    quantity--;
                    displayQuantity();
                    int coffePrice = basePrice * quantity;
                    String setnewPrice = String.valueOf(coffePrice);
                    vegetablePrice.setText(setnewPrice);

                }
            }
        });


    }

    private boolean SaveCart() {


        String name = vegetableName.getText().toString();
        String price = vegetablePrice.getText().toString();
        String quantity = quantitynumber.getText().toString();

        ContentValues values = new ContentValues();
        values.put(OrderContract.OrderEntry.COLUMN_NAME, name);
        values.put(OrderContract.OrderEntry.COLUMN_PRICE, price);
        values.put(OrderContract.OrderEntry.COLUMN_QUANTITY, quantity);


        if (mCurrentCartUri == null) {
            Uri newUri = getContentResolver().insert(OrderContract.OrderEntry.CONTENT_URI, values);
            if (newUri == null) {
                Toast.makeText(this, "Failed to add to Cart", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Success  adding to Cart", Toast.LENGTH_SHORT).show();

            }
        }

        hasAllRequiredValues = true;
        return hasAllRequiredValues;

    }


    private void displayQuantity() {
        quantitynumber.setText(String.valueOf(quantity));
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {OrderContract.OrderEntry._ID,
                OrderContract.OrderEntry.COLUMN_NAME,
                OrderContract.OrderEntry.COLUMN_PRICE,
                OrderContract.OrderEntry.COLUMN_QUANTITY

        };

        return new CursorLoader(this, mCurrentCartUri,
                projection,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

        if (cursor == null || cursor.getCount() < 1) {
            return;
        }

        if (cursor.moveToFirst()) {

            int name = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_NAME);
            int price = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_PRICE);
            int quantity = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_QUANTITY);

            String nameofvegetable = cursor.getString(name);
            String priceofvegetable = cursor.getString(price);
            String quantityofvegetable = cursor.getString(quantity);

            vegetableName.setText(nameofvegetable);
            vegetablePrice.setText(priceofvegetable);
            quantitynumber.setText(quantityofvegetable);
        }


    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {


        vegetableName.setText("");
        vegetablePrice.setText("");
        quantitynumber.setText("");

    }
}