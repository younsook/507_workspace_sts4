package edu.pnu.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
	 private Integer id;
	 private String pass;
	 private String name;
	 
	 @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	 private Date regidate;
	 
	
}
