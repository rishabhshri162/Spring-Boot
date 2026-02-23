package com.rays.ctl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.common.ORSResponse;
import com.rays.dto.StudentDTO;
import com.rays.form.StudentForm;
import com.rays.service.StudentService;

@RestController
@RequestMapping(value = "Student")
public class StudentCtl extends BaseCtl {

	@Autowired
	StudentService studentService;

	@PostMapping("save")
	public ORSResponse save(@RequestBody @Valid StudentForm form, BindingResult bindingResult) {

		ORSResponse res = new ORSResponse();

		res = validate(bindingResult);
		if (res.isSuccess() == false) {
			return res;

		}
		StudentDTO dto = (StudentDTO) form.getDto();

		studentService.add(dto);
		res.setSuccess(true);
		res.addMessage("Student Added Successfully");
		res.addData(dto);
		return res;

	}

	@PostMapping("update")
	public ORSResponse update(@RequestBody @Valid StudentForm form, BindingResult bindingResult) {

		ORSResponse res = new ORSResponse();

		res = validate(bindingResult);
		if (res.isSuccess() == false) {
			return res;

		}
		StudentDTO dto = (StudentDTO) form.getDto();

		studentService.update(dto);
		res.setSuccess(true);
		res.addMessage("Student updated successfull");
		res.addData(dto);

		return res;

	}

	@PostMapping("delete/{ids}")
	public ORSResponse delete(@PathVariable long[] ids) {

		ORSResponse res = new ORSResponse();

		if (ids != null && ids.length > 0) {
			for (long id : ids) {
				studentService.delete(id);
				res.setSuccess(true);
				res.addMessage("student deleted successfully");

			}
		} else {

			res.addMessage("select at least one record");

		}
		return res;

	}

	@GetMapping("get/{id})")
	public ORSResponse get(@PathVariable long id) {

		ORSResponse res = new ORSResponse();

		StudentDTO dto = studentService.findByPk(id);

		if (dto != null) {
			res.addData(dto);
			res.setSuccess(true);

		}
		return res;

	}

	@GetMapping("/search/{pageNo}")
	public ORSResponse search(@RequestBody StudentForm form, @PathVariable int pageNo) {

		ORSResponse res = new ORSResponse();
		int pageSize = 5;

		StudentDTO dto = (StudentDTO) form.getDto();
		List<StudentDTO> list = studentService.search(dto, pageNo, pageSize);
		
		if (list.size() > 0) {
			res.addData(list);
			res.setSuccess(true);
			
		}
		return res;
		

	}

}
