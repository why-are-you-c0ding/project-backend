package wayc.backend.architecture;


import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@AnalyzeClasses(packages = "wayc.backend",
        importOptions = {ImportOption.DoNotIncludeTests.class}
)
public class LayerDependenciesTest {


    private static final String PRESENTATION = "Presentation";
    private static final String APPLICATION = "Application";
    private static final String DOMAIN = "Domain";
    private static final String INFRA = "Infrastructure";

    @ArchTest
    public static final ArchRule layeredArchitectureRule = layeredArchitecture()
            .consideringOnlyDependenciesInLayers()
            .layer(PRESENTATION).definedBy("..presentation..")
            .layer(APPLICATION).definedBy("..application..")
            .layer(DOMAIN).definedBy("..domain..")
            .layer(INFRA).definedBy("..infrastructure..")
            .whereLayer(PRESENTATION).mayNotBeAccessedByAnyLayer()
            .whereLayer(PRESENTATION).mayOnlyAccessLayers(PRESENTATION, APPLICATION, DOMAIN)
            .whereLayer(APPLICATION).mayOnlyBeAccessedByLayers(PRESENTATION, APPLICATION)
            .whereLayer(APPLICATION).mayOnlyAccessLayers(APPLICATION, DOMAIN)
            .whereLayer(DOMAIN).mayNotAccessAnyLayer()
            .whereLayer(INFRA).mayOnlyAccessLayers(DOMAIN)
            .whereLayer(INFRA).mayNotBeAccessedByAnyLayer();
}

/**
 * 프로젠테이션은 어디서도 호출되지 않음. 프레젠테이션은 애플리케이션 계층만 의존
 * 애플리케이션은 도메인과 애플리케이션 계층에서만 사용.
 * 도메인은 도메인끼리만 의존. 애플리케이션과 인프라스트럭쳐에서 호출된다.
 * 인프라스트럭쳐는 도메인만 의존.
 */