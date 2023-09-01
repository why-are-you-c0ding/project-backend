package wayc.backend.cart.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCartLineItem is a Querydsl query type for CartLineItem
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCartLineItem extends EntityPathBase<CartLineItem> {

    private static final long serialVersionUID = 1649249047L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCartLineItem cartLineItem = new QCartLineItem("cartLineItem");

    public final wayc.backend.common.domain.QBaseEntity _super = new wayc.backend.common.domain.QBaseEntity(this);

    public final QCart cart;

    public final ListPath<CartOptionGroup, QCartOptionGroup> cartOptionGroups = this.<CartOptionGroup, QCartOptionGroup>createList("cartOptionGroups", CartOptionGroup.class, QCartOptionGroup.class, PathInits.DIRECT2);

    public final NumberPath<Integer> count = createNumber("count", Integer.class);

    //inherited
    public final StringPath createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imageUrl = createString("imageUrl");

    public final NumberPath<Long> itemId = createNumber("itemId", Long.class);

    public final StringPath name = createString("name");

    //inherited
    public final EnumPath<wayc.backend.common.domain.BaseStatus> status = _super.status;

    //inherited
    public final StringPath updatedAt = _super.updatedAt;

    public QCartLineItem(String variable) {
        this(CartLineItem.class, forVariable(variable), INITS);
    }

    public QCartLineItem(Path<? extends CartLineItem> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCartLineItem(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCartLineItem(PathMetadata metadata, PathInits inits) {
        this(CartLineItem.class, metadata, inits);
    }

    public QCartLineItem(Class<? extends CartLineItem> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.cart = inits.isInitialized("cart") ? new QCart(forProperty("cart"), inits.get("cart")) : null;
    }

}

