package wayc.backend.order.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QOrderer is a Querydsl query type for Orderer
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QOrderer extends BeanPath<Orderer> {

    private static final long serialVersionUID = -456842427L;

    public static final QOrderer orderer = new QOrderer("orderer");

    public final NumberPath<Long> memberId = createNumber("memberId", Long.class);

    public QOrderer(String variable) {
        super(Orderer.class, forVariable(variable));
    }

    public QOrderer(Path<? extends Orderer> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOrderer(PathMetadata metadata) {
        super(Orderer.class, metadata);
    }

}

