package wayc.backend.factory.shop;

import wayc.backend.shop.domain.Shop;

public class ShopFactory {

    public static Shop create(){
        return new Shop(9L, "익명의 shop");
    }
}
