package yupp.hmbr.jabba.hldmybur;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity
{
    ImageButton imgBtnWine, imgBtnBeer, imgBtnMixed, imgBtnReset;
    TextView txtViewTotalDrinks;
    DrinkTracker dt;
    String drinksFile = "Drinks.csv";
    final String TOTAL = "total";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dt = new DrinkTracker(getApplicationContext());

        imgBtnBeer = (ImageButton) findViewById(R.id.imgBtnBeer);
        imgBtnWine = (ImageButton) findViewById(R.id.imgBtnWine);
        imgBtnMixed = (ImageButton) findViewById(R.id.imgBtnMixed);
        imgBtnReset = (ImageButton) findViewById(R.id.imgBtnReset);

        txtViewTotalDrinks = (TextView) findViewById(R.id.txtViewTotalCount);

        txtViewTotalDrinks.clearComposingText();
        txtViewTotalDrinks.setText(Integer.toString(dt.getDrinkTotal(TOTAL)));

        imgBtnReset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getApplicationContext(), "Reset drink count", Toast.LENGTH_SHORT);
                dt.clearDrinkTotal("all");
                txtViewTotalDrinks.clearComposingText();
                txtViewTotalDrinks.setText(Integer.toString(dt.getDrinkTotal(TOTAL)));
            }
        });

        imgBtnBeer.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dt.writeToStorage(drinksFile, "Beer,");
                Toast.makeText(getApplicationContext(),"Beer!", Toast.LENGTH_SHORT).show();
                txtViewTotalDrinks.clearComposingText();
                txtViewTotalDrinks.setText(Integer.toString(dt.getDrinkTotal(TOTAL)));
            }
        });

        imgBtnWine.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dt.writeToStorage(drinksFile, "Wine,");
                Toast.makeText(getApplicationContext(), "Meh Wine!", Toast.LENGTH_SHORT).show();
                txtViewTotalDrinks.clearComposingText();
                txtViewTotalDrinks.setText(Integer.toString(dt.getDrinkTotal(TOTAL)));
            }
        });

        imgBtnMixed.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dt.writeToStorage(drinksFile, "Mix Drink,");
                Toast.makeText(getApplicationContext(), "It better be whiskey!", Toast.LENGTH_SHORT).show();
                txtViewTotalDrinks.clearComposingText();
                txtViewTotalDrinks.setText(Integer.toString(dt.getDrinkTotal(TOTAL)));
            }
        });
    }
}
