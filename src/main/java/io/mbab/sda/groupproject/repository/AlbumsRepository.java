package io.mbab.sda.groupproject.repository;

import io.mbab.sda.groupproject.entity.Album;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@RequiredArgsConstructor
public class AlbumsRepository implements CrudRepository<Album, Integer> {

  private final EntityManager em;

  @Override
  public List<Album> getAll() {
    return em.createQuery("FROM Album", Album.class).getResultList();
  }

  @Override
  public Optional<Album> findById(Integer id) {
    try {
      var criteriaBuilder = em.getCriteriaBuilder();
      var criteriaQuery = criteriaBuilder.createQuery(Album.class);
      var root = criteriaQuery.from(Album.class);
      var predicate = criteriaBuilder.equal(root.get("id"), id);
      var entity = em.createQuery(criteriaQuery.select(root).where(predicate)).getSingleResult();
      return Optional.of(entity);
    } catch (NoResultException e) {
      return Optional.empty();
    }
  }

  @Override
  public Album create(Album entity) {
    em.getTransaction().begin();
    em.persist(entity);
    em.getTransaction().commit();
    return entity;
  }

  @Override
  public Album update(Album entity) {
    return em.merge(entity);
  }

  @Override
  public void delete(Integer integer) {
    var criteriaBuilder = em.getCriteriaBuilder();
    var criteriaDelete = criteriaBuilder.createCriteriaDelete(Album.class);
    var root = criteriaDelete.from(Album.class);
    var predicate = criteriaBuilder.equal(root.get("id"), integer);

    em.createQuery(criteriaDelete.where(predicate)).executeUpdate();
  }

  public List<Album> findByAuthor(String author) {

    var criteriaBuilder = em.getCriteriaBuilder();
    var criteriaQuery = criteriaBuilder.createQuery(Album.class);
    var root = criteriaQuery.from(Album.class);
    var predicate = criteriaBuilder.equal(root.get("author"), author);
    return em.createQuery(criteriaQuery.select(root).where(predicate)).getResultList();
  }

  public List<Album> findByAlbumName(String albumName) {

    var criteriaBuilder = em.getCriteriaBuilder();
    var criteriaQuery = criteriaBuilder.createQuery(Album.class);
    var root = criteriaQuery.from(Album.class);
    var predicate = criteriaBuilder.equal(root.get("albumName"), albumName);
    return em.createQuery(criteriaQuery.select(root).where(predicate)).getResultList();
  }


}
