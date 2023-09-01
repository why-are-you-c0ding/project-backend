package wayc.backend.stock.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QStock is a Querydsl query type for Stock
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStock extends EntityPathBase<Stock> {

    private static final long serialVersionUID = 874234264L;

    public static final QStock stock = new QStock("stock");

    public final wayc.backend.common.domain.QBaseEntity _super = new wayc.backend.common.domain.QBaseEntity(this);

    //inherited
    public final StringPath createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> quantity = createNumber("quantity", Integer.class);

    //inherited
    public final EnumPath<wayc.backend.common.domain.BaseStatus> status = _super.status;

    //inherited
    public final StringPath updatedAt = _super.updatedAt;

    public QStock(String variable) {
        super(Stock.class, forVariable(variable));
    }

    public QStock(Path<? extends Stock> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStock(PathMetadata metadata) {
        super(Stock.class, metadata);
    }

}

