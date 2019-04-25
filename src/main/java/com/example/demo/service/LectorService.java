package com.example.demo.service;

import com.example.demo.dto.request.LectorFilterRequest;
import com.example.demo.dto.request.LectorRequest;
import com.example.demo.dto.response.LectorResponse;
import com.example.demo.entity.Degree;
import com.example.demo.entity.Lector;
import com.example.demo.exception.WrongInputException;
import com.example.demo.repository.LectorRepository;
import com.example.demo.specification.LectorSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LectorService {

    @Autowired
    private LectorRepository lectorRepository;


    public Lector findOne(Long id) throws WrongInputException {
        return lectorRepository.findById(id).orElseThrow(() -> new WrongInputException("Lector with " + id + " wasn`t found"));

    }

    public Lector lectorRequestToLector(Lector lector, LectorRequest lectorRequest) {
        if (lector == null) {
            lector = new Lector();
        }

        lector.setName(lectorRequest.getName());
        lector.setSurname(lectorRequest.getSurname());
        lector.setDegree(lectorRequest.getDegree());
        lector.setHead(lectorRequest.getHead());


        return lector;
    }

    public LectorResponse save(LectorRequest lectorRequest) {
        return new LectorResponse(lectorRepository.save(lectorRequestToLector(null, lectorRequest)));
    }

    public LectorResponse update(Long id, LectorRequest lectorRequest) throws WrongInputException {
        return new LectorResponse(lectorRepository.save(lectorRequestToLector(findOne(id), lectorRequest)));
    }

    public List<LectorResponse> findAll() {
        return lectorRepository.findAll().stream().map(LectorResponse::new).collect(Collectors.toList());
    }


    public List<LectorResponse> filter(LectorFilterRequest lectorFilterRequest) {
        return lectorRepository.findAll(new LectorSpecification(lectorFilterRequest)).stream().map(LectorResponse::new).collect(Collectors.toList());
    }


    public Long countAllByDegree(Degree degree) {
        return lectorRepository.countAllByDegreeLike(degree);
    }

    public String countDegree() {

        String newLine = "assistants - " + countAllByDegree(Degree.ASSISTANT) + "\n" +
                "associate professors - " + countAllByDegree(Degree.ASSOCIATE_PROFESSOR) + "\n" +
                "professors - " + countAllByDegree(Degree.PROFESSOR) + "\n";
        return newLine;
    }

    public String seekByPieceOf(String inputValue) {
        StringBuilder stringBuilder = new StringBuilder();
        LectorFilterRequest lectorFilterRequest = new LectorFilterRequest();
        lectorFilterRequest.setName(inputValue);
        for (LectorResponse lectorResponse : filter(lectorFilterRequest)) {
            String name = lectorResponse.getName();
            String surname = lectorResponse.getSurname();
            stringBuilder.append(surname + " " + name + "\n");
        }
        return stringBuilder.toString();
    }
}
