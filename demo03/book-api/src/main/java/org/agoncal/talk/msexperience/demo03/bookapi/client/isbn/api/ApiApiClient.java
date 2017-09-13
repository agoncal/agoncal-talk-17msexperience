package org.agoncal.talk.msexperience.demo03.bookapi.client.isbn.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.agoncal.talk.msexperience.demo03.bookapi.client.isbn.ClientConfiguration;

@FeignClient(name="${isbn.name:isbn}", url="${isbn.url:http://localhost:8081}", configuration = ClientConfiguration.class)
public interface ApiApiClient extends ApiApi {
}
