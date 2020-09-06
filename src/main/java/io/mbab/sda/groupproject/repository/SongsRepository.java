package io.mbab.sda.groupproject.repository;

import io.mbab.sda.groupproject.entity.Albums;
import io.mbab.sda.groupproject.entity.Songs;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@RequiredArgsConstructor
public class SongsRepository implements CrudRepository<Songs, Integer> {

  private final EntityManager em;

  @Override
  public List<Songs> getAll() {
    return em.createQuery("FROM Songs", Songs.class).getResultList();
  }

  @Override
  public Songs findById(Integer integer) {

    CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
    CriteriaQuery<Songs> criteriaQuery = criteriaBuilder.createQuery(Songs.class);
    Root<Songs> root = criteriaQuery.from(Songs.class);
    Predicate predicate = criteriaBuilder.equal(root.get("id"), integer);
    Songs entity = em.createQuery(criteriaQuery.select(root).where(predicate)).getSingleResult();
    return entity;
  }

  @Override
  public Songs create(Songs entity) {
    em.getTransaction().begin();
    em.persist(entity);
    em.getTransaction().commit();
    return entity;
  }

  @Override
  public Songs update(Songs entity) {
    return em.merge(entity);
  }

  @Override
  public void delete(Integer integer) {
    var criteriaBuilder = em.getCriteriaBuilder();
    var criteriaDelete = criteriaBuilder.createCriteriaDelete(Songs.class);
    var root = criteriaDelete.from(Songs.class);
    var predicate = criteriaBuilder.equal(root.get("id"), integer);

    em.createQuery(criteriaDelete.where(predicate)).executeUpdate();
  }

  public List<Songs> findByAuthor(String author) {
    var criteriaBuilder = em.getCriteriaBuilder();
    var criteriaQuery = criteriaBuilder.createQuery(Songs.class);
    var root = criteriaQuery.from(Songs.class);
    var predicate = criteriaBuilder.equal(root.get("author"), author);
    return em.createQuery(criteriaQuery.select(root).where(predicate)).getResultList();
  }

  public Songs getByTitle(String title) {
    var criteriaBuilder = em.getCriteriaBuilder();
    var criteriaQuery = criteriaBuilder.createQuery(Songs.class);
    var root = criteriaQuery.from(Songs.class);
    var predicate = criteriaBuilder.equal(root.get("title"), title);
    return em.createQuery(criteriaQuery.select(root).where(predicate)).getSingleResult();
  }
}
