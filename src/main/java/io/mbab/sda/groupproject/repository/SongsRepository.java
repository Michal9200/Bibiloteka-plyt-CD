package io.mbab.sda.groupproject.repository;

import io.mbab.sda.groupproject.entity.Song;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class SongsRepository implements CrudRepository<Song, Integer> {

  private final EntityManager em;

  @Override
  public List<Song> getAll() {
    return em.createQuery("FROM Song", Song.class).getResultList();
  }

  @Override
  public Optional<Song> findById(Integer integer) {
      try{
    CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
    CriteriaQuery<Song> criteriaQuery = criteriaBuilder.createQuery(Song.class);
    Root<Song> root = criteriaQuery.from(Song.class);
    Predicate predicate = criteriaBuilder.equal(root.get("id"), integer);
    Song entity = em.createQuery(criteriaQuery.select(root).where(predicate)).getSingleResult();
    return Optional.of(entity);
    } catch (NoResultException e) {
          return Optional.empty();
      }
  }

  @Override
  public Song create(Song entity) {
    em.getTransaction().begin();
    em.persist(entity);
    em.getTransaction().commit();
    return entity;
  }

  @Override
  public Song update(Song entity) {
    return em.merge(entity);
  }

  @Override
  public void delete(Integer integer) {
    var criteriaBuilder = em.getCriteriaBuilder();
    var criteriaDelete = criteriaBuilder.createCriteriaDelete(Song.class);
    var root = criteriaDelete.from(Song.class);
    var predicate = criteriaBuilder.equal(root.get("id"), integer);

    em.createQuery(criteriaDelete.where(predicate)).executeUpdate();
  }

  public List<Song> findByAuthor(String author) {
    var criteriaBuilder = em.getCriteriaBuilder();
    var criteriaQuery = criteriaBuilder.createQuery(Song.class);
    var root = criteriaQuery.from(Song.class);
    var predicate = criteriaBuilder.equal(root.get("author"), author);
    return em.createQuery(criteriaQuery.select(root).where(predicate)).getResultList();
  }

  public List<Song> getByTitle(String title) {
    var criteriaBuilder = em.getCriteriaBuilder();
    var criteriaQuery = criteriaBuilder.createQuery(Song.class);
    var root = criteriaQuery.from(Song.class);
    var predicate = criteriaBuilder.like(root.get("title"), title);
    return em.createQuery(criteriaQuery.select(root).where(predicate)).getResultList();
  }

  public List<Song>  findByAlbum(Integer integer) {
      CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
      CriteriaQuery<Song> criteriaQuery = criteriaBuilder.createQuery(Song.class);
      Root<Song> root = criteriaQuery.from(Song.class);
      Predicate predicate = criteriaBuilder.equal(root.get("album"), integer);
      return em.createQuery(criteriaQuery.select(root).where(predicate)).getResultList();
  }
}
