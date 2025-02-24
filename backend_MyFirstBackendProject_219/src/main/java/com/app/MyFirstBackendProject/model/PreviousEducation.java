package com.app.MyFirstBackendProject.model;


import lombok.Data;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


 
import com.app.MyFirstBackendProject.model.College;
import com.app.MyFirstBackendProject.model.Subject;
import com.app.MyFirstBackendProject.model.Department;
import com.app.MyFirstBackendProject.model.PreviousEducation;
import com.app.MyFirstBackendProject.model.Student;
import com.app.MyFirstBackendProject.model.Course;
import com.app.MyFirstBackendProject.model.Lecturer;
import com.app.MyFirstBackendProject.converter.DurationConverter;
import com.app.MyFirstBackendProject.converter.UUIDToByteConverter;
import com.app.MyFirstBackendProject.converter.UUIDToStringConverter;

import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmFunction;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.time.Duration;
import java.util.Date;
import java.sql.Timestamp;
import java.sql.Time;
import java.util.List;
import java.util.Objects;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Lob;
import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmMediaStream;

@Entity(name = "PreviousEducation")
@Table(name = "\"PreviousEducation\"", schema =  "\"myfirstbackendproject\"")
@Data
                        
public class PreviousEducation {
	public PreviousEducation () {   
  }
	  
  @Column(name = "\"EducationName\"", nullable = true )
  private String educationName;
  
	  
  @Column(name = "\"FromYear\"", nullable = true )
  @Temporal(value = TemporalType.TIMESTAMP)
  private Date fromYear;  
  
	  
  @Column(name = "\"ToYear\"", nullable = true )
  @Temporal(value = TemporalType.TIMESTAMP)
  private Date toYear;  
  
	  
  @Column(name = "\"BoardName\"", nullable = true )
  private String boardName;
  
	  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "\"EducationID\"", nullable = true )
  private Integer educationID;
  
  
  
   
  
  
  
  
  
  
  
  
  
  @Override
  public String toString() {
	return "PreviousEducation [" 
  + "EducationName= " + educationName  + ", " 
  + "FromYear= " + fromYear  + ", " 
  + "ToYear= " + toYear  + ", " 
  + "BoardName= " + boardName  + ", " 
  + "EducationID= " + educationID 
 + "]";
	}
	
}