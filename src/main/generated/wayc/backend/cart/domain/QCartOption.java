package wayc.backend.cart.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCartOption is a Querydsl query type for CartOption
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QCartOption extends BeanPath<CartOption> {

    private static final long serialVersionUID = -1867756955L;

    public static final QCartOption cartOption = new QCartOption("cartOption");

    public final StringPath optionName = createString("optionName");

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public QCartOption(String variable) {
        super(CartOption.class, forVariable(variable));
    }

    public QCartOption(Path<? extends CartOption> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCartOption(PathMetadata metadata) {
        super(CartOption.class, metadata);
    }

}

