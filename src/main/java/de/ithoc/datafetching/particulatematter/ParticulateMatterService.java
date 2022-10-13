package de.ithoc.datafetching.particulatematter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class ParticulateMatterService {

    @Autowired
    private ParticulateMatterRepository particulateMatterRepository;

    public ParticulateMatterService(ParticulateMatterRepository particulateMatterRepository) {
        this.particulateMatterRepository = particulateMatterRepository;
    }

    public List<Example> read(Instant from, Instant to) {

        List<Example> query = particulateMatterRepository.query();

        return query;
    }

}
