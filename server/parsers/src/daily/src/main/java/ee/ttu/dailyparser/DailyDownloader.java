package ee.ttu.dailyparser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class DailyDownloader {
    private final String link;
    private final String establishment;

    DailyDownloader(String link, String establishment) {
        this.link = link;
        this.establishment = establishment;
    }

    void download() throws IOException {
        URL url = new URL(link);
        InputStream in = url.openStream();
        String save = "C:/file/" + establishment.trim() + ".pdf";
        FileOutputStream fos = new FileOutputStream(new File(save));

        int length;
        byte[] buffer = new byte[1024];

        while ((length = in.read(buffer)) > -1) {
            fos.write(buffer, 0, length);
        }
        fos.close();
        in.close();
    }
}
