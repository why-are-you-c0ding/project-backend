package wayc.backend.cart.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCartOptionGroup is a Querydsl query type for CartOptionGroup
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCartOptionGroup extends EntityPathBase<CartOptionGroup> {

    private static final long serialVersionUID = -236358886L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCartOptionGroup cartOptionGroup = new QCartOptionGroup("cartOptionGroup");

    public final wayc.backend.common.domain.QBaseEntity _super = new wayc.backend.common.domain.QBaseEntity(this);

    public final QCartLineItem cartLineItem;

    public final QCartOption cartOption;

    //inherited
    public final StringPath createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    //inherited
    public final EnumPath<wayc.backend.common.domain.BaseStatus> status = _super.status;

    //inherited
    public final StringPath updatedAt = _super.updatedAt;

    public QCartOptionGroup(String variable) {
        this(CartOptionGroup.class, forVariable(variable), INITS);
    }

    public QCartOptionGroup(Path<? extends CartOptionGroup> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCartOptionGroup(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCartOptionGroup(PathMetadata metadata, PathInits inits) {
        this(CartOptionGroup.class, metadata, inits);
    }

    public QCartOptionGroup(Class<? extends CartOptionGroup> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.cartLineItem = inits.isInitialized("cartLineItem") ? new QCartLineItem(forProperty("cartLineItem"), inits.get("cartLineItem")) : null;
        this.cartOption = inits.isInitialized("cartOption") ? new QCartOption(forProperty("cartOption")) : null;
    }

}

