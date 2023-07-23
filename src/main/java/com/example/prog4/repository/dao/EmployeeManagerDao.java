package com.example.prog4.repository.dao;

import com.example.prog4.model.exception.InternalServerErrorException;
import com.example.prog4.repository.entity.Employee;
import com.example.prog4.repository.entity.enums.Sex;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class EmployeeManagerDao {
    private EntityManager entityManager;

    public List<Employee> findByCriteria(String lastName, String firstName, Sex sex, String position, Pageable pageable) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> query = builder.createQuery(Employee.class);
        Root<Employee> root = query.from(Employee.class);

        List<Predicate> predicates = new ArrayList<>();

        predicates.add(builder.or(
                builder.like(builder.lower(root.get("lastName")), "%" + lastName.toLowerCase() + "%")
        ));

        predicates.add(builder.or(
                builder.like(builder.lower(root.get("firstName")), "%" + firstName.toLowerCase() + "%")
        ));

        if(sex != null){
            predicates.add(builder.or(
                    builder.equal(root.get("sex"), sex)
            ));
        }

        predicates.add(builder.or(
                builder.like(builder.lower(root.get("positions").get("name")), "%" + position.toLowerCase() + "%")
        ));

        query.where(predicates.toArray(Predicate[]::new))
                .orderBy(QueryUtils.toOrders(pageable.getSort(), root, builder));

        try {
            return entityManager.createQuery(query)
                    .setFirstResult((pageable.getPageNumber()) * pageable.getPageSize())
                    .setMaxResults(pageable.getPageSize())
                    .getResultList();
        } catch (Exception e) {
            System.out.println(e);
            throw new InternalServerErrorException(e.getMessage());
        }
    }
}
