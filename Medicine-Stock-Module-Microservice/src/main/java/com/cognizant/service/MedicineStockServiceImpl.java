package com.cognizant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.model.MedicineStock;
import com.cognizant.repository.MedicineStockRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MedicineStockServiceImpl implements MedicineStockService {

	@Autowired
	MedicineStockRepository medicinestockrepo;

	@Override
	public List<MedicineStock> getMedicineStockInformation() {

		List<MedicineStock> medicineStockList = medicinestockrepo.findAll();
		if (!medicineStockList.isEmpty())
		log.info("Successfully retrieved all the Medicine Stock Information");
		return medicineStockList;
	}

	
	@Override
	public List<MedicineStock> getMedicineByTargetAilment(String targetAliment) {

		List<MedicineStock> medicineslist = medicinestockrepo.getMedicineByTAilment(targetAliment);
		if (medicineslist.isEmpty())
		log.error("No medicines are available for this target Aliment");
		return medicineslist;

	}
	

	@Override
	public MedicineStock getNumberOfTabletsInStockByName(String medicine) {
     MedicineStock stock=	medicinestockrepo.getNumberOfTabletsInStockByName(medicine);
		return stock;
		}
	

	@Override
	public Boolean updateNumberOfTabletsInStockByName(String medicine, int count) {

		MedicineStock mstock = medicinestockrepo.getNumberOfTabletsInStockByName(medicine);
		mstock.setNumberOfTabletsInStock(count);
		mstock = medicinestockrepo.save(mstock);
		Boolean result=false;
		if (mstock.getNumberOfTabletsInStock() == count) {
			log.info(" Number of rows updated their Stock Value based on medicine.");
			return true;
		} 
		return result;

	}

}
