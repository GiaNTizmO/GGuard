// 
// Decompiled by Procyon v0.5.36
// 

package ru.NanoNik.imgur;

import java.io.DataOutputStream;
import com.google.gson.reflect.TypeToken;
import java.util.Map;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URLEncoder;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.io.OutputStream;
import java.awt.image.RenderedImage;
import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.Gson;
import java.awt.image.BufferedImage;

public class Uploader implements Runnable
{
    private BufferedImage image;
    private Gson gson;
    private String from;
    
    public Uploader(final BufferedImage image, final String from) {
        this.gson = new Gson();
        this.image = image;
        this.from = from;
    }
    
    private String getImgurContent(final String clientID) throws Exception {
        try {
            final URL url = new URL("https://api.imgur.com/3/image");
            final HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            final ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
            ImageIO.write(this.image, "png", byteArray);
            final byte[] byteImage = byteArray.toByteArray();
            final String dataImage = Base64.encode(byteImage);
            final String data = URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(dataImage, "UTF-8");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Client-ID " + clientID);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.connect();
            final StringBuilder stb = new StringBuilder();
            final OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);
            wr.flush();
            final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                stb.append(line).append("\n");
            }
            wr.close();
            rd.close();
            return stb.toString();
        }
        catch (Exception e) {
            return "Not found";
        }
    }
    
    private String getLink(final String jsonData) {
        try {
            final String link = new String(jsonData.substring(jsonData.indexOf("link="), jsonData.length()).replace("link=", "").replace("}", "").trim());
            return link;
        }
        catch (Exception e) {
            return "Not found";
        }
    }
    
    @Override
    public void run() {
        try {
            final String response = this.getImgurContent("85b9637793370a9");
            final Map<String, Object> map = (Map<String, Object>)this.gson.fromJson(response, new TypeToken<Map<String, Object>>() {}.getType());
            final ByteArrayOutputStream bos = new ByteArrayOutputStream();
            final DataOutputStream outputStream = new DataOutputStream(bos);
            try {
                if (this.from == null) {
                    outputStream.writeUTF("srvAuth;" + this.getLink(map.get("data").toString()));
                }
                else {
                    outputStream.writeUTF(this.from + ";" + this.getLink(map.get("data").toString()));
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            final iz payload = new iz("nGuard", bos.toByteArray());
            bao.B().h.a.a((ft)payload);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
