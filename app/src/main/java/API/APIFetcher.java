package API;

import com.allsociety.mobilkiwsb.model.AssortmentModel;
import com.allsociety.mobilkiwsb.model.CartItemModel;
import com.allsociety.mobilkiwsb.model.CheckedOutCart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import API.ResponseObject.DiscountedAssortment;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Query;

/**
 * Główna klasa, odpowiadająca za odpytanie API
 */
public class APIFetcher {

    ShopService _apiService;

    /**
     * Inicjalizacja serwisu do odpytywania
     */
    public APIFetcher() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://" + Config.IP + ":" + Config.Port)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this._apiService = retrofit.create(ShopService.class);
    }

    /**
     * Logowanie użytkownika
     */
    public boolean LoginUser(String code, String password) {
        try {
            Response<Integer> response = _apiService.LoginUser(code, password).execute();
            return response.code() == 200;
        } catch (IOException ioException) {
            return false;
        }
    }

    /**
     * Zwraca wszystkie, przecenione asortymenty
     */
    public List<DiscountedAssortment> GetAllPromotions() {
        try {
            Response<List<DiscountedAssortment>> response = _apiService.DiscountAssortment().execute();
            return response.body();
        } catch (IOException ioException) {
            return new ArrayList<DiscountedAssortment>();
        }
    }

    public List<CartItemModel> assortmentInCart(String userCode) {
        try {
            Response<List<CartItemModel>> response = _apiService.AssortmentInCart(userCode).execute();
            if (response.code()==200)
                return response.body();
            else
                return new ArrayList<CartItemModel>();
        } catch (IOException ioException) {
            return null;
        }catch (Exception e){
            return null;
        }
    }

    public AssortmentModel getAssortmentByEan(String eanCode) {
        try {
            Response<AssortmentModel> response = _apiService.getAssortmentByEAN(eanCode).execute();
            if (response.code()==200)
                return response.body();
            else if (response.code()==404) {
                AssortmentModel notFound=new AssortmentModel();
                notFound.setCode("Brak");
                return notFound;
            }
            else
                return new AssortmentModel();
        } catch (IOException ioException) {
            return new AssortmentModel();
        }catch (Exception e){
            return new AssortmentModel();
        }
    }

    public boolean addAssortmentToCart(String assortmentEANCode, float quantity, String userCode, boolean removeExistingItem){
        try {
            Response<Integer> response = _apiService.AddAssortmentToCart(assortmentEANCode, quantity, userCode, removeExistingItem).execute();
            return response.code()==200;
        }catch (IOException ioException){
            return false;
        }
    }

    public CheckedOutCart getCartBarcode(String userCode){
        try {
            Response<CheckedOutCart> response = _apiService.getCartBarcode(userCode).execute();
            return response.body();
        }catch (Exception e){
            return null;
        }
    }
}
