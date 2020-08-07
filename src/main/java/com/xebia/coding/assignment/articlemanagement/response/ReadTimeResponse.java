package com.xebia.coding.assignment.articlemanagement.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ReadTimeResponse {
		String articleId;
		TimeToRead timeToRead;

}
