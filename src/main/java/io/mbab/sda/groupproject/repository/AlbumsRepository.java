package io.mbab.sda.groupproject.repository;

import io.mbab.sda.groupproject.entity.Albums;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
public class AlbumsRepository implements CrudRepository<Albums, Integer> {

    private final EntityManager em;

    @Override
    public List<Albums> getAll() {
        return em.createQuery("FROM Albums", Albums.class)
                .getResultList();
    }

    @Override
    public Albums findById(Integer integer) {
        return null;
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
        return null;
    }

    @Override
    public void delete(Integer integer) {

    }
}
