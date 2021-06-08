package com.msjava.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msjava.model.GLAdjustment;
import com.msjava.model.GeneralLedgerBalance;
import com.msjava.repository.GLAdjustmentRepository;

//defining the business logic
@Service
public class GLAdjustmentService {
	
@Autowired
GLAdjustmentRepository glAdjustmentRepository;

//getting all glAdjustment records
	public List<GLAdjustment> getAllGLAdjustment() {
		List<GLAdjustment> glAdjustments = new ArrayList<GLAdjustment>();
		glAdjustmentRepository.findAll().forEach(glAdjustment -> glAdjustments.add(glAdjustment));
		return glAdjustments;
	}

//getting a specific record
	public GLAdjustment getGLAdjustmentById(int id) {
		return glAdjustmentRepository.findById(id).get();
	}

	public void saveOrUpdate(GLAdjustment glAdjustment) {
		glAdjustmentRepository.save(glAdjustment);
	}

//deleting a specific record
	public void delete(int id) {
		glAdjustmentRepository.deleteById(id);
	}
	
	public List<GLAdjustment> getGLAdjustmentByDate(Date date) {
		List<GLAdjustment>  l = getAllGLAdjustment();
		List<GLAdjustment> newList = l.stream().filter(glAdjustment -> glAdjustment.getDate().compareTo(date)==0).collect(Collectors.toList());
		return newList;
	}
}