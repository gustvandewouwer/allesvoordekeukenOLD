package be.vdab.services;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import be.vdab.entities.Artikel;
import be.vdab.filters.JPAFilter;
import be.vdab.repositories.ArtikelRepository;

public class ArtikelService {
	private final ArtikelRepository artikelRepository = new ArtikelRepository();

	public Optional<Artikel> read(long id) {
		EntityManager entityManager = JPAFilter.getEntityManager();
		try {
			return artikelRepository.read(id, entityManager);
		} finally {
			entityManager.close();
		}
	}

	public void create(Artikel artikel) {
		EntityManager entityManager = JPAFilter.getEntityManager();
		entityManager.getTransaction().begin();
		try {
			artikelRepository.create(artikel, entityManager);
			entityManager.getTransaction().commit();
		} catch (PersistenceException ex) {
			entityManager.getTransaction().rollback();
			throw ex;
		} finally {
			entityManager.close();
		}
	}
}
