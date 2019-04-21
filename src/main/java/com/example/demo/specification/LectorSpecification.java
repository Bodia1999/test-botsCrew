package com.example.demo.specification;

import com.example.demo.dto.request.LectorFilterRequest;
import com.example.demo.entity.Lector;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class LectorSpecification implements Specification<Lector> {

    private LectorFilterRequest lectorFilterRequest;

    public LectorSpecification(LectorFilterRequest lectorFilterRequest) {
        this.lectorFilterRequest = lectorFilterRequest;
    }

    @Override
    public Predicate toPredicate(Root<Lector> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        Predicate allByName = findAllByName(root, criteriaBuilder);
        if (allByName != null) predicates.add(allByName);
        Predicate allBySurname = findAllBySurname(root, criteriaBuilder);
        if (allBySurname != null) predicates.add(allBySurname);

        return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
    }


    private Predicate findAllByName(Root<Lector> root, CriteriaBuilder criteriaBuilder) {
        if (lectorFilterRequest.getName() == null || lectorFilterRequest.getName().trim().isEmpty()) {
            return null;
        }

        return criteriaBuilder.like(root.get("name"), '%' + lectorFilterRequest.getName() + '%');
    }

    private Predicate findAllBySurname(Root<Lector> root, CriteriaBuilder criteriaBuilder) {
        if (lectorFilterRequest.getName() == null || lectorFilterRequest.getName().trim().isEmpty()) {
            return null;
        }

        return criteriaBuilder.like(root.get("surname"), '%' + lectorFilterRequest.getName() + '%');
    }
}
