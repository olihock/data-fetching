package de.ithoc.datafetching.particulatematter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ParticulateMatterServiceTest {

    @Mock
    private ParticulateMatterRepository particulateMatterRepository;

    @InjectMocks
    private ParticulateMatterService particulateMatterService;

    @Test
    void read() {
        List<Example> examples = new java.util.ArrayList<>(List.of());
        int n = 3000;
        for(int i = 1; i <= n; i++) {
            examples.add(createExample(i));
        }
        Mockito.when(particulateMatterRepository.query()).thenReturn(examples);

        List<Example> read = particulateMatterService.read(Instant.now().minus(n, ChronoUnit.DAYS), Instant.now());

        Assertions.assertEquals(n, read.size());

    }

    private Example createExample(long offset) {
        Example example = new Example();
        example.setId(offset);
        example.setTimestamp(Instant.now().minus(offset, ChronoUnit.DAYS));
        return example;
    }

}