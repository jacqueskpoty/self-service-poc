package com.example.poc.archUnit;

import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;


public class HexagonalArchitectureTest {

    @Test
    void AdaptersInAndOutShouldNotDependOnEachOther(){
        
        ArchUnitUtils.checkThatNoDependencyExist(
                ArchUnitUtils.getAdapterInPackage(),
                ArchUnitUtils.getAdapterOutPackage(),
                ArchUnitUtils.getApplicationBasePackage());

        ArchUnitUtils.checkThatNoDependencyExist(
                ArchUnitUtils.getAdapterOutPackage(),
                ArchUnitUtils.getAdapterInPackage(),
                ArchUnitUtils.getApplicationBasePackage());
    }

    @Test
    void PortsInAndPortOutShouldNotDependOnEachOther(){
        ArchUnitUtils.checkThatNoDependencyExist(
                ArchUnitUtils.getApplicationPortInPackage(),
                ArchUnitUtils.getApplicationPortOutPackage(),
                ArchUnitUtils.getApplicationBasePackage());

        ArchUnitUtils.checkThatNoDependencyExist(
                ArchUnitUtils.getApplicationPortOutPackage(),
                ArchUnitUtils.getApplicationPortInPackage(),
                ArchUnitUtils.getApplicationBasePackage());
    }

    @Test
    void AdaptersInShouldNotDependOnPortOut(){
        ArchUnitUtils.checkThatNoDependencyExist(
                ArchUnitUtils.getAdapterInPackage(),
                ArchUnitUtils.getApplicationPortOutPackage(),
                ArchUnitUtils.getApplicationBasePackage());
    }

    @Test
    void AdaptersOutShouldNotDependOnPortIn(){
        ArchUnitUtils.checkThatNoDependencyExist(
                ArchUnitUtils.getApplicationPortOutPackage(),
                ArchUnitUtils.getAdapterInPackage(),
                ArchUnitUtils.getApplicationBasePackage());
    }

    @Test
    void ApplicationShouldNotDependOnAdapters(){
        ArchUnitUtils.checkThatNoDependencyExist(
                ArchUnitUtils.getApplicationPackage(),
                ArchUnitUtils.getAdapterPackage(),
                ArchUnitUtils.getApplicationBasePackage());
    }

    @Test
    void DomainShouldNotDependOnAdapters(){
        ArchUnitUtils.checkThatNoDependencyExist(
                ArchUnitUtils.getDomainPackage(),
                ArchUnitUtils.getAdapterPackage(),
                ArchUnitUtils.getApplicationBasePackage());
    }

    @Test
    void DomainShouldNotDependOnApplication(){
        ArchUnitUtils.checkThatNoDependencyExist(
                ArchUnitUtils.getDomainPackage(),
                ArchUnitUtils.getApplicationPackage(),
                ArchUnitUtils.getApplicationBasePackage());
    }

    @Test
    public void ApplicationServiceShouldBePackagePrivate() {
        classes()
                .that().haveSimpleNameEndingWith(ArchUnitUtils.getApplicationServicePackage())
                .should()
                .bePackagePrivate()
                .check(new ClassFileImporter()
                        .importPackages(ArchUnitUtils.getApplicationServicePackage()));
    }
}
