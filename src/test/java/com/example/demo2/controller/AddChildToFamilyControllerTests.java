package com.example.demo2.controller;

import com.example.demo2.entity.Child;
import com.example.demo2.entity.Family;
import com.example.demo2.repository.ChildRepository;
import com.example.demo2.repository.FamilyRepository;
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
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {"spring.datasource.url=jdbc:h2:mem:AddChildToFamilyControllerTests"})
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@DatabaseSetup("family.xml")
public class AddChildToFamilyControllerTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private FamilyRepository familyRepository;

    @Autowired
    private ChildRepository childRepository;

	@Test
    public void childIsAdded() {

        Family family = familyRepository.findOne(0);
        Child child = createChild(family);

        ResponseEntity<LinkedHashMap> responseEntity = restTemplate.postForEntity(
                "http://localhost:" + port + "/addChildToFamily",
                child,
                LinkedHashMap.class);

        Assert.assertEquals(200, responseEntity.getStatusCodeValue());

        LinkedHashMap resultFamily = responseEntity.getBody();

        Assert.assertNotNull(resultFamily);

        List responseChildren = ((List) resultFamily.get("children"));
        Assert.assertNotNull(responseChildren);

        LinkedHashMap responseChild = ((LinkedHashMap) responseChildren.get(0));
        Assert.assertNotNull(responseChild);

        Object childId = responseChild.get("id");
        Assert.assertNotNull(childId);

        Child persistentChild = childRepository.findOne(Integer.parseInt(childId.toString()));
        Assert.assertNotNull(persistentChild);
	}

    private Child createChild(Family family) {

        Child child = new Child();
        child.setFamily(family);
        child.setSex(Child.Sex.MALE);
        child.setFirstName("FirstName");
        child.setSecondName("SecondName");
        child.setBirthDate(LocalDate.now());
        child.setPesel("123456789");
        return child;
    }
}
