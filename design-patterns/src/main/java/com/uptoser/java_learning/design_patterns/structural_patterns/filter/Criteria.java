package com.uptoser.java_learning.design_patterns.structural_patterns.filter;

import java.util.List;

public interface Criteria {
    public List<Person> meetCriteria(List<Person> persons);
}
