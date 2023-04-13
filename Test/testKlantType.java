import com.mandelorian.klant.KlantType;
import com.mandelorian.library.Categorie;
import com.mandelorian.library.Utility;
import com.mandelorian.product.Boat;
import com.mandelorian.product.Option;
import com.mandelorian.product.ProductList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class testKlantType {
    @Test
    public void testGetKlantTypeByname() {
        //Arrange
        String testKlantTypeNaam = "testBoot";
        double testKorting = 3.5;
        KlantType testKlantType = new KlantType(testKlantTypeNaam, testKorting);
        KlantType.getKlantTypeList().add(testKlantType);

        //Act
        KlantType testKlantType2 = KlantType.getByName(testKlantTypeNaam);

        //Assert
        assertEquals(testKlantType, testKlantType2);
    }
}
