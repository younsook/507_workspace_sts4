package edu.pnu.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data
@Getter
@Setter
@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
public class MemberDTO {
	 private Integer id;
	 private String pass;
	 private String name;
	 private Date regidate;
	 
	 public MemberDTO() {}
	
}
