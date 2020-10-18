package pl.kkobusprogramming.bankmanagement.Currency.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(
        value = "/nbp",
        produces = {"application/json;charset=utf-8"})
public class CurrencyController {

    private final CurrencyService currencyService;

    @GetMapping("/ask/{code}")
    @ResponseStatus(HttpStatus.OK)
    public Double getAsk(@PathVariable String code) {
        return currencyService.getCurrencyAskParametr(code);
    }

    @GetMapping("/bid/{code}")
    @ResponseStatus(HttpStatus.OK)
    public Double getBid(@PathVariable String code) {
        return currencyService.getCurrencyBidParametr(code);
    }
}
