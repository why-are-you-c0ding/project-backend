package wayc.backend.security.role;

public enum Role {
    CONSUMER, SELLER
}


/**
 * @Bean
 * AccessDecisionVoter hierarchyVoter() {
 *     RoleHierarchy hierarchy = new RoleHierarchyImpl();
 *     hierarchy.setHierarchy("ROLE_ADMIN > ROLE_STAFF\n" +
 *             "ROLE_STAFF > ROLE_USER\n" +
 *             "ROLE_USER > ROLE_GUEST");
 *     return new RoleHierarchyVoter(hierarchy);
 * }
 *
 * https://docs.spring.io/spring-security/reference/servlet/authorization/architecture.html
 *
 * 현재는 권한이 그렇게 중요하지 않아서 관리하기 편하게 enum으로 하지만,
 * 추후에는 공식 문서가 지원하는 방식이나 계층형 테이블을 사용하면 좋을 듯.
 */