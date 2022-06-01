package com.customer.accountingconsumer.cotroller;

import com.customer.accountingconsumer.model.Sock;
import com.customer.accountingconsumer.model.SockDtoPatch;
import com.customer.accountingconsumer.model.SockResponse;
import com.customer.accountingconsumer.utils.AppConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Data
@RestController
@RequestMapping(value = "/template/products")
@AllArgsConstructor
public class ProductController {

    private RestTemplate restTemplate;

    /**
     * Pagination and Sorting Example
     *
     * @param pageNo   page number
     * @param pageSize number of entities per page
     * @param sortBy   sort in ascending or descending order
     * @param sortDir  default as ascending
     * @return SockResponse
     */
    @GetMapping("/")
    public SockResponse getAllSocks(
            @RequestParam(value = "pageNo",
                    defaultValue = AppConstant.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize",
                    defaultValue = AppConstant.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy",
                    defaultValue = AppConstant.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir",
                    defaultValue = AppConstant.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ) {
        var rsl = restTemplate.getForEntity(AppConstant.API, SockResponse.class, pageNo, pageSize, sortBy, sortDir);
        return rsl.getBody();
    }

    /**
     * find All Product
     *
     * @return List<Sock>
     */
    @GetMapping("/")
    public List<Sock> findAll() {
        return restTemplate.exchange(
                AppConstant.API,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Sock>>() {
                }
        ).getBody();
    }

    /**
     * Save Sock in DB
     *
     * @param sock
     * @return ResponseEntity<Sock>
     */
    @PostMapping("/")
    public ResponseEntity<Sock> save(@Valid @RequestBody Sock sock) {
        Optional<Sock> rsl = Optional.ofNullable(restTemplate
                .postForObject(AppConstant.API, sock, Sock.class));
        if (rsl.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "We're sorry, server error, please try again later!");
        }
        return new ResponseEntity<>(rsl.get(), CREATED);
    }

    /**
     * получить Паспорт используя его id
     *
     * @param id (int) Passport Object int DB
     * @return ResponseEntity<Passport>
     */
    @GetMapping("/{id}")
    public ResponseEntity<Sock> findProductById(@PathVariable Long id) {
        return restTemplate.getForEntity(AppConstant.API_ID,
                Sock.class, id);
    }

    /**
     * Update one parameter in to Sock
     *
     * @param sock
     * @return ResponseEntity<Sock>
     */
    @PatchMapping("/")
    public ResponseEntity<Sock> patch(@Valid @RequestBody SockDtoPatch sock) {
        Optional<Sock> rsl = Optional.ofNullable(restTemplate
                .postForObject(AppConstant.API, sock, Sock.class));
        if (rsl.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "We're sorry, server error, please try again later!");
        }
        return new ResponseEntity<>(rsl.get(), OK);
    }

    /**
     * delete Sock using by Sock object id
     *
     * @param id Sock object
     * @return void
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        var sock = Optional
                .ofNullable(restTemplate.getForEntity(AppConstant.API_ID,
                        Sock.class, id).getBody());
        if (sock.isEmpty()) {
            throw new IllegalArgumentException(
                    "The object id must be correct, object like this Id don't exist!");
        }
        restTemplate.delete(AppConstant.API_ID, id);
        return ResponseEntity.ok().build();
    }

//todo
//    /**
//     * find all Sock object which more ---
//     *
//     * @return ResponseEntity<List < Sock>>
//     */
//    @GetMapping("/unavaliabe")
//    public ResponseEntity<Sock[]> findByDateExpiretion() {
//        ResponseEntity<Sock[]> responseEntity = restTemplate.getForEntity(API_UNAVAILIABLE,
//                Sock[].class);
//        return responseEntity;
//    }
//

}
