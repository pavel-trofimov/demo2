package com.example.demo2.controller;

import com.example.demo2.entity.Family;
import com.example.demo2.entity.Father;
import com.example.demo2.repository.FamilyRepository;
import com.example.demo2.repository.FatherRepository;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.time.LocalDate;
import java.util.LinkedHashMap;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {"spring.datasource.url=jdbc:h2:mem:AddFatherToFamilyControllerTests"})
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@DatabaseSetup("family.xml")
public class AddFatherToFamilyControllerTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private FamilyRepository familyRepository;

    @Autowired
    private FatherRepository fatherRepository;

	@Test
    public void fatherIsAdded() {

        Family family = familyRepository.findOne(0);
        Father father = createFather(family);

        ResponseEntity<LinkedHashMap> responseEntity = restTemplate.postForEntity(
                "http://localhost:" + port + "/addFatherToFamily",
                father,
                LinkedHashMap.class);

        Assert.assertEquals(200, responseEntity.getStatusCodeValue());

        LinkedHashMap resultFamily = responseEntity.getBody();
        Assert.assertNotNull(resultFamily);

        LinkedHashMap responseFather = ((LinkedHashMap) resultFamily.get("father"));
        Assert.assertNotNull(responseFather);

        Object fatherId = responseFather.get("id");
        Assert.assertNotNull(fatherId);

        Father persistentFather = fatherRepository.findOne(Integer.parseInt(fatherId.toString()));
        Assert.assertNotNull(persistentFather);

        fatherRepository.deleteAll();
	}

	@Test
    public void ifFamilyAlreadyHasFatherReturn422HttpStatus() {
        Family family = familyRepository.findOne(0);
        Father father = createFather(family);

        ResponseEntity<LinkedHashMap> responseEntity = restTemplate.postForEntity(
                "http://localhost:" + port + "/addFatherToFamily",
                father,
                LinkedHashMap.class);

        Assert.assertEquals(200, responseEntity.getStatusCodeValue());

        responseEntity = restTemplate.postForEntity(
                "http://localhost:" + port + "/addFatherToFamily",
                father,
                LinkedHashMap.class);

        Assert.assertEquals(422, responseEntity.getStatusCodeValue());

        fatherRepository.deleteAll();
    }

    private Father createFather(Family family) {

        Father father = new Father();
        father.setFamily(family);
        father.setBirthDate(LocalDate.now());
        father.setFirstName("FirstName");
        father.setSecondName("SecondName");
        father.setPesel("123456789");
        return father;
    }
}
