package com.example.demo2.repository;

import com.example.demo2.entity.Child;
import com.example.demo2.entity.Family;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ChildRepository extends JpaRepository<Child, Integer> {

    @Query( " SELECT c FROM Child AS c " +
            " WHERE " +
            " ( :firstName is null OR UPPER(c.firstName) LIKE CONCAT('%', UPPER(:firstName), '%') ) AND " +
            " ( :secondName is null OR UPPER(c.secondName) LIKE CONCAT('%', UPPER(:secondName), '%') ) AND  " +
            " ( :pesel is null OR c.pesel = :pesel ) AND  " +

            " ( " +
            "   ( :birthDateFrom is null " +
            "           OR c.birthDate >= :birthDateFrom ) AND " +
            "   ( :birthDateTo is null " +
            "           OR c.birthDate <= :birthDateTo ) " +
            " ) AND " +

            " ( :sex is null OR c.sex = :sex ) "
    )
    List<Child> filter(
                @Param("firstName") String firstName,
                @Param("secondName") String secondName,
                @Param("sex") Child.Sex sex,
                @Param("pesel") String pesel,
                @Param("birthDateFrom") LocalDate birthDateFrom,
                @Param("birthDateTo") LocalDate birthDateTo
            );

    List<Child> findByFamily(Family family);
}
