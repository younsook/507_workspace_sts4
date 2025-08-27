package com.rubypaper.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Board
 *
 */
@Entity
@Table(name="BOARD")
//@Table(name="E_BOARD", uniqueConstraints = {@UniqueConstraint(columnNames = {"SEQ", "WRITER"})})
public class Board implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private static final long serialVersionUID = 1L;

	private Long seq;
	@Column(nullable = false)
	private String itile;
	@Column(nullable = false, length = 40)
	private String writer;
	private String content;
	private Date createDate;
	private Long cnt;

	public Long getSeq() {
		return seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	public String getItile() {
		return itile;
	}

	public void setItile(String itile) {
		this.itile = itile;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Long getCnt() {
		return cnt;
	}

	public void setCnt(Long cnt) {
		this.cnt = cnt;
	}

//	public static long getSerialversionuid() {
//		return serialVersionUID;
//	}

	public Board() {
		
		super();
	}
   
	
	@Override
	public String toString() {
		return "Board [seq=" + seq + ", itile=" + itile + ", writer=" + writer + ", content=" + content + ", createDate=" + createDate + ", cnt=" + cnt
				+ "]";
	}

}
