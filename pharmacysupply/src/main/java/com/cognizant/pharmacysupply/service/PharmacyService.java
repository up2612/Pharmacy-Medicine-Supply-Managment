package com.cognizant.pharmacysupply.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cognizant.pharmacysupply.exception.MedicineNotFoundException;
import com.cognizant.pharmacysupply.model.MedicineDemand;
import com.cognizant.pharmacysupply.model.PharmacyMedicineSupply;

@Service
public interface PharmacyService {
	public List<PharmacyMedicineSupply> getPharmacySupplyCount(List<MedicineDemand> medicineDemandList,String token) throws MedicineNotFoundException;
	public List<MedicineDemand> getMedicineDemand();
	public List<PharmacyMedicineSupply> getMedicineSupply();
	//public List<PharmacyMedicineSupply> getPharmacySupplyCountLessDemandCount(List<MedicineDemand> medicineDemandList,String token);
}
