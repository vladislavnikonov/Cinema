package edu.school21.cinema.services.impl;

import edu.school21.cinema.models.Hall;
import edu.school21.cinema.repositories.HallRepository;
import edu.school21.cinema.services.HallService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HallServiceImpl implements HallService {

    private final HallRepository hallRepository;

    public HallServiceImpl(@Qualifier("hallRepositoryImpl") HallRepository hallRepository) {
        this.hallRepository = hallRepository;
    }

    @Override
    public List<Hall> findAll() {
        return hallRepository.findAll();
    }

    @Override
    public boolean create(Hall hall) {
        if (hallRepository.getBySerialNumber(hall.getSerialNumber()) != null) {
            return false;
        }
        hallRepository.save(hall);
        return true;
    }
}
