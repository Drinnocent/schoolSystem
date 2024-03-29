/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.gov.sars.common;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author S2028389
 */
//Configuration clasesess annotainted with
//to scan repositories we need to use reposi
//za.gov.sars.persistence that where we find out repositories
//Transaction manager for CRUD which is made simple for you and what it will be managing which needs to manage the domain
//component scan which is for scanning one
@Configuration
@EnableJpaRepositories(basePackages = {"za.gov.sars.persistence"}, transactionManagerRef = "transactionManager") //this where repositories are act
@ComponentScan(basePackages = {"za.gov.sars"}) //that where you scan
@EnableTransactionManagement  //Now you enable all the transaction that were scans
public class TestDataSourceConfiguration {

    @Bean(name = "localContainerEntityManagerFactoryBean")//we are naming this method as a bin we can call this configuration anywhere
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() { //this environment it actually an enum
        Map<String, Object> hibernateProps = new LinkedHashMap<>();  //Maps works like table take the key and the value,value in the form of object
        hibernateProps.put(org.hibernate.cfg.Environment.DIALECT, "org.hibernate.dialect.SQLServerDialect"); //it returns a string called dialet which we will be using
        hibernateProps.put(org.hibernate.cfg.Environment.SHOW_SQL, true); //returns th word sql thsi
        hibernateProps.put(org.hibernate.cfg.Environment.FORMAT_SQL, true);
        hibernateProps.put(org.hibernate.cfg.Environment.HBM2DDL_AUTO, "update");//how should it generate do you want to create current data or delete we are saying if there tables just update
        hibernateProps.put(org.hibernate.cfg.Environment.ENABLE_LAZY_LOAD_NO_TRANS, true);//enable easily loading
        hibernateProps.put(org.hibernate.cfg.Environment.GENERATE_STATISTICS, true);//we want to edit
        hibernateProps.put(org.hibernate.cfg.Environment.DEFAULT_BATCH_FETCH_SIZE, 100);//if we want to fetch data how big can it be
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();//that wherewe set up our property
        localContainerEntityManagerFactoryBean.setPersistenceUnitName("server-persistence-unit");
        localContainerEntityManagerFactoryBean.setPackagesToScan("za.gov.sars.domain");//you are tellignthat this domain are sitting here
        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        localContainerEntityManagerFactoryBean.setJpaPropertyMap(hibernateProps);//all the properties we are passing it here
        localContainerEntityManagerFactoryBean.setDataSource(DatasourceUtility.getDatasource());
        return localContainerEntityManagerFactoryBean;
    }

    @Bean(name = "entityManagerFactory")//it a container it passes to manager entity factory generated by the entity entiy
    public EntityManagerFactory entityManagerFactory(@Qualifier("localContainerEntityManagerFactoryBean") LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) { //object of Entity
        return entityManagerFactoryBean.getObject();//qualifier passes container
    }

    @Bean(name = "transactionManager")//
    public PlatformTransactionManager transactionManager(@Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {//we call this entity managerfactory
        return new JpaTransactionManager(entityManagerFactory);
    }

}
