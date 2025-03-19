package br.com.itau.vaga.desafio.controller;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import br.com.itau.vaga.desafio.dto.TransactionDto;
import br.com.itau.vaga.desafio.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
public class TransactionController {
    @Getter
    private final List<TransactionDto> transactionDto;
    private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

    TransactionController(TransactionService transactionService) {
        this.transactionDto=new ArrayList<>();
    }

    @PostMapping("/transaction")
    public ResponseEntity<String> postTransaction(@RequestBody TransactionDto transactionDto){

        Optional<String> status=TransactionService.validatePostTransaction(transactionDto);
        if(status.isPresent() && status.get().equals("OK")){
            this.transactionDto.add(transactionDto);
            logger.info("Todas transacoes criadas : {}",this.transactionDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        status.ifPresent(logger::info);
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @DeleteMapping("/transaction")
    public ResponseEntity<String> deleteTransaction() {
        this.transactionDto.clear();
        logger.info("Todas transacoes deletadas");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleInvalidJson(HttpMessageNotReadableException ex) {
        logger.info("Mensagem nao legivel");
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


}
