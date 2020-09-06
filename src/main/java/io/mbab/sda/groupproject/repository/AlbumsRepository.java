package io.mbab.sda.groupproject.repository;

import io.mbab.sda.groupproject.entity.Albums;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class AlbumsRepository implements CrudRepository<Albums, Integer> {

  private final EntityManager em;

  @Override
  public List<Albums> getAll() {
    return em.createQuery("FROM Albums", Albums.class).getResultList();
  }

  @Override
  public Optional<Albums> findById(Integer id) {
    try {
      var criteriaBuilder = em.getCriteriaBuilder();
      var criteriaQuery = criteriaBuilder.createQuery(Albums.class);
      var root = criteriaQuery.from(Albums.class);
      var predicate = criteriaBuilder.equal(root.get("id"), id);
      var entity = em.createQuery(criteriaQuery.select(root).where(predicate)).getSingleResult();
      return Optional.of(entity);
    } catch (NoResultException e) {
      return Optional.empty();
    }
  }

  @Override
  public Albums create(Albums entity) {
    em.getTransaction().begin();
    em.persist(entity);
    em.getTransaction().commit();
    return entity;
  }

  @Override
  public Albums update(Albums entity) {
    return em.merge(entity);
  }

  @Override
  public void delete(Integer integer) {
    var criteriaBuilder = em.getCriteriaBuilder();
    var criteriaDelete = criteriaBuilder.createCriteriaDelete(Albums.class);
    var root = criteriaDelete.from(Albums.class);
    var predicate = criteriaBuilder.equal(root.get("id"), integer);

    em.createQuery(criteriaDelete.where(predicate)).executeUpdate();
  }

  public List<Albums> findByAuthor(String author) {

    var criteriaBuilder = em.getCriteriaBuilder();
    var criteriaQuery = criteriaBuilder.createQuery(Albums.class);
    var root = criteriaQuery.from(Albums.class);
    var predicate = criteriaBuilder.equal(root.get("author"), author);
    return em.createQuery(criteriaQuery.select(root).where(predicate)).getResultList();
  }

  public List<Albums> getByAlbumName(String albumName) {

    var criteriaBuilder = em.getCriteriaBuilder();
    var criteriaQuery = criteriaBuilder.createQuery(Albums.class);
    var root = criteriaQuery.from(Albums.class);
    var predicate = criteriaBuilder.equal(root.get("albumName"), albumName);
    return em.createQuery(criteriaQuery.select(root).where(predicate)).getResultList();
  }
}
