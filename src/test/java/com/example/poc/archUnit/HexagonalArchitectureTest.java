package com.example.poc.archUnit;

import org.junit.jupiter.api.Test;


public class HexagonalArchitectureTest {

    @Test
    void AdaptersInAndOutShouldNotDependOnEachOther(){
        
        ArchUnitUtils.checkThatNoDependenciesExist(
                ArchUnitUtils.getAdapterInPackages(),
                ArchUnitUtils.getAdapterOutPackages(),
                ArchUnitUtils.getApplicationBasePackage());

        ArchUnitUtils.checkThatNoDependenciesExist(
                ArchUnitUtils.getAdapterOutPackages(),
                ArchUnitUtils.getAdapterInPackages(),
                ArchUnitUtils.getApplicationBasePackage());
    }

    @Test
    void PortsInAndPortOutShouldNotDependOnEachOther(){
        ArchUnitUtils.checkThatNoDependenciesExist(
                ArchUnitUtils.getApplicationPortInPackages(),
                ArchUnitUtils.getApplicationPortOutPackages(),
                ArchUnitUtils.getApplicationBasePackage());

        ArchUnitUtils.checkThatNoDependenciesExist(
                ArchUnitUtils.getApplicationPortOutPackages(),
                ArchUnitUtils.getApplicationPortInPackages(),
                ArchUnitUtils.getApplicationBasePackage());
    }

    @Test
    void AdaptersInShouldNotDependOnPortOut(){
        ArchUnitUtils.checkThatNoDependenciesExist(
                ArchUnitUtils.getAdapterInPackages(),
                ArchUnitUtils.getApplicationPortOutPackages(),
                ArchUnitUtils.getApplicationBasePackage());
    }

    @Test
    void AdaptersOutShouldNotDependOnPortIn(){
        ArchUnitUtils.checkThatNoDependenciesExist(
                ArchUnitUtils.getApplicationPortOutPackages(),
                ArchUnitUtils.getAdapterInPackages(),
                ArchUnitUtils.getApplicationBasePackage());
    }

    @Test
    void ApplicationShouldNotDependOnAdapters(){
        ArchUnitUtils.checkThatNoDependenciesExist(
                ArchUnitUtils.getApplicationPackages(),
                ArchUnitUtils.getAdapterPackages(),
                ArchUnitUtils.getApplicationBasePackage());
    }

    @Test
    void DomainShouldNotDependOnAdapters(){
        ArchUnitUtils.checkThatNoDependenciesExist(
                ArchUnitUtils.getDomainPackages(),
                ArchUnitUtils.getAdapterPackages(),
                ArchUnitUtils.getApplicationBasePackage());
    }

    @Test
    void DomainShouldNotDependOnApplication(){
        ArchUnitUtils.checkThatNoDependencyExist(
                ArchUnitUtils.getDomainPackage(),
                ArchUnitUtils.getApplicationPackage(),
                ArchUnitUtils.getApplicationBasePackage());
    }
}
