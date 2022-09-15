package com.kotak.mb2.archunit;

import static com.tngtech.archunit.base.DescribedPredicate.not;
import static com.tngtech.archunit.core.domain.JavaClass.Predicates.belongToAnyOf;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noMethods;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.conditions.ArchPredicates;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AbstractArchUnitTests {

    private final Logger logger = LoggerFactory.getLogger(AbstractArchUnitTests.class);

    @ArchTest
    public void utils_classes_should_only_have_static_methods(JavaClasses javaClasses) {
        ArchUnitRules.utilClassesMethodsShouldBeStatic().check(javaClasses);
    }

    @Test
    public void utils_classes_should_not_be_injected(JavaClasses javaClasses) {
        ArchUnitRules.utilsClassesShouldNotBeInjected().check(javaClasses);
    }

    @ArchTest
    public void layers_should_be_free_of_cycles(JavaClasses javaClasses) {
        ArchUnitRules.layersShouldBeFreeOfCycles().check(javaClasses);
    }

    @ArchTest
    public void classes_should_not_have_public_fields(JavaClasses javaClasses) {
        ArchUnitRules.noPublicField().check(javaClasses);
    }

    @ArchTest
    public void should_not_call_exception_print_stacktrace(JavaClasses javaClasses) {
        ArchUnitRules.noExceptionPrintStacktrace().check(javaClasses);
    }

    @ArchTest
    public void fieldAndValueInjectionNotAllowed(JavaClasses javaClasses) {
        ArchUnitRules.DO_NOT_USE_FIELD_INJECTION.check(javaClasses);
    }

    @ArchTest
    void shouldNotUseJunit4Classes(JavaClasses javaClasses) {

        noClasses()
            .should().accessClassesThat().resideInAnyPackage("org.junit")
            .because("Tests should use Junit5 instead of Junit4")
            .check(javaClasses);

        noMethods().should().beAnnotatedWith("org.junit.Test")
            .orShould().beAnnotatedWith("org.junit.Ignore")
            .because("Tests should use Junit5 instead of Junit4")
            .check(javaClasses);
    }
}
