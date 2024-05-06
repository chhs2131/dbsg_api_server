package kr.co.dbsg.api.api.underwriter.service;

import java.util.List;
import kr.co.dbsg.api.api.event.entity.UnderwriterTypeEntity;
import kr.co.dbsg.api.api.underwriter.repository.UnderwriterTypeEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UnderwriterService {
    private final UnderwriterTypeEntityRepository underwriterRepository;

    public List<UnderwriterTypeEntity> getAll() {
        return underwriterRepository.findAll();
    }
}
