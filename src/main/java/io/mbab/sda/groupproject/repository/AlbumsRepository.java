package io.mbab.sda.groupproject.repository;

import io.mbab.sda.groupproject.entity.Album;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;


public class AlbumsRepository extends AbstractRepository<Album, Integer> {


  public AlbumsRepository(EntityManager em) {
    super(em, Album.class);
  }

  public void addAlbummFromList (List<Album> listAlbums){

    Album album= new Album();
    for (int i = 0; i <listAlbums.size() ; i++) {
      em.getTransaction().begin();
      album = listAlbums.get(i);
      em.merge(album);
      em.getTransaction().commit();
    }
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
