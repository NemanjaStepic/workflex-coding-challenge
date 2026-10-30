package com.workflex.workation.model;
import jakarta.persistence.*;
import java.time.LocalDate;
@Entity @Table(name="workations")
public class Workation {
 @Id @Column(nullable=false) private String workationId;
 @Column(nullable=false) private String employee;
 @Column(nullable=false) private String origin;
 @Column(nullable=false) private String destination;
 @Column(nullable=false) private LocalDate startDate;
 @Column(nullable=false) private LocalDate endDate;
 @Column(nullable=false) private Integer workingDays;
 @Column(nullable=false) private String risk;
 protected Workation() {}
 public Workation(String workationId,String employee,String origin,String destination,LocalDate startDate,LocalDate endDate,Integer workingDays,String risk){
 this.workationId=workationId;this.employee=employee;this.origin=origin;this.destination=destination;this.startDate=startDate;this.endDate=endDate;this.workingDays=workingDays;this.risk=risk;
 }
 public String getWorkationId(){return workationId;} public String getEmployee(){return employee;}
 public String getOrigin(){return origin;} public String getDestination(){return destination;}
 public LocalDate getStart(){return startDate;} public LocalDate getEnd(){return endDate;}
 public Integer getWorkingDays(){return workingDays;} public String getRisk(){return risk;}
}
