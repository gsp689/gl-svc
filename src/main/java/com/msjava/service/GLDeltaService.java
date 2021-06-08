package com.msjava.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msjava.model.GLDelta;
import com.msjava.model.GLDelta;
import com.msjava.repository.GLDeltaRepository;

//defining the business logic
@Service
public class GLDeltaService {
	
@Autowired
GLDeltaRepository glDeltaRepository;

//getting all glDelta records
	public List<GLDelta> getAllGLDelta() {
		List<GLDelta> glDeltas = new ArrayList<GLDelta>();
		glDeltaRepository.findAll().forEach(glDelta -> glDeltas.add(glDelta));
		return glDeltas;
	}

//getting a specific record
	public GLDelta getGLDeltaById(int id) {
		return glDeltaRepository.findById(id).get();
	}

	public void saveOrUpdate(GLDelta glDelta) {
		glDeltaRepository.save(glDelta);
	}

//deleting a specific record
	public void delete(int id) {
		glDeltaRepository.deleteById(id);
	}
	
	public List<GLDelta> getGLDeltaByDate(Date date) {
		List<GLDelta>  l = getAllGLDelta();
		List<GLDelta> newList = l.stream().filter(glDelta -> glDelta.getDate().compareTo(date)==0).collect(Collectors.toList());
		return newList;
	}
}