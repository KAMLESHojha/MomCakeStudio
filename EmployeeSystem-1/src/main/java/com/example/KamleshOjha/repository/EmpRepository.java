package com.example.KamleshOjha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.KamleshOjha.entity.Employee;

public interface EmpRepository extends JpaRepository<Employee, Integer> {

}
