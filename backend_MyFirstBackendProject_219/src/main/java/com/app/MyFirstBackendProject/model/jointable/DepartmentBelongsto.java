package com.app.MyFirstBackendProject.model.jointable;

import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmIgnore;
import lombok.Data;
import javax.persistence.*;

import com.app.MyFirstBackendProject.model.College;
import com.app.MyFirstBackendProject.model.Subject;
import com.app.MyFirstBackendProject.model.Department;
import com.app.MyFirstBackendProject.model.PreviousEducation;
import com.app.MyFirstBackendProject.model.Student;
import com.app.MyFirstBackendProject.model.Course;
import com.app.MyFirstBackendProject.model.Lecturer;

@Entity(name = "DepartmentBelongsto")
@Table(schema = "\"myfirstbackendproject\"", name = "\"DepartmentBelongsto\"")
@Data
public class DepartmentBelongsto{

 	@Id
    @Column(name = "\"Id\"")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@Column(name = "\"DeptID\"")
	private String deptID;

    
    @Column(name = "\"StaffID\"")
    private String staffID;
 
}