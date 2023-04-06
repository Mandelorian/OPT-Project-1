import com.mandelorian.library.Categorie;
import com.mandelorian.library.Utility;
import com.mandelorian.product.Boat;
import com.mandelorian.product.Option;
import com.mandelorian.product.ProductList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestUtility {

    @Test
    public void testGetBoatByName() {
        //Arrange
        String testBootNaam = "testBoot";
        Boat testBoot = new Boat(testBootNaam, 11111111, Utility.getCategorieByName("Grote boten"));
        ProductList.getBoatList().add(testBoot);

        //Act
        Boat testBoot2 = Utility.getBoatByName(testBootNaam);

        //Assert
        assertEquals(testBoot, testBoot2);
    }

    @Test
    public void testGetOptionByName() {
        //Arrange
        String testOptionNaam = "testOption";
        Option testOption = new Option(testOptionNaam, 11111111, null, null);
        ProductList.getOptionList().add(testOption);

        //Act
        Option testOption2 = Utility.getOptionByName(testOptionNaam);

        //Assert
        assertEquals(testOption, testOption2);
    }

    @Test
    public void testGetCatogorieByName() {
        //Arrange
        String testCatogorieNaam = "testCatogorie";
        Categorie testCategorie = new Categorie(testCatogorieNaam);
        Categorie.getCategorieList().add(testCategorie);

        //Act
        Categorie testCategorie2 = Utility.getCategorieByName(testCatogorieNaam);

        //Assert
        assertEquals(testCategorie, testCategorie2);
    }
}
