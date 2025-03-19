package br.com.itau.vaga.desafio.service;


import br.com.itau.vaga.desafio.dto.TransactionDto;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Stream;


@Service
public class StatsService {

    public static DoubleSummaryStatistics getStats(List<TransactionDto> transactions) {
        return transactions.stream()
                .filter(it -> it.dataHora().isAfter(OffsetDateTime.now().minusMinutes(60)))
                .map(TransactionDto::valor)
                .collect(DoubleSummaryStatistics::new,DoubleSummaryStatistics::accept,DoubleSummaryStatistics::combine);
    }
}
