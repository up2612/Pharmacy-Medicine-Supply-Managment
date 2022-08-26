package com.cognizant.repository;

import java.util.List;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognizant.model.MedicineStock;

@Repository
public interface MedicineStockRepository extends JpaRepository<MedicineStock, Integer> {
	
	
	@Query(value="select m from MedicineStock m where m.targetAliment = ?1")
	List<MedicineStock> getMedicineByTAilment(String taliment);
	
	@Query(value = "SELECT m FROM MedicineStock m where m.name = ?1")
	 MedicineStock getNumberOfTabletsInStockByName(String medicine);
	
	
}
