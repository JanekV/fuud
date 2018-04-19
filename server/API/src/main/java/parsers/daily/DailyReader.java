package parsers.daily;

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

    /**
     * Method for splitting the menu into a linked list.
     */
    private void menuToList() {
        LinkedList<String> list = new LinkedList<>();
        //Splits the menu string into a linked list by new line.
        list.addAll(Arrays.asList(this.menu.split("\\r?\\n")));
        this.menuAsLinkedList = list;
    }

    /**
     * Getter method for menu as a linked list.
     * @return menu as a linked list.
     */
    public LinkedList<String> getMenuAsLinkedList() {
        return menuAsLinkedList;
    }
}

