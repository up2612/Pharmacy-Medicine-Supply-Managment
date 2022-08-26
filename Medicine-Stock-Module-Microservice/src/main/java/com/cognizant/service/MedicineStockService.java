package com.cognizant.service;


import java.util.List;


import org.springframework.stereotype.Service;

import com.cognizant.model.MedicineStock;

@Service
public interface MedicineStockService {
	
	public List<MedicineStock> getMedicineStockInformation();
	public List<MedicineStock> getMedicineByTargetAilment(String targetAilment);
	public   MedicineStock getNumberOfTabletsInStockByName(String medicine);
	public Boolean updateNumberOfTabletsInStockByName(String medicine, int count);


}
