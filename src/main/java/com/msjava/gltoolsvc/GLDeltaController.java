package com.msjava.gltoolsvc;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.msjava.model.GLAdjustment;
import com.msjava.model.GLDelta;
import com.msjava.model.GeneralLedgerBalance;
import com.msjava.model.SubLedgerBalance;
import com.msjava.service.GLAdjustmentService;
import com.msjava.service.GLDeltaService;
import com.msjava.service.GeneralLedgerBalanceService;
import com.msjava.service.SubLedgerBalanceService;

@RestController
public class GLDeltaController {
//autowired the GLDeltaService class
	@Autowired
	GLDeltaService glDeltaService;

	@Autowired
	GeneralLedgerBalanceService generalLedgerBalanceService;

	@Autowired
	SubLedgerBalanceService subLedgerBalanceService;

	@Autowired
	GLAdjustmentService glAdjustmentService;
	

//creating a get mapping that retrieves all the glDeltas detail from the database 
	@GetMapping("/glDelta")
	private List<GLDelta> getAllGLDelta() {
		return glDeltaService.getAllGLDelta();
	}

//creating a get mapping that retrieves the detail of a specific glDelta
	@GetMapping("/glDelta/{id}")
	private GLDelta getGLDelta(@PathVariable("id") int id) {
		return glDeltaService.getGLDeltaById(id);
	}

//creating a delete mapping that deletes a specific glDelta
	@DeleteMapping("/glDelta/{id}")
	private void deleteGLDelta(@PathVariable("id") int id) {
		glDeltaService.delete(id);
	}

//creating post mapping that post the glDelta detail in the database
	@PostMapping("/glDelta")
	private int saveGLDelta(@RequestBody GLDelta glDelta) {
		glDeltaService.saveOrUpdate(glDelta);
		return glDelta.getId();
	}
	
	@GetMapping("/glDelta/date/{date}")
	private List<GLDelta> getAllGLDeltaByDate(@PathVariable("date") Date date) {
		return glDeltaService.getGLDeltaByDate(date);
	}

	@PostMapping("/glDelta/run/{date}")
	private void saveGLDelta(@PathVariable("date") Date date) {

		List<GeneralLedgerBalance> glbList = generalLedgerBalanceService.getGeneralLedgerBalanceByDate(date);
		List<SubLedgerBalance> slbList = subLedgerBalanceService.getSubLedgerBalanceByDate(date);
		List<GLAdjustment> glAdjustmentList = glAdjustmentService.getGLAdjustmentByDate(date);
		List<GLDelta> glDeltaList = glDeltaService.getGLDeltaByDate(date);

		Map<Integer, List<GeneralLedgerBalance>> glMap = glbList.stream()
				.collect(Collectors.groupingBy(GeneralLedgerBalance::getAcctId));
		Map<Integer, List<GLAdjustment>> adjMap = glAdjustmentList.stream()
				.collect(Collectors.groupingBy(GLAdjustment::getAcctId));
		Map<Integer, List<SubLedgerBalance>> slMap = slbList.stream()
				.collect(Collectors.groupingBy(SubLedgerBalance::getAcctId));
		
		Map<Integer, List<GLDelta>> glDeltaMap = glDeltaList.stream()
				.collect(Collectors.groupingBy(GLDelta::getAcctId));
		
		

		for (Map.Entry<Integer, List<GeneralLedgerBalance>> entry : glMap.entrySet()) {
			GLDelta delta = new GLDelta();
			int glAcctId = entry.getKey();
			GeneralLedgerBalance glBalance = entry.getValue().get(0);
			Double glBalanceAmount = glBalance.getAmount();
			Double adjAmount = 0d;
			Double slAmount = 0d;
			boolean alreadyAdded = false;

			if(!adjMap.isEmpty()) {
				if(adjMap.get(glAcctId)!=null) {
					adjAmount = adjMap.get(glAcctId).get(0).getAmount();
				}
			}
			if(!slMap.isEmpty()) {
				if(slMap.get(glAcctId).get(0)!=null) {
					slAmount = slMap.get(glAcctId).get(0).getAmount();
				}
			}
			Double glDeltaAmount = glBalanceAmount + adjAmount - slAmount;
			
			if(!glDeltaMap.isEmpty()) {
				if(glDeltaMap.get(glAcctId).get(0)!=null) {
					if(glDeltaAmount == glDeltaMap.get(glAcctId).get(0).getAmount()){
						alreadyAdded = true;
					}
				}
			}
			
			if(!alreadyAdded) {
			delta.setAcctId(glAcctId);
			delta.setDate(glMap.get(glAcctId).get(0).getDate());
			delta.setAmount(glBalanceAmount + adjAmount - slAmount);
			delta.setCurrency(glMap.get(glAcctId).get(0).getCurrency());
			System.out.println(delta);

			saveGLDelta(delta);
			}
		}

	}

}
