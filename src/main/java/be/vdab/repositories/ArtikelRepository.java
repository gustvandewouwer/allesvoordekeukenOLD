package be.vdab.repositories;

import java.util.Optional;

import javax.persistence.EntityManager;

import be.vdab.entities.Artikel;
import be.vdab.filters.JPAFilter;

public class ArtikelRepository {
	public Optional<Artikel> read(long id) {
		EntityManager entityManager = JPAFilter.getEntityManager();
		try {
			return Optional.ofNullable(entityManager.find(Artikel.class, id));
		} finally {
			entityManager.close();
		}
	}
}