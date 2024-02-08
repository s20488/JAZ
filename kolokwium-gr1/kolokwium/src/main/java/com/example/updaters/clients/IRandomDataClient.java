package com.example.updaters.clients;

import com.example.updaters.clients.contract.RandomPersonDto;

import java.util.List;

public interface IRandomDataClient {

    List<RandomPersonDto> getRandomPeople(int size);
}
