package com.cognizant.pharmacysupply.model;



import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class MedicineDemandTest {

	@Mock
	MedicineDemand demand;
	
//	@Autowired
//	MedicineDemand demandtest;
	
	@Before
	public void setup(){
		
		demand = new MedicineDemand();
		demand.setId(1);
		demand.setMedicinename("Crocin");
		demand.setDemandcount(10);
		demand.setTranDate("26-06-2021 11:30");
	}
	
	@Test
	public void testSetter() {
		assertThat(demand.getId().equals(1));
		assertThat(demand.getMedicinename().equals("Crocin"));
		assertThat(demand.getDemandcount().equals(10));
		assertThat(demand.getTranDate().equals("26-06-2021 11:30"));
	}
	
	@Test
	public void testGetter() {
		assertEquals(1,demand.getId());
		assertEquals("Crocin", demand.getMedicinename());
		assertEquals(10,demand.getDemandcount());
		assertEquals("26-06-2021 11:30",demand.getTranDate());
		
	}
	
	@Test
	public void testNoArgsConstructor() {
		MedicineDemand demand = new MedicineDemand();
		assertEquals(null, demand.getMedicinename());
	}
	
	@Test
	public void testAllArgsConstructor() {
		MedicineDemand medicineDemand = new MedicineDemand(1,"Dolo-650",500,"26-06-2021 11:30");
		MedicineDemand demandtest=new MedicineDemand(1,"Dolo-650",500,"26-06-2021 11:30");
		//assertThat(ReflectionTestUtils.setField(demandtest, 1,"dolo-650",500,"26-06-2021 11:30"));
		//medicineDemand demandtest;
		//Assert.assertEquals(demandtest,medicineDemand);
		assertEquals(1,medicineDemand.getId());
		assertEquals( "Dolo-650",medicineDemand.getMedicinename());
		assertEquals(500,medicineDemand.getDemandcount());
		assertEquals("26-06-2021 11:30",medicineDemand.getTranDate());
	}
}
