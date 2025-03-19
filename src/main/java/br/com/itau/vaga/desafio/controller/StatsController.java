package br.com.itau.vaga.desafio.controller;

import br.com.itau.vaga.desafio.dto.StatsDto;
import br.com.itau.vaga.desafio.service.StatsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.DoubleSummaryStatistics;


@RestController
public class StatsController {
    private static final Logger logger = LoggerFactory.getLogger(StatsController.class);
    @Autowired
    TransactionController transactionController;

    @GetMapping("/statistics")
    public ResponseEntity<String> getStats(){
        DoubleSummaryStatistics stats;
        stats = StatsService.getStats(transactionController.getTransactionDto());
        logger.info(stats.toString());
        return new ResponseEntity<>(stats.toString(),HttpStatus.OK);
    }
}
