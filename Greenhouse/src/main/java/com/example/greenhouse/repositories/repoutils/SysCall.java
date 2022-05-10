package com.example.greenhouse.repositories.repoutils;

import java.io.Serializable;

public class SysCall implements Serializable {

  private static final long serialVersionUID = 1L;
  private String message;
  private Boolean status;

  public SysCall(){}

  public SysCall(String message, Boolean status){
    this.message = message;
    this.status = status;
  }

  public String getMessage() {return message;}
  public Boolean getStatus() {return status;}

  public void setMessage(String message) {this.message = message;}
  public void setStatus(Boolean status) {this.status = status;}
}