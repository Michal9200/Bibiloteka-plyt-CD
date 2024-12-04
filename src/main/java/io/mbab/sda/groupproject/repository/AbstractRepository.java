package io.mbab.sda.groupproject.repository;

import com.sun.xml.bind.v2.model.core.ID;
import io.mbab.sda.groupproject.entity.Song;
import lombok.RequiredArgsConstructor;
import org.w3c.dom.Entity;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class AbstractRepository<Entity, Integer> implements CrudRepository<Entity, Integer> {

    protected final EntityManager em;
    private final Class<Entity> entityClass;

    public List<Entity> getAll(){
        var criteriaBuilder = em.getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(entityClass);
        var root = criteriaQuery.from(entityClass);

        return em.createQuery(criteriaQuery.select(root)).getResultList();
    }

    public Optional<Entity> findById(Integer id){
        try {
        var criteriaBuilder = em.getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(entityClass);
        var root = criteriaQuery.from(entityClass);
        var predicate = criteriaBuilder.equal(root.get("id"), id);
        var entity = em.createQuery(criteriaQuery.select(root).where(predicate)).getSingleResult();
        return Optional.of(entity);}
        catch (NoResultException e) {
        return Optional.empty();
    }
    }

    public Entity create(Entity entity){
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        return entity;
    };

    public Entity update(Entity entity){
        return em.merge(entity);
    }

    public void delete(Integer id){
        var criteriaBuilder = em.getCriteriaBuilder();
        var criteriaDelete = criteriaBuilder.createCriteriaDelete(entityClass);
        var root = criteriaDelete.from(entityClass);
        var predicate = criteriaBuilder.equal(root.get("id"), id);

        em.createQuery(criteriaDelete.where(predicate)).executeUpdate();
    }

}
