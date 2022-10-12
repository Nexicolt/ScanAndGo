package API;

import com.allsociety.mobilkiwsb.model.AssortmentModel;
import com.allsociety.mobilkiwsb.model.CartItemModel;
import com.allsociety.mobilkiwsb.model.CheckedOutCart;

import java.util.List;

import API.ResponseObject.DiscountedAssortment;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Interfejs reprezentujący API
 */
public interface ShopService {

    /**
     * Autentykacja użytkownika
     */
    @POST("App/LogIn")
    Call<Integer> LoginUser(@Query("code") String code, @Query("password") String password);

    @POST("Cart/GetAssortmentFromCart")
    Call<List<CartItemModel>> AssortmentInCart(@Query("userCode") String userCode);

    @POST("Cart/AddAssortmentToCart")
    Call<Integer> AddAssortmentToCart(@Query("assortmentEANCode") String assortmentEANCode,
                                                  @Query("quantity") float quantity,
                                                  @Query("userCode") String userCode,
                                                  @Query("removeExistingItem") boolean removeExistingItem);

    @POST("Cart/GetAssortmentByEAN")
    Call<AssortmentModel> getAssortmentByEAN(@Query("assortmentEANCode") String assortmentEANCode);

    @POST("Cart/GetCartBarcode")
    Call<CheckedOutCart> getCartBarcode(@Query("userCode") String userCode);

    @GET("App/AllDiscountAssortment")
    Call<List<DiscountedAssortment>> DiscountAssortment();
}
