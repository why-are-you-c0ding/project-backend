package wayc.backend.order.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrder is a Querydsl query type for Order
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrder extends EntityPathBase<Order> {

    private static final long serialVersionUID = -1345725288L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrder order = new QOrder("order1");

    public final wayc.backend.common.domain.QBaseEntity _super = new wayc.backend.common.domain.QBaseEntity(this);

    public final QAddress address;

    public final NumberPath<Integer> count = createNumber("count", Integer.class);

    //inherited
    public final StringPath createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> itemId = createNumber("itemId", Long.class);

    public final StringPath name = createString("name");

    public final QOrderer orderer;

    public final ListPath<OrderOptionGroup, QOrderOptionGroup> orderOptionGroups = this.<OrderOptionGroup, QOrderOptionGroup>createList("orderOptionGroups", OrderOptionGroup.class, QOrderOptionGroup.class, PathInits.DIRECT2);

    public final EnumPath<OrderStatus> orderStatus = createEnum("orderStatus", OrderStatus.class);

    public final NumberPath<Integer> payment = createNumber("payment", Integer.class);

    //inherited
    public final EnumPath<wayc.backend.common.domain.BaseStatus> status = _super.status;

    //inherited
    public final StringPath updatedAt = _super.updatedAt;

    public QOrder(String variable) {
        this(Order.class, forVariable(variable), INITS);
    }

    public QOrder(Path<? extends Order> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrder(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrder(PathMetadata metadata, PathInits inits) {
        this(Order.class, metadata, inits);
    }

    public QOrder(Class<? extends Order> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.address = inits.isInitialized("address") ? new QAddress(forProperty("address")) : null;
        this.orderer = inits.isInitialized("orderer") ? new QOrderer(forProperty("orderer")) : null;
    }

}

