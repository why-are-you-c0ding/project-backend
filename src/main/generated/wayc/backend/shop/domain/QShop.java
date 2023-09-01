package wayc.backend.shop.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QShop is a Querydsl query type for Shop
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QShop extends EntityPathBase<Shop> {

    private static final long serialVersionUID = 1854218364L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QShop shop = new QShop("shop");

    public final wayc.backend.common.domain.QBaseEntity _super = new wayc.backend.common.domain.QBaseEntity(this);

    //inherited
    public final StringPath createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<Item, QItem> items = this.<Item, QItem>createList("items", Item.class, QItem.class, PathInits.DIRECT2);

    public final QShopOwner owner;

    public final StringPath shopName = createString("shopName");

    //inherited
    public final EnumPath<wayc.backend.common.domain.BaseStatus> status = _super.status;

    //inherited
    public final StringPath updatedAt = _super.updatedAt;

    public QShop(String variable) {
        this(Shop.class, forVariable(variable), INITS);
    }

    public QShop(Path<? extends Shop> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QShop(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QShop(PathMetadata metadata, PathInits inits) {
        this(Shop.class, metadata, inits);
    }

    public QShop(Class<? extends Shop> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.owner = inits.isInitialized("owner") ? new QShopOwner(forProperty("owner")) : null;
    }

}

