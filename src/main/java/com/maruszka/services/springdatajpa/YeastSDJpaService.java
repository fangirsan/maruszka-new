package com.maruszka.services.springdatajpa;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.maruszka.model.Yeast;
import com.maruszka.repositories.YeastRepository;
import com.maruszka.services.YeastService;

@Service
@Profile("springdatajpa")
public class YeastSDJpaService implements YeastService {

	private final YeastRepository yeastRepository;
	
	public YeastSDJpaService(YeastRepository yeastRepository) {
		this.yeastRepository = yeastRepository;
	}

	@Override
	public Set<Yeast> findAll() {
		Set<Yeast> yeasts = new HashSet<>();
		yeastRepository.findAll().forEach(yeasts::add);
		return yeasts;
	}

	@Override
	public Yeast findById(Long id) {
		return yeastRepository.findById(id).orElse(null);
	}

	@Override
	public Yeast save(Yeast object) {
		return yeastRepository.save(object);
	}

	@Override
	public void delete(Yeast object) {
		yeastRepository.delete(object);
	}

	@Override
	public void deleteById(Long id) {
		yeastRepository.deleteById(id);
	}

	@Override
	public Yeast findByYeastName(String yeastName) {
		return yeastRepository.findByYeastName(yeastName);
	}

	@Override
	public List<Yeast> findAllByYeastNameLike(String YeastName) {
		return yeastRepository.findAllByYeastNameLike(YeastName);
	}

}
