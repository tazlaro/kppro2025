package cz.uhk.kppro2025.service;

import cz.uhk.kppro2025.model.Result;
import cz.uhk.kppro2025.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultServiceImpl implements ResultService {

    private final ResultRepository resultRepository;

    @Autowired
    public ResultServiceImpl(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    @Override
    public List<Result> getAllResults() {
        return resultRepository.findAll();
    }

    @Override
    public void saveResult(Result result) {
        resultRepository.save(result);
    }

    @Override
    public Result getResultById(Long id) {
        return resultRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteResultById(Long id) {
        resultRepository.deleteById(id);
    }
}
