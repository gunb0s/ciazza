package gunb0s.toy.ciazza.user.controller;

import gunb0s.toy.ciazza.user.controller.dto.CreateEducatorDto;
import gunb0s.toy.ciazza.user.service.EducatorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
}
