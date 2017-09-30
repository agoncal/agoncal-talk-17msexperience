package org.agoncal.talk.msexperience.demo03.bookapi.client.isbn.api;

import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(value = "numberapi")
public interface ApiApiClient extends ApiApi {
}
