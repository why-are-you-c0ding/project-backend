package wayc.backend.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.Architectures;
import org.aspectj.lang.annotation.AfterReturning;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@AnalyzeClasses(packages = "wayc.backend", importOptions = ImportOption.DoNotIncludeTests.class)
public class LayerDependenciesTest {



    @ArchTest
    public static final ArchRule layeredArchitectureRule = layeredArchitecture()
            .consideringAllDependencies()
            .layer("Config").definedBy("..config..")
            .layer("Presentation").definedBy("..presentation..")
            .layer("Application").definedBy("..application..")
            .layer("Domain").definedBy("..domain..")
            .layer("Infrastructure").definedBy("..infrastructure..")
            .whereLayer("Presentation").mayOnlyBeAccessedByLayers("Config");
//            .whereLayer("Service").mayOnlyBeAccessedByLayers("Controller")
//            .whereLayer("Persistence").mayOnlyBeAccessedByLayers("Service");

}

/**
 * 프로젠테이션은 어디서도 호출되지 않음. 프레젠테이션은 애플리케이션 계층만 의존
 * 애플리케이션은 도메인과 애플리케이션 계층에서만 사용.
 * 도메인은 도메인끼리만 의존. 애플리케이션과 인프라스트럭쳐에서 호출된다.
 * 인프라스트럭쳐는 도메인만 의존.
 */