import com.epam.tamentoring.bo.Product;
import com.epam.tamentoring.bo.ShoppingCart;
import com.epam.tamentoring.exceptions.ProductNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class UnitTest {
    private static List<Product> products;
    private ShoppingCart shoppingCart;
    private Product product;

    @BeforeEach
    public void setUpProducts() {
        products = new ArrayList<>();
        shoppingCart = new ShoppingCart(products);
        product = new Product(2, "balsam", 12.00, 1);

    }


    @Test
    public void verifyAddingProductsInShoppingCart() {
        shoppingCart.addProductToCart(product);

        Assertions.assertTrue(products.contains(product));
    }

    @Test
    public void verifyAddingTheSameProductsInShoppingCart() {
        shoppingCart.addProductToCart(product);
        shoppingCart.addProductToCart(product);

        Assertions.assertEquals(1, products.size());
        Assertions.assertEquals(2, products.get(0).getQuantity());
    }

    @Test
    public void verifyRemoveProductsFromShoppingCart() {
        shoppingCart.addProductToCart(product);
        shoppingCart.removeProductFromCart(product);

        Assertions.assertEquals(0, products.size());
        Assertions.assertFalse(products.contains(product));
    }

    @Test
    public void verifyRemoveNonExistentProductsFromShoppingCart() {
        Exception exception = assertThrows(ProductNotFoundException.class, () -> {
            shoppingCart.removeProductFromCart(product);
        });

        Assertions.assertTrue(exception.getMessage().contains(product.toString()));
    }

    @Test
    public void verifyGettingTheTotalPriceOfShoppingCart() {
        shoppingCart.addProductToCart(new Product(2, "balsam", 12.00, 1));
        shoppingCart.addProductToCart(new Product(1, "shampoo", 14.00, 10.0));
        shoppingCart.addProductToCart(new Product(3, "rosemary oil", 7.00, 8));
        shoppingCart.addProductToCart(new Product(4, "body gel", 6.00, 6));
        shoppingCart.addProductToCart(new Product(5, "body lotion", 6.00, 6));

        Assertions.assertEquals(280, shoppingCart.getCartTotalPrice());
    }
}
