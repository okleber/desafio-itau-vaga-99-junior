package br.com.itau.vaga.desafio.service;

import br.com.itau.vaga.desafio.dto.TransactionDto;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class TransactionService
{
    public static Optional<String> validatePostTransaction(TransactionDto transactionDto){
        Stream<Double> valor = Stream.of(transactionDto.valor())
                .filter(it -> it < 0);
        if(valor.findAny().isPresent()){
            return Optional.of("Value lower or equal to  0");
        }

        Stream<OffsetDateTime> dataHora = Stream.of(transactionDto.dataHora())
                .filter(it -> it.isAfter(OffsetDateTime.now()));
        if(dataHora.findAny().isPresent()){
            return Optional.of("Date in the future");
        }

        return Optional.of("OK");
    }
}
