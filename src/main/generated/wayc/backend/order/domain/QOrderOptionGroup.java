package wayc.backend.order.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrderOptionGroup is a Querydsl query type for OrderOptionGroup
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrderOptionGroup extends EntityPathBase<OrderOptionGroup> {

    private static final long serialVersionUID = 1357380434L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrderOptionGroup orderOptionGroup = new QOrderOptionGroup("orderOptionGroup");

    public final wayc.backend.common.domain.QBaseEntity _super = new wayc.backend.common.domain.QBaseEntity(this);

    //inherited
    public final StringPath createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final QOrderOption orderOptions;

    //inherited
    public final EnumPath<wayc.backend.common.domain.BaseStatus> status = _super.status;

    //inherited
    public final StringPath updatedAt = _super.updatedAt;

    public QOrderOptionGroup(String variable) {
        this(OrderOptionGroup.class, forVariable(variable), INITS);
    }

    public QOrderOptionGroup(Path<? extends OrderOptionGroup> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrderOptionGroup(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrderOptionGroup(PathMetadata metadata, PathInits inits) {
        this(OrderOptionGroup.class, metadata, inits);
    }

    public QOrderOptionGroup(Class<? extends OrderOptionGroup> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.orderOptions = inits.isInitialized("orderOptions") ? new QOrderOption(forProperty("orderOptions")) : null;
    }

}

