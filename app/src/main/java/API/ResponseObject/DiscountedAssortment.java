package API.ResponseObject;

/**
 * Obiekt, zwracany dla '/App/AllDiscountAssortment'
 */
public class DiscountedAssortment {
        public String code;
        public String eanCode;
        public float price;
        public float discount;
        public boolean forAdultOnly;
}
