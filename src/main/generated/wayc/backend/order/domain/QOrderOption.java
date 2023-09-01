package wayc.backend.order.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QOrderOption is a Querydsl query type for OrderOption
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QOrderOption extends BeanPath<OrderOption> {

    private static final long serialVersionUID = -975732947L;

    public static final QOrderOption orderOption = new QOrderOption("orderOption");

    public final StringPath optionName = createString("optionName");

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public QOrderOption(String variable) {
        super(OrderOption.class, forVariable(variable));
    }

    public QOrderOption(Path<? extends OrderOption> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOrderOption(PathMetadata metadata) {
        super(OrderOption.class, metadata);
    }

}

