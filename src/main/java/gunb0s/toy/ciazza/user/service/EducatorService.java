package gunb0s.toy.ciazza.user.service;

import gunb0s.toy.ciazza.user.controller.dto.CreateEducatorDto;
import gunb0s.toy.ciazza.user.entity.Educator;
import gunb0s.toy.ciazza.user.repository.EducatorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EducatorService {
	private final EducatorRepository educatorRepository;

	@Transactional
	public Long create(CreateEducatorDto createEducatorDto) {
		Educator educator = Educator.builder()
				.name(createEducatorDto.getName())
				.build();
		educatorRepository.save(educator);
		return educator.getId();
	}
}
