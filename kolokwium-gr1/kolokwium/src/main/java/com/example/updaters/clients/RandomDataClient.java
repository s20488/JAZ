package com.example.updaters.clients;


import com.example.updaters.clients.contract.RandomPersonDto;

import java.util.List;

public class RandomDataClient implements IRandomDataClient{

    private String url;

    @Override
    public List<RandomPersonDto> getRandomPeople(int size) {
        return null;
    }
}
