package cz.uhk.kppro2025.service;

import cz.uhk.kppro2025.model.Result;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface ResultService {

    List<Result> getAllResults();
    void saveResult(Result result);
    Result getResultById(Long id);
    void deleteResultById(Long id);
}
