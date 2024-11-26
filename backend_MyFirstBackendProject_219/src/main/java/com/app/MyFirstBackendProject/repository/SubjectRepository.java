package com.app.MyFirstBackendProject.repository;

import com.app.MyFirstBackendProject.model.Subject;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;


@Component
public class SubjectRepository extends SimpleJpaRepository<Subject, String> {
    private final EntityManager em;
    public SubjectRepository(EntityManager em) {
        super(Subject.class, em);
        this.em = em;
    }
    @Override
    public List<Subject> findAll() {
        return em.createNativeQuery("Select * from \"myfirstbackendproject\".\"Subject\"", Subject.class).getResultList();
    }
}