package com.maruszka.services.springdatajpa;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.maruszka.model.Malt;
import com.maruszka.repositories.MaltRepository;
import com.maruszka.services.MaltService;

@Service
@Profile("springdatajpa")
public class MaltSDJpaService implements MaltService {

	@Autowired
	private final MaltRepository maltRepository;
	
	public MaltSDJpaService(MaltRepository maltRepository) {
		this.maltRepository = maltRepository;
	}

	@Override
	public Set<Malt> findAll() {
		Set<Malt> malts = new HashSet<Malt>();
		maltRepository.findAll().forEach(malts::add);
		
		return malts;
	}

	@Override
	public Malt findById(Long id) {
		return maltRepository.findById(id).orElse(null);
	}

	@Override
	public Malt save(Malt malt) {
		return maltRepository.save(malt);
	}

	@Override
	public void delete(Malt object) {
		maltRepository.delete(object);
	}

	@Override
	public void deleteById(Long id) {
		maltRepository.deleteById(id);
	}

	@Override
	public Malt findByMaltName(String maltName) {
		return maltRepository.findByMaltName(maltName);
	}

	@Override
	public List<Malt> findAllByMaltNameLike(String maltName) {
		return maltRepository.findAllByMaltNameLike(maltName);
	}

}
