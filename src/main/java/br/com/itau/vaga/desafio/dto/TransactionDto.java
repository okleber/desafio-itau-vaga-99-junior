package br.com.itau.vaga.desafio.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.NonNull;

import java.time.OffsetDateTime;

@NonNull
public record TransactionDto(
        Double valor,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX") OffsetDateTime dataHora
        ){}
