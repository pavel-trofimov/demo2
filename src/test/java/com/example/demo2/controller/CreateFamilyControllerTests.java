package com.example.demo2.controller;

import com.example.demo2.entity.Family;
import com.example.demo2.repository.FamilyRepository;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
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

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {"spring.datasource.url=jdbc:h2:mem:CreateFamilyControllerTests"})
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class CreateFamilyControllerTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private FamilyRepository familyRepository;

	@Test
    public void familyIsAdded() {

        ResponseEntity<Family> responseEntity = restTemplate.postForEntity(
                "http://localhost:" + port + "/createFamily",
                null,
                Family.class);

        Assert.assertEquals(200, responseEntity.getStatusCodeValue());

        Family family = responseEntity.getBody();

        Assert.assertNotNull(family);

        Integer id = family.getId();

        Assert.assertNotNull(id);
        Assert.assertNotNull(familyRepository.findOne(id));
	}
}
