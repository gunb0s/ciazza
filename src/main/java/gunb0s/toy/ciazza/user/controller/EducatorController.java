package gunb0s.toy.ciazza.user.controller;

import gunb0s.toy.ciazza.user.controller.dto.CreateEducatorDto;
import gunb0s.toy.ciazza.user.controller.dto.GetEducatorDto;
import gunb0s.toy.ciazza.user.entity.Educator;
import gunb0s.toy.ciazza.user.service.EducatorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EducatorController {
	private final EducatorService educatorService;

	@PostMapping("/educator")
	public Long create(@RequestBody @Valid CreateEducatorDto createEducatorDto) {
		return educatorService.create(createEducatorDto);
	}

	@GetMapping("/educator")
	public Page<GetEducatorDto> getList(Pageable pageable) {
		Page<Educator> educators = educatorService.getList(pageable);
		return educators.map(GetEducatorDto::new);
	}

	@GetMapping("/educator/{id}")
	public GetEducatorDto get(@PathVariable Long id) {
		Educator educator = educatorService.get(id);
		return GetEducatorDto.builder()
				.id(educator.getId())
				.name(educator.getName())
				.build();
	}
}
