package yupp.hmbr.jabba.hldmybur;

import android.content.Context;
import android.os.Build;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    public String writeToJSON(String fileName, String data)
    {
        String jsonStr = BuildJsonObj(data, fileName);
        writeToStorage(fileName, jsonStr.toString(), false);
        return "";
    }

    //param[0] == filename
    //param[1] == data
    public String writeToStorage(String fileName, String data, boolean append)
    {
        FileOutputStream fos = null;
        File file = null;
        byte[] dataBytes = data.getBytes();


        try
        {
            file = mContext.getFilesDir();
            fos = mContext.openFileOutput(fileName, append ? mContext.MODE_APPEND : mContext.MODE_PRIVATE);
            fos.write(dataBytes);
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

    private String BuildJsonObj(String param, String fileName)
    {
        JSONObject obj = new JSONObject();
        try
        {
            if (!checkFileExists(mContext.getFilesDir().getAbsolutePath()+"/"+fileName))
            {
                obj.put("Drinks", new JSONArray());
            }
            else
            {
                obj = new JSONObject(readFromStorage(fileName));
            }

            //add a Drink object to the Drinks Array
            obj.getJSONArray("Drinks").put(new JSONObject().put("Drink", param));

        } catch (JSONException e)
        {
            e.printStackTrace();
        }

        return obj.toString();
    }

    public String readFromStorage(String fileName)
    {
        int read;
        String result = "";
        FileInputStream fis = null;
        StringBuffer sb = new StringBuffer();

        try
        {
            if (!checkFileExists(mContext.getFilesDir().getAbsolutePath()+"/"+fileName))
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

    public static boolean checkFileExists(String path)
    {
        return new File(path).exists();
    }
}
