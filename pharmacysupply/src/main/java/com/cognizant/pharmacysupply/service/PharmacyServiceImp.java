package com.cognizant.pharmacysupply.service;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.cognizant.pharmacysupply.PharmacysupplyApplication;
import com.cognizant.pharmacysupply.exception.MedicineNotFoundException;
import com.cognizant.pharmacysupply.feignclient.MedicineStockFeignClient;
import com.cognizant.pharmacysupply.model.MedicineDemand;
import com.cognizant.pharmacysupply.model.MedicineStock;
import com.cognizant.pharmacysupply.model.PharmacyMedicineSupply;

import com.cognizant.pharmacysupply.repository.MedicineDemandRepository;
import com.cognizant.pharmacysupply.repository.PharmacyMedicineSupplyRepository;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PharmacyServiceImp implements PharmacyService {

	
	@Autowired
    private PharmacyMedicineSupplyRepository pharmacyMedicineSupplyRepository;

	@Autowired
	private MedicineDemandRepository medicineDemandRepository;
	@Autowired
	MedicineStockFeignClient stockFeignClient;
	
		@Override
		public List<PharmacyMedicineSupply> getPharmacySupplyCount(List<MedicineDemand> medicineDemandList,String token)
				throws MedicineNotFoundException 
		{		
			log.info("inside get pharmacy supply count method");
			List<PharmacyMedicineSupply> pharamacyMedicineSupplyList = new ArrayList<>();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			   LocalDateTime now = LocalDateTime.now();  
			   String tranDate=dtf.format(now);
			for (MedicineDemand medicineDemand : medicineDemandList) {
				PharmacyMedicineSupply pharmacyMedicineSupply = new PharmacyMedicineSupply();
				MedicineStock medicineStock = getNumberOfTablets(medicineDemand,token);				
				setSupply(pharmacyMedicineSupply, medicineDemand, medicineStock,token);
				log.info("supply is set");
				pharmacyMedicineSupply.setTranDate(tranDate);
				medicineDemand.setTranDate(tranDate);
				pharamacyMedicineSupplyList.add(pharmacyMedicineSupply);
				log.info("pharamacy medicine supply list added");

			}
			
			pharmacyMedicineSupplyRepository.saveAll(pharamacyMedicineSupplyList);
			log.info("pharmacy repository is saved");
			medicineDemandRepository.saveAll(medicineDemandList);
			log.info("medicineDemandRepos repository is saved");

			
			log.info("returning pharmacy medicine supply list");
			return pharamacyMedicineSupplyList;
		}

		public boolean setSupply(PharmacyMedicineSupply pharmacyMedicineSupply, MedicineDemand medicineDemand,
				MedicineStock medicineStock,String token) throws MedicineNotFoundException {
			int val = 0;
			
			log.info("inside set supply method");
			if (medicineStock.getNumberOfTabletsInStock() < medicineDemand.getDemandcount()) {
				
				pharmacyMedicineSupply.setSupplycount(medicineStock.getNumberOfTabletsInStock());
				

			} else {
				pharmacyMedicineSupply.setSupplycount(medicineDemand.getDemandcount());//50
				val = medicineStock.getNumberOfTabletsInStock() - medicineDemand.getDemandcount();//100-50

			}
			
			try {
			stockFeignClient.updateNumberOfTabletsInStockByName( medicineDemand.getMedicinename(), val,token);
			} catch (FeignException ex) {
				throw new MedicineNotFoundException("Medicine not found");
			}
			log.info("set medicine name");
			pharmacyMedicineSupply.setMedicinename(medicineDemand.getMedicinename());
			log.info("set pharmacy name");
			pharmacyMedicineSupply.setPharmacyname(medicineStock.getPharmacyName());
			log.info("out of set supply method");
			return true;
			
		}

		public MedicineStock getNumberOfTablets(MedicineDemand medicineDemand,String token) throws MedicineNotFoundException{
			
			log.info("inside get number of tablets method");
			
			MedicineStock medicineStock=null;
			try {
			 medicineStock= stockFeignClient.getNumberOfTabletsInStock( medicineDemand.getMedicinename(),token);
			} catch (FeignException ex) {
				throw new MedicineNotFoundException("Medicine not found");
			}

			if (medicineStock==null) {
				throw new MedicineNotFoundException("Medicine not found");
			}
			
			log.info("returning medicine stock");
			return medicineStock;
		}
		

		

		@Override
		public List<MedicineDemand> getMedicineDemand() {
			
			log.info("inside get medicine demand that will return all medicine demand");
			List<MedicineDemand> medicineDemandList=new ArrayList<MedicineDemand>();
			for(MedicineDemand med:medicineDemandRepository.findAll() )
			{
				if(med.getDemandcount()!=null) medicineDemandList.add(med);
			}
			
			log.info("returning demand list");
			return medicineDemandList;

		}

		@Override
		public List<PharmacyMedicineSupply> getMedicineSupply() {
			
			log.info("inside get medicine supply that will return all pharmacy medicine supply");
			List<PharmacyMedicineSupply> pharmacyMedicineSupplies=new ArrayList<PharmacyMedicineSupply>();
			for(PharmacyMedicineSupply med:pharmacyMedicineSupplyRepository.findAll() )
			{
				if(med.getSupplycount()!=null) pharmacyMedicineSupplies.add(med);
			}
			log.info("Returning Pharamcy medicine supplies");
			return pharmacyMedicineSupplies;

		}		
		
}
