package com.msjava.gltoolsvc;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.msjava.model.GLAdjustment;
import com.msjava.model.GeneralLedgerBalance;
import com.msjava.service.GLAdjustmentService;


@RestController
public class GLAdjustmentController {
//autowired the GLAdjustmentService class
	@Autowired
	GLAdjustmentService glAdjustmentService;

//creating a get mapping that retrieves all the glAdjustments detail from the database 
	@GetMapping("/glAdjustment")
	private List<GLAdjustment> getAllGLAdjustment() {
		return glAdjustmentService.getAllGLAdjustment();
	}

//creating a get mapping that retrieves the detail of a specific glAdjustment
	@GetMapping("/glAdjustment/{id}")
	private GLAdjustment getGLAdjustment(@PathVariable("id") int id) {
		return glAdjustmentService.getGLAdjustmentById(id);
	}

//creating a delete mapping that deletes a specific glAdjustment
	@DeleteMapping("/glAdjustment/{id}")
	private void deleteGLAdjustment(@PathVariable("id") int id) {
		glAdjustmentService.delete(id);
	}

//creating post mapping that post the glAdjustment detail in the database
	@PostMapping("/glAdjustment")
	private int saveGLAdjustment(@RequestBody GLAdjustment glAdjustment) {
		glAdjustmentService.saveOrUpdate(glAdjustment);
		return glAdjustment.getId();
	}
	
	@GetMapping("/glAdjustment/date/{date}")
	private List<GLAdjustment> getAllGLAdjustmentByDate(@PathVariable("date") Date date) {
		return glAdjustmentService.getGLAdjustmentByDate(date);
	}
}
