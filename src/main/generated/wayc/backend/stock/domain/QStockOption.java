package wayc.backend.stock.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QStockOption is a Querydsl query type for StockOption
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStockOption extends EntityPathBase<StockOption> {

    private static final long serialVersionUID = -60185555L;

    public static final QStockOption stockOption = new QStockOption("stockOption");

    public final wayc.backend.common.domain.QBaseEntity _super = new wayc.backend.common.domain.QBaseEntity(this);

    //inherited
    public final StringPath createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> optionId = createNumber("optionId", Long.class);

    //inherited
    public final EnumPath<wayc.backend.common.domain.BaseStatus> status = _super.status;

    public final NumberPath<Long> stockId = createNumber("stockId", Long.class);

    //inherited
    public final StringPath updatedAt = _super.updatedAt;

    public QStockOption(String variable) {
        super(StockOption.class, forVariable(variable));
    }

    public QStockOption(Path<? extends StockOption> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStockOption(PathMetadata metadata) {
        super(StockOption.class, metadata);
    }

}

