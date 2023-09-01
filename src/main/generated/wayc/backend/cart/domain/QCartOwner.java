package wayc.backend.cart.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCartOwner is a Querydsl query type for CartOwner
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QCartOwner extends BeanPath<CartOwner> {

    private static final long serialVersionUID = 909783747L;

    public static final QCartOwner cartOwner = new QCartOwner("cartOwner");

    public final NumberPath<Long> memberId = createNumber("memberId", Long.class);

    public QCartOwner(String variable) {
        super(CartOwner.class, forVariable(variable));
    }

    public QCartOwner(Path<? extends CartOwner> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCartOwner(PathMetadata metadata) {
        super(CartOwner.class, metadata);
    }

}

