import com.epam.tamentoring.bo.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
public class UnitTestWithMockito {

    @InjectMocks
    OrderService orderService;
    @Mock
    DiscountUtility discountUtility;

    @Test
    public void verifyGettingOrderPrice() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(2, "balsam", 12.00, 1));
        ShoppingCart shoppingCart = new ShoppingCart(products);
        UserAccount userAccount = new UserAccount(
                "John", "Smith", "1990/10/10", shoppingCart);
        Mockito.when(discountUtility.calculateDiscount(userAccount)).thenReturn(3.0);

        Assertions.assertEquals(3.0, discountUtility.calculateDiscount(userAccount));
        verify(discountUtility, Mockito.times(1)).calculateDiscount(userAccount);
        verifyNoMoreInteractions(discountUtility);
    }


}
