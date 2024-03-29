package wayc.backend.config;

import org.hibernate.dialect.MySQL8Dialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.type.StandardBasicTypes;

public class MysqlCustomDialect extends MySQL8Dialect {

    public MysqlCustomDialect(){
        super();

        this.registerFunction(
                "match",
                new SQLFunctionTemplate(StandardBasicTypes.DOUBLE, "match (?1) against (?2 in boolean mode) and 1")
        );

        this.registerFunction(
                "match2",
                new SQLFunctionTemplate(StandardBasicTypes.DOUBLE, "match (?1, ?2) against (?3 in boolean mode)")
        );
    }
}
