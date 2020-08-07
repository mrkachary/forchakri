package com.xebia.coding.assignment.articlemanagement.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class TimeToRead {
	private int mins;
	private int seconds;
}
