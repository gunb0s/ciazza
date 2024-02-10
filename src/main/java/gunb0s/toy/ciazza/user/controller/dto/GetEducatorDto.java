package gunb0s.toy.ciazza.user.controller.dto;

import gunb0s.toy.ciazza.user.entity.Educator;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GetEducatorDto {
	private Long id;
	private String name;

	@Builder
	public GetEducatorDto(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public GetEducatorDto(Educator educator) {
		this.id = educator.getId();
		this.name = educator.getName();
	}
}
