package com.serdeliverance.cryptowallet.services;

import com.serdeliverance.cryptowallet.dto.CurrencyQuoteDTO;
import com.serdeliverance.cryptowallet.dto.CurrencyTotalDTO;
import com.serdeliverance.cryptowallet.dto.PorfolioDTO;
import com.serdeliverance.cryptowallet.exceptions.InvalidOperationException;
import com.serdeliverance.cryptowallet.repositories.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.serdeliverance.cryptowallet.dto.PorfolioDTO.emptyPortfolio;
import static java.util.stream.Collectors.groupingBy;

@Service
@RequiredArgsConstructor
@Slf4j
public class PortfolioService {

    private final UserService userService;
    private final CryptocurrencyService cryptocurrencyService;
    private final TransactionRepository transactionRepository;

    public PorfolioDTO getPortfolio(Integer userId) {
        log.info("Getting portfolio for userId: {}", userId);
        userService.validateUser(userId);
        List<Transaction> transactions = transactionRepository.getByUser(userId);
        return !transactions.isEmpty() ? buildPorfolio(userId, transactions) : emptyPortfolio(userId, LocalDateTime.now());
    }

    private PorfolioDTO buildPorfolio(Integer userId, List<Transaction> transactions) {
        log.debug("Building crypto portfolio");
        Map<String, BigDecimal> quotesInUSD = cryptocurrencyService.quotes().stream()
                .collect(Collectors.toMap(CurrencyQuoteDTO::getCrypto, crypto -> crypto.getQuoteInUsd()));
        Map<Integer, String> cryptoMap = cryptocurrencyService
                .getByIdList(transactions.stream().map(tx -> tx.getCryptocurrencyId()).distinct().collect(Collectors.toList()))
                .stream()
                .collect(Collectors.toMap(Cryptocurrency::getId, crypto -> crypto.getName()));
        List<CurrencyTotalDTO> currencies = transactions.stream()
                .collect(groupingBy(Transaction::getCryptocurrencyId))
                .entrySet().stream()
                .map(entry ->
                        new CurrencyTotalDTO(
                                cryptoMap.get(entry.getKey()),
                                entry.getValue().stream()
                                        .map(tx -> tx.getAmount())
                                        .reduce(BigDecimal.ZERO, BigDecimal::add)))
                .collect(Collectors.toList());
        BigDecimal totalInUSD = currencies.stream()
                .map(crypto -> crypto.getAmount().multiply(quotesInUSD.get(crypto.getCurrency()))).reduce(BigDecimal.ZERO, BigDecimal::add);
        return new PorfolioDTO(userId, currencies, totalInUSD, LocalDateTime.now());
    }

    public void validateFunds(Integer userId, String cryptocurrency, BigDecimal amount) {
        log.info("Validating funds for selling/transferring data. userId={}, cryptocurrency={}, amount={}", userId, cryptocurrency, amount);
        BigDecimal currencyTotal = transactionRepository
                .getByUser(userId)
                .stream()
                .filter(tx -> tx.getCryptocurrencyId().equals(cryptocurrencyService.getByName(cryptocurrency).getId()))
                .map(Transaction::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        if (currencyTotal.compareTo(amount) < 0) throw new InvalidOperationException("Insufficient funds for transference/selling");
    }
}
