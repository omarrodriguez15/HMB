package yupp.hmbr.jabba.hldmybur;

import android.content.Context;
import android.util.Xml;

import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;

/**
 * Created by jabba on 1/17/16.
 */
public class DrinkTrackerXML
{
    public String mFileName = "drinks.xml";
    private Context mContext = null;

    public DrinkTrackerXML(Context context, String fname)
    {
        mContext = context;
        mFileName = fname;
    }

    //param[0] == filename
    //param[1] == data
    public String writeToStorage(String... params)
    {
        FileOutputStream fos = null;
        File file = null;
        byte[] bString = params.length > 1 ? params[1].getBytes() : new byte[1];

        try
        {

            XmlSerializer xmlSerializer = Xml.newSerializer();
            StringWriter writer = new StringWriter();

            xmlSerializer.setOutput(writer);
            xmlSerializer.startDocument("UTF-8", true);
            xmlSerializer.startTag(null, "userData");
            xmlSerializer.startTag(null, "userName");
            xmlSerializer.text("UsernameStringHERE");
            xmlSerializer.endTag(null, "userName");
            xmlSerializer.startTag(null,"password");
            xmlSerializer.text("PasswordStringHERE");
            xmlSerializer.endTag(null, "password");
            xmlSerializer.endTag(null, "userData");
            xmlSerializer.endDocument();
            xmlSerializer.flush();
            String dataWrite = writer.toString();

            file = mContext.getFilesDir();
            fos = mContext.openFileOutput(mFileName, mContext.MODE_APPEND);
            fos.write(dataWrite.getBytes());
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
}
