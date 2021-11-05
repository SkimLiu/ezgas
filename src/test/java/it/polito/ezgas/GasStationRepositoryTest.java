package it.polito.ezgas;

import it.polito.ezgas.entity.GasStation;
import it.polito.ezgas.repository.GasStationRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class GasStationRepositoryTest {

	@Autowired
    private TestEntityManager testEntityManager;
	
    @Autowired
    private GasStationRepository gasStationRepository;
    
    @Before
    public void setUp(){
        
        GasStation g1Diesel = new GasStation();
        g1Diesel.setHasDiesel(true);
        g1Diesel.setDieselPrice(1.0);
        g1Diesel.setCarSharing("carSharing");
        
        GasStation g2Diesel = new GasStation();
        g2Diesel.setHasDiesel(true);
        g2Diesel.setDieselPrice(0.8);
        
        testEntityManager.persist(g1Diesel);
        testEntityManager.persist(g2Diesel);
        
        GasStation g1Methane = new GasStation();
        g1Methane.setHasMethane(true);
        g1Methane.setMethanePrice(1.0);
        
        GasStation g2Methane = new GasStation();
        g2Methane.setHasMethane(true);
        g2Methane.setMethanePrice(0.8);
        
        testEntityManager.persist(g1Methane);
        testEntityManager.persist(g2Methane);
        
        GasStation g1Gas = new GasStation();
        g1Gas.setHasGas(true);
        g1Gas.setGasPrice(1.0);
        
        GasStation g2Gas = new GasStation();
        g2Gas.setHasGas(true);
        g2Gas.setGasPrice(0.8);
        
        testEntityManager.persist(g1Gas);
        testEntityManager.persist(g2Gas);
        
        GasStation g1Super = new GasStation();
        g1Super.setHasSuper(true);
        g1Super.setSuperPrice(1.0);
        
        GasStation g2Super = new GasStation();
        g2Super.setHasSuper(true);
        g2Super.setSuperPrice(0.8);
        
        testEntityManager.persist(g1Super);
        testEntityManager.persist(g2Super);
        
        GasStation g1SuperPlus = new GasStation();
        g1SuperPlus.setHasSuperPlus(true);
        g1SuperPlus.setSuperPlusPrice(1.0);
        
        GasStation g2SuperPlus = new GasStation();
        g2SuperPlus.setHasSuperPlus(true);
        g2SuperPlus.setSuperPlusPrice(0.8);
        
        testEntityManager.persist(g1SuperPlus);
        testEntityManager.persist(g2SuperPlus);
    }

    @Test
    public void testFindByHasDieselTrueOrderByDieselPrice() {

        List<GasStation> list = gasStationRepository.findByHasDieselTrueOrderByDieselPrice();
        list.forEach(gasStation -> {
            assertTrue(gasStation.getHasDiesel());
        });
        
        assert(list.get(0).getDieselPrice() <= list.get(1).getDieselPrice());
    }

    @Test
    public void testFindByHasGasTrueOrderByGasPrice() {
        List<GasStation> list = gasStationRepository.findByHasGasTrueOrderByGasPrice();
        list.forEach(gasStation -> {
            assertTrue(gasStation.getHasGas());
        });
        
        assert(list.get(0).getGasPrice() <= list.get(1).getGasPrice());
    }

    
    @Test
    public void testFindByHasMethaneTrueOrderByMethanePrice() {
        List<GasStation> list = gasStationRepository.findByHasMethaneTrueOrderByMethanePrice();
        list.forEach(gasStation -> {
            assertTrue(gasStation.getHasMethane());
        });
        
        assert(list.get(0).getMethanePrice() <= list.get(1).getMethanePrice());
    }

    @Test
    public void testFindByHasSuperTrueOrderBySuperPrice() {
        List<GasStation> list = gasStationRepository.findByHasSuperTrueOrderBySuperPrice();
        list.forEach(gasStation -> {
            assertTrue(gasStation.getHasSuper());
        });
        
        assert(list.get(0).getSuperPrice() <= list.get(1).getSuperPrice());
    }

    @Test
    public void testFindByHasSuperPlusTrueOrderBySuperPlusPrice() {
        List<GasStation> list = gasStationRepository.findByHasSuperPlusTrueOrderBySuperPlusPrice();
        list.forEach(gasStation -> {
            assertTrue(gasStation.getHasSuperPlus());
        });
        
        assert(list.get(0).getSuperPlusPrice() <= list.get(1).getSuperPlusPrice());
    }

    @Test
    public void testFindByCarSharingOrderByCarSharing() {
        List<GasStation> list = gasStationRepository.findByCarSharingOrderByCarSharing("carSharing");
        list.forEach(gasStation -> {
            assertEquals("carSharing",gasStation.getCarSharing());
        });
        
        assertEquals(list.size(), 1);
    }
  
}