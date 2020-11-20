package br.com.vr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.vr.entity.ItemVO;

@Repository
public interface ItemRepository extends JpaRepository<ItemVO, Integer>{

}
