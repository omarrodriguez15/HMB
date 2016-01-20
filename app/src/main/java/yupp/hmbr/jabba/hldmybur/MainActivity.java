package yupp.hmbr.jabba.hldmybur;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity
{
    private ImageButton imgBtnWine, imgBtnBeer, imgBtnMixed, imgBtnReset;
    private TextView txtViewTotalDrinks;
    private DrinkTracker dt;
    private String drinksFile = "Drinks.csv";
    private final String TOTAL = "total";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dt = new DrinkTracker(getApplicationContext());

        LoadWidgets();

        UpdateTotalCount();

        imgBtnReset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getApplicationContext(), "Reset drink count", Toast.LENGTH_SHORT);
                dt.clearDrinkTotal("all");
                UpdateTotalCount();
            }
        });

        imgBtnBeer.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dt.writeToJSON("drinks.json","Beer");
                dt.writeToStorage(drinksFile, "Beer,", true);
                Toast.makeText(getApplicationContext(),"Beer!", Toast.LENGTH_SHORT).show();
                UpdateTotalCount();
            }
        });

        imgBtnWine.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dt.writeToStorage(drinksFile, "Wine,", true);
                Toast.makeText(getApplicationContext(), "Meh Wine!", Toast.LENGTH_SHORT).show();
                UpdateTotalCount();
            }
        });

        imgBtnMixed.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dt.writeToStorage(drinksFile, "Mix Drink,", true);
                Toast.makeText(getApplicationContext(), "It better be whiskey!", Toast.LENGTH_SHORT).show();
                UpdateTotalCount();
            }
        });
    }

    private void UpdateTotalCount()
    {
        txtViewTotalDrinks.clearComposingText();
        txtViewTotalDrinks.setText(Integer.toString(dt.getDrinkTotal(TOTAL)));
    }

    private void LoadWidgets()
    {
        imgBtnBeer = (ImageButton) findViewById(R.id.imgBtnBeer);
        imgBtnWine = (ImageButton) findViewById(R.id.imgBtnWine);
        imgBtnMixed = (ImageButton) findViewById(R.id.imgBtnMixed);
        imgBtnReset = (ImageButton) findViewById(R.id.imgBtnReset);
        txtViewTotalDrinks = (TextView) findViewById(R.id.txtViewTotalCount);
    }
}
