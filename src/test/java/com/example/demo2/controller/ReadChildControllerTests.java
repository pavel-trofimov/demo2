package com.example.demo2.controller;

import com.example.demo2.entity.Child;
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

import java.util.LinkedHashMap;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {"spring.datasource.url=jdbc:h2:mem:ReadChildControllerTests"})
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@DatabaseSetup("full-family.xml")
public class ReadChildControllerTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void IfChildIsNotFoundReturn404HttpStatus() {

        ResponseEntity<LinkedHashMap> responseEntity = restTemplate.getForEntity(
                "http://localhost:" + port + "/readChild/999",
                LinkedHashMap.class);

        Assert.assertEquals(404, responseEntity.getStatusCodeValue());
        Assert.assertNull(responseEntity.getBody());
    }

    @Test
    public void getChildById() {

        ResponseEntity<Child> responseEntity = restTemplate.getForEntity(
                "http://localhost:" + port + "/readChild/0",
                Child.class);

        Assert.assertEquals(200, responseEntity.getStatusCodeValue());

        Child child = responseEntity.getBody();
        Assert.assertNotNull(child);
    }
}
