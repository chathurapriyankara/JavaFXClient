package parser;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

public class DataProcessor {
    public BreakfastMenu getBreakfastMenu(String result) {
        BreakfastMenu breakfastMenu = null;
        try {
            JAXBContext context = JAXBContext.newInstance(BreakfastMenu.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            breakfastMenu = (BreakfastMenu) unmarshaller.unmarshal(new StringReader(result));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return breakfastMenu;
    }
}
