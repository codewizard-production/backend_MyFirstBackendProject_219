package com.app.MyFirstBackendProject.function;

import com.app.MyFirstBackendProject.model.College;
import com.app.MyFirstBackendProject.model.Subject;
import com.app.MyFirstBackendProject.model.Department;
import com.app.MyFirstBackendProject.model.PreviousEducation;
import com.app.MyFirstBackendProject.model.Student;
import com.app.MyFirstBackendProject.model.Course;
import com.app.MyFirstBackendProject.model.Lecturer;
import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmFunction;
import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmParameter;
import com.sap.olingo.jpa.metadata.core.edm.mapper.extension.ODataFunction;
import com.app.MyFirstBackendProject.repository.DepartmentRepository;
import com.app.MyFirstBackendProject.repository.CollegeRepository;
import com.app.MyFirstBackendProject.repository.PreviousEducationRepository;
import com.app.MyFirstBackendProject.repository.StudentRepository;
import com.app.MyFirstBackendProject.repository.CourseRepository;
import com.app.MyFirstBackendProject.repository.LecturerRepository;
import com.app.MyFirstBackendProject.repository.SubjectRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Component
public class JavaFunctions implements ODataFunction {


    
    
}
   
