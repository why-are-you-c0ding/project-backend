package wayc.backend.order.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPay is a Querydsl query type for Pay
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPay extends EntityPathBase<Pay> {

    private static final long serialVersionUID = -180170638L;

    public static final QPay pay1 = new QPay("pay1");

    public final wayc.backend.common.domain.QBaseEntity _super = new wayc.backend.common.domain.QBaseEntity(this);

    //inherited
    public final StringPath createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> orderId = createNumber("orderId", Long.class);

    public final NumberPath<Integer> pay = createNumber("pay", Integer.class);

    //inherited
    public final EnumPath<wayc.backend.common.domain.BaseStatus> status = _super.status;

    //inherited
    public final StringPath updatedAt = _super.updatedAt;

    public QPay(String variable) {
        super(Pay.class, forVariable(variable));
    }

    public QPay(Path<? extends Pay> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPay(PathMetadata metadata) {
        super(Pay.class, metadata);
    }

}

