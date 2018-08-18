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
import org.springframework.web.util.UriComponentsBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {"spring.datasource.url=jdbc:h2:mem:SearchFamilyControllerTests"})
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@DatabaseSetup("child.xml")
public class SearchFamilyControllerTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void findAllChildren() {

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:" + port + "/searchChild");

        ResponseEntity<Child[]> responseEntity = restTemplate.getForEntity(
                builder.build().encode().toUri(),
                Child[].class);

        Assert.assertEquals(200, responseEntity.getStatusCodeValue());

        Child[] children = responseEntity.getBody();
        Assert.assertEquals(children.length, 5);
    }

	@Test
    public void findOneByAllParams() {

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:" + port + "/searchChild")
                .queryParam("firstName", "firstName3")
                .queryParam("secondName", "secondName3")
                .queryParam("sex", "MALE")
                .queryParam("pesel", "003")
                .queryParam("birthDateFrom", "2012-04-04")
                .queryParam("birthDateTo", "2012-04-04");

        ResponseEntity<Child[]> responseEntity = restTemplate.getForEntity(
                builder.build().encode().toUri(),
                Child[].class);

        Assert.assertEquals(200, responseEntity.getStatusCodeValue());

        Child[] children = responseEntity.getBody();
        Assert.assertEquals(children.length, 1);
	}

    @Test
    public void findAllBoys() {

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:" + port + "/searchChild")
                .queryParam("sex", "MALE");

        ResponseEntity<Child[]> responseEntity = restTemplate.getForEntity(
                builder.build().encode().toUri(),
                Child[].class);

        Assert.assertEquals(200, responseEntity.getStatusCodeValue());

        Child[] children = responseEntity.getBody();
        Assert.assertEquals(children.length, 3);
    }
}
