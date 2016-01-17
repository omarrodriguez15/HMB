package yupp.hmbr.jabba.hldmybur;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ImageButton imgBtnWine, imgBtnBeer, imgBtnMixed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgBtnBeer = (ImageButton) findViewById(R.id.imgBtnBeer);
        imgBtnWine = (ImageButton) findViewById(R.id.imgBtnWine);
        imgBtnMixed = (ImageButton) findViewById(R.id.imgBtnMixed);

        imgBtnBeer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"BEER!", Toast.LENGTH_SHORT).show();
            }
        });

        imgBtnWine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "WINE!", Toast.LENGTH_SHORT).show();
            }
        });

        imgBtnMixed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Mixed 2 Drink!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
