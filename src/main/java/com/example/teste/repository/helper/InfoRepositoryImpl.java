package com.example.teste.repository.helper;

import com.example.teste.domain.Info;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Component
public class InfoRepositoryImpl implements InfoRepositoryUpdate {

    @PersistenceContext
    private EntityManager manager;


    @Transactional
    @Override
    public void updateByLogic(Info model, Integer logic) {
      Query query = manager.createQuery("UPDATE Info i SET i.model = :model  WHERE i.logic = :logic")
              .setParameter("logic", logic)
              .setParameter("model", model);
      query.executeUpdate();
      manager.getTransaction().commit();


    }
}
