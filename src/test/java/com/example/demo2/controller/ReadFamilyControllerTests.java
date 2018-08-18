package com.example.demo2.controller;

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
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {"spring.datasource.url=jdbc:h2:mem:ReadFamilyControllerTests"})
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@DatabaseSetup("full-family.xml")
public class ReadFamilyControllerTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void IfFamilyIsNotFoundReturn404HttpStatus() {

        ResponseEntity<LinkedHashMap> responseEntity = restTemplate.getForEntity(
                "http://localhost:" + port + "/readFamily/999",
                LinkedHashMap.class);

        Assert.assertEquals(404, responseEntity.getStatusCodeValue());
        Assert.assertNull(responseEntity.getBody());
    }

    @Test
    public void getFullFamilyByFamilyId() {

        ResponseEntity<LinkedHashMap> responseEntity = restTemplate.getForEntity(
                "http://localhost:" + port + "/readFamily/0",
                LinkedHashMap.class);

        Assert.assertEquals(200, responseEntity.getStatusCodeValue());

        LinkedHashMap family = responseEntity.getBody();

        Assert.assertNotNull(family);
        Assert.assertNotNull(family.get("id"));

        checkChildren(family);
        checkFather(family);
    }

	private void checkChildren(LinkedHashMap family) {

        List responseChildren = ((List) family.get("children"));
        Assert.assertNotNull(responseChildren);
        Assert.assertEquals(2, responseChildren.size());

        LinkedHashMap responseChild = ((LinkedHashMap) responseChildren.get(0));
        Assert.assertNotNull(responseChild);
        Assert.assertNotNull(responseChild.get("id"));
    }

    private void checkFather(LinkedHashMap family) {
        LinkedHashMap responseFather = ((LinkedHashMap) family.get("father"));
        Assert.assertNotNull(responseFather);
        Assert.assertNotNull(responseFather.get("id"));
    }
}
