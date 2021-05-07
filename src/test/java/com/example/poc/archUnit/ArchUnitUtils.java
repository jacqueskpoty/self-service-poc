package com.example.poc.archUnit;


import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;

import java.util.List;

import static com.tngtech.archunit.base.DescribedPredicate.greaterThanOrEqualTo;
import static com.tngtech.archunit.lang.conditions.ArchConditions.containNumberOfElements;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchUnitUtils {

    private static final String APPLICATION_BASE_PACKAGE = "com.example.poc";
    private static final String ADAPTER_PACKAGE = "adapter";
    private static final String ADAPTER_IN_PACKAGE = "adapter.in";
    private static final String ADAPTER_OUT_PACKAGE = "adapter.out";
    private static final String APPLICATION_PACKAGE = "application";
    private static final String APPLICATION_PORT_IN_PACKAGE = "application.port.in";
    private static final String APPLICATION_PORT_OUT_PACKAGE = "application.port.out";
    private static final String APPLICATION_SERVICE_PACKAGE = "application.service";
    private static final String DOMAIN_PACKAGE = "domain";

    public static String getApplicationBasePackage(){
        return APPLICATION_BASE_PACKAGE;
    }

    public static String getFullPackage(String packageName){
        return APPLICATION_BASE_PACKAGE+"."+packageName;
    }

    public static String getAdapterPackage(){
        return getFullPackage(ADAPTER_PACKAGE);
    }

    public static String getAdapterInPackage(){
        return getFullPackage(ADAPTER_IN_PACKAGE);
    }

    public static String getAdapterOutPackage(){
        return getFullPackage(ADAPTER_OUT_PACKAGE);
    }

    public static String getDomainPackage(){
        return getFullPackage(DOMAIN_PACKAGE);
    }

    public static String getApplicationPackage(){
        return getFullPackage(APPLICATION_PACKAGE);
    }

    public static String getApplicationPortInPackage(){
        return getFullPackage(APPLICATION_PORT_IN_PACKAGE);
    }

    public static String getApplicationPortOutPackage(){
        return getFullPackage(APPLICATION_PORT_OUT_PACKAGE);
    }

    public static String getApplicationServicePackage(){
        return getFullPackage(APPLICATION_SERVICE_PACKAGE);
    }


    static void checkThatNoDependencyExist(String fromPackage, String toPackage, String checkPackage){
        noClasses()
                .that()
                .resideInAnyPackage(allClassesInPackage(fromPackage))
                .should()
                .dependOnClassesThat()
                .resideInAnyPackage(allClassesInPackage(toPackage))
                .check(getClassesInPackage(checkPackage));
    }

    static void checkThatNoDependenciesExist(List<String> fromPackages,List<String> toPackages,String checkPackage){
        for(String fromPackage : fromPackages){
            for(String toPackage : toPackages){
                checkThatNoDependencyExist(fromPackage,toPackage,checkPackage);
            }
        }
    }

    static String allClassesInPackage(String packageName){
        return packageName+"..";
    }

    static JavaClasses getClassesInPackage(String packageName) {
        return new ClassFileImporter().importPackages(packageName);
    }

    static void denyEmptyPackage(String packageName){
        classes()
                .that()
                .resideInAnyPackage(allClassesInPackage(packageName))
                .should(containNumberOfElements(greaterThanOrEqualTo(1)))
                .check(getClassesInPackage(packageName));
    }

    static void denyEmptyPackages(List<String> packages) {
        for (String packageName : packages) {
            denyEmptyPackage(packageName);
        }
    }

    static void checkPackagePrivateClassesOnly(String packageName){
        classes()
                .that().resideInAnyPackage(allClassesInPackage(packageName))
                .should()
                .bePackagePrivate()
                .check(getClassesInPackage(packageName));
    }
}
