package ee.ttu.dailyparser;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

public class DailyReader {
    private final String menu;
    private LinkedList<String> menuAsLinkedList;

    /**
     * Class constructor for storing Daily menu data.
     *
     * @param file Daily Menu PDF file
     * @throws IOException description.
     */
    DailyReader(File file) throws IOException {
        PDDocument document = PDDocument.load(file);
        PDFTextStripper pdfStripper = new PDFTextStripper();
        pdfStripper.setSortByPosition(true);
        this.menu = pdfStripper.getText(document);
        document.close();
        menuToList();
    }

    private void menuToList() {
        LinkedList<String> list = new LinkedList<>();
        list.addAll(Arrays.asList(this.menu.split("\\r?\\n")));
        this.menuAsLinkedList = list;
    }

    public LinkedList<String> getMenuAsLinkedList() {
        return menuAsLinkedList;
    }
}
