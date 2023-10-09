package com.example.spring2023;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ArchitectureTest {

    @Test
    public void testArchitecture() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("com.example.spring2023");

        ArchRule myRule = ArchRuleDefinition.classes().that().resideInAPackage("com.example.spring2023..")
                .should().accessClassesThat().resideInAnyPackage("com.example.spring2023..");

        myRule.check(importedClasses);

        assertThat(myRule).isNotNull();
    }
}
