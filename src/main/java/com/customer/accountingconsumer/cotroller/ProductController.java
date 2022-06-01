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
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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
@Validated
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
        return restTemplate.getForEntity
                (AppConstant.API, SockResponse.class, pageNo, pageSize, sortBy, sortDir)
                .getBody();
    }

    /**
     * find All Product
     *
     * @return List<Sock>
     */
    @GetMapping("/")
    public List<Sock> findAll() {
        return restTemplate.exchange(
                AppConstant.API_ALL,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Sock>>() {
                }
        ).getBody();
    }

    /**
     * Save Sock in DB
     *
     * @param sock Object
     * @return ResponseEntity<Sock>
     */
    @PostMapping("/")
    public ResponseEntity<Sock> save(@Valid @RequestBody Sock sock) {
        var rsl = Optional
                .of(restTemplate.postForEntity(AppConstant.API, sock, Sock.class));
        if (!rsl.get().hasBody()) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "We're sorry, server error, please try again later!");
        }
        return rsl.get();
    }

    /**
     * Find all products matching the specified parameters
     *
     * @param coloring   Object Sock
     * @param operator   not registered; "moreThan"; "lessThan";
     * @param cottonPart as a percentage
     * @return ResponseEntity<Sock [ ]>
     */
    @GetMapping("/socks")
    public ResponseEntity<Sock[]> findAllLike(@RequestParam("coloring")
                                              @NotBlank(message = "Coloring must not be empty!")
                                                      String coloring,
                                              @NotBlank(message = "Operator must not be empty!")
                                              @RequestParam("operator")
                                                      String operator,
                                              @RequestParam("cottonPart")
                                              @NotBlank(message = "CottonPart must not be empty!")
                                                      String cottonPart) {
        Optional<ResponseEntity<Sock[]>> rsl = Optional.of(
                restTemplate.getForEntity(
                        AppConstant.API_FIND, Sock[].class, coloring, operator, cottonPart
                ));
        if (!rsl.get().hasBody()) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "We're sorry, server error, please try again later!");
        }
        return rsl.get();
    }

    /**
     * Get a Sock using its id
     *
     * @param id (int) Sock Object int DB
     * @return ResponseEntity<Sock>
     */
    @GetMapping("/{id}")
    public ResponseEntity<Sock> findProductById(@Size(min = 1) @PathVariable Long id) {
        return restTemplate.getForEntity(AppConstant.API_ID,
                Sock.class, id);
    }

    /**
     * //todo patch
     * Update one parameter in to Sock
     * @param sock Object
     * @return ResponseEntity<Sock>
     */
    @PatchMapping("/")
    public ResponseEntity<Sock> patch(@Valid @RequestBody SockDtoPatch sock) {
        var rsl = Optional.of(restTemplate
                .patchForObject(AppConstant.API, sock, Sock.class));
        if (!rsl.get().hasBody()) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "We're sorry, server error, please try again later!");
        }
        return rsl.get();
    }

    /**
     * delete Sock using by Sock object id
     *
     * @param id Sock object
     * @return void
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@Size(min = 1) @PathVariable int id) {
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
}
