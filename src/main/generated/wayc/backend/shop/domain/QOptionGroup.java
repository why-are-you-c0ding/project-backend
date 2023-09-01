package wayc.backend.shop.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOptionGroup is a Querydsl query type for OptionGroup
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOptionGroup extends EntityPathBase<OptionGroup> {

    private static final long serialVersionUID = -1525279036L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOptionGroup optionGroup = new QOptionGroup("optionGroup");

    public final wayc.backend.common.domain.QBaseEntity _super = new wayc.backend.common.domain.QBaseEntity(this);

    //inherited
    public final StringPath createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QItem item;

    public final StringPath name = createString("name");

    public final ListPath<Option, QOption> options = this.<Option, QOption>createList("options", Option.class, QOption.class, PathInits.DIRECT2);

    //inherited
    public final EnumPath<wayc.backend.common.domain.BaseStatus> status = _super.status;

    //inherited
    public final StringPath updatedAt = _super.updatedAt;

    public QOptionGroup(String variable) {
        this(OptionGroup.class, forVariable(variable), INITS);
    }

    public QOptionGroup(Path<? extends OptionGroup> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOptionGroup(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOptionGroup(PathMetadata metadata, PathInits inits) {
        this(OptionGroup.class, metadata, inits);
    }

    public QOptionGroup(Class<? extends OptionGroup> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.item = inits.isInitialized("item") ? new QItem(forProperty("item"), inits.get("item")) : null;
    }

}

