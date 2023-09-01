package wayc.backend.shop.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QShopOwner is a Querydsl query type for ShopOwner
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QShopOwner extends BeanPath<ShopOwner> {

    private static final long serialVersionUID = 1339608983L;

    public static final QShopOwner shopOwner = new QShopOwner("shopOwner");

    public final NumberPath<Long> memberId = createNumber("memberId", Long.class);

    public QShopOwner(String variable) {
        super(ShopOwner.class, forVariable(variable));
    }

    public QShopOwner(Path<? extends ShopOwner> path) {
        super(path.getType(), path.getMetadata());
    }

    public QShopOwner(PathMetadata metadata) {
        super(ShopOwner.class, metadata);
    }

}

