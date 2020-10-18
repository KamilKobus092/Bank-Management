package pl.kkobusprogramming.bankmanagement.Account.api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import pl.kkobusprogramming.bankmanagement.Account.model.Account;
import pl.kkobusprogramming.bankmanagement.Account.model.PostRequestAccount;
import pl.kkobusprogramming.bankmanagement.Currency.api.CurrencyService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest
@DisplayName("Test cases for AccountController")
class AccountControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    protected AccountService accountService;

    @MockBean
    protected CurrencyService currencyService;

    @Test
    @DisplayName("it create account and return 201")
    void it_returns_201_when_account_is_created() throws Exception {
        Account account = new Account("90010100313", "", null);
        doReturn(account).when(accountService).createAccount(any(PostRequestAccount.class));

        mvc.perform(post("/account")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\t\"pesel\": \"90090900514\",\n" +
                        "\t\"fullname\": \"test test\",\n" +
                        "\t\"balance\": 100.0\n" +
                        "}\n"))
                .andExpect(status().isOk());
    }
}


//    @Test
//    @WithMockUser(authorities = "ROLE_ADMIN")
//    void it_return_201_and_create() throws Exception {
//        mockMvc.perform(post("/api/availability")
//                .contentType("application/json")
//                .accept(MediaType.APPLICATION_JSON)
//                .content("{\n" +
//                        "  \"id\": 5,\n" +
//                        "  \"declarantPerson\": {\n" +
//                        "    \"id\": 1,\n" +
//                        "    \"login\": \"hubert\",\n" +
//                        "    \"firstName\": \"Hubert\",\n" +
//                        "    \"lastName\": \"Lewandowski\",\n" +
//                        "    \"email\": \"hubert@tmobile.pl\",\n" +
//                        "    \"isActive\": null\n" +
//                        "  },\n" +
//                        "  \"absentPerson\": null,\n" +
//                        "  \"startDate\": \"2019-04-15T00:00:00.000+0000\",\n" +
//                        "  \"endDate\": \"2019-04-19T00:00:00.000+0000\",\n" +
//                        "  \"availability\": \"HOLIDAY\",\n" +
//                        "  \"overtime\": \"4\",\n" +
//                        "  \"description\": \"method post\"\n" +
//                        "}")
//                .with(csrf()))
//                .andExpect(status().isCreated());
//    }