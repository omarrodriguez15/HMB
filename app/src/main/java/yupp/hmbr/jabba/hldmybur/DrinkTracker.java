package yupp.hmbr.jabba.hldmybur;

import android.content.Context;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by jabba on 1/16/16.
 */
public class DrinkTracker
{
    Context mContext = null;
    String drinksFile = "Drinks.csv";

    //Constructor gives us context
    public DrinkTracker(Context _context)
    {
        mContext = _context;
    }

    public int parseCsvString(String drinks)
    {

        return drinks.isEmpty() ? 0 : drinks.split(",").length;
    }

    public int getDrinkTotal(String drinkType)
    {
        int total = -1;
        switch (drinkType.toLowerCase())
        {
            case "total":
                total = parseCsvString(readFromStorage(drinksFile));
                break;
            default:
                break;
        }
        return total;
    }

    public boolean clearDrinkTotal(String drinkType)
    {
        boolean result = false;
        switch (drinkType.toLowerCase())
        {
            case "all":
                try
                {
                    FileOutputStream fos = mContext.openFileOutput(drinksFile, mContext.MODE_PRIVATE);
                    result = true;
                } catch (FileNotFoundException e)
                {
                    e.printStackTrace();
                    result = false;
                }
            default:
                break;
        }
        return result;
    }

    //param[0] == filename
    //param[1] == data
    public String writeToStorage(String... params)
    {
        FileOutputStream fos = null;
        File file = null;
        String fileName = params.length > 0 ? params[0] : "";
        byte[] bString = params.length > 1 ? params[1].getBytes() : new byte[1];

        try
        {
            file = mContext.getFilesDir();
            fos = mContext.openFileOutput(fileName, mContext.MODE_APPEND);
            fos.write(bString);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try
            {
                fos.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        return file.getPath().toString();
    }

    public String readFromStorage(String fileName)
    {
        int read;
        String result = "";
        FileInputStream fis = null;
        StringBuffer sb = new StringBuffer();
        File file = new File(mContext.getFilesDir().getPath()+fileName);

        try
        {
            if (!file.exists())
            {
                mContext.openFileOutput(fileName, mContext.MODE_APPEND);
            }

            fis = mContext.openFileInput(fileName);

            while ((read = fis.read()) != -1)
            {
                sb.append((char)read);
            }

            result = sb.toString();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try
            {
                //Check if ever opened. Happens when file doesn't exist.
                if (fis != null)
                {
                    fis.close();
                }
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        return result;
    }
}
