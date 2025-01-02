package com.smartcity.smartcity.service.implementation;

import com.smartcity.smartcity.model.Facility;
import com.smartcity.smartcity.repository.FacilityRepository;
import com.smartcity.smartcity.service.FacilityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacilityServiceImpl implements FacilityService {
    private final FacilityRepository facilityRepository;

    public FacilityServiceImpl(FacilityRepository facilityRepository) {
        this.facilityRepository = facilityRepository;
    }

    @Override
    public Facility createFacility(Facility facility) {
        return facilityRepository.save(facility);
    }

    @Override
    public Facility updateFacility(Long id, Facility facility) {
        Facility existingFacility = facilityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Facility not found with id: " + id));
        existingFacility.setName(facility.getName());
        existingFacility.setDescription(facility.getDescription());
        existingFacility.setAddress(facility.getAddress());
        existingFacility.setCategory(facility.getCategory());
        existingFacility.setTags(facility.getTags());
        existingFacility.setOpeningHours(facility.getOpeningHours());
        existingFacility.setRating(facility.getRating());
        existingFacility.setAdditionalInfo(facility.getAdditionalInfo());
        existingFacility.setIsActive(facility.getIsActive());
        existingFacility.setImageUrl(facility.getImageUrl());
        return facilityRepository.save(existingFacility);
    }

    @Override
    public Facility getFacilityById(Long id) {
        return facilityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Facility not found with id: " + id));
    }

    @Override
    public List<Facility> getAllFacilities() {
        return facilityRepository.findAll();
    }

    @Override
    public void deleteFacility(Long id) {
        Facility facility = facilityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Facility not found with id: " + id));
        facilityRepository.delete(facility);
    }
}