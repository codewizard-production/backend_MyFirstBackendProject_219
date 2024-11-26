package com.app.MyFirstBackendProject.repository;

import com.app.MyFirstBackendProject.model.College;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;


@Component
public class CollegeRepository extends SimpleJpaRepository<College, String> {
    private final EntityManager em;
    public CollegeRepository(EntityManager em) {
        super(College.class, em);
        this.em = em;
    }
    @Override
    public List<College> findAll() {
        return em.createNativeQuery("Select * from \"myfirstbackendproject\".\"College\"", College.class).getResultList();
    }
}