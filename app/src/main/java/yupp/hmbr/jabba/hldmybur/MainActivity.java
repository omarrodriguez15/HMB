package yupp.hmbr.jabba.hldmybur;


import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;


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

        imgBtnReset.setOnClickListener(ResetCounter());

        imgBtnBeer.setOnClickListener(DrinkClick("beer","BEER!"));

        imgBtnWine.setOnClickListener(DrinkClick("wine","Really? Wine!"));

        imgBtnMixed.setOnClickListener(DrinkClick("mixed_drink","It better be Whiskey!"));
    }

    @NonNull
    private View.OnClickListener ResetCounter()
    {
        return new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getApplicationContext(), "Reset drink count", Toast.LENGTH_SHORT);
                dt.clearDrinkTotal("all");
                dt.writeToStorage("drinkLog.txt","\n|Reset counter |TIME|"+Calendar.getInstance().getTime()+"|,",true);
                UpdateTotalCount();
            }
        };
    }

    @NonNull
    private View.OnClickListener DrinkClick(final String drinkType, final String message)
    {
        return new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dt.writeToJSON("drinks.json",drinkType);
                dt.writeToStorage("drinkLog.txt","\n"+drinkType+":TIME:"+Calendar.getInstance().getTime()+",",true);
                dt.writeToStorage(drinksFile, drinkType + ",", true);
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                UpdateTotalCount();
            }
        };
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
