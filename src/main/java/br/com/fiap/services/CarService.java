package br.com.fiap.services;

import java.util.List;
import java.util.Optional;

import br.com.fiap.model.Car;
import br.com.fiap.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    public List<Car> findAll(){
        return carRepository.findAll();
    }

    public Optional<Car> findById(Long id){
        return carRepository.findById(id);
    }

    public void save(Car car){
        carRepository.save(car);
    }

    public void deleteById(Long id){
        carRepository.deleteById(id);
    }

}
